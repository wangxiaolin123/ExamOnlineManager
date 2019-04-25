package com.exam.service;

import com.exam.dao.ExamDao;
import com.exam.domain.Exam;
import com.exam.utlis.FtpUtil;
import com.exam.utlis.ResultModel;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.io.InputStream;
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

    @Override
    public ResultModel upPaper(MultipartFile file, Integer examID) {

        try{
            Exam exam=examDao.queryExamById(examID);


            String originalName = file.getOriginalFilename();
            String suffix = originalName.substring(originalName.lastIndexOf("."));
            // 时间戳+随机数生成文件名
            String fileName =String.valueOf(System.currentTimeMillis())+(int)((Math.random()*9+1)*100000)+suffix;

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
}
