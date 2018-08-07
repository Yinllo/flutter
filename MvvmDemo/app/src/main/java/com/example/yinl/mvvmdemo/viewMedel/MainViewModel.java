package com.example.yinl.mvvmdemo.viewMedel;

import android.content.Context;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

/**
  * @author yinl
  * @creat  time 2018/8/2 14:47
  * @explain  mvvm模式下的业务逻辑处理类
  *
 **/
public class MainViewModel {

    private String name;
    private String pwd;
    private Context mContext;

    public MainViewModel(Context context){
        this.mContext=context;

    }

    /**
     * 登录方法
     *
     */

    public void login(View view){
        if(!TextUtils.isEmpty(name) && !TextUtils.isEmpty(pwd)){
           if(name.equals("yinl") && pwd.equals("622623")){
               Toast.makeText(mContext,"登录成功",Toast.LENGTH_SHORT).show();
           }else{
               Toast.makeText(mContext,"登录失败",Toast.LENGTH_SHORT).show();
           }

        }else{
            Toast.makeText(mContext,"用户名、密码 不能为空",Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * 监听输入框
     */
    public TextWatcher nameChangeListener(){
        return new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //活动实时的用户名
                name=s.toString();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        };
    }

    public TextWatcher pwdChangeListener(){
        return new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
               //获得实时密码
                pwd=s.toString();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        };
    }

}
