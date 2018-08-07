package com.desjf.dsjr.utils;

import android.app.Dialog;
import android.content.Context;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.TextView;

import com.desjf.dsjr.R;

/**
 * @Author YinL
 * @Date 2018/1/24 0024
 * @Describe       包括单按钮带提示内容的initSimpleDialog、和双按钮带内容提示的 initDialog
 */

public class DialogUtil {
    private static DialogUtil dialogUtil;
    private Dialog dialog;
    private TextView message;
    private TextView leftBtn;
    private TextView rightBtn;
    TextView confirm; //单按钮Dialog中的确定按钮
    private onDefineClickListener defineClickListener;

    /**
     * 双按钮Dialog
     * @param context
     * @param msg
     * @param cancel
     * @param ok
     * @return
     */
    public static DialogUtil getDialogInstance(Context context,String msg,String cancel,String ok) {
//        if (null == dialogUtil) {
            dialogUtil = new DialogUtil(context,msg,cancel,ok);
//        }
        dialogUtil.showDialog();
        return dialogUtil;
    }

    //双按钮
    private DialogUtil(Context context,String msg,String cancel,String ok) {
        initDialog(context,msg,cancel,ok);
    }

    private void initDialog(Context context,String msg,String cancel,String ok) {
        dialog = new Dialog(context,R.style.My_Dialog);

        dialog.setContentView(R.layout.dialog_auth_name);
        dialog.setCanceledOnTouchOutside(false);
        dialog.setCanceledOnTouchOutside(true);

        DisplayMetrics dm = context.getResources().getDisplayMetrics();
        int displayWidth = dm.widthPixels;
        int displayHeight = dm.heightPixels;
        android.view.WindowManager.LayoutParams p = dialog.getWindow().getAttributes(); //获取对话框当前的参数值
        p.width = (int) (displayWidth * 0.8); //宽度设置为屏幕的0.5
        p.height = (int) (displayHeight * 0.3); //宽度设置为屏幕的0.25
        dialog.setCanceledOnTouchOutside(false);// 设置点击屏幕Dialog不消失
        dialog.getWindow().setAttributes(p);  //设置生效

        message = (TextView) dialog.findViewById(R.id.tv_msg);
        leftBtn = (TextView) dialog.findViewById(R.id.tv_cancel);
        rightBtn = (TextView) dialog.findViewById(R.id.tv_ok);
        message.setText(msg);
        leftBtn.setText(cancel);
        rightBtn.setText(ok);
        leftBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (null != defineClickListener)
                    defineClickListener.onLeftBtnClick(v);
                dissmissDialog();
            }
        });
        rightBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (null != defineClickListener)
                    defineClickListener.onRightBtnClick(v);
                dissmissDialog();
            }
        });
    }


    /**
     * 单按钮
     * @param context
     * @param msg
     * @param ok
     * @return
     */
    public static DialogUtil getSimpleDialogInstance(Context context,String msg,String ok) {
//        if (null == dialogUtil) {
            dialogUtil = new DialogUtil(context,msg,ok);
//        }
        dialogUtil.showDialog();
        return dialogUtil;
    }

    //单按钮
    private DialogUtil(Context context,String msg,String ok) {
        initSimpleDialog(context,msg,ok);
    }

    //初始化单按钮的Dialog
    private void initSimpleDialog(Context context,String msg,String ok) {
        dialog = new Dialog(context,R.style.My_Dialog);

        dialog.setContentView(R.layout.mydialog);
        dialog.setCanceledOnTouchOutside(false);
        dialog.setCanceledOnTouchOutside(true);

        DisplayMetrics dm = context.getResources().getDisplayMetrics();
        int displayWidth = dm.widthPixels;
        int displayHeight = dm.heightPixels;
        android.view.WindowManager.LayoutParams p = dialog.getWindow().getAttributes(); //获取对话框当前的参数值
        p.width = (int) (displayWidth * 0.8); //宽度设置为屏幕的0.5
        p.height = (int) (displayHeight * 0.3); //宽度设置为屏幕的0.25
        dialog.setCanceledOnTouchOutside(false);// 设置点击屏幕Dialog不消失
        dialog.getWindow().setAttributes(p);  //设置生效

        message = (TextView) dialog.findViewById(R.id.tv_dialog_message);
        confirm = (TextView) dialog.findViewById(R.id.tv_dialog_ok);
        message.setText(msg);
        confirm.setText(ok);
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dissmissDialog();
            }
        });
    }


    public void showDialog() {
        if (dialog != null && !dialog.isShowing()) {
            dialog.show();
        }
    }

    public void dissmissDialog() {
        if (dialog != null && dialog.isShowing()) {
            dialog.dismiss();

        }
    }

    public void setMessage(String message) {
        if (null != message && !message.equals("")) {
            this.message.setText(message);
        }

    }

    public void setOnDefineClickListener(onDefineClickListener defineClickListener) {
        this.defineClickListener = defineClickListener;
    }

    public interface onDefineClickListener {
         void onLeftBtnClick(View v);

         void onRightBtnClick(View v);
    }
}
