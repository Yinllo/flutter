package com.desjf.dsjr.fragment.invest;


import android.annotation.SuppressLint;
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
import android.widget.Button;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.desjf.dsjr.R;
import com.desjf.dsjr.activity.StandarDetailsActivity;
import com.desjf.dsjr.adapter.StandardAdapter;
import com.desjf.dsjr.base.BaseFragment;
import com.desjf.dsjr.bean.ShouyeListBean;
import com.desjf.dsjr.biz.InvestmentListABiz;
import com.desjf.dsjr.biz.exception.BizFailure;
import com.desjf.dsjr.biz.exception.ZYException;
import com.desjf.dsjr.biz.task.BizDataAsyncTask;
import com.desjf.dsjr.config.DsjrConfig;
import com.desjf.dsjr.model.StanderdModel;
import com.desjf.dsjr.widget.WrapContentLinearLayoutManager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 * 散标Fragment
 */
public class StandardpowderFragment extends BaseFragment {
    @BindView(R.id.srl_sanbiao)
    SwipeRefreshLayout srlSanbiao;
    private List<ShouyeListBean> slb_list = new ArrayList<>();
    @BindView(R.id.rv_standerpower)
    RecyclerView rvStanderpower;
    @BindView(R.id.top_btn)
    Button toTop;

    private  StandardAdapter standarAdapter;
    private StanderdModel standerModel;
    private String tzqx="";
    private String hkfs="";
    private int i=0;
    private int lastVisibleItem;
//    private  LinearLayoutManager linearLayoutManager  = new WrapContentLinearLayoutManager(getActivity());
//    private LinearLayoutManager layout = new FastScrollManger(getActivity(), LinearLayoutManager.VERTICAL, false);
private LinearLayoutManager layout = new WrapContentLinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);

    private List<StanderdModel.RESULTLISTBean> list = new ArrayList<>();
    private BroadcastReceiver receiver;//通知刷新广播
    public List<StanderdModel.RESULTLISTBean> getList() {
        return list;
    }

    public void setList(List<StanderdModel.RESULTLISTBean> list) {
        this.list = list;
    }

    public String getTzqx() {
        return tzqx;
    }

    public void setTzqx(String tzqx) {
        this.tzqx = tzqx;
    }

    public String getHkfs() {
        return hkfs;
    }

    public void setHkfs(String hkfs) {
        this.hkfs = hkfs;
    }

    public StandardAdapter getStandarAdapter() {
        return standarAdapter;
    }

    public void setStandarAdapter(StandardAdapter standarAdapter) {
        this.standarAdapter = standarAdapter;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_standardpowder, container, false);
        ButterKnife.bind(this, view);
        //注册广播
        receiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                onReceiveBroadcast(intent);
            }
        };

        IntentFilter filter = new IntentFilter();
        filter.addAction("shuaxin"); //token过期
        getActivity().registerReceiver(receiver, filter);
        DsjrConfig.TYPE=10;
        //获取散标数据
        getSanB();
        initData();
        buttonlistener();
        return view;
    }
    //接收广播
    private void onReceiveBroadcast(Intent intent) {
        String action = intent.getAction();
        if (action.equals("shuaxin")){
//            list.clear();
            i=0;
            getSanB();
//            return;
//            getActivity().unregisterReceiver(receiver);
        }
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (hidden){
            return;
        }else{
            list.clear();
            getSanB();
        }
    }


    private  void buttonlistener(){
        toTop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rvStanderpower.smoothScrollToPosition(0);
            }
        });
    }

    private void initData() {


        srlSanbiao.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                srlSanbiao.setRefreshing(false);
//                list.clear();
                i=0;
                getSanB();
            }
        });
        srlSanbiao.setColorSchemeResources(R.color.main);
        rvStanderpower.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent = new Intent(getActivity(), StandarDetailsActivity.class);
                intent.putExtra("key", list.get(position).getOIDPLATFORMPRODUCTSID());
                intent.putExtra("tender", list.get(position).getTENDERAWARDSCALE());
                intent.putExtra("status", list.get(position).getPRODUCTSSTATUS());
                startActivity(intent);
            }
        });
        //下拉加载更多
        rvStanderpower.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == RecyclerView.SCROLL_STATE_IDLE
                        && lastVisibleItem + 1 == standarAdapter.getItemCount()) {
                    i++;
                    getSanB();
                }

                //11.9新增
                LinearLayoutManager manager = (LinearLayoutManager) recyclerView.getLayoutManager();
                int firstVisibleItemPosition = manager.findFirstVisibleItemPosition();
                // 当不滚动时
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    // 判断是否滚动超过一屏
                    if (firstVisibleItemPosition == 0) {
                        toTop.setVisibility(View.INVISIBLE);
                    } else {
                        toTop.setVisibility(View.VISIBLE);
                    }

                } else if (newState == RecyclerView.SCROLL_STATE_DRAGGING) {//拖动中
                    toTop.setVisibility(View.INVISIBLE);
                }
            }




            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                lastVisibleItem = layout.findLastVisibleItemPosition();

            }
        });

//        rvStanderpower.addOnScrollListener(new MyRecyclerViewScrollListener(toTop));



    }

    public  void getSanB() {
        showLoadingDialog();
        @SuppressLint("StaticFieldLeak") BizDataAsyncTask<StanderdModel> getList = new BizDataAsyncTask<StanderdModel>() {
            @Override
            protected StanderdModel doExecute() throws ZYException, BizFailure {
                return InvestmentListABiz.getInvestmentList(String.valueOf(i), "20", "1", tzqx, hkfs);
            }

            @Override
            protected void onExecuteSucceeded(StanderdModel standerModels) {
                hideLoadingDialog();
                standerModel = standerModels;
                initStandar(standerModels);
                srlSanbiao.setRefreshing(false);

            }

            @Override
            protected void OnExecuteFailed(String error) {
                hideLoadingDialog();
                srlSanbiao.setRefreshing(false);
            }
        };
        getList.execute();
    }

    private void initStandar(final StanderdModel standerModels) {

        if (i==0){
            list.clear();
            list.addAll(standerModels.getRESULTLIST());
            standarAdapter = new StandardAdapter(list, getActivity());
//            rvStanderpower.setLayoutManager(linearLayoutManager);
//            LinearLayoutManager layout = new FastScrollManger(getActivity(), LinearLayoutManager.VERTICAL, false);
            rvStanderpower.setLayoutManager(layout);//竖直放置
            rvStanderpower.setAdapter(standarAdapter);

            standarAdapter.notifyDataSetChanged();
        }else{
            standarAdapter.addData(standerModels.getRESULTLIST());
            standarAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        getActivity().unregisterReceiver(receiver);
    }

    @Override
    public void onResume() {
        super.onResume();

    }
}
