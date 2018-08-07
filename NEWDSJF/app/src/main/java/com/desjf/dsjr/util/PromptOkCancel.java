package com.desjf.dsjr.util;


import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;

import com.desjf.dsjr.R;


public abstract class PromptOkCancel {

	private Context mContext;

	public PromptOkCancel(Context context) {
		mContext = context;
	}

	public void show(String title, String message, int okText, int cancelText, String type) {

		AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
		builder.setTitle(title);
		builder.setCancelable(false);

		builder.setMessage(message);

		builder.setPositiveButton(okText, new OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				onOk();
			}
		});
		if("".equals(type)){
		builder.setNegativeButton(cancelText, new OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				onCancel();
			}
		});
		}
		final AlertDialog dialog = builder.create();
		dialog.setOnCancelListener(new DialogInterface.OnCancelListener() {

			@Override
			public void onCancel(DialogInterface dialog) {
				onDismiss();
			}
		});
		
		dialog.show();
	}

	public void show(int titleRes, int messageRes, int okText, int cancelText,String type) {
		show(mContext.getString(titleRes), mContext.getString(messageRes),
				okText, cancelText,type);
	}

	public void show(int titleRes, int messageRes) {
		show(mContext.getString(titleRes), mContext.getString(messageRes),
				R.string.ok, R.string.cancel,"");
	}

	public void show(String title, int messageRes) {
		show(title, mContext.getString(messageRes), R.string.ok,
				R.string.cancel,"");
	}

	public void show(int title, String messageRes) {
		show(mContext.getString(title), messageRes, R.string.ok,
				R.string.cancel,"");
	}
	public void show(String title, String messageRes) {
		show(title, messageRes, R.string.ok,
				R.string.cancel,"");
	}
	
	public void show(String message){
		show(R.string.prompt, message);
	}

	public void show(int messageRes){
		show(R.string.prompt, messageRes);
	}

	protected abstract void onOk();

	protected void onCancel() {

	}

	protected void onDismiss() {

	}
}
