package com.exam.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

/*捕获异常*/
public class ExceptionResolver implements HandlerExceptionResolver {

	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler,
			Exception ex) {
		System.err.println("====================异常开始===================");
		ex.printStackTrace();
		System.err.println("====================异常结束===================");
		ModelAndView mv = new ModelAndView("error");
		return mv;
	}

	
}