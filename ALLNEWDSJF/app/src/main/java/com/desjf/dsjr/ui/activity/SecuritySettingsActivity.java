package com.desjf.dsjr.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.desjf.dsjr.R;
import com.desjf.dsjr.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @Author YinL
 * @Date 2018/1/23 0015
 * @Describe 点击头像进入   安全设置Activity
 */
public class SecuritySettingsActivity extends BaseActivity {

    @BindView(R.id.tv_title)
    TextView tvTitle;//顶部导航栏标题
    @BindView(R.id.tv_phone_num)
    TextView tvPhoneNum;//电话号码
    @BindView(R.id.tv_bank_word)
    TextView tvBankWord;//是否开通银行存管文字描述
    @BindView(R.id.tv_agreement_word)
    TextView tvAgreementWord;//法大大授权文字描述
    @BindView(R.id.tv_version)
    TextView tvVersion;//当前版本号


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_security_settings);
        ButterKnife.bind(this);



    }

    private void initView(){
        //标题设置
       tvTitle.setText("安全设置");

    }

    @OnClick({R.id.iv_back,R.id.ll_login_pwd_revise,R.id.ll_phone_change,R.id.ll_bank_manager,R.id.ll_agreement,R.id.ll_test
              ,R.id.ll_finger,R.id.ll_gesture,R.id.ll_finger_pwd_revise,R.id.ll_bank_pwd_revise,R.id.ll_bankcard_change
              ,R.id.tv_setting_exit})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_login_pwd_revise:
                //重置登录密码
                Intent resetLoginPwd=new Intent(this,ResetLoginPwdActivity.class);
                startActivity(resetLoginPwd);
                break;
            case R.id.ll_phone_change:
                //重置手机号
                Intent resetPhoneNum=new Intent(this,ResetPhoneNumActivity.class);
                startActivity(resetPhoneNum);
                break;
            case R.id.ll_bank_manager:
                //银行存管
                break;
            case R.id.ll_agreement:
                //法大大电子合同授权
                break;
            case R.id.ll_test:
                //风险测评
                break;
            case R.id.ll_finger:
                //指纹密码是否开启
                break;
            case R.id.ll_gesture:
                //手势密码是否开启
                break;
            case R.id.ll_finger_pwd_revise:
                //手势密码修改
                break;
            case R.id.ll_bank_pwd_revise:
                //存管密码修改
                break;
            case R.id.ll_bankcard_change:
                //修改银行卡
                break;
            case R.id.iv_back:
                finish();
                break;
            case R.id.tv_setting_exit:
                //安全退出
                finish();
                break;
        }
    }

}
