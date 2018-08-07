package com.desjf.dsjr.adapter;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.desjf.dsjr.R;
import com.desjf.dsjr.model.IncomeListModel;

import java.util.List;

/**
 * Created by Administrator on 2017/7/20 0020.
 */

public class MyIncomeAdapter extends BaseQuickAdapter<IncomeListModel,BaseViewHolder> {
    private Context context;
    public MyIncomeAdapter( @Nullable List<IncomeListModel> data,Context context) {
        super(R.layout.item_my_marks, data);
        this.context = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, IncomeListModel item) {
        TextView tv_amount = helper.getView(R.id.tv_my_marks);
        if (item.getREVENUE_EXPEND_TYPE().equals("R")){
            tv_amount.setText(item.getAMOUNT()+"元");
            tv_amount.setTextColor(context.getResources().getColor(R.color.main));
        }else{
            tv_amount.setText(item.getAMOUNT()+"元");
            tv_amount.setTextColor(context.getResources().getColor(R.color.font_blue));
        }

        helper.setText(R.id.tv_my_time,item.getDATE());
        helper.setText(R.id.tv_my_type,item.getFUND_TYPE());

    }
}
