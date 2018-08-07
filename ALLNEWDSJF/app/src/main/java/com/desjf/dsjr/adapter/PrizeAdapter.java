package com.desjf.dsjr.adapter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.desjf.dsjr.R;
import com.desjf.dsjr.bean.AllCouponAndInterestBean;

import java.util.List;

/**
 * @Author YinL
 * @Date 2018/4/27 0027
 * @Describe 红包加息  ： 红包和加息券 适配器
 */

public class PrizeAdapter extends BaseQuickAdapter<AllCouponAndInterestBean,BaseViewHolder>{

    private String type="";//类型

    public PrizeAdapter(@Nullable List<AllCouponAndInterestBean> data, Context context){
        super(R.layout.item_my_redbag, data);
    }


    @Override
    protected void convert(BaseViewHolder helper, AllCouponAndInterestBean item) {
        type = item.getTYPE();

        ImageView rl_bg = helper.getView(R.id.iv_red);
        //类型  红包or加息券
        TextView tv_type = helper.getView(R.id.tv_type);
        //投资金额
        TextView tv_money = helper.getView(R.id.tv_money);
        //红包来源
        TextView tv_redbag_from = helper.getView(R.id.tv_from);
        //有效期
        TextView tv_time = helper.getView(R.id.tv_time);
        //标的期限
        TextView tv_data = helper.getView(R.id.tv_data);
        //  元
        TextView tv_redbag = helper.getView(R.id.tv_red);
        //红包金额
        TextView tv_redbag_money = helper.getView(R.id.tv_red_money);
        //    %符号
        TextView tv_i = helper.getView(R.id.tv_i);
        //加息券
        TextView tv_i_money = helper.getView(R.id.tv_i_money);

        //如果是红包
        if (type.equals("0")) {

            //隐藏加息券信息
            tv_i.setVisibility(View.GONE);
            tv_i_money.setVisibility(View.GONE);
            //显示红包信息
            tv_redbag.setVisibility(View.VISIBLE);
            tv_redbag_money.setVisibility(View.VISIBLE);
            tv_type.setText("红包");
            tv_money.setText(item.getMINIMUM_TENDER_AMOUNT());
            if (item.getPERIOD().equals("")) {
                tv_data.setText("≥0个月");
            } else {
                tv_data.setText(item.getPERIOD());
            }
            tv_redbag_from.setText(item.getFROM());
            tv_time.setText(item.getEND_DATE());
            tv_redbag_money.setText(item.getNUMBER());
        } else {
            //隐藏红包信息
            tv_redbag.setVisibility(View.GONE);
            tv_redbag_money.setVisibility(View.GONE);
            //显示加息券信息
            tv_i.setVisibility(View.VISIBLE);
            tv_i_money.setVisibility(View.VISIBLE);
            tv_type.setText("加息券");
            tv_money.setText(item.getMINIMUM_TENDER_AMOUNT());
            if (item.getPERIOD().equals("")) {
                tv_data.setText("≥0个月");
            } else {
                tv_data.setText(item.getPERIOD());
            }
            tv_redbag_from.setText(item.getFROM());
            tv_time.setText(item.getEND_DATE());
            tv_i_money.setText(item.getNUMBER());

        }
    }
}
