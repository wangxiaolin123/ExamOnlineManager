package com.exam.service;

import com.exam.domain.Exam;
import com.exam.domain.Student;
import com.exam.utlis.ResultModel;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

public interface TeacherService {

    List<Exam> queryExamByTeacher(String teaNumber);

    Exam getExamByID(Integer examID);

    ResultModel addExam(Exam exam);

    ResultModel deleteExam(Integer examID);

    ResultModel modifyExam(Exam exam);

    ResultModel upPaper(MultipartFile file, Integer examID);

    List<Map<String, String>> getStuNumberFile(Integer examID);

    ResultModel unBindIp(String stuNumber, Integer examID);

    ResultModel importAllStudent(MultipartFile multipartFile);

    ResultModel importStudent(Student student);

    List<Student> getAllStudents();

    ResultModel updateStudent(Student student);

    ResultModel deleteStudent(String stuNumber);

    //ResultModel updateExamState(int examID, String state);

    //ResultModel queryExams(int examID,String teaNumber, String state);
}
