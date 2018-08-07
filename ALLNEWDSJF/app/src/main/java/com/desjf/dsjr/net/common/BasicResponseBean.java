package com.desjf.dsjr.net.common;

/**
 * @Author YinL
 * @Date 2018/5/21 0021
 * @Describe  基础的返回json格式类
 *             要求：返回json数据  data后面必须是{}，不然会转换异常  因为gson解析问题
 */

public class BasicResponseBean<T> {
    private String status;
    private String code;
    private String message;
    private T data;
//    private boolean error;


    public String getStatus() {
        return status == null ? "" : status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCode() {
        return code == null ? "" : code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message == null ? "" : message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
