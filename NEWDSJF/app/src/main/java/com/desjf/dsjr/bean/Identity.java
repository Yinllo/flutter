package com.desjf.dsjr.bean;

/**
 * @Author YinL
 * @Date 2018/3/24 0024
 * @Describe
 */

public class Identity {


    /**
     * message : 未实名
     * status : false
     * messageType : 0002
     */

    private String message;
    private boolean status;
    private String messageType;

    public String getMessage() {
        return message == null ? "" : message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getMessageType() {
        return messageType == null ? "" : messageType;
    }

    public void setMessageType(String messageType) {
        this.messageType = messageType;
    }
}
