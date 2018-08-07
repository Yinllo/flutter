package com.desjf.dsjr.bean;

/**
 * @Author YinL
 * @Date 2018/6/13 0023
 * @Describe  用户剩余登录尝试次数（用户名或密码输错即减少一次）
 */

public class RestLoginNum{

        /**
         * restLoginTime : 1
         */

    private String restLoginTime;

    public String getRestLoginTime() {
        return restLoginTime == null ? "" : restLoginTime;
    }

    public void setRestLoginTime(String restLoginTime) {
        this.restLoginTime = restLoginTime;
    }
}

