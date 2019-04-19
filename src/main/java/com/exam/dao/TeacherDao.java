package com.exam.dao;


import com.exam.domain.Teacher;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.sql.SQLException;
import java.util.List;

@Repository("teacherDao")
public class TeacherDao {

    @Resource(name = "sqlSessionTemplate")
    private SqlSessionTemplate sqlSessionTemplate;

    public List getTeachers() throws SQLException{

        return sqlSessionTemplate.selectList("com.exam.mapper.TeacherMapper.getTeachers");
    }

    public int insertTeacher(Teacher teacher) throws SQLException{
       return sqlSessionTemplate.insert("com.exam.mapper.TeacherMapper.insertTeacher",teacher);
    }


}
