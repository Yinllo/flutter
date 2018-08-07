package com.desjf.dsjr.widget;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.desjf.dsjr.R;
import com.desjf.dsjr.adapter.RedBagListAdapter;
import com.desjf.dsjr.model.CouponListForTenderModel;

import java.util.List;

/**
 * Created by Administrator on 2017/7/31 0031.
 */

public class RedBagAndCouponDailog extends Dialog {
    private RecyclerView recyclerView;
    private TextView textView;
    private RedBagListAdapter redBagListAdapter;
    private List<CouponListForTenderModel> couponListForTenderModels;
    private String str="";
    private Listener listener;
    public RedBagAndCouponDailog(@NonNull Context context,List<CouponListForTenderModel> couponListForTenderModels,Listener listener) {
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
        redBagListAdapter = new RedBagListAdapter(couponListForTenderModels);
        recyclerView.setAdapter(redBagListAdapter);
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL));
        redBagListAdapter.notifyDataSetChanged();
        recyclerView.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {
                dismiss();
                str = couponListForTenderModels.get(position).getRED_AMOUNT();
                listener.send(position);
            }
        });
    }

}
