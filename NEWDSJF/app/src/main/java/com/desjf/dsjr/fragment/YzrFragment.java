package com.desjf.dsjr.fragment;


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
import com.desjf.dsjr.activity.ZrzDetailsActivity;
import com.desjf.dsjr.adapter.YZRAdapter;
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
public class YzrFragment extends BaseFragment {
    @BindView(R.id.rv_yzr)
    RecyclerView rvYzr;
    @BindView(R.id.srl_yzr)
    SwipeRefreshLayout srlYzr;
    private YZRAdapter yrzAdapter;
    private MyInvestModel myInvestModel;
    private ClickListener clickListener = new ClickListener() {
        @Override
        public void onClick(Object... objects) {
            myInvestModel = (MyInvestModel) objects[0];
            initGo();
        }
    };
    private List<MyInvestModel> myInvest_list = new ArrayList<>();

    private void initGo() {
        Intent intent = new Intent(getActivity(), ZrzDetailsActivity.class);
        intent.putExtra("zrz", myInvestModel);
        startActivity(intent);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_yzr, container, false);
        ButterKnife.bind(this, view);
        getYzr();
        initData();
        return view;
    }

    private void initData() {
        srlYzr.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getYzr();
            }
        });
        srlYzr.setColorSchemeResources(R.color.main);
    }

    private void getYzr() {
        showLoadingDialog();
        BizDataAsyncTask<List<MyInvestModel>> yzr = new BizDataAsyncTask<List<MyInvestModel>>() {
            @Override
            protected List<MyInvestModel> doExecute() throws ZYException, BizFailure {
                return MyInvestBiz.getInvest("5", "0", "20");
            }

            @Override
            protected void onExecuteSucceeded(List<MyInvestModel> myInvestModels) {
                hideLoadingDialog();
                srlYzr.setRefreshing(false);
                initYzr(myInvestModels);
            }

            @Override
            protected void OnExecuteFailed(String error) {
                hideLoadingDialog();
                srlYzr.setRefreshing(false);
            }
        };
        yzr.execute();
    }

    private void initYzr(List<MyInvestModel> myInvestModels) {
        myInvest_list.clear();
        myInvest_list.addAll(myInvestModels);
        yrzAdapter = new YZRAdapter(myInvest_list, getActivity(), clickListener);
        rvYzr.setAdapter(yrzAdapter);
        rvYzr.setLayoutManager(new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL));
        yrzAdapter.notifyDataSetChanged();
    }

}
