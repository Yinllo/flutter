package com.desjf.dsjr.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.desjf.dsjr.R;
import com.desjf.dsjr.base.BaseActivity;
import com.desjf.dsjr.base.BaseApplication;
import com.desjf.dsjr.widget.GestureView;
import com.desjf.dsjr.widget.GestureViewChange;

import java.util.List;

public class GestureChangeActivity  extends BaseActivity implements GestureViewChange.GestureCallBack,View.OnClickListener {
    private GestureViewChange gestureView;
    private int gestureFlg = -1; // 1表示安全中心跳入 2表示设置页面跳入 3表示从闪屏页跳入

    private TextView tv_user_name;
    private TextView tv_forget_gesture_pwd;
    private TextView tv_change_account;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gesture_change);
        gestureFlg = getIntent().getIntExtra("gestureFlg", -1);
        gestureView = (GestureViewChange) findViewById(R.id.gesture1);
        gestureView.setGestureCallBack(this);
        gestureView.clearCacheLogin();

        initView();
    }

    private void initView() {
        tv_forget_gesture_pwd = (TextView) findViewById(R.id.tv_forget_gesture_pwd);
        tv_forget_gesture_pwd.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        tv_forget_gesture_pwd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GestureChangeActivity.this, LoginActivity.class);
                BaseApplication.forgetGesturePwd = 1;
                startActivity(intent);
            }
        });
    }



    @Override
    public void gestureVerifySuccessListener(int stateFlag, List<GestureViewChange.GestureBean> data, boolean success) {
        if (success) {
            if (gestureFlg == 1) {
                Intent intent = new Intent(this, GestureSetActivity.class);
                startActivity(intent);
            }
            if (gestureFlg == 1 || gestureFlg == 2) {
                //this.finish();
                Intent intent = new Intent(this, GestureSetActivity.class);
                startActivity(intent);
                finish();
            } else {
                startActivity(new Intent(GestureChangeActivity.this, MainActivity.class));
                finish();
            }
        }
    }
}
