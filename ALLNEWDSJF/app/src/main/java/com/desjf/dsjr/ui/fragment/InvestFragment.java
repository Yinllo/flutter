package com.desjf.dsjr.ui.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.RadioGroup;

import com.desjf.dsjr.R;
import com.desjf.dsjr.base.BaseFragment;

import butterknife.BindView;

/**
 * @Author YinL
 * @Date 2018/1/15 0015
 * @Describe 投资Fragment
 */

public class InvestFragment extends BaseFragment{
    @BindView(R.id.rg_invest)
    RadioGroup rgInvest;
    @BindView(R.id.vp_invest)
    ViewPager vpInvest;
    @BindView(R.id.standard)//按钮下方横线，被选中时变为橙色
    View standard;
    @BindView(R.id.debt)
    View debt;

    //散标Fragment
    private StandardpowderFragment standardpowderFragment;
    //可转债Fragment
    private AssignmentFragment assignmentFragment;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_invest;
    }

    @Override
    protected void init(Bundle savedInstanceState) {

        initData();
        initView();

    }

    private void initData() {
        //债转Fragment
        assignmentFragment = new AssignmentFragment();
        //散标Fragment
        standardpowderFragment = new StandardpowderFragment();
        //ViewPager设置
        vpInvest.setAdapter(new FragmentPagerAdapter(getChildFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                Fragment fragment = null;
                switch (position) {
                    case 0:
                        fragment = standardpowderFragment;
                        break;
                    case 1:
                        fragment = assignmentFragment;
                        break;
                }
                return fragment;
            }

            @Override
            public int getCount() {
                return 2;
            }
        });
    }




    private void initView() {
        rgInvest.check(R.id.btn_standard_powder);
        rgInvest.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i) {
                    case R.id.btn_standard_powder:
                        //选中的是企业贷，将当前标记为被选中状态
                        vpInvest.setCurrentItem(0);
                        standard.setBackgroundColor(getResources().getColor(R.color.nmainOrign));
                        debt.setBackgroundColor(getResources().getColor(R.color.huibai));
                        break;
                    case R.id.btn_debt:
                        vpInvest.setCurrentItem(1);
                        standard.setBackgroundColor(getResources().getColor(R.color.huibai));
                        debt.setBackgroundColor(getResources().getColor(R.color.nmainOrign));
                        break;
                }
            }
        });
        vpInvest.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 0:
                        //选中的是企业贷，将当前标记为被选中状态
                        rgInvest.check(R.id.btn_standard_powder);
                        standard.setBackgroundColor(getResources().getColor(R.color.nmainOrign));
                        debt.setBackgroundColor(getResources().getColor(R.color.huibai));
                        break;
                    case 1:
                        rgInvest.check(R.id.btn_debt);
                        standard.setBackgroundColor(getResources().getColor(R.color.huibai));
                        debt.setBackgroundColor(getResources().getColor(R.color.nmainOrign));
                        break;

                }
            }
            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

}
