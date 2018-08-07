package com.example.yinl.mvvmdemo.model;

/**
  * @author yinl
  * @creat  time 2018/8/2 14:38
  * @explain 用户model
  *
 **/
public class Users {

    private String name;
    private String pwd;

    public Users(String name, String pwd) {
        this.name = name;
        this.pwd = pwd;
    }

    public String getName() {
        return name == null ? "" : name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPwd() {
        return pwd == null ? "" : pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }
}
