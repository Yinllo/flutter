package com.desjf.dsjr.adapter.bankAdapter;

import android.content.Context;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.desjf.dsjr.R;
import com.desjf.dsjr.model.bankModel.BankFreezeModel;
import com.desjf.dsjr.utils.DataUtil;

import java.util.List;

/**
 * @Author YinL
 * @Date 2018/4/4 0004
 * @Describe  我要投资：冻结
 */

public class BankFreezeAdapter extends BaseQuickAdapter<BankFreezeModel.UserTenderListBean,BaseViewHolder> {

    private Context context;
    public BankFreezeAdapter(@Nullable List<BankFreezeModel.UserTenderListBean> data, Context context) {
        super(R.layout.bank_item_freeze, data);
        this.context =context;
    }
    @Override
    protected void convert(BaseViewHolder helper, BankFreezeModel.UserTenderListBean item) {

        //标的名称
        helper.setText(R.id.tv_cyz_title,item.getPRODUCTS_TITLE());
        //时间
        String t= DataUtil.ToDate(item.getINS_DATE()+"");
        String time=DataUtil.getWeek(t);
        helper.setText(R.id.tv_date,t+""+time);
        //冻结本金
        helper.setText(R.id.tv_capital,String.valueOf(item.getTENDER_AMOUNT()));

    }

}
