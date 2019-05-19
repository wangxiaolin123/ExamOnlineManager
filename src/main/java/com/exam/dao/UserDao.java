package com.exam.dao;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.exam.domain.User;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
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

    public void updateIpAddrByNumber(String number,String ipInfo) throws SQLException{
        Map<String,Object> map=new HashMap<String, Object>();
        map.put("number", number);
        map.put("ipInfo", ipInfo);
        sqlSessionTemplate.update("com.exam.mapper.UserMapper.updateIpAddrByNumber",map);
    }

    public void insertUser(User user) throws SQLException{
        sqlSessionTemplate.insert("com.exam.mapper.UserMapper.insertUser",user);
    }

    public void updatePasswordByTeaNum(String userName,String password) throws SQLException{
        Map<String,Object> map=new HashMap<String, Object>();
        map.put("userName",userName);
        map.put("password", password);
        sqlSessionTemplate.update("com.exam.mapper.UserMapper.updatePasswordByUserName",map);

    }

    public void updateUserNameByUserName(String newUsername, String oldUserName) throws SQLException{
        Map<String,Object> map=new HashMap<String, Object>();
        map.put("newUserName",newUsername);
        map.put("oldUserName",oldUserName);
        sqlSessionTemplate.update("com.exam.mapper.UserMapper.updateUserNameByUserName",map);
    }
    public void deleteByUserName(String username) throws SQLException{
        sqlSessionTemplate.delete("com.exam.mapper.UserMapper.deleteByUserName",username);
    }

    public void insertUsers(List<User>list) throws SQLException{
        sqlSessionTemplate.insert("com.exam.mapper.UserMapper.insertUsers",list);
    }

    public void notifyByID(User user) throws SQLException {
        sqlSessionTemplate.update("com.exam.mapper.UserMapper.notifyByID",user);
    }

    public void deleteByUserNames(List stuNumbers) throws SQLException{
        sqlSessionTemplate.update("com.exam.mapper.UserMapper.deleteByUserNames",stuNumbers);
    }
}
