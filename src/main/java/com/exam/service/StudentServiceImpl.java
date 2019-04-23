package com.exam.service;

import com.exam.dao.ExamDao;
import com.exam.dao.ExamStudentDao;
import com.exam.domain.Exam;
import com.exam.domain.ExamStudent;
import com.exam.utlis.ResultModel;
import org.omg.Messaging.SYNC_WITH_TRANSPORT;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.SQLException;
import java.util.ArrayList;
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
            List<ExamStudent> examStudentList=null;
           examStudentList=examStudentDao.getExamsByNumber(stuNumber);
           List<Exam> examList=new ArrayList<>();
           for (int i=0;i<examStudentList.size();i++){
               ExamStudent examStudent=examStudentList.get(i);
               Exam exam=examDao.queryExamById(examStudent.getExamID());
               examList.add(exam);
           }
            return ResultModel.ok(examList);
        }catch (SQLException e){
            e.printStackTrace();
        }

        return null;
    }
}
