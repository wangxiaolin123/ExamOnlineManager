package com.exam.service;

import com.exam.domain.User;

public interface UserService {

    public User selectUserByName(String username);
    
    public User getUser(User user);
}
