package com.desjf.dsjr.adapter.bankAdapter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.RelativeLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.desjf.dsjr.R;
import com.desjf.dsjr.model.bankModel.RechargeRecordModel;
import com.desjf.dsjr.utils.DataUtil;

import java.util.List;

/**
 * Created by Administrator on 2017/9/7 0007.
 */

public class BankReChargeRecordAdapter extends BaseQuickAdapter<RechargeRecordModel.GetRechargeListBean,BaseViewHolder> {
    private Context context;
    public BankReChargeRecordAdapter(@Nullable List<RechargeRecordModel.GetRechargeListBean> data, Context context) {
        super(R.layout.item_recharge_record, data);
        this.context = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, RechargeRecordModel.GetRechargeListBean item) {
//       helper.getView(R.id.tv_item_recharge_title);
        RelativeLayout ll = helper.getView(R.id.layout);
        ll.setVisibility(View.VISIBLE);
//        LinearLayout tv = helper.getView(R.id.tv_none);
//        tv.setVisibility(View.GONE);
        if(item.getStatus().equals("1")){
            helper.setText(R.id.tv_item_recharge_title,"充值成功");
        }else{
            helper.setText(R.id.tv_item_recharge_title,"充值失败");
        }


        String s= DataUtil.stampToDates(String.valueOf(item.getUpdDate()));
        helper.setText(R.id.tv_item_recharge_data,s);
        helper.setText(R.id.tv_item_recharge_deal_amount,item.getTransAmt()+"");
//        helper.setText(R.id.tv_item_recharge_balance,item.getACTUAL_AMOUNT_FORMAT());

    }
}
