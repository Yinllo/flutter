package com.desjf.dsjr.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.desjf.dsjr.R;
import com.desjf.dsjr.adapter.TabFragmentShouYeAdapter;
import com.desjf.dsjr.base.BaseActivity;
import com.desjf.dsjr.fragment.HistoryFragment;
import com.desjf.dsjr.fragment.NowFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author YinL
 * @Describe 运营数据
 */
public class OperationDataActivity extends BaseActivity {
    @BindView(R.id.tablayout)
    TabLayout tabLayout;
    @BindView(R.id.viewpager)
    ViewPager viewPager;

    private List<String> strings = new ArrayList<String>();
    private List<Fragment> fragments = new ArrayList<Fragment>();


    //实时数据Fragment
    private NowFragment nowFragment;
    //历史数据Fragment
    private HistoryFragment historyFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_operation_data);
        ButterKnife.bind(this);
        initData();
        initView();

    }

    private void initView() {
        viewPager.setAdapter(new TabFragmentShouYeAdapter(fragments, strings,
                getSupportFragmentManager(), this));

        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setTabTextColors(getResources().getColor(R.color.font_gray)
                , getResources().getColor(R.color.nmainOrign));

        //设置默认选中项
        //        tabLayout_shouye.getTabAt(3).select();
        //        viewPager_shouye.setCurrentItem(3);

    }

    private void initData(){

        nowFragment = new NowFragment();
        fragments.add(nowFragment);
        strings.add("实时数据");

        historyFragment=new HistoryFragment();
        fragments.add(historyFragment);
        strings.add("历史数据");

    }

    @OnClick(R.id.iv_back)
    public void OnClick(View view){
        switch(view.getId()){
            case R.id.iv_back:
                finish();
                break;
        }


    }

}
