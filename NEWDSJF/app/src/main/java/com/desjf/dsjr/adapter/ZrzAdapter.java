package com.desjf.dsjr.adapter;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.desjf.dsjr.R;
import com.desjf.dsjr.model.MyInvestModel;
import com.desjf.dsjr.widget.ClickListener;

import java.util.List;

/**
 * Created by Administrator on 2017/8/15 0015.
 */

public class ZrzAdapter extends BaseQuickAdapter<MyInvestModel,BaseViewHolder> {
    private Context context;
    private ClickListener clickListener;
    public ZrzAdapter(@Nullable List<MyInvestModel> data,Context context,ClickListener clickListener) {
        super(R.layout.item_zrz, data);
        this.context = context;
        this.clickListener = clickListener;
    }

    @Override
    protected void convert(BaseViewHolder helper, final MyInvestModel item) {
        helper.setText(R.id.tv_zrz_one,item.getTRANSFERCONTRACTID());
        helper.setText(R.id.tv_zrz_two,item.getPRODUCTSTITLE());
        helper.setText(R.id.tv_cyz_tzje,item.getTRANSFERCAPITALFORMAT());
        helper.setText(R.id.tv_cyz_ysje,item.getTRANSFERCAPITALYES());
        helper.setText(R.id.tv_cyz_dsje,item.getREMAINTIME());
        final TextView tv_click = helper.getView(R.id.tv_cyz_hkxq);
        tv_click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickListener.onClick(item);
            }
        });


    }
}
