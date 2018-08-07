package com.desjf.dsjr.adapter;

import android.content.Context;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.desjf.dsjr.R;
import com.desjf.dsjr.model.bankModel.BankInvestRecordModel;

import java.util.List;

/**
 * Created by Administrator on 2017/8/10 0010.
 */


public class TouzjlAdapter extends BaseQuickAdapter<BankInvestRecordModel.InvestorListBean,BaseViewHolder> {
    private Context context;
    public TouzjlAdapter(@Nullable List<BankInvestRecordModel.InvestorListBean> data,Context context) {
        super(R.layout.item_touzijilu, data);
        this.context = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, BankInvestRecordModel.InvestorListBean item) {
        helper.setText(R.id.tv_touzijilu_phone,item.getUserId());
        helper.setText(R.id.tv_touzijilu_date,item.getTenderDate());
        helper.setText(R.id.tv_touzijilu_money,item.getTenderAmount()+"元");
    }
}
