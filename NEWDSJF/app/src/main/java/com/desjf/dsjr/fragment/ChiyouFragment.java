package com.desjf.dsjr.fragment;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.desjf.dsjr.R;
import com.desjf.dsjr.activity.WodeInvestDetailsActivity;
import com.desjf.dsjr.adapter.MyCyzAdapter;
import com.desjf.dsjr.base.BaseFragment;
import com.desjf.dsjr.biz.MyInvestBiz;
import com.desjf.dsjr.biz.exception.BizFailure;
import com.desjf.dsjr.biz.exception.ZYException;
import com.desjf.dsjr.biz.task.BizDataAsyncTask;
import com.desjf.dsjr.model.MyInvestModel;
import com.desjf.dsjr.widget.WrapContentLinearLayoutManager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class ChiyouFragment extends BaseFragment {
    @BindView(R.id.rv_chiyou)
    RecyclerView rvChiyou;
    @BindView(R.id.srl_chiyou)
    SwipeRefreshLayout srlChiyou;
    private Context context = getActivity();
    private MyCyzAdapter myCyzAdapter;
    private List<MyInvestModel> myInvestModel = new ArrayList<>();
    private int i=0;
    private int lastVisibleItem;
    private LinearLayoutManager linearLayoutManager;
    private String status="";
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_chiyou, container, false);
        ButterKnife.bind(this, view);
        getInvest();
        initData();
        return view;

    }

    private void initData() {
        srlChiyou.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                i=0;
                myInvestModel.clear();
                getInvest();
            }
        });
        srlChiyou.setColorSchemeResources(R.color.main);
        //下拉加载更多
        rvChiyou.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == RecyclerView.SCROLL_STATE_IDLE
                        && lastVisibleItem + 1 == myCyzAdapter.getItemCount()&& myCyzAdapter.getItemCount()>=20) {
                    i++;
                    getInvest();
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                lastVisibleItem = linearLayoutManager.findLastVisibleItemPosition();
            }
        });

    }

    private void getInvest() {
        showLoadingDialog();
        BizDataAsyncTask<List<MyInvestModel>> Invest = new BizDataAsyncTask<List<MyInvestModel>>() {
            @Override
            protected List<MyInvestModel> doExecute() throws ZYException, BizFailure {
                return MyInvestBiz.getInvest("1", String.valueOf(i), "20");
            }

            @Override
            protected void onExecuteSucceeded(List<MyInvestModel> myInvestModels) {
                hideLoadingDialog();
                srlChiyou.setRefreshing(false);
                initZyc(myInvestModels);

            }

            @Override
            protected void OnExecuteFailed(String error) {
                hideLoadingDialog();
                srlChiyou.setRefreshing(false);
            }
        };
        Invest.execute();
    }

    private void initZyc(final List<MyInvestModel> myInvestModels) {
        myInvestModel.clear();
        myInvestModel.addAll(myInvestModels);
        if (i==0){
            myCyzAdapter = new MyCyzAdapter(myInvestModel, getActivity());
                rvChiyou.setAdapter(myCyzAdapter);
                linearLayoutManager =new WrapContentLinearLayoutManager(getActivity());
                rvChiyou.setLayoutManager(linearLayoutManager);
                myCyzAdapter.notifyDataSetChanged();

        }else{
            myCyzAdapter.addData(myInvestModels);
            myCyzAdapter.notifyDataSetChanged();
          }

        myCyzAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                switch (view.getId()) {
                    case R.id.tv_cyz_hkxq:
                    Intent intent = new Intent(getActivity(), WodeInvestDetailsActivity.class);
                    intent.putExtra("kind", 1);
                    intent.putExtra("details", myInvestModel.get(position));
                    intent.putExtra("tag", 0);
                    startActivity(intent);
                    break;
                    case R.id.find:

                        break;
                }
            }
        });

    }


}
