package com.desjf.dsjr.utils;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.desjf.dsjr.R;
import com.desjf.dsjr.widget.MyToast;

public class AlertUtil {

    private static Toast mToast;

    public static void t(Context context, String msg) {
        if (mToast == null) {
            mToast = new MyToast(context);
            LayoutInflater inflate = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View v = inflate.inflate(R.layout.view_toast, null);
            TextView tv = (TextView) v.findViewById(android.R.id.message);
            tv.setText(msg);
            mToast.setView(v);
            // setGravity方法用于设置位置，此处为垂直居中
            mToast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
            mToast.setDuration(Toast.LENGTH_SHORT);
        } else {
            mToast.setText(msg);
        }
        mToast.show();
    }

    public static void t(Context context, int msgRes) {
        if (mToast == null) {
            mToast = new MyToast(context);
            LayoutInflater inflate = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View v = inflate.inflate(R.layout.view_toast, null);
            TextView tv = (TextView) v.findViewById(android.R.id.message);
            tv.setText(msgRes);
            mToast.setView(v);
            // setGravity方法用于设置位置，此处为垂直居中
            mToast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
            mToast.setDuration(Toast.LENGTH_SHORT);
        } else {
            mToast.setText(msgRes);
        }
        mToast.show();
    }

    public static void t(Context context, int msgRes, int duration) {
        Toast mToast = new MyToast(context);
        LayoutInflater inflate = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = inflate.inflate(R.layout.view_toast, null);
        TextView tv = (TextView) v.findViewById(android.R.id.message);
        tv.setText(msgRes);
        mToast.setView(v);
        // setGravity方法用于设置位置，此处为垂直居中
        mToast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
        mToast.setDuration(duration);
        mToast.show();
    }

    public static void t(Context context, String msg, int duration) {
        Toast mToast = new MyToast(context);
        LayoutInflater inflate = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = inflate.inflate(R.layout.view_toast, null);
        TextView tv = (TextView) v.findViewById(android.R.id.message);
        tv.setText(msg);
        mToast.setView(v);
        // setGravity方法用于设置位置，此处为垂直居中
        mToast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
        mToast.setDuration(duration);
        mToast.show();
    }
}
