package com.desjf.dsjr.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.desjf.dsjr.R;
import com.desjf.dsjr.adapter.AutoTenderListAdapter;
import com.desjf.dsjr.base.BaseActivity;
import com.desjf.dsjr.biz.AutoTenderBiz;
import com.desjf.dsjr.biz.exception.BizFailure;
import com.desjf.dsjr.biz.exception.ZYException;
import com.desjf.dsjr.biz.task.BizDataAsyncTask;
import com.desjf.dsjr.model.AutoRecordModel;
import com.desjf.dsjr.utils.ToastUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AccountAutoTenderListActivity extends BaseActivity {
    @BindView(R.id.tv_zusj)
    TextView tvZusj;
    @BindView(R.id.srl_snmlgb)
    SwipeRefreshLayout srlSnmlgb;
    private Context content = this;
    @BindView(R.id.iv_auto_tender_record_list_back)
    ImageView ivAutoTenderRecordListBack;
    @BindView(R.id.rv_auto_tender_list)
    RecyclerView rvAutoTenderList;
    private AutoTenderListAdapter autotenderListAdapter;
    private int i=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_auto_tender_list);
        ButterKnife.bind(this);
        getListInfo();
        initData();
    }

    private void initData() {
        srlSnmlgb.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getListInfo();
            }
        });
        srlSnmlgb.setColorSchemeResources(R.color.main);
    }

    private void getListInfo() {
        showLoadingDialog();
        BizDataAsyncTask<AutoRecordModel> getRecordList = new BizDataAsyncTask<AutoRecordModel>() {
            @Override
            protected AutoRecordModel doExecute() throws ZYException, BizFailure {
                return AutoTenderBiz.getAutoRecord(String.valueOf(i), "20");
            }

            @Override
            protected void onExecuteSucceeded(AutoRecordModel autoRecordModel) {
                hideLoadingDialog();
                srlSnmlgb.setRefreshing(false);
                initListRecord(autoRecordModel);
            }

            @Override
            protected void OnExecuteFailed(String error) {
                hideLoadingDialog();
                srlSnmlgb.setRefreshing(false);

                ToastUtils.showTost(content, error);
            }
        };
        getRecordList.execute();
    }

    private void initListRecord(AutoRecordModel autoRecordModel) {
        autotenderListAdapter = new AutoTenderListAdapter(autoRecordModel.getAUTOSETLIST(), AccountAutoTenderListActivity.this);
        rvAutoTenderList.setAdapter(autotenderListAdapter);
        rvAutoTenderList.setLayoutManager(new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL));
        autotenderListAdapter.notifyDataSetChanged();

    }

    @OnClick(R.id.iv_auto_tender_record_list_back)
    public void onClick() {
        finish();
    }
}
