package com.desjf.dsjr.fragment;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.desjf.dsjr.R;
import com.desjf.dsjr.activity.BankWebViewActivity;
import com.desjf.dsjr.activity.LoginActivity;
import com.desjf.dsjr.activity.NewInvestmentDetailsActivity;
import com.desjf.dsjr.activity.ProjectDetailsActivity;
import com.desjf.dsjr.adapter.TouzjlAdapter;
import com.desjf.dsjr.base.BaseFragment;
import com.desjf.dsjr.bean.NewRegBean;
import com.desjf.dsjr.biz.retrofit.BankHttpUtil;
import com.desjf.dsjr.biz.retrofit.CallUtil;
import com.desjf.dsjr.biz.retrofit.CallUtils;
import com.desjf.dsjr.biz.retrofit.InitHttpUtil;
import com.desjf.dsjr.model.ProjectInfoModel;
import com.desjf.dsjr.model.bankModel.BankInvestRecordModel;
import com.desjf.dsjr.utils.PreferenceCache;
import com.lcodecore.tkrefreshlayout.RefreshListenerAdapter;
import com.lcodecore.tkrefreshlayout.TwinklingRefreshLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;

/**
 * A simple {@link Fragment} subclass.
 */
public class InvestRecordFragment extends BaseFragment {
    @BindView(R.id.rv_zouzijilu)
    RecyclerView rvZouzijilu;
    @BindView(R.id.tv_empty)
    TextView tvEmpty;
    @BindView(R.id.tv_details_login)
    TextView tvDetailsLogin;
    private String id;
    private TouzjlAdapter touziAdapter;
    private LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
    private ProjectInfoModel projectInfoModel;
    private TwinklingRefreshLayout twinklingRefreshLayout;
    private int firstIdx = 0;
    private List<BankInvestRecordModel.InvestorListBean> list;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_invest_record, container, false);
        ButterKnife.bind(this, view);

        twinklingRefreshLayout = view.findViewById(R.id.refreshlayout);
        twinklingRefreshLayout.setEnableRefresh(false);
        twinklingRefreshLayout.setFloatRefresh(false);
        twinklingRefreshLayout.setOverScrollRefreshShow(false);
        twinklingRefreshLayout.setEnableLoadmore(true);
        twinklingRefreshLayout.setOnRefreshListener(new RefreshListenerAdapter() {

            @Override
            public void onLoadMore(TwinklingRefreshLayout refreshLayout) {
                super.onLoadMore(refreshLayout);
                firstIdx++;
                getRecord();
            }
        });

        list=new ArrayList<>();
        touziAdapter = new TouzjlAdapter(list, getActivity());
        rvZouzijilu.setAdapter(touziAdapter);
        rvZouzijilu.setLayoutManager(linearLayoutManager);
        getRecord();
        initData();

        return view;
    }

    private void initData() {
        if (PreferenceCache.getToken().isEmpty()) {
            tvDetailsLogin.setText("立即登录");
            tvDetailsLogin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (PreferenceCache.getToken().isEmpty()) {
                        Intent i = new Intent(getActivity(), LoginActivity.class);
                        startActivity(i);
                    } else {
//                        if (projectInfoModel.getID_CARD_VERIFY_FLG().equals("0")) {
//                            //未实名认证
//                            Intent in = new Intent(getActivity(), AccountRealNameActivity.class);
//                            startActivity(in);
//                        } else {
//                            if (projectInfoModel.getID_CARD_VERIFY_FLG().equals("0")) {
//                                goBank();
//                            } else {
                                Intent i = new Intent(getActivity(), NewInvestmentDetailsActivity.class);
                                i.putExtra("Info", projectInfoModel);
                                i.putExtra("ID", id);
                                startActivity(i);
//                            }
                        }
//                    }
                }
            });
        } else {
            if (projectInfoModel.getPRODUCTS_STATUS().toString().equals("未开始")) {
                tvDetailsLogin.setText("即将发布");
                tvDetailsLogin.setClickable(false);
            } else if (projectInfoModel.getPRODUCTS_STATUS().toString().equals("立即投资")) {
                tvDetailsLogin.setText("立即投资");
                tvDetailsLogin.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (PreferenceCache.getToken().isEmpty()) {
                            Intent i = new Intent(getActivity(), LoginActivity.class);
                            startActivity(i);
                        } else {
//                                if (projectInfoModel.getID_CARD_VERIFY_FLG().equals("0")) {
//                                 goBank();
//                                } else {
                                    Intent i = new Intent(getActivity(), NewInvestmentDetailsActivity.class);
                                  i.putExtra("Info", projectInfoModel);
                                    i.putExtra("ID", id);
                                    startActivity(i);

//                            }
                        }
                    }
                });
            } else if (projectInfoModel.getPRODUCTS_STATUS().toString().equals("满标待审")) {
                tvDetailsLogin.setText("已满标");
                tvDetailsLogin.setClickable(false);
                tvDetailsLogin.setBackgroundColor(getResources().getColor(R.color.navy_gray));
            } else if (projectInfoModel.getPRODUCTS_STATUS().toString().equals("已完成")) {
                tvDetailsLogin.setText("已还清");
                tvDetailsLogin.setClickable(false);
                tvDetailsLogin.setBackgroundColor(getResources().getColor(R.color.navy_gray));
            } else if (projectInfoModel.getPRODUCTS_STATUS().toString().equals("还款中")) {
                tvDetailsLogin.setText("收益中");
                tvDetailsLogin.setClickable(false);
                tvDetailsLogin.setBackgroundColor(getResources().getColor(R.color.navy_gray));
            }

        }

    }

    private void getRecord() {
        showLoadingDialog();
        CallUtil.call(getActivity(), InitHttpUtil.getHttpRequestService().listInvestor(id, String.valueOf(firstIdx), "50"), new CallUtil.MyCallListener() {
            @Override
            public void onRespnseSuccess(String jsonString) {
                BankInvestRecordModel investRecordModel=JSON.parseObject(jsonString,BankInvestRecordModel.class);
                hideLoadingDialog();
                twinklingRefreshLayout.finishLoadmore();
                if (investRecordModel.getInvestorList().size()!=0) {
                    initRecrod(investRecordModel.getInvestorList());
                }

            }

            @Override
            public void onRespnseFailure(Call<String> call, Throwable t) {
                hideLoadingDialog();
                twinklingRefreshLayout.finishLoadmore();
            }
        });

    }

    private void initRecrod(List<BankInvestRecordModel.InvestorListBean> investorsModels) {
        list.addAll(investorsModels);
        touziAdapter.notifyDataSetChanged();
//        if (investorsModels.size() != 0) {
//            touziAdapter = new TouzjlAdapter(investorsModels, getActivity());
//            rvZouzijilu.setAdapter(touziAdapter);
//            rvZouzijilu.setLayoutManager(linearLayoutManager);
//            touziAdapter.notifyDataSetChanged();
//            tvEmpty.setVisibility(View.GONE);
//        } else {
//            tvEmpty.setVisibility(View.VISIBLE);
//            rvZouzijilu.setVisibility(View.GONE);
//        }


    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        id = ((ProjectDetailsActivity) activity).getId();
        projectInfoModel = ((ProjectDetailsActivity) activity).getProjectInfoModel();
    }
    private void goBank(){
        //前往开通存管
        CallUtils.call(getActivity(), BankHttpUtil.getHttpRequestService().bankReg(projectInfoModel.getMOBILE()), new CallUtils.MyCallListener() {
            @Override
            public void onRespnseSuccess(String jsonString) {
                NewRegBean newRegBean= JSON.parseObject(jsonString,NewRegBean.class);
                Intent i=new Intent(getActivity(),BankWebViewActivity.class);
                i.putExtra("type",1);
                i.putExtra("newRegBean",newRegBean);
                startActivity(i);

            }
        });
    }

}
