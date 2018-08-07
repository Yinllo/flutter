package com.desjf.dsjr.adapter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.RelativeLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.desjf.dsjr.R;
import com.desjf.dsjr.model.ReChargeRecordModel;

import java.util.List;

/**
 * Created by Administrator on 2017/9/7 0007.
 */

public class ReChargeRecordAdapter extends BaseQuickAdapter<ReChargeRecordModel,BaseViewHolder> {
    private Context context;
    public ReChargeRecordAdapter(@Nullable List<ReChargeRecordModel> data,Context context) {
        super(R.layout.item_recharge_record, data);
        this.context = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, ReChargeRecordModel item) {
//       helper.getView(R.id.tv_item_recharge_title);
        RelativeLayout ll = helper.getView(R.id.layout);
        ll.setVisibility(View.VISIBLE);
//        LinearLayout tv = helper.getView(R.id.tv_none);
//        tv.setVisibility(View.GONE);
        helper.setText(R.id.tv_item_recharge_title,item.getRECHARGE_STATUS());
        helper.setText(R.id.tv_item_recharge_data,item.getRECHARGE_DATE());
        helper.setText(R.id.tv_item_recharge_deal_amount,item.getRECHARGE_AMOUNT_FORMAT());
//        helper.setText(R.id.tv_item_recharge_balance,item.getACTUAL_AMOUNT_FORMAT());

    }
}
