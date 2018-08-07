package com.desjf.dsjr.adapter;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.desjf.dsjr.R;
import com.desjf.dsjr.bean.WodeRvBean;

import java.util.List;

/**
 * Created by Administrator on 2017/7/25 0025.
 */

public class WodeAdapter extends BaseQuickAdapter<WodeRvBean,BaseViewHolder> {
    private Context context;
    public WodeAdapter(@Nullable List<WodeRvBean> data,Context context) {
        super(R.layout.item_wode_rv, data);
        this.context = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, WodeRvBean item) {
        ImageView iv = helper.getView(R.id.iv_wode_rv_icon);
        Glide.with(context).load(item.getIcon()).into(iv);
        helper.setText(R.id.tv_wode_rv_title,item.getTitle());
    }
}
