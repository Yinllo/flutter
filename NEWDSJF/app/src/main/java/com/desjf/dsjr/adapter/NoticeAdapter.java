package com.desjf.dsjr.adapter;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.desjf.dsjr.R;
import com.desjf.dsjr.model.NoticeModel;

import java.util.List;

/**
 * Created by Administrator on 2017/7/26 0026.
 */

public class NoticeAdapter extends BaseQuickAdapter<NoticeModel,BaseViewHolder> {
    private Context context;
    public NoticeAdapter(@Nullable List<NoticeModel> data,Context context) {
        super(R.layout.item_notice_info, data);
        this.context = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, NoticeModel item) {
        helper.setText(R.id.tv_notice_title,item.getTITLE());
        helper.setText(R.id.tv_notice_time,item.getPUBLISH_DATE());
    }
}
