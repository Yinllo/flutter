package com.desjf.dsjr.adapter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.desjf.dsjr.R;
import com.desjf.dsjr.model.InviteModel;
import com.desjf.dsjr.utils.DataUtil;

import java.util.List;

/**
 * @Author YinL
 * @Date 2018/5/14 0014
 * @Describe  邀请奖励和邀请返现 适配器
 */

public class InviteFanXianAdapter extends BaseQuickAdapter<InviteModel.InviteAwardListBean,BaseViewHolder> {

    private Context context;
    public InviteFanXianAdapter(@Nullable List<InviteModel.InviteAwardListBean> data, Context context) {
        super(R.layout.invite_item_layout, data);
        this.context =context;
    }
    @Override
    protected void convert(BaseViewHolder helper, InviteModel.InviteAwardListBean item) {

        helper.setText(R.id.tv_name,item.getUSER_NAME());

        helper.setText(R.id.tv_phone,item.getMOBILE());

        String t= DataUtil.ToDate(item.getINS_DATE()+"");
        String time=DataUtil.getWeek(t);

        String s=item.getTENDER_AMOUNT().substring(0,item.getTENDER_AMOUNT().length()-3);
//        helper.setText(R.id.tv_three,s+"元/"+item.getFINANCE_PERIOD()+"个月");
        TextView tv=helper.getView(R.id.tv_three);
        tv.setTextSize(10);
        tv.setText(s+"元/"+item.getFINANCE_PERIOD()+"个月");
        helper.setText(R.id.tv_four,item.getINVITE_AWARD_AMOUNT()+"元");

    }

}
