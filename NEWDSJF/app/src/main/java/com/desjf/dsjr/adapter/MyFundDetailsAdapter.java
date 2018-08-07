package com.desjf.dsjr.adapter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.desjf.dsjr.R;
import com.desjf.dsjr.model.JyRecordModel;

import java.util.List;

/**
 * Created by Administrator on 2017/7/20 0020.
 */

public class MyFundDetailsAdapter extends BaseQuickAdapter<JyRecordModel,BaseViewHolder> {
    private Context context;
    private NewMyFundDetailsItemAdapter myFundDetailsItemAdapter;
    public MyFundDetailsAdapter( @Nullable List<JyRecordModel> data,Context context) {
        super(R.layout.item_fund_details, data);
        this.context = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, JyRecordModel item) {
//        TextView tv_month = helper.getView(R.id.tv_yj_month);
//        tv_month.setText(item.getYMKEY());
        myFundDetailsItemAdapter = new NewMyFundDetailsItemAdapter(item.getYMVAL(),context);
        RecyclerView view = helper.getView(R.id.rv_fund_details_item);
        view.setAdapter(myFundDetailsItemAdapter);
        view.setLayoutManager(new StaggeredGridLayoutManager(1,StaggeredGridLayoutManager.VERTICAL));
        myFundDetailsItemAdapter.notifyDataSetChanged();

    }
}
