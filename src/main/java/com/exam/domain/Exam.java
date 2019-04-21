package com.exam.domain;

import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

public class Exam implements Serializable {
    //examID	int
    private Integer examID;
    //examName	varchar

    private String examName;
    //startTime	datetime

    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date startTime;

    //endTime	datetime
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date endTime;
    //teaNumber	char
    private String teaNumber;
    //examPath	varchar
    private String examPath;
    //isAuto	bit
    private boolean isAuto;

    private String state;

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Integer getExamID() {
        return examID;
    }

    public void setExamID(Integer examID) {
        this.examID = examID;
    }

    public String getExamName() {
        return examName;
    }

    public void setExamName(String examName) {
        this.examName = examName;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getTeaNumber() {
        return teaNumber;
    }

    public void setTeaNumber(String teaNumber) {
        this.teaNumber = teaNumber;
    }

    public String getExamPath() {
        return examPath;
    }

    public void setExamPath(String examPath) {
        this.examPath = examPath;
    }

    public boolean getisAuto() {
        return isAuto;
    }

    public void setisAuto(boolean isAauto) {
        this.isAuto = isAauto;
    }

    @Override
    public String toString() {
        return "Exam{" +
                "examID=" + examID +
                ", examName='" + examName + '\'' +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", teaNumber='" + teaNumber + '\'' +
                ", examPath='" + examPath + '\'' +
                ", isAuto=" + isAuto +
                ", state='" + state + '\'' +
                '}';
    }
}
