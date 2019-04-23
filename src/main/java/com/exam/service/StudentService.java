package com.exam.service;

import com.exam.utlis.ResultModel;

public interface StudentService {

    ResultModel getExamsByNumberState(String stuNumber,String state);
}
