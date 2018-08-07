package com.desjf.dsjr.fragment.bank;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.alibaba.fastjson.JSON;
import com.desjf.dsjr.R;
import com.desjf.dsjr.activity.AccountChargeActivity;
import com.desjf.dsjr.activity.BankWebViewActivity;
import com.desjf.dsjr.base.BaseApplication;
import com.desjf.dsjr.base.BaseFragment;
import com.desjf.dsjr.bean.NewRegBean;
import com.desjf.dsjr.biz.retrofit.BankHttpUtil;
import com.desjf.dsjr.biz.retrofit.CallUtils;
import com.desjf.dsjr.model.AccountModel;
import com.desjf.dsjr.model.bankModel.BankUserBalanceModel;
import com.desjf.dsjr.util.MyDialogUtil;
import com.desjf.dsjr.util.NumberUtil;
import com.desjf.dsjr.utils.ButtonUtils;
import com.desjf.dsjr.utils.ToastUtils;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @Author YinL
 * @Date 2018/4/26
 * @Describe 快捷充值Fragment
 */

public class FastRechargeFragment extends BaseFragment {

    @BindView(R.id.btn_recharge)
    Button btnRecharge;//充值按钮
    @BindView(R.id.et_recharge_amount)
    EditText rechargeAmount;

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

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_fast_recharge, container, false);
        ButterKnife.bind(this, view);

        BaseApplication baseApplication = (BaseApplication) getActivity().getApplication();
        accountModel = baseApplication.getAccountModel();
        if (accountModel!=null){
            phone = accountModel.getPHONENUMBER();
            sys = accountModel.getOPENFORE();
        }

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        BaseApplication baseApplication = (BaseApplication) getActivity().getApplication();
        accountModel = baseApplication.getAccountModel();
        if (accountModel!=null){
            phone = accountModel.getPHONENUMBER();
            sys = accountModel.getOPENFORE();
        }
    }

    @OnClick({R.id.btn_recharge})
    public void OnClick(View view){
        switch (view.getId()) {
            case R.id.btn_recharge:
                if(ButtonUtils.isFastClick()) {
                    charge();
                }
                break;
        }

    }

    private void charge() {
        if (rechargeAmount.getText().toString().isEmpty()) {
            ToastUtils.showTost(getActivity(), "请输入充值金额");
            return;
        } if (rechargeAmount.getText().toString().endsWith(".")||rechargeAmount.getText().toString().startsWith(".")) {
            ToastUtils.showTost(getActivity(), "请输入正确的充值金额");
            return;
        }if (NumberUtil.getNumber(rechargeAmount.getText().toString())>2) {
            ToastUtils.showTost(getActivity(), "您的输入有误，最多小数点后两位数字");
            return;
        }
        else {
            double i = Double.parseDouble(rechargeAmount.getText().toString());
            if (i<=0) {
                //            ToastUtils.showTost(context, "提现金额不少于" + with_less + "元");
                MyDialogUtil.showSimpleDialog(getActivity(),"充值金额应大于" + 0 + "元","知道了");
                return;
            }
        }

        CallUtils.call(getActivity(), BankHttpUtil.getHttpRequestService().charge(phone, rechargeAmount.getText().toString()), new CallUtils.MyCallListener() {
            @Override
            public void onRespnseSuccess(String jsonString) {
                NewRegBean newRegBean= JSON.parseObject(jsonString,NewRegBean.class);
                Intent i=new Intent(getActivity(),BankWebViewActivity.class);
                i.putExtra("newRegBean",newRegBean);
                i.putExtra("type",2);
                startActivity(i);

            }
        });
    }


    @Override
    public void onDetach() {
        super.onDetach();

    }


}
