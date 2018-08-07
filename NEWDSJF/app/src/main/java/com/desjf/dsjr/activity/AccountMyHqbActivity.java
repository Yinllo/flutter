package com.desjf.dsjr.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.desjf.dsjr.R;
import com.desjf.dsjr.base.BaseActivity;
import com.desjf.dsjr.utils.BarUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AccountMyHqbActivity extends BaseActivity {

    @BindView(R.id.ll_hqb_jiaoyi)
    LinearLayout llHqbJiaoyi;
    @BindView(R.id.tv_hqb_money_out)
    TextView tvHqbMoneyOut;
    @BindView(R.id.tv_hqb_money_in)
    TextView tvHqbMoneyIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_my_hqb);
        ButterKnife.bind(this);
        BarUtils.setColor(this, getResources().getColor(R.color.endmian), 0);
        initData();
    }

    private void initData() {
        llHqbJiaoyi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AccountMyHqbActivity.this, AccountTransactionRecordsActivity.class);
                startActivity(intent);
            }
        });
    }

    @OnClick({R.id.tv_hqb_money_out, R.id.tv_hqb_money_in})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_hqb_money_out:
                //活期宝转出
                Intent intent = new Intent(AccountMyHqbActivity.this,AccountHqbChangeMoneytActivity.class);
                startActivity(intent);
                break;
            case R.id.tv_hqb_money_in:
                //活期宝转入
                break;
        }
    }
}
