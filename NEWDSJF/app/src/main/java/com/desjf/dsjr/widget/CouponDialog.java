package com.desjf.dsjr.widget;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.desjf.dsjr.R;
import com.desjf.dsjr.adapter.CouponListAdapter;
import com.desjf.dsjr.adapter.RedBagListAdapter;
import com.desjf.dsjr.model.CouponListForTenderModel;

import java.util.List;

/**
 * Created by Administrator on 2017/7/31 0031.
 */

public class CouponDialog extends Dialog {
    private RecyclerView recyclerView;
    private CouponListAdapter redBagListAdapter;
    private List<CouponListForTenderModel> couponListForTenderModels;
    private CouponListener listener;
    private String str="";
    public CouponDialog(@NonNull Context context,List<CouponListForTenderModel> couponListForTenderModels,CouponListener listener) {
        super(context, R.style.My_Dialog);
        this.couponListForTenderModels = couponListForTenderModels;
        this.listener = listener;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item_redbag_dialog);
        initData();

    }

    private void initData() {
        recyclerView = (RecyclerView) findViewById(R.id.rv_redbag_list);
        redBagListAdapter = new CouponListAdapter(couponListForTenderModels);
        recyclerView.setAdapter(redBagListAdapter);
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL));
        redBagListAdapter.notifyDataSetChanged();
        recyclerView.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {
                dismiss();
                str = couponListForTenderModels.get(position).getRED_AMOUNT();
                listener.sendCoupon(position);
            }
        });
    }
}
