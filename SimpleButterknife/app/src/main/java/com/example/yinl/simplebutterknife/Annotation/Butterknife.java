package com.example.yinl.simplebutterknife.Annotation;


import java.lang.reflect.Field;

/**
 * 绑定类 获得属性值
 */
public class Butterknife {

    public static void bind(Test test) throws NoSuchFieldException, IllegalAccessException {

        //获得对象
        Class c= test.getClass();
        //获得属性
        Field name=c.getDeclaredField("name");
        Field age=c.getDeclaredField("age");
        //允许暴力反射
        name.setAccessible(true);
        age.setAccessible(true);

        //获取注解
        BindView bindView=name.getAnnotation(BindView.class);
        if(bindView != null){
          //获取注解值
            String uName=bindView.name();
            int uAge=bindView.age();

          //将值设置
            name.set(test,uName);
            age.set(test,uAge);


        }else{
            //注解为空

        }


    }

}
