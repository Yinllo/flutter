package com.desjf.dsjr.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.desjf.dsjr.R;
import com.desjf.dsjr.base.BaseActivity;
import com.desjf.dsjr.utils.PreferenceCache;
import com.desjf.dsjr.widget.GestureView;

import java.util.List;

public class GestureSetActivity extends BaseActivity implements GestureView.GestureCallBack,View.OnClickListener{
    private GestureView gestureView;
    private String message;
    private TextView tv_jump;
    public ImageView iv_back;
    private int jumpFlg;
    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gesture_set);
        intent=getIntent();
        jumpFlg = intent.getIntExtra("jumpFlg", 0);
        gestureView = (GestureView) findViewById(R.id.gesture);
        iv_back = (ImageView) findViewById(R.id.iv_back);
        iv_back.setOnClickListener(this);
        gestureView.setGestureCallBack(this);

        //不调用这个方法会造成第二次启动程序直接进入手势识别而不是手势设置
        gestureView.clearCache();
        gestureView.setMinPointNums(5);
        tv_jump = (TextView) findViewById(R.id.tv_jump);
        if (jumpFlg == 1) {
            tv_jump.setVisibility(View.VISIBLE);
        } else {
            tv_jump.setVisibility(View.GONE);
        }
        tv_jump.setOnClickListener(this);
    }
    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public void gestureVerifySuccessListener(int stateFlag, List<GestureView.GestureBean> data, boolean success) {
        if (stateFlag == GestureView.STATE_LOGIN) {
            if (jumpFlg == 1) {
                Intent intent = new Intent(this, RegisterOverActivity.class);
                intent.putExtra("phoneNumber",this.getIntent().getStringExtra("phoneNumber"));
                PreferenceCache.putGestureFlag(true);
                startActivity(intent);
                finish();
            } else {
                PreferenceCache.putGestureFlag(true);
                finish();
            }

        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_jump:
                Intent intent = new Intent(this, RegisterOverActivity.class);
                intent.putExtra("phoneNumber",this.getIntent().getStringExtra("phoneNumber"));
                PreferenceCache.putGestureFlag(false);
                startActivity(intent);
                finish();
                break;
            case R.id.iv_back:
                if (jumpFlg == 1) {
                    //注册页进入
                    Intent intent1 = new Intent(this, RegisterOverActivity.class);
                    PreferenceCache.putGestureFlag(false);
                    startActivity(intent1);
                    finish();
                } else {
                    finish();
                }
                break;
        }
    }
    @Override
    public void onBackPressed() {
        //注册成功不可返回
        if (jumpFlg != 1) {
            super.onBackPressed();
        }
    }
}
