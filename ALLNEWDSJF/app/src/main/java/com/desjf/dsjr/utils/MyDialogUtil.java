package com.desjf.dsjr.utils;

import android.app.Dialog;
import android.content.Context;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.desjf.dsjr.R;


/**
 * @author YinL
 * @Describe 自定义弹框提示类
 */
public class MyDialogUtil {

     public  static void showSimpleDialog(Context context , String message,String ok) {
        View view = LayoutInflater.from(context).inflate(R.layout.mydialog, null);
        TextView confirm; //确定按钮
        final TextView content; //内容
        confirm = (TextView) view.findViewById(R.id.tv_dialog_ok);
        content = (TextView) view.findViewById(R.id.tv_dialog_message);confirm.setText(ok);
        content.setText(message);
        final Dialog dialog = new Dialog(context, R.style.My_Dialog);
        dialog.setContentView(view);
         dialog.setCancelable(false);
//        dialog.getWindow().setBackgroundDrawableResource(R.color.bg_white);

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();
        DisplayMetrics dm = context.getResources().getDisplayMetrics();
        int displayWidth = dm.widthPixels;
        int displayHeight = dm.heightPixels;
        android.view.WindowManager.LayoutParams p = dialog.getWindow().getAttributes(); //获取对话框当前的参数值
        p.width = (int) (displayWidth * 0.8); //宽度设置为屏幕的0.5
        p.height = (int) (displayHeight * 0.25); //宽度设置为屏幕的0.25
        dialog.setCanceledOnTouchOutside(false);// 设置点击屏幕Dialog不消失
        dialog.getWindow().setAttributes(p);  //设置生效

    }

    public  static void showSkipDialog(final Context context , String message, String ok, final int type) {
        View view = LayoutInflater.from(context).inflate(R.layout.mydialog, null);
        TextView confirm; //确定按钮
        final TextView content; //内容
        confirm = (TextView) view.findViewById(R.id.tv_dialog_ok);
        content = (TextView) view.findViewById(R.id.tv_dialog_message);
        confirm.setText(ok);
        content.setText(message);
        final Dialog dialog = new Dialog(context, R.style.My_Dialog);
        dialog.setContentView(view);
        dialog.setCancelable(false);
//        dialog.getWindow().setBackgroundDrawableResource(R.color.bg_white);

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(type==1){  //充值

                }

            }
        });
        dialog.show();
        DisplayMetrics dm = context.getResources().getDisplayMetrics();
        int displayWidth = dm.widthPixels;
        int displayHeight = dm.heightPixels;
        android.view.WindowManager.LayoutParams p = dialog.getWindow().getAttributes(); //获取对话框当前的参数值
        p.width = (int) (displayWidth * 0.8); //宽度设置为屏幕的0.5
        p.height = (int) (displayHeight * 0.25); //宽度设置为屏幕的0.25
        dialog.setCanceledOnTouchOutside(false);// 设置点击屏幕Dialog不消失
        dialog.getWindow().setAttributes(p);  //设置生效

    }

    public static void showDialog(final Context context , String msg, final int flg) {
        final Dialog dialog = new Dialog(context, R.style.My_Dialog);
        dialog.setContentView(R.layout.dialog_auth_name);
        dialog.setCancelable(false);
        TextView tv_msg = (TextView) dialog.findViewById(R.id.tv_msg);
        tv_msg.setText(msg);
        dialog.findViewById(R.id.tv_cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();

            }
        });
        dialog.findViewById(R.id.tv_ok).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (flg == 1) {

                } else if (flg == 2) {

                } else if (flg == 3) {

                }else if(flg == 4){

                }
                dialog.dismiss();
            }
        });
        dialog.show();
        DisplayMetrics dm = context.getResources().getDisplayMetrics();
        int displayWidth = dm.widthPixels;
        int displayHeight = dm.heightPixels;
        android.view.WindowManager.LayoutParams p = dialog.getWindow().getAttributes(); //获取对话框当前的参数值
        p.width = (int) (displayWidth * 0.8); //宽度设置为屏幕的0.5
        p.height = (int) (displayHeight * 0.3); //宽度设置为屏幕的0.25
        dialog.setCanceledOnTouchOutside(false);// 设置点击屏幕Dialog不消失
        dialog.getWindow().setAttributes(p);  //设置生效
    }

}
