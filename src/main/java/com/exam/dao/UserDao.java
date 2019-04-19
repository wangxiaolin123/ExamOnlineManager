package com.exam.dao;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.exam.domain.User;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

@Repository("userDao")
public class UserDao {
	@Resource(name = "sqlSessionTemplate")
    private SqlSessionTemplate sqlSessionTemplate;
    
	public User getUserByName(String username){
        return (User)sqlSessionTemplate.selectOne("com.exam.mapper.UserMapper.getUserByName",username);
    }
	
	public User getUser(User user){
        return (User)sqlSessionTemplate.selectOne("com.exam.mapper.UserMapper.getUser",user);
    }

    public void updateIpAddrByID(Integer userID,String ipInfo) throws SQLException{
        Map<String,Object> map=new HashMap<String, Object>();
        map.put("userID", userID);
        map.put("ipInfo", ipInfo);
        sqlSessionTemplate.update("com.exam.mapper.UserMapper.updateIpAddrByID",map);
    }

    public void insertUser(User user) throws SQLException{
        sqlSessionTemplate.insert("com.exam.mapper.UserMapper.insertUser",user);
    }
}
