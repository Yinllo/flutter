package com.desjf.dsjr.utils;

import android.view.View;

import com.desjf.dsjr.bean.NoticeBean;

/**
 * @Author YinL
 * @Date 2018/4/28 0028
 * @Describe  公告 下拉TextView
 */

public class ItemData {
    public NoticeBean noticeBean;//数据类
    public View view;//展开的item,一定要将view一起封装起来，否则会有问题的。

    public NoticeBean getNoticeBean() {
        return noticeBean;
    }

    public void setNoticeBean(NoticeBean noticeBean) {
        this.noticeBean = noticeBean;
    }

    public View getView() {
        return view;
    }

    public void setView(View view) {
        this.view = view;
    }
}
