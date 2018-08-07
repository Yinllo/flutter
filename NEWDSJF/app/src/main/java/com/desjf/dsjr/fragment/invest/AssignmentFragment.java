package com.desjf.dsjr.fragment.invest;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.desjf.dsjr.R;
import com.desjf.dsjr.activity.AssignmentDetailsActivity;
import com.desjf.dsjr.adapter.AssignmentAdapter;
import com.desjf.dsjr.base.BaseFragment;
import com.desjf.dsjr.bean.ShouyeListBean;
import com.desjf.dsjr.biz.InvestmentListABiz;
import com.desjf.dsjr.biz.exception.BizFailure;
import com.desjf.dsjr.biz.exception.ZYException;
import com.desjf.dsjr.biz.task.BizDataAsyncTask;
import com.desjf.dsjr.model.AssignmentModel;
import com.desjf.dsjr.widget.WrapContentLinearLayoutManager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 * 债转Fragment
 */
public class AssignmentFragment extends BaseFragment {
    @BindView(R.id.srl_assigment)
    SwipeRefreshLayout srlAssigment;
    private List<ShouyeListBean> slb_list;
    private AssignmentAdapter assignmentAdapter;
    @BindView(R.id.rv_assignment)
    RecyclerView rvAssignment;

    @BindView(R.id.tv_zwsj)
    LinearLayout tvZwsj;

    private AssignmentModel assignmentModels;
    private int i=0;
    private String tzqx="";
    private String hkfs="";
    private List<AssignmentModel.RESULTLISTBean> list = new ArrayList<>();
    private int lastVisibleItem;
    private LinearLayoutManager linearLayoutManager = new WrapContentLinearLayoutManager(getActivity());
    private BroadcastReceiver receiver;//通知刷新广播
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_assignment, container, false);
        ButterKnife.bind(this, view);
        receiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                onReceiveBroadcast(intent);
            }
        };

        IntentFilter filter = new IntentFilter();
        filter.addAction("zhaizhuan"); //token过期
        getActivity().registerReceiver(receiver, filter);
        //获取债转信息
        getAssigment();
        initData();
        return view;
    }

    private void onReceiveBroadcast(Intent intent) {
        String action = intent.getAction();
        if (action.equals("zhaizhuan")){
            list.clear();
            i=0;
            getAssigment();
        }
    }

    public void getAssigment() {
        showLoadingDialog();
        BizDataAsyncTask<AssignmentModel> getZhaiz = new BizDataAsyncTask<AssignmentModel>() {
            @Override
            protected AssignmentModel doExecute() throws ZYException, BizFailure {
                return InvestmentListABiz.getZhaiz(String.valueOf(i), "20", "2", tzqx, hkfs);
            }

            @Override
            protected void onExecuteSucceeded(AssignmentModel assignmentModel) {
                hideLoadingDialog();
                assignmentModels = assignmentModel;
                srlAssigment.setRefreshing(false);
                initAssigment(assignmentModel);
            }

            @Override
            protected void OnExecuteFailed(String error) {
                hideLoadingDialog();
                srlAssigment.setRefreshing(false);
            }
        };
        getZhaiz.execute();
    }


    private void initAssigment(final AssignmentModel assignmentModel) {

        if (assignmentModel.getRESULTLIST().size() > 0) {
            if (i == 0) {
                list.clear();
                list.addAll(assignmentModel.getRESULTLIST());
                assignmentAdapter = new AssignmentAdapter(list, getActivity());
                rvAssignment.setAdapter(assignmentAdapter);
                rvAssignment.setLayoutManager(linearLayoutManager);
                assignmentAdapter.notifyDataSetChanged();
            } else {
                assignmentAdapter.addData(assignmentModel.getRESULTLIST());
                assignmentAdapter.notifyDataSetChanged();
                tvZwsj.setVisibility(View.GONE);
                srlAssigment.setVisibility(View.VISIBLE);
            }
        } else {
            srlAssigment.setVisibility(View.GONE);
            tvZwsj.setVisibility(View.VISIBLE);
        }
    }
    private void initData() {
        //下拉刷新
        srlAssigment.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
//                list.clear();
                i=0;
                getAssigment();
            }
        });
        srlAssigment.setColorSchemeResources(R.color.main);
        rvAssignment.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent = new Intent(getActivity(), AssignmentDetailsActivity.class);
                intent.putExtra("zzid", list.get(position).getOIDPLATFORMPRODUCTSID());
                intent.putExtra("bili",list.get(position).getTRANSFERCAPITALSCALE());
                if (list.get(position).getTRANSFERSTATUS().equals("0")){
                    intent.putExtra("status","0");
                }else{
                    intent.putExtra("status","1");
                }
                startActivity(intent);
            }
        });
        rvAssignment.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == RecyclerView.SCROLL_STATE_IDLE
                        && lastVisibleItem + 1 == assignmentAdapter.getItemCount()) {
                    i++;
//                    srlSanbiao.setRefreshing(true);
                    getAssigment();
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                lastVisibleItem = linearLayoutManager.findLastVisibleItemPosition();
            }
        });

    }

}
