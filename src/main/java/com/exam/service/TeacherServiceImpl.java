package com.exam.service;

import com.exam.dao.ExamDao;
import com.exam.dao.ExamStudentDao;
import com.exam.dao.StudentDao;
import com.exam.dao.UserDao;
import com.exam.domain.Exam;
import com.exam.domain.Student;
import com.exam.domain.User;
import com.exam.exception.ExcelException;
import com.exam.utlis.ExcelUtil;
import com.exam.utlis.FtpUtil;
import com.exam.utlis.MD5_Encoding;
import com.exam.utlis.ResultModel;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service("teacherService")
public class TeacherServiceImpl implements TeacherService{

    @Resource
    private ExamDao examDao;
    @Resource
    private ExamStudentDao examStudentDao;
    @Resource
    private UserDao userDao;
    @Resource
    private StudentDao studentDao;

    @Override
    public List<Exam> queryExamByTeacher(String teaNumber) {
        try {
            List<Exam> list=examDao.queryExamsByTeaNumber(teaNumber);
            return list;
        }catch (SQLException e){
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public Exam getExamByID(Integer examID) {

        try {
            return examDao.queryExamById(examID);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public ResultModel addExam(Exam exam) {
        try{
            examDao.insert(exam);
            return ResultModel.ok();
        }catch (SQLException e){
            e.printStackTrace();
            return ResultModel.build(500,"添加考试错误");
        }
    }

    @Override
    public ResultModel deleteExam(Integer examID) {
        try {
            examDao.delete(examID);
            return ResultModel.ok();
        } catch (SQLException e) {
            e.printStackTrace();
            return ResultModel.build(500, "系统出错");
        }
    }

    @Override
    public ResultModel modifyExam(Exam exam) {
        try {
            //examDao.modify(exam);
            examDao.updateExamById(exam);
            return ResultModel.ok();
        } catch (SQLException e) {
            e.printStackTrace();
            return ResultModel.build(500, "系统出错");
        }
    }

    @Override
    public ResultModel upPaper(MultipartFile file, Integer examID) {

        try{
            Exam exam=examDao.queryExamById(examID);


            String originalName = file.getOriginalFilename();
            String suffix = originalName.substring(originalName.lastIndexOf("."));
            // 时间戳+随机数生成文件名
            String fileName =String.valueOf(System.currentTimeMillis())+(int)((Math.random()*9+1)*100000)+suffix;

            //System.out.println("教师上传 文件名"+fileName+"后缀名"+suffix);
            String path="/public/"+examID+"/";

            // 将文件转化为字节流
            try {
                InputStream is = file.getInputStream();
                boolean uploadRes = FtpUtil.uploadFile(path, fileName, is);
              if(uploadRes){
                  exam.setExamPath(path+fileName);

                  examDao.updateExamById(exam);
                  return ResultModel.ok();
              } else{
                  return ResultModel.build(500,"试卷信息更新失败");
              }

            }catch (IOException e){
                e.printStackTrace();
            }
        }catch (SQLException e){
            e.printStackTrace();
        }

        return ResultModel.build(500,"上传失败");
    }

    @Override
    public List<Map<String, String>> getStuNumberFile(Integer examID) {

        try {
            List<Map<String,String>> mapList=examStudentDao.getNumPathByExamID(examID);
            return mapList;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public ResultModel unBindIp(String stuNumber, Integer examID) {

        if(stuNumber==null||stuNumber.equals("")){
            return ResultModel.build(500,"学生学号不合法");
        }

        try {
            List<String> stuNumbers=examStudentDao.getStuNumberByExam(examID);
            for(String s:stuNumbers){
                if(stuNumber.equals(s)){
                    userDao.updateIpAddrByNumber(stuNumber,null);
                    return ResultModel.ok();
                }
            }
            return ResultModel.build(500,"学号未找到");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return ResultModel.build(500,"解除学号绑定失败");
    }

    @Override
    public ResultModel importAllStudent(MultipartFile multipartFile) {

        List<Student> list= null;
        try {
            list = ExcelUtil.readExcel(multipartFile, Student.class);
        } catch (ExcelException e) {
            System.out.println("excel异常抛出");
            e.printStackTrace();
        }
        if(list!=null){

            String stuNumbers="";
            List<Student> studentList=new ArrayList<>();
            for(int i=0;i<list.size();i++){

                Student student=list.get(i);

                if(!isExist(student.getStuNumber())){
                    studentList.add(student);
                }else {
                    stuNumbers+=student.getStuNumber()+",";
                }
            }

            if(studentList==null ||studentList.size()==0){

                return ResultModel.build(500,"所有学生信息已经存在");
            }

            List<User> users=new ArrayList<>();
            for(int i=0;i<studentList.size();i++){
                Student student=studentList.get(i);
                User user=new User();
                user.setUsername(student.getStuNumber());
                user.setPassword(new MD5_Encoding().getMD5ofStr(student.getStuName()));
                user.setType(3);
                users.add(user);
            }
            try {
                studentDao.insertStudents(studentList);
                userDao.insertUsers(users);
                String msg="";
                if(stuNumbers.length()>0)
                {
                    msg="插入成功，部分学生已存在("+stuNumbers+")";
                }else {
                    msg="全部数据插入完毕";
                }
                return ResultModel.build(200,msg);
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
        return ResultModel.build(500,"批量导入失败");
    }

    private boolean isExist(String stuNumber) {


        try {
           Student student1= studentDao.getStudentByStuNumber(stuNumber);
           if(student1!=null){
               return true;
           }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public ResultModel importStudent(Student student) {

        try {
            Student student1=studentDao.getStudentByStuNumber(student.getStuNumber());
            if(student1==null)
            {
                User user=new User();
                user.setUsername(student.getStuNumber());
                user.setPassword(new MD5_Encoding().getMD5ofStr(student.getStuName()));
                user.setType(3);
                studentDao.insert(student);
                userDao.insertUser(user);
                return ResultModel.ok();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ResultModel.build(500,"添加失败，学生信息已存在");
    }

    @Override
    public List<Student> getAllStudents() {
        List<Student> list= null;
        try {
            list = studentDao.getAllStudents();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public ResultModel updateStudent(Student student) {

       if(updateUserAndStudnet(student))
           return ResultModel.ok();
        return ResultModel.build(500,"数据写入失败");
    }

    @Override
    public ResultModel deleteStudent(String stuNumber) {

        try {
            userDao.deleteByUserName(stuNumber);
            studentDao.deleteByStuNumber(stuNumber);
            return ResultModel.ok();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ResultModel.build(500,"数据删除失败");
    }

    private boolean updateUserAndStudnet(Student student){

        try {
        Student oldStudent=studentDao.getStudnetByID(student.getStuID());
        User oldUser=userDao.getUserByName(oldStudent.getStuNumber());

        User newUser=new User();
        newUser.setUserID(oldUser.getUserID());
        newUser.setUsername(student.getStuNumber());
        newUser.setPassword(new MD5_Encoding().getMD5ofStr(student.getStuName()));
        newUser.setType(3);
        newUser.setIp(oldUser.getIp());


            userDao.notifyByID(newUser);
            studentDao.modifyByID(student);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }


        return false;
    }


}
