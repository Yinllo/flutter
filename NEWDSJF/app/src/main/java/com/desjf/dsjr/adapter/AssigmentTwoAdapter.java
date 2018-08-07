package com.desjf.dsjr.adapter;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.desjf.dsjr.R;
import com.desjf.dsjr.model.TransferInvestorsModel;

import java.util.List;

/**
 * Created by Administrator on 2017/8/14 0014.
 */

public class AssigmentTwoAdapter extends BaseQuickAdapter<TransferInvestorsModel,BaseViewHolder> {
    private Context context;
    public AssigmentTwoAdapter(@Nullable List<TransferInvestorsModel> data,Context context) {
        super(R.layout.item_assigment_two, data);
        this.context = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, TransferInvestorsModel item) {
        helper.setText(R.id.tv_wzdklsl,"购买金额"+item.getBUY_AMOUNT());
        helper.setText(R.id.tv_assigmengt_time,item.getBUY_DATE());
        helper.setText(R.id.tv_assigmengt_jine,item.getREAL_AMOUNT()+"元");
    }
}
