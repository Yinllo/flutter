package com.desjf.dsjr.ui.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.desjf.dsjr.R;
import com.desjf.dsjr.adapter.TabFragmentAdapter;
import com.desjf.dsjr.ui.fragment.IntegrationDetails.AllFragment;
import com.desjf.dsjr.ui.fragment.IntegrationDetails.ExchangeFragment;
import com.desjf.dsjr.ui.fragment.IntegrationDetails.InvestmentFragment;
import com.desjf.dsjr.ui.fragment.IntegrationDetails.OtherFragment;
import com.desjf.dsjr.ui.fragment.IntegrationDetails.SignInFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author YinL
 * @Date 2018-1-27
 * @Describe 积分明细
 */
public class IntegrationDetailsActivity extends AppCompatActivity {
    @BindView(R.id.tablayout)
    TabLayout tabLayout;
    @BindView(R.id.viewpager)
    ViewPager viewPager;

    private List<String> strings = new ArrayList<String>();
    private List<Fragment> fragments = new ArrayList<Fragment>();

    AllFragment allFragment;
    ExchangeFragment exchangeFragment;
    OtherFragment otherFragment;
    InvestmentFragment investmentFragment;
    SignInFragment signInFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_integration_details);
        ButterKnife.bind(this);

        initView();
        initData();

    }

    private void initView() {
        viewPager.setAdapter(new TabFragmentAdapter(fragments, strings,
                getSupportFragmentManager(), this));

        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setTabTextColors(getResources().getColor(R.color.font_gray)
                , getResources().getColor(R.color.nmainOrign));

        //设置默认选中项
        //        tabLayout_shouye.getTabAt(3).select();
        //        viewPager_shouye.setCurrentItem(3);

    }

    private void initData(){
        //全部、兑换、其他、投资、签到
        allFragment = new AllFragment();
        fragments.add(allFragment);
        strings.add("全部");

        exchangeFragment=new ExchangeFragment();
        fragments.add(exchangeFragment);
        strings.add("兑换");

        otherFragment = new OtherFragment();
        fragments.add(otherFragment);
        strings.add("其他");

        investmentFragment=new InvestmentFragment();
        fragments.add(investmentFragment);
        strings.add("投资");

        signInFragment =new SignInFragment();
        fragments.add(signInFragment);
        strings.add("签到");

    }


}
