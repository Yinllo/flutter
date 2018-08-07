package com.desjf.dsjr.adapter;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.desjf.dsjr.R;
import com.desjf.dsjr.model.JyRecordModel;

import java.util.List;

/**
 * Created by Administrator on 2017/7/20 0020.
 */

public class MyFundDetailsItemAdapter extends BaseQuickAdapter<JyRecordModel.YMVALBean,BaseViewHolder> {
    private Context context;
    public MyFundDetailsItemAdapter(@Nullable List<JyRecordModel.YMVALBean> data,Context context) {
        super(R.layout.item_fund_details_list, data);
        this.context =context;
    }

    @Override
    protected void convert(BaseViewHolder helper, JyRecordModel.YMVALBean item) {
        TextView tv_money  = helper.getView(R.id.tv_zjmx_money);
        if (item.getREVENUEEXPENDTYPE().equals("R")){
            //收入
            tv_money.setText("+"+item.getAMOUNT()+"元");
            tv_money.setTextColor( context.getResources().getColor(R.color.main));
        }else{
            //支出
            tv_money.setText("-"+item.getAMOUNT()+"元");
            tv_money.setTextColor( context.getResources().getColor(R.color.font_blue));
        }
        helper.setText(R.id.tv_zjmx_type,item.getREMARK());
        helper.setText(R.id.tv_zjmx_ye,"余额"+item.getUSABLEAMOUNT()+"元");
        helper.setText(R.id.tv_zjmx_date,item.getINSDATE());
    }
}
