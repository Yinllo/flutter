package com.desjf.dsjr.net.exception;

/**
 * @Author YinL
 * @Date 2018/5/22 0022
 * @Describe  服务器无数据返回异常
 */

public class ServerNoDataResponseException extends RuntimeException{
    public ServerNoDataResponseException() {
        super("服务器错误，没有返回数据", new Throwable("Server error"));
    }
}
