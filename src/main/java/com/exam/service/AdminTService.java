package com.exam.service;

import com.exam.domain.Teacher;
import com.exam.exception.AdminTException;
import com.exam.utlis.ResultModel;

import java.util.List;


public interface AdminTService {

    public List<Teacher> getAllTeachers() throws AdminTException;

    public ResultModel addTeacher(Teacher teacher);
}
