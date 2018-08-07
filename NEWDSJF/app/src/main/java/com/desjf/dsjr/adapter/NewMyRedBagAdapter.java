package com.desjf.dsjr.adapter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.desjf.dsjr.R;
import com.desjf.dsjr.model.AllCouponAndInterestModle;
import com.desjf.dsjr.widget.ClickListener;
import com.desjf.dsjr.widget.ClickMore;

import java.util.List;

/**
 * Created by Administrator on 2017/7/18 0018.
 */

public class NewMyRedBagAdapter extends BaseQuickAdapter<AllCouponAndInterestModle,BaseViewHolder> {
    private Context context;
    private ClickListener mClickListener;
    private ClickMore clickMore;

    private String type="";//类型

    public NewMyRedBagAdapter(@Nullable List<AllCouponAndInterestModle> data, Context context, ClickListener mClickListener, ClickMore clickMore) {
        super(R.layout.new_item_my_redbag, data);
        this.context = context;
        this.mClickListener = mClickListener;
        this.clickMore = clickMore;
    }

    @Override
    protected void convert(final BaseViewHolder helper, final AllCouponAndInterestModle item) {

        type = item.getTYPE();

        RelativeLayout rl_bg = helper.getView(R.id.rl_redbag_background);
        //类型  红包or加息券
        TextView tv_type = helper.getView(R.id.tv_type);
        //投资金额
        TextView tv_money = helper.getView(R.id.tv_money);
        //红包来源
        TextView tv_redbag_from = helper.getView(R.id.tv_redbag_from);
        //有效期
        TextView tv_time = helper.getView(R.id.tv_redbag_time);
        //标的期限
        TextView tv_data = helper.getView(R.id.tv_redbag_data);
        //  ￥符号
        TextView tv_redbag = helper.getView(R.id.tv_redbag);
        //红包金额
        TextView tv_redbag_money = helper.getView(R.id.tv_redbag_money);
        //    %符号
        TextView tv_i = helper.getView(R.id.tv_i);
        //加息券
        TextView tv_i_money = helper.getView(R.id.tv_i_money);

        //点击使用按钮
        TextView tv_redbag_button = helper.getView(R.id.tv_redbag_button);

        //如果是红包
        if (type.equals("0")) {

            //隐藏加息券信息
            tv_i.setVisibility(View.GONE);
            tv_i_money.setVisibility(View.GONE);
            //显示红包信息
            tv_redbag.setVisibility(View.VISIBLE);
            tv_redbag_money.setVisibility(View.VISIBLE);
            tv_type.setText("红包");
            tv_money.setText("投资金额：" + item.getMINIMUM_TENDER_AMOUNT());
            if (item.getPERIOD().equals("")) {
                tv_data.setText("标的期限：≥0个月");
            } else {
                tv_data.setText("标的期限：" + item.getPERIOD());
            }
            tv_redbag_from.setText("奖励来自：" + item.getFROM());
            tv_time.setText("有效期至：" + item.getEND_DATE());
            tv_redbag_money.setText(item.getNUMBER());
            tv_redbag_button.setText("点击使用");
        } else {

            //隐藏红包信息
            tv_redbag.setVisibility(View.GONE);
            tv_redbag_money.setVisibility(View.GONE);
            //显示加息券信息
            tv_i.setVisibility(View.VISIBLE);
            tv_i_money.setVisibility(View.VISIBLE);
            rl_bg.setBackgroundResource(R.mipmap.coupon_wei);
            tv_type.setText("加息券");
            tv_money.setText("投资金额：" + item.getMINIMUM_TENDER_AMOUNT());
            if (item.getPERIOD().equals("")) {
                tv_data.setText("标的期限：≥0个月");
            } else {
                tv_data.setText("标的期限：" + item.getPERIOD());
            }
            tv_redbag_from.setText("奖励来自：" + item.getFROM());
            tv_time.setText("有效期至：" + item.getEND_DATE());
            tv_i_money.setText(item.getNUMBER());
            tv_redbag_button.setText("点击使用");


        }

        //未使用
//       if(item.getUSE_WAY_FLG().equals("1"))
//       {
//           tv_type.setText("红包");
//           tv_money.setText("投资金额："+item.getMINIMUM_TENDER_AMOUNT());
//           tv_data.setText("标的期限："+item.getMINIMUN_TENDER_AMOUNT_UNIT());
//           tv_redbag_from.setText("奖励来自："+item.getCOUPON_ORIGIN_AVENUE());
//           tv_time.setText("有效期至："+item.getEND_DATE());
//           tv_redbag_money.setText(item.getACQUISITION_AMOUNT());
//           tv_redbag_button.setText("已使用");
//
//
//       }
        //已使用
//       else if(item.getUSE_WAY_FLG().equals("2"))
//       {
//
//           rl_bg.setBackgroundResource(R.mipmap.redbag);
//
//           tv_type.setText("红包");
//           tv_money.setText("投资金额："+item.getMINIMUM_TENDER_AMOUNT());
//           tv_data.setText("标的期限："+item.getMINIMUN_TENDER_AMOUNT_UNIT());
//           tv_redbag_from.setText("奖励来自："+item.getCOUPON_ORIGIN_AVENUE());
//           tv_time.setText("有效期至："+item.getEND_DATE());
//           tv_redbag_money.setText(item.getACQUISITION_AMOUNT());
//
//
////            tv_time.setText("使用时间"+item.getUSED_DATE());
//
//       }
//       else { //已过期
//
//           rl_bg.setBackgroundResource(R.mipmap.redbag);
//
//           tv_type.setText("红包");
//           tv_money.setText("投资金额："+item.getMINIMUM_TENDER_AMOUNT());
//           tv_data.setText("标的期限："+item.getMINIMUN_TENDER_AMOUNT_UNIT());
//           tv_redbag_from.setText("奖励来自："+item.getCOUPON_ORIGIN_AVENUE());
//           tv_time.setText("有效期至："+item.getEND_DATE());
//           tv_redbag_money.setText(item.getACQUISITION_AMOUNT());
//           tv_redbag_button.setText("已过期");
//
//       }


    }
    }

