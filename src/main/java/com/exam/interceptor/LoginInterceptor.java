package com.exam.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.exam.domain.User;

public class LoginInterceptor extends HandlerInterceptorAdapter{

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		String path = request.getServletPath();
		//System.out.println("loginInterceptor###" + path);
		
		//login url是登陆
		if(path.indexOf("login") != -1) {
			return true;
		}else {
			//not login 判定session
			HttpSession session = request.getSession();
			User user = (User) session.getAttribute("user");
			//session 不为空
			if(user != null) {
				return true;
			}
		}
		System.out.println("登陆拦截器拦截！");
		return false;
	}
}
