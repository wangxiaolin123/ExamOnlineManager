package com.exam.service;

import com.exam.dao.UserDao;
import com.exam.domain.User;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("userService")
public class UserServiceImpl implements UserService {

    @Resource
    private UserDao userDao;

	public User selectUserByName(String username) {
		return this.userDao.getUserByName(username);
	}

	public User getUser(User user) {
		return this.userDao.getUser(user);
	}

}
