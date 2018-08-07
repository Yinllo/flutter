package com.desjf.dsjr.activity;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.desjf.dsjr.R;
import com.desjf.dsjr.adapter.ReChargeRecordAdapter;
import com.desjf.dsjr.base.BaseActivity;
import com.desjf.dsjr.biz.ChargeBiz;
import com.desjf.dsjr.biz.exception.BizFailure;
import com.desjf.dsjr.biz.exception.ZYException;
import com.desjf.dsjr.biz.task.BizDataAsyncTask;
import com.desjf.dsjr.model.ReChargeRecordModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RechargeRecordActivity extends BaseActivity {
    ReChargeRecordAdapter rechargeRecordadapter;
    @BindView(R.id.iv_charge_back)
    ImageView ivChargeBack;
    @BindView(R.id.rv_recharge_record)
    RecyclerView rvRechargeRecord;
    @BindView(R.id.tv_none)
    LinearLayout tv ;
    int i = 0;
    @BindView(R.id.srl_record)
    SwipeRefreshLayout srlRecord;
    private int lastVisibleItem;
    private LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
    private List<ReChargeRecordModel> list_record=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recharge_record);
        ButterKnife.bind(this);
        initData();
        initView();

    }

    private void initView() {
        srlRecord.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                i=0;
                list_record.clear();
                initData();
            }
        });
        srlRecord.setColorSchemeResources(R.color.main);
        rvRechargeRecord.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == RecyclerView.SCROLL_STATE_IDLE
                        && lastVisibleItem + 1 == rechargeRecordadapter.getItemCount()&&rechargeRecordadapter.getItemCount()>=19) {
                    i++;
                    initData();
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                lastVisibleItem = linearLayoutManager.findLastVisibleItemPosition();
            }
        });

        //判断当前是否有数据
//        if(list_record.size()>0){
//           tv.setVisibility(View.GONE);
//            srlRecord.setVisibility(View.VISIBLE);
//        }else{
//           tv.setVisibility(View.VISIBLE);
//            srlRecord.setVisibility(View.GONE);
//        }


    }




    private void initData() {
        showLoadingDialog();
        BizDataAsyncTask<List<ReChargeRecordModel>> getRecord = new BizDataAsyncTask<List<ReChargeRecordModel>>() {
            @Override
            protected List<ReChargeRecordModel> doExecute() throws ZYException, BizFailure {
                return ChargeBiz.getRechargeRecord(String.valueOf(i), "20");
            }

            @Override
            protected void onExecuteSucceeded(List<ReChargeRecordModel> reChargeRecordModel) {
                hideLoadingDialog();
                srlRecord.setRefreshing(false);
                initRecord(reChargeRecordModel);
            }

            @Override
            protected void OnExecuteFailed(String error) {
                hideLoadingDialog();
                srlRecord.setRefreshing(false);
            }
        };
        getRecord.execute();

    }

    private void initRecord(List<ReChargeRecordModel> reChargeRecordModel) {
//        ToastUtils.showTost(RechargeRecordActivity.this,reChargeRecordModel.size()+"");
        list_record.addAll(reChargeRecordModel);
        if (i==0){
            rechargeRecordadapter = new ReChargeRecordAdapter(reChargeRecordModel, this);
            rvRechargeRecord.setAdapter(rechargeRecordadapter);
            rvRechargeRecord.setLayoutManager(linearLayoutManager);
            rechargeRecordadapter.notifyDataSetChanged();
        }else{
            rechargeRecordadapter.addData(reChargeRecordModel);
            rechargeRecordadapter.notifyDataSetChanged();
        }

    }

    @OnClick(R.id.iv_charge_back)
    public void onClick() {
        finish();
    }
}
