package com.desjf.dsjr.ui.activity;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.TypedValue;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.desjf.dsjr.R;
import com.desjf.dsjr.adapter.TabFragmentAdapter;
import com.desjf.dsjr.base.BaseActivity;
import com.desjf.dsjr.ui.fragment.MessageFragment;
import com.desjf.dsjr.ui.fragment.NoticeFragment;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @Author YinL
 * @Date 2018/4/24 0015
 * @Describe  公告消息Activity
 */
public class NoticeMessageActivity extends BaseActivity {
    @BindView(R.id.tv_title)
    TextView tvTitle;//当前页面的标题
    private TabLayout tabLayout;
    private ViewPager viewPager;
    //标题
    private List<String> strings = new ArrayList<String>();
    //Fragment
    private List<Fragment> fragments = new ArrayList<Fragment>();
    NoticeFragment noticeFragment;//公告
    MessageFragment messageFragment;//消息

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice_message);
        ButterKnife.bind(this);

        initData();
        initView();

    }

    private void initView() {
        //设置顶部导航栏标题
        tvTitle.setText("标题而已");

        tabLayout = this.findViewById(R.id.tabLayout);
        viewPager = this.findViewById(R.id.viewpager);
        viewPager.setAdapter(new TabFragmentAdapter(fragments, strings,getSupportFragmentManager(),this));
        //与ViewPager关联
        tabLayout.setupWithViewPager(viewPager);
        //设置下划线长度
        tabLayout.post(new Runnable() {
            @Override
            public void run() {
                setIndicator(tabLayout,60,60);
            }
        });

    }

    private void initData() {

        noticeFragment = new NoticeFragment();
        fragments.add(noticeFragment);
        strings.add("公告");

        messageFragment = new MessageFragment();
        fragments.add(messageFragment);
        strings.add("消息");

    }

    /**
     * 通过反射机制 修改TableLayout 的下划线长度
     */
    public void setIndicator (TabLayout tabs,int leftDip,int rightDip) {
        //通过反射获取到
        Class<?> tabLayout = tabs.getClass();
        Field tabStrip = null;
        try {
            tabStrip = tabLayout.getDeclaredField("mTabStrip");
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
            //设置模式
        tabStrip.setAccessible(true);
        //获得tabview
        LinearLayout llTab = null;
        try {
            llTab = (LinearLayout) tabStrip.get(tabs);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        //设置tabView的padding为0，并且设置了margin
        int left = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, leftDip, Resources.getSystem().getDisplayMetrics());
        int right = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, rightDip, Resources.getSystem().getDisplayMetrics());

        for (int i = 0; i < llTab.getChildCount(); i++) {
            View child = llTab.getChildAt(i);
            child.setPadding(0, 0, 0, 0);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT, 1);
            params.leftMargin = left;
            params.rightMargin = right;
            child.setLayoutParams(params);
            child.invalidate();
        }
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
