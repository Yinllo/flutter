package com.desjf.dsjr.adapter.bankAdapter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.desjf.dsjr.R;
import com.desjf.dsjr.model.bankModel.BankHuikuanModel;
import com.desjf.dsjr.utils.DataUtil;

import java.text.DecimalFormat;
import java.util.List;

/**
 * Created by Administrator on 2017/7/28 0028.
 */

public class BankHuikuanAdapter extends BaseQuickAdapter<BankHuikuanModel.UserRecoverListBean,BaseViewHolder> {
    private Context context;
    public BankHuikuanAdapter(@Nullable List<BankHuikuanModel.UserRecoverListBean> data, Context context) {
        super(R.layout.bank_item_invest_cyz, data);
        this.context = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, BankHuikuanModel.UserRecoverListBean item) {
        //标的名称
        helper.setText(R.id.tv_cyz_title,item.getPRODUCTS_TITLE());
        //时间
        String time=DataUtil.getWeek(item.getRECOVER_DATE_YES());
        helper.setText(R.id.tv_date,item.getRECOVER_DATE_YES().substring(2,item.getRECOVER_DATE_YES().length())+""+time);
        //期数
        helper.setText(R.id.tv_time_limit,String.valueOf(item.getRECOVER_CNT()+"/"+item.getFINANCE_PERIOD()));
        //本金
        helper.setText(R.id.tv_capital,String.valueOf(item.getRECOVER_AMOUNT_CAPITAL_YES()));
        //利息
        helper.setText(R.id.tv_interest,String.valueOf(item.getRECOVER_AMOUNT_INTEREST_YES()));


        TextView find=helper.getView(R.id.find);
        helper.addOnClickListener(R.id.find);


        DecimalFormat df = new DecimalFormat("#.00");
        //        double a = Double.parseDouble(item.getRECOVERAMOUNTINTERESTYES());
        //        double b = Double.parseDouble(item.getRECOVERAMOUNTCOUPONYES());

    }
}
