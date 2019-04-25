package com.exam.controller;


import com.exam.domain.Exam;
import com.exam.service.TeacherService;
import com.exam.utlis.ResultModel;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/teacher")
public class TeacherController {

    @Resource
    private TeacherService teacherService;

    @RequestMapping("/exammanager.do")
    public String getExamList(HttpServletRequest request) {

        HttpSession session=request.getSession();
        String teaNumber=request.getParameter("teaNumber");
        //判空
        if (teaNumber != null){

            List<Exam> exams = teacherService.queryExamByTeacher(teaNumber);
            session.setAttribute("examList",exams);
            return "teacher/exammanager";
        }
        else {
            return "error";
        }
    }


    @RequestMapping(value="/addExam.do", method= RequestMethod.POST)
    @ResponseBody
    public ResultModel addExam(Exam exam) {

        System.out.println(exam.toString());
        try {
           ResultModel res=teacherService.addExam(exam);
            return res;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResultModel.build(500, "创建考试失败！");
    }

    @RequestMapping(value="/updateExam.do", method=RequestMethod.POST)
    @ResponseBody
    public ResultModel updateExam(Exam exam) {
        System.out.println(exam.toString());
        try {
            ResultModel res=teacherService.modifyExam(exam);
            return res;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultModel.build(500, "更新考试失败，请联系管理员！");
        }

    }

    @RequestMapping("/deleteExam.do/{examID}")
    @ResponseBody
    public ResultModel removeExam(@PathVariable Integer examID) {
        try {

          ResultModel res= teacherService.deleteExam(examID);
            //返回数据
            return res;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultModel.build(500, "删除考试失败，请联系管理员！");
        }
    }


    @RequestMapping(value = "/upPaper.do",method=RequestMethod.POST)
    @ResponseBody
    public ResultModel upPaper(@RequestParam(value = "examID")Integer examID, @RequestParam(value = "upFile", required = false) MultipartFile file){

        if (file != null){
            ResultModel res=teacherService.upPaper(file,examID);
            return res;
        }
        return ResultModel.build(500,"试卷上传失败");
    }


}
