package com.desjf.dsjr.fragment.bank;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.desjf.dsjr.R;
import com.desjf.dsjr.adapter.bankAdapter.BankJiangHuiKuanAdapter;
import com.desjf.dsjr.base.BaseFragment;
import com.desjf.dsjr.biz.retrofit.BankHttpUtils;
import com.desjf.dsjr.biz.retrofit.CallUtil;
import com.desjf.dsjr.model.bankModel.BankJiangHuiKuanModel;
import com.desjf.dsjr.utils.PreferenceCache;
import com.desjf.dsjr.widget.WrapContentLinearLayoutManager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;

/**
 * A simple {@link Fragment} subclass.
 * 银行存管 将回款
 */
public class BankJiangHuiKuanFragment extends BaseFragment {
    @BindView(R.id.tv_money)
    TextView money;
    @BindView(R.id.tv_rate)
    TextView rate;
    @BindView(R.id.rv_huikuan)
    RecyclerView rvHuikuan;
    @BindView(R.id.srl_huikuan)
    SwipeRefreshLayout srlHuikuan;
    private Context context = getActivity();
    private BankJiangHuiKuanAdapter huiKuanAdapter;
    private List<BankJiangHuiKuanModel.UserRecoverListBean> myInvest_list = new ArrayList<>();
    private int i = 1;//当前页数
    int maxCount=150;//每次加载的记录条数
    int totalNum=0;//记录总条数
    private int lastVisibleItem;
    BankJiangHuiKuanModel bankHuikuanModel;
    private LinearLayoutManager  linearLayoutManager = new WrapContentLinearLayoutManager(getActivity());;
    private View notDataView;
    private View rootView;
    boolean ifRefresh=false;//记录是否刷新
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
//        getInvest();
//        View view = inflater.inflate(R.layout.fragment_bank_jiang_hui_kuan, container, false);
//        ButterKnife.bind(this, view);
//        initData();
//        Log.e("aaaa","将回款");
//        return view;
        if (null == rootView) {
            rootView = inflater.inflate(R.layout.fragment_bank_jiang_hui_kuan, null);
            ButterKnife.bind(this, rootView);
            initData();
            getInvest();
        }

        ViewGroup parent = (ViewGroup)rootView.getParent();
        if (parent != null) {
            parent.removeView(rootView);
        }

        return rootView;
    }

    private void initData() {
        srlHuikuan.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                i=1;
                ifRefresh=true;
                getInvest();
            }
        });
        srlHuikuan.setColorSchemeResources(R.color.main);
        //下拉加载更多
        rvHuikuan.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == RecyclerView.SCROLL_STATE_IDLE
                        && lastVisibleItem + 1 == huiKuanAdapter.getItemCount()&& huiKuanAdapter.getItemCount()>=149) {
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
                .queryUserRecover(PreferenceCache.getToken(),i+"",maxCount+""), new CallUtil.MyCallListener() {
                    @Override
                    public void onRespnseSuccess(String jsonString) {
                        hideLoadingDialog();
                        srlHuikuan.setRefreshing(false);
                        bankHuikuanModel= JSON.parseObject(jsonString,BankJiangHuiKuanModel.class);
                        if (bankHuikuanModel.getUserRecoverList()!=null) {
                            totalNum=bankHuikuanModel.getCounts();
                            money.setText("累计将回本金(元)："+bankHuikuanModel.getUserRecoverMapToList().get(0).getRECOVER_AMOUNT_CAPITAL());
                            rate.setText("累计将回利息(元)："+bankHuikuanModel.getUserRecoverMapToList().get(1).getRECOVER_AMOUNT_INTEREST());

                        }
                        initHuikuan(bankHuikuanModel.getUserRecoverList());

                    }

                    @Override
                    public void onRespnseFailure(Call<String> call, Throwable t) {
                        srlHuikuan.setRefreshing(false);
                        hideLoadingDialog();
                    }
                });


    }

    private void initHuikuan(final List<BankJiangHuiKuanModel.UserRecoverListBean> myInvestModels) {
        if(ifRefresh){
            myInvest_list.clear();
        }
        myInvest_list.addAll(myInvestModels);
        if (i==1){
            huiKuanAdapter = new BankJiangHuiKuanAdapter(myInvest_list, getActivity());
            //如果没有数据则显示 无数据
            if(myInvestModels.size()==0) {
                rvHuikuan.setHasFixedSize(true);
                //初始化无数据布局
                notDataView = getActivity().getLayoutInflater().inflate(R.layout.empty, (ViewGroup) rvHuikuan.getParent(), false);
                //将无数据布局设置到适配器中
                huiKuanAdapter.setEmptyView(notDataView);
            }
            rvHuikuan.setAdapter(huiKuanAdapter);
            rvHuikuan.setLayoutManager(linearLayoutManager);
            huiKuanAdapter.notifyDataSetChanged();


        }else{
            huiKuanAdapter.addData(myInvestModels);
            huiKuanAdapter.notifyDataSetChanged();
        }

        huiKuanAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                switch (view.getId()) {
//                    case R.id.tv_cyz_hkxq:
//                Intent intent = new Intent(getActivity(), WodeInvestDetailsActivity.class);
//                intent.putExtra("kind", 2);
//                intent.putExtra("details", myInvest_list.get(position));
//                intent.putExtra("tag", 1);
//                startActivity(intent);
//                        break;
                    case R.id.find:

                        break;
                }
            }
        });}
    //计算最大页数
    private int getMaxPageNum(int totalNumber,int maxCount){
        return (totalNumber%maxCount==0)?(totalNumber/maxCount):(totalNumber/maxCount+1);
    }
}
