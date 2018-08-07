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

public class KZRAdapter extends BaseQuickAdapter<MyInvestModel,BaseViewHolder> {
    private Context context;
    private ClickListener clickListener;
    public KZRAdapter( @Nullable List<MyInvestModel> data,Context context,ClickListener clickListener) {
        super(R.layout.item_kzr, data);
        this.context = context;
        this.clickListener = clickListener;
    }

    @Override
    protected void convert(BaseViewHolder helper, final MyInvestModel item) {
        TextView tv_click = helper.getView(R.id.tv_cyz_hkxq);
        helper.setText(R.id.tv_cyz_title,item.getPRODUCTSTITLE());
        helper.setText(R.id.tv_cyz_tzje,item.getFINANCEINTERESTRATE());
        helper.setText(R.id.tv_cyz_ysje,item.getREMAINDAYS());
        helper.setText(R.id.tv_cyz_dsje,item.getCAPITALWAIT());
//        helper.setText(R.id.tv_oone,"债权金额(元)");
//        helper.setText(R.id.tv_otwo,"转出金额(元)");
        helper.setText(R.id.tv_sy,"债权价值(元)");
        tv_click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickListener.onClick(item);
            }
        });
    }
}
