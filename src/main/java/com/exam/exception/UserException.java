package com.exam.exception;

/**
 * 用户登陆的异常处理类
 */
public class UserException extends Exception {
    public UserException(String message){
        super(message);
    }
}
