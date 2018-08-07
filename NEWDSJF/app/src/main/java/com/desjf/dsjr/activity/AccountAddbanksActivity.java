package com.desjf.dsjr.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.desjf.dsjr.R;
import com.desjf.dsjr.activity.bankActivity.BankAccountMessageActivity;
import com.desjf.dsjr.base.BaseActivity;
import com.desjf.dsjr.base.BaseApplication;
import com.desjf.dsjr.biz.AreaBiz;
import com.desjf.dsjr.biz.BanksBiz;
import com.desjf.dsjr.biz.exception.BizFailure;
import com.desjf.dsjr.biz.exception.ZYException;
import com.desjf.dsjr.biz.task.BizDataAsyncTask;
import com.desjf.dsjr.model.AreaListModel;
import com.desjf.dsjr.model.BanksModel;
import com.desjf.dsjr.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AccountAddbanksActivity extends BaseActivity {
    private Context context = this;
    @BindView(R.id.iv_add_banks_back)
    ImageView ivAddBanksBack;
    @BindView(R.id.sp_choose_bank)
    Spinner spChooseBank;
    @BindView(R.id.sp_choose_province)
    Spinner spChooseProvince;
    @BindView(R.id.sp_choose_city)
    Spinner spChooseCity;
    @BindView(R.id.et_banks_name)
    EditText etBanksName;
    @BindView(R.id.et_banks_idcard)
    EditText etBanksIdcard;
    @BindView(R.id.et_banks_idcard_twice)
    EditText etBanksIdcardTwice;
    @BindView(R.id.tv_add_banks_bangd)
    TextView tvAddBanksBangd;
    private ArrayAdapter<String> banksAdapter,provinceAdapter,cityAdapter;
    private List<String> list_banks,list_province,list_city;
    private String parentId = "0";
    private String bank,province,city;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_addbanks);






        ButterKnife.bind(this);
        //获取银行
        getBanks();
        //获取地区
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
                ToastUtils.showTost(context,error);
            }
        };
        getArea.execute();
    }

    private void initProvince(final List<AreaListModel> areaListModels) {
        if (parentId.equals("0")){
            list_province = new ArrayList<>();
            list_province.clear();
            for (int i=0;i<areaListModels.size();i++){
                list_province.add(areaListModels.get(i).getNAME());
            }
            provinceAdapter = new ArrayAdapter<String>(context,android.R.layout.simple_spinner_item,list_province);
            provinceAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spChooseProvince.setAdapter(provinceAdapter);
            provinceAdapter.notifyDataSetChanged();

            spChooseProvince.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                        getProvince(areaListModels.get(i).getID());
                        parentId= areaListModels.get(i).getID();
                        province = areaListModels.get(i).getID();
                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {

                }
            });
        }else{
            list_city = new ArrayList<>();
            list_city.clear();
            for (int i=0;i<areaListModels.size();i++){
                list_city.add(areaListModels.get(i).getNAME());
            }
            cityAdapter = new ArrayAdapter<String>(context,android.R.layout.simple_spinner_item,list_city);
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
               ToastUtils.showTost(context,error);
           }
       };
       getBanks.execute();
    }

    private void initBanks(final List<BanksModel> banksModels) {
        list_banks = new ArrayList<>();
        list_banks.clear();
        for (int i=0;i<banksModels.size();i++){
            list_banks.add(banksModels.get(i).getNAME());
        }
        banksAdapter = new ArrayAdapter<String>(context,android.R.layout.simple_spinner_item,list_banks);
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

    @OnClick({R.id.iv_add_banks_back, R.id.tv_add_banks_bangd})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_add_banks_back:
                finish();
                break;
            case R.id.tv_add_banks_bangd:
                //绑定银行卡
                forBanksCard();
                break;
        }
    }

    private void forBanksCard() {
        if (etBanksName.getText().toString().isEmpty()){
            ToastUtils.showTost(context,"请输入支行名称");
            return;
        }
        if (etBanksIdcard.getText().toString().isEmpty()){
            ToastUtils.showTost(context,"请输入银行卡号");
            return;
        }
        if (etBanksIdcardTwice.getText().toString().isEmpty()){
            ToastUtils.showTost(context,"请确认银行卡号");
            return;
        }
        if (!etBanksIdcardTwice.getText().toString().equals(etBanksIdcard.getText().toString())){
            ToastUtils.showTost(context,"两次输入不一致");
            return;
        }
        showLoadingDialog();
        BizDataAsyncTask<String> getbindBank = new BizDataAsyncTask<String>() {
            @Override
            protected String doExecute() throws ZYException, BizFailure {
                return BanksBiz.getbindBank(bank,province,city,etBanksName.getText().toString(),etBanksIdcard.getText().toString());
            }

            @Override
            protected void onExecuteSucceeded(String s) {
                hideLoadingDialog();
                ToastUtils.showTost(context,"添加成功");
                Intent intent = new Intent();
                intent.setAction("withdraw");
                BaseApplication.getAppContext().sendBroadcast(intent);
                BankAccountMessageActivity.mInstace.finish();
                finish();
            }

            @Override
            protected void OnExecuteFailed(String error) {
                hideLoadingDialog();
                ToastUtils.showTost(context,error.toString());
            }
        };
        getbindBank.execute();
    }
}
