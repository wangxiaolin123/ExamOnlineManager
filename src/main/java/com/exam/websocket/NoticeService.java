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

    public static void onLineCounts(Map<String,Object> mapInfo) {

        String teaNumber= (String) mapInfo.get("teaNumber");
        List<Student> students= (List<Student>) mapInfo.get("students");

        List<Student> students1=new ArrayList<>();
        //获取WebSocketServer对象的映射。
        ConcurrentHashMap<String, WebSocketServer> map = WebSocketServer.getWebSocketMap();
        if (map.size() != 1 &&students.size()!=0) {

            //筛选在线人
            for (int i = 0; i < students.size(); i++) {
                Student student= students.get(i);
                System.out.println("NoticeService 本次考试人员信息："+student.toString());
                if (map.containsKey(student.getStuNumber())) {
                    System.out.println("当前在线："+student);
                    students1.add(student);
                }else {
                    System.out.println(student.getStuNumber()+"未在考试");
                }
            }
            //发送通知
                if (map.containsKey(teaNumber)) {
                    WebSocketServer webSocketServer = map.get(teaNumber);
                    webSocketServer.sendtoUser(ResultModel.ok(students1), teaNumber);
                }
            }
        }


}
