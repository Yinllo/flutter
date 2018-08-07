package com.desjf.dsjr.net.exception;


/**
 * @Author YinL
 * @Date 2018/5/22 0022
 * @Describe  服务器返回异常
 */
public class ServerResponseException extends RuntimeException {
    public ServerResponseException(String errorCode, String cause) {
        super(errorCode+":"+cause, new Throwable("Server error"));
    }
}
