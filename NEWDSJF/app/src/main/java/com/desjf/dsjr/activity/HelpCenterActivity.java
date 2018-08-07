package com.desjf.dsjr.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.desjf.dsjr.R;
import com.desjf.dsjr.base.BaseActivity;
import com.desjf.dsjr.utils.BarUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class HelpCenterActivity extends BaseActivity {

    @BindView(R.id.iv_charge_back)
    ImageView ivChargeBack;
    @BindView(R.id.ll_cjwt)
    LinearLayout llCjwt;
    @BindView(R.id.ll_zfsm)
    LinearLayout llZfsm;
    @BindView(R.id.ll_zcfg)
    LinearLayout llZcfg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help_center);
        ButterKnife.bind(this);
        BarUtils.setColor(this, getResources().getColor(R.color.main), 0);
    }

    @OnClick({R.id.iv_charge_back, R.id.ll_cjwt, R.id.ll_zfsm, R.id.ll_zcfg})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_charge_back:
                finish();
                break;
            case R.id.ll_cjwt:
                Intent intent_one = new Intent(HelpCenterActivity.this,WebViewActivity.class);
                intent_one.putExtra("web",7);
                startActivity(intent_one);
                break;
            case R.id.ll_zfsm:
                Intent intent_two = new Intent(HelpCenterActivity.this,WebViewActivity.class);
                intent_two.putExtra("web",8);
                startActivity(intent_two);
                break;
            case R.id.ll_zcfg:
                Intent intent_three = new Intent(HelpCenterActivity.this,WebViewActivity.class);
                intent_three.putExtra("web",9);
                startActivity(intent_three);
                break;
        }
    }
}
