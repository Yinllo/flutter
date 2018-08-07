package com.desjf.dsjr.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.desjf.dsjr.R;
import com.desjf.dsjr.adapter.MyIncomeAdapter;
import com.desjf.dsjr.base.BaseActivity;
import com.desjf.dsjr.biz.IncomeBiz;
import com.desjf.dsjr.biz.exception.BizFailure;
import com.desjf.dsjr.biz.exception.ZYException;
import com.desjf.dsjr.biz.task.BizDataAsyncTask;
import com.desjf.dsjr.model.IncomeListModel;
import com.desjf.dsjr.model.IncomeModel;
import com.desjf.dsjr.utils.BarUtils;
import com.desjf.dsjr.utils.ToastUtils;
import com.desjf.dsjr.widget.WrapContentLinearLayoutManager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AccountMyInComeActivity extends BaseActivity {
    @BindView(R.id.srl_income)
    SwipeRefreshLayout srlIncome;
    private Context context = this;
    @BindView(R.id.iv_my_income_back)
    ImageView ivMyIncomeBack;
    @BindView(R.id.tv_my_total_income)
    TextView tvMyTotalIncome;
    @BindView(R.id.tv_my_total_outcome)
    TextView tvMyTotalOutcome;
    private MyIncomeAdapter myIncomeAdapter;
    private List<String> str_list = new ArrayList<>();
    @BindView(R.id.rv_wode_income)
    RecyclerView rvWodeIncome;
    private int i=0;
    private List<IncomeListModel> incomeListModelList = new ArrayList<>();
    private int lastVisibleItem;
    private LinearLayoutManager linearLayoutManager = new WrapContentLinearLayoutManager(this);
    boolean ifRefresh=false;//记录是否刷新

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_my_in_come);
        ButterKnife.bind(this);
        BarUtils.setColor(this, getResources().getColor(R.color.endmian), 0);
        //获得合计
        getTotal();
        //获得数据集合
        getTotalList();
        initData();
    }

    private void getTotalList() {
        showLoadingDialog();
        BizDataAsyncTask<List<IncomeListModel>> getListIncome = new BizDataAsyncTask<List<IncomeListModel>>() {
            @Override
            protected List<IncomeListModel> doExecute() throws ZYException, BizFailure {
                return IncomeBiz.getIncomeList(String.valueOf(i), "20");
            }

            @Override
            protected void onExecuteSucceeded(List<IncomeListModel> incomeListModels) {
                hideLoadingDialog();
                srlIncome.setRefreshing(false);
                initIncomeList(incomeListModels);
            }

            @Override
            protected void OnExecuteFailed(String error) {
                hideLoadingDialog();
                srlIncome.setRefreshing(false);
                ToastUtils.showTost(context, error);
            }
        };
        getListIncome.execute();
    }

    private void initIncomeList(List<IncomeListModel> incomeListModels) {
        if(ifRefresh){
            incomeListModelList.clear();
        }
        incomeListModelList.addAll(incomeListModels);
        if (i==0){
            myIncomeAdapter = new MyIncomeAdapter(incomeListModelList, this);
            rvWodeIncome.setAdapter(myIncomeAdapter);
            rvWodeIncome.setLayoutManager(linearLayoutManager);
            myIncomeAdapter.notifyDataSetChanged();
        }else{
            myIncomeAdapter.addData(incomeListModels);
            myIncomeAdapter.notifyDataSetChanged();
        }

    }

    private void getTotal() {
        BizDataAsyncTask<IncomeModel> getTotalIncome = new BizDataAsyncTask<IncomeModel>() {
            @Override
            protected IncomeModel doExecute() throws ZYException, BizFailure {
                return IncomeBiz.getTotalIncome();
            }

            @Override
            protected void onExecuteSucceeded(IncomeModel incomeModel) {
                initTotalView(incomeModel);
            }

            @Override
            protected void OnExecuteFailed(String error) {
                ToastUtils.showTost(context, error);
            }
        };
        getTotalIncome.execute();
    }

    private void initTotalView(IncomeModel incomeModel) {
        tvMyTotalIncome.setText(incomeModel.getPROFIT_AMOUNT());
        tvMyTotalOutcome.setText(incomeModel.getLOSS_AMOUNT());
    }

    private void initData() {
        ivMyIncomeBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        srlIncome.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                i=0;
                ifRefresh=true;
                getTotalList();
            }
        });
        srlIncome.setColorSchemeResources(R.color.main);
        rvWodeIncome.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == RecyclerView.SCROLL_STATE_IDLE
                        && lastVisibleItem + 1 == myIncomeAdapter.getItemCount()&&myIncomeAdapter.getItemCount()>=19) {
                    i++;
                    ifRefresh=false;
                    getTotalList();
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
