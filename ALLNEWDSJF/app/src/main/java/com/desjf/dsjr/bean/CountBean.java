package com.desjf.dsjr.bean;

/**
 * @Author YinL
 * @Date 2018/3/23 0023
 * @Describe  首页顶部  累计交易本金和累计赚取利息 请求返回json数据接收Bean
 */

public class CountBean {
    private String result;
    private boolean status;
    private String message;

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
