package com.desjf.dsjr.activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.desjf.dsjr.R;
import com.desjf.dsjr.activity.bankActivity.BankRechargeRecordActivity;
import com.desjf.dsjr.base.BaseActivity;
import com.desjf.dsjr.base.BaseApplication;
import com.desjf.dsjr.bean.NewRegBean;
import com.desjf.dsjr.biz.retrofit.BankHttpUtil;
import com.desjf.dsjr.biz.retrofit.BankHttpUtils;
import com.desjf.dsjr.biz.retrofit.CallUtils;
import com.desjf.dsjr.model.AccountModel;
import com.desjf.dsjr.model.bankModel.BankUserBalanceModel;
import com.desjf.dsjr.model.bankModel.BankUserInfModel;
import com.desjf.dsjr.util.MyDialogUtil;
import com.desjf.dsjr.util.NumberUtil;
import com.desjf.dsjr.utils.ButtonUtils;
import com.desjf.dsjr.utils.DataUtil;
import com.desjf.dsjr.utils.PreferenceCache;
import com.desjf.dsjr.utils.ToastUtils;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AccountChargeActivity extends BaseActivity {
    @BindView(R.id.btn_recharge)
    Button btnRecharge;
    @BindView(R.id.tv_recharge_record)
    TextView tvRechargeRecord;
    private Context context = this;
    private String reqSn = "";
    @BindView(R.id.iv_charge_back)
    ImageView ivChargeBack;
    @BindView(R.id.tv_kyye)
    TextView tvKyye;
    @BindView(R.id.et_charge_amount)
    EditText etChargeAmount;

//    @BindView(R.id.bank_number)
//    TextView bankNumber;
    @BindView(R.id.bh_bank_number)
    TextView bhBankNumber;

//    @BindView(R.id.et_charge_sms)
//    EditText etChargeSms;
//    @BindView(R.id.tv_charge_sms)
//    TextView tvChargeSms;
    private AccountModel accountModel;
    //sys 为1代表身份证等验证已经通过 直接验证手机验证码
    //为0 则代表通联四要素验证没通过 要走通联四要素认证
    private String sys;
    private String phone;
    private Timer timer,timer_btn;
    private TimerTask timerTask,timerTask_btn;
    public static AccountChargeActivity mInstace = null;
    private double money;//用户输入的充值金额
    private double totalMoney;//用户
    private int count = 60;
    private int countClick=5;
    private int toRegist = 0;
    List<BankUserBalanceModel.UsermessageBean> list=null;

    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler(){
        @Override
        public void dispatchMessage(Message msg) {
            if (countClick>=0){
                btnRecharge.setText("正在充值...");
                btnRecharge.setClickable(false);
                countClick--;
            }else{
                resetBtn();
            }
        }
    };

    private void resetBtn() {
        btnRecharge.setClickable(true);
        btnRecharge.setFocusable(true);
        btnRecharge.setText("充值");
        countClick=5;
        timerTask_btn.cancel();
        timer_btn.cancel();
        timerTask_btn = null;
        timer_btn = null;
    }

    private void resetBtns() {
        btnRecharge.setClickable(true);
        btnRecharge.setFocusable(true);
        btnRecharge.setText("充值");
        countClick=5;
    }

    private void resetTimer() {
//        tvChargeSms.setText("获取短信验证码");
//        tvChargeSms.setClickable(true);
        count = 60;
        timerTask.cancel();
        timer.cancel();
        timerTask = null;
        timer = null;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_charge);
        ButterKnife.bind(this);
        mInstace=this;
        BaseApplication baseApplication = (BaseApplication) getApplication();
        accountModel = baseApplication.getAccountModel();
        if (accountModel!=null){
            phone = accountModel.getPHONENUMBER();
            sys = accountModel.getOPENFORE();
        }
        initData();
    }

    private void initData() {

        CallUtils.call(AccountChargeActivity.this, BankHttpUtils.getHttpRequestService().queryUserInf(PreferenceCache.getToken()), new CallUtils.MyCallListener() {
            @Override
            public void onRespnseSuccess(String jsonString) {
                BankUserInfModel bankUserInfModel=JSON.parseObject(jsonString,BankUserInfModel.class);
//                StringBuilder s=new StringBuilder(bankUserInfModel.getUsermessage().get(0).getCap_crd_no());
//                s.replace(6,15,"******");
//                bankNumber.setText(s);
                bhBankNumber.setText(bankUserInfModel.getUsermessage().get(bankUserInfModel.getUsermessage().size()-1).getPlaCustId());
            }
        });
        CallUtils.call(AccountChargeActivity.this, BankHttpUtils.getHttpRequestService().QueryBalance(PreferenceCache.getToken()), new CallUtils.MyCallListener() {
            @Override
            public void onRespnseSuccess(String jsonString) {

                BankUserBalanceModel bankUserBalanceModel=JSON.parseObject(jsonString,BankUserBalanceModel.class);
                list=bankUserBalanceModel.getUsermessage();
                for(int i=0;i<list.size();i++){
                    if(list.get(i).getCap_typ().equals("1")){
                        //可用余额
                        if(list.get(i).getAvlBal().equals("0")){
                            tvKyye.setText("0.00元");
                        }else {
                            tvKyye.setText(DataUtil.toMoney(Float.parseFloat(list.get(i).getAvlBal())) + "元");
                        }
                    }
                }
            }
        });

    }

    @OnClick({R.id.iv_charge_back, R.id.btn_recharge,R.id.tv_recharge_record})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_charge_back:
                finish();
                break;
            case R.id.btn_recharge:
                if(ButtonUtils.isFastClick()) {
                    charge();
                }
                break;
            case R.id.tv_recharge_record:
                Intent intent = new Intent(AccountChargeActivity.this,BankRechargeRecordActivity.class);
                startActivity(intent);
                break;
        }

    }

    private void charge() {
        if (etChargeAmount.getText().toString().isEmpty()) {
            ToastUtils.showTost(context, "请输入充值金额");
            return;
        } if (etChargeAmount.getText().toString().endsWith(".")||etChargeAmount.getText().toString().startsWith(".")) {
            ToastUtils.showTost(context, "请输入正确的充值金额");
            return;
        }if (NumberUtil.getNumber(etChargeAmount.getText().toString())>2) {
            ToastUtils.showTost(context, "您的输入有误，最多小数点后两位数字");
            return;
        }
        else {
            double i = Double.parseDouble(etChargeAmount.getText().toString());
            if (i<=0) {
                //            ToastUtils.showTost(context, "提现金额不少于" + with_less + "元");
                MyDialogUtil.showSimpleDialog(context,"充值金额应大于" + 0 + "元","知道了");
                return;
            }
        }

        CallUtils.call(AccountChargeActivity.this, BankHttpUtil.getHttpRequestService().charge(phone, etChargeAmount.getText().toString()), new CallUtils.MyCallListener() {
            @Override
            public void onRespnseSuccess(String jsonString) {
                NewRegBean newRegBean= JSON.parseObject(jsonString,NewRegBean.class);
                Intent i=new Intent(AccountChargeActivity.this,BankWebViewActivity.class);
                i.putExtra("newRegBean",newRegBean);
                i.putExtra("type",2);
                startActivity(i);

            }
        });

    }

    private void tinitCharge() {
        resetBtns();
    }

    private void initCharge() {

    }

}
