package com.exam.service;

import com.exam.dao.TeacherDao;
import com.exam.dao.UserDao;
import com.exam.domain.Teacher;
import com.exam.domain.User;
import com.exam.exception.AdminTException;
import com.exam.utlis.MD5_Encoding;
import com.exam.utlis.ResultModel;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service("adminService")
public class AdminTServiceImpl implements AdminTService {

    @Resource
    private TeacherDao teacherDao;

    @Override
    public List<Teacher> getAllTeachers() throws AdminTException {
        List<Teacher> teacherList = new ArrayList<>();
        try {
            teacherList = (List<Teacher>) this.teacherDao.getTeachers();
            if (teacherList == null) {
                throw new AdminTException("获取教师列表失败");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return teacherList;
    }

    @Resource
    private UserDao userDao;
    @Override
    public ResultModel addTeacher(Teacher teacher) {
        teacher.setTeaID(null);
        try {
            this.teacherDao.insertTeacher(teacher);

            User user=new User();
            user.setUsername(teacher.getTeaNumber());
            user.setPassword(new MD5_Encoding().getMD5ofStr(teacher.getTeaNumber()));
            user.setType(2);

            this.userDao.insertUser(user);

            return ResultModel.ok(teacher);
        } catch (SQLException e) {
            e.printStackTrace();
            return ResultModel.build(500, "教师信息添加出错");
        }
    }
}
