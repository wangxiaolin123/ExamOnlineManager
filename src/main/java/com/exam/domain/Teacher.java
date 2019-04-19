package com.exam.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown=true)
public class Teacher  implements Serializable {

//    teaID	int
    private Integer teaID;
//    teaNumber	char
    private String teaNumber;
//    teaName	varchar
    private String teaName;
    //    isadmin	bit
    private boolean isadmin;

    public Integer getTeaID() {
        return teaID;
    }

    public void setTeaID(Integer teaID) {
        this.teaID = teaID;
    }

    public String getTeaNumber() {
        return teaNumber;
    }

    public void setTeaNumber(String teaNumber) {
        this.teaNumber = teaNumber;
    }

    public String getTeaName() {
        return teaName;
    }

    public void setTeaName(String teaName) {
        this.teaName = teaName;
    }

    public boolean isIsadmin() {
        return isadmin;
    }

    public void setIsadmin(boolean isadmin) {
        this.isadmin = isadmin;
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "teaID=" + teaID +
                ", teaNumber='" + teaNumber + '\'' +
                ", teaName='" + teaName + '\'' +
                ", isadmin=" + isadmin +
                '}';
    }
}
