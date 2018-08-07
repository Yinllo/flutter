package com.desjf.dsjr.adapter;

import com.desjf.dsjr.bean.NoticeBean;

/**
 * 父布局Item点击监听接口
 */

public interface ItemClickListener {
    /**
     * 展开子Item
     * @param bean
     */
    void onExpandChildren(NoticeBean bean);

    /**
     * 隐藏子Item
     * @param bean
     */
    void onHideChildren(NoticeBean bean);
}
