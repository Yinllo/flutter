package com.desjf.dsjr.ui.activity;

import android.os.Bundle;

import com.desjf.dsjr.R;
import com.desjf.dsjr.base.BaseActivity;

import butterknife.ButterKnife;

/**
 * @author YinL
 * @Data 2018-1-27
 * @Describe 重置手机号码
 */
public class ResetPhoneNumActivity extends BaseActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_phone_num);
        ButterKnife.bind(this);
    }
}
