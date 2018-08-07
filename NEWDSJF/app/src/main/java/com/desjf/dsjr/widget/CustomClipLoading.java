package com.desjf.dsjr.widget;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;

import com.desjf.dsjr.R;


/**
 * @author @Qiu
 * @version V1.0
 * @Description:自定义进度条
 * @date 2017/3/7 15:49
 */
public class CustomClipLoading extends ProgressDialog {
	private AnimationDrawable mAnimation;
	private Context mContext;

	public CustomClipLoading(Context context) {
		super(context, R.style.dialog);
		this.mContext = context;
		// 设置在窗口的边界之外时，该对话框是否被取消(true-取消)
		setOnOutSide(false);
	}

	// 设置在窗口的边界之外时，该对话框是否被取消(true-取消)
	public void setOnOutSide(boolean state) {
		setCanceledOnTouchOutside(state);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.view_progress_dialog);


	}

}
