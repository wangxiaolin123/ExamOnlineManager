package com.exam.dao;


import com.exam.domain.Student;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.sql.SQLException;
import java.util.List;

@Repository("studentDao")
public class StudentDao {

    @Resource(name = "sqlSessionTemplate")
    private SqlSessionTemplate sqlSessionTemplate;
    private static String path="com.exam.mapper.StudentMapper.";
    /**
     * insert:(倒入学生信息，包含班级的id). <br/>
     * @param student
     * @throws SQLException
     */
    public void insert(Student student) throws SQLException{

        sqlSessionTemplate.insert(path+"insert",student);
    }

    /**
     * deleteByStuNumber:(根据学生id和考试id移除学生名单). <br/>
     * @param stuNumber
     * @throws SQLException
     */
    public void deleteByStuNumber(String stuNumber) throws SQLException{

        sqlSessionTemplate.delete(path+"deleteByStuNumber",stuNumber);
    }


    /**
     * getStudentByStuNumber:(get Student info by student's id). <br/>
     * @param stuNumber
     * @return Student
     * @throws SQLException
     */
    public Student getStudentByStuNumber(String stuNumber) throws SQLException{

        return (Student)sqlSessionTemplate.selectOne(path+"getStudentByStuNumber",stuNumber);
    }

    /**
     * @Description:(get Student info by classID). <br/>
     * @param classID 班级id
     * @return
     * @throws SQLException
     */
    public List<Student> queryStudentsByClass(Integer classID) throws SQLException{

        List<Student> list=sqlSessionTemplate.selectList(path+"queryStudentsByClass",classID);
        return list;
    }

    /**
     * modify:(修改学生信息). <br/>
     * @param student void
     * @throws SQLException
     */
    public void modifyByID(Student student) throws SQLException{
        sqlSessionTemplate.update(path+"modifyByID",student);
    }

}
