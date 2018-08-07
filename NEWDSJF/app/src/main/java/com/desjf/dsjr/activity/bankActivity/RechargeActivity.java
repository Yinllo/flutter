package com.desjf.dsjr.activity.bankActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.desjf.dsjr.R;
import com.desjf.dsjr.base.BaseActivity;
import com.desjf.dsjr.base.BaseFragment;
import com.desjf.dsjr.fragment.bank.BigRechargeFragment;
import com.desjf.dsjr.fragment.bank.FastRechargeFragment;
import com.desjf.dsjr.model.bankModel.BankUserBalanceModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author YinL
 * @Describe 充值Activity
 */

public class RechargeActivity extends BaseActivity {

    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.recharge_rg)
    RadioGroup radioGroup;
    @BindView(R.id.tv_money)
    TextView tvKyye;

    //Fragment集合
    private List<BaseFragment> list_fragment;
    FastRechargeFragment fastRechargeFragment;
    BigRechargeFragment bigRechargeFragment;

    List<BankUserBalanceModel.UsermessageBean> list=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recharge);
        ButterKnife.bind(this);

        initData();
        initView();

    }


    //初始化三个Fragment
    private void initData() {
        fastRechargeFragment = new FastRechargeFragment();
        bigRechargeFragment=new BigRechargeFragment();
        list_fragment=new ArrayList<>();
        list_fragment.add(fastRechargeFragment);
        list_fragment.add(bigRechargeFragment);
        //初始化显示
        for(int i=0;i<list_fragment.size();i++){
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.recharge_fragment_layout,list_fragment.get(i),"BaseFragment"+i)
                    .show(list_fragment.get(0))
                    .hide(list_fragment.get(i))
                    .commitAllowingStateLoss();

        }

    }

    private void initView(){
        //初始化标题
        tvTitle.setText("充值");
        //可用余额
        tvKyye.setText(getIntent().getStringExtra("money"));

        //初始化按钮组
        radioGroup.check(R.id.rb_fast);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                for (int j = 0; j < list_fragment.size(); j++) {
                    getSupportFragmentManager().beginTransaction()
                            .hide(list_fragment.get(j))
                            .commitAllowingStateLoss();
                }
                switch (checkedId){
                    case R.id.rb_fast:
                        getSupportFragmentManager().beginTransaction()
                                .show(list_fragment.get(0))
                                .commitAllowingStateLoss();
                        break;
                    case R.id.rb_big:
                        getSupportFragmentManager().beginTransaction()
                                .show(list_fragment.get(1))
                                .commitAllowingStateLoss();
                        break;
                }
            }
        });
    }


    @OnClick({R.id.tv_recharge_record, R.id.iv_recharge_back})
    public void onClick(View view){
        switch(view.getId()){
            //充值记录
            case R.id.tv_recharge_record:
                Intent rechargeRecord=new Intent(this,BankRechargeRecordActivity.class);
                startActivity(rechargeRecord);
                break;
            //退出
            case R.id.iv_recharge_back:
                finish();
                break;
        }
    }

}
