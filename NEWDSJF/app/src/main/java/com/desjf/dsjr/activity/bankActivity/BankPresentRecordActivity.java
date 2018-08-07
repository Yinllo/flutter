package com.desjf.dsjr.activity.bankActivity;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.desjf.dsjr.R;
import com.desjf.dsjr.adapter.bankAdapter.BankWithDrawRecordAdapter;
import com.desjf.dsjr.base.BaseActivity;
import com.desjf.dsjr.biz.WithDrawBiz;
import com.desjf.dsjr.biz.exception.BizFailure;
import com.desjf.dsjr.biz.exception.ZYException;
import com.desjf.dsjr.biz.retrofit.BankHttpUtils;
import com.desjf.dsjr.biz.retrofit.CallUtils;
import com.desjf.dsjr.biz.task.BizDataAsyncTask;
import com.desjf.dsjr.cyview.base.BaseDialog;
import com.desjf.dsjr.model.AccountModel;
import com.desjf.dsjr.model.bankModel.BankPresentRecordModel;
import com.desjf.dsjr.utils.PreferenceCache;
import com.desjf.dsjr.widget.WithDrawListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class BankPresentRecordActivity extends BaseActivity{

    @BindView(R.id.rv_txjl)
    RecyclerView rvTxjl;
    @BindView(R.id.srl_jl)
    SwipeRefreshLayout srlJl;
    private Context context = this;
    @BindView(R.id.iv_withdraw_back)
    ImageView ivWithdrawBack;
    @BindView(R.id.tv_project_title)
    TextView tvProjectTitle;
//    @BindView(R.id.tv_with_draw_shuom)
//    TextView tvWithDrawShuom;

    private AccountModel accountModel;
    private BankWithDrawRecordAdapter withDrawAdapter;
    private int i = 0;
    private List<BankPresentRecordModel.GetwithdrawListBean> withDrawList = new ArrayList<>();
    private int lastVisibleItem;
    private LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
    private WithDrawListener withDraw = new WithDrawListener() {
        @Override
        public void Chexiao(String s) {
            initCx(s);
        }
    };
    private String with_less, all_money;

    int maxCount=200;//每次加载的记录条数
    int totalNum=0;//记录总条数

    private BaseDialog dialog_tixian;
    private TextView tv_poundage, tv_sum;

    private Handler handler;
    private View notDataView;
    private void initCx(final String s) {
        showLoadingDialog();
        BizDataAsyncTask<String> getCx = new BizDataAsyncTask<String>() {
            @Override
            protected String doExecute() throws ZYException, BizFailure {
                return WithDrawBiz.getWithDrawCx(s);
            }

            @Override
            protected void onExecuteSucceeded(String s) {
                hideLoadingDialog();
                i = 0;
                withDrawList.clear();
                initList();
                if (String.valueOf(s).equals("1")) {
                    showToast("提现撤销成功");
                } else {
                    showToast("提现撤销失败");
                }
            }

            @Override
            protected void OnExecuteFailed(String error) {
                hideLoadingDialog();
                showToast(error + "");
            }
        };
        getCx.execute();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);

                tv_poundage.setText(String.valueOf(msg.obj));


                dialog_tixian.show();
            }
        };

        setContentView(R.layout.activity_bank_present_record);

        dialog_tixian = new BaseDialog(this);

        dialog_tixian.config(R.layout.dialog_tixian, Gravity.CENTER, -1, true);
        tv_poundage = dialog_tixian.findViewById(R.id.tv_poundage);
        tv_sum = dialog_tixian.findViewById(R.id.tv_sum);
        dialog_tixian.findViewById(R.id.tv_cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog_tixian.dismiss();
            }
        });


        ButterKnife.bind(this);
        initList();
        initView();



    }

    private void initView() {
        srlJl.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                i = 0;
                withDrawList.clear();
                initList();
            }
        });
        srlJl.setColorSchemeResources(R.color.main);
        rvTxjl.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == RecyclerView.SCROLL_STATE_IDLE
                        && lastVisibleItem + 1 == withDrawAdapter.getItemCount() && withDrawAdapter.getItemCount() >= 199) {
                    i++;
                    //如果当前i小于总页数，则加载更多
                    if(i<getMaxPageNum(totalNum,maxCount)) {
                        initList();
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


    private void initList() {
        CallUtils.call(BankPresentRecordActivity.this, BankHttpUtils.getHttpRequestService().getWithdraw(PreferenceCache.getToken(),i+"", "20"), new CallUtils.MyCallListener() {
            @Override
            public void onRespnseSuccess(String jsonString) {
              srlJl.setRefreshing(false);
              BankPresentRecordModel bankPresentRecordModel= JSON.parseObject(jsonString,BankPresentRecordModel.class);
                if (bankPresentRecordModel.getGetwithdrawList()!=null) {
                    totalNum=bankPresentRecordModel.getWithdrawNum();
                }
              List<BankPresentRecordModel.GetwithdrawListBean> getwithdrawListBeans=bankPresentRecordModel.getGetwithdrawList();
              initRecord(getwithdrawListBeans);

            }
        });

    }

    private void initRecord(List<BankPresentRecordModel.GetwithdrawListBean> withDrawModel) {
        withDrawList.addAll(withDrawModel);
        if (i == 0) {
            withDrawAdapter = new BankWithDrawRecordAdapter(withDrawModel, this, withDraw);
            //如果没有数据则显示 无数据
            if(withDrawModel.size()==0) {
                rvTxjl.setHasFixedSize(true);
                //初始化无数据布局
                notDataView = this.getLayoutInflater().inflate(R.layout.empty, (ViewGroup) rvTxjl.getParent(), false);
                //将无数据布局设置到适配器中
                withDrawAdapter.setEmptyView(notDataView);
            }
            rvTxjl.setAdapter(withDrawAdapter);
            rvTxjl.setLayoutManager(linearLayoutManager);
            withDrawAdapter.notifyDataSetChanged();
        } else {
            withDrawAdapter.addData(withDrawModel);
            withDrawAdapter.notifyDataSetChanged();
        }

    }


    @OnClick({R.id.iv_withdraw_back})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_withdraw_back:
                finish();
                break;

        }
    }


}
