package com.exam.service;

import com.exam.dao.ExamDao;
import com.exam.dao.ExamStudentDao;
import com.exam.domain.Exam;
import com.exam.domain.ExamStudent;
import com.exam.utlis.FtpUtil;
import com.exam.utlis.ResultModel;
import org.omg.Messaging.SYNC_WITH_TRANSPORT;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service("studentService")
public class StudentServiceImpl implements StudentService {

    @Resource
    private ExamStudentDao examStudentDao;
    @Resource
    private ExamDao examDao;

    @Override
    public ResultModel getExamsByNumberState(String stuNumber, String state) {
        try {
            List<ExamStudent> examStudentList = null;
            examStudentList = examStudentDao.getExamsByNumber(stuNumber);
            List<Exam> examList = new ArrayList<>();
            for (int i = 0; i < examStudentList.size(); i++) {
                ExamStudent examStudent = examStudentList.get(i);
                Exam exam = examDao.queryExamById(examStudent.getExamID());
                examList.add(exam);
            }
            return ResultModel.ok(examList);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public Exam getExamPager(Integer examID) {

        try {
            Exam exam = examDao.queryExamById(examID);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public ResultModel upAnswer(MultipartFile file, Integer examID, String stuNumber) {

        try {

            String originalName = file.getOriginalFilename();
            String suffix = originalName.substring(originalName.lastIndexOf("."));
            // 时间戳+随机数生成文件名
            String fileName = String.valueOf(System.currentTimeMillis()) + (int) ((Math.random() * 9 + 1) * 100000) + suffix;

            String path = "/public/" + examID + "/";

            // 将文件转化为字节流
            try {
                InputStream is = file.getInputStream();
                boolean uploadRes = FtpUtil.uploadFile(path, fileName, is);
                if (uploadRes) {

                    examStudentDao.updateAnswerPathByStuNumber(examID, stuNumber, path+ fileName, new Date());
                    return ResultModel.ok();
                } else {
                    return ResultModel.build(500, "答卷信息更新失败");
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return ResultModel.build(500, "上传失败");
    }
}
