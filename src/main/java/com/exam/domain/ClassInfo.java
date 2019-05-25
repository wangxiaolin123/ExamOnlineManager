package com.exam.domain;

public class ClassInfo {

    private Integer classID;
    private String className;

    public Integer getClassID() {
        return classID;
    }

    public void setClassID(Integer classID) {
        this.classID = classID;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    @Override
    public String toString() {
        return "ClassInfo{" +
                "classID=" + classID +
                ", className='" + className + '\'' +
                '}';
    }
}
