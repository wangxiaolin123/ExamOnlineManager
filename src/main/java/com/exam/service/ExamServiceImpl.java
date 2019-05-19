package com.exam.service;

import com.exam.dao.ExamDao;
import com.exam.dao.ExamStudentDao;
import com.exam.dao.StudentDao;
import com.exam.dao.UserDao;
import com.exam.domain.*;
import com.exam.utlis.FtpUtil;
import com.exam.utlis.ResultModel;
import com.exam.utlis.TimeJudge;
import com.exam.websocket.NoticeService;
import com.exam.websocket.WebSocketServer;
import org.apache.ibatis.jdbc.SQL;
import org.apache.ibatis.reflection.SystemMetaObject;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.sql.SQLException;
import java.util.*;


@Service("examService")
public class ExamServiceImpl implements ExamService {


    @Resource
    private ExamDao examDao;
    @Resource
    private ExamStudentDao examStudentDao;
    @Resource
    private StudentDao studentDao;

    @Resource
    private UserDao userDao;


    /**
     * 准备开始考试操作
     *
     * @param exam
     */
    @Override
    public void BeginExam(Exam exam) {


        if (!exam.getState().equals("underway")) {

            //查询所有和该考试相关的考生
            try {
                exam.setState("underway");
                examDao.updateExamById(exam);
                List<ExamStudent> esList = examStudentDao.getExamsByExamID(exam.getExamID());

                Notice notice = new Notice();
                notice.setExamID(exam.getExamID());
                notice.setNoticeTitle("考试开始");
                notice.setSignal("start");


                NoticeService.sendInfoToUser(ResultModel.build(200,"signal",notice),exam.getTeaNumber());

                //根据学号向考生推送考生开始信息
                //websocket通信实现

                List<String> stuNumbers = new ArrayList<>();
                for (ExamStudent es : esList) {
                    stuNumbers.add(es.getStuNumber());
                    System.out.println("刷新页面，通知学生" + es.getStuNumber() + "开始考试");
                }

                NoticeService.sendNotice(notice, stuNumbers);

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }else {
            System.out.println("考试《"+exam.getExamName()+"》已开始");
        }

    }

    /**
     * 考试结束，更新考试状态，发送考试通知
     *
     * @param exam
     */
    @Override
    public void EndExam(Exam exam) {


        //查询所有和该考试相关的考生
        try {

            List<ExamStudent> esList = examStudentDao.getExamsByExamID(exam.getExamID());

            //根据学号向考生推送考生开始信息
            //websocket通信实现
            Notice notice = new Notice();
            notice.setExamID(exam.getExamID());
            notice.setNoticeTitle("考试结束");
            notice.setSignal("end");

            NoticeService.sendInfoToUser(ResultModel.build(200,"signal",notice),exam.getTeaNumber());

            List<String> stuNumbers = new ArrayList<>();
            for (ExamStudent es : esList) {
                stuNumbers.add(es.getStuNumber());
                System.out.println("刷新页面，通知学生" + es.getStuNumber() + "考试结束");
            }
            NoticeService.sendNotice(notice, stuNumbers);

            //修改考试状态
            exam.setState("end");
            exam.setIsManager(false);
            examDao.updateExamById(exam);

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }


    @Override
    public void WaitForBegin(Date date) {

        TimeJudge.SleepTime(date);
    }

    @Override
    public void Examining(Exam exam) {

        System.out.println("正在考试，同时向每个学生更新剩余考试时间");
        TimeJudge.SleepTime(exam.getEndTime());
    }


    @Override
    public List<Exam> GetReadyExams(Integer minutes) {

        if (minutes == null)
            minutes = 15;
        try {
            List<Exam> list = examDao.getExamsForAllStates("before", false);

            List<Exam> readyExam = new ArrayList<>();
            for (int i = 0; i < list.size(); i++) {
                Exam exam = list.get(i);

                if (TimeJudge.JudgeBucket(exam.getStartTime(), 15)) {
                    exam.setIsManager(true);
                    examDao.updateExamById(exam);
                    System.out.println("ExamStart:15分钟内即将开始的考试" + exam.toString());
                    readyExam.add(exam);
                }
            }
            return readyExam;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public Map<String, Object> GetExamming(String stuNumber) {

        User user = userDao.getUserByName(stuNumber);
        if (user.getType() != 3) {
            System.out.println("非学生用户不予查询");
            return null;
        }

        Map<String, Object> map = new HashMap<String, Object>();
        List<Student> students = new ArrayList<>();

        try {
            List<Exam> examList = examDao.getExamsByState("underway");
            if (examList.size() == 0) {

                examList = examDao.getExamsForAllStates("before", true);
            }
            for (int i = 0; i < examList.size(); i++) {
                Exam exam = examList.get(i);
                ExamStudent es = examStudentDao.getExamsByExamIDStuNumber(exam.getExamID(), stuNumber);

                if (es != null) {

                    System.out.println("ExamService查询到考试" + exam.toString());

                    List<ExamStudent> esList = examStudentDao.getExamsByExamID(exam.getExamID());
                    map.put("teaNumber", exam.getTeaNumber());
                    for (ExamStudent examStudent : esList) {

                        Student student = studentDao.getStudentByStuNumber(examStudent.getStuNumber());

                        if (student != null) {
                            //System.out.println("ExamService 学生"+student.toString());

                            if(examStudent.getAnswerPath()!=null){
                                student.setSubmit(true);
                            }else {
                                student.setSubmit(false);
                            }
                            students.add(student);
                        } else {
                            System.out.println(examStudent.getStuNumber() + "学生用户不存在");
                        }
                    }
                    map.put("students", students);
                }
            }


            return map;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public List<String> getStudent(Integer examID) {


        List<String> stuNumbers = new ArrayList<>();
        try {
            stuNumbers = examStudentDao.getStuNumberByExam(examID);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return stuNumbers;
    }

    /**
     * 考试清理主要在打包下载后，清理考试信息（ExamStudent,ip，ftp服务器的文件，学生数据库可以暂存）
     *
     * @param exam
     */
    @Override
    public boolean CleanExam(Exam exam) {

        if (exam.getPack()) {
            try {

                List<String> stuNumbers = examStudentDao.getStuNumberByExam(exam.getExamID());
                for (String stuNumber : stuNumbers) {
                    //清理ip
                    userDao.updateIpAddrByNumber(stuNumber, null);
                }

                //删除考生考生信息
                examStudentDao.deleteByExamID(exam.getExamID());

                //删除考试
                examDao.delete(exam.getExamID());

                //删除考试文件信息
                FtpUtil.deleteDirectory("/public/", String.valueOf(exam.getExamID()));

                return true;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("考试文件尚未下载，不可清理");
        }
        return false;
    }

    @Override
    public void ExportExamInfo(Integer examID) {

        try {
            List<ExamStudent> esList = examStudentDao.getExamsByExamID(examID);
            List<Map<String, Object>> mapList = new ArrayList<>();
            for (ExamStudent es : esList) {
                Map<String, Object> map = new HashMap<>();
                map.put("stuNumber", es.getStuNumber());//学号
                map.put("answerTime", es.getAnswerTime());//最后提交时间
                Student student = studentDao.getStudentByStuNumber(es.getStuNumber());
                map.put("stuName", student.getStuName());//姓名
                map.put("classID", student.getClassID());//班级id
                mapList.add(map);
            }
//mapList为最后信息等待转换为Excel
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }


}
