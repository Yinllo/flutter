package com.example.yinl.mvpdemo.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.yinl.mvpdemo.R;
import com.example.yinl.mvpdemo.model.Users;
import com.example.yinl.mvpdemo.presenter.MainPresenterImpl;

public class MainActivity extends AppCompatActivity implements MianActivityView{

    private EditText name;
    private EditText pwd;
    private Button login;

    //获得presenter 实例
    private MainPresenterImpl mainPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name=this.findViewById(R.id.et_username);
        pwd=this.findViewById(R.id.et_pwd);
        login=this.findViewById(R.id.btn_login);

        mainPresenter=new MainPresenterImpl();
        //绑定视图
        mainPresenter.attach(this);

        //登录按钮 监听
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Users users=new Users(name.getText().toString().trim(),pwd.getText().toString().trim());
                mainPresenter.login(users);
            }
        });

    }



    @Override
    public void showToast(String mag) {
        Toast.makeText(this,mag,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void loginSuccess(String mag) {
           showToast(mag);
    }

    @Override
    public void loginFailed(String mag) {
           showToast(mag);
    }

    //销毁时 解除与视图的绑定  方式oom
    @Override
    protected void onDestroy() {
        super.onDestroy();
        mainPresenter.detach();
    }
}
