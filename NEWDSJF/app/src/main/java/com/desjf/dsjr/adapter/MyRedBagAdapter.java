package com.desjf.dsjr.adapter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.desjf.dsjr.R;
import com.desjf.dsjr.model.AllCouponModel;
import com.desjf.dsjr.widget.ClickListener;
import com.desjf.dsjr.widget.ClickMore;

import java.util.List;

/**
 * Created by Administrator on 2017/7/18 0018.
 */

public class MyRedBagAdapter extends BaseQuickAdapter<AllCouponModel,BaseViewHolder> {
    private Context context;
    private ClickListener mClickListener;
    private ClickMore clickMore;
    private boolean isopen=true;
    public MyRedBagAdapter(@Nullable List<AllCouponModel> data, Context context, ClickListener mClickListener, ClickMore clickMore) {
        super(R.layout.new_item_my_redbag, data);
        this.context = context;
        this.mClickListener = mClickListener;
        this.clickMore = clickMore;
    }

    @Override
    protected void convert(final BaseViewHolder helper, final AllCouponModel item) {

        RelativeLayout rl_back = helper.getView(R.id.rl_redbag_background);
        //下拉出现的文字描述
        final RelativeLayout rl_more = helper.getView(R.id.rl_getmore);
        ImageView iv_status = helper.getView(R.id.iv_redbag_status);
        //下拉按钮
        final ImageView iv_more = helper.getView(R.id.iv_getmore);
        TextView tv_money = helper.getView(R.id.tv_redbag_money);
        TextView tv_time = helper.getView(R.id.tv_redbag_time);
        //文字描述TextView
        TextView tv_laiyuan = helper.getView(R.id.tv_redbag_laiyuan);


        TextView tv_less = helper.getView(R.id.tv_zdtzje);
         TextView tv_ysed_way =helper.getView(R.id.red_used_way);
        Log.e("ddddd",item.getTO_CASH_FLG());
        tv_less.setText(item.getRULE());
        tv_money.setText(item.getACQUISITION_AMOUNT());
        //初始时显示的文字内容
        tv_laiyuan.setText(item.getCOUPON_ORIGIN_AVENUE());
        tv_ysed_way.setText(item.getYONG_YU());
        //未使用
       if(item.getUSE_WAY_FLG().equals("1"))
       {
           rl_more.setBackgroundColor(context.getResources().getColor(R.color.redbag));
           tv_money.setTextColor(context.getResources().getColor(R.color.red));
           rl_back.setBackgroundResource(R.mipmap.my_redbag);
           tv_time.setText("有效期至"+item.getEND_DATE());
                     if (item.getTO_CASH_FLG().equals("不可以")){
                iv_status.setClickable(false);
                iv_status.setVisibility(View.GONE);
            }else{
                //可兑换
                iv_status.setClickable(true);
                iv_status.setVisibility(View.VISIBLE);
                iv_status.setImageResource(R.mipmap.duixian);
            }
       }
       //已使用
       else if(item.getUSE_WAY_FLG().equals("2"))
       {
           rl_more.setBackgroundColor(context.getResources().getColor(R.color.used));
           tv_ysed_way.setVisibility(View.VISIBLE);
           iv_status.setVisibility(View.VISIBLE);
           iv_status.setImageResource(R.mipmap.have_used);
           tv_money.setTextColor(context.getResources().getColor(R.color.font_gray));
            rl_back.setBackgroundResource(R.mipmap.my_redbag_used);
            tv_time.setText("使用时间"+item.getUSED_DATE());
         // rl_more.setBackgroundColor(context.getResources().getColor(R.color.used));
       }
       else {
           rl_more.setBackgroundColor(context.getResources().getColor(R.color.used));
           tv_money.setTextColor(context.getResources().getColor(R.color.font_gray));
           rl_back.setBackgroundResource(R.mipmap.my_redbag_used);
           tv_time.setText("有效期至"+item.getEND_DATE());
           iv_status.setImageResource(R.mipmap.have_out_of_time);
       }
        iv_status.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mClickListener.onClick(item);
            }
        });

        //下拉按钮点击事件（按钮区域过小，因此容易出现点击失效的情况）
        iv_more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isopen){
                    iv_more.setImageResource(R.mipmap.daoli);
                    rl_more.setVisibility(View.VISIBLE);
                    isopen=!isopen;
                }else{
                    iv_more.setImageResource(R.mipmap.moremessage);
                    rl_more.setVisibility(View.GONE);
                    isopen=!isopen;
                }

            }
        });

        //2017.11.8 新增TextView的监听事件，点击文字也能下拉
        tv_laiyuan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isopen){
                    iv_more.setImageResource(R.mipmap.daoli);
                    rl_more.setVisibility(View.VISIBLE);
                    isopen=!isopen;
                }else{
                    iv_more.setImageResource(R.mipmap.moremessage);
                    rl_more.setVisibility(View.GONE);
                    isopen=!isopen;
                }

            }
        });

    }
}
