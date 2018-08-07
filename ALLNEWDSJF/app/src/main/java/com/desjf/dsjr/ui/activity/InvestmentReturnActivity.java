package com.desjf.dsjr.ui.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.desjf.dsjr.R;
import com.desjf.dsjr.adapter.TabFragmentAdapter;
import com.desjf.dsjr.base.BaseActivity;
import com.desjf.dsjr.ui.fragment.InvestmentReturn.AlreadyReturnFragment;
import com.desjf.dsjr.ui.fragment.InvestmentReturn.AlreadyTenderFragment;
import com.desjf.dsjr.ui.fragment.InvestmentReturn.FreezeFragment;
import com.desjf.dsjr.ui.fragment.InvestmentReturn.WillFragment;
import com.desjf.dsjr.utils.MiscUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author YinL
 * @Describe  投资管理Activity
 */
public class InvestmentReturnActivity extends BaseActivity {
    @BindView(R.id.iv_return_back)
    ImageView back;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private List<String> strings = new ArrayList<String>();
    private List<Fragment> fragments = new ArrayList<Fragment>();

    AlreadyReturnFragment alreadyReturnFragment;
    AlreadyTenderFragment alreadyTenderFragment;
    FreezeFragment freezeFragment;
    WillFragment willFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_investment_return);
        ButterKnife.bind(this);
        initData();
        initView();
    }
    private void initView() {
        tabLayout = (TabLayout) findViewById(R.id.tablayout);
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        viewPager.setAdapter(new TabFragmentAdapter(fragments, strings,
                getSupportFragmentManager(), this));

        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setTabTextColors(getResources().getColor(R.color.font_gray)
                , getResources().getColor(R.color.nmainOrign));

        //设置默认选择项
        tabLayout.getTabAt(3).select();
        viewPager.setCurrentItem(3);

        //设置分割线
        LinearLayout linearLayout = (LinearLayout) tabLayout.getChildAt(0);
        linearLayout.setShowDividers(LinearLayout.SHOW_DIVIDER_MIDDLE);
        linearLayout.setDividerDrawable(ContextCompat.getDrawable(this,
                R.drawable.divider)); //设置分割线的样式
        linearLayout.setDividerPadding(MiscUtil.dipToPx(this,10)); //设置分割线间隔
    }

    private void initData() {

        alreadyReturnFragment = new AlreadyReturnFragment();
        fragments.add(alreadyReturnFragment);
        strings.add("已回款");

        alreadyTenderFragment = new AlreadyTenderFragment();
        fragments.add(alreadyTenderFragment);
        strings.add("已投资");

        freezeFragment = new FreezeFragment();
        fragments.add(freezeFragment);
        strings.add("冻结(未满)");

        willFragment = new WillFragment();
        fragments.add(willFragment);
        strings.add("将回款");

    }

    @OnClick(R.id.iv_return_back)
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_return_back:
                finish();
                break;
        }
    }
}
