package com.exam.service;

import com.exam.dao.ClassInfoDao;
import com.exam.domain.ClassInfo;
import com.exam.utlis.ResultModel;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service("classInfoService")
public class ClassInfoServiceImpl implements ClassInfoService {

    @Resource
    private ClassInfoDao classInfoDao;

    @Override
    public ResultModel deleteClassInfo(Integer classID) {

        try {
            classInfoDao.delete(classID);
            return ResultModel.ok();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ResultModel.build(500,"删除班级信息失败");
    }

    @Override
    public ResultModel updateClassInfo(ClassInfo classInfo) {
        try {
            classInfoDao.modify(classInfo);
            return ResultModel.ok();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ResultModel.build(500,"更新班级信息失败");
    }

    @Override
    public ResultModel getAllClassInfo() {
        List<ClassInfo> list=new ArrayList<>();
        try {
           list=classInfoDao.queryClassInfos();
            return ResultModel.ok(list);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
