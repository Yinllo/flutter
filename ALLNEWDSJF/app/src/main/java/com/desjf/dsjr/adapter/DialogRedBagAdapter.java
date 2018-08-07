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
 * @Date 2018/1/22 0022
 * @Describe     红包弹窗适配器     BaseQuickAdapter是项目中引入的第三方开元库
 */

public class DialogRedBagAdapter extends BaseQuickAdapter<AllCouponAndInterestBean,BaseViewHolder> {
    private String flg="0";
    private Context context;
    private int i=-1;
    private String amount;
    private String qixian;
    private String danwei;
    private String qi_tou_period;
    private ImageView iv_choose;
    private TextView tv_money ;
    private TextView tv_y ;//￥
    private TextView tv_jin ;//%
    private TextView tv ;//加息券
    private TextView tv_jine ;//红包金额
    private TextView tv_data ;
    private TextView tv_end ;
    private TextView tv_from ;
    private double money;

    //当前的位置
    public int getI() {
        return i;
    }

    public void setI(int i) {
        this.i = i;
    }

    //是否被选中的状态：flg.equals("1")代表被选中，则将图片设置为被选中的图片
    public String getFlg() {
        return flg;
    }

    public void setFlg(String flg) {
        this.flg = flg;
    }

    public DialogRedBagAdapter(@Nullable List<AllCouponAndInterestBean> data, Context context, String amount, String qixian, String danwei) {
        super(R.layout.new_item_recyclerview_redbag, data);
        this.context = context;
        this.amount = amount;//用户输入的投资金额
        this.qixian =  qixian;//标的期限
        this.danwei = danwei;
    }

    @Override
    protected void convert(final BaseViewHolder helper,final AllCouponAndInterestBean item) {

        //      helper.setText(R.id.tv_money,"8888888888");  标准使用方法
        // helper.setTextColor(R.id.tv_money,context.getResources().getColor(R.color.font));

        qi_tou_period = item.getPERIOD().substring(1,2);//当前红包的期限
        double i2 = Double.parseDouble(qi_tou_period);//红包使用期限
        double i1 = Double.parseDouble(qixian);//标的期限             money>=mimi && i1>=i2红包才能使用
        if(("").equals(amount)){
            money=0;
        }else{
            money = Double.parseDouble(amount);//用户输入的投资金额
        }
        String min_money=item.getMINIMUM_TENDER_AMOUNT().substring(1,item.getMINIMUM_TENDER_AMOUNT().length()-1);
        double mimi = Double.parseDouble(min_money);//红包最小投资金额

        iv_choose = helper.getView(R.id.iv_recyclerview_choose);//选择按钮
        tv_money = helper.getView(R.id.tv_money);//投资金额
        tv_y=helper.getView(R.id.tv_y);// ￥符号
        tv_jine = helper.getView(R.id.tv_jine);//红包金额
        tv=helper.getView(R.id.tv);//加息券
        tv_jin = helper.getView(R.id.tv_jin);// %符号
        tv_data = helper.getView(R.id.tv_data);//红包有效期
        tv_end = helper.getView(R.id.tv_end);//最终期限
        tv_from = helper.getView(R.id.tv_from);//红包来源

        if (danwei.equals("个月")){
            //如果用户没有输入金额  则所有红包都可以选择
            if(money==0){
                //可用红包
                tv_end.setText("标的期限:"+item.getPERIOD());
                tv_end.setTextColor(context.getResources().getColor(R.color.font));
                tv_money.setTextColor(context.getResources().getColor(R.color.font));
                tv_money.setText("投资金额："+item.getMINIMUM_TENDER_AMOUNT());
                tv_from.setTextColor(context.getResources().getColor(R.color.font));
                tv_from.setText(item.getFROM());
                tv_data.setTextColor(context.getResources().getColor(R.color.font));
                tv_data.setText("有效期至:"+item.getEND_DATE());

                //红包还是加息券
                if(item.getTYPE().equals("0")){
                    tv.setVisibility(View.GONE);
                    tv_jin.setVisibility(View.GONE);

                    tv_y.setVisibility(View.VISIBLE);
                    tv_jine.setVisibility(View.VISIBLE);
                    tv_y.setTextColor(context.getResources().getColor(R.color.font));
                    tv_jine.setTextColor(context.getResources().getColor(R.color.font));
                    tv_jine.setText(item.getNUMBER());
                }else{
                    tv_y.setVisibility(View.GONE);
                    tv_jine.setVisibility(View.GONE);

                    tv.setVisibility(View.VISIBLE);
                    tv_jin.setVisibility(View.VISIBLE);
                    tv.setTextColor(context.getResources().getColor(R.color.font));
                    tv.setText(item.getNUMBER());
                    tv_jin.setTextColor(context.getResources().getColor(R.color.font));
                }

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

                if (money>=mimi&&i1>=i2){
                    //可用红包
                    tv_end.setText("标的期限:"+item.getPERIOD());
                    tv_end.setTextColor(context.getResources().getColor(R.color.font));
                    tv_money.setTextColor(context.getResources().getColor(R.color.font));
                    tv_money.setText("投资金额："+item.getMINIMUM_TENDER_AMOUNT());
                    tv_from.setTextColor(context.getResources().getColor(R.color.font));
                    tv_from.setText(item.getFROM());
                    //                    tv_jine.setTextColor(context.getResources().getColor(R.color.font));
                    //                    tv_jine.setText(item.getNUMBER());
                    tv_data.setTextColor(context.getResources().getColor(R.color.font));
                    tv_data.setText("有效期至:"+item.getEND_DATE());
                    if(item.getTYPE().equals("0")){
                        tv.setVisibility(View.GONE);
                        tv_jin.setVisibility(View.GONE);

                        tv_y.setVisibility(View.VISIBLE);
                        tv_jine.setVisibility(View.VISIBLE);
                        tv_jine.setTextColor(context.getResources().getColor(R.color.font));
                        tv_jine.setText(item.getNUMBER());
                        tv_y.setTextColor(context.getResources().getColor(R.color.font));
                    }else{
                        tv_y.setVisibility(View.GONE);
                        tv_jine.setVisibility(View.GONE);

                        tv.setVisibility(View.VISIBLE);
                        tv_jin.setVisibility(View.VISIBLE);
                        tv.setTextColor(context.getResources().getColor(R.color.font));
                        tv.setText(item.getNUMBER());
                        tv_jin.setTextColor(context.getResources().getColor(R.color.font));
                    }

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
                    tv_end.setText("标的期限:"+item.getPERIOD());
                    tv_end.setTextColor(context.getResources().getColor(R.color.font_gray));
                    tv_money.setTextColor(context.getResources().getColor(R.color.font_gray));
                    tv_money.setText("投资金额："+item.getMINIMUM_TENDER_AMOUNT());
                    tv_from.setTextColor(context.getResources().getColor(R.color.font_gray));
                    tv_from.setText(item.getFROM());
                    //                    tv_jine.setTextColor(context.getResources().getColor(R.color.font_gray));
                    //                    tv_jine.setText(item.getNUMBER());
                    tv_data.setTextColor(context.getResources().getColor(R.color.font_gray));
                    tv_data.setText("有效期至:"+item.getEND_DATE());
                    if(item.getTYPE().equals("0")){
                        tv.setVisibility(View.GONE);
                        tv_jin.setVisibility(View.GONE);

                        tv_y.setVisibility(View.VISIBLE);
                        tv_jine.setVisibility(View.VISIBLE);
                        tv_jine.setTextColor(context.getResources().getColor(R.color.font_gray));
                        tv_y.setTextColor(context.getResources().getColor(R.color.font_gray));
                        tv_jine.setText(item.getNUMBER());

                    }else{
                        tv_y.setVisibility(View.GONE);
                        tv_jine.setVisibility(View.GONE);

                        tv.setVisibility(View.VISIBLE);
                        tv_jin.setVisibility(View.VISIBLE);
                        tv_jin.setTextColor(context.getResources().getColor(R.color.font_gray));
                        tv.setTextColor(context.getResources().getColor(R.color.font_gray));
                        tv.setText(item.getNUMBER());
                    }
                    iv_choose.setImageResource(R.mipmap.red_unchoose);
                }
            }
        }else{
            tv_end.setText("标的期限:"+item.getPERIOD());
            tv_end.setTextColor(context.getResources().getColor(R.color.font));
            //            tv_money.setTextColor(context.getResources().getColor(R.color.main));
            tv_money.setText("投资金额："+item.getMINIMUM_TENDER_AMOUNT());
            tv_from.setTextColor(context.getResources().getColor(R.color.font));
            tv_from.setText(item.getFROM());
            //            tv_jine.setTextColor(context.getResources().getColor(R.color.font));
            //            tv_jine.setText(item.getNUMBER());
            tv_data.setTextColor(context.getResources().getColor(R.color.font));
            tv_data.setText("有效期至:"+item.getEND_DATE());

            if(item.getTYPE().equals("0")){
                tv.setVisibility(View.GONE);
                tv_jin.setVisibility(View.GONE);

                tv_y.setVisibility(View.VISIBLE);
                tv_jine.setVisibility(View.VISIBLE);
                tv_y.setTextColor(context.getResources().getColor(R.color.font));
                tv_jine.setTextColor(context.getResources().getColor(R.color.font));
                tv_jine.setText(item.getNUMBER());

            }else{
                tv_y.setVisibility(View.GONE);
                tv_jine.setVisibility(View.GONE);

                tv.setVisibility(View.VISIBLE);
                tv_jin.setVisibility(View.VISIBLE);
                tv_jin.setTextColor(context.getResources().getColor(R.color.font));
                tv.setTextColor(context.getResources().getColor(R.color.font));
                tv.setText(item.getNUMBER());
            }
            iv_choose.setImageResource(R.mipmap.red_unchoose);
        }
    }
}


