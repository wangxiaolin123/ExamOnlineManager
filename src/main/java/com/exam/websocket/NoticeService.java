package com.exam.websocket;

import com.exam.domain.Notice;
import com.exam.domain.Student;
import com.exam.utlis.ResultModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class NoticeService {


    public static void sendNotice(Notice notice, List<String> ipList) {

        //获取WebSocketServer对象的映射。
        ConcurrentHashMap<String, WebSocketServer> map = WebSocketServer.getWebSocketMap();
        if (map.size() != 0) {
            for (int i = 0; i < ipList.size(); i++) {
                String ip = ipList.get(i);
                if (map.containsKey(ip)) {
                    WebSocketServer webSocketServer = map.get(ip);
                    webSocketServer.sendtoUser(ResultModel.ok(notice), ip);
                }
            }
        }
    }

    public static void onLineCounts(Map<String, Object> mapInfo) {


        String teaNumber = (String) mapInfo.get("teaNumber");
        List<Student> students = (List<Student>) mapInfo.get("students");

        List<Student> students1 = new ArrayList<>();

        //获取WebSocketServer对象的映射。
        ConcurrentHashMap<String, WebSocketServer> map = WebSocketServer.getWebSocketMap();
        if (map == null || students== null) {
            System.out.println("map或students为空");

            if(map==null){
                System.out.println("map为空");
            }
            if(students==null){
                System.out.println("students为空");
            }
        } else if (map.size() != 0 && students.size() != 0) {

            //筛选在线人
            for (int i = 0; i < students.size(); i++) {

                Student student = students.get(i);

                if (map.containsKey(student.getStuNumber())) {

                    System.out.println(student.getStuNumber() + "在线");
                    student.setOnLine(true);


                } else {
                    System.out.println(student.getStuNumber() + "离线");
                    student.setOnLine(false);
                }
                students1.add(student);
            }
            //发送通知
            ResultModel rs=ResultModel.build(200,"onLine",students1);
           sendInfoToTeacher(map,rs,teaNumber);
        } else {
            System.out.println("getWebSocketMap(); 为0");
        }
    }

    public static void sendInfoToTeacher(ConcurrentHashMap<String, WebSocketServer> map, ResultModel rs, String teaNumber) {

        if (map.containsKey(teaNumber)) {
            WebSocketServer webSocketServer = map.get(teaNumber);
            webSocketServer.sendtoUser(rs, teaNumber);
        } else {
            System.out.println("websocket 包含" + teaNumber);
        }
    }

    public static void sendInfoToUser(ResultModel rs,String number){

        //获取WebSocketServer对象的映射。
        ConcurrentHashMap<String, WebSocketServer> map = WebSocketServer.getWebSocketMap();
        if (map.containsKey(number)) {
            WebSocketServer webSocketServer = map.get(number);
            webSocketServer.sendtoUser(rs, number);
        } else {
            System.out.println(number+"不在线");
        }
    }


}
