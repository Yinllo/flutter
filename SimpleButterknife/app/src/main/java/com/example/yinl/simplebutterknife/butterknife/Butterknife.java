package com.example.yinl.simplebutterknife.butterknife;

import android.app.Activity;
import android.util.Log;
import android.view.View;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 自定义Butterknife  通过反射和注解 将控件绑定
 * 传入的参数 为Acitvity
 */

public class Butterknife {

    public static void bind(Activity activity) {

        try {
            bindView(activity);
            bindClick(activity);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 绑定视图view
     */
    private static void bindView(Activity activity) throws IllegalAccessException {

        //获得字节码
        Class clazz=activity.getClass();
        //获得activity中的所有变量
        Field[] fields=clazz.getDeclaredFields();
        //循环获取
        for(Field field:fields){
          //允许暴力反射
            field.setAccessible(true);
            //获取变量上加的注解
            BindView bindView=field.getAnnotation(BindView.class);
            if(bindView != null){

                //注解不为空的情况下  获取注解的值  这里实际上是找到当前控件对应的ID
                int id=bindView.value();
                //通过id来获取控件
                View view=activity.findViewById(id);
                //将控件的值赋值给变量
                field.set(activity,view);

            }else{

            }

        }

    }

    /**
     * 点击事件
     */
    private static void bindClick(final Activity activity){

        Class<? extends Activity> c=activity.getClass();

        //获得方法
        Method[] methods=c.getDeclaredMethods();
        for(final Method method:methods){

            //允许暴力反射
            method.setAccessible(true);
            //获得注解
            MyClick view=  method.getAnnotation(MyClick.class);

            if(view != null){

                //获得控件id
                int id=view.value();

                View v=activity.findViewById(id);
                //点击事件
                v.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        try {

                            //调用方法  这里方法没带参数 所以不用传递
                            method.invoke(activity);

                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        } catch (InvocationTargetException e) {
                            e.printStackTrace();
                        }
                    }
                });

            }else{

            }

        }

    }


}
