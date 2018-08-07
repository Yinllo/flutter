package com.desjf.dsjr.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.desjf.dsjr.R;
import com.desjf.dsjr.base.BaseActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author YinL
 * @Describe 提现Activity
 */
public class WithdrawActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_withdraw);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.iv_withdraw_back,R.id.tv_withdraw_record})
    public void onClick(View view) {
        switch (view.getId()) {
            //提现记录
            case R.id.tv_withdraw_record:
                Intent withdrawRecord=new Intent(this,WithdrawRecordActivity.class);
                startActivity(withdrawRecord);
                break;
            //退出
            case R.id.iv_withdraw_back:
                finish();
                break;
        }
    }
}
