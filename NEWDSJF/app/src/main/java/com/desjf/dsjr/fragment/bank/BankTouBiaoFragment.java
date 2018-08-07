package com.desjf.dsjr.fragment.bank;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.desjf.dsjr.R;
import com.desjf.dsjr.adapter.bankAdapter.BankFreezeAdapter;
import com.desjf.dsjr.base.BaseFragment;
import com.desjf.dsjr.biz.retrofit.BankHttpUtils;
import com.desjf.dsjr.biz.retrofit.CallUtil;
import com.desjf.dsjr.model.bankModel.BankFreezeModel;
import com.desjf.dsjr.utils.PreferenceCache;
import com.desjf.dsjr.widget.WrapContentLinearLayoutManager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;

/**
 * 银行存管投标中  冻结（未满标）
 */
public class BankTouBiaoFragment extends BaseFragment {
    @BindView(R.id.tv_dongjie)
    TextView money;
    @BindView(R.id.rv_toubiao)
    RecyclerView rvToubiao;
    @BindView(R.id.srl_refresh)
    SwipeRefreshLayout srlRefresh;
    private Context context = getActivity();
    private BankFreezeAdapter myTouBiaoAdapter;
    private int i = 1;//当前页数
    int maxCount=150;//每次加载的记录条数
    int totalNum=0;//记录总条数
    private int lastVisibleItem;
    private LinearLayoutManager linearLayoutManager =new WrapContentLinearLayoutManager(getActivity());;
    private List<BankFreezeModel.UserTenderListBean> myInvestModel = new ArrayList<>();
    private View notDataView;
    private View rootView;
    boolean ifRefresh=false;//记录是否刷新
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
//        View view = inflater.inflate(R.layout.fragment_bank_tou_biao, container, false);
//        ButterKnife.bind(this, view);
//        initView();
//        getInvest();
//        return view;
        if (null == rootView) {
            rootView = inflater.inflate(R.layout.fragment_bank_tou_biao, null);
            ButterKnife.bind(this, rootView);
            initView();
            getInvest();
        }

        ViewGroup parent = (ViewGroup)rootView.getParent();
        if (parent != null) {
            parent.removeView(rootView);
        }

        return rootView;

    }

    private void initView() {
        srlRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                i=1;
                ifRefresh=true;
                getInvest();
            }
        });
        srlRefresh.setColorSchemeResources(R.color.main);
        rvToubiao.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == RecyclerView.SCROLL_STATE_IDLE
                        && lastVisibleItem + 1 == myTouBiaoAdapter.getItemCount()&& myTouBiaoAdapter.getItemCount()>=149) {
                    //如果当前i小于总页数，则加载更多
                    if(i<=getMaxPageNum(totalNum,maxCount)) {
                        i++;
                        ifRefresh=false;
                        getInvest();
                    }
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
        CallUtil.Refreshcall(getActivity(), BankHttpUtils.getHttpRequestService()
                .queryUserTender(PreferenceCache.getToken(),i+"",maxCount+""),new CallUtil.MyCallListener() {
            @Override
            public void onRespnseSuccess(String jsonString) {
                hideLoadingDialog();
                srlRefresh.setRefreshing(false);

                BankFreezeModel bankFreezeModel= JSON.parseObject(jsonString,BankFreezeModel.class);

                if (bankFreezeModel.getUserTenderList()!=null) {
                    money.setText("目前共冻结本金(元)："+bankFreezeModel.getUserRecoverMapToList().get(0).getTENDER_AMOUNT());
                    totalNum=bankFreezeModel.getCounts();
                }

                initToubiao(bankFreezeModel.getUserTenderList());

            }

            @Override
            public void onRespnseFailure(Call<String> call, Throwable t) {
                hideLoadingDialog();
                srlRefresh.setRefreshing(false);
            }
        });
    }

    private void initToubiao(List<BankFreezeModel.UserTenderListBean> myInvestModels) {
        if(ifRefresh){
            myInvestModel.clear();
        }
        myInvestModel.addAll(myInvestModels);
        if (i==1){
            myTouBiaoAdapter = new BankFreezeAdapter(myInvestModels, getActivity());
            //如果没有数据则显示 无数据
            if(myInvestModels.size()==0) {
                rvToubiao.setHasFixedSize(true);
                //初始化无数据布局
                notDataView = getActivity().getLayoutInflater().inflate(R.layout.empty, (ViewGroup) rvToubiao.getParent(), false);
                //将无数据布局设置到适配器中
                myTouBiaoAdapter.setEmptyView(notDataView);
            }
            rvToubiao.setAdapter(myTouBiaoAdapter);
            rvToubiao.setLayoutManager(linearLayoutManager);
            myTouBiaoAdapter.notifyDataSetChanged();
        }else{
            myTouBiaoAdapter.addData(myInvestModels);
            myTouBiaoAdapter.notifyDataSetChanged();
        }

    }
    //计算最大页数
    private int getMaxPageNum(int totalNumber,int maxCount){
        return (totalNumber%maxCount==0)?(totalNumber/maxCount):(totalNumber/maxCount+1);
    }
}
