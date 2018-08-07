package com.desjf.dsjr.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.TextView;

import com.desjf.dsjr.R;
import com.desjf.dsjr.adapter.TabFragmentShouYeAdapter;
import com.desjf.dsjr.base.BaseActivity;
import com.desjf.dsjr.fragment.InviteCashbackFragment;
import com.desjf.dsjr.fragment.TheAwardingFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 *  2018-5-14
 *  邀请分润
 */

public class InviteFenRunActivity extends BaseActivity {

    @BindView(R.id.tv_title)
    TextView tvTitle;//当前页面的标题
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private List<String> strings = new ArrayList<String>();
    private List<Fragment> fragments = new ArrayList<Fragment>();

    TheAwardingFragment theAwardingFragment;//红包发放
    InviteCashbackFragment inviteCashbackFragment;//邀请返现

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invite_fen_run);
        ButterKnife.bind(this);

        initData();
        initView();

    }

    private void initView() {
        tabLayout =findViewById(R.id.ifr_tabLayout);
        viewPager =findViewById(R.id.viewpager);
        viewPager.setAdapter(new TabFragmentShouYeAdapter(fragments, strings,getSupportFragmentManager(),this));
        //与ViewPager关联
        tabLayout.setupWithViewPager(viewPager);

    }

    private void initData() {

        theAwardingFragment=new TheAwardingFragment();
        fragments.add(theAwardingFragment);
        strings.add("邀请红包");

        inviteCashbackFragment=new InviteCashbackFragment();
        fragments.add(inviteCashbackFragment);
        strings.add("邀请返现");


    }


    @OnClick(R.id.iv_back)
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
        }
    }

}
