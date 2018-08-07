package com.desjf.dsjr.widget;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

/**
 * @Author YinL
 * @Date 2018/4/14 0014
 * @Describe  解决RecyclerView数据更新时的内部BUG
 */

public class RecyclerViewNoBugLinearLayoutManager extends LinearLayoutManager {

    public RecyclerViewNoBugLinearLayoutManager(Context context) {
        super(context);
    }

    public RecyclerViewNoBugLinearLayoutManager(Context context, int orientation, boolean reverseLayout) {
        super( context, orientation, reverseLayout );
    }

    public RecyclerViewNoBugLinearLayoutManager(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super( context, attrs, defStyleAttr, defStyleRes );
    }

    @Override
    public void onLayoutChildren(RecyclerView.Recycler recycler, RecyclerView.State state) {
        try {
            //try catch一下
            super.onLayoutChildren( recycler, state );
        } catch (IndexOutOfBoundsException e) {
            e.printStackTrace();
        }

    }


    @Override
    public boolean supportsPredictiveItemAnimations() {
        return false;
    }

    @Override
    public int scrollVerticallyBy(int dy, RecyclerView.Recycler recycler, RecyclerView.State state) {
        try {
            return super.scrollVerticallyBy(dy, recycler, state);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
}
