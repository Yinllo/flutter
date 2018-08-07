package com.desjf.dsjr.activity.cyaccount;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.desjf.dsjr.R;
import com.desjf.dsjr.base.BaseActivity;
import com.desjf.dsjr.base.BaseApplication;
import com.desjf.dsjr.biz.AccountBiz;
import com.desjf.dsjr.biz.AreaBiz;
import com.desjf.dsjr.biz.BanksBiz;
import com.desjf.dsjr.biz.ChargeBiz;
import com.desjf.dsjr.biz.exception.BizFailure;
import com.desjf.dsjr.biz.exception.ZYException;
import com.desjf.dsjr.biz.task.BizDataAsyncTask;
import com.desjf.dsjr.model.AccountModel;
import com.desjf.dsjr.model.AreaListModel;
import com.desjf.dsjr.model.BanksModel;
import com.desjf.dsjr.model.RechargeSmsModel;
import com.desjf.dsjr.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class BankModifyActiviy extends BaseActivity {

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_bank_modify_activiy);

//        Bundle bundle=getIntent().getBundleExtra(Constant.KEY_INTENT_MODIFY_BANK_INFO);
//
//        if(bundle!=null){
//            AccountModel accountModel= (AccountModel) bundle.getSerializable(Constant.KEY_BUNDLE_MODIFY_BANK_INFO);
//
//        }

        @BindView(R.id.sp_choose_province)
        Spinner spChooseProvince;
        @BindView(R.id.sp_choose_city)
        Spinner spChooseCity;
        @BindView(R.id.sp_choose_bank)
        Spinner spChooseBank;
        @BindView(R.id.et_banks_name)
        EditText etBanksName;
        @BindView(R.id.et_banks_idcard)
        EditText etBanksIdcard;
        @BindView(R.id.et_banks_idcard_twice)
        EditText etBanksIdcardTwice;
        @BindView(R.id.et_banks_idcard_phone_num)
        EditText etBanksIdcardPhoneNum;
        @BindView(R.id.et_banks_idcard_yzm)
        EditText etBanksIdcardYzm;
        @BindView(R.id.et_banks_idcard_emails)
        EditText etBanksIdcardEmails;
        @BindView(R.id.tv_getsms)
        TextView tvGetsms;
        @BindView(R.id.tv_name)
        TextView tvName;
        @BindView(R.id.tv_shenfenzheng)
        TextView tvShenfenzheng;
        private Context context = this;
        @BindView(R.id.iv_real_name_back)
        ImageView ivRealNameBack;
        @BindView(R.id.et_real_name)
        TextView tvRealName;
        @BindView(R.id.et_real_id)
        TextView tvRealId;
        @BindView(R.id.tv_real_name_config)
        TextView tvRealNameConfig;
        private String str = "";
        private List<String> list_banks, list_province, list_city;
        private ArrayAdapter<String> banksAdapter, provinceAdapter, cityAdapter;
        private String parentId = "0";
        private String bank, province, city;
        private AccountModel accountModel;
        private String sys;
        private String phone;
        private Timer timer;
        private TimerTask timerTask;
        private int count = 60;
        private int toRegist = 0;
        private String reqsn;
        private String name, id;
        private int type;
        private Handler mHandler = new Handler() {

            @Override
            public void dispatchMessage(Message msg) {
                if (count >= 0) {
                    tvGetsms.setText("重新发送(" + count + ")");
                    tvGetsms.setClickable(false);
                    count--;
                } else {
                    resetTimer();
                }
            }
        };

    private void resetTimer() {
        tvGetsms.setText("获取验证码");
        tvGetsms.setClickable(true);
        count = 60;
        timerTask.cancel();
        timer.cancel();
        timerTask = null;
        timer = null;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bank_modify_activiy);
        ButterKnife.bind(this);
        BaseApplication baseApplication = (BaseApplication) getApplication();
        accountModel = baseApplication.getAccountModel();
        if (accountModel != null) {
            phone = accountModel.getPHONENUMBER().toString();
            etBanksIdcardPhoneNum.setText(accountModel.getPHONENUMBER());
        }
        Intent intent = getIntent();
        type = intent.getIntExtra("RealName", -1);
        if (type == 2) {
            str = intent.getStringExtra("register");
            if (str != "1") {
                str = "2";
            }
        } else if (type == 1) {
            name = intent.getStringExtra("UserName");
            id = intent.getStringExtra("UserID");
            tvRealName.setText(name);
            tvRealId.setText(id);
            tvName.setText(name);
            tvShenfenzheng.setText(id);
            if (name.isEmpty()){
                tvRealName.setVisibility(View.VISIBLE);
                tvRealId.setVisibility(View.VISIBLE);
                tvName.setVisibility(View.GONE);
                tvShenfenzheng.setVisibility(View.GONE);
            }else{
                tvRealName.setVisibility(View.GONE);
                tvRealId.setVisibility(View.GONE);
                tvName.setVisibility(View.VISIBLE);
                tvShenfenzheng.setVisibility(View.VISIBLE);
            }
        }
        getBanks();
        getProvince(parentId);
    }

    private void getProvince(final String parentId) {
        showLoadingDialog();
        BizDataAsyncTask<List<AreaListModel>> getArea = new BizDataAsyncTask<List<AreaListModel>>() {
            @Override
            protected List<AreaListModel> doExecute() throws ZYException, BizFailure {
                return AreaBiz.getArea(parentId);
            }

            @Override
            protected void onExecuteSucceeded(List<AreaListModel> areaListModels) {
                hideLoadingDialog();
                initProvince(areaListModels);
            }

            @Override
            protected void OnExecuteFailed(String error) {
                hideLoadingDialog();
                ToastUtils.showTost(context, error);
            }
        };
        getArea.execute();
    }

    private void initProvince(final List<AreaListModel> areaListModels) {
        if (parentId.equals("0")) {
            list_province = new ArrayList<>();
            list_province.clear();
            for (int i = 0; i < areaListModels.size(); i++) {
                list_province.add(areaListModels.get(i).getNAME());
            }
            provinceAdapter = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_item, list_province);
            provinceAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spChooseProvince.setAdapter(provinceAdapter);
            provinceAdapter.notifyDataSetChanged();

            spChooseProvince.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    getProvince(areaListModels.get(i).getID());
                    parentId = areaListModels.get(i).getID();
                    province = areaListModels.get(i).getID();
                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {

                }
            });
        } else {
            list_city = new ArrayList<>();
            list_city.clear();
            for (int i = 0; i < areaListModels.size(); i++) {
                list_city.add(areaListModels.get(i).getNAME());
            }
            cityAdapter = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_item, list_city);
            cityAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spChooseCity.setAdapter(cityAdapter);
            cityAdapter.notifyDataSetChanged();
            spChooseCity.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    city = areaListModels.get(i).getID();
                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {

                }
            });
        }


    }

    private void getBanks() {
        showLoadingDialog();
        BizDataAsyncTask<List<BanksModel>> getBanks = new BizDataAsyncTask<List<BanksModel>>() {
            @Override
            protected List<BanksModel> doExecute() throws ZYException, BizFailure {
                return BanksBiz.getBanks();
            }

            @Override
            protected void onExecuteSucceeded(List<BanksModel> banksModels) {
                hideLoadingDialog();
                //处理银行信息
                initBanks(banksModels);
            }

            @Override
            protected void OnExecuteFailed(String error) {
                hideLoadingDialog();
                ToastUtils.showTost(context, error);
            }
        };
        getBanks.execute();
    }

    private void initBanks(final List<BanksModel> banksModels) {
        list_banks = new ArrayList<>();
        list_banks.clear();
        for (int i = 0; i < banksModels.size(); i++) {
            list_banks.add(banksModels.get(i).getNAME());
        }
        banksAdapter = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_item, list_banks);
        banksAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spChooseBank.setAdapter(banksAdapter);
        banksAdapter.notifyDataSetChanged();
        spChooseBank.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                bank = banksModels.get(i).getID();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }

    @OnClick({R.id.iv_real_name_back, R.id.tv_real_name_config, R.id.tv_getsms})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_real_name_back:
                finish();
                break;
            case R.id.tv_real_name_config:
                initYzSms();
                break;
            case R.id.tv_getsms:
                getSms();
                break;
        }
    }

    private void initYzSms() {
//        if (etRealName.getText().toString().isEmpty()) {
//            ToastUtils.showTost(context, "请输入真实姓名");
//            return;
//        }
//        if (etRealId.getText().toString().isEmpty()) {
//            ToastUtils.showTost(context, "请输入身份证号");
//            return;
//        }
        if (etBanksName.getText().toString().isEmpty()) {
            ToastUtils.showTost(context, "请输入支行名称");
            return;
        }
        if (etBanksIdcard.getText().toString().isEmpty()) {
            ToastUtils.showTost(context, "请输入银行卡号");
            return;
        }
        if (etBanksIdcardTwice.getText().toString().isEmpty()) {
            ToastUtils.showTost(context, "请输入确认银行卡号");
            return;
        }
        if (etBanksIdcardYzm.getText().toString().isEmpty()) {
            ToastUtils.showTost(context, "请输入验证码");
            return;
        }
        if (!etBanksIdcardTwice.getText().toString().equals(etBanksIdcard.getText().toString())) {
            ToastUtils.showTost(context, "两次银行卡号输入不一致");
            return;
        }
        showLoadingDialog();
        BizDataAsyncTask<String> getRealNameYZ = new BizDataAsyncTask<String>() {
            @Override
            protected String doExecute() throws ZYException, BizFailure {
                return AccountBiz.getRealNameSmsYz(reqsn, etBanksIdcardYzm.getText().toString());
            }

            @Override
            protected void onExecuteSucceeded(String s) {
                hideLoadingDialog();
                initRealName();
            }

            @Override
            protected void OnExecuteFailed(String error) {
                hideLoadingDialog();
                showToast(error);
            }
        };
        getRealNameYZ.execute();
    }

    private void getSms() {
//        if (etRealName.getText().toString().isEmpty()) {
//            ToastUtils.showTost(context, "请输入真实姓名");
//            return;
//        }
//        if (etRealId.getText().toString().isEmpty()) {
//            ToastUtils.showTost(context, "请输入身份证号");
//            return;
//        }
        if (etBanksName.getText().toString().isEmpty()) {
            ToastUtils.showTost(context, "请输入支行名称");
            return;
        }
        if (etBanksIdcard.getText().toString().isEmpty()) {
            ToastUtils.showTost(context, "请输入银行卡号");
            return;
        }
        if (etBanksIdcardTwice.getText().toString().isEmpty()) {
            ToastUtils.showTost(context, "请输入确认银行卡号");
            return;
        }
        if (!etBanksIdcardTwice.getText().toString().equals(etBanksIdcard.getText().toString())) {
            ToastUtils.showTost(context, "两次银行卡号输入不一致");
            return;
        }
//        showLoadingDialog();
//        BizDataAsyncTask<RechargeSmsModel> getReChargeSmsSecond = new BizDataAsyncTask<RechargeSmsModel>() {
//            @Override
//            protected RechargeSmsModel doExecute() throws ZYException, BizFailure {
//                return ChargeBiz.getRechargeSmsSecond("",phone);
//            }
//
//            @Override
//            protected void onExecuteSucceeded(RechargeSmsModel rechargeSmsModel) {
//                hideLoadingDialog();
//                reqsn = rechargeSmsModel.getREQ_SN().toString();
//                runTimerTask();
//                tvGetsms.setClickable(false);
//                tvGetsms.setFocusable(false);
//            }
//
//            @Override
//            protected void OnExecuteFailed(String error) {
//                hideLoadingDialog();
//                ToastUtils.showTost(context, error.toString());
//            }
//        };
//        getReChargeSmsSecond.execute();
        showLoadingDialog();
        BizDataAsyncTask<RechargeSmsModel> getRechargeSms = new BizDataAsyncTask<RechargeSmsModel>() {
            @Override
            protected RechargeSmsModel doExecute() throws ZYException, BizFailure {
                return ChargeBiz.getRealNameSmsSecond(etBanksIdcardPhoneNum.getText().toString(), bank, etBanksIdcard.getText().toString(), tvRealName.getText().toString(), tvRealId.getText().toString());
            }

            @Override
            protected void onExecuteSucceeded(RechargeSmsModel rechargeSmsModel) {
                hideLoadingDialog();
                reqsn = rechargeSmsModel.getREQ_SN().toString();
                runTimerTask();
                tvGetsms.setClickable(false);
                tvGetsms.setFocusable(false);
            }

            @Override
            protected void OnExecuteFailed(String error) {
                hideLoadingDialog();
                showToast(error);
            }
        };
        getRechargeSms.execute();
    }

    private void runTimerTask() {
        timer = new Timer();
        timerTask = new TimerTask() {
            @Override
            public void run() {
                mHandler.sendEmptyMessage(0);
            }
        };
        timer.schedule(timerTask, 1000, 1000);
    }

    private void initRealName() {
        showLoadingDialog();
        BizDataAsyncTask<String> getRealName = new BizDataAsyncTask<String>() {
            @Override
            protected String doExecute() throws ZYException, BizFailure {
                return AccountBiz.getRealName(tvRealName.getText().toString(), tvRealId.getText().toString(), etBanksIdcard.getText().toString(), bank, province, city, etBanksName.getText().toString(),etBanksIdcardPhoneNum.getText().toString(),reqsn,etBanksIdcardYzm.getText().toString());
            }
            @Override
            protected void onExecuteSucceeded(String s) {
                hideLoadingDialog();
                ToastUtils.showTost(context, "银行卡修改成功");
                if (str.equals("1")) {
//                    Intent intent = new Intent(BankModifyActiviy.this, MainActivity.class);
//                    startActivity(intent);
                    finish();
                } else {
                    Intent intent = new Intent();
                    intent.setAction("withdraw");
                    BaseApplication.getAppContext().sendBroadcast(intent);
                    finish();
                }
            }

            @Override
            protected void OnExecuteFailed(String error) {
                hideLoadingDialog();
                ToastUtils.showTost(context, error);
            }
        };
        getRealName.execute();
    }
}
