package com.exam.dao;

import com.exam.domain.Exam;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository("examDao")
public class ExamDao {


    @Resource(name = "sqlSessionTemplate")
    private SqlSessionTemplate sqlSessionTemplate;

    /**
     * insert:(新建一场考试)
     *
     * @param exam
     * @return
     * @throws SQLException
     */
    public int insert(Exam exam) throws SQLException {
        int row=sqlSessionTemplate.insert("com.exam.mapper.ExamMapper.insert",exam);
        return 0;
    }

    /**
     * delete:(移除一场考试)
     * @param examID
     * @throws SQLException
     */
    public void delete(Integer examID) throws SQLException{
        sqlSessionTemplate.delete("com.exam.mapper.ExamMapper.delete",examID);
    }

    /**
     * modify:(修改考试信息)
     * @param exam
     * @throws SQLException
     */
    public void modify(Exam exam) throws SQLException{
        sqlSessionTemplate.update("com.exam.mapper.ExamMapper.modify",exam);
    }

    /**
     * queryExamsByTeacher:(查找教师创建的考试)
     * @param teaNumber 根据工号查询
     * @param state 根据状态查询
     * @return
     * @throws SQLException
     */
    public List<Exam> queryExamsByTeaNumber(String teaNumber, String state) throws SQLException{
        Map<String,Object> map=new HashMap<String, Object>();
        map.put("teaNumber",teaNumber);
        map.put("state",state);
        List<Exam> list=sqlSessionTemplate.selectList("com.exam.mapper.ExamMapper.queryExamsByTeaNumber",map);
        return list;
    }

    /**
     * queryExamsById:(根据考试号查找考试)
     * @param examID
     * @return
     * @throws SQLException
     */
    public Exam queryExamsById(int examID) throws SQLException{
        return (Exam)sqlSessionTemplate.selectOne("com.exam.mapper.ExamMapper.queryExamsById",examID);
    }

    /**
     * updateState:(设置考试状态)
     * @param examID
     * @param state
     * @throws SQLException
     */
    public void updateState(int examID, String state) throws SQLException{
        Map<String,Object> map=new HashMap<String, Object>();
        map.put("examID",examID);
        map.put("state",state);
        sqlSessionTemplate.update("com.exam.mapper.ExamMapper.updateState",map);
    }

    /**
     * @Description:(根据考试状态查询考试列表). <br/>
     * @param state
     * @return
     * @throws SQLException
     */
    public List<Exam> getExamsByState(String state) throws SQLException{
        List<Exam> list=sqlSessionTemplate.selectList("com.exam.mapper.ExamMapper.getExamsByState",state);
        return list;
    }

}
