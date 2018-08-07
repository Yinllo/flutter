package com.desjf.dsjr.photo;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.desjf.dsjr.R;
import com.desjf.dsjr.base.BaseActivity;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;

/**
 * 相册预览
 * 
 * @author Liujinxin
 * @version 1.0.0
 * @since 2016/8/19
 */
public class ShowBigImage extends BaseActivity implements OnPageChangeListener {

	private static final String ISLOCKED_ARG = "isLocked";
	private HackyViewPager mViewPager;
	private MenuItem menuLockItem;
	// 装点点的ImageView数组
	private ImageView[] tips;
	// 装ImageView数组
	private ViewGroup group;
	private TextView show_big_text;
	private static ArrayList<String> imgStringArray;
	private int location;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_show_big_image);

		show_big_text=(TextView) findViewById(R.id.show_big_text);
		group = (ViewGroup) findViewById(R.id.viewGroup);
		mViewPager = (HackyViewPager) findViewById(R.id.viewPager);
		if (savedInstanceState != null) {
			boolean isLocked = savedInstanceState.getBoolean(ISLOCKED_ARG,
					false);
			((HackyViewPager) mViewPager).setLocked(isLocked);
		}
		imgStringArray = getIntent().getStringArrayListExtra("images");
		location=getIntent().getIntExtra("currentItem",0);
		if (getIntent().getBooleanExtra("flag", false)) {
			group.setVisibility(View.GONE);
		} else {
			// 将点点加入到ViewGroup中
			tips = new ImageView[imgStringArray.size()];
			show_big_text.setText(location+1+"/"+tips.length);
			for (int i = 0; i < tips.length; i++) {
				ImageView imageView = new ImageView(this);
				imageView.setLayoutParams(new LayoutParams(10, 10));
				tips[i] = imageView;
				if (i == 0) {
					tips[i].setBackgroundResource(R.mipmap.point_sel);
				} else {
					tips[i].setBackgroundResource(R.mipmap.point_normal);
				}

				LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
						new LayoutParams(LayoutParams.WRAP_CONTENT,
								LayoutParams.WRAP_CONTENT));
				layoutParams.leftMargin = 8;
				layoutParams.rightMargin = 8;
				group.addView(imageView, layoutParams);
			}
			
		}

		// 设置Adapter
		mViewPager.setAdapter(new SamplePagerAdapter(imgStringArray,ShowBigImage.this));
		// 设置监听，主要是设置点点的背景
		mViewPager.setOnPageChangeListener(this);
		// 设置ViewPager的默认项
		mViewPager.setCurrentItem(location);
	}

	static class SamplePagerAdapter extends PagerAdapter {
		private ArrayList<String> mImageViews;
		private Context context;

		public SamplePagerAdapter(ArrayList<String> mImageViews, Context context) {
			super();
			this.context = context;
			this.mImageViews = mImageViews;
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
					LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
			photoView.setOnPhotoTapListener(new PhotoViewAttacher.OnPhotoTapListener() {

				@Override
				public void onPhotoTap(View view, float x, float y) {
					((ShowBigImage) context).finish();
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
	public void onPageScrollStateChanged(int arg0) {

	}

	@Override
	public void onPageScrolled(int arg0, float arg1, int arg2) {

	}

	@Override
	public void onPageSelected(int arg0) {
		setImageBackground(arg0);
	}

	/**
	 * 设置选中的tip的背景
	 * 
	 * @param selectItems
	 */
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
