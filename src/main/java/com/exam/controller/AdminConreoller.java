package com.exam.controller;


import com.exam.domain.Teacher;
import com.exam.exception.AdminException;
import com.exam.service.AdminService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminConreoller {

    @Resource
    private AdminService adminService;

    @RequestMapping("/t_manager.do")
    public String Teacher_Manager(HttpServletRequest request, HttpServletResponse response) {


        try{
            List<Teacher> teachers = new ArrayList<>();
            teachers=adminService.getAllTeachers();

            request.setAttribute("teachers",teachers);
            return "admin/t_manager";
        }catch (AdminException e){
            e.printStackTrace();
        }

        return "a_main";

    }

}
