package com.desjf.dsjr.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.desjf.dsjr.R;
import com.desjf.dsjr.base.BaseActivity;
import com.desjf.dsjr.biz.RegistBiz;
import com.desjf.dsjr.biz.exception.BizFailure;
import com.desjf.dsjr.biz.exception.ZYException;
import com.desjf.dsjr.biz.task.BizDataAsyncTask;
import com.desjf.dsjr.utils.ToastUtils;

import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RegistSecondStepActivity extends BaseActivity {
    @BindView(R.id.et_verify_code)
    EditText etVerifyCode;
    @BindView(R.id.iv_register_back)
    ImageView ivRegisterBack;
    private Context context = this;
    private String phone;
    @BindView(R.id.getmobile)
    TextView getmobile;
    @BindView(R.id.tv_mobile_next)
    TextView tvMobileNext;
    private Timer timer;
    private TimerTask timerTask;
    private int count = 60;
    private int toRegist = 0;
    private Handler mHandler = new Handler() {
        @Override
        public void dispatchMessage(Message msg) {
            if (count >= 0) {
                getmobile.setText("重新发送(" + count + ")");
                getmobile.setClickable(false);
                count--;
            } else {
                resetTimer();
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regist_second_step);
        ButterKnife.bind(this);
        initData();
    }

    private void initData() {
        //获取到上一页面的手机号
        Intent intent = getIntent();
        phone = intent.getStringExtra("phone");
    }

    @OnClick({R.id.getmobile, R.id.tv_mobile_next,R.id.iv_register_back})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_register_back:
                //返回
                finish();
                break;
            case R.id.getmobile:
                //获取验证码
                getVoiceCode();
                break;
            case R.id.tv_mobile_next:
                if (etVerifyCode.getText().toString().isEmpty()) {
                    ToastUtils.showTost(context, "请输入验证码");
                    return;
                }
                showLoadingDialog();
                BizDataAsyncTask<String> getVerifyCode = new BizDataAsyncTask<String>() {
                    @Override
                    protected String doExecute() throws ZYException, BizFailure {
                        return RegistBiz.checkVerifyCode(phone, etVerifyCode.getText().toString());
                    }

                    @Override
                    protected void onExecuteSucceeded(String s) {
                        //下一步
                        Intent intent = new Intent(RegistSecondStepActivity.this, RegesterThirdActivity.class);
                        intent.putExtra("phone",phone);
                        startActivity(intent);
                        finish();

                    }

                    @Override
                    protected void OnExecuteFailed(String error) {
                        ToastUtils.showTost(context, error.toString());
                    }
                };
                getVerifyCode.execute();

                break;
        }
    }

    //获取验证码
    private void getVoiceCode() {
        showLoadingDialog();
        BizDataAsyncTask<String> voiceCode = new BizDataAsyncTask<String>() {
            @Override
            protected String doExecute() throws ZYException, BizFailure {
                return RegistBiz.getVoicePhone(phone);
            }

            @Override
            protected void onExecuteSucceeded(String s) {
                hideLoadingDialog();
                runTimerTask();
                getmobile.setClickable(false);
                getmobile.setFocusable(false);
            }

            @Override
            protected void OnExecuteFailed(String error) {
                hideLoadingDialog();
                ToastUtils.showTost(context, error.toString());
            }
        };
        voiceCode.execute();
    }

    private void runTimerTask() {
        timer = new Timer();
        timerTask = new TimerTask() {
            @Override
            public void run() {
                mHandler.sendEmptyMessage(0);
            }
        };
        timer.schedule(timerTask, 1000, 1000);
    }

    private void resetTimer() {
        getmobile.setText(R.string.find_getverifycode);
        getmobile.setClickable(true);
        count = 60;
        timerTask.cancel();
        timer.cancel();
        timerTask = null;
        timer = null;
    }

}
