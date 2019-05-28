package com.exam.service;

import com.exam.dao.ClassInfoDao;
import com.exam.dao.TeacherDao;
import com.exam.dao.UserDao;
import com.exam.domain.ClassInfo;
import com.exam.domain.Teacher;
import com.exam.domain.User;
import com.exam.exception.AdminTException;
import com.exam.utlis.MD5_Encoding;
import com.exam.utlis.ResultModel;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service("adminService")
public class AdminTServiceImpl implements AdminTService {

    @Resource
    private TeacherDao teacherDao;
    @Resource
    private UserDao userDao;
    @Resource
    private ClassInfoDao classInfoDao;



    @Override
    public ResultModel deleteTeacher(String teaNumber) {
        try {
            userDao.deleteByUserName(teaNumber);
            teacherDao.deleteByteaNumber(teaNumber);
            return ResultModel.ok();
        } catch (SQLException e) {
            e.printStackTrace();
            return ResultModel.build(500, "系统出错");
        }
    }

    public ResultModel updateTeacher(Teacher teacher,String password) {
        try {
            Teacher t = teacherDao.getTeacherById(teacher.getTeaID());

            //System.out.println("更新"+t);
            if(t.getTeaNumber()!=teacher.getTeaNumber()){
                userDao.updateUserNameByUserName(teacher.getTeaNumber(),t.getTeaNumber());
            }
            //只修改改变部分
            if (password != null) {
                password=new MD5_Encoding().getMD5ofStr(password);
                userDao.updatePasswordByTeaNum(teacher.getTeaNumber(),password);
            }
            teacherDao.updateTeacherByID(teacher);
            teacher=teacherDao.getTeacherById(teacher.getTeaID());

            return ResultModel.ok(teacher);
        } catch (SQLException e) {
            e.printStackTrace();
            return ResultModel.build(500, "系统出错");
        }
    }

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

    @Override
    public ResultModel updateClass(ClassInfo classInfo) {

        try {
            classInfoDao.modify(classInfo);
            return ResultModel.ok();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ResultModel.build(500,"数据写入失败");

    }

    @Override
    public ResultModel deleteClass(Integer classID) {

        try {

            classInfoDao.delete(classID);
            return ResultModel.ok();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ResultModel.build(500,"数据删除失败");
    }

    @Override
    public List<ClassInfo> getAllClassInfos() {

        List<ClassInfo> list= null;
        try {
            list =classInfoDao.queryClassInfos();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public ResultModel addClassInfo(ClassInfo classInfo) {

        try {
            classInfoDao.insert(classInfo);
            return ResultModel.ok();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ResultModel.build(500,"数据添加失败");
    }
}
