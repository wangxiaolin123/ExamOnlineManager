package com.exam.service;

import com.exam.domain.Exam;
import com.exam.domain.ExamStudent;
import com.exam.domain.ExportStudentInfo;
import com.exam.domain.Student;
import com.exam.utlis.ResultModel;


import java.util.Date;
import java.util.List;
import java.util.Map;

public interface ExamService {

    public void BeginExam(Exam exam);

    public void EndExam(Exam exam);

    public void WaitForBegin(Date date);

    public void Examining(Exam exam);

    public List<Exam> GetReadyExams(Integer minutes);

    public Map<String,Object> GetExamming(String stuNumber);

    List<String> getStudent(Integer examID);

    public boolean CleanExam(Exam exam);

    List<ExportStudentInfo> ExportExamInfo(Integer examID);


    ResultModel addAdditionStudent(Integer examID, String stuNumber, String stuName);
}
