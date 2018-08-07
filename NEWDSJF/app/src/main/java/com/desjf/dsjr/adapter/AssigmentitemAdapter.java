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

public class AssigmentitemAdapter extends BaseQuickAdapter<TransferInvestorsModel,BaseViewHolder> {
    private Context context;
    public AssigmentitemAdapter( @Nullable List<TransferInvestorsModel> data,Context context) {
        super(R.layout.item_assigment_list, data);
        this.context = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, TransferInvestorsModel item) {
        helper.setText(R.id.tv_assigmengt_qishu,"期数"+item.getRECOVER_PERIOD());
        helper.setText(R.id.tv_assigmengt_time,item.getRECOVER_DATE_FORMAT());
        helper.setText(R.id.tv_assigmengt_jine,"还款金额(元)"+item.getRECOVER_AMOUNT_FORMAT());
    }
}
