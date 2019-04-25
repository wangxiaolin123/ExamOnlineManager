package com.exam.service;

import com.exam.domain.Exam;
import com.exam.utlis.ResultModel;
import org.springframework.web.multipart.MultipartFile;

public interface StudentService {

    ResultModel getExamsByNumberState(String stuNumber,String state);

    Exam getExamPager(Integer examID);

    ResultModel upAnswer(MultipartFile file, Integer examID,String stuNumber);
}
