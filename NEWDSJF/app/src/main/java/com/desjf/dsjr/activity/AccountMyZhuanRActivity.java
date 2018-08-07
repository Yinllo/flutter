package com.desjf.dsjr.activity;

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
import com.desjf.dsjr.fragment.ChiyouFragment;
import com.desjf.dsjr.fragment.HuiKuanFragment;
import com.desjf.dsjr.fragment.KzrFragment;
import com.desjf.dsjr.fragment.TouBiaoFragment;
import com.desjf.dsjr.fragment.YzrFragment;
import com.desjf.dsjr.fragment.ZrzFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AccountMyZhuanRActivity extends BaseActivity {


    @BindView(R.id.iv_wdzr_back)
    ImageView ivWdzrBack;
    @BindView(R.id.btn_wdzr_kzr)
    RadioButton btnWdzrKzr;
    @BindView(R.id.btn_wdzr_wrz)
    RadioButton btnWdzrWrz;
    @BindView(R.id.btn_wdzr_yzr)
    RadioButton btnWdzrYzr;
    @BindView(R.id.rg_wdzr)
    RadioGroup rgWdzr;
    @BindView(R.id.vp_wdzr)
    ViewPager vpWdzr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_my_zhuan_r);
        ButterKnife.bind(this);
        initData();
        initView();
    }

    private void initView() {
        rgWdzr.check(R.id.btn_wdzr_kzr);
        rgWdzr.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i) {
                    case R.id.btn_wdzr_kzr:
                        vpWdzr.setCurrentItem(0);
                        break;
                    case R.id.btn_wdzr_wrz:
                        vpWdzr.setCurrentItem(1);
                        break;
                    case R.id.btn_wdzr_yzr:
                        vpWdzr.setCurrentItem(2);
                        break;
                }
            }
        });
        vpWdzr.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 0:
                        rgWdzr.check(R.id.btn_wdzr_kzr);
                        break;
                    case 1:
                        rgWdzr.check(R.id.btn_wdzr_wrz);
                        break;
                    case 2:
                        rgWdzr.check(R.id.btn_wdzr_yzr);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        ivWdzrBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void initData() {
        vpWdzr.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                Fragment fragment = null;
                switch (position) {
                    case 0:
                        fragment = new KzrFragment();
                        break;
                    case 1:
                        fragment = new ZrzFragment();
                        break;
                    case 2:
                        fragment = new YzrFragment();
                        break;
                }
                return fragment;
            }

            @Override
            public int getCount() {
                return 3;
            }
        });
    }
}
