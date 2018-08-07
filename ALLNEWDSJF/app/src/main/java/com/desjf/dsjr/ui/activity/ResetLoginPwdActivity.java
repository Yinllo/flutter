package com.desjf.dsjr.ui.activity;

import android.os.Bundle;

import com.desjf.dsjr.R;
import com.desjf.dsjr.base.BaseActivity;

import butterknife.ButterKnife;

/**
 * @author YinL
 * @Date 2018-1-27
 * @Describe 重置登录密码
 */

public class ResetLoginPwdActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_re_set_login_pwd);
        ButterKnife.bind(this);
    }
}
