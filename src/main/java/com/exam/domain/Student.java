package com.exam.domain;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;

public class Student extends BaseRowModel {
    //stuID	int

    private Integer stuID;
    //stuNumber	char
    @ExcelProperty(index = 0 , value = "学号")
    private String stuNumber;
    //stuName	varchar
    @ExcelProperty(index = 1 , value = "姓名")
    private String stuName;
    //classID	int
    @ExcelProperty(index = 2 , value = "班级")
    private Integer classID;


    public Integer getStuID() {
        return stuID;
    }

    public void setStuID(Integer stuID) {
        this.stuID = stuID;
    }

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

    public Integer getClassID() {
        return classID;
    }

    public void setClassID(Integer classID) {
        this.classID = classID;
    }


    @Override
    public String toString() {
        return "Student{" +
                "stuID=" + stuID +
                ", stuNumber='" + stuNumber + '\'' +
                ", stuName='" + stuName + '\'' +
                ", classID=" + classID +
                ", onLine=" + onLine +
                ", submit=" + submit +
                '}';
    }

    private Boolean onLine;

    private Boolean submit;

    public Boolean getOnLine() {
        return onLine;
    }

    public void setOnLine(Boolean onLine) {
        this.onLine = onLine;
    }

    public Boolean getSubmit() {
        return submit;
    }

    public void setSubmit(Boolean submit) {
        this.submit = submit;
    }
}
