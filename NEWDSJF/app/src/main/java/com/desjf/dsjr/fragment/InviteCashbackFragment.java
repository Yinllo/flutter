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
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.desjf.dsjr.R;
import com.desjf.dsjr.adapter.InviteFanXianAdapter;
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
 * 邀请返现
 */
public class InviteCashbackFragment extends BaseFragment {


    @BindView(R.id.tv_fanxian)
    TextView fanxian;
    @BindView(R.id.tv_name)
    TextView name;
    @BindView(R.id.tv_phone)
    TextView phone;
    @BindView(R.id.tv_friends_invest)
    TextView friendsInvest;
    @BindView(R.id.srl_invite_cash_back)
    SwipeRefreshLayout srl_cashBack;
    @BindView(R.id.rv_invite_cash_back)
    RecyclerView rv_cashBack;

    private Activity mactivity;
    public View rootView;
    private int i=1;//分页
    private int lastVisibleItem;
    private LinearLayoutManager linearLayoutManager =new WrapContentLinearLayoutManager(getActivity());;
    private List<InviteModel.InviteAwardListBean> data = new ArrayList<>();
    private View notDataView;
    private InviteFanXianAdapter inviteAdapter;
    private boolean isSuccess=false;//是否获取收据成功
    private int mCurrentCounter;//当前获取到的数据总数
    private int TOTAL_COUNTER;//总数据条数

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (null == rootView) {
            rootView = inflater.inflate(R.layout.fragment_invite_cashback, null);
            ButterKnife.bind(this, rootView);

            initView();
            getData();

        }

        ViewGroup parent = (ViewGroup)rootView.getParent();
        if (parent != null) {
            parent.removeView(rootView);
        }
        return rootView;
    }

    private void initView(){
        srl_cashBack.setColorSchemeResources(R.color.main);
        srl_cashBack.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                i=1;
                getData();
            }
        });

    }

    //获得数据
    private void getData(){
        showLoadingDialog();
        CallUtil.Refreshcall(mactivity, BankHttpUtils.getHttpRequestService().getAwarding(PreferenceCache.getToken(), "2", i+"", 200+""), new CallUtil.MyCallListener() {
            @Override
            public void onRespnseSuccess(String jsonString) {
              hideLoadingDialog();
              srl_cashBack.setRefreshing(false);
                InviteModel inviteModel= JSON.parseObject(jsonString,InviteModel.class);
                List<InviteModel.InviteAwardListBean> listBeans=inviteModel.getInviteAwardList();

                initAdapter(listBeans);


            }

            @Override
            public void onRespnseFailure(Call<String> call, Throwable t) {
              hideLoadingDialog();
                srl_cashBack.setRefreshing(false);
            }
        });

    }

    //衔接适配器
    private void initAdapter(final List<InviteModel.InviteAwardListBean> data){
        if (i==1){
            inviteAdapter=new InviteFanXianAdapter(data,mactivity);
            //如果没有数据则显示 无数据
            if(data.size()==0) {
                rv_cashBack.setHasFixedSize(true);
                //初始化无数据布局
                notDataView = getActivity().getLayoutInflater().inflate(R.layout.empty, (ViewGroup) rv_cashBack.getParent(), false);
                //将无数据布局设置到适配器中
                inviteAdapter.setEmptyView(notDataView);
            }
            rv_cashBack.setAdapter(inviteAdapter);
            rv_cashBack.setLayoutManager(linearLayoutManager);
            inviteAdapter.notifyDataSetChanged();
        }

    }



    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.mactivity= (Activity) context;
    }
}
