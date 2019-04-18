package com.exam.service;

import com.exam.domain.Teacher;
import com.exam.exception.AdminException;
import org.springframework.stereotype.Service;

import java.util.List;


public interface AdminService {

    public List<Teacher> getAllTeachers() throws AdminException;
}
