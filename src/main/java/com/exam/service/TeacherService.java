package com.exam.service;

import com.exam.domain.Exam;
import com.exam.utlis.ResultModel;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface TeacherService {

    List<Exam> queryExamByTeacher(String teaNumber);

    ResultModel addExam(Exam exam);

    ResultModel deleteExam(Integer examID);

    ResultModel modifyExam(Exam exam);

    ResultModel upPaper(MultipartFile file, Integer examID);

    //ResultModel updateExamState(int examID, String state);

    //ResultModel queryExams(int examID,String teaNumber, String state);
}
