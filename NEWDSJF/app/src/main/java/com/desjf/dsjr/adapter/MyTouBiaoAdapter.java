package com.desjf.dsjr.adapter;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.desjf.dsjr.R;
import com.desjf.dsjr.model.MyInvestModel;

import java.util.List;

/**
 * Created by Administrator on 2017/7/28 0028.
 */

public class MyTouBiaoAdapter extends BaseQuickAdapter<MyInvestModel,BaseViewHolder> {
    private Context context;
    public MyTouBiaoAdapter(@Nullable List<MyInvestModel> data,Context context) {
        super(R.layout.item_myinvest_toubiao, data);
        this.context =context;
    }

    @Override
    protected void convert(BaseViewHolder helper, MyInvestModel item) {
        if (!item.getTRANSFERCONTRACTID().equals("")){
            helper.setText(R.id.tv_toubiao_title,item.getTRANSFERCONTRACTID());
        }else{
            helper.setText(R.id.tv_toubiao_title,item.getPRODUCTSTITLE());
        }
        helper.setText(R.id.tv_cyz_hkxq,item.getSTATUS());
        helper.setText(R.id.tv_toubiao_tzje,String.valueOf(item.getTENDERAMOUNT()));
        helper.setText(R.id.tv_toubiao_ysje,String.valueOf(item.getRECOVERAMOUNTINTEREST()));
        helper.setText(R.id.tv_toubiao_dsje,String.valueOf(item.getFINANCEINTERESTRATE())+"%");
        helper.setText(R.id.tv_toubiao_tzqx,String.valueOf(item.getFINANCEPERIODFORMAT())+"个月");
        helper.setText(R.id.tv_cyz_hkxq,item.getSTATUS());
    }
}
