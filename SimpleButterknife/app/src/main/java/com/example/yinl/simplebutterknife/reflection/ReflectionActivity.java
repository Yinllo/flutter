package com.example.yinl.simplebutterknife.reflection;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.yinl.simplebutterknife.R;
import com.example.yinl.simplebutterknife.User;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
  * @author yinl
  * @creat  time 2018/8/7
  * @explain  反射使用小案例
  *
 **/
public class ReflectionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reflection);

        /**
         * 反射 首先需要获得类：有以下三种方式获取
         *      然后获得类中的成员变量
         */


        //第一种  类名.class
//        Class class1= User.class;
        //第二种 通过forName(全类名)
//        try {
//            Class class2=Class.forName("com.example.yinl.simplebutterknife.User");
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }
        //第三种 通过对象的getClass()
        User user = new User("yinl", 18);
        Class clazz = user.getClass();

        //然后通过获得的类 来获取类中的成员变量
        try {
            //这里注意  当属性为private的时候  需要使用getDeclaredField()
            //如果是public  则也可使用getField()
            Field name=clazz.getDeclaredField("name");
            Field age=clazz.getField("age");
            //private属性值 还需设置 允许暴力反射
            name.setAccessible(true);
            //获得属性值  从user对象中获取
            String uName= (String) name.get(user);
            int uAge=age.getInt(user);

            Log.e("aaa",uName+"----修改前----"+uAge);

            //修改属性值  修改的对象  修改值
            name.set(user,"yinll");
            age.set(user,12);

            Log.e("aaa",user.getName()+"----修改后----"+user.getAge());

        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        //获得类中的方法
        //一：获得所有方法
        Method[] methods=clazz.getMethods();
        for(Method method:methods){
            Log.e("aaa","方法-----"+method.getName());
        }

        //获得某一个方法  方法名  方法类型
        try {
            Method method=clazz.getMethod("like",String.class);
            //调用该方法（在user对象中调用该方法，传入参数）
            method.invoke(user, "水果茶");
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }


    }
}
