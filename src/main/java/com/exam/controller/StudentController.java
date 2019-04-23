package com.exam.controller;

import com.exam.dao.ExamStudentDao;
import com.exam.domain.Exam;
import com.exam.service.StudentService;
import com.exam.utlis.ResultModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.POST;
import java.util.List;

@Controller
@RequestMapping("/student")
public class StudentController {


    @Resource
    private StudentService studentService;

    @RequestMapping(value = "/examList.do",method = RequestMethod.POST)
    @ResponseBody
    public ResultModel getExamList(HttpServletRequest request) {
        String stuNumber=request.getParameter("stuNumber");
        String state=request.getParameter("state");
        //判空
        if (stuNumber == null)
            return null;
        try {
            ResultModel res =studentService.getExamsByNumberState(stuNumber, state);
            List<Exam> list=(List<Exam>)res.getData();
            System.out.println("______________________________________");
            for(Exam e:list){
                System.out.println(e.toString());
            }
            System.out.println("______________________________________");
            return res;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
