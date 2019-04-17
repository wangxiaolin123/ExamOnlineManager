package com.exam.service;

import com.exam.domain.User;
import com.exam.exception.UserException;

public interface UserService {

    public User isLogin(String username,String password,Integer type) throws UserException;
    
    public User getUser(User user) throws UserException;
}
