package com.exam.service;

import com.exam.domain.ClassInfo;
import com.exam.domain.Teacher;
import com.exam.exception.AdminTException;
import com.exam.utlis.ResultModel;

import java.util.List;


public interface AdminTService {


    public ResultModel deleteTeacher(String teaNumber);

    public ResultModel updateTeacher(Teacher teacher,String password);

    public List<Teacher> getAllTeachers() throws AdminTException;

    public ResultModel addTeacher(Teacher teacher);

    public ResultModel updateClass(ClassInfo classInfo);

    public ResultModel deleteClass(Integer classID);

    List<ClassInfo> getAllClassInfos();

    ResultModel addClassInfo(ClassInfo classInfo);
}
