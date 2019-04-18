package com.exam.service;

import com.exam.dao.UserDao;
import com.exam.domain.User;
import com.exam.exception.UserException;
import com.exam.utlis.MD5_Encoding;
import org.apache.ibatis.jdbc.SQL;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.SQLException;

@Service("userService")
public class UserServiceImpl implements UserService {

    @Resource
    private UserDao userDao;

    public User isLogin(User user) throws UserException {

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
                            return user1;

                        }
                        if(user1.getType()==3){

                            if (user.getIp().equals(user1.getIp())) {
                                user1.setIp(user.getIp());
                                return user1;

                            } else {
                                throw new UserException("IP分配错误，请联系任课教师或管理员！");
                            }
                        }else {
                            userDao.updateIpAddrByID(user1.getUserID(),user.getIp());
                            user1.setIp(user.getIp());
                            return user1;
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
