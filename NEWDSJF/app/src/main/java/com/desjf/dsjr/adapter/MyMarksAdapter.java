package com.desjf.dsjr.adapter;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.desjf.dsjr.R;
import com.desjf.dsjr.model.AccountModel;

import java.util.List;

/**
 * Created by Administrator on 2017/7/20 0020.
 */

public class MyMarksAdapter extends BaseQuickAdapter<AccountModel.POINTINFOLISTBean,BaseViewHolder> {
    private Context context;
    public MyMarksAdapter(@Nullable List<AccountModel.POINTINFOLISTBean> data,Context context) {
        super(R.layout.item_my_marks, data);
        this.context=context;
    }

    @Override
    protected void convert(BaseViewHolder helper, AccountModel.POINTINFOLISTBean item) {
            helper.setText(R.id.tv_my_marks,item.getPOINT());
            helper.setText(R.id.tv_my_time,item.getINSDATE());
            helper.setText(R.id.tv_my_type,item.getPOINTDESCRIPTION());
    }
}
