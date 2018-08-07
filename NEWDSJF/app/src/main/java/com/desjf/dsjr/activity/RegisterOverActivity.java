package com.desjf.dsjr.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.desjf.dsjr.R;
import com.desjf.dsjr.base.BaseActivity;
import com.desjf.dsjr.bean.NewRegBean;
import com.desjf.dsjr.biz.retrofit.BankHttpUtil;
import com.desjf.dsjr.biz.retrofit.CallUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RegisterOverActivity extends BaseActivity {
    @BindView(R.id.ll_auto_to_main)
    TextView llAutoToMain;
    private Context context = this;
    @BindView(R.id.iv_over_back)
    ImageView ivOverBack;
    @BindView(R.id.tv_over_to_realname)
    TextView tvOverToRealname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_over_new);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.iv_over_back, R.id.ll_auto_to_main, R.id.tv_over_to_realname})
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.iv_over_back:
              Intent  intent = new Intent(context, MainActivity.class);
                startActivity(intent);
                break;
            case R.id.ll_auto_to_main:
                Intent  intenta = new Intent(context, MainActivity.class);
                startActivity(intenta);
                break;
            case R.id.tv_over_to_realname:
                //去实名认证e
//                intent = new Intent(context, AccountRealNameActivity.class);
//                intent.putExtra("RealName",2);
//                intent.putExtra("register", "1");
//                finish();

                //前往开通存管
                Intent i=this.getIntent();
                CallUtils.call(RegisterOverActivity.this, BankHttpUtil.getHttpRequestService().bankReg(i.getStringExtra("phoneNumber")), new CallUtils.MyCallListener() {
                    @Override
                    public void onRespnseSuccess(String jsonString) {
                        NewRegBean newRegBean= JSON.parseObject(jsonString,NewRegBean.class);
                        Intent i=new Intent(RegisterOverActivity.this,BankWebViewActivity.class);
                        i.putExtra("type",1);
                        i.putExtra("newRegBean",newRegBean);
                        startActivity(i);

                    }
                });
                break;
        }

    }
}
