package com.desjf.dsjr.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.desjf.dsjr.R;
import com.desjf.dsjr.base.BaseActivity;
import com.desjf.dsjr.base.BaseFragment;
import com.desjf.dsjr.ui.fragment.BigRechargeFragment;
import com.desjf.dsjr.ui.fragment.FastRechargeFragment;

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

    //Fragment集合
    private List<BaseFragment> list_fragment;
    FastRechargeFragment fastRechargeFragment;
    BigRechargeFragment bigRechargeFragment;

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
                    .commit();

        }

    }

    private void initView(){
        //初始化标题
        tvTitle.setText("充值");
        //初始化按钮组
        radioGroup.check(R.id.rb_fast);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                for (int j = 0; j < list_fragment.size(); j++) {
                    getSupportFragmentManager().beginTransaction()
                            .hide(list_fragment.get(j))
                            .commit();
                    }
                switch (checkedId){
                    case R.id.rb_fast:
                        getSupportFragmentManager().beginTransaction()
                                .show(list_fragment.get(0))
                                .commit();
                        break;
                    case R.id.rb_big:
                        getSupportFragmentManager().beginTransaction()
                                .show(list_fragment.get(1))
                                .commit();
                        break;
                }
            }
        });
    }


    @OnClick({R.id.tv_recharge_record,R.id.iv_recharge_back})
    public void onClick(View view){
        switch(view.getId()){
            //充值记录
            case R.id.tv_recharge_record:
                Intent rechargeRecord=new Intent(this,RechargeRecordActivity.class);
                startActivity(rechargeRecord);
                break;
            //退出
            case R.id.iv_recharge_back:
                finish();
                break;
        }
    }

}
