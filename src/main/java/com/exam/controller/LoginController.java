package com.exam.controller;

import com.exam.exception.UserException;
import com.exam.utlis.GetIPInfo;
import com.exam.utlis.ResultModel;
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
		String ipInfo= GetIPInfo.getIP(request);
		//print
		System.out.println(inputUsername);
		System.out.println(inputPassword);
		System.out.println(inputSelect);
        System.out.println(ipInfo);

        User loginUser=new User();
        loginUser.setUsername(inputUsername);
        loginUser.setPassword(inputPassword);
        loginUser.setType(inputSelect);
        loginUser.setIp(ipInfo);


		//获取session
		HttpSession session = request.getSession();
		try {
			ResultModel res = this.userService.isLogin(loginUser);
			session.setAttribute("user", res.getData());
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
