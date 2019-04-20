package com.exam.controller;


import com.exam.domain.Teacher;
import com.exam.exception.AdminTException;
import com.exam.service.AdminTService;
import com.exam.utlis.ResultModel;
import org.omg.Messaging.SYNC_WITH_TRANSPORT;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/adminT")
public class AdminTConreoller {

    @Resource
    private AdminTService adminTService;

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

        return "a_main";

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

}
