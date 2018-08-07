package com.desjf.dsjr.adapter;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.desjf.dsjr.R;
import com.desjf.dsjr.model.PaymentDetailsModel;

import java.util.List;

/**
 * Created by Administrator on 2017/8/14 0014.
 */

public class PaymentInfoAdapter extends BaseQuickAdapter<PaymentDetailsModel,BaseViewHolder> {
    private Context context;
    private int i;

    public PaymentInfoAdapter( @Nullable List<PaymentDetailsModel> data,Context context,int i) {
        super(R.layout.item_payment_list, data);
        this.context = context;
        this.i = i;
    }


    @Override
    protected void convert(BaseViewHolder helper, PaymentDetailsModel item) {
        LinearLayout ll_top = helper.getView(R.id.ll_zyc);
        LinearLayout ll_under = helper.getView(R.id.ll_gc);
        ImageView iv = helper.getView(R.id.iv_wsyzt);
        TextView tv_yqts = helper.getView(R.id.tv_yqts);
        TextView tv_yqlx = helper.getView(R.id.tv_yqlx);
        TextView tv_yqfx = helper.getView(R.id.tv_yqfx);
        tv_yqts.setText(item.getOVERDUE_DAY());
        tv_yqlx.setText(item.getOVERDUE_INTEREST());
        tv_yqfx.setText(item.getOVERDUE_FORFEIT());
        helper.setText(R.id.tv_payment_date,item.getRECOVER_DATE());
        helper.setText(R.id.tv_payment_money,item.getRECOVER_AMOUNT_CAPITAL());
        helper.setText(R.id.tv_payment_lixi,item.getRECOVER_AMOUNT_INTEREST());
//        if (i==0){
//            iv.setVisibility(View.GONE);
//        }else{
//
//            iv.setVisibility(View.VISIBLE);
//            if (item.getOVERDUE_FLG().equals("1")){
//                iv.setImageResource(R.mipmap.falsetttt);
//            }
//        }

        if (item.getOVERDUE_FLG().equals("0")){
            if (item.getSUB_STATUS().equals("0")){
                iv.setVisibility(View.GONE);
                ll_top.setVisibility(View.GONE);
                ll_under.setVisibility(View.GONE);
            }else{
                iv.setVisibility(View.VISIBLE);
                iv.setImageResource(R.mipmap.truettt);
                ll_top.setVisibility(View.GONE);
                ll_under.setVisibility(View.GONE);
            }

        }else{
            if (item.getSUB_STATUS().equals("1")){
                iv.setVisibility(View.VISIBLE);
                iv.setImageResource(R.mipmap.truettt);
                ll_top.setVisibility(View.VISIBLE);
                ll_under.setVisibility(View.VISIBLE);
            }else{
                iv.setVisibility(View.VISIBLE);
                iv.setImageResource(R.mipmap.falsetttt);
                ll_top.setVisibility(View.VISIBLE);
                ll_under.setVisibility(View.VISIBLE);
            }

        }

    }
}
