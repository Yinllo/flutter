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
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.desjf.dsjr.R;
import com.desjf.dsjr.adapter.bankAdapter.BankInvestmentAdapter;
import com.desjf.dsjr.base.BaseFragment;
import com.desjf.dsjr.biz.retrofit.BankHttpUtils;
import com.desjf.dsjr.biz.retrofit.CallUtil;
import com.desjf.dsjr.model.bankModel.BankInvestmentModel;
import com.desjf.dsjr.utils.PreferenceCache;
import com.desjf.dsjr.widget.WrapContentLinearLayoutManager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;

/**
 * 银行持有标   历史投资
 */
public class BankChiyouFragment extends BaseFragment {
    @BindView(R.id.tv_all_money)
    TextView allMoney;
    @BindView(R.id.rv_chiyou)
    RecyclerView rvChiyou;
    @BindView(R.id.srl_chiyou)
    SwipeRefreshLayout srlChiyou;
    private Context context = getActivity();
    private BankInvestmentAdapter myCyzAdapter;
    private List<BankInvestmentModel.UserTenderListBean> myInvestModel = new ArrayList<>();
    private int i = 1;//当前页数
    int maxCount=150;//每次加载的记录条数
    int totalNum=0;//记录总条数
    private int lastVisibleItem;
    private LinearLayoutManager linearLayoutManager = new WrapContentLinearLayoutManager(getActivity());;
    private String status="";
    private View notDataView;
    private View rootView;
    boolean ifRefresh=false;//记录是否刷新
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //        View view = inflater.inflate(R.layout.fragment_bank_chiyou, container, false);
        //        ButterKnife.bind(this, view);
        //        getInvest();
        //        initData();
        //
        //        return view;

        if (null == rootView) {
            rootView = inflater.inflate(R.layout.fragment_bank_chiyou, null);
            ButterKnife.bind(this, rootView);
            getInvest();
            initData();
        }

        ViewGroup parent = (ViewGroup)rootView.getParent();
        if (parent != null) {
            parent.removeView(rootView);
        }

        return rootView;



    }

    private void initData() {
        srlChiyou.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                i=1;
                ifRefresh=true;
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
                        && lastVisibleItem + 1 == myCyzAdapter.getItemCount()&& myCyzAdapter.getItemCount()>=149) {
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
                .queryUserTenderYes(PreferenceCache.getToken(),i+"",maxCount+""),new CallUtil.MyCallListener() {
            @Override
            public void onRespnseSuccess(String jsonString) {
                hideLoadingDialog();
                srlChiyou.setRefreshing(false);
                BankInvestmentModel bankInvestmentModel= JSON.parseObject(jsonString,BankInvestmentModel.class);
                if (bankInvestmentModel.getUserTenderList()!=null) {
                    totalNum=bankInvestmentModel.getCounts();
                    allMoney.setText("目前共冻结本金(元)："+bankInvestmentModel.getUserRecoverMapToList().get(0).getTENDER_AMOUNT());
                }

                initZyc(bankInvestmentModel.getUserTenderList());

            }

            @Override
            public void onRespnseFailure(Call<String> call, Throwable t) {
              hideLoadingDialog();
                srlChiyou.setRefreshing(false);
            }
        });
    }

    private void initZyc(final List<BankInvestmentModel.UserTenderListBean> myInvestModels) {
        //如果没有数据则显示 无数据
        if(ifRefresh){
            myInvestModel.clear();
        }
        myInvestModel.addAll(myInvestModels);
        if (i==1){
            myCyzAdapter = new BankInvestmentAdapter(myInvestModel, getActivity());
            //如果没有数据则显示 无数据
            if(myInvestModels.size()==0) {
                rvChiyou.setHasFixedSize(true);
                //初始化无数据布局
                notDataView = getActivity().getLayoutInflater().inflate(R.layout.empty, (ViewGroup) rvChiyou.getParent(), false);
                //将无数据布局设置到适配器中
                myCyzAdapter.setEmptyView(notDataView);
            }
            rvChiyou.setAdapter(myCyzAdapter);
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
//                    case R.id.tv_cyz_hkxq:
//                    Intent intent = new Intent(getActivity(), WodeInvestDetailsActivity.class);
//                    intent.putExtra("kind", 1);
//                    intent.putExtra("details", myInvestModel.get(position));
//                    intent.putExtra("tag", 0);
//                    startActivity(intent);
//                    break;
                    case R.id.find:

                        break;
                }
            }
        });

    }
    //计算最大页数
    private int getMaxPageNum(int totalNumber,int maxCount){
        return (totalNumber%maxCount==0)?(totalNumber/maxCount):(totalNumber/maxCount+1);
    }
}
