package com.exam.service;

import com.exam.domain.Exam;
import com.exam.utlis.ResultModel;

import java.util.List;

public interface TeacherService {

    List<Exam> queryExamByTeacher(String teaNumber);

    ResultModel addExam(Exam exam);

    ResultModel deleteExam(Integer examID);

    ResultModel modifyExam(Exam exam);

    //ResultModel updateExamState(int examID, String state);

    //ResultModel queryExams(int examID,String teaNumber, String state);
}
