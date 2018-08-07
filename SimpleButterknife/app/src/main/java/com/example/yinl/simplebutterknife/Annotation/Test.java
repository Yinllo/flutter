package com.example.yinl.simplebutterknife.Annotation;



public class Test {

    //使用注解
    @BindView(age=18,name="yinl")
    private String name ;

    private int age ;


    @Override
    public String toString(){
        return "name:"+name+",age:"+age;

    }

}
