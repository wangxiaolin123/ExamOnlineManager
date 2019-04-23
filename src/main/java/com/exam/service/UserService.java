package com.exam.service;

import com.exam.domain.User;
import com.exam.exception.UserException;
import com.exam.utlis.ResultModel;

public interface UserService {

    ResultModel isLogin(User user) throws UserException;
    
    User getUser(User user) throws UserException;
}
