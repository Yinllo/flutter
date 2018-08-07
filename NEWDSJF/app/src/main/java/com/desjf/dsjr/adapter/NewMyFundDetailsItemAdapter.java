package com.desjf.dsjr.adapter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.desjf.dsjr.R;
import com.desjf.dsjr.model.JyRecordModel;

import java.util.List;


public class NewMyFundDetailsItemAdapter extends BaseQuickAdapter<JyRecordModel.YMVALBean,BaseViewHolder> {
    private Context context;
    public NewMyFundDetailsItemAdapter(@Nullable List<JyRecordModel.YMVALBean> data, Context context) {
        super(R.layout.new_item_fund_details_list, data);
        this.context =context;
    }

    @Override
    protected void convert(BaseViewHolder helper, JyRecordModel.YMVALBean item) {
        TextView tv_money  = helper.getView(R.id.tv_zjmx_money);
        if (item.getREVENUEEXPENDTYPE().equals("R")){
            //收入
            tv_money.setText("+"+item.getAMOUNT());
//            tv_money.setTextColor( context.getResources().getColor(R.color.main));
            tv_money.setTextColor( context.getResources().getColor(R.color.redbag_tishi));
        }else{
            //支出
            tv_money.setText("-"+item.getAMOUNT());
            tv_money.setTextColor( context.getResources().getColor(R.color.green_m));
        }

        String type=item.getREMARK();
        helper.setText(R.id.tv_zjmx_type,type);
        helper.setText(R.id.tv_zjmx_ye,item.getUSABLEAMOUNT());
        helper.setText(R.id.tv_zjmx_date,item.getINSDATE());
    }
}
