package com.example.yinl.mvpdemo.view;

/**
  * @author yinl
  * @creat  time 2018/8/2 15:10
  * @explain  MainActivity UI逻辑接口
  *
 **/
public interface MianActivityView {

    void showToast(String mag);

    void loginSuccess(String mag);

    void loginFailed(String mag);


}
