package com.exam.controller;

import com.exam.exception.UserException;
import com.exam.utlis.MD5_Encoding;
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
	
	@RequestMapping("loginPage.do")
	public String show() {
		return "login/login";
	}
	
	@RequestMapping("/login.do")
	public String login(HttpServletRequest request, HttpServletResponse response) {

		//获取用户名等
		String inputUsername = request.getParameter("inputUser");
		String inputPassword = request.getParameter("inputPassword");
		Integer inputSelect = Integer.parseInt(request.getParameter("inputSelect"));
		//print
		System.out.println(inputUsername);
		System.out.println(inputPassword);
		System.out.println(inputSelect);

		//获取session
		HttpSession session = request.getSession();
		try {
			User user = this.userService.isLogin(inputUsername,inputPassword,inputSelect);
			session.setAttribute("user", user);
			switch (inputSelect) {
				case 1: {
					return "a_main";
				}
				case 2: {
					return "t_main";
				}
				case 3: {
					return "s_main";
				}

			}

		} catch (UserException e) {
			e.printStackTrace();
			request.setAttribute("tips", e.getMessage());
		}
		return "login/login";
	}



	@RequestMapping("/logout.do")
	public String logout(HttpServletRequest request, HttpServletResponse response){

		HttpSession session=request.getSession();
		session.removeAttribute("user");

		return "login/login";
	}
}
