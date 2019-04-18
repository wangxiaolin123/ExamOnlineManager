package com.exam.service;

import com.exam.dao.TeacherDao;
import com.exam.domain.Teacher;
import com.exam.exception.AdminException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service("adminService")
public class AdminServiceImpl implements AdminService {

    @Resource
    private TeacherDao teacherDao;
    @Override
    public List<Teacher> getAllTeachers() throws AdminException {
        List<Teacher>teacherList=new ArrayList<>();
        try {
            teacherList=(List<Teacher>)this.teacherDao.getTeachers();
            if(teacherList==null){
                throw new AdminException("获取教师列表失败");
            }
        }catch (SQLException e){
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return teacherList;
    }
}
