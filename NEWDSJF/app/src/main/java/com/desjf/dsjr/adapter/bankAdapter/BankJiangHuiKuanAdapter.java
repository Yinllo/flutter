package com.desjf.dsjr.adapter.bankAdapter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.desjf.dsjr.R;
import com.desjf.dsjr.model.bankModel.BankJiangHuiKuanModel;
import com.desjf.dsjr.utils.DataUtil;

import java.util.List;

/**
 * @Author YinL
 * @Date 2018/4/4 0004
 * @Describe  我要投资：将回款
 */

public class BankJiangHuiKuanAdapter extends BaseQuickAdapter<BankJiangHuiKuanModel.UserRecoverListBean,BaseViewHolder> {

    private Context context;
    public BankJiangHuiKuanAdapter(@Nullable List<BankJiangHuiKuanModel.UserRecoverListBean> data, Context context) {
        super(R.layout.bank_item_invest_cyz, data);
        this.context =context;
    }
    @Override
    protected void convert(BaseViewHolder helper, BankJiangHuiKuanModel.UserRecoverListBean item) {
        //标的名称
        helper.setText(R.id.tv_cyz_title, item.getPRODUCTS_TITLE());
        //时间
        String time=DataUtil.getWeek(item.getRECOVER_DATE());
        helper.setText(R.id.tv_date,item.getRECOVER_DATE().substring(2,item.getRECOVER_DATE().length())+""+time);
        //期数
        helper.setText(R.id.tv_time_limit, String.valueOf(item.getRECOVER_CNT() + "/" + item.getFINANCE_PERIOD()));
        //本金
        helper.setText(R.id.tv_capital, String.valueOf(item.getRECOVER_AMOUNT_CAPITAL()));
        //利息
        helper.setText(R.id.tv_interest, item.getRECOVER_AMOUNT_INTEREST());



        TextView find = helper.getView(R.id.find);
        helper.addOnClickListener(R.id.find);

    }
    }
