package com.desjf.dsjr.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.desjf.dsjr.R;
import com.desjf.dsjr.base.BaseActivity;
import com.desjf.dsjr.model.MyInvestModel;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ZrzDetailsActivity extends BaseActivity {
    @BindView(R.id.iv_zrz_info_back)
    ImageView ivZrzInfoBack;
    @BindView(R.id.tv_zrz_title)
    TextView tvZrzTitle;
    @BindView(R.id.tv_zrz_yuanjine)
    TextView tvZrzYuanjine;
    @BindView(R.id.tv_zrz_zcje)
    TextView tvZrzZcje;
    @BindView(R.id.tv_oone)
    TextView tvOone;
    @BindView(R.id.tv_zrz_gyjg)
    TextView tvZrzGyjg;
    @BindView(R.id.tv_otwo)
    TextView tvOtwo;
    @BindView(R.id.tv_zrz_cjjg)
    TextView tvZrzCjjg;
    @BindView(R.id.tv_sy)
    TextView tvSy;
    @BindView(R.id.tv_zrz_zrbl)
    TextView tvZrzZrbl;
    @BindView(R.id.tv_zrz_zrj)
    TextView tvZrzZrj;
    @BindView(R.id.tv_zrz_fwf)
    TextView tvZrzFwf;
    @BindView(R.id.tv_zrz_status)
    TextView tvZrzStatus;
    @BindView(R.id.tv_zrz_time)
    TextView tvZrzTime;
    private MyInvestModel myInvestModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zrz_details);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        myInvestModel = (MyInvestModel) intent.getSerializableExtra("zrz");
        initData();
    }

    private void initData() {
        tvZrzTitle.setText(myInvestModel.getTRANSFERCONTRACTID());
        tvZrzYuanjine.setText("原债权金额:"+myInvestModel.getTRANSFERDETAIL().getDETAILTENDERAMOUNT()+"元");
        tvZrzZcje.setText(myInvestModel.getTRANSFERDETAIL().getDETAILTRANSFERCAPITAL());
        tvZrzGyjg.setText(myInvestModel.getTRANSFERDETAIL().getDETAILFAIRVALUE());
        tvZrzCjjg.setText(myInvestModel.getTRANSFERDETAIL().getDETAILTRANSFERAMOUNT());
        tvZrzZrbl.setText(myInvestModel.getTRANSFERDETAIL().getDETAILDISCOUNTSCALE()+"%");
        tvZrzZrj.setText(myInvestModel.getTRANSFERDETAIL().getDETAILDISCOUNTAMOUNT()+"元");
        tvZrzFwf.setText(myInvestModel.getTRANSFERDETAIL().getDETAILTRANSFERMANAGEFEE());
        tvZrzStatus.setText(myInvestModel.getTRANSFERDETAIL().getDETAILTRANSFERSTATUS());
        tvZrzTime.setText(myInvestModel.getTRANSFERDETAIL().getDETAILTRANSFERTIME());
    }

    @OnClick(R.id.iv_zrz_info_back)
    public void onClick() {
        finish();
    }
}
