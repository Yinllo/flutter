package com.desjf.dsjr.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.desjf.dsjr.R;
import com.desjf.dsjr.utils.PreferenceCache;

import java.util.ArrayList;
import java.util.List;

    /**
     * @Author YinL
     * @Date 2018/1/15 0015
     * @Describe APP第一次启动时的欢迎页面
     */
public class WelcomeActivity extends AppCompatActivity {

    //是否显示引导界面
    boolean isShow = false;
    //ViewPager对象
    private ViewPager mViewPager;
    //装载小圆圈的LinearLayout(底部小圆点，表示当前选中的状态)
    private LinearLayout indicatorLayout;
    //ViewPager的每个页面集合
    private List<View> views;
    //ViewPager下面的小圆圈(底部小圆点，表示当前选中的状态)
    private ImageView[] mImageViews;
    //PagerAdapter对象（页面切换）
    private MyPagerAdapter myPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        // 得到Preference存储的isShow数据
        isShow = PreferenceCache.getGuideBoolean();
        //isShow=false;调试的时候用的
        if (isShow) {
            initLog();
        } else {
            initView();
        }
    }

    //如果当前不是第一次启动则 进入登录界面
    private void initLog() {
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }

    //第一次启动时进入引导界面
    private void initView() {
        mViewPager = (ViewPager) findViewById(R.id.guide_viewPager);
        indicatorLayout = (LinearLayout) findViewById(R.id.linearlayout);
        LayoutInflater inflater = LayoutInflater.from(this);
        views = new ArrayList<View>();
        views.add(inflater.inflate(R.layout.pager_guide_one, null));
        views.add(inflater.inflate(R.layout.pager_guide_two, null));
        views.add(inflater.inflate(R.layout.pager_guide_three, null));
        myPagerAdapter = new MyPagerAdapter(this, views);
        mImageViews = new ImageView[views.size()];
        drawCircl();
        mViewPager.setAdapter(myPagerAdapter);
        mViewPager.setOnPageChangeListener(new GuidePageChangeListener());
    }

    //画出底部的圆圈
    private void drawCircl() {
        int num = views.size();
        for (int i = 0; i < num; i++) {
            //实例化每一个mImageViews[i]
            mImageViews[i] = new ImageView(this);
            if (i == 0) {
                // 默认选中第一张照片，所以将第一个小圆圈变为btn_cricle_choose
                mImageViews[i].setImageResource(R.drawable.btn_cricle_choose);
            } else {
                mImageViews[i].setImageResource(R.drawable.btn_cricle_white);
            }
            // 给每个小圆圈都设置间隔
            mImageViews[i].setPadding(7, 7, 7, 7);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            params.gravity = Gravity.CENTER_VERTICAL;
            // 让每一个小圆圈都在LinearLayout的CENTER_VERTICAL（中间垂直）
            indicatorLayout.addView(mImageViews[i], params);
        }

    }

    //页面改变监听事件
    private class GuidePageChangeListener implements ViewPager.OnPageChangeListener {
        public void onPageScrollStateChanged(int arg0) {
        }

        public void onPageScrolled(int arg0, float arg1, int arg2) {
        }

        //页面有所改变，如果是当前页面，将小圆圈改为btn_cricle_choose，其他页面则改为btn_cricle_white
        public void onPageSelected(int arg0) {
            for (int i = 0; i < mImageViews.length; i++) {
                if (arg0 != i) {
                    mImageViews[i]
                            .setImageResource(R.drawable.btn_cricle_white);
                } else {
                    mImageViews[arg0]
                            .setImageResource(R.drawable.btn_cricle_choose);
                }
            }
        }
    }

    class MyPagerAdapter extends PagerAdapter {
        private List<View> mViews;
        private Activity mContext;

        public MyPagerAdapter(Activity context, List<View> views) {
            this.mViews = views;
            this.mContext = context;
        }

        @Override
        public int getCount() {
            return mViews.size();
        }

        @Override
        public boolean isViewFromObject(View arg0, Object arg1) {
            return arg0 == arg1;
        }

        @Override
        public int getItemPosition(Object object) {
            return super.getItemPosition(object);
        }

        @Override
        public void destroyItem(View arg0, int arg1, Object arg2) {
            ((ViewPager) arg0).removeView(mViews.get(arg1));
        }

        // 实例化页卡，如果变为最后一页，则获取它的button并且添加点击事件
        @Override
        public Object instantiateItem(View arg0, int arg1) {
            ((ViewPager) arg0).addView(mViews.get(arg1), 0);
            if (arg1 == mViews.size() - 1) {
                TextView enterBtn = (TextView) arg0
                        .findViewById(R.id.guide_enter);
                enterBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // 将isShow保存为true，并进入登录界面
                        PreferenceCache.setGuideBoolean(true);
                        initLog();
                    }
                });
            }
            return mViews.get(arg1);
        }
    }
}
