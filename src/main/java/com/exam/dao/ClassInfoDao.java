package com.exam.dao;

import com.exam.domain.ClassInfo;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository("classInfoDao")
public class ClassInfoDao {


    @Resource(name = "sqlSessionTemplate")
    private SqlSessionTemplate sqlSessionTemplate;
    private static String path="com.exam.mapper.ClassInfoMapper.";
    /**
     * insert:(新建班级)
     *
     * @param
     * @return
     * @throws SQLException
     */
    public void insert(ClassInfo classInfo) throws SQLException {
        sqlSessionTemplate.insert(path+"insert",classInfo);
    }

    /**
     * delete:(移除班级)
     * @param classID
     * @throws SQLException
     */
    public void delete(Integer classID) throws SQLException{
        sqlSessionTemplate.delete(path+"delete",classID);
    }

    /**
     * modify:(修改考试信息)
     * @param classInfo
     * @throws SQLException
     */
    public void modify(ClassInfo classInfo) throws SQLException{
        sqlSessionTemplate.update(path+"modify",classInfo);
    }



    /**
     * queryClassInfos:(根据考试号查找考试)
     * @throws SQLException
     */
    public List<ClassInfo> queryClassInfos() throws SQLException{
        return sqlSessionTemplate.selectList(path+"queryClassInfos");
    }


    public String getClassNameByID(Integer classID) {
        return sqlSessionTemplate.selectOne(path+"getClassNameByID",classID);
    }
}
