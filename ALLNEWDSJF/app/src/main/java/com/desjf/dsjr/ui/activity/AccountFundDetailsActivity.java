package com.desjf.dsjr.ui.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;

import com.desjf.dsjr.R;
import com.desjf.dsjr.adapter.TabFragmentAdapter;
import com.desjf.dsjr.base.BaseActivity;
import com.desjf.dsjr.ui.fragment.FundDetails.AllFragment;
import com.desjf.dsjr.ui.fragment.FundDetails.CashFragment;
import com.desjf.dsjr.ui.fragment.FundDetails.InvestmentFragment;
import com.desjf.dsjr.ui.fragment.FundDetails.OtherFragment;
import com.desjf.dsjr.ui.fragment.FundDetails.PaymentsFragment;
import com.desjf.dsjr.ui.fragment.FundDetails.RechargeFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @Author YinL
 * @Date 2018/1/23 0015
 * @Describe 资金流水
 */
public class AccountFundDetailsActivity extends BaseActivity {
    @BindView(R.id.iv_fund_details_back)
    ImageView back;
    @BindView(R.id.tablayout)
    TabLayout tabLayout;
    @BindView(R.id.viewpager)
    ViewPager viewPager;

    private List<String> strings = new ArrayList<String>();
    private List<Fragment> fragments = new ArrayList<Fragment>();


    PaymentsFragment paymentsFragment;
    InvestmentFragment investmentFragment;
    AllFragment allFragment;
    RechargeFragment rechargeFragment;
    CashFragment cashFragment;
    OtherFragment otherFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_fund_details);
        ButterKnife.bind(this);
        initData();
        initView();
    }

    private void initView() {
        viewPager.setAdapter(new TabFragmentAdapter(fragments, strings,
                getSupportFragmentManager(), this));

        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setTabTextColors(getResources().getColor(R.color.font_gray)
                , getResources().getColor(R.color.nmainOrign));

        //设置默认选中项
                tabLayout.getTabAt(2).select();
                viewPager.setCurrentItem(2);
    }

    private void initData() {

        paymentsFragment = new PaymentsFragment();
        fragments.add(paymentsFragment);
        strings.add("回款");

        investmentFragment = new InvestmentFragment();
        fragments.add(investmentFragment);
        strings.add("投资");

        allFragment = new AllFragment();
        fragments.add(allFragment);
        strings.add("全部");

        rechargeFragment = new RechargeFragment();
        fragments.add(rechargeFragment);
        strings.add("充值");

        cashFragment = new CashFragment();
        fragments.add(cashFragment);
        strings.add("提现");

        otherFragment = new OtherFragment();
        fragments.add(otherFragment);
        strings.add("其他");

    }

    @OnClick(R.id.iv_fund_details_back)
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_fund_details_back:
                finish();
                break;
        }

    }
}
