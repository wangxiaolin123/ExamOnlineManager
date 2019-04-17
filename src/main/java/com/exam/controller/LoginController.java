package com.exam.controller;

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
	public String login(HttpServletRequest request, HttpServletResponse response){

		//获取用户名等
		String inputUsername = request.getParameter("inputUser");
		String inputPassword = request.getParameter("inputPassword");
		String inputSelect = request.getParameter("inputSelect");
		//print
		System.out.println(inputUsername);
		System.out.println(inputPassword);
		System.out.println(inputSelect);
		
		//未输入
		if(inputUsername == null || inputPassword == null || inputSelect == null) {
			//session提示
			request.setAttribute("tips", "格式不正确，请重新输入！");
			return "login/login";
		}
		//强制转换type
		int type = Integer.parseInt(inputSelect);
		User user = new User();
		user.setUsername(inputUsername);
		MD5_Encoding md5_encoding = new MD5_Encoding();
		inputPassword= md5_encoding.getMD5ofStr(inputPassword);
		System.out.println(inputPassword);
		user.setPassword(inputPassword);
		user.setType(type);
		System.out.println(user.toString());
		 user = this.userService.getUser(user);
		System.out.println(user.toString());
		if(user == null) {
			//登陆失败
			request.setAttribute("tips", "用户名或者密码错误，请重新输入！");
			return "login/login";
		}else {
			//succ
			//获取session
			HttpSession session = request.getSession();
			session.setAttribute("user",user);
			switch (type){
				case 1:
				{
					return "a_main";
				}
				case 2:
				{
					return "t_main";
				}
				case 3:
				{
					return "s_main";
				}

			}

		}

		return "login/login";

	}
}
