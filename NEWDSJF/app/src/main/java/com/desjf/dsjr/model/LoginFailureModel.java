package com.desjf.dsjr.model;

/**
 * @Author YinL
 * @Date 2018/4/16 0016
 * @Describe
 */

public class LoginFailureModel {

    private String message;
    private String result;
    private String status;

    public String getMessage() {
        return message == null ? "" : message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getResult() {
        return result == null ? "" : result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getStatus() {
        return status == null ? "" : status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
