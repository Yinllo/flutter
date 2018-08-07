package com.desjf.dsjr.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.desjf.dsjr.R;
import com.desjf.dsjr.base.BaseActivity;
import com.desjf.dsjr.biz.exception.BizFailure;
import com.desjf.dsjr.biz.exception.ZYException;
import com.desjf.dsjr.biz.task.BizDataAsyncTask;
import com.desjf.dsjr.biz.task.UserZRBiz;
import com.desjf.dsjr.model.AjaxEventModel;
import com.desjf.dsjr.model.MyInvestModel;
import com.desjf.dsjr.model.UserTransferInfoModel;
import com.desjf.dsjr.utils.SoftInputUtil;
import com.desjf.dsjr.utils.ToastUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class KzrDetailsActivity extends BaseActivity {

    @BindView(R.id.iv_kzr_back)
    ImageView ivKzrBack;
    @BindView(R.id.iv_kzr_icon)
    ImageView ivKzrIcon;
    @BindView(R.id.tv_kzr_title)
    TextView tvKzrTitle;
    @BindView(R.id.et_kzr_money)
    EditText etKzrMoney;
    @BindView(R.id.tv_kzr_gyjz)
    TextView tvKzrGyjz;
    @BindView(R.id.et_kzr_zrje)
    EditText etKzrZrje;
    @BindView(R.id.tv_zrbl)
    TextView tvZrbl;
    @BindView(R.id.tv_kzr_zrsxf)
    TextView tvKzrZrsxf;
    @BindView(R.id.tv_kzr_zrj)
    TextView tvKzrZrj;
    @BindView(R.id.tv_kzr_queren)
    TextView tvKzrQueren;
    @BindView(R.id.iv_kzr_qezr)
    ImageView ivKzrQezr;
    @BindView(R.id.ll_all)
    LinearLayout llAll;
    @BindView(R.id.tv_kzr_kzrze)
    TextView tvKzrKzrze;
    private MyInvestModel myInvestModel;
    private String str = "";
    private Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kzr_details);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        myInvestModel = (MyInvestModel) intent.getSerializableExtra("zr");
        tvKzrKzrze.setText("可转让总额"+myInvestModel.getCAPITALWAIT()+"元");
        tvKzrTitle.setText(myInvestModel.getPRODUCTSTITLE());
        initData();
    }

    private void initData() {
        etKzrMoney.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (b) {
                } else {
                    initZrje();
                    SoftInputUtil.hideSoftKeyboard(etKzrMoney);
                }
            }
        });
        etKzrZrje.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (b) {
                } else {
                    initZrje();
                    SoftInputUtil.hideSoftKeyboard(etKzrMoney);
                }
            }
        });
        llAll.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                llAll.setFocusable(true);
                llAll.setFocusableInTouchMode(true);
                llAll.requestFocus();
                return false;
            }
        });
    }

    private void initZrje() {
        showLoadingDialog();
        BizDataAsyncTask<AjaxEventModel> getAjax = new BizDataAsyncTask<AjaxEventModel>() {
            @Override
            protected AjaxEventModel doExecute() throws ZYException, BizFailure {
                if (etKzrZrje.getText().toString().isEmpty()) {
                    str = "0";
                } else {
                    str = etKzrZrje.getText().toString();
                }
                return UserZRBiz.getUser(myInvestModel.getOIDTENDERID(), "2", etKzrMoney.getText().toString(), str);
            }

            @Override
            protected void onExecuteSucceeded(AjaxEventModel ajaxEventModel) {
                hideLoadingDialog();
                initValue(ajaxEventModel);
            }

            @Override
            protected void OnExecuteFailed(String error) {
                hideLoadingDialog();
                ToastUtils.showTost(context, error.toString());
            }
        };
        getAjax.execute();
    }

    private void initValue(AjaxEventModel ajaxEventModel) {
        tvKzrGyjz.setText(ajaxEventModel.getFAIR_VALUE() + "元");
        tvZrbl.setText(ajaxEventModel.getDISCOUNT_RATE() + "%");
        tvKzrZrsxf.setText(ajaxEventModel.getFEE() + "元");
        tvKzrZrj.setText(ajaxEventModel.getREAL_TRANSFER_VALUE() + "元");
    }

    @OnClick({R.id.iv_kzr_back, R.id.tv_kzr_queren, R.id.iv_kzr_qezr})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_kzr_back:
                finish();
                break;
            case R.id.tv_kzr_queren:
                getBtn();
                break;
            case R.id.iv_kzr_qezr:
//                getQuane();
                etKzrMoney.setText(myInvestModel.getCAPITALWAIT());
                break;
        }
    }

    private void getBtn() {
        if (etKzrMoney.getText().toString().isEmpty()){
            ToastUtils.showTost(context,"请输入转让金额");
            return;
        }
        showLoadingDialog();
        BizDataAsyncTask<String> getInfo = new BizDataAsyncTask<String>() {
            @Override
            protected String doExecute() throws ZYException, BizFailure {
                return UserZRBiz.getTransfer(myInvestModel.getOIDTENDERID(),myInvestModel.getTENDERFROM(),etKzrMoney.getText().toString(),str);
            }

            @Override
            protected void onExecuteSucceeded(String s) {
                hideLoadingDialog();
                ToastUtils.showTost(context,"转让成功");
                finish();
            }

            @Override
            protected void OnExecuteFailed(String error) {
                hideLoadingDialog();
                ToastUtils.showTost(context,error.toString());
            }
        };
        getInfo.execute();
    }

    private void getQuane() {
        showLoadingDialog();
        BizDataAsyncTask<UserTransferInfoModel> getQuane = new BizDataAsyncTask<UserTransferInfoModel>() {
            @Override
            protected UserTransferInfoModel doExecute() throws ZYException, BizFailure {
                return UserZRBiz.getUserInfer(myInvestModel.getOIDTENDERID(), "2");
            }

            @Override
            protected void onExecuteSucceeded(UserTransferInfoModel userTransferInfoModel) {
                hideLoadingDialog();
            }

            @Override
            protected void OnExecuteFailed(String error) {
                hideLoadingDialog();
            }
        };
        getQuane.execute();
    }
}
