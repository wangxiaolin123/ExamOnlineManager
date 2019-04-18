package com.exam.interceptor;

import com.exam.domain.User;
import org.apache.log4j.Logger;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginInterceptor extends HandlerInterceptorAdapter {

    /**
     * 日志对象
     */
    private Logger logger = Logger.getLogger(LoginInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
                             Object handler) throws Exception {

        String path = request.getServletPath();
        System.out.println("loginInterceptor###" + path);

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
