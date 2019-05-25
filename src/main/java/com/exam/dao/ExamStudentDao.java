package com.exam.dao;

import com.exam.domain.ExamStudent;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository("examStudentDao")
public class ExamStudentDao {

    @Resource(name = "sqlSessionTemplate")
    private SqlSessionTemplate sqlSessionTemplate;

    private static String path="com.exam.mapper.ExamStudentMapper.";

    public void insert(ExamStudent examStudent) throws SQLException{

        sqlSessionTemplate.insert(path+"insert", examStudent);
    }

    public void deleteBySidEid(String stuNumber,Integer examID) throws SQLException{

        Map<String,Object> map=new HashMap<String, Object>();
        map.put("stuNumber",stuNumber);
        map.put("examID",examID);
        sqlSessionTemplate.delete(path+"deleteBySidEid",map);
    }

    public void deleteByExamID(Integer examID) throws SQLException{

        sqlSessionTemplate.delete(path+"deleteByExamID",examID);
    }

    public void updateAnswerPathByStuNumber(Integer examID,String stuNumber, String answerPath, @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")Date answerTime)throws SQLException{
        Map<String,Object> map=new HashMap<String, Object>();
        map.put("examID",examID);
        map.put("stuNumber",stuNumber);
        map.put("answerPath",answerPath);
        map.put("answerTime",answerTime);
        sqlSessionTemplate.update(path+"updateAnswerPathByStuNumber",map);
    }



    public List<ExamStudent> getExamsByNumber(String stuNumber) throws SQLException{
        return sqlSessionTemplate.selectList(path+"getExamsByNumber",stuNumber);
    }

    public List<ExamStudent> getExamsByExamID(Integer examID) throws SQLException{
        return sqlSessionTemplate.selectList(path+"getExamsByExamID",examID);
    }

    public ExamStudent getExamsByExamIDStuNumber(Integer examID,String stuNumber) throws SQLException{
        Map<String,Object> map=new HashMap<String, Object>();
        map.put("examID",examID);
        map.put("stuNumber",stuNumber);
        return sqlSessionTemplate.selectOne(path+"getExamsByExamIDStuNumber",map);
    }


    public List<String> getStuNumberByExam(Integer examID) throws SQLException{
        return sqlSessionTemplate.selectList(path+"getStuNumberByExam",examID);
    }

    public List<Map<String, String>> getNumPathByExamID(Integer examID)  throws SQLException{
        return sqlSessionTemplate.selectList(path+"getNumPathByExamID",examID);
    }

    public void insertBatch(List<ExamStudent> esList) throws SQLException {
        sqlSessionTemplate.insert(path+"insertBatch",esList);
    }
}
