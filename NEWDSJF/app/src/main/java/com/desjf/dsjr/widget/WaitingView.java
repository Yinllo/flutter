package com.desjf.dsjr.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

import com.desjf.dsjr.R;


/**
 * @Author GL
 * @Description
 * @Date 2012-12-22
 */
public class WaitingView extends LinearLayout {
	public static final int WAITING_VIEW_ID = R.id.waiting_view;

	private LinearLayout llLoading;
	private Context context;

	public WaitingView(Context context, AttributeSet attrs) {
		super(context, attrs);
		this.context = context;
		init();
	}

	public WaitingView(Context context) {
		this(context, null);
	}

	private void init() {
		this.setVisibility(View.GONE);
		this.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

			}
		});
		llLoading = (LinearLayout) View.inflate(context,
				R.layout.waiting_view_loading_widget, null);
		
		this.addView(llLoading,
				new LinearLayout.LayoutParams(-1,
						-1));
	}


	public void show() {
		this.setVisibility(View.VISIBLE);
	}

	/**
	 * @Description 隐藏等待界面
	 */
	public void hide() {
		this.setVisibility(View.GONE);
	}

	public boolean isShowing() {
		return this.getVisibility() == View.VISIBLE;
	}
}
