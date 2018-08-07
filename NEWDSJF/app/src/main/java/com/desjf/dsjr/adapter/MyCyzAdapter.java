package com.desjf.dsjr.adapter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.desjf.dsjr.R;
import com.desjf.dsjr.model.MyInvestModel;

import java.util.List;

/**
 * Created by Administrator on 2017/7/28 0028.
 */

public class MyCyzAdapter extends BaseQuickAdapter<MyInvestModel,BaseViewHolder> {
    private Context context;
    public MyCyzAdapter(@Nullable List<MyInvestModel> data,Context context) {
        super(R.layout.item_invest_cyz, data);
        this.context = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, MyInvestModel item) {
        if (!item.getTRANSFERCONTRACTID().equals("")){
            helper.setText(R.id.tv_cyz_title,item.getTRANSFERCONTRACTID());
        }else{
            helper.setText(R.id.tv_cyz_title,item.getPRODUCTSTITLE());
        }
//        helper.setText(R.id.tv_cyz_hkxq,item.getSTATUS());
        helper.setText(R.id.tv_cyz_tzje,String.valueOf(item.getTENDERAMOUNT()));
        helper.setText(R.id.tv_cyz_ysje,String.valueOf(item.getRECOVERAMOUNTTOTALYES()));
        helper.setText(R.id.tv_cyz_dsje,String.valueOf(item.getRECOVERAMOUNTTOTALWAIT()));
        TextView tv = helper.getView(R.id.tv_cyz_hkxq);
        helper.addOnClickListener(R.id.tv_cyz_hkxq);
        TextView find=helper.getView(R.id.find);
        helper.addOnClickListener(R.id.find);
    }
}
