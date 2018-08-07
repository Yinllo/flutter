package com.desjf.dsjr.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.desjf.dsjr.R;
import com.desjf.dsjr.adapter.AssigmentTwoAdapter;
import com.desjf.dsjr.adapter.AssigmentitemAdapter;
import com.desjf.dsjr.base.BaseActivity;
import com.desjf.dsjr.biz.TransferInfoBiz;
import com.desjf.dsjr.biz.exception.BizFailure;
import com.desjf.dsjr.biz.exception.ZYException;
import com.desjf.dsjr.biz.task.BizDataAsyncTask;
import com.desjf.dsjr.model.TransferInfoModel;
import com.desjf.dsjr.model.TransferInvestorsModel;
import com.desjf.dsjr.utils.PreferenceCache;
import com.desjf.dsjr.widget.WrapContentLinearLayoutManager;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AssignmentDetailsActivity extends BaseActivity {
    @BindView(R.id.rv_assignment_details)
    RecyclerView rvAssignmentDetails;
    @BindView(R.id.ll_getmore)
    LinearLayout llGetmore;
    private Context context = this;
    @BindView(R.id.btn_buy_immediate)
    Button btnBuyImmediate;
    @BindView(R.id.iv_transfer_back)
    ImageView ivTransferBack;
    @BindView(R.id.tv_transfer_ybnh)
    TextView tvTransferYbnh;
    @BindView(R.id.tv_transfer_syts)
    TextView tvTransferSyts;
    @BindView(R.id.tv_transfer_zrjg)
    TextView tvTransferZrjg;
    @BindView(R.id.pb_transfer)
    ProgressBar pbTransfer;
    @BindView(R.id.tv_transfer_sykt)
    TextView tvTransferSykt;
    @BindView(R.id.tv_transfer_ydc)
    TextView tvTransferYdc;
    @BindView(R.id.tv_transfer_zrbh)
    TextView tvTransferZrbh;
    @BindView(R.id.tv_transfer_ybmc)
    TextView tvTransferYbmc;
    @BindView(R.id.tv_transfer_zqjg)
    TextView tvTransferZqjg;
    @BindView(R.id.tv_transfer_gyjg)
    TextView tvTransferGyjg;
    @BindView(R.id.tv_transfer_zrbl)
    TextView tvTransferZrbl;
    private String id = "";
    private String bili;
    private String status;
    private AssigmentitemAdapter assigemntAdapter;
    private AssigmentTwoAdapter assigmenttwoAdapter;
    private LinearLayoutManager linearLayoutManager = new WrapContentLinearLayoutManager(this);
    private String trans_id;
    private String trans_status;
    private String trans_tender;
    public static AssignmentDetailsActivity mInstace = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assignment_details);
        ButterKnife.bind(this);
        //获取转让详情信息
        Intent intent = getIntent();
        id = intent.getStringExtra("zzid");
        bili = intent.getStringExtra("bili");
        status = intent.getStringExtra("status");
        mInstace= this;
        getTransferInfo();
        getList();
        initData();
    }

    private void getList() {
        showLoadingDialog();
        BizDataAsyncTask<List<TransferInvestorsModel>> getListInfo = new BizDataAsyncTask<List<TransferInvestorsModel>>() {
            @Override
            protected List<TransferInvestorsModel> doExecute() throws ZYException, BizFailure {
                return TransferInfoBiz.getTransfer(id, "0", "20", status);
            }

            @Override
            protected void onExecuteSucceeded(List<TransferInvestorsModel> transferInvestorsModel) {
                hideLoadingDialog();
                initRecord(transferInvestorsModel);
            }

            @Override
            protected void OnExecuteFailed(String error) {
                hideLoadingDialog();
            }
        };
        getListInfo.execute();
    }

    private void initRecord(List<TransferInvestorsModel> transferInvestorsModel) {
        if (status.equals("0")) {
            assigemntAdapter = new AssigmentitemAdapter(transferInvestorsModel, context);
            rvAssignmentDetails.setAdapter(assigemntAdapter);
            rvAssignmentDetails.setLayoutManager(linearLayoutManager);
            assigemntAdapter.notifyDataSetChanged();
        } else {
            assigmenttwoAdapter = new AssigmentTwoAdapter(transferInvestorsModel, context);
            rvAssignmentDetails.setAdapter(assigmenttwoAdapter);
            rvAssignmentDetails.setLayoutManager(linearLayoutManager);
            assigmenttwoAdapter.notifyDataSetChanged();
        }

    }

    private void getTransferInfo() {
        showLoadingDialog();
        BizDataAsyncTask<TransferInfoModel> getinfo = new BizDataAsyncTask<TransferInfoModel>() {
            @Override
            protected TransferInfoModel doExecute() throws ZYException, BizFailure {
                return TransferInfoBiz.getTransferInfo(id);
            }

            @Override
            protected void onExecuteSucceeded(TransferInfoModel transferInfoModel) {
                hideLoadingDialog();
                initTransferInfo(transferInfoModel);
                trans_id = transferInfoModel.getOID_PLATFORM_PRODUCTS_ID();
                trans_status = transferInfoModel.getSTATUS();
                trans_tender = transferInfoModel.getDISCOUNT_SCALE();
            }

            @Override
            protected void OnExecuteFailed(String error) {
                hideLoadingDialog();
            }
        };
        getinfo.execute();
    }

    private void initTransferInfo(TransferInfoModel transferInfoModel) {
        if (PreferenceCache.getToken().isEmpty()) {
            btnBuyImmediate.setText("立即登录");
            btnBuyImmediate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(AssignmentDetailsActivity.this, LoginActivity.class);
                    startActivity(intent);
                }
            });
        } else {
            if (status.equals("0")) {
                btnBuyImmediate.setText("立即购买");
                btnBuyImmediate.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(AssignmentDetailsActivity.this, BuyAssignmentDetailsActivity.class);
                        intent.putExtra("zaizid", id);
                        startActivity(intent);
                    }
                });

            } else {
                btnBuyImmediate.setText("转让成功");
                btnBuyImmediate.setBackgroundColor(getResources().getColor(R.color.navy_gray));
                btnBuyImmediate.setClickable(false);
            }

        }
        if (transferInfoModel.getTRANSFER_YOURSELF().equals("0")){
            //是登陆者发送
            btnBuyImmediate.setBackgroundColor(getResources().getColor(R.color.navy_gray));
            btnBuyImmediate.setClickable(false);
        }else{
            btnBuyImmediate.setBackgroundColor(getResources().getColor(R.color.main));
            btnBuyImmediate.setClickable(true);
        }

        tvTransferYbnh.setText(transferInfoModel.getRATE() + "%");
        tvTransferSyts.setText(transferInfoModel.getPERIOD() + "天");
        tvTransferZrjg.setText(transferInfoModel.getTRANSFER_AMOUNT() + transferInfoModel.getTRANSFER_AMOUNT_UNIT());
        tvTransferSykt.setText("剩余可投" + transferInfoModel.getTRANSFER_CAPTIAL_WAIT() + "元");
        tvTransferZrbh.setText(transferInfoModel.getTRANSFER_CONTRACT_ID());
        tvTransferYbmc.setText(transferInfoModel.getPRODUCTS_TITLE());
        tvTransferZqjg.setText(transferInfoModel.getTRANSFER_CAPITAL() + "元");
        tvTransferGyjg.setText(transferInfoModel.getFAIR_VALUE()+"元");
        tvTransferZrbl.setText(transferInfoModel.getDISCOUNT_SCALE() + "%");
        tvTransferYdc.setText("已达成" + bili);
        String str = bili.substring(0, bili.length() - 4);
        int i = Integer.parseInt(str);
        pbTransfer.setProgress(i);
    }

    private void initData() {


        ivTransferBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        llGetmore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AssignmentDetailsActivity.this, StandarDetailsActivity.class);
                intent.putExtra("key", trans_id);
                intent.putExtra("tender",trans_tender);
                intent.putExtra("status",trans_status);
                startActivity(intent);
            }
        });
    }
}
