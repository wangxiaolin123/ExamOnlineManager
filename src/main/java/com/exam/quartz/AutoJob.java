package com.exam.quartz;

import com.exam.domain.Exam;
import com.exam.domain.Notice;
import com.exam.service.ExamService;
import com.exam.websocket.NoticeService;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

public class AutoJob {

    @Resource
    private ExamService examService;

    public void Test(){

        /*Notice notice=new Notice();
        notice.setExamID(1);
        notice.setNoticeTitle("考试开始");
        notice.setSignal("start");
        List<String> stuNumbers=new ArrayList<>();
        stuNumbers.add("1610121165");
        NoticeService.sendNotice(notice,stuNumbers);*/
    }

    public void Task() {

        System.out.println("系统自动化扫描任务开始");

        //获取即将开始的考试列表
        List<Exam> examList = examService.GetReadyExams(15);

        if (examList.size() != 0) {

            for (int i = 0; i < examList.size(); i++) {
                int j = i;
                new Thread() {
                    @Override
                    public void run() {

                        Exam exam = examList.get(j);

                        System.out.println("查询到考试，自动化考试模块启动，\n考试信息:" + exam.toString());

                        examService.WaitForBegin(exam.getStartTime());

                        System.out.println("发送通知，开始考试");
                        examService.BeginExam(exam);

                        System.out.println("考试正在进行中，休眠至考试结束前一刻");
                        examService.Examining(exam);

                        System.out.println("考试结束，发送通知考试结束");
                        examService.EndExam(exam);

                    }

                }.start();

            }

        }else {
            System.out.println("暂无考试进行，扫描任务提前结束");
        }

    }
}
