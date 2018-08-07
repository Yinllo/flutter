package com.desjf.dsjr.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import com.desjf.dsjr.utils.ScreenUtils;


/**
 * @author YinL
 * @Describe  自定义状态栏  StatusBarView
 */

public class StatusBarView extends View {
    private Context context;
    public StatusBarView(Context context) {
        this(context,null);
    }

    public StatusBarView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context=context;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        super.onMeasure(widthMeasureSpec, MeasureSpec.makeMeasureSpec(ScreenUtils.getStatusBarHeight(context), MeasureSpec.EXACTLY));
    }
}
