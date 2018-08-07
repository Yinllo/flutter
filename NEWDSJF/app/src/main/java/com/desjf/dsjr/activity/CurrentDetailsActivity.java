package com.desjf.dsjr.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.desjf.dsjr.R;
import com.desjf.dsjr.base.BaseActivity;
import com.desjf.dsjr.utils.ToastUtils;

public class CurrentDetailsActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_current_details);
        initData();
    }

    private void initData() {
        Intent intent = getIntent();
        String key = intent.getStringExtra("key");
        ToastUtils.showTost(this,key);
    }
}
