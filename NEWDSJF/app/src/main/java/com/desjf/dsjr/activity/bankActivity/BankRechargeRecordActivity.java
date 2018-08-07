package com.desjf.dsjr.activity.bankActivity;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.alibaba.fastjson.JSON;
import com.desjf.dsjr.R;
import com.desjf.dsjr.adapter.bankAdapter.BankReChargeRecordAdapter;
import com.desjf.dsjr.base.BaseActivity;
import com.desjf.dsjr.biz.retrofit.BankHttpUtils;
import com.desjf.dsjr.biz.retrofit.CallUtils;
import com.desjf.dsjr.model.bankModel.RechargeRecordModel;
import com.desjf.dsjr.utils.PreferenceCache;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class BankRechargeRecordActivity extends BaseActivity {
    BankReChargeRecordAdapter rechargeRecordadapter;
    @BindView(R.id.iv_charge_back)
    ImageView ivChargeBack;
    @BindView(R.id.rv_recharge_record)
    RecyclerView rvRechargeRecord;
    @BindView(R.id.tv_none)
    LinearLayout tv ;
    int i = 0;
    int maxCount=200;//每次加载的记录条数
    int totalNum=0;//记录总条数
    @BindView(R.id.srl_record)
    SwipeRefreshLayout srlRecord;
    private int lastVisibleItem;
    private LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
    private List<RechargeRecordModel.GetRechargeListBean> list_record=new ArrayList<>();
    private View notDataView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bankrecharge_record);
        ButterKnife.bind(this);
        initData();
        initView();
    }

    private void initView() {
        srlRecord.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                i=0;
                list_record.clear();
                initData();
            }
        });
        srlRecord.setColorSchemeResources(R.color.main);
        rvRechargeRecord.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == RecyclerView.SCROLL_STATE_IDLE
                        && lastVisibleItem + 1 == rechargeRecordadapter.getItemCount()&&rechargeRecordadapter.getItemCount()>=199) {
                    //如果当前i小于总页数，则加载更多
                    if(i<getMaxPageNum(totalNum,maxCount)) {
                        i++;
                        initData();
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
    //计算最大页数
      private int getMaxPageNum(int totalNumber,int maxCount){
            return (totalNumber%maxCount==0)?(totalNumber/maxCount):(totalNumber/maxCount+1);
      }


    private void initData() {

        CallUtils.call(BankRechargeRecordActivity.this, BankHttpUtils.getHttpRequestService().getRecord(PreferenceCache.getToken(),i+"", maxCount+""), new CallUtils.MyCallListener() {
            @Override
            public void onRespnseSuccess(String jsonString) {
                srlRecord.setRefreshing(false);
                RechargeRecordModel rechargeRecordModel= JSON.parseObject(jsonString,RechargeRecordModel.class);
                if(rechargeRecordModel.getGetRechargeList()!=null){
                    totalNum=rechargeRecordModel.getRechargeNum();
                }
                List<RechargeRecordModel.GetRechargeListBean> reChargeRecordModel=rechargeRecordModel.getGetRechargeList();
                initRecord(reChargeRecordModel);

            }
        });

    }

    private void initRecord(List<RechargeRecordModel.GetRechargeListBean> reChargeRecordModel) {
//        ToastUtils.showTost(RechargeRecordActivity.this,reChargeRecordModel.size()+"");
        list_record.addAll(reChargeRecordModel);
        if (i==0){
            rechargeRecordadapter = new BankReChargeRecordAdapter(reChargeRecordModel, this);
            //如果没有数据则显示 无数据
            if(reChargeRecordModel.size()==0) {
                rvRechargeRecord.setHasFixedSize(true);
                //初始化无数据布局
                notDataView = this.getLayoutInflater().inflate(R.layout.empty, (ViewGroup) rvRechargeRecord.getParent(), false);
                //将无数据布局设置到适配器中
                rechargeRecordadapter.setEmptyView(notDataView);
            }
            rvRechargeRecord.setAdapter(rechargeRecordadapter);
            rvRechargeRecord.setLayoutManager(linearLayoutManager);
            rechargeRecordadapter.notifyDataSetChanged();
        }else{
            rechargeRecordadapter.addData(reChargeRecordModel);
            rechargeRecordadapter.notifyDataSetChanged();
        }

    }

    @OnClick(R.id.iv_charge_back)
    public void onClick() {
        finish();
    }
}
