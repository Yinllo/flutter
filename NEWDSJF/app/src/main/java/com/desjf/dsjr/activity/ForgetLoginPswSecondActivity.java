package com.desjf.dsjr.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
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

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ForgetLoginPswSecondActivity extends BaseActivity {
    private Context context = this;
    @BindView(R.id.iv_forget_two_back)
    ImageView ivForgetTwoBack;
    @BindView(R.id.et_forget_two)
    EditText etForgetTwo;
    @BindView(R.id.tv_forget_two_over)
    TextView tvForgetTwoOver;
    private String phone;
    private String pwd;//用户密码
    Boolean flag=false;//用户密码类型 不为纯数字或者纯字母  则为false
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_login_psw_second);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        phone = intent.getStringExtra("phone");
    }

    @OnClick({R.id.iv_forget_two_back, R.id.tv_forget_two_over})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_forget_two_back:
                finish();
                break;
            case R.id.tv_forget_two_over:
                initOver();
                break;
        }
    }

    //判断输入的密码是否为纯数字或者纯字母
    private boolean checkPwd(){
        pwd=etForgetTwo.getText().toString();
        String nreg="^\\d+$";//数字
        boolean n=pwd.matches(nreg);

        String reg="^[a-zA-Z]+$";//字母
        boolean s=pwd.matches(reg);
        boolean length=pwd.length()<8;
        boolean lengths=pwd.length()>20;
        if(n||s||length||lengths){
            flag=true;
        }else {
            flag=false;
        }
        return flag;
    }


    private void initOver() {

        if (etForgetTwo.getText().toString().isEmpty()){
            ToastUtils.showTost(context,"请输入新密码");
            return;
        }
        if (checkPwd()){
            ToastUtils.showTost(context,"为保证安全，请您设置密码为8到20个数字，数字+字母的形式！");
            return;
        }
        showLoadingDialog();
        BizDataAsyncTask<String> getLogin = new BizDataAsyncTask<String>() {
            @Override
            protected String doExecute() throws ZYException, BizFailure {
                return PassWordBiz.findLoginPsw(phone,etForgetTwo.getText().toString(),"0");
            }

            @Override
            protected void onExecuteSucceeded(String s) {
                hideLoadingDialog();
                ToastUtils.showTost(context,"恭喜您修改成功！");
                finish();
            }

            @Override
            protected void OnExecuteFailed(String error) {
                hideLoadingDialog();
                ToastUtils.showTost(context,error);
            }
        };
        getLogin.execute();
    }
}
