package com.exam.service;

import com.exam.dao.StudentDao;
import com.exam.dao.TeacherDao;
import com.exam.dao.UserDao;
import com.exam.domain.Student;
import com.exam.domain.Teacher;
import com.exam.domain.User;
import com.exam.exception.UserException;
import com.exam.utlis.MD5_Encoding;
import com.exam.utlis.ResultModel;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

@Service("userService")
public class UserServiceImpl implements UserService {

    @Resource
    private UserDao userDao;
    @Resource
    private StudentDao studentDao;
    @Resource
    private TeacherDao teacherDao;

    public ResultModel isLogin(User user) throws UserException {

        try {
            //未输入
            if (user.getUsername() == null || user.getPassword() == null || user.getType() == null || user.getIp() == null) {
                //session提示
                throw new UserException("格式不正确，请重新输入！");

            }

            // 1 验证用户名存在不存在？
            User user1 = this.userDao.getUserByName(user.getUsername());

            if (user1 == null) {
                throw new UserException("用户名不存在！");
            } else {
                String md5_password = new MD5_Encoding().getMD5ofStr(user.getPassword());
                if (!md5_password.equals(user1.getPassword())) {
                    throw new UserException("密码错误！");
                } else {
                    if (user1.getType() != user.getType()) {
                        throw new UserException("类型错误！");
                    } else {

                        if (user1.getIp() == null) {

                            userDao.updateIpAddrByID(user1.getUserID(),user.getIp());
                            user1.setIp(user.getIp());

                        }
                        if(user1.getType()==3){

                            if (user.getIp().equals(user1.getIp())) {
                                user1.setIp(user.getIp());
                                Student student=studentDao.getStudentByStuNumber(user.getUsername());
                                Map<String,String> map=new HashMap<String, String>();
                                map.put("number",student.getStuNumber());
                                map.put("name",student.getStuName());

                                //实验测试使用
                                map.put("ip",user.getIp());

                                return ResultModel.ok(map);

                            } else {
                                throw new UserException("IP分配错误，请联系任课教师或管理员！");
                            }
                        }else {
                            userDao.updateIpAddrByID(user1.getUserID(),user.getIp());
                            user1.setIp(user.getIp());
                            Map<String,Object> map=new HashMap<String, Object>();
                            if(!user.getUsername().equals("admin")){
                                Teacher teacher=teacherDao.getTeacherByTeaNumber(user1.getUsername());
                                map.put("number",teacher.getTeaNumber());
                                map.put("name",teacher.getTeaName());
                                map.put("isadmin",teacher.getIsadmin());

                                //实验测试使用
                                map.put("ip",user.getIp());

                            }else {
                                map.put("name",user.getUsername());
                            }

                            return ResultModel.ok(map);
                        }



                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public User getUser(User user) throws UserException {

        return null;
    }

}
