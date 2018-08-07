package com.desjf.dsjr.activity;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.desjf.dsjr.R;
import com.desjf.dsjr.adapter.MyFundDetailsAdapter;
import com.desjf.dsjr.base.BaseActivity;
import com.desjf.dsjr.biz.JyRecordBiz;
import com.desjf.dsjr.biz.exception.BizFailure;
import com.desjf.dsjr.biz.exception.ZYException;
import com.desjf.dsjr.biz.task.BizDataAsyncTask;
import com.desjf.dsjr.model.JyRecordModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 资金明细
 */

public class AccountFundDetailsActivity extends BaseActivity {
    @BindView(R.id.tv_account_choose)
    TextView tvAccountChoose;
    @BindView(R.id.rl_zjmx)
    RelativeLayout rlZjmx;
    @BindView(R.id.srl_zjmx)
    SwipeRefreshLayout srlZjmx;
    @BindView(R.id.iv_fund_details_back)
    ImageView ivFundDetailsBack;
    @BindView(R.id.tv_zwsj)
    TextView tvZwsj;
    private List<String> str_list = new ArrayList<>();
    private MyFundDetailsAdapter myFundDetailsAdapter;
    @BindView(R.id.rv_account_fund_details)
    RecyclerView rvAccountFundDetails;
    private Context context = this;
    private boolean isOpen = true;
    private PopupWindow mPopupWindow;
    private String str = "";
    private int i = 0;
    private List<JyRecordModel> jyRecordModelList = new ArrayList<>();
    private int lastVisibleItem;
    private LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_fund_details);
        ButterKnife.bind(this);
        //获取交易记录信息
        getJyjl();
        initData();
    }

    private void getJyjl() {
        showLoadingDialog();
        BizDataAsyncTask<List<JyRecordModel>> getJy = new BizDataAsyncTask<List<JyRecordModel>>() {
            @Override
            protected List<JyRecordModel> doExecute() throws ZYException, BizFailure {
                return JyRecordBiz.getRecord(String.valueOf(i), "20", str);
            }

            @Override
            protected void onExecuteSucceeded(List<JyRecordModel> jyRecordModels) {
                hideLoadingDialog();
                srlZjmx.setRefreshing(false);
                //处理交易记录逻辑
                initJy(jyRecordModels);
            }

            @Override
            protected void OnExecuteFailed(String error) {
                hideLoadingDialog();
                srlZjmx.setRefreshing(false);
            }
        };
        getJy.execute();
    }

    private void initJy(List<JyRecordModel> jyRecordModels) {
        jyRecordModelList.addAll(jyRecordModels);
        if (jyRecordModelList.size()!=0) {
            if (i == 0) {
                myFundDetailsAdapter = new MyFundDetailsAdapter(jyRecordModelList, this);
                rvAccountFundDetails.setAdapter(myFundDetailsAdapter);
                rvAccountFundDetails.setLayoutManager(linearLayoutManager);
                myFundDetailsAdapter.notifyDataSetChanged();
            } else {
                myFundDetailsAdapter.addData(jyRecordModels);
                myFundDetailsAdapter.notifyDataSetChanged();
            }
            tvZwsj.setVisibility(View.GONE);
            srlZjmx.setVisibility(View.VISIBLE);
        } else {
            srlZjmx.setVisibility(View.GONE);
            tvZwsj.setVisibility(View.VISIBLE);
        }


    }

    private void initData() {
        mPopupWindow = new PopupWindow(this);
        srlZjmx.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                i = 0;
                jyRecordModelList.clear();
                getJyjl();
            }
        });
        srlZjmx.setColorSchemeResources(R.color.main);

//        rvAccountFundDetails.setOnScrollListener(new RecyclerView.OnScrollListener() {
//            @Override
//            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
//                super.onScrollStateChanged(recyclerView, newState);
//                if (newState == RecyclerView.SCROLL_STATE_IDLE
//                        && lastVisibleItem + 1 == myFundDetailsAdapter.getItemCount() ) {
//                    i++;
//                    getJyjl();
//                }
//            }
//
//            @Override
//            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
//                super.onScrolled(recyclerView, dx, dy);
//                lastVisibleItem = linearLayoutManager.findLastVisibleItemPosition();
//            }
//        });
    }

    @OnClick({R.id.tv_account_choose, R.id.iv_fund_details_back})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_account_choose:
                //筛选按钮,弹出POP
                if (isOpen) {
                    showPopup();
                    tvAccountChoose.setText("取消");
                    isOpen = !isOpen;
                } else {
                    mPopupWindow.dismiss();
                    tvAccountChoose.setText("筛选");
                    isOpen = !isOpen;
                }
                break;
            case R.id.iv_fund_details_back:
                finish();
                break;
        }


    }

    private void showPopup() {
        View contentView = LayoutInflater.from(context).inflate(R.layout.item_popup_zjmx, null);
        ColorDrawable dw = new ColorDrawable(0xb0000000);
        mPopupWindow.setContentView(contentView);
        mPopupWindow.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        mPopupWindow.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        mPopupWindow.setBackgroundDrawable(dw);
        mPopupWindow.showAsDropDown(rlZjmx);
        final TextView quanbu = (TextView) contentView.findViewById(R.id.tv_zjmx_qb);
        final TextView chongzhi = (TextView) contentView.findViewById(R.id.tv_zjmx_chongzhi);
        final TextView tixian = (TextView) contentView.findViewById(R.id.tv_zjmx_tixian);
        final TextView touzi = (TextView) contentView.findViewById(R.id.tv_zjmx_tz);
        final TextView huikuan = (TextView) contentView.findViewById(R.id.tv_zjmx_huikuan);
        final TextView qita = (TextView) contentView.findViewById(R.id.tv_zjmx_qita);
        quanbu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                jyRecordModelList.clear();
                str = "";
                tvAccountChoose.setText("筛选");
                isOpen = !isOpen;
                quanbu.setTextColor(getResources().getColor(R.color.white));
                quanbu.setBackgroundResource(R.drawable.text_pop_blue);
                chongzhi.setTextColor(getResources().getColor(R.color.popfont));
                chongzhi.setBackgroundResource(R.drawable.text_pop_huibai);
                tixian.setTextColor(getResources().getColor(R.color.popfont));
                tixian.setBackgroundResource(R.drawable.text_pop_huibai);
                touzi.setTextColor(getResources().getColor(R.color.popfont));
                touzi.setBackgroundResource(R.drawable.text_pop_huibai);
                huikuan.setTextColor(getResources().getColor(R.color.popfont));
                huikuan.setBackgroundResource(R.drawable.text_pop_huibai);
                qita.setTextColor(getResources().getColor(R.color.popfont));
                qita.setBackgroundResource(R.drawable.text_pop_huibai);
                mPopupWindow.dismiss();

                getJyjl();
            }
        });
        //充值
        chongzhi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                jyRecordModelList.clear();
                mPopupWindow.dismiss();
                str = "1";
                tvAccountChoose.setText("筛选");
                isOpen = !isOpen;
                chongzhi.setTextColor(getResources().getColor(R.color.white));
                chongzhi.setBackgroundResource(R.drawable.text_pop_blue);

                quanbu.setTextColor(getResources().getColor(R.color.popfont));
                quanbu.setBackgroundResource(R.drawable.text_pop_huibai);
                tixian.setTextColor(getResources().getColor(R.color.popfont));
                tixian.setBackgroundResource(R.drawable.text_pop_huibai);
                touzi.setTextColor(getResources().getColor(R.color.popfont));
                touzi.setBackgroundResource(R.drawable.text_pop_huibai);
                huikuan.setTextColor(getResources().getColor(R.color.popfont));
                huikuan.setBackgroundResource(R.drawable.text_pop_huibai);
                qita.setTextColor(getResources().getColor(R.color.popfont));
                qita.setBackgroundResource(R.drawable.text_pop_huibai);
                getJyjl();
            }
        });
        //提现
        tixian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                jyRecordModelList.clear();
                mPopupWindow.dismiss();
                str = "2";
                tvAccountChoose.setText("筛选");
                isOpen = !isOpen;
                tixian.setTextColor(getResources().getColor(R.color.white));
                tixian.setBackgroundResource(R.drawable.text_pop_blue);
                quanbu.setTextColor(getResources().getColor(R.color.popfont));
                quanbu.setBackgroundResource(R.drawable.text_pop_huibai);
                chongzhi.setTextColor(getResources().getColor(R.color.popfont));
                chongzhi.setBackgroundResource(R.drawable.text_pop_huibai);
                touzi.setTextColor(getResources().getColor(R.color.popfont));
                touzi.setBackgroundResource(R.drawable.text_pop_huibai);
                huikuan.setTextColor(getResources().getColor(R.color.popfont));
                huikuan.setBackgroundResource(R.drawable.text_pop_huibai);
                qita.setTextColor(getResources().getColor(R.color.popfont));
                qita.setBackgroundResource(R.drawable.text_pop_huibai);
                getJyjl();
            }
        });
        //投资
        touzi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                jyRecordModelList.clear();
                mPopupWindow.dismiss();
                str = "3";
                tvAccountChoose.setText("筛选");
                isOpen = !isOpen;
                touzi.setTextColor(getResources().getColor(R.color.white));
                touzi.setBackgroundResource(R.drawable.text_pop_blue);
                quanbu.setTextColor(getResources().getColor(R.color.popfont));
                quanbu.setBackgroundResource(R.drawable.text_pop_huibai);
                tixian.setTextColor(getResources().getColor(R.color.popfont));
                tixian.setBackgroundResource(R.drawable.text_pop_huibai);
                chongzhi.setTextColor(getResources().getColor(R.color.popfont));
                chongzhi.setBackgroundResource(R.drawable.text_pop_huibai);
                huikuan.setTextColor(getResources().getColor(R.color.popfont));
                huikuan.setBackgroundResource(R.drawable.text_pop_huibai);
                qita.setTextColor(getResources().getColor(R.color.popfont));
                qita.setBackgroundResource(R.drawable.text_pop_huibai);
                getJyjl();
            }
        });
        //回款
        huikuan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                jyRecordModelList.clear();
                mPopupWindow.dismiss();
                str = "4";
                tvAccountChoose.setText("筛选");
                isOpen = !isOpen;
                huikuan.setTextColor(getResources().getColor(R.color.white));
                huikuan.setBackgroundResource(R.drawable.text_pop_blue);
                quanbu.setTextColor(getResources().getColor(R.color.popfont));
                quanbu.setBackgroundResource(R.drawable.text_pop_huibai);
                tixian.setTextColor(getResources().getColor(R.color.popfont));
                tixian.setBackgroundResource(R.drawable.text_pop_huibai);
                chongzhi.setTextColor(getResources().getColor(R.color.popfont));
                chongzhi.setBackgroundResource(R.drawable.text_pop_huibai);
                touzi.setTextColor(getResources().getColor(R.color.popfont));
                touzi.setBackgroundResource(R.drawable.text_pop_huibai);
                qita.setTextColor(getResources().getColor(R.color.popfont));
                qita.setBackgroundResource(R.drawable.text_pop_huibai);
                getJyjl();
            }
        });
        //其他
        qita.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                jyRecordModelList.clear();
                mPopupWindow.dismiss();
                str = "5";
                tvAccountChoose.setText("筛选");
                isOpen = !isOpen;
                qita.setTextColor(getResources().getColor(R.color.white));
                qita.setBackgroundResource(R.drawable.text_pop_blue);
                quanbu.setTextColor(getResources().getColor(R.color.popfont));
                quanbu.setBackgroundResource(R.drawable.text_pop_huibai);
                tixian.setTextColor(getResources().getColor(R.color.popfont));
                tixian.setBackgroundResource(R.drawable.text_pop_huibai);
                chongzhi.setTextColor(getResources().getColor(R.color.popfont));
                chongzhi.setBackgroundResource(R.drawable.text_pop_huibai);
                touzi.setTextColor(getResources().getColor(R.color.popfont));
                touzi.setBackgroundResource(R.drawable.text_pop_huibai);
                huikuan.setTextColor(getResources().getColor(R.color.popfont));
                huikuan.setBackgroundResource(R.drawable.text_pop_huibai);
                getJyjl();
            }
        });

    }


}
