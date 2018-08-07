package com.desjf.dsjr.fragment;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.desjf.dsjr.R;
import com.desjf.dsjr.activity.WodeInvestDetailsActivity;
import com.desjf.dsjr.adapter.HuikuanAdapter;
import com.desjf.dsjr.base.BaseFragment;
import com.desjf.dsjr.biz.MyInvestBiz;
import com.desjf.dsjr.biz.exception.BizFailure;
import com.desjf.dsjr.biz.exception.ZYException;
import com.desjf.dsjr.biz.task.BizDataAsyncTask;
import com.desjf.dsjr.model.MyInvestModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class HuiKuanFragment extends BaseFragment {
    @BindView(R.id.rv_huikuan)
    RecyclerView rvHuikuan;
    @BindView(R.id.srl_huikuan)
    SwipeRefreshLayout srlHuikuan;
    private Context context = getActivity();
    private HuikuanAdapter huiKuanAdapter;
    private List<MyInvestModel> myInvest_list = new ArrayList<>();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        getInvest();
        View view = inflater.inflate(R.layout.fragment_hui_kuan, container, false);
        ButterKnife.bind(this, view);
        initData();
        return view;
    }

    private void initData() {
        srlHuikuan.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getInvest();
            }
        });
        srlHuikuan.setColorSchemeResources(R.color.main);
    }

    private void getInvest() {
        showLoadingDialog();
        BizDataAsyncTask<List<MyInvestModel>> Invest = new BizDataAsyncTask<List<MyInvestModel>>() {
            @Override
            protected List<MyInvestModel> doExecute() throws ZYException, BizFailure {
                return MyInvestBiz.getInvest("6", "0", "20");
            }

            @Override
            protected void onExecuteSucceeded(List<MyInvestModel> myInvestModels) {
                hideLoadingDialog();
                Log.e("TAG", "onExecuteSucceeded: " + "回款中");
                srlHuikuan.setRefreshing(false);
                initHuikuan(myInvestModels);
            }

            @Override
            protected void OnExecuteFailed(String error) {
                hideLoadingDialog();
                srlHuikuan.setRefreshing(false);
            }
        };
        Invest.execute();
    }

    private void initHuikuan(final List<MyInvestModel> myInvestModels) {
        myInvest_list.clear();
        myInvest_list.addAll(myInvestModels);
        huiKuanAdapter = new HuikuanAdapter(myInvest_list, getActivity());
        rvHuikuan.setAdapter(huiKuanAdapter);
        rvHuikuan.setLayoutManager(new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL));
        huiKuanAdapter.notifyDataSetChanged();

        huiKuanAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                switch (view.getId()) {
                    case R.id.tv_cyz_hkxq:
                Intent intent = new Intent(getActivity(), WodeInvestDetailsActivity.class);
                intent.putExtra("kind", 2);
                intent.putExtra("details", myInvest_list.get(position));
                intent.putExtra("tag", 1);
                startActivity(intent);
                        break;
                    case R.id.find:

                        break;
                }
            }
        });
    }

}
