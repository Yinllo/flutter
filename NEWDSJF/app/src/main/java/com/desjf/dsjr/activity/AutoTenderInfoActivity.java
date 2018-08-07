package com.desjf.dsjr.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.desjf.dsjr.R;
import com.desjf.dsjr.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AutoTenderInfoActivity extends BaseActivity {

    @BindView(R.id.iv_auto_tender_info_back)
    ImageView ivAutoTenderInfoBack;
    @BindView(R.id.tv_project_title)
    TextView tvProjectTitle;
    @BindView(R.id.tv_edd)
    TextView tvEdd;
    private String str;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auto_tender_info);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        str = intent.getStringExtra("rate");
        tvEdd.setText("3、自动投标投出的金额最高只能达到借款标额度的"+str+"%。");
    }

    @OnClick(R.id.iv_auto_tender_info_back)
    public void onClick() {
        finish();
    }
}
