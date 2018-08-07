package com.desjf.dsjr.fragment;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.desjf.dsjr.R;
import com.desjf.dsjr.activity.KzrDetailsActivity;
import com.desjf.dsjr.adapter.KZRAdapter;
import com.desjf.dsjr.base.BaseFragment;
import com.desjf.dsjr.biz.MyInvestBiz;
import com.desjf.dsjr.biz.exception.BizFailure;
import com.desjf.dsjr.biz.exception.ZYException;
import com.desjf.dsjr.biz.task.BizDataAsyncTask;
import com.desjf.dsjr.model.MyInvestModel;
import com.desjf.dsjr.widget.ClickListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class KzrFragment extends BaseFragment {
    @BindView(R.id.rv_kzr)
    RecyclerView rvKzr;
    @BindView(R.id.rsl_kzr)
    SwipeRefreshLayout rslKzr;
    private Context context = getActivity();
    private KZRAdapter kzrAdapter;
    private MyInvestModel myInvestModel;
    private ClickListener clickListener = new ClickListener() {
        @Override
        public void onClick(Object... objects) {
            myInvestModel = (MyInvestModel) objects[0];
            initDuihuan(myInvestModel);
        }
    };
    private List<MyInvestModel> myInvest_list = new ArrayList<>();

    private void initDuihuan(MyInvestModel myInvestModel) {
//        ToastUtils.showTost(getActivity(),"点击");
        Intent intent = new Intent(getActivity(), KzrDetailsActivity.class);
        intent.putExtra("zr", myInvestModel);
        startActivity(intent);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_kzr, container, false);
        ButterKnife.bind(this, view);
        getKzr();
        initData();
        return view;
    }

    private void initData() {
        rslKzr.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getKzr();
            }
        });
        rslKzr.setColorSchemeResources(R.color.main);
    }

    private void getKzr() {
        showLoadingDialog();
        BizDataAsyncTask<List<MyInvestModel>> kzr = new BizDataAsyncTask<List<MyInvestModel>>() {
            @Override
            protected List<MyInvestModel> doExecute() throws ZYException, BizFailure {
                return MyInvestBiz.getInvest("3", "0", "20");
            }

            @Override
            protected void onExecuteSucceeded(List<MyInvestModel> myInvestModels) {
                hideLoadingDialog();
                rslKzr.setRefreshing(false);
                initKzr(myInvestModels);
            }

            @Override
            protected void OnExecuteFailed(String error) {
                hideLoadingDialog();
                rslKzr.setRefreshing(false);
            }
        };
        kzr.execute();
    }

    private void initKzr(List<MyInvestModel> myInvestModels) {
        myInvest_list.clear();
        myInvest_list.addAll(myInvestModels);
        kzrAdapter = new KZRAdapter(myInvest_list, getActivity(), clickListener);
        rvKzr.setAdapter(kzrAdapter);
        rvKzr.setLayoutManager(new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL));
        kzrAdapter.notifyDataSetChanged();
    }

}
