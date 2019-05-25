package com.exam.domain;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;

import java.util.Date;

public class ExportStudentInfo extends BaseRowModel {

    @ExcelProperty(index = 0 , value = "学号")
    private String stuNumber;

    @ExcelProperty(index = 1 , value = "姓名")
    private String stuName;

    @ExcelProperty(index = 2 , value = "班级")
    private String className;

    @ExcelProperty(index = 3 , value = "提交时间",format = "yyyy-MM-dd HH:mm:ss")
    private Date answerTime;

    public String getStuNumber() {
        return stuNumber;
    }

    public void setStuNumber(String stuNumber) {
        this.stuNumber = stuNumber;
    }

    public String getStuName() {
        return stuName;
    }

    public void setStuName(String stuName) {
        this.stuName = stuName;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public Date getAnswerTime() {
        return answerTime;
    }

    public void setAnswerTime(Date answerTime) {
        this.answerTime = answerTime;
    }

    @Override
    public String toString() {
        return "ExportStudentInfo{" +
                "stuNumber='" + stuNumber + '\'' +
                ", stuName='" + stuName + '\'' +
                ", className='" + className + '\'' +
                ", answerTime=" + answerTime +
                '}';
    }
}
