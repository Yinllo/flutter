package com.desjf.dsjr.bean;

/**
 * @Author YinL
 * @Date 2018/6/12 0012
 * @Describe   登录获取token 以及账号或者密码输入错误时的剩余错误次数
 */

public class LoginBean {
    private String token;
    private String restLoginTime;

    public String getRestLoginTime() {
        return restLoginTime == null ? "" : restLoginTime;
    }

    public void setRestLoginTime(String restLoginTime) {
        this.restLoginTime = restLoginTime;
    }

    public String getToken() {
        return token == null ? "" : token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
