package com.desjf.dsjr.widget;

import android.app.Dialog;
import android.content.Context;
import android.view.Window;
import android.view.WindowManager;

import com.desjf.dsjr.R;
import com.desjf.dsjr.utils.ScreenUtils;

/**
 * @author YinL
 * @Describe
 */

public class BaseDialog extends Dialog {
    private Context context;

    public BaseDialog(Context context) {
        super(context);
        this.context = context;

    }

    public BaseDialog(Context context, int themeResId) {
        super(context, themeResId);
        this.context = context;

    }

    protected BaseDialog(Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
        this.context = context;

    }

    /*
        显示对话框,参数：AppCompatActivity对象、Dialog对象、布局文件、背景资源文件、宽高比例
         */
    public void config(int layoutResID_contentView, int gravity, int anim, float width_config, float height_config, boolean canceledOnTouchOutside) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);// 去除对话框的标题
        setContentView(layoutResID_contentView);//填充对话框布局

        Window window = getWindow();
        WindowManager.LayoutParams lp = window.getAttributes();
        lp.dimAmount = 0.2f;
        window.setBackgroundDrawableResource(R.drawable.dialog_bg);//设置对话框边框背景,必须在代码中设置对话框背景，不然对话框背景是黑色的

        window.setGravity(gravity);

        switch (anim) {
            case 0://从底部进入
                window.setWindowAnimations(R.style.dialog_anim_bottom);

                break;
            case 1://从头部进入
                window.setWindowAnimations(R.style.dialog_anim_top);

                break;
            default:
                window.setWindowAnimations(R.style.dialog_zoom);

                break;
        }

        if (width_config != 0 && height_config != 0) {

            window.setLayout((int) (ScreenUtils.getScreenWidth() * width_config), (int) (ScreenUtils.getScreenHeight() * height_config));

        } else {
            window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);

        }
        setCanceledOnTouchOutside(canceledOnTouchOutside ? true : false);

    }

    public void config(int layoutResID_contentView, int gravity, int anim, boolean canceledOnTouchOutside) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);// 去除对话框的标题
        setContentView(layoutResID_contentView);//填充对话框布局

        Window window = getWindow();
        WindowManager.LayoutParams lp = window.getAttributes();
        lp.dimAmount = 0.2f;
        window.setBackgroundDrawableResource(R.drawable.dialog_bg);//设置对话框边框背景,必须在代码中设置对话框背景，不然对话框背景是黑色的

        window.setGravity(gravity);

        switch (anim) {
            case 0://从底部进入
                window.setWindowAnimations(R.style.dialog_anim_bottom);

                break;
            case 1://从头部进入
                window.setWindowAnimations(R.style.dialog_anim_top);

                break;
            default:
                window.setWindowAnimations(R.style.dialog_zoom);

                break;
        }
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);

        setCanceledOnTouchOutside(canceledOnTouchOutside ? true : false);

    }

    public void setMarginTop(int top) {
        Window window = getWindow();
        WindowManager.LayoutParams layoutParams = window.getAttributes();
        layoutParams.y = top;

    }
}
