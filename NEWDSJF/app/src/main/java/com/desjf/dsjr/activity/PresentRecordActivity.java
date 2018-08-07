package com.desjf.dsjr.activity;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.desjf.dsjr.R;
import com.desjf.dsjr.adapter.WithDrawRecordAdapter;
import com.desjf.dsjr.base.BaseActivity;
import com.desjf.dsjr.biz.WithDrawBiz;
import com.desjf.dsjr.biz.exception.BizFailure;
import com.desjf.dsjr.biz.exception.ZYException;
import com.desjf.dsjr.biz.task.BizDataAsyncTask;
import com.desjf.dsjr.cyview.base.BaseDialog;
import com.desjf.dsjr.model.AccountModel;
import com.desjf.dsjr.model.WithDrawModel;
import com.desjf.dsjr.widget.WithDrawListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PresentRecordActivity extends BaseActivity{

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
    private WithDrawRecordAdapter withDrawAdapter;
    private int i = 0;
    private List<WithDrawModel> withDrawList = new ArrayList<>();
    private int lastVisibleItem;
    private LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
    private WithDrawListener withDraw = new WithDrawListener() {
        @Override
        public void Chexiao(String s) {
            initCx(s);
        }
    };
    private String with_less, all_money;


    private BaseDialog dialog_tixian;
    private TextView tv_poundage, tv_sum;


    private Handler handler;

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


        setContentView(R.layout.activity_present_record);

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
                        && lastVisibleItem + 1 == withDrawAdapter.getItemCount() && withDrawAdapter.getItemCount() >= 19) {
                    i++;
                    initList();
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                lastVisibleItem = linearLayoutManager.findLastVisibleItemPosition();
            }
        });
    }

    private void initList() {

        showLoadingDialog();
        BizDataAsyncTask<List<WithDrawModel>> getList = new BizDataAsyncTask<List<WithDrawModel>>() {
            @Override
            protected List<WithDrawModel> doExecute() throws ZYException, BizFailure {
                return WithDrawBiz.getWithDrawList(String.valueOf(i), "20");
            }

            @Override
            protected void onExecuteSucceeded(List<WithDrawModel> withDrawModel) {
                hideLoadingDialog();
                initRecord(withDrawModel);
                srlJl.setRefreshing(false);
            }

            @Override
            protected void OnExecuteFailed(String error) {
                hideLoadingDialog();
                showToast(error);
                srlJl.setRefreshing(false);
            }
        };
        getList.execute();
    }

    private void initRecord(List<WithDrawModel> withDrawModel) {
        withDrawList.addAll(withDrawModel);
        if (i == 0) {
            withDrawAdapter = new WithDrawRecordAdapter(withDrawModel, this, withDraw);
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
