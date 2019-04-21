package com.exam.domain;

public class Student {
    //stuID	int
    private Integer stuID;
    //stuNumber	char
    private String stuNumber;
    //stuName	varchar
    private String stuName;
    //classID	int
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
                '}';
    }
}
