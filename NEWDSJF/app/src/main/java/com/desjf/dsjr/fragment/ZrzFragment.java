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
import com.desjf.dsjr.adapter.ZrzAdapter;
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
public class ZrzFragment extends BaseFragment {
    @BindView(R.id.rv_zrz)
    RecyclerView rvZrz;
    @BindView(R.id.srl_zrz)
    SwipeRefreshLayout srlZrz;
    private ZrzAdapter zrzAdapter;
    private MyInvestModel myInvestModel;
    private ClickListener clickListener = new ClickListener() {
        @Override
        public void onClick(Object... objects) {
            myInvestModel = (MyInvestModel) objects[0];
            initClic();
        }
    };
    private List<MyInvestModel> myInvest_list = new ArrayList<>();

    private void initClic() {
        Intent intent = new Intent(getActivity(), ZrzDetailsActivity.class);
        intent.putExtra("zrz", myInvestModel);
        startActivity(intent);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        getZrz();
        View view = inflater.inflate(R.layout.fragment_zrz, container, false);
        ButterKnife.bind(this, view);
        initData();
        return view;
    }

    private void initData() {
        srlZrz.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getZrz();
            }
        });
        srlZrz.setColorSchemeResources(R.color.main);
    }

    private void getZrz() {
        showLoadingDialog();
        BizDataAsyncTask<List<MyInvestModel>> zrz = new BizDataAsyncTask<List<MyInvestModel>>() {
            @Override
            protected List<MyInvestModel> doExecute() throws ZYException, BizFailure {
                return MyInvestBiz.getInvest("4", "0", "20");
            }

            @Override
            protected void onExecuteSucceeded(List<MyInvestModel> myInvestModels) {
                hideLoadingDialog();
                srlZrz.setRefreshing(false);
                initZrz(myInvestModels);
            }

            @Override
            protected void OnExecuteFailed(String error) {
                hideLoadingDialog();
                srlZrz.setRefreshing(false);
            }
        };
        zrz.execute();
    }

    private void initZrz(List<MyInvestModel> myInvestModels) {
        myInvest_list.clear();
        myInvest_list.addAll(myInvestModels);
        zrzAdapter = new ZrzAdapter(myInvest_list, getActivity(), clickListener);
        rvZrz.setAdapter(zrzAdapter);
        rvZrz.setLayoutManager(new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL));
        zrzAdapter.notifyDataSetChanged();
    }

}
