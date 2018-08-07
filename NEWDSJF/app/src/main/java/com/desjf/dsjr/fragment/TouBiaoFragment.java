package com.desjf.dsjr.fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.desjf.dsjr.R;
import com.desjf.dsjr.adapter.MyTouBiaoAdapter;
import com.desjf.dsjr.base.BaseFragment;
import com.desjf.dsjr.biz.MyInvestBiz;
import com.desjf.dsjr.biz.exception.BizFailure;
import com.desjf.dsjr.biz.exception.ZYException;
import com.desjf.dsjr.biz.task.BizDataAsyncTask;
import com.desjf.dsjr.model.MyInvestModel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class TouBiaoFragment extends BaseFragment {
    @BindView(R.id.rv_toubiao)
    RecyclerView rvToubiao;
    @BindView(R.id.srl_refresh)
    SwipeRefreshLayout srlRefresh;
    private Context context = getActivity();
    private MyTouBiaoAdapter myTouBiaoAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        getInvest();
        View view = inflater.inflate(R.layout.fragment_tou_biao, container, false);
        ButterKnife.bind(this, view);
        initView();
        return view;
    }

    private void initView() {
        srlRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getInvest();
            }
        });
        srlRefresh.setColorSchemeResources(R.color.main);
    }

    private void getInvest() {
        showLoadingDialog();
        BizDataAsyncTask<List<MyInvestModel>> Invest = new BizDataAsyncTask<List<MyInvestModel>>() {
            @Override
            protected List<MyInvestModel> doExecute() throws ZYException, BizFailure {
                return MyInvestBiz.getInvest("2", "0", "20");
            }

            @Override
            protected void onExecuteSucceeded(List<MyInvestModel> myInvestModels) {
                hideLoadingDialog();
                Log.e("TAG", "onExecuteSucceeded: " + "投标中");
                initToubiao(myInvestModels);
                srlRefresh.setRefreshing(false);
            }

            @Override
            protected void OnExecuteFailed(String error) {
                hideLoadingDialog();
                srlRefresh.setRefreshing(false);
            }
        };
        Invest.execute();
    }

    private void initToubiao(List<MyInvestModel> myInvestModels) {

        myTouBiaoAdapter = new MyTouBiaoAdapter(myInvestModels, getActivity());
        rvToubiao.setAdapter(myTouBiaoAdapter);
        rvToubiao.setLayoutManager(new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL));
        myTouBiaoAdapter.notifyDataSetChanged();
    }

}
