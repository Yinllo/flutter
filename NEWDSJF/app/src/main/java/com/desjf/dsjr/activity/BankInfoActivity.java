package com.desjf.dsjr.activity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.desjf.dsjr.R;
import com.desjf.dsjr.activity.bankActivity.BankAccountMessageActivity;
import com.desjf.dsjr.base.BaseActivity;
import com.desjf.dsjr.base.BaseApplication;
import com.desjf.dsjr.biz.AreaBiz;
import com.desjf.dsjr.biz.BanksBiz;
import com.desjf.dsjr.biz.exception.BizFailure;
import com.desjf.dsjr.biz.exception.ZYException;
import com.desjf.dsjr.biz.task.BizDataAsyncTask;
import com.desjf.dsjr.model.AccountModel;
import com.desjf.dsjr.model.AreaListModel;
import com.desjf.dsjr.model.BanksModel;
import com.desjf.dsjr.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class BankInfoActivity extends BaseActivity {

    @BindView(R.id.iv_bank_info_back)
    ImageView ivBankInfoBack;
    @BindView(R.id.iv_bank_info)
    ImageView ivBankInfo;
    @BindView(R.id.tv_bank_info_name)
    TextView tvBankInfoName;
    @BindView(R.id.tv_bank_info_card)
    TextView tvBankInfoCard;
    @BindView(R.id.tv_reset_bank_info)
    TextView tvResetBankInfo;
    @BindView(R.id.sp_city)
    Spinner spCity;
    @BindView(R.id.sp_area)
    Spinner spArea;
    @BindView(R.id.ll_location)
    LinearLayout llLocation;
    @BindView(R.id.ll_zhname)
    LinearLayout llZhname;
    @BindView(R.id.sp_subank)
    Spinner spSubank;
    @BindView(R.id.ll_subank)
    LinearLayout llSubank;
    @BindView(R.id.tv_reset_bank_save)
    TextView tvResetBankSave;
    @BindView(R.id.et_zhname)
    EditText etZhname;
    private AccountModel accountModel;
    private ArrayAdapter<String> banksAdapter, provinceAdapter, cityAdapter;
    private List<String> list_banks, list_province, list_city;
    private Context context = this;
    private String parentId = "0";
    private String bank, province, city;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bank_info);
        ButterKnife.bind(this);
        BaseApplication baseApplication = (BaseApplication) getApplication();
        accountModel = baseApplication.getAccountModel();
        bank = accountModel.getBANKID();
        province = accountModel.getPROVINCEID();
        city = accountModel.getCITYID();
        parentId = accountModel.getPROVINCEID();
        getBankInfo();
        getProvince();
        getCity();
        initData();
    }

    private void getCity() {
        showLoadingDialog();
        BizDataAsyncTask<List<AreaListModel>> getArea = new BizDataAsyncTask<List<AreaListModel>>() {
            @Override
            protected List<AreaListModel> doExecute() throws ZYException, BizFailure {
                return AreaBiz.getArea(parentId);
            }

            @Override
            protected void onExecuteSucceeded(List<AreaListModel> areaListModels) {
                hideLoadingDialog();
                initCity(areaListModels);
            }

            @Override
            protected void OnExecuteFailed(String error) {
                hideLoadingDialog();
                ToastUtils.showTost(context, error);
            }
        };
        getArea.execute();
    }

    private void initCity(final List<AreaListModel> areaListModels) {
        list_city = new ArrayList<>();
        list_city.clear();
        for (int i = 0; i < areaListModels.size(); i++) {
            list_city.add(areaListModels.get(i).getNAME());
        }

        cityAdapter = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_item, list_city);
        cityAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spArea.setAdapter(cityAdapter);
        for (int i = 0; i < list_city.size(); i++) {
            if (list_city.get(i).toString().equals(accountModel.getCITYNAME())) {
                spArea.setSelection(i, true);
            }
        }
        cityAdapter.notifyDataSetChanged();
        spArea.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                city = areaListModels.get(i).getID();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private void getProvince() {
        showLoadingDialog();
        BizDataAsyncTask<List<AreaListModel>> getArea = new BizDataAsyncTask<List<AreaListModel>>() {
            @Override
            protected List<AreaListModel> doExecute() throws ZYException, BizFailure {
                return AreaBiz.getArea("0");
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
        list_province = new ArrayList<>();
        list_province.clear();
        for (int i = 0; i < areaListModels.size(); i++) {
            list_province.add(areaListModels.get(i).getNAME());
        }

        provinceAdapter = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_item, list_province);
        provinceAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spCity.setAdapter(provinceAdapter);
        for (int i = 0; i < list_province.size(); i++) {
            if (list_province.get(i).toString().equals(accountModel.getPROVINCENAME())) {
                spCity.setSelection(i, true);
            }
        }
        provinceAdapter.notifyDataSetChanged();

        spCity.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
//                    getProvince(areaListModels.get(i).getID());

                parentId = areaListModels.get(i).getID();
                province = areaListModels.get(i).getID();
                getCity();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private void getBankInfo() {
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
                ToastUtils.showTost(BankInfoActivity.this, error);
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
        spSubank.setAdapter(banksAdapter);
        for (int i = 0; i < list_banks.size(); i++) {
            if (list_banks.get(i).toString().equals(accountModel.getBANKREALNAME())) {
                spSubank.setSelection(i, true);
            }
        }
        banksAdapter.notifyDataSetChanged();
        spSubank.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                bank = banksModels.get(i).getID();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private void initData() {
        Glide.with(BankInfoActivity.this).load(accountModel.getBANKIMG()).into(ivBankInfo);
        tvBankInfoCard.setText(accountModel.getBANKCARDNOCONCEAL());
        tvBankInfoName.setText("持卡人:" + accountModel.getUSERNAMECONCEAL());
        etZhname.setText(accountModel.getBRANCH());

        tvResetBankSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                initChangeInfo();
            }
        });


    }

    private void initChangeInfo() {
        showLoadingDialog();
        BizDataAsyncTask<String> getInfo = new BizDataAsyncTask<String>() {
            @Override
            protected String doExecute() throws ZYException, BizFailure {
                return BanksBiz.getChange(parentId, city, etZhname.getText().toString(), bank);
            }

            @Override
            protected void onExecuteSucceeded(String s) {
                hideLoadingDialog();
                ToastUtils.showTost(context, "更改银行卡信息成功");
                BankAccountMessageActivity.mInstace.finish();
                finish();
            }

            @Override
            protected void OnExecuteFailed(String error) {
                hideLoadingDialog();
                ToastUtils.showTost(context, error);
            }
        };
        getInfo.execute();
    }

    @OnClick({R.id.iv_bank_info_back, R.id.tv_reset_bank_info})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_bank_info_back:
                finish();
                break;
            case R.id.tv_reset_bank_info:





                tvResetBankInfo.setVisibility(View.GONE);
                tvResetBankSave.setVisibility(View.VISIBLE);
                llLocation.setVisibility(View.VISIBLE);
                llSubank.setVisibility(View.VISIBLE);
                llZhname.setVisibility(View.VISIBLE);
                break;
        }

    }
}
