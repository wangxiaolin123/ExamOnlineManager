package com.exam.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.exam.domain.User;
import com.exam.service.UserService;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/login")
public class LoginController {
	
	@Resource
    private UserService userService;
	
	@RequestMapping("/loginPage.do")
	public String show() {
		return "login";
	}
	
	@RequestMapping("/login.do")
	public String login(HttpServletRequest request, HttpServletResponse response){
		//鑾峰彇session
		HttpSession session = request.getSession();
		//鑾峰彇鐢ㄦ埛鍚嶇瓑
		String inputUsername = request.getParameter("inputUser");
		String inputPassword = request.getParameter("inputPassword");
		String inputSelect = request.getParameter("inputSelect");
		//print
		System.out.println(inputUsername);
		System.out.println(inputPassword);
		System.out.println(inputSelect);

		return "login";

	}
}
