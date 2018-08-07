package com.desjf.dsjr.adapter.bankAdapter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.desjf.dsjr.R;
import com.desjf.dsjr.model.bankModel.BankPresentRecordModel;
import com.desjf.dsjr.utils.DataUtil;
import com.desjf.dsjr.widget.WithDrawListener;

import java.util.List;

/**
 * Created by yc.Zhao on 2017/10/9 0009.
 */

public class BankWithDrawRecordAdapter extends BaseQuickAdapter<BankPresentRecordModel.GetwithdrawListBean,BaseViewHolder> {
    private Context context;
    private List<BankPresentRecordModel.GetwithdrawListBean> data;
    private WithDrawListener withDrawListener;
    public BankWithDrawRecordAdapter(@Nullable List<BankPresentRecordModel.GetwithdrawListBean> data, Context context, WithDrawListener withDrawListener) {
        super(R.layout.item_withdraw_list, data);
        this.data=  data;
        this.context = context;
        this.withDrawListener = withDrawListener;
    }

    @Override
    protected void convert(BaseViewHolder helper, final BankPresentRecordModel.GetwithdrawListBean item) {
        helper.setText(R.id.tv_withdraw_txje,item.getTransAmt()+"元");
//        helper.setText(R.id.tv_withdraw_dzje,item.getACTUAL_AMOUNT()+"元");
        String time= DataUtil.stampToDate(item.getUpdDate()+"");
        helper.setText(R.id.tv_withdraw_txsj,time);
//        helper.setText(R.id.tv_withdraw_txsj_two,item.getINS_DATE_H());
//        TextView textView = helper.getView(R.id.tv_withdraw_cz);
        TextView textzt = helper.getView(R.id.tv_withdraw_zt);
//        textView.setText("撤回");
//        textView.setTextColor(context.getResources().getColor(R.color.font_blue));
        textzt.setText(item.getStatus());
      if (item.getStatus().equals("1")){
            //提现成功
            textzt.setText("成功");
        }else {
            //提现失败
            textzt.setText("失败");
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
