package com.desjf.dsjr.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.desjf.dsjr.R;
import com.desjf.dsjr.adapter.PaymentInfoAdapter;
import com.desjf.dsjr.base.BaseActivity;
import com.desjf.dsjr.biz.PaymentDetailsBiz;
import com.desjf.dsjr.biz.exception.BizFailure;
import com.desjf.dsjr.biz.exception.ZYException;
import com.desjf.dsjr.biz.task.BizDataAsyncTask;
import com.desjf.dsjr.model.MyInvestModel;
import com.desjf.dsjr.model.PaymentDetailsModel;
import com.desjf.dsjr.model.PaymentInfoModel;
import com.desjf.dsjr.widget.WrapContentLinearLayoutManager;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class WodeInvestDetailsActivity extends BaseActivity {
    @BindView(R.id.iv_hkxq_back)
    ImageView ivHkxqBack;
    @BindView(R.id.tv_hkxq_tm)
    TextView tvHkxqTm;
    @BindView(R.id.tv_hkxq_tzje)
    TextView tvHkxqTzje;
    @BindView(R.id.tv_hkxq_nhll)
    TextView tvHkxqNhll;
    @BindView(R.id.tv_hkxq_jkqx)
    TextView tvHkxqJkqx;
    @BindView(R.id.tv_hkxq_fs)
    TextView tvHkxqFs;
    @BindView(R.id.rv_hkxq_list)
    RecyclerView rvHkxqList;
    @BindView(R.id.tv_biaoming)
    TextView tvBiaoming;
    @BindView(R.id.tv_biaohao)
    TextView tvBiaohao;
    @BindView(R.id.tv_project_title)
    TextView tvProjectTitle;
    @BindView(R.id.tv_first)
    TextView tvFirst;
    @BindView(R.id.tv_second)
    TextView tvSecond;
    @BindView(R.id.tv_third)
    TextView tvThird;
    @BindView(R.id.tv_one)
    TextView tvOne;
    @BindView(R.id.tv_two)
    TextView tvTwo;
    @BindView(R.id.tv_three)
    TextView tvThree;
    @BindView(R.id.tv_four)
    TextView tvFour;
    private MyInvestModel myInvestModel;
    private String id = "";
    private String status = "";
    private PaymentInfoAdapter paymentInfoAdapter;
    private LinearLayoutManager linearLayoutManager = new WrapContentLinearLayoutManager(this);
    private int i;
    private String name="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wode_invest_details);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        int kind = intent.getIntExtra("kind", -1);
        if (kind==1){
            //持有
            myInvestModel = (MyInvestModel) intent.getSerializableExtra("details");
            id = myInvestModel.getOIDTENDERID();
            i = intent.getIntExtra("tag",-1);
            name = myInvestModel.getTRANSFERCONTRACTID();
            if (!myInvestModel.getTRANSFERCONTRACTID().equals("")) {
                //债权
                status = "2";
            } else {
                //融资
                status = "1";
            }
        }else if (kind==2){
            //回款
            myInvestModel = (MyInvestModel) intent.getSerializableExtra("details");
            id = myInvestModel.getOIDTENDERID();
            i = intent.getIntExtra("tag",-1);
            name = myInvestModel.getTRANSFERCONTRACTID();
            if (!myInvestModel.getTRANSFERCONTRACTID().equals("")) {
                //债权
                status = "2";
            } else {
                //融资
                status = "1";
            }
        }else if(kind==3){
            id = intent.getStringExtra("kind_id");
            status = intent.getStringExtra("kind_status");
            name = intent.getStringExtra("kind_zhaiquan");
            i=intent.getIntExtra("kind_shou",-1);
        }else if (kind==4){
            id = intent.getStringExtra("kind_id");
            status = intent.getStringExtra("kind_status");
            name = intent.getStringExtra("kind_zhaiquan");
            i=1;
        }else if (kind==5){
            id = intent.getStringExtra("kind_id");
            status = intent.getStringExtra("kind_status");
            name = intent.getStringExtra("kind_zhaiquan");
            i=1;
        }
        getDetails();
        getList();
    }

    private void getDetails() {
        showLoadingDialog();
        BizDataAsyncTask<PaymentInfoModel> getInfo = new BizDataAsyncTask<PaymentInfoModel>() {
            @Override
            protected PaymentInfoModel doExecute() throws ZYException, BizFailure {
                return PaymentDetailsBiz.getPaymentInfo(id, status);
            }

            @Override
            protected void onExecuteSucceeded(PaymentInfoModel paymentInfoModel) {
                hideLoadingDialog();
                initBiao(paymentInfoModel);
            }

            @Override
            protected void OnExecuteFailed(String error) {
                hideLoadingDialog();
            }
        };
        getInfo.execute();
    }

    //处理标数据
    private void initBiao(PaymentInfoModel paymentInfoModel) {
        if (status.equals("1")) {
            tvHkxqTm.setText(paymentInfoModel.getPRODUCTS_TITLE());
            tvHkxqTzje.setText(paymentInfoModel.getTENDER_AMOUNT() + paymentInfoModel.getTENDER_AMOUNT_UNIT());
            tvHkxqNhll.setText(paymentInfoModel.getRATE() + "%");
            tvHkxqJkqx.setText(paymentInfoModel.getPERIOD());
            tvHkxqFs.setText(paymentInfoModel.getREPAY_TYPE());
        } else {
            tvHkxqTm.setVisibility(View.GONE);
            tvBiaohao.setVisibility(View.VISIBLE);
            tvBiaoming.setVisibility(View.VISIBLE);
            tvBiaohao.setText(paymentInfoModel.getPRODUCTS_TITLE());
            tvBiaoming.setText(name);
            tvFirst.setText("债权金额");
            tvHkxqTzje.setText(paymentInfoModel.getTENDER_AMOUNT_TRANS() + paymentInfoModel.getTENDER_AMOUNT_TRANS_UNIT());
            tvSecond.setText("原表年化");
            tvHkxqNhll.setText(paymentInfoModel.getRATE() + "%");
            tvThird.setText("剩余期限");
            tvHkxqJkqx.setText(paymentInfoModel.getSURPLUS_PERIODS());
            tvHkxqFs.setVisibility(View.GONE);
            tvOne.setVisibility(View.VISIBLE);
            tvOne.setText("公允价值: "+paymentInfoModel.getFAIR_VALUE()+paymentInfoModel.getFAIR_VALUE_UNIT());
            tvTwo.setVisibility(View.VISIBLE);
            tvTwo.setText("成交价格: "+paymentInfoModel.getACTUAL_AMOUNT()+paymentInfoModel.getACTUAL_AMOUNT_UNIT());
            tvThree.setVisibility(View.VISIBLE);
            tvThree.setText("还款方式: "+paymentInfoModel.getREPAY_TYPE());
            tvFour.setVisibility(View.VISIBLE);
            tvFour.setText("交易时间: "+paymentInfoModel.getSUCCESS_DATE());
        }

    }

    private void getList() {
        showLoadingDialog();
        BizDataAsyncTask<List<PaymentDetailsModel>> getPaymentDetails = new BizDataAsyncTask<List<PaymentDetailsModel>>() {
            @Override
            protected List<PaymentDetailsModel> doExecute() throws ZYException, BizFailure {
                return PaymentDetailsBiz.getPayment(id, status, "0", "20");
            }

            @Override
            protected void onExecuteSucceeded(List<PaymentDetailsModel> paymentDetailsModels) {
                hideLoadingDialog();
                initList(paymentDetailsModels);
            }

            @Override
            protected void OnExecuteFailed(String error) {
                hideLoadingDialog();
            }
        };
        getPaymentDetails.execute();

    }

    private void initList(List<PaymentDetailsModel> paymentDetailsModels) {
        paymentInfoAdapter = new PaymentInfoAdapter(paymentDetailsModels, this,i);
        rvHkxqList.setAdapter(paymentInfoAdapter);
        rvHkxqList.setLayoutManager(linearLayoutManager);
        paymentInfoAdapter.notifyDataSetChanged();
    }


    @OnClick(R.id.iv_hkxq_back)
    public void onClick() {
        finish();
    }
}
