package com.desjf.dsjr.adapter;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.desjf.dsjr.R;
import com.desjf.dsjr.bean.ShouyeListBean;

import java.util.List;

/**
 * Created by Administrator on 2017/7/17 0017.
 * 活期适配器
 */

public class CurrentAdapter extends BaseQuickAdapter<ShouyeListBean,BaseViewHolder> {
    private Context context;
    public CurrentAdapter(@Nullable List<ShouyeListBean> data,Context context) {
        super(R.layout.item_shouye_list, data);
        this.context = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, ShouyeListBean item) {
        helper.setText(R.id.tv_shouye_list_title,item.getName());

    }
}
