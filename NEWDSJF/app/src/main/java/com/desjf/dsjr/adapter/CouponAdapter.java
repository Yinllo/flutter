package com.desjf.dsjr.adapter;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.desjf.dsjr.R;
import com.desjf.dsjr.model.AllInterestModel;
import com.desjf.dsjr.widget.ClickListener;

import java.util.List;

/**
 * Created by Administrator on 2017/8/11 0011.
 */

public class CouponAdapter extends BaseQuickAdapter<AllInterestModel,BaseViewHolder> {
    private Context context;
    private ClickListener clickListener;
    private boolean isopen=true;
    public CouponAdapter( @Nullable List<AllInterestModel> data,Context context,ClickListener clickListener) {
        super(R.layout.item_my_redbag, data);
        this.context = context;
        this.clickListener = clickListener;
    }

    @Override
    protected void convert(BaseViewHolder helper, final AllInterestModel item) {
        RelativeLayout rl_back = helper.getView(R.id.rl_redbag_background);
        ImageView iv_status = helper.getView(R.id.iv_redbag_status);
        TextView tv_lixi = helper.getView(R.id.tv_redbag_money);
        TextView tv_time = helper.getView(R.id.tv_redbag_time);
        TextView tv_laiyuan = helper.getView(R.id.tv_redbag_laiyuan);
        final ImageView iv_more = helper.getView(R.id.iv_getmore);
        TextView tv_less = helper.getView(R.id.tv_zdtzje);
        final RelativeLayout rl_more = helper.getView(R.id.rl_getmore);

        tv_less.setText(item.getRULE());
        tv_lixi.setText(item.getRATECOUPONPOSITION());
        tv_laiyuan.setText(item.getCOUPONORIGINAVENUE());
//
//        if (item.getUSEDFLG().equals("0")){
//            //未使用
//            tv_time.setText("有效期至"+item.getEFFECTIVEDATE());
//            rl_back.setBackgroundResource(R.mipmap.my_coupon_image);
////            if (item.getTRANSFERABLEFLG().equals("0")){
////                //不可转让
////                iv_status.setVisibility(View.GONE);
////                iv_status.setClickable(false);
////            }else{
////                //可转让
////                iv_status.setVisibility(View.VISIBLE);
////                iv_status.setImageResource(R.mipmap.zengsong);
////            }
//        }else{
//            //使用
//            tv_time.setText("使用时间"+item.getENDDATE());
//            rl_back.setBackgroundResource(R.mipmap.my_redbag_used);
//            if (item.getSENDOUTFLG().equals("1")){
//                //已送出
//                iv_status.setVisibility(View.VISIBLE);
//                iv_status.setImageResource(R.mipmap.complimentary);
//            }else{
//                iv_status.setVisibility(View.GONE);
//                iv_status.setClickable(false);
//            }
//            if (item.getOVERDUEFLG().equals("1")){
//                //已过期
//                iv_status.setVisibility(View.VISIBLE);
//                iv_status.setImageResource(R.mipmap.out_of_time);
//            }else{
//                iv_status.setVisibility(View.GONE);
//                iv_status.setClickable(false);
//            }
//            if (item.getUSEDFLG().equals("1")){
//                //已使用
//                iv_status.setVisibility(View.VISIBLE);
//                iv_status.setImageResource(R.mipmap.used);
//            }else{
//                iv_status.setVisibility(View.GONE);
//                iv_status.setClickable(false);
//            }
//        }
     //先判断是否赠送 在判断是否过期 在判断是否使用
        //加息劵未赠送
//        if(item.getSENDOUTFLG().equals("0"))
//        {
//            rl_more.setBackgroundColor(context.getResources().getColor(R.color.coupon));
//            tv_lixi.setTextColor(context.getResources().getColor(R.color.red));
//            tv_time.setText("有效期至"+item.getENDDATE());
//            rl_back.setBackgroundResource(R.mipmap.my_coupon_image);
//            if (item.getTRANSFERABLEFLG().equals("0")){
//                //不可转让
//                iv_status.setVisibility(View.GONE);
//                iv_status.setClickable(false);
//            }else{
//                //可转让
//                iv_status.setVisibility(View.VISIBLE);
//                iv_status.setImageResource(R.mipmap.zengsong);
//            }
//
//            //加息劵过期
//            if(item.getOVERDUEFLG().equals("1"))
//            {
//                iv_status.setImageResource(R.mipmap.out_of_time);
//            }
//            iv_status.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    clickListener.onClick(item);
//                }
//            });
//        }
//        //加息劵已经赠送 在已经赠送里判断是否过期
//        else {
//            rl_more.setBackgroundColor(context.getResources().getColor(R.color.used));
//            tv_lixi.setTextColor(context.getResources().getColor(R.color.font_gray));
//            iv_status.setImageResource(R.mipmap.given_away);
//            tv_time.setText("使用时间"+item.getENDDATE());
//            rl_back.setBackgroundResource(R.mipmap.my_redbag_used);
//            //加息劵过期
//            if(item.getOVERDUEFLG().equals("1"))
//            {
//                iv_status.setImageResource(R.mipmap.out_of_time);
//            }
//            //加息劵未过期
//            else {
//              if(item.getUSEDFLG().equals("1"))
//              {
//                  iv_status.setImageResource(R.mipmap.used);
//              }
//              else {
//
//              }
//            }
//
    //  }
        //根据USE_WAY_FLG这个字段判断加息劵的使用情况
        //加息劵未使用
        if(item.getUSE_WAY_FLG().equals("1"))
        {
            rl_more.setBackgroundColor(context.getResources().getColor(R.color.coupon));
            tv_lixi.setTextColor(context.getResources().getColor(R.color.red));
            tv_time.setText("有效期至"+item.getENDDATE());
            rl_back.setBackgroundResource(R.mipmap.my_coupon_image);
            if (item.getTRANSFERABLEFLG().equals("0")){
                //不可转让
                iv_status.setVisibility(View.GONE);
                iv_status.setClickable(false);
            }else{
                //可转让
                iv_status.setVisibility(View.VISIBLE);
                iv_status.setImageResource(R.mipmap.zengsong);
                iv_status.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    clickListener.onClick(item);
                }
            });
            }
        }
        //加息劵已使用
        else if(item.getUSE_WAY_FLG().equals("2"))
        {
            rl_more.setBackgroundColor(context.getResources().getColor(R.color.used));
            tv_lixi.setTextColor(context.getResources().getColor(R.color.font_gray));
            iv_status.setImageResource(R.mipmap.have_used);
            tv_time.setText("使用时间"+item.getENDDATE());
            rl_back.setBackgroundResource(R.mipmap.my_redbag_used);
        }
        //加息卷已赠送
        else if(item.getUSE_WAY_FLG().equals("3"))
        {
            rl_more.setBackgroundColor(context.getResources().getColor(R.color.used));
            tv_lixi.setTextColor(context.getResources().getColor(R.color.font_gray));
            iv_status.setImageResource(R.mipmap.have_given_away);
            tv_time.setText("赠送时间"+item.getTRANSFER_DATE());
            rl_back.setBackgroundResource(R.mipmap.my_redbag_used);
        }
        //加息劵已过期
        else {
            rl_more.setBackgroundColor(context.getResources().getColor(R.color.used));
            tv_lixi.setTextColor(context.getResources().getColor(R.color.font_gray));
            iv_status.setImageResource(R.mipmap.have_out_of_time);
            tv_time.setText("有效期至"+item.getENDDATE());
            rl_back.setBackgroundResource(R.mipmap.my_redbag_used);
        }
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

    }
}
