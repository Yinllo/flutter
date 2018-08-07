package com.desjf.dsjr.adapter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.desjf.dsjr.R;
import com.desjf.dsjr.model.MyInvestModel;

import java.text.DecimalFormat;
import java.util.List;

/**
 * Created by Administrator on 2017/7/28 0028.
 */

public class HuikuanAdapter extends BaseQuickAdapter<MyInvestModel,BaseViewHolder> {
    private Context context;
    public HuikuanAdapter(@Nullable List<MyInvestModel> data,Context context) {
        super(R.layout.item_invest_cyz, data);
        this.context = context;
    }

    @Override
    protected void convert(BaseViewHolder helper,  MyInvestModel item) {
        helper.setText(R.id.tv_cyz_title,item.getPRODUCTSTITLE());
        helper.setText(R.id.tv_cyz_tzje,String.valueOf(item.getTENDERAMOUNT()));
        helper.setText(R.id.tv_cyz_ysje,String.valueOf(item.getRECOVERAMOUNTTOTALYES()));
        DecimalFormat df = new DecimalFormat("#.00");
        double a = Double.parseDouble(item.getRECOVERAMOUNTINTERESTYES());
        double b = Double.parseDouble(item.getRECOVERAMOUNTCOUPONYES());
        helper.setText(R.id.tv_cyz_dsje,String.valueOf(df.format(a+b)));
        helper.setText(R.id.tv_sy,"收益(元)");
        helper.addOnClickListener(R.id.tv_cyz_hkxq);
        TextView find=helper.getView(R.id.find);
        helper.addOnClickListener(R.id.find);
    }
}
