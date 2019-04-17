package com.exam.dao;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.exam.domain.User;

import java.sql.SQLException;

@Repository("userDao")
public class UserDao {
	@Resource(name = "sqlSessionTemplate")
    private SqlSessionTemplate sqlSessionTemplate;
    
	public User getUserByName(String username) throws SQLException{
        return (User)sqlSessionTemplate.selectOne("com.exam.mapper.UserMapper.getUserByName",username);
    }
	
	public User getUser(User user){
        return (User)sqlSessionTemplate.selectOne("com.exam.mapper.UserMapper.getUser",user);
    }
}
