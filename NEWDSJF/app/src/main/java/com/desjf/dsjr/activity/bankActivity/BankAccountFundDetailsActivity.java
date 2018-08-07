package com.desjf.dsjr.activity.bankActivity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;

import com.desjf.dsjr.R;
import com.desjf.dsjr.adapter.TabFragmentShouYeAdapter;
import com.desjf.dsjr.base.BaseActivity;
import com.desjf.dsjr.fragment.bank.BankAllFragment;
import com.desjf.dsjr.fragment.bank.BankCashFragment;
import com.desjf.dsjr.fragment.bank.BankInvestmentFragment;
import com.desjf.dsjr.fragment.bank.BankPaymentsFragment;
import com.desjf.dsjr.fragment.bank.BankRechargeFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

//我的页面   资金明细主Activity
public class BankAccountFundDetailsActivity extends BaseActivity {

    @BindView(R.id.iv_fund_details_back)
    ImageView back;

    private TabLayout tabLayout_shouye;
    private ViewPager viewPager_shouye;
    private List<String> strings = new ArrayList<String>();
    private List<Fragment> fragments = new ArrayList<Fragment>();


    BankPaymentsFragment fragment1;
    BankInvestmentFragment fragment2;
    BankAllFragment fragment3;
    BankRechargeFragment fragment4;
    BankCashFragment fragment5;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bank_account_fund_details);
        ButterKnife.bind(this);
        initdate();
        initView();
//        initEvents();
    }


    private void initView() {
        tabLayout_shouye = (TabLayout) findViewById(R.id.tablayout_shouye);
        viewPager_shouye = (ViewPager) findViewById(R.id.viewpager_ShouYe);
        viewPager_shouye.setAdapter(new TabFragmentShouYeAdapter(fragments, strings,
                getSupportFragmentManager(), this));


        tabLayout_shouye.setupWithViewPager(viewPager_shouye);
        tabLayout_shouye.setTabTextColors(getResources().getColor(R.color.radiobutton)
                , getResources().getColor(R.color.nmainOrign));

        //设置默认选中项
        tabLayout_shouye.getTabAt(2).select();
        viewPager_shouye.setCurrentItem(2);

    }

    private void initdate() {

        fragment1 = new  BankPaymentsFragment();
        fragments.add(fragment1);
        strings.add("回款");

        fragment2 = new  BankInvestmentFragment();
        fragments.add(fragment2);
        strings.add("投资");

        fragment3 = new  BankAllFragment();
        fragments.add(fragment3);
        strings.add("全部");

        fragment4 = new  BankRechargeFragment();
        fragments.add(fragment4);
        strings.add("充值");

        fragment5 = new  BankCashFragment();
        fragments.add(fragment5);
        strings.add("提现");


    }

//    private void initEvents() {
//
//        tabLayout_shouye.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
//            @Override
//            public void onTabSelected(TabLayout.Tab tab) {
//                if (tab == tabLayout_shouye.getTabAt(0)) {
//                    viewPager_shouye.setCurrentItem(0);
//                } else if (tab == tabLayout_shouye.getTabAt(1)) {
//                    viewPager_shouye.setCurrentItem(1);
//                } else if (tab == tabLayout_shouye.getTabAt(2)) {
//                    viewPager_shouye.setCurrentItem(2);
//                } else if (tab == tabLayout_shouye.getTabAt(3)) {
//                    viewPager_shouye.setCurrentItem(3);
//                }
//
//            }
//
//            @Override
//            public void onTabUnselected(TabLayout.Tab tab) {
//
//            }
//
//            @Override
//            public void onTabReselected(TabLayout.Tab tab) {
//
//            }
//        });
//
//    }


    @OnClick(R.id.iv_fund_details_back)
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_fund_details_back:
                finish();
                break;
        }

    }
}
