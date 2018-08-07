package com.desjf.dsjr.adapter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.desjf.dsjr.R;
import com.desjf.dsjr.model.CouponListForTenderModel;

import java.util.List;

/**
 * Created by Administrator on 2017/8/16 0016.
 */

public class DialogRedBagAdapter extends BaseQuickAdapter<CouponListForTenderModel,BaseViewHolder> {
    private String flg="0";
    private Context context;
    private int i=-1;
    private String amount;
    private String qixian;
    private String danwei;
    public int getI() {
        return i;
    }

    public void setI(int i) {
        this.i = i;
    }

    public String getFlg() {
        return flg;
    }

    public void setFlg(String flg) {
        this.flg = flg;
    }

    public DialogRedBagAdapter(@Nullable List<CouponListForTenderModel> data, Context context,String amount,String qixian,String danwei) {
        super(R.layout.item_recyclerview_redbag, data);
        this.context = context;
        this.amount = amount;
        this.qixian =  qixian;
        this.danwei = danwei;
    }

    @Override
    protected void convert(BaseViewHolder helper, CouponListForTenderModel item) {
        String qi_tou_period = item.getQI_TOU_PERIOD();
        qi_tou_period = qi_tou_period.substring(0,qi_tou_period.length()-2);
        int i2 = Integer.parseInt(qi_tou_period);
        int i1 = Integer.parseInt(qixian);
        int money = Integer.parseInt(amount);
        int mimi = Integer.parseInt(item.getMINI_TENDER());
        ImageView iv_choose = helper.getView(R.id.iv_recyclerview_choose);
        TextView tv_money = helper.getView(R.id.tv_recyclerview_money);
        TextView tv_time = helper.getView(R.id.tv_recyclerview_time);
        TextView tv_lessmony = helper.getView(R.id.tv_recyclerview_less);
        TextView tv_rl = helper.getView(R.id.tv_recyclerview);
        TextView tv_period = helper.getView(R.id.tv_tender_period);

        if (danwei.equals("个月")){
            if (money>=mimi&&i1>=i2){
                //可用红包
                tv_period.setText("投资使用期限类型:"+item.getQI_TOU_PERIOD()+"以上");
                tv_period.setTextColor(context.getResources().getColor(R.color.font));
                tv_money.setTextColor(context.getResources().getColor(R.color.main));
                tv_time.setTextColor(context.getResources().getColor(R.color.font));
                tv_lessmony.setTextColor(context.getResources().getColor(R.color.font));
                tv_rl.setTextColor(context.getResources().getColor(R.color.font));
                tv_money.setText(item.getRED_AMOUNT());
                tv_time.setText("有效期至:"+item.getEND_DATE());
                tv_lessmony.setText("使用条件:投满"+item.getMINI_TENDER()+"元可以使用");
                if (this.i ==helper.getLayoutPosition()){
                    if (flg.equals("1")){
                        iv_choose.setImageResource(R.mipmap.red_choose);
                    }else{
                        iv_choose.setImageResource(R.mipmap.red_unchoose);
                    }
                }else{
                    iv_choose.setImageResource(R.mipmap.red_unchoose);
                }
            }else{
                //不可用红包
                tv_money.setText(item.getRED_AMOUNT());
                tv_money.setTextColor(context.getResources().getColor(R.color.font_gray));
                tv_time.setText("有效期至:"+item.getEND_DATE());
                tv_time.setTextColor(context.getResources().getColor(R.color.font_gray));
                tv_lessmony.setText("使用条件:投满"+item.getMINI_TENDER()+"元可以使用");
                tv_lessmony.setTextColor(context.getResources().getColor(R.color.font_gray));
                tv_rl.setTextColor(context.getResources().getColor(R.color.font_gray));
                tv_period.setText("投资使用期限类型:"+item.getQI_TOU_PERIOD()+"以上");
                tv_period.setTextColor(context.getResources().getColor(R.color.font_gray));
                iv_choose.setImageResource(R.mipmap.red_unchoose);
            }


        }else{
            tv_money.setText(item.getRED_AMOUNT());
            tv_money.setTextColor(context.getResources().getColor(R.color.font_gray));
            tv_period.setText("投资使用期限类型:"+item.getQI_TOU_PERIOD()+"以上");
            tv_period.setTextColor(context.getResources().getColor(R.color.font_gray));
            tv_time.setText("有效期至:"+item.getEND_DATE());
            tv_time.setTextColor(context.getResources().getColor(R.color.font_gray));
            tv_lessmony.setText("使用条件:投满"+item.getMINI_TENDER()+"元可以使用");
            tv_lessmony.setTextColor(context.getResources().getColor(R.color.font_gray));
            tv_rl.setTextColor(context.getResources().getColor(R.color.font_gray));
            iv_choose.setImageResource(R.mipmap.red_unchoose);
        }

    }
}
