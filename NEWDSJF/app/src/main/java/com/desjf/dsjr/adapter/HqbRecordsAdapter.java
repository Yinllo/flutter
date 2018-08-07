package com.desjf.dsjr.adapter;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.desjf.dsjr.R;

import java.util.List;

/**
 * Created by Administrator on 2017/7/20 0020.
 * 活期宝的交易记录
 */

public class HqbRecordsAdapter extends BaseQuickAdapter<String,BaseViewHolder>{
    private Context context;
    public HqbRecordsAdapter( @Nullable List<String> data,Context context) {
        super(R.layout.item_hqb_records, data);
        this.context = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
            helper.setText(R.id.tv_money_hqb_records,item);
    }
}
