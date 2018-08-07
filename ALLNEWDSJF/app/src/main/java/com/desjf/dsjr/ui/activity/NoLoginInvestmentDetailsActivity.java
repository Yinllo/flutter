package com.desjf.dsjr.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.desjf.dsjr.R;
import com.desjf.dsjr.utils.DialogUtil;
import com.desjf.dsjr.widget.TimerView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class NoLoginInvestmentDetailsActivity extends AppCompatActivity {

    @BindView(R.id.tv_title)
    TextView tvTitle;//当前页面的标题
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_rate)
    TextView tvRate;//标的利率
    @BindView(R.id.tv_limit_time)
    TextView LimitTime;//标的期限
    @BindView(R.id.tv_limit_time_unit)
    TextView LimitTimeUnit;//标的期限单位
    @BindView(R.id.tv_residue)
    TextView residue;//剩余可投金额
    @BindView(R.id.tv_way)
    TextView way;//满标方式
    @BindView(R.id.tv_repayment)
    TextView repayment;//还款方式
    @BindView(R.id.timerView)
    TimerView timerView;//倒计时时间
    @BindView(R.id.tv_invest_money)
    TextView investMoney;//投资金额
    @BindView(R.id.tv_anticipated_income)
    TextView anticipatedIncome;//预计收益
    @BindView(R.id.tv_login_btn)
    TextView loginBtn;//确认投资按钮



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_no_login_investment_details);
        ButterKnife.bind(this);

    }



    @OnClick({R.id.tv_login_btn,R.id.iv_back,R.id.tv_way})
    public void OnClick(View view){
        switch(view.getId()){
            case R.id.tv_login_btn:
                Intent toLogin=new Intent(this,LoginActivity.class);
                startActivity(toLogin);
                break;
            case R.id.tv_way:
                DialogUtil.getSimpleDialogInstance(this,"计息方式","知道了");
                break;
            case R.id.iv_back:
                finish();
                break;
        }
    }

}
