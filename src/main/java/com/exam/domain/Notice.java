package com.exam.domain;

import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

public class Notice implements Serializable {

    //noticeID	int
    private Integer noticeID;

    //niticeTitle	varchar
    private String noticeTitle;

    private String signal;

    //noticeTime	datetime
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm")
    private Date noticeTime;

    //noticeContent	text
    private String noticeContent;

    //examID	int
    private Integer examID;

    //teaNumber	char
    private String teaNumber;

    public Integer getNoticeID() {
        return noticeID;
    }

    public void setNoticeID(Integer noticeID) {
        this.noticeID = noticeID;
    }

    public String getNoticeTitle() {
        return noticeTitle;
    }

    public void setNoticeTitle(String noticeTitle) {
        this.noticeTitle = noticeTitle;
    }

    public Date getNoticeTime() {
        return noticeTime;
    }

    public void setNoticeTime(Date noticeTime) {
        this.noticeTime = noticeTime;
    }

    public String getNoticeContent() {
        return noticeContent;
    }

    public void setNoticeContent(String noticeContent) {
        this.noticeContent = noticeContent;
    }

    public Integer getExamID() {
        return examID;
    }

    public void setExamID(Integer examID) {
        this.examID = examID;
    }

    public String getTeaNumber() {
        return teaNumber;
    }

    public void setTeaNumber(String teaNumber) {
        this.teaNumber = teaNumber;
    }

    public String getSignal() {
        return signal;
    }

    public void setSignal(String signal) {
        this.signal = signal;
    }

    @Override
    public String toString() {
        return "Notice{" +
                "noticeID=" + noticeID +
                ", noticeTitle='" + noticeTitle + '\'' +
                ", signal='" + signal + '\'' +
                ", noticeTime=" + noticeTime +
                ", noticeContent='" + noticeContent + '\'' +
                ", examID=" + examID +
                ", teaNumber='" + teaNumber + '\'' +
                '}';
    }
}
