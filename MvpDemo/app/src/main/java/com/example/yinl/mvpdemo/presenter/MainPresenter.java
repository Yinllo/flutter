package com.example.yinl.mvpdemo.presenter;

import com.example.yinl.mvpdemo.model.Users;
import com.example.yinl.mvpdemo.view.MianActivityView;

/**
  * @author yinl
  * @creat  time 2018/8/2 16:08
  * @explain  业务逻辑接口
  *
 **/
public interface MainPresenter {

    //绑定视图
    void attach(MianActivityView view);

    //解除视图
    void detach();

    //登录
    void login(Users users);

}
