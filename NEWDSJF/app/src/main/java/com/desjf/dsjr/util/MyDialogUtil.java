package com.desjf.dsjr.util;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.desjf.dsjr.R;
import com.desjf.dsjr.activity.AccountAddbanksActivity;
import com.desjf.dsjr.activity.AccountChargeActivity;
import com.desjf.dsjr.activity.AccountRealNameActivity;
import com.desjf.dsjr.activity.MainActivity;
import com.desjf.dsjr.activity.StandarDetailsActivity;
import com.desjf.dsjr.base.BaseApplication;
import com.desjf.dsjr.config.DsjrConfig;
import com.desjf.dsjr.utils.PreferenceCache;

/**
 * Created by YL on 2017/11/16 0016.      自定义弹框提示类
 */

public class MyDialogUtil {
     public  static void showSimpleDialog(Context context , String message,String ok) {
        View view = LayoutInflater.from(context).inflate(R.layout.mydialog, null);
        TextView confirm; //确定按钮
        final TextView content; //内容
        confirm = (TextView) view.findViewById(R.id.tv_dialog_ok);
        content = (TextView) view.findViewById(R.id.tv_dialog_message);confirm.setText(ok);
        content.setText(message);
        final Dialog dialog = new Dialog(context,R.style.My_Dialog);
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
        content = (TextView) view.findViewById(R.id.tv_dialog_message);confirm.setText(ok);
        content.setText(message);
        final Dialog dialog = new Dialog(context,R.style.My_Dialog);
        dialog.setContentView(view);
        dialog.setCancelable(false);
//        dialog.getWindow().setBackgroundDrawableResource(R.color.bg_white);

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(type==1){  //充值
                    Intent intent = new Intent();
                    intent.setAction("withdraw");
                    BaseApplication.getAppContext().sendBroadcast(intent);
                    dialog.dismiss();
                    AccountChargeActivity.mInstace.finish();
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
                    //未实名
                    Intent intent = new Intent(context, AccountRealNameActivity.class);
                    context.startActivity(intent);
                } else if (flg == 2) {
                    //未绑卡
                    Intent inBankCard = new Intent(context, AccountAddbanksActivity.class);
                    context.startActivity(inBankCard);
                } else if (flg == 3) {
                    //退出账户
                    PreferenceCache.putToken("");
//                    Util.showLogin(AccountSettingActivity.this);
                    PreferenceCache.putGestureFlag(false);
                    Intent over = new Intent(context, MainActivity.class);
//                    DsjrConfig.TYPE = 1;
                    DsjrConfig.WHERE = 10;
//                    PreferencesUtil.writeInt(AccountSettingActivity.this, "where", 2);
                    context.startActivity(over);
                }else if(flg == 4){
                    Intent i = new Intent();
                    i.setAction("shuaxin");
                    BaseApplication.getAppContext().sendBroadcast(i);
                    StandarDetailsActivity.mInstace.finish();

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
