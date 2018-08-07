package com.desjf.dsjr.bean;

import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.support.annotation.LayoutRes;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.desjf.dsjr.R;
import com.desjf.dsjr.base.BaseApplication;
import com.desjf.dsjr.model.TimeInfoModel;
import com.desjf.dsjr.widget.ITimer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Qiu
 * @version V1.0
 * @Description:倒计时-00:00:00
 * @date 2017/2/13 11:37
 */
public class TimerView extends LinearLayout implements ITimer, ITimer.OnCountTimeListener {

	private ITimer mTimer = new CountDown();

	private OnCountTimeListener mOnCountTimeListener;

	private List<ViewUnit> mViewUnitList;

	private String[] splits;

	boolean mIsShowLastSuffix = false;  // 是否显示 "秒" 后面的后缀

	ViewUnit mViewUnitStyle;    // 显示风格
	private int i=0;
	private int rootLayoutRes;//数字布局样式
	private int signLayoutRes;//文字指示布局样式
	private int textViewNumColor;//数字颜色
	private int textViewSignColor;//文字指示颜色
	private int textViewNumSize;//数字 字号
	private int textViewSignSize;//文字指示 字号
	private boolean isSecond;//是否带秒
	private boolean isTag;//:分割
	private boolean isHour;//显示小时

	int defaultColor = Color.parseColor("#ffffff");

	public void setTimer(ITimer timer) {
		mTimer = timer;
	}

	public TimerView(Context context) {
		this(context, null);
	}

	public TimerView(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public TimerView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.TimerView);
		rootLayoutRes = typedArray.getResourceId(R.styleable.TimerView_rootLayoutRes, R.layout.view_timer);
		signLayoutRes = typedArray.getResourceId(R.styleable.TimerView_rootLayoutRes, R.layout.view_timertip);
		textViewNumColor = typedArray.getColor(R.styleable.TimerView_textViewNumColor, defaultColor);
		textViewSignColor = typedArray.getColor(R.styleable.TimerView_textViewSignColor, defaultColor);
		textViewNumSize = typedArray.getDimensionPixelSize(R.styleable.TimerView_textViewNumSize, 16);
		textViewSignSize = typedArray.getDimensionPixelSize(R.styleable.TimerView_textViewSignSize, 16);
		isSecond = typedArray.getBoolean(R.styleable.TimerView_textViewIsSecond, true);
		isTag = typedArray.getBoolean(R.styleable.TimerView_textViewIsTag, false);
		isHour = typedArray.getBoolean(R.styleable.TimerView_textViewIsHour, true);
		typedArray.recycle();
		init();
	}


	@Override
	public void setOnCountTimeListener(OnCountTimeListener onCountTimeListener) {
		mOnCountTimeListener = onCountTimeListener;
	}

	@Override
	final public void start(long initTime) {
		mTimer.start(initTime);
	}

	@Override
	final public void stop() {
		mTimer.stop();
	}

	@Override
	public void countTime(long curTime) {
		updateShow(curTime);
		if (mOnCountTimeListener != null)
			mOnCountTimeListener.countTime(curTime);
	}

	@Override
	public void onEnd() {
		//发送广播，刷新页面
		if (i==0){
			Intent intent = new Intent();
			intent.setAction("shuaxin");
			BaseApplication.getAppContext().sendBroadcast(intent);
			i++;
		}

	}


	protected void init() {
		//有 : 分割的情况
		if (isTag) {
			//有秒的情况
			if (isHour) {
				splits = new String[]{ ":", ":"};
			}
		} else {
			//有秒的情况
			if (isSecond) {
				splits = new String[]{  ":",":",""};
			} else {
				splits = new String[]{ ":", ":"};
			}
		}

		mTimer.setOnCountTimeListener(this);
		this.setOrientation(LinearLayout.HORIZONTAL);
		this.setGravity(Gravity.CENTER);

		//整体风格
		mViewUnitStyle = new ViewUnit(
				new SubTimeItem(getContext(), rootLayoutRes, textViewNumColor),
				new SuffixItem(getContext(), signLayoutRes, textViewSignColor)
		);
	}

	private void initViewUnitList() {
		this.removeAllViews();
		mViewUnitList = new ArrayList<>();
		LayoutParams mRootLayoutParams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		int size = splits.length;
		for (int i = 0; i < size; i++) {
			ViewUnit viewUnit = new ViewUnit(
					new SubTimeItem(getContext(), mViewUnitStyle.mSubTimeItem),
					new SuffixItem(getContext(), mViewUnitStyle.mSuffixItem)
			);
			mViewUnitList.add(viewUnit);
			this.addView(viewUnit.mSubTimeItem.mRootView);
			View suffixView = viewUnit.mSuffixItem.mRootView;
			if (isHour) {
				mRootLayoutParams.setMargins(0,0, 0, 5);
				if (i != size - 1) {
					((TextView) suffixView).setText(splits[i]);
				}
				((TextView) suffixView).setLayoutParams(mRootLayoutParams);
			} else {
				((TextView) suffixView).setText(splits[i]);
			}
			this.addView(suffixView);
		}
	}

	boolean mHasInit = false;

	public void updateShow(long curTime) {
		if (!mHasInit) {
			mHasInit = true;
			initViewUnitList();
		}

		TimeInfoModel timeInfo = TimeInfoModel.make(curTime, isHour);

		List<Integer> timeList = null;
		List<String> formattedTimeList = null;

		if (!isHour) {
			timeList = Arrays.asList((int) timeInfo.getMday(), (int) timeInfo.getHour(), (int) timeInfo.getMinute(), (int) timeInfo.getSecond());
			formattedTimeList = Arrays.asList(timeInfo.getFormatedDay(isHour), timeInfo.getFormatedHour(isHour), timeInfo.getFormatedMinute(isHour), timeInfo.getFormatedSecond(isHour));
		} else {
			timeList = Arrays.asList((int) timeInfo.getHour(), (int) timeInfo.getMinute(), (int) timeInfo.getSecond());
			formattedTimeList = Arrays.asList(timeInfo.getFormatedHour(isHour), timeInfo.getFormatedMinute(isHour), timeInfo.getFormatedSecond(isHour));
		}

		for (int i = 0; i < mViewUnitList.size() && i < formattedTimeList.size(); i++) {
			View subTimeView = mViewUnitList.get(i).mSubTimeItem.mRootView;
			((TextView) subTimeView).setText(formattedTimeList.get(i));
		}
	}

	private static class ViewUnit {
		Item mSubTimeItem,      // 子时间单元: 如 时、分、秒
				mSuffixItem;    // 后缀: 如 冒号":"

		public ViewUnit(Item subTimeItem, Item suffixItem) {
			mSubTimeItem = subTimeItem;
			mSuffixItem = suffixItem;
		}

		private static class Item {
			@LayoutRes
			int mRootLayoutRes;  // 根布局
			int mTextColor;  // 文字颜色

			TextView mRootView;

			ViewGroup.LayoutParams mRootLayoutParams;

			public Item(Context context) {
				this(context, R.layout.view_timer, Color.parseColor("#f14848"));
			}

			public Item(Context context, int rootLayoutRes, int tvColor) {
				mRootLayoutRes = rootLayoutRes;
				mTextColor = tvColor;
				if (mRootLayoutParams == null) {
					mRootLayoutParams = new LayoutParams(
							LayoutParams.WRAP_CONTENT,
							LayoutParams.WRAP_CONTENT
					);
				}

				mRootView = (TextView) View.inflate(context, mRootLayoutRes, null);
				mRootView.setLayoutParams(mRootLayoutParams);
				mRootView.setTextColor(mTextColor);
			}

			public Item(Context context, Item item) {
				this(context, item.mRootLayoutRes, item.mTextColor);
			}

			public Item(Context context, Item item, int tvColor) {
				this(context, item.mRootLayoutRes, tvColor);
			}
		}
	}

	private static class SubTimeItem extends ViewUnit.Item {
		public SubTimeItem(Context context) {
			super(context);
		}

		public SubTimeItem(Context context, ViewUnit.Item item) {
			super(context, item);
		}

		public SubTimeItem(Context context, ViewUnit.Item item, int tvColor) {
			super(context, item, tvColor);
		}


		public SubTimeItem(Context context, @LayoutRes int rootLayoutRes, int tvColor) {
			super(context, rootLayoutRes, tvColor);
		}
	}

	private static class SuffixItem extends ViewUnit.Item {
		public SuffixItem(Context context) {
			super(context);
		}

		public SuffixItem(Context context, ViewUnit.Item item) {
			super(context, item);
		}

		public SuffixItem(Context context, ViewUnit.Item item, int tvColor) {
			super(context, item, tvColor);
		}

		public SuffixItem(Context context, @LayoutRes int rootLayoutRes, int tvColor) {
			super(context, rootLayoutRes, tvColor);
		}
	}
}
