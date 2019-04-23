package com.exam.service;

import com.exam.dao.ExamDao;
import com.exam.domain.Exam;
import com.exam.utlis.ResultModel;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.SQLException;
import java.util.List;

@Service("teacherService")
public class TeacherServiceImpl implements TeacherService{

    @Resource
    private ExamDao examDao;

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
            examDao.modify(exam);
            return ResultModel.ok();
        } catch (SQLException e) {
            e.printStackTrace();
            return ResultModel.build(500, "系统出错");
        }
    }
}
