package com.desjf.dsjr.model.bankModel;

/**
 * @Author YinL
 * @Date 2018/4/10 0010
 * @Describe  获取验证码回调  以及用户操作投资的结果回调
 */

public class BankCodeModel {
    /**
     * 获取验证码
     * message : 583689
     * status : true
     */
    /**
     * 投资
     * message : 投资成功
     * status : true
     */
    private String message;
    private boolean status;

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
}
