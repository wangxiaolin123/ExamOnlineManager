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
public class UserServiceImpl implements UserService{

    @Resource
    private UserDao userDao;

	public User isLogin(String username,String password,Integer type) throws UserException{

		try {
			//未输入
			if (username == null || password == null || type == null) {
				//session提示
				throw new UserException("格式不正确，请重新输入！");

			}

			// 1 验证用户名存在不存在？
			User user1=this.userDao.getUserByName(username);
			if (user1 == null) {
				throw new UserException("用户名不存在！");
			} else {
				String md5_password = new MD5_Encoding().getMD5ofStr(password);
				if (!md5_password.equals(user1.getPassword())) {
					throw new UserException("密码错误！");
				} else {
					if(user1.getType()==type) {
						return user1;
					}
					else {
						throw new UserException("类型错误！");
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
