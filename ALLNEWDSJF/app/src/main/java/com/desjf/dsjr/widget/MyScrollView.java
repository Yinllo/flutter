package com.desjf.dsjr.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ScrollView;
/**
 * @author YinL
 * @Describe  自定义ScrollView
 */
public class MyScrollView extends ScrollView {

    public MyScrollView(Context context) {
        this(context,null);
    }

    public MyScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setOverScrollMode(OVER_SCROLL_NEVER);
    }
}
