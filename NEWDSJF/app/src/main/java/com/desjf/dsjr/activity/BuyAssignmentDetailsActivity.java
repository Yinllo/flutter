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
import com.desjf.dsjr.base.BaseApplication;
import com.desjf.dsjr.biz.TenderForTransferBiz;
import com.desjf.dsjr.biz.exception.BizFailure;
import com.desjf.dsjr.biz.exception.ZYException;
import com.desjf.dsjr.biz.task.BizDataAsyncTask;
import com.desjf.dsjr.model.AccountModel;
import com.desjf.dsjr.model.InterestTotalForTransferModel;
import com.desjf.dsjr.model.TenderForTransferModel;
import com.desjf.dsjr.utils.SoftInputUtil;
import com.desjf.dsjr.utils.StringUtil;
import com.desjf.dsjr.utils.ToastUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class BuyAssignmentDetailsActivity extends BaseActivity {
    @BindView(R.id.iv_assignment_back)
    ImageView ivAssignmentBack;
    @BindView(R.id.title_tv)
    TextView titleTv;
    @BindView(R.id.tv_assignment_money)
    TextView tvAssignmentMoney;
    @BindView(R.id.tv_assignment_chongzhi)
    TextView tvAssignmentChongzhi;
    @BindView(R.id.et_assignment_tzje)
    EditText etAssignmentTzje;
    @BindView(R.id.tv_assignment_qegm)
    TextView tvAssignmentQegm;
    @BindView(R.id.tv_assignment_yjsy)
    TextView tvAssignmentYjsy;
    @BindView(R.id.tv_assignment_zrsy)
    TextView tvAssignmentZrsy;
    @BindView(R.id.tv_assignment_sjyf)
    TextView tvAssignmentSjyf;
    @BindView(R.id.tv_assignment_forget_psw)
    TextView tvAssignmentForgetPsw;
    @BindView(R.id.tv_assignment_ljgm)
    TextView tvAssignmentLjgm;
    @BindView(R.id.et_assignment_jjmm)
    EditText etAssignmentJjmm;
    @BindView(R.id.ll_wsskk)
    LinearLayout llWsskk;
    private Context context = this;
    private TenderForTransferModel tenderForModel;
    private String tzje = "";
    private String id = "";
    private AccountModel accountModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy_assignment_details);
        BaseApplication baseApplication = (BaseApplication) getApplication();
        accountModel = baseApplication.getAccountModel();
        Intent intent = getIntent();
        id = intent.getStringExtra("zaizid");
        ButterKnife.bind(this);
        initData();
    }

    private void initData() {
        etAssignmentTzje.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (b) {
                } else {
                    if (tzje.equals(etAssignmentTzje.getText().toString())) {
                    } else {
                        tzje = etAssignmentTzje.getText().toString();
                        if (StringUtil.isEmpty(tzje)) {
                            etAssignmentTzje.setText("--");
                            tvAssignmentYjsy.setText("--");
                            tvAssignmentZrsy.setText("--");
                            tvAssignmentSjyf.setText("--");
                        }
                        initInterestInfo();

                    }
                    SoftInputUtil.hideSoftKeyboard(etAssignmentTzje);
                }
            }
        });
        llWsskk.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                llWsskk.setFocusable(true);
                llWsskk.setFocusableInTouchMode(true);
                llWsskk.requestFocus();
                return false;
            }
        });
        if (accountModel!=null){
            tvAssignmentMoney.setText(accountModel.getBALANCE()+"元");
        }


    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        return super.dispatchTouchEvent(ev);
    }

    private void initInterestInfo() {
        showLoadingDialog();
        BizDataAsyncTask<InterestTotalForTransferModel> getInterestTotal = new BizDataAsyncTask<InterestTotalForTransferModel>() {
            @Override
            protected InterestTotalForTransferModel doExecute() throws ZYException, BizFailure {
                return TenderForTransferBiz.getInterest(id, etAssignmentTzje.getText().toString());
            }

            @Override
            protected void onExecuteSucceeded(InterestTotalForTransferModel interestTotalForTransferModel) {
                hideLoadingDialog();
                tvAssignmentYjsy.setText(interestTotalForTransferModel.getINTEREST_TOTAL() + "元");
                tvAssignmentZrsy.setText(interestTotalForTransferModel.getDISCOUNT() + "元");
                tvAssignmentSjyf.setText(interestTotalForTransferModel.getACTUAL_ACCOUNT() + "元");
            }

            @Override
            protected void OnExecuteFailed(String error) {
                hideLoadingDialog();
                ToastUtils.showTost(context, error);
            }
        };
        getInterestTotal.execute();
    }

    @OnClick({R.id.iv_assignment_back, R.id.tv_assignment_chongzhi, R.id.tv_assignment_qegm, R.id.tv_assignment_forget_psw, R.id.tv_assignment_ljgm})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_assignment_back:
                finish();
                break;
            case R.id.tv_assignment_chongzhi:
                if (accountModel.getIDCARDVERIFYFLG().toString().equals("0")||accountModel.getBANKFLG().toString().equals("0")){
                    Intent intent = new Intent(BuyAssignmentDetailsActivity.this, AccountRealNameActivity.class);
                    intent.putExtra("RealName",1);
                    intent.putExtra("UserName",accountModel.getUSERNAME());
                    intent.putExtra("UserID",accountModel.getIDCARD());
                    showToast("请先验证四要素");
                    startActivity(intent);
                }else{
                    Intent intent = new Intent(BuyAssignmentDetailsActivity.this, AccountChargeActivity.class);
                    startActivity(intent);
                }


                break;
            case R.id.tv_assignment_qegm:
                initQegm();
                break;
            case R.id.tv_assignment_forget_psw:
                Intent intent_forget = new Intent(BuyAssignmentDetailsActivity.this, ForgetJYPswStepOneActivity.class);
                startActivity(intent_forget);
                break;
            case R.id.tv_assignment_ljgm:
                //购买
                initLJgm();
                break;
        }
    }

    private void initLJgm() {
        if (etAssignmentTzje.getText().toString().isEmpty()) {
            ToastUtils.showTost(context, "投资金额不能为空");
            return;
        }
        if (etAssignmentJjmm.getText().toString().isEmpty()) {
            ToastUtils.showTost(context, "请填写交易密码");
            return;
        }
        showLoadingDialog();
        BizDataAsyncTask<String> getLjgm = new BizDataAsyncTask<String>() {
            @Override
            protected String doExecute() throws ZYException, BizFailure {
                return TenderForTransferBiz.biddingForTransfer(id, tzje, etAssignmentJjmm.getText().toString());
            }

            @Override
            protected void onExecuteSucceeded(String s) {
                hideLoadingDialog();
                ToastUtils.showTost(context, "购买成功");
                Intent i = new Intent();
                i.setAction("zhaizhuan");
                BaseApplication.getAppContext().sendBroadcast(i);
                AssignmentDetailsActivity.mInstace.finish();
                finish();
            }

            @Override
            protected void OnExecuteFailed(String error) {
                hideLoadingDialog();
                ToastUtils.showTost(context, error.toString());
            }
        };
        getLjgm.execute();
    }

    private void initQegm() {

        showLoadingDialog();
        BizDataAsyncTask<TenderForTransferModel> getTenderAll = new BizDataAsyncTask<TenderForTransferModel>() {
            @Override
            protected TenderForTransferModel doExecute() throws ZYException, BizFailure {
                return TenderForTransferBiz.getTender(id);
            }

            @Override
            protected void onExecuteSucceeded(TenderForTransferModel tenderForTransferModel) {
                hideLoadingDialog();
                tenderForModel = tenderForTransferModel;
                etAssignmentTzje.setText(tenderForTransferModel.getALL_AMOUNT());
                tvAssignmentYjsy.setText(tenderForTransferModel.getEXPECTED_INTEREST() + "元");
                tvAssignmentZrsy.setText(tenderForTransferModel.getDISCOUNT() + "元");
                tvAssignmentSjyf.setText(tenderForTransferModel.getACTUAL_ACCOUNT() + "元");
                tzje = tenderForTransferModel.getALL_AMOUNT();
            }

            @Override
            protected void OnExecuteFailed(String error) {
                hideLoadingDialog();
            }
        };
        getTenderAll.execute();
    }
}
