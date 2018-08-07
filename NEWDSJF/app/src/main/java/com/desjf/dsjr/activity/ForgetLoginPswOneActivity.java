package com.desjf.dsjr.activity;

import android.annotation.SuppressLint;
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
import com.desjf.dsjr.biz.PassWordBiz;
import com.desjf.dsjr.biz.exception.BizFailure;
import com.desjf.dsjr.biz.exception.ZYException;
import com.desjf.dsjr.biz.task.BizDataAsyncTask;
import com.desjf.dsjr.utils.ToastUtils;

import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ForgetLoginPswOneActivity extends BaseActivity {
    @BindView(R.id.et_forget_psw_phone)
    EditText etForgetPswPhone;
    private Context context = this;
    @BindView(R.id.iv_forget_back)
    ImageView ivForgetBack;
    @BindView(R.id.et_forget_psw)
    EditText etForgetPsw;
    @BindView(R.id.tv_forget_getmsm)
    TextView tvForgetGetmsm;
    @BindView(R.id.tv_forget_next)
    TextView tvForgetNext;
    private Timer timer;
    private TimerTask timerTask;
    private int count = 60;
    private int toRegist = 0;
    String phoneCheck="";//在点击确认注册时用来再一次判断手机号码是否正确：1为正确   0为错误
    private String phone;//手机号码
    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {

        @Override
        public void dispatchMessage(Message msg) {
            if (count >= 0) {
                tvForgetGetmsm.setText("重新发送(" + count + ")");
                tvForgetGetmsm.setClickable(false);
                count--;
            } else {
                resetTimer();
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_login_psw_one);
        ButterKnife.bind(this);

    }



    @OnClick({R.id.iv_forget_back, R.id.tv_forget_getmsm, R.id.tv_forget_next})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_forget_back:
                finish();
                break;
            case R.id.tv_forget_getmsm:
                getMsm();
                break;
            case R.id.tv_forget_next:
                GoToNext();
                break;
        }
    }

    private void GoToNext() {
        if (etForgetPswPhone.getText().toString().isEmpty()){
            ToastUtils.showTost(context,"请输入手机号码");
            return;
        }
        if (etForgetPsw.getText().toString().isEmpty()) {
            ToastUtils.showTost(context, "请输入验证码");
            return;
        }

        showLoadingDialog();
        BizDataAsyncTask<String> getNext = new BizDataAsyncTask<String>() {
            @Override
            protected String doExecute() throws ZYException, BizFailure {
                return PassWordBiz.getCheckCode(etForgetPswPhone.getText().toString(), "1", etForgetPsw.getText().toString());
            }

            @Override
            protected void onExecuteSucceeded(String s) {
                hideLoadingDialog();
                Intent intent = new Intent(ForgetLoginPswOneActivity.this, ForgetLoginPswSecondActivity.class);
                intent.putExtra("phone",etForgetPswPhone.getText().toString());
                startActivity(intent);
                finish();
            }

            @Override
            protected void OnExecuteFailed(String error) {
                hideLoadingDialog();
                ToastUtils.showTost(context, error);
            }
        };
        getNext.execute();
    }

    private void getMsm() {

        if (etForgetPswPhone.getText().toString().isEmpty()){
            ToastUtils.showTost(context,"请输入手机号码");
            return;
        }
        showLoadingDialog();
        BizDataAsyncTask<String> getCode = new BizDataAsyncTask<String>() {
            @Override
            protected String doExecute() throws ZYException, BizFailure {
                return PassWordBiz.getPhoneVerifyCode(etForgetPswPhone.getText().toString(), "1");
            }

            @Override
            protected void onExecuteSucceeded(String s) {
                hideLoadingDialog();
                runTimerTask();
                tvForgetGetmsm.setClickable(false);
                tvForgetGetmsm.setFocusable(false);
            }

            @Override
            protected void OnExecuteFailed(String error) {
                hideLoadingDialog();
                ToastUtils.showTost(context,error);
            }
        };
        getCode.execute();
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
        tvForgetGetmsm.setText("获取短信验证码");
        tvForgetGetmsm.setClickable(true);
        count = 60;
        timerTask.cancel();
        timer.cancel();
        timerTask = null;
        timer = null;
    }
}
