package com.desjf.dsjr.adapter.bankAdapter;

import android.content.Context;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.desjf.dsjr.R;
import com.desjf.dsjr.model.bankModel.BankInvestmentModel;
import com.desjf.dsjr.utils.DataUtil;

import java.util.List;

/**
 * @Author YinL
 * @Date 2018/4/4 0004
 * @Describe
 */

public class BankInvestmentAdapter extends BaseQuickAdapter<BankInvestmentModel.UserTenderListBean,BaseViewHolder> {
    private Context context;
    public BankInvestmentAdapter(@Nullable List<BankInvestmentModel.UserTenderListBean> data, Context context) {
        super(R.layout.bank_item_invest_cyz, data);
        this.context = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, BankInvestmentModel.UserTenderListBean item) {
        //标的名称
        helper.setText(R.id.tv_cyz_title,item.getPRODUCTS_TITLE());
        //时间
        String t= DataUtil.ToDate(item.getINS_DATE()+"");
        String time=DataUtil.getWeek(t);
        helper.setText(R.id.tv_date,t+""+time);
        //利率
        helper.setText(R.id.tv_time_limit,String.valueOf(item.getFINANCE_INTEREST_RATE()+"%"));
        //期限
        helper.setText(R.id.tv_capital,String.valueOf(item.getFINANCE_PERIOD()+"个月"));
        //投资本金
        helper.setText(R.id.tv_interest,String.valueOf(item.getTENDER_AMOUNT()));

    }

}
