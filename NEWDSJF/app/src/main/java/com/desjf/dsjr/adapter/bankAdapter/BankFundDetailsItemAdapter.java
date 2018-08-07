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
 * 资金明细中的 全部
 */

public class BankFundDetailsItemAdapter extends BaseQuickAdapter<BankAccountFundDetailsModel.ReturnListBean,BaseViewHolder> {
    private Context context;
    public BankFundDetailsItemAdapter(@Nullable List<BankAccountFundDetailsModel.ReturnListBean> data, Context context) {
        super(R.layout.new_item_fund_details_list, data);
        this.context =context;
    }

    @Override
    protected void convert(BaseViewHolder helper, BankAccountFundDetailsModel.ReturnListBean item) {
        TextView tv_money  = helper.getView(R.id.tv_zjmx_money);
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

//        String type=item.getFUND_TYPE();
//        if(type.equals("059")){
//            helper.setText(R.id.tv_zjmx_type,"提现");
//        }else if(type.equals("014")){
//            helper.setText(R.id.tv_zjmx_type,"投资");
//        }else if(type.equals("015")){
//            helper.setText(R.id.tv_zjmx_type,"回款");
//        }else if(type.equals("016")){
//            helper.setText(R.id.tv_zjmx_type,"回款");
//        }else if(type.equals("001")){
//            helper.setText(R.id.tv_zjmx_type,"充值");
//        }else if(type.equals("039")&&item.getREVENUE_EXPEND_TYPE().equals("R")){
//            helper.setText(R.id.tv_zjmx_type,"邀请返现");
//        }else if(type.equals("002")&&item.getREVENUE_EXPEND_TYPE().equals("R")){
//            helper.setText(R.id.tv_zjmx_type,"提现");//提现撤回
//        }else if(type.equals("061")&&item.getREVENUE_EXPEND_TYPE().equals("R")) {
//            helper.setText(R.id.tv_zjmx_type, "提现");//提现撤回
//        }else if(type.equals("002")&&item.getREVENUE_EXPEND_TYPE().equals("E")){
//            helper.setText(R.id.tv_zjmx_type,"提现");//提现成功
//        }else if(type.equals("032")&&item.getREVENUE_EXPEND_TYPE().equals("E")){
//            helper.setText(R.id.tv_zjmx_type,"提现");//提现手续费
//        }else if(type.equals("054")&&item.getREVENUE_EXPEND_TYPE().equals("E")){
//            helper.setText(R.id.tv_zjmx_type,"提现");//提现申请
//        }else if(type.equals("060")&&item.getREVENUE_EXPEND_TYPE().equals("R")) {
//            helper.setText(R.id.tv_zjmx_type, "提现");//提现失败
//        } else{
//            helper.setText(R.id.tv_zjmx_type,"投资冻结");
//        }

        String type=item.getFUND_TYPE();
        if(type.equals("013")){
            helper.setText(R.id.tv_zjmx_type,"已撤标，解除投资冻结资金");
        }else {
            helper.setText(R.id.tv_zjmx_type, item.getREMARK());
        }

//        BigDecimal bigDecimal= BigDecimal.valueOf(item.getUSABLE_AMOUNT());
        helper.setText(R.id.tv_zjmx_ye,item.getUSABLE_AMOUNT()+"");
        String time= DataUtil.stampToDates(item.getINS_DATE()+"");
        helper.setText(R.id.tv_zjmx_date,time);
    }
}
