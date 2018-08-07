package com.desjf.dsjr.adapter;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.desjf.dsjr.R;
import com.desjf.dsjr.model.MyInvestModel;

import java.util.List;

/**
 * Created by Administrator on 2017/7/28 0028.
 */

public class MyKzrAdapter extends BaseQuickAdapter<MyInvestModel,BaseViewHolder> {
    private Context context;
    public MyKzrAdapter( @Nullable List<MyInvestModel> data,Context context) {
        super(R.layout.item_my_kzr, data);
        this.context = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, MyInvestModel item) {
        helper.setText(R.id.tv_kzr_title,item.getPRODUCTSTITLE());
        helper.setText(R.id.tv_kzr_tzje,String.valueOf(item.getFINANCEINTERESTRATE()));
        helper.setText(R.id.tv_zkr_ysje,String.valueOf(item.getTRANSFERCONTRACTID()));
    }
}
