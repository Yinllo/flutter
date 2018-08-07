package com.desjf.dsjr.adapter;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.desjf.dsjr.R;

import java.util.List;

/**
 * Created by Administrator on 2017/7/28 0028.
 */

public class MyInesterAdapter extends BaseQuickAdapter<Object,BaseViewHolder> {
    private Context context;
    public MyInesterAdapter( @Nullable List<Object> data, Context context) {
        super(R.layout.item_my_redbag, data);
        this.context = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, Object item) {

    }
}
