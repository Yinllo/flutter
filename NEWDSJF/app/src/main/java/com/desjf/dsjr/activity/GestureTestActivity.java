package com.desjf.dsjr.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.desjf.dsjr.R;
import com.desjf.dsjr.base.BaseActivity;
import com.desjf.dsjr.base.BaseApplication;
import com.desjf.dsjr.utils.PreferenceCache;
import com.desjf.dsjr.widget.GestureView;

import java.util.List;

public class GestureTestActivity extends BaseActivity implements GestureView.GestureCallBack,View.OnClickListener {
    private GestureView gestureView;
    private int gestureFlg = -1; // 1表示安全中心跳入 2表示设置页面跳入 3表示从闪屏页跳入

    private TextView tv_user_name;
    private TextView tv_forget_gesture_pwd;
    private TextView tv_change_account;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gesture_test);
        gestureFlg = getIntent().getIntExtra("gestureFlg", -1);
        gestureView = (GestureView) findViewById(R.id.gesture1);
        gestureView.setGestureCallBack(this);
        gestureView.clearCacheLogin();

        initView();
    }
    private void initView() {
        tv_user_name = (TextView) findViewById(R.id.tv_user_name);
        tv_forget_gesture_pwd = (TextView) findViewById(R.id.tv_forget_gesture_pwd);
        tv_change_account = (TextView) findViewById(R.id.tv_change_account);

        tv_forget_gesture_pwd.setOnClickListener(this);
        tv_change_account.setOnClickListener(this);

        try {
            String str = PreferenceCache.getPhoneNum();
            String ss = str.substring(0, str.length() - (str.substring(3)).length()) + "****" + str.substring(7);
            tv_user_name.setText("欢迎您 , " + ss);
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void gestureVerifySuccessListener(int stateFlag, List<GestureView.GestureBean> data, boolean success) {
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
                startActivity(new Intent(GestureTestActivity.this, MainActivity.class));
                finish();
            }
        }

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_change_account:
                Intent intent1 = new Intent(this, LoginActivity.class);
                BaseApplication.forgetGesturePwd = 1;
                startActivity(intent1);
//                finish();
                break;
            case R.id.tv_forget_gesture_pwd:
                //忘记手势密码
                Intent intent = new Intent(this, LoginActivity.class);
                BaseApplication.forgetGesturePwd = 1;
                startActivity(intent);
//                finish();
                break;
        }

    }
}
