package com.desjf.dsjr.activity;

import android.os.Bundle;
import android.widget.ImageView;

import com.desjf.dsjr.R;
import com.desjf.dsjr.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PhoneUsActivity extends BaseActivity {

    @BindView(R.id.iv_phone_us_back)
    ImageView ivPhoneUsBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone_us);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.iv_phone_us_back)
    public void onClick() {
        finish();
    }
}
