package com.exam.service;

import com.exam.domain.Exam;
import com.exam.utlis.ResultModel;

public interface TeacherService {

    ResultModel addExam(Exam exam);

    ResultModel modifyExam(Exam exam);

    ResultModel updateExamState(int examID, String state);

    ResultModel queryExams(int examID,String teaNumber, String state);
}
