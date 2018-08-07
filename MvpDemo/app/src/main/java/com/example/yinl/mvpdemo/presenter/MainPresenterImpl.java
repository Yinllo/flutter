package com.example.yinl.mvpdemo.presenter;

import android.text.TextUtils;
import android.widget.Toast;

import com.example.yinl.mvpdemo.model.Users;
import com.example.yinl.mvpdemo.view.MianActivityView;

/**
  * @author yinl
  * @creat  time 2018/8/2 16:09
  * @explain 业务逻辑接口具体实现
  *
 **/
public class MainPresenterImpl implements MainPresenter{

    private MianActivityView view;

    @Override
    public void attach(MianActivityView view) {
        this.view=view;
    }

    @Override
    public void detach() {
        view=null;
    }

    @Override
    public void login(Users users) {
        if(!TextUtils.isEmpty(users.getName()) && !TextUtils.isEmpty(users.getPwd())){
            if(users.getName().equals("yinl") && users.getPwd().equals("622623")){
               view.loginSuccess("恭喜您，登录成功");
            }else{
               view.loginFailed("登录失败");
            }
        }else{
            view.showToast("用户名、密码不能为空");
        }
    }
}
