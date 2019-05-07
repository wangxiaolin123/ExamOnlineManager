package com.exam.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

@JsonIgnoreProperties(ignoreUnknown=true)
public class Exam implements Serializable {

    //examID	int
    private Integer examID;
    //examName	varchar

    private String examName;
    //startTime	datetime

    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm")
    private Date startTime;

    //endTime	datetime
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm")
    private Date endTime;
    //teaNumber	char
    private String teaNumber;
    //examPath	varchar
    private String examPath;
    //isAuto	bit
    private boolean isAuto;

    private String state;

    private Integer classID;

    private boolean isManager;


    public Integer getClassID() {
        return classID;
    }

    public void setClassID(Integer classID) {
        this.classID = classID;
    }

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


    public boolean getIsAuto() {
        return isAuto;
    }

    public void setIsAuto(boolean isAuto) {
        this.isAuto = isAuto;
    }


    public boolean getIsManager() {
        return isManager;
    }

    public void setIsManager(boolean isManager) {
        this.isManager = isManager;
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
                ", classID=" + classID +
                ", isManager=" + isManager +
                '}';
    }
}
