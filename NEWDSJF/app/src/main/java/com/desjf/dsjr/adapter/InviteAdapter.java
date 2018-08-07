package com.desjf.dsjr.adapter;

import android.content.Context;
import android.support.annotation.Nullable;

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

public class InviteAdapter extends BaseQuickAdapter<InviteModel.InviteAwardListBean,BaseViewHolder> {

    private Context context;
    public InviteAdapter(@Nullable List<InviteModel.InviteAwardListBean> data, Context context) {
        super(R.layout.invite_item_layout, data);
        this.context =context;
    }
    @Override
    protected void convert(BaseViewHolder helper, InviteModel.InviteAwardListBean item) {

        helper.setText(R.id.tv_name,item.getUSER_NAME());

        helper.setText(R.id.tv_phone,item.getMOBILE());

        String t= DataUtil.ToDate(item.getINS_DATE()+"");
        String time=DataUtil.getWeek(t);

        helper.setText(R.id.tv_three,t);

        if(item.getINVITE_AWARD_AMOUNT().equals("20.00")){
            String amount=item.getINVITE_AWARD_AMOUNT().substring(0,2);
            helper.setText(R.id.tv_four,amount+"元注册红包");
        }else{
            String amounts=item.getINVITE_AWARD_AMOUNT().substring(0,2);
            helper.setText(R.id.tv_four,amounts+"元首投红包");
        }

    }

}
