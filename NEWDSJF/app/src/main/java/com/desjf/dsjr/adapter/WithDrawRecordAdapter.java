package com.desjf.dsjr.adapter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.desjf.dsjr.R;
import com.desjf.dsjr.model.WithDrawModel;
import com.desjf.dsjr.widget.WithDrawListener;

import java.util.List;

/**
 * Created by yc.Zhao on 2017/10/9 0009.
 */

public class WithDrawRecordAdapter extends BaseQuickAdapter<WithDrawModel,BaseViewHolder> {
    private Context context;
    private List<WithDrawModel> data;
    private WithDrawListener withDrawListener;
    public WithDrawRecordAdapter( @Nullable List<WithDrawModel> data, Context context,WithDrawListener withDrawListener) {
        super(R.layout.item_withdraw_list, data);
        this.data=  data;
        this.context = context;
        this.withDrawListener = withDrawListener;
    }

    @Override
    protected void convert(BaseViewHolder helper, final WithDrawModel item) {
        helper.setText(R.id.tv_withdraw_txje,item.getWITHDRAW_AMOUNT()+"元");
//        helper.setText(R.id.tv_withdraw_dzje,item.getACTUAL_AMOUNT()+"元");
        helper.setText(R.id.tv_withdraw_txsj,item.getINS_DATE_Y()+" "+item.getINS_DATE_H());
//        helper.setText(R.id.tv_withdraw_txsj_two,item.getINS_DATE_H());
//        TextView textView = helper.getView(R.id.tv_withdraw_cz);
        TextView textzt = helper.getView(R.id.tv_withdraw_zt);
//        textView.setText("撤回");
//        textView.setTextColor(context.getResources().getColor(R.color.font_blue));
        textzt.setText(item.getWITHDRAW_STATUS());
        if (item.getWITHDRAW_STATUS().toString().equals("0")){
            //未审核
            textzt.setText("未审核");
        }else if (item.getWITHDRAW_STATUS().toString().equals("1")){
            //提现成功
            textzt.setText("提现成功");
        }else if (item.getWITHDRAW_STATUS().toString().equals("2")){
            //提现失败
            textzt.setText("提现失败");
        }
//        if (item.getSHOW_FLG().equals("0")){
//            //隐藏
//            textView.setVisibility(View.INVISIBLE);
//        }else{
//            textView.setVisibility(View.VISIBLE);
//            textView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    withDrawListener.Chexiao(item.getOID_WITHDRAW_ID());
//                }
//            });
//        }
    }
}
