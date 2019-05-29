package com.exam.controller;


import com.alibaba.fastjson.JSONObject;
import com.exam.domain.ClassInfo;
import com.exam.domain.Exam;
import com.exam.domain.Teacher;
import com.exam.exception.AdminTException;
import com.exam.service.AdminTService;
import com.exam.service.ExamService;
import com.exam.utlis.ResultModel;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminTConreoller {

    @Resource
    private AdminTService adminTService;
    @Resource
    private ExamService examService;

    @RequestMapping("/mainPage.do")
	public String show() {
		return "admin/a_main";
	}
    
    @RequestMapping("/t_manager.do")
    public String Teacher_Manager(HttpServletRequest request, HttpServletResponse response) {

        try{
            List<Teacher> teachers = new ArrayList<>();
            teachers= adminTService.getAllTeachers();

            request.setAttribute("teachers",teachers);
            return "admin/t_manager";
        }catch (AdminTException e){
            e.printStackTrace();
        }

        return "admin/a_main";

    }

    @RequestMapping(value = "/t_addteacher.do",method= RequestMethod.POST)
    @ResponseBody
    public ResultModel Teacher_Add(Teacher teacher){
        try {
            System.out.println(teacher);
            ResultModel res = adminTService.addTeacher(teacher);
            //返回数据
            return res;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultModel.build(500, "系统故障，请联系管理员！");
        }
    }

    @RequestMapping(value="/updateTeacher.do", method=RequestMethod.POST)
    @ResponseBody
    public ResultModel updateTeacher(Teacher teacher,HttpServletRequest request) {
        String password=request.getParameter("teaPassword");
        System.out.println(password);
        System.out.println(teacher.toString());
     try {
            ResultModel res=adminTService.updateTeacher(teacher,password);
            //返回数据
            return res;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultModel.build(500, "更新失败，请联系管理员！");
        }

    }

    @RequestMapping("/deleteTeacher.do/{teaNumber}")
    @ResponseBody
    public ResultModel removeTeacher(@PathVariable String teaNumber) {
        try {

            adminTService.deleteTeacher(teaNumber);
            //返回数据
            return ResultModel.ok();
        } catch (Exception e) {
            e.printStackTrace();
            return ResultModel.build(500, "删除失败，请联系管理员！");
        }
    }


    @RequestMapping("/examclean.do")
    public String examclean() {
        return "admin/exam_clean";
    }


    @RequestMapping(value = "/getExamInfos.do",method = { RequestMethod.GET })
    @ResponseBody
    public JSONObject getExamInfos(@RequestParam(value = "limit",required = false)Integer limit,
                                  @RequestParam(value = "offset",required = false)Integer offset,
                                  @RequestParam(value = "search",required = false)String search,
                                   @Param(value = "state")String state,
                                  HttpServletResponse response){

        System.out.println("state："+state);
        JSONObject object = new JSONObject();
        List<Exam> list=new ArrayList<>();

        list=examService.getExamsByState(state);
        for(Exam e:list)
            System.out.println(e);
        if(list!=null && list.size()>0){

            object.put("total",list.size());
            object.put("totalNotFiltered",list.size());
            object.put("rows",list);
            return object;
        }

    return null;

    }

    @RequestMapping("/classmanager.do")
    public String classmanager() {
        return "admin/classmanager";
    }


    @RequestMapping(value = "/getClassInfos.do",method = { RequestMethod.GET })
    @ResponseBody
    public JSONObject getstudents(@RequestParam(value = "limit",required = false)Integer limit,
                                  @RequestParam(value = "offset",required = false)Integer offset,
                                  @RequestParam(value = "search",required = false)String search,
                                  HttpServletResponse response){

        JSONObject object = new JSONObject();
        List<ClassInfo> list=new ArrayList<>();

        list=adminTService.getAllClassInfos();

        object.put("total",list.size());
        object.put("totalNotFiltered",list.size());
        object.put("rows",list);
        return object;
    }


    @RequestMapping(value = "/updateClass.do",method = RequestMethod.POST)
    @ResponseBody
    public ResultModel updateClass(ClassInfo classInfo) {
        if(classInfo!=null){
            ResultModel res=adminTService.updateClass(classInfo);
            return res;
        }
        return ResultModel.build(500,"修改班级信息失败");
    }

    @RequestMapping(value = "/deleteClass.do",method = { RequestMethod.GET })
    @ResponseBody
    public ResultModel deleteStudent(@Param(value = "classID")Integer classID){

        if(classID!=null){
            ResultModel res=adminTService.deleteClass(classID);
            return res;
        }
        return ResultModel.build(500,"传入班级编号为空");
    }


    @RequestMapping(value = "/addClass.do",method=RequestMethod.POST)
    @ResponseBody
    public ResultModel importStudent(ClassInfo classInfo){
        System.out.println(classInfo);
        if (classInfo != null){
            ResultModel res=adminTService.addClassInfo(classInfo);
            return  ResultModel.ok();
        }
        return ResultModel.build(500,"学生信息为空");

    }


}
