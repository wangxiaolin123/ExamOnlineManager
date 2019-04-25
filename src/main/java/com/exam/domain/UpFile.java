package com.exam.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown=true)
public class UpFile implements Serializable {

    private MultipartFile file;
    private String examIDString;

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }

    public String getExamIDString() {
        return examIDString;
    }

    public void setExamIDString(String examIDString) {
        this.examIDString = examIDString;
    }

    @Override
    public String toString() {
        return "UpFile{" +
                "file=" + file +
                ", examIDString='" + examIDString + '\'' +
                '}';
    }
}
