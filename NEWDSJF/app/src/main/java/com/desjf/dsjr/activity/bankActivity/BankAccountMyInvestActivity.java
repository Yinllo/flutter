package com.desjf.dsjr.activity.bankActivity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.desjf.dsjr.R;
import com.desjf.dsjr.base.BaseActivity;
import com.desjf.dsjr.fragment.bank.BankChiyouFragment;
import com.desjf.dsjr.fragment.bank.BankHuiKuanFragment;
import com.desjf.dsjr.fragment.bank.BankJiangHuiKuanFragment;
import com.desjf.dsjr.fragment.bank.BankTouBiaoFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BankAccountMyInvestActivity extends BaseActivity {

    @BindView(R.id.iv_my_invest_back)
    ImageView ivMyInvestBack;
    @BindView(R.id.btn_myinvest_chiyou)
    RadioButton btnMyinvestChiyou;
    @BindView(R.id.btn_myinvest_toubiao)
    RadioButton btnMyinvestToubiao;
    @BindView(R.id.btn_myinvest_huikuan)
    RadioButton btnMyinvestHuikuan;
    @BindView(R.id.btn_myinvest_jianghuikuan)
    RadioButton btnMyinvestJiangHuikuan;
    @BindView(R.id.rg_my_invest)
    RadioGroup rgMyInvest;
    @BindView(R.id.vp_my_invest)
    ViewPager vpMyInvest;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bank_account_my_invest);
        ButterKnife.bind(this);
        initData();
        initView();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    private void initView() {
        rgMyInvest.check(R.id.btn_myinvest_jianghuikuan);
        rgMyInvest.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i) {
                    case R.id.btn_myinvest_chiyou:
                        vpMyInvest.setCurrentItem(1);
                        break;
                    case R.id.btn_myinvest_toubiao:
                        vpMyInvest.setCurrentItem(2);
                        break;
                    case R.id.btn_myinvest_huikuan:
                        vpMyInvest.setCurrentItem(3);
                        break;
                    case R.id.btn_myinvest_jianghuikuan:
                        vpMyInvest.setCurrentItem(0);
                        break;
                }
            }
        });
        vpMyInvest.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 1:
                        rgMyInvest.check(R.id.btn_myinvest_chiyou);
                        break;
                    case 2:
                        rgMyInvest.check(R.id.btn_myinvest_toubiao);
                        break;
                    case 3:
                        rgMyInvest.check(R.id.btn_myinvest_huikuan);
                        break;
                    case 0:
                        rgMyInvest.check(R.id.btn_myinvest_jianghuikuan);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        ivMyInvestBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void initData() {
        vpMyInvest.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                Fragment fragment = null;
                switch (position) {
                    case 1:
                        fragment= new BankHuiKuanFragment();
                        break;
                    case 2:
                        fragment = new BankChiyouFragment();
                        break;
                    case 3:
                        fragment = new BankTouBiaoFragment();
                        break;
                    case 0:
                        fragment = new BankJiangHuiKuanFragment();
                        break;
                }
                return fragment;
            }

            @Override
            public int getCount() {
                return 4;
            }
        });
    }
}
