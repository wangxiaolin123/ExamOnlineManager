package com.exam.service;

import com.exam.domain.Exam;


import java.util.Date;
import java.util.List;
import java.util.Map;

public interface ExamService {

    public void BeginExam(Exam exam);

    public void EndExam(Exam exam);

    public void WaitForBegin(Date date);

    public void Examining(Exam exam);

    public void ForceBegin(Exam exam);


    public List<Exam> GetReadyExams(Integer minutes);

    public Map<String,Object> GetExamming(String stuNumber);


}
