package com.exam.controller;

import com.exam.domain.Notice;
import com.exam.exception.UserException;
import com.exam.service.ExamService;
import com.exam.utlis.GetIPInfo;
import com.exam.utlis.ResultModel;
import com.exam.websocket.NoticeService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.exam.domain.User;
import com.exam.service.UserService;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller
@RequestMapping("/login")
public class LoginController {

	//public static String HostIpPort="127.0.0.1:8080";//测试
	//public static String HostIpPort="192.168.1.103:8080";//局域网测试
	public static String HostIpPort="47.102.124.211:80";//实际环境

	@Resource
    private UserService userService;
	@Resource
	private ExamService examService;
	
	@RequestMapping("/loginPage.do")
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
		System.out.println(inputUsername+" "+inputPassword+" "+inputSelect+" "+ipInfo);


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
			session.setAttribute("HostIpPort", HostIpPort);
			System.out.println("用户登陆返回的信息 " + res.getData());

			switch (inputSelect) {
				case 1: {

					return "admin/a_main";
				}
				case 2: {

					return "teacher/t_main";
				}
				case 3: {
					return "student/s_main";
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
