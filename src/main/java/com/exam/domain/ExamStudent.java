package com.exam.domain;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class ExamStudent {

    //esID	int
    private Integer esID;
   // stuNumber	varchar
    private String stuNumber;
    //examID	int
    private Integer examID;
    //answerPath	varchar
    private String answerPath;

    //answerTime	datetime
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date answerTime;

    public Integer getEsID() {
        return esID;
    }

    public void setEsID(Integer esID) {
        this.esID = esID;
    }

    public String getStuNumber() {
        return stuNumber;
    }

    public void setStuNumber(String stuNumber) {
        this.stuNumber = stuNumber;
    }

    public Integer getExamID() {
        return examID;
    }

    public void setExamID(Integer examID) {
        this.examID = examID;
    }

    public String getAnswerPath() {
        return answerPath;
    }

    public void setAnswerPath(String answerPath) {
        this.answerPath = answerPath;
    }

    public Date getAnswerTime() {
        return answerTime;
    }

    public void setAnswerTime(Date answerTime) {
        this.answerTime = answerTime;
    }

    @Override
    public String toString() {
        return "ExamStudentDao{" +
                "esID=" + esID +
                ", stuNumber='" + stuNumber + '\'' +
                ", examID=" + examID +
                ", answerPath='" + answerPath + '\'' +
                ", answerTime=" + answerTime +
                '}';
    }
}
