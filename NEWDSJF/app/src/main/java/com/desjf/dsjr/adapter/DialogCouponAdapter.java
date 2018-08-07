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

public class DialogCouponAdapter extends BaseQuickAdapter<CouponListForTenderModel,BaseViewHolder> {
    private String flg="0";
    private Context context;
    private int i=-1;
    private String amount;
    private String qixian;
    private String danwei;
    public String getFlg() {
        return flg;
    }

    public void setFlg(String flg) {
        this.flg = flg;
    }

    public int getI() {
        return i;
    }

    public void setI(int i) {
        this.i = i;
    }

    public DialogCouponAdapter(@Nullable List<CouponListForTenderModel> data, Context context,String amount,String qixian,String danwei) {
        super(R.layout.new_item_recyclerview_redbag, data);
        this.context = context;
        this.amount = amount;
        this.qixian = qixian;
        this.danwei = danwei;
    }

    @Override
    protected void convert(BaseViewHolder helper, CouponListForTenderModel item) {
        String qi_tou_period = item.getQI_TOU_PERIOD();
        qi_tou_period = qi_tou_period.substring(0,qi_tou_period.length()-2);
        int i2 = Integer.parseInt(qi_tou_period);
        int i1 = Integer.parseInt(qixian);
        int money = Integer.parseInt(amount);
        int mimi = Integer.parseInt(item.getCONDITIONS());
        ImageView iv_choose = helper.getView(R.id.iv_recyclerview_choose);
        TextView tv_money = helper.getView(R.id.tv_recyclerview_money);
        TextView tv_time = helper.getView(R.id.tv_recyclerview_time);
        TextView tv_lessmony = helper.getView(R.id.tv_recyclerview_less);
        TextView tv_rl = helper.getView(R.id.tv_recyclerview);
        if (danwei.equals("个月")){
            if (money>=mimi&&i1<=i2){
                tv_money.setTextColor(context.getResources().getColor(R.color.main));
                tv_time.setTextColor(context.getResources().getColor(R.color.font));
                tv_lessmony.setTextColor(context.getResources().getColor(R.color.font));
                tv_rl.setTextColor(context.getResources().getColor(R.color.font));
                tv_rl.setText("加息劵:");
                tv_money.setText(item.getRATE_COUPON_POSITION()+"%");
                tv_time.setText("有效期至:"+item.getEND_DATE());
                tv_lessmony.setText("最低投资金额:"+item.getCONDITIONS()+"元");
                if (i==helper.getLayoutPosition()){
                    if (flg.equals("1")){
                        iv_choose.setImageResource(R.mipmap.red_choose);
                    }else{
                        iv_choose.setImageResource(R.mipmap.red_unchoose);
                    }
                }else{
                    iv_choose.setImageResource(R.mipmap.red_unchoose);
                }

            }else{
                tv_money.setTextColor(context.getResources().getColor(R.color.font_gray));
                tv_time.setTextColor(context.getResources().getColor(R.color.font_gray));
                tv_lessmony.setTextColor(context.getResources().getColor(R.color.font_gray));
                tv_rl.setTextColor(context.getResources().getColor(R.color.font_gray));
                tv_rl.setText("加息劵:");
                tv_money.setText(item.getRATE_COUPON_POSITION()+"%");
                tv_time.setText("有效期至:"+item.getEND_DATE());
                tv_lessmony.setText("最低投资金额:"+item.getCONDITIONS()+"元");
                iv_choose.setImageResource(R.mipmap.red_unchoose);
            }
        }else{
            tv_money.setTextColor(context.getResources().getColor(R.color.font_gray));
            tv_time.setTextColor(context.getResources().getColor(R.color.font_gray));
            tv_lessmony.setTextColor(context.getResources().getColor(R.color.font_gray));
            tv_rl.setTextColor(context.getResources().getColor(R.color.font_gray));
            tv_rl.setText("加息劵:");
            tv_money.setText(item.getRATE_COUPON_POSITION()+"%");
            tv_time.setText("有效期至:"+item.getEND_DATE());
            tv_lessmony.setText("最低投资金额:"+item.getCONDITIONS()+"元");
            iv_choose.setImageResource(R.mipmap.red_unchoose);
        }


    }
}
