package com.desjf.dsjr.activity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.desjf.dsjr.R;
import com.desjf.dsjr.photo.HackyViewPager;
import com.desjf.dsjr.photo.PhotoView;
import com.desjf.dsjr.photo.PhotoViewAttacher;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PhotoActivity extends Activity implements ViewPager.OnPageChangeListener {
    private static final String ISLOCKED_ARG = "isLocked";
    @BindView(R.id.tv_wcnm)
    TextView tvWcnm;
    private HackyViewPager mViewPager;
    private MenuItem menuLockItem;
    // 装点点的ImageView数组
    private ImageView[] tips;
    // 装ImageView数组
    private ViewGroup group;
    private TextView show_big_text;
    private static ArrayList<String> imgStringArray;
    private static ArrayList<String> contentStringArray;
    private int location;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo);
        ButterKnife.bind(this);
        show_big_text = (TextView) findViewById(R.id.show_big_text);
        group = (ViewGroup) findViewById(R.id.viewGroup);
        mViewPager = (HackyViewPager) findViewById(R.id.viewPager);
        if (savedInstanceState != null) {
            boolean isLocked = savedInstanceState.getBoolean(ISLOCKED_ARG,
                    false);
            ((HackyViewPager) mViewPager).setLocked(isLocked);
        }
        imgStringArray = getIntent().getStringArrayListExtra("images");
        contentStringArray = getIntent().getStringArrayListExtra("content");
        location = getIntent().getIntExtra("currentItem", 0);

        if (getIntent().getBooleanExtra("flag", false)) {
            group.setVisibility(View.GONE);
        } else {
            // 将点点加入到ViewGroup中
            tips = new ImageView[imgStringArray.size()];
            show_big_text.setText(location + 1 + "/" + tips.length);
            for (int i = 0; i < tips.length; i++) {
                ImageView imageView = new ImageView(this);
                imageView.setLayoutParams(new ViewGroup.LayoutParams(10, 10));
                tips[i] = imageView;
                if (i == 0) {
                    tips[i].setBackgroundResource(R.mipmap.point_sel);
                } else {
                    tips[i].setBackgroundResource(R.mipmap.point_normal);
                }

                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                        new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                                ViewGroup.LayoutParams.WRAP_CONTENT));
                layoutParams.leftMargin = 8;
                layoutParams.rightMargin = 8;
                group.addView(imageView, layoutParams);
            }

        }

        // 设置Adapter
        mViewPager.setAdapter(new SamplePagerAdapter(imgStringArray, PhotoActivity.this,contentStringArray));
        // 设置监听，主要是设置点点的背景
        mViewPager.setOnPageChangeListener(this);
        // 设置ViewPager的默认项
        mViewPager.setCurrentItem(location);
    }

    static class SamplePagerAdapter extends PagerAdapter {
        private ArrayList<String> mImageViews;
        private ArrayList<String> mContent;
        private Context context;

        public SamplePagerAdapter(ArrayList<String> mImageViews, Context context,ArrayList<String> mContent) {
            super();
            this.context = context;
            this.mImageViews = mImageViews;
            this.mContent = mContent;
        }

        @Override
        public int getCount() {
            return mImageViews.size();
        }

        @Override
        public View instantiateItem(ViewGroup container, int position) {
            PhotoView photoView = new PhotoView(context);
            ImageLoader.getInstance().displayImage(imgStringArray.get(position), photoView);
            ((HackyViewPager) container).addView(photoView,
                    ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            photoView.setOnPhotoTapListener(new PhotoViewAttacher.OnPhotoTapListener() {

                @Override
                public void onPhotoTap(View view, float x, float y) {
                    ((PhotoActivity) context).finish();
                }
            });
            return photoView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        tvWcnm.setText(contentStringArray.get(position));
    }

    @Override
    public void onPageSelected(int position) {
        setImageBackground(position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    private void setImageBackground(int selectItems) {
        for (int i = 0; i < tips.length; i++) {
            if (i == selectItems) {
                tips[i].setBackgroundResource(R.mipmap.point_sel);
            } else {
                tips[i].setBackgroundResource(R.mipmap.point_normal);
            }
            show_big_text.setText(selectItems + 1 + "/" + tips.length);

        }
        //show_big_text.setText(selectItems+1+"/"+tips.length);
    }

    private void toggleViewPagerScrolling() {
        if (isViewPagerActive()) {
            ((HackyViewPager) mViewPager).toggleLock();
        }
    }

    private void toggleLockBtnTitle() {
        boolean isLocked = false;
        if (isViewPagerActive()) {
            isLocked = ((HackyViewPager) mViewPager).isLocked();
        }
        String title = (isLocked) ? getString(R.string.menu_unlock)
                : getString(R.string.menu_lock);
        if (menuLockItem != null) {
            menuLockItem.setTitle(title);
        }
    }

    private boolean isViewPagerActive() {
        return (mViewPager != null && mViewPager instanceof HackyViewPager);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        if (isViewPagerActive()) {
            outState.putBoolean(ISLOCKED_ARG,
                    ((HackyViewPager) mViewPager).isLocked());
        }
        super.onSaveInstanceState(outState);
    }
}
