package com.desjf.dsjr.widget;


import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.desjf.dsjr.R;
import com.desjf.dsjr.utils.StringUtil;

public abstract class PromptOkCancel {

    private Context mContext;

    public PromptOkCancel(Context context) {
        mContext = context;
    }

    public void show(String title, String message, int okText, int cancelText, String type, boolean middleFlg) {
        final Dialog dialog = new Dialog(mContext, R.style.My_Dialog);
        dialog.setContentView(R.layout.dialog_normal);
        dialog.setCancelable(false);
        TextView tv_title = (TextView) dialog.findViewById(R.id.tv_title);
        TextView tv_msg = (TextView) dialog.findViewById(R.id.tv_msg);
        TextView tv_cancel = (TextView) dialog.findViewById(R.id.tv_cancel);
        TextView tv_ok = (TextView) dialog.findViewById(R.id.tv_ok);

        if (middleFlg) {
            LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) tv_msg.getLayoutParams();
            params.gravity = Gravity.CENTER;
            tv_msg.setLayoutParams(params);
        }

        tv_title.setText(title);
        if (!StringUtil.isEmpty(message)) {
            tv_msg.setText(message);
            tv_msg.setVisibility(View.VISIBLE);
        } else {
            tv_msg.setVisibility(View.GONE);
        }
        tv_cancel.setText(cancelText);
        tv_ok.setText(okText);

        if (type != null && type.equals("1")) {
            //强制更新
            dialog.findViewById(R.id.tv_cancel).setVisibility(View.GONE);
            dialog.findViewById(R.id.tv_view).setVisibility(View.GONE);
        } else {
            //不强制更新
            dialog.findViewById(R.id.tv_cancel).setVisibility(View.VISIBLE);
            dialog.findViewById(R.id.tv_view).setVisibility(View.VISIBLE);
        }

        dialog.findViewById(R.id.tv_cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                onCancel();
            }
        });
        dialog.findViewById(R.id.tv_ok).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                onOk();
            }
        });

        dialog.show();
    }

    public void show(int titleRes, int messageRes) {
        show(mContext.getString(titleRes), mContext.getString(messageRes),
                R.string.ok, R.string.cancel, "", true);
    }

    public void show(String title, int messageRes) {
        show(title, mContext.getString(messageRes), R.string.ok,
                R.string.cancel, "", true);
    }

    public void show(int title, String messageRes) {
        show(mContext.getString(title), messageRes, R.string.ok,
                R.string.cancel, "", true);
    }

    public void show(String title, String messageRes) {
        show(title, messageRes, R.string.ok,
                R.string.cancel, "", true);
    }

    public void show(String message) {
        show(R.string.prompt, message);
    }

    public void show(int messageRes) {
        show(R.string.prompt, messageRes);
    }

    protected abstract void onOk();

    protected void onCancel() {

    }
}
