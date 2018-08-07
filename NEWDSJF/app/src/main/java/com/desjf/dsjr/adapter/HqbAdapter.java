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
 * Created by Administrator on 2017/7/20 0020.
 */

public class HqbAdapter extends BaseQuickAdapter<String,BaseViewHolder> {
    private Context context;
    public HqbAdapter(@Nullable List<String> data, Context context) {
        super(R.layout.item_account_hqb, data);
        this.context = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        helper.setText(R.id.tv_hqb_title,item);
    }
}
