package com.desjf.dsjr.adapter;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.desjf.dsjr.R;
import com.desjf.dsjr.model.AutoRecordModel;

import java.util.List;

/**
 * Created by Administrator on 2017/7/27 0027.
 */

public class AutoTenderListAdapter extends BaseQuickAdapter<AutoRecordModel.AUTOSETLISTBean,BaseViewHolder> {
    private Context context;
    public AutoTenderListAdapter( @Nullable List<AutoRecordModel.AUTOSETLISTBean> data,Context context) {
        super(R.layout.item_auto_tender_list, data);
        this.context = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, AutoRecordModel.AUTOSETLISTBean item) {
        helper.setText(R.id.tv_auto_tender_message,item.getBORROWTITLE());
        helper.setText(R.id.tv_auto_tender_money,item.getVALIDACCOUNTTENDER()+item.getVALIDACCOUNTUNIT());
        helper.setText(R.id.tv_auto_tender_status,item.getVERIFYSTATUS());
        helper.setText(R.id.tv_auto_tender_time,item.getBORROWPERIOD()+item.getINTERESTRATETYPE());
        helper.setText(R.id.tv_auto_tender_lilv,"利率"+item.getBORROWRATE()+"%");
    }
}
