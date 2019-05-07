package com.exam.listener;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import javax.servlet.http.HttpSessionListener;

public class OnLineListener implements HttpSessionListener {



    @Override
    public void sessionCreated(HttpSessionEvent se) {


        System.out.println("检测在线用户");
       /* HttpSession session=se.getSession();
        Map<String,String> map= (Map<String, String>) session.getAttribute("user");
        String number=map.get("number");
        String name=map.get("name");
       String ip=map.get("ip");
        System.out.println("检测在线用户信息："+name+" "+number+" "+ip);*/
      /*  Map<String, HttpSession> onlines= (Map<String, HttpSession>) se.getSession().getServletContext().getAttribute("onlines");
        if(onlines==null){
            onlines= Collections.synchronizedMap(new HashMap<String, HttpSession>());//对map进行加上同步锁
            se.getSession().getServletContext().setAttribute("onlines",onlines);
        }
        onlines.put(se.getSession().getId(), se.getSession());*/
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {

      /*  Map<String, HttpSession> onlines= (Map<String, HttpSession>) se.getSession().getServletContext().getAttribute("onlines");
        if(onlines.containsKey(se.getSession().getId())){
            onlines.remove(se.getSession().getId());
        }*/
    }

}
