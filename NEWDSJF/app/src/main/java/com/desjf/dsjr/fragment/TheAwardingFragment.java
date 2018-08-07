package com.desjf.dsjr.fragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alibaba.fastjson.JSON;
import com.desjf.dsjr.R;
import com.desjf.dsjr.adapter.InviteAdapter;
import com.desjf.dsjr.base.BaseFragment;
import com.desjf.dsjr.biz.retrofit.BankHttpUtils;
import com.desjf.dsjr.biz.retrofit.CallUtil;
import com.desjf.dsjr.model.InviteModel;
import com.desjf.dsjr.utils.PreferenceCache;
import com.desjf.dsjr.widget.WrapContentLinearLayoutManager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;

/**
 * 2018-5-14  YinL
 * 红包发放
 */
public class TheAwardingFragment extends BaseFragment {

    @BindView(R.id.srl_the_awarding)
    SwipeRefreshLayout srl_awarding;
    @BindView(R.id.rv_the_awarding)
    RecyclerView rv_awarding;

    private Activity mactivity;

    protected View rootView;
    private int i=1;//分页
    private int lastVisibleItem;
    private LinearLayoutManager linearLayoutManager =new WrapContentLinearLayoutManager(getActivity());;
    private List<InviteModel.InviteAwardListBean> data = new ArrayList<>();
    private View notDataView;
    private InviteAdapter inviteAdapter;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if(null==rootView){
            rootView=inflater.inflate(R.layout.fragment_the_awarding,container,false);
            ButterKnife.bind(this,rootView);

            initView();
            initData();

        }

        ViewGroup parent= (ViewGroup) rootView.getParent();
        if(parent!=null){
            parent.removeView(rootView);
        }

        return rootView;

    }
    private void initView(){
        srl_awarding.setColorSchemeResources(R.color.main);
        srl_awarding.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                i=1;
                initData();
            }
        });

    }
    private void initData(){
        showLoadingDialog();
        CallUtil.Refreshcall(mactivity,BankHttpUtils.getHttpRequestService().getAwarding(PreferenceCache.getToken(),"1",i+"",200+""), new CallUtil.MyCallListener() {
            @Override
            public void onRespnseSuccess(String jsonString) {
                hideLoadingDialog();
                srl_awarding.setRefreshing(false);
                InviteModel inviteModel= JSON.parseObject(jsonString,InviteModel.class);

                List<InviteModel.InviteAwardListBean> listBeans=inviteModel.getInviteAwardList();

                initAdapter(listBeans);

            }

            @Override
            public void onRespnseFailure(Call<String> call, Throwable t) {
                hideLoadingDialog();
                srl_awarding.setRefreshing(false);
            }
        });
    }

    private void initAdapter(List<InviteModel.InviteAwardListBean> data){
        if (i==1){
            inviteAdapter=new InviteAdapter(data,mactivity);
            //如果没有数据则显示 无数据
            if(data.size()==0) {
                rv_awarding.setHasFixedSize(true);
                //初始化无数据布局
                notDataView = getActivity().getLayoutInflater().inflate(R.layout.empty, (ViewGroup) rv_awarding.getParent(), false);
                //将无数据布局设置到适配器中
                inviteAdapter.setEmptyView(notDataView);
            }
            rv_awarding.setAdapter(inviteAdapter);
            rv_awarding.setLayoutManager(linearLayoutManager);
            inviteAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.mactivity= (Activity) context;
    }
}
