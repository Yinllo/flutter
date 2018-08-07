package com.example.yinl.fanshedemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class MainActivity extends AppCompatActivity {

    Field fieldName;
    Field fieldAge;
    String name;
    int age;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /**
         * 获得类 对象的方式  有三种
         */

        //第一种
//        Class class1=User.class;
        //第二种
//        try {
//            Class class2=Class.forName("com.example.yinl.fanshedemo.User");
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }

        //第三种
        User user=new User("yinl",18);
        Class clazz=user.getClass();

        /**
         * 获得类中的各个成员变量
         */

        //获得变量
        //获得私有的属性是 使用 getDeclaredField()
        try {
            fieldName=clazz.getDeclaredField("name");
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
        //获得非私有的  getField()
        try {
            fieldAge=clazz.getField("age");
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }

        //设置 允许对获得的对象进行操作（暴力反射取得属性的值）
        fieldName.setAccessible(true);

        try {
            name=(String)fieldName.get(user);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        try {
            age=fieldAge.getInt(user);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        Log.e("aaa",name+"-----"+age);

        /**
         * 修改属性值
         */
        try {
            fieldName.set("name","yinlliang");
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        try {
            fieldAge.set("age",23);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }


        /**
         * 取方法
         */
        try {
            Method method=clazz.getMethod("eat",String.class);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }
}
