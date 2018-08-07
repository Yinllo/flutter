package com.desjf.dsjr.adapter;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.desjf.dsjr.R;
import com.desjf.dsjr.model.CouponListForTenderModel;

import java.util.List;

/**
 * Created by Administrator on 2017/7/31 0031.
 */

public class CouponListAdapter extends BaseQuickAdapter<CouponListForTenderModel,BaseViewHolder> {
    public CouponListAdapter( @Nullable List<CouponListForTenderModel> data) {
        super(R.layout.item_redbagandcoupon, data);
    }



    @Override
    protected void convert(BaseViewHolder helper, CouponListForTenderModel item) {
        helper.setText(R.id.tv_bag_sysj,"使用时间:"+item.getEND_DATE());
        helper.setText(R.id.tv_bag_syje,"使用额度:"+item.getRATE_COUPON_POSITION());
        helper.setText(R.id.tv_bag_zdje,item.getMINIMUN_TENDER_AMOUNT_UNIT());
    }
}
