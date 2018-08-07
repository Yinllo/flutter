package com.desjf.dsjr.adapter.bankAdapter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.desjf.dsjr.R;
import com.desjf.dsjr.model.bankModel.BankAccountFundDetailsModel;
import com.desjf.dsjr.utils.DataUtil;

import java.util.List;

/**
 * 资金明细中的 回款
 */

public class BankFundDetailsPaymentsAdapter extends BaseQuickAdapter<BankAccountFundDetailsModel.ReturnListBean,BaseViewHolder> {
    private Context context;
    public BankFundDetailsPaymentsAdapter(@Nullable List<BankAccountFundDetailsModel.ReturnListBean> data, Context context) {
        super(R.layout.new_item_fund_details_list, data);
        this.context =context;
    }

    @Override
    protected void convert(BaseViewHolder helper, BankAccountFundDetailsModel.ReturnListBean item) {
        TextView tv_money  = helper.getView(R.id.tv_zjmx_money);
            //支出
//            tv_money.setText("+"+item.getAMOUNT());
//            tv_money.setTextColor( context.getResources().getColor(R.color.redbag_tishi));

        if (item.getREVENUE_EXPEND_TYPE().equals("R")){
            //收入
            tv_money.setText("+"+item.getAMOUNT());
            //            tv_money.setTextColor( context.getResources().getColor(R.color.main));
            tv_money.setTextColor( context.getResources().getColor(R.color.redbag_tishi));
        }else{
            //支出
            tv_money.setText("-"+item.getAMOUNT());
            tv_money.setTextColor( context.getResources().getColor(R.color.green_m));
        }

        helper.setText(R.id.tv_zjmx_type,item.getREMARK());

//        BigDecimal bigDecimal= BigDecimal.valueOf(item.getUSABLE_AMOUNT());
        helper.setText(R.id.tv_zjmx_ye,item.getUSABLE_AMOUNT()+"");


        String time= DataUtil.stampToDates(item.getINS_DATE()+"");
        helper.setText(R.id.tv_zjmx_date,time);

    }
}
