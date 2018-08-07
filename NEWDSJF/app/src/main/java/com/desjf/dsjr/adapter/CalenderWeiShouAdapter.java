package com.desjf.dsjr.adapter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.desjf.dsjr.R;
import com.desjf.dsjr.model.CalendarMonDetailsModel;

import java.util.List;

/**
 * Created by Administrator on 2017/8/4 0004.
 */

public class CalenderWeiShouAdapter extends BaseQuickAdapter<CalendarMonDetailsModel,BaseViewHolder> {
    private Context context;
    private String shou;
    public CalenderWeiShouAdapter( @Nullable List<CalendarMonDetailsModel> data,Context context,String shou) {
        super(R.layout.item_calender_weishou, data);
        this.context = context;
        this.shou = shou;
    }

    @Override
    protected void convert(BaseViewHolder helper, CalendarMonDetailsModel item) {

        //截取期数
        String all;//总期数
        String now;//现在的期数
        String qishu=item.getCURRENT_PERIOD();//格式为  1/2
        if(qishu.length()>1){
            all=qishu.substring(2,3);
            now=qishu.substring(0,1);
        }else{
            all =qishu;
                    now=qishu;
        }

        //计算本金
//         int total=Integer.parseInt(item.getRECOVER_AMOUNT_TOTAL());//总共应回款
//         int lixi=Integer.parseInt(item.getRECOVER_AMOUNT_INTEREST());//利息
//         int bj=total-lixi;//本金

        helper.setText(R.id.tv_item_time,item.getRECOVER_DATE());
        helper.setText(R.id.tv_item_title,item.getPRODUCTS_TITLE());
        helper.setText(R.id.tv_item_qishu_all,"共"+all+"期");
        helper.setText(R.id.tv_item_qishu,"第"+now+"期");
        helper.setText(R.id.tv_item_dsbj_money,item.getRECOVER_AMOUNT_CAPITAL_WEI());

        helper.setText(R.id.tv_item_dhlx_money,item.getRECOVER_AMOUNT_INTEREST());
        ImageView iv = helper.getView(R.id.iv_item_type);
        if (shou.equals("0")){
            if ( item.getRECOVER_FLG().equals("0")){
                //未逾期
                iv.setVisibility(View.INVISIBLE);
            }else{
                //逾期
                iv.setVisibility(View.VISIBLE);
                iv.setImageResource(R.mipmap.huikuan);
            }
        }else{
            if ( item.getOVERDUE_FLG().equals("0")){
                //未逾期
                iv.setVisibility(View.INVISIBLE);
            }else{
                //逾期
                iv.setVisibility(View.VISIBLE);
                iv.setImageResource(R.mipmap.yuqi);
            }
        }

    }
}
