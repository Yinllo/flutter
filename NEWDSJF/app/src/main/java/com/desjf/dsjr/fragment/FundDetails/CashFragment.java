package com.desjf.dsjr.fragment.FundDetails;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.desjf.dsjr.R;
import com.desjf.dsjr.adapter.MyFundDetailsAdapter;
import com.desjf.dsjr.base.BaseFragment;
import com.desjf.dsjr.biz.JyRecordBiz;
import com.desjf.dsjr.biz.exception.BizFailure;
import com.desjf.dsjr.biz.exception.ZYException;
import com.desjf.dsjr.biz.task.BizDataAsyncTask;
import com.desjf.dsjr.model.JyRecordModel;
import com.desjf.dsjr.widget.RecyclerViewNoBugLinearLayoutManager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class CashFragment extends BaseFragment {

    private View rootView;

    @BindView(R.id.tv_zwsj)
    LinearLayout tvZwsj;
    private List<String> str_list = new ArrayList<>();
    private MyFundDetailsAdapter myFundDetailsAdapter;
    @BindView(R.id.srl_zjmx)
    SwipeRefreshLayout srlZjmx;
    @BindView(R.id.rv_account_fund_details)
    RecyclerView rvAccountFundDetails;

    private String str = "2";
    private int i = 0;
    private List<JyRecordModel> jyRecordModelList = new ArrayList<>();
    private int lastVisibleItem;
    private RecyclerViewNoBugLinearLayoutManager linearLayoutManager;
    boolean ifRefresh=false;//记录是否刷新


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        if (null == rootView) {
            rootView = inflater.inflate(R.layout.fragment_cash, null);
            ButterKnife.bind(this, rootView);
            getJyjl();
            initData();
        }

        ViewGroup parent = (ViewGroup)rootView.getParent();
        if (parent != null) {
            parent.removeView(rootView);
        }

//        rootView = (View) inflater.inflate(R.layout.fragment_payments, container, false);

        return rootView;
    }

    private void getJyjl() {
        showLoadingDialog();
        BizDataAsyncTask<List<JyRecordModel>> getJy = new BizDataAsyncTask<List<JyRecordModel>>() {
            @Override
            protected List<JyRecordModel> doExecute() throws ZYException, BizFailure {
                return JyRecordBiz.getRecord(String.valueOf(i), "300", str);
            }

            @Override
            protected void onExecuteSucceeded(List<JyRecordModel> jyRecordModels) {
                hideLoadingDialog();
                srlZjmx.setRefreshing(false);
                //处理交易记录逻辑
                initJy(jyRecordModels);
            }

            @Override
            protected void OnExecuteFailed(String error) {
                hideLoadingDialog();
                srlZjmx.setRefreshing(false);
                srlZjmx.setVisibility(View.GONE);
                tvZwsj.setVisibility(View.VISIBLE);
            }
        };
        getJy.execute();
    }

    private void initJy(List<JyRecordModel> jyRecordModels) {
        linearLayoutManager = new RecyclerViewNoBugLinearLayoutManager(getActivity());
        if(ifRefresh){
            jyRecordModelList.clear();
        }
        jyRecordModelList.addAll(jyRecordModels);
        if (jyRecordModelList.size()!=0) {
            if (i == 0) {
                myFundDetailsAdapter = new MyFundDetailsAdapter(jyRecordModelList,getActivity());
                rvAccountFundDetails.setAdapter(myFundDetailsAdapter);
                rvAccountFundDetails.setLayoutManager(linearLayoutManager);
                myFundDetailsAdapter.notifyDataSetChanged();
            } else {
                myFundDetailsAdapter.addData(jyRecordModels);
                myFundDetailsAdapter.notifyDataSetChanged();
            }
            tvZwsj.setVisibility(View.GONE);
            srlZjmx.setVisibility(View.VISIBLE);
        } else {
            srlZjmx.setVisibility(View.GONE);
            tvZwsj.setVisibility(View.VISIBLE);
        }


    }

    private void initData() {
        srlZjmx.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                i = 0;
               ifRefresh=true;
                getJyjl();
            }
        });
        srlZjmx.setColorSchemeResources(R.color.main);

    }
}