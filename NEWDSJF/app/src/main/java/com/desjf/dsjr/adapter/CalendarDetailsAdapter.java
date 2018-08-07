package com.desjf.dsjr.adapter;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.desjf.dsjr.R;
import com.desjf.dsjr.model.CalendarDayDetailsModel;

import java.util.List;

/**
 * Created by Administrator on 2017/8/7 0007.
 */

public class CalendarDetailsAdapter extends BaseQuickAdapter<CalendarDayDetailsModel,BaseViewHolder> {
    private Context context;
    private String shou;
    public CalendarDetailsAdapter( @Nullable List<CalendarDayDetailsModel> data,Context context,String shou) {
        super(R.layout.item_calender_details, data);
        this.context = context;
        this.shou= shou;
    }

    @Override
    protected void convert(BaseViewHolder helper, CalendarDayDetailsModel item) {
        ImageView iv = helper.getView(R.id.iv_item_type_m);
        helper.setText(R.id.tv_calender_details_bj,"本金"+item.getRECOVER_AMOUNT_CAPITAL()+"元");
        helper.setText(R.id.tv_calender_details_sj,item.getRECOVER_DATE());
        helper.setText(R.id.tv_calender_details_lx,"利息"+item.getRECOVER_AMOUNT_INTEREST()+"元");
        helper.setText(R.id.tv_calender_details_title,item.getPRODUCTS_TITLE());
        if (shou.equals("0")){
            //已收
            if (item.getRECOVER_FLG().equals("0")){
                //未回款
                iv.setVisibility(View.VISIBLE);
                iv.setImageResource(R.mipmap.yuqi);
            }else{
                iv.setVisibility(View.VISIBLE);
                iv.setImageResource(R.mipmap.huikuan);
            }
        }else{
            //未收
            if (item.getOVERDUE_FLG().equals("0")){
                //未逾期
                if (item.getRECOVER_FLG().equals("0")){
                    iv.setVisibility(View.INVISIBLE);
                }else{
                    iv.setVisibility(View.VISIBLE);
                    iv.setImageResource(R.mipmap.huikuan);
                }

            }else if (item.getOVERDUE_FLG().equals("1")){
                //逾期
                iv.setVisibility(View.VISIBLE);
                iv.setImageResource(R.mipmap.yuqi);
            }else{
                iv.setVisibility(View.VISIBLE);
                iv.setImageResource(R.mipmap.huikuan);
            }
        }


    }
}
