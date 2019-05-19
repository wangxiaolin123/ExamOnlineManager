package com.exam.dao;


import com.exam.domain.Teacher;
import org.apache.ibatis.session.SqlSessionException;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.sql.SQLException;
import java.util.List;

@Repository("teacherDao")
public class TeacherDao {

    @Resource(name = "sqlSessionTemplate")
    private SqlSessionTemplate sqlSessionTemplate;

    public  void updateTeacherByID(Teacher teacher) throws SQLException{
        sqlSessionTemplate.update("com.exam.mapper.TeacherMapper.updateTeacherByID",teacher);
    }

    public List getTeachers() throws SQLException{

        return sqlSessionTemplate.selectList("com.exam.mapper.TeacherMapper.getTeachers");
    }

    public int insertTeacher(Teacher teacher) throws SQLException{
       return sqlSessionTemplate.insert("com.exam.mapper.TeacherMapper.insertTeacher",teacher);
    }


    public Teacher getTeacherById(Integer teaID) throws SQLException{
        return (Teacher) sqlSessionTemplate.selectOne("com.exam.mapper.TeacherMapper.getTeacherById",teaID);
    }

    public Teacher getTeacherByTeaNumber(String teaNumber) throws SQLException{
        return (Teacher) sqlSessionTemplate.selectOne("com.exam.mapper.TeacherMapper.getTeacherByTeaNumber",teaNumber);
    }


    public void deleteByteaNumber(String teaNumber) throws SQLException{
        sqlSessionTemplate.delete("com.exam.mapper.TeacherMapper.deleteByteaNumber",teaNumber);
    }

    public List<Teacher> getByIsAdmin(boolean isadmin) throws SQLException {
       return sqlSessionTemplate.selectList("com.exam.mapper.TeacherMapper.getByIsAdmin",isadmin);
    }
}
