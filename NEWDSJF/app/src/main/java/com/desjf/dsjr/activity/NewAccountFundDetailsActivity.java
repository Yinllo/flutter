package com.desjf.dsjr.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;

import com.desjf.dsjr.R;
import com.desjf.dsjr.adapter.TabFragmentShouYeAdapter;
import com.desjf.dsjr.base.BaseActivity;
import com.desjf.dsjr.fragment.FundDetails.AllFragment;
import com.desjf.dsjr.fragment.FundDetails.CashFragment;
import com.desjf.dsjr.fragment.FundDetails.InvestmentFragment;
import com.desjf.dsjr.fragment.FundDetails.OtherFragment;
import com.desjf.dsjr.fragment.FundDetails.PaymentsFragment;
import com.desjf.dsjr.fragment.FundDetails.RechargeFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

//我的页面   资金明细主Activity
public class NewAccountFundDetailsActivity extends BaseActivity {

    @BindView(R.id.iv_fund_details_back)
    ImageView back;

    private TabLayout tabLayout_shouye;
    private ViewPager viewPager_shouye;
    private List<String> strings = new ArrayList<String>();
    private List<Fragment> fragments = new ArrayList<Fragment>();


    PaymentsFragment fragment1;
    InvestmentFragment fragment2;
    AllFragment fragment3;
    RechargeFragment fragment4;
    CashFragment fragment5;
    OtherFragment fragment6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_account_fund_details);
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

        fragment1 = new PaymentsFragment();
        fragments.add(fragment1);
        strings.add("回款");

        fragment2 = new InvestmentFragment();
        fragments.add(fragment2);
        strings.add("投资");

        fragment3 = new AllFragment();
        fragments.add(fragment3);
        strings.add("全部");

        fragment4 = new RechargeFragment();
        fragments.add(fragment4);
        strings.add("充值");

        fragment5 = new CashFragment();
        fragments.add(fragment5);
        strings.add("提现");

        fragment6 = new OtherFragment();
        fragments.add(fragment6);
        strings.add("其他");

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
