package com.example.yinl.simplebutterknife;

import android.util.Log;

public class User {

    private String name;
    public int age;

    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name == null ? "" : name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void like(String type){
        Log.e("aaa","喜欢的东西是：----"+type);
    }

}
