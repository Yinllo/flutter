package com.desjf.dsjr.util;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

/**
 * Created by YL on 2017/11/9 0009.       自定义RecyclerView  用于判断当前位置  如果位置超过一页则显示回到顶部按钮
 */

public class MyRecyclerViewScrollListener extends RecyclerView.OnScrollListener {
    Button button;
    public MyRecyclerViewScrollListener(Button button){
        this.button=button;
    }

    @Override
    public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
        super.onScrollStateChanged(recyclerView, newState);
        LinearLayoutManager manager = (LinearLayoutManager) recyclerView.getLayoutManager();
        int firstVisibleItemPosition = manager.findFirstVisibleItemPosition();
        // 当不滚动时
        if (newState == RecyclerView.SCROLL_STATE_IDLE) {
            // 判断是否滚动超过一屏
            if (firstVisibleItemPosition == 0) {
                button.setVisibility(View.INVISIBLE);
            } else {
                button.setVisibility(View.VISIBLE);
            }

        } else if (newState == RecyclerView.SCROLL_STATE_DRAGGING) {//拖动中
            button.setVisibility(View.INVISIBLE);
        }
    }
}
