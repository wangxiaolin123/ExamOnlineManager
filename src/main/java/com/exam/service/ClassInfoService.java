package com.exam.service;

import com.exam.domain.ClassInfo;
import com.exam.utlis.ResultModel;

import java.util.List;


public interface ClassInfoService {


    public ResultModel deleteClassInfo(Integer classID);

    public ResultModel updateClassInfo(ClassInfo classInfo);

    public ResultModel getAllClassInfo();


}
