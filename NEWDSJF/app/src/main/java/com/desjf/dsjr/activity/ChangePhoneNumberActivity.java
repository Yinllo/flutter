package com.desjf.dsjr.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.alibaba.fastjson.JSON;
import com.desjf.dsjr.R;
import com.desjf.dsjr.base.BaseActivity;
import com.desjf.dsjr.base.BaseApplication;
import com.desjf.dsjr.bean.NewRegBean;
import com.desjf.dsjr.biz.retrofit.BankHttpUtil;
import com.desjf.dsjr.biz.retrofit.BankHttpUtils;
import com.desjf.dsjr.biz.retrofit.CallUtil;
import com.desjf.dsjr.biz.retrofit.CallUtils;
import com.desjf.dsjr.biz.retrofit.InitHttpUtil;
import com.desjf.dsjr.model.AccountModel;
import com.desjf.dsjr.model.bankModel.BankCodeModel;
import com.desjf.dsjr.model.bankModel.BankUserInfModel;
import com.desjf.dsjr.model.bankModel.BankUserModel;
import com.desjf.dsjr.utils.PreferenceCache;
import com.desjf.dsjr.utils.ToastUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;

public class ChangePhoneNumberActivity extends BaseActivity {

    @BindView(R.id.et_phoneNumber)
    EditText etPhone;
//    @BindView(R.id.et_cardNumber)
//    EditText etCard;
    String bankNumber="";
    private AccountModel accountModel;
    String phoneCheck="";//在点击确认注册时用来再一次判断手机号码是否正确：1为正确(手机号码不存在数据库中)   0为错误
    private String phone;//手机号码
    BankUserModel bankUserModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_phone_number);
        ButterKnife.bind(this);

        BaseApplication baseApplication = (BaseApplication) getApplication();
        accountModel = baseApplication.getAccountModel();


    }

    @OnClick({R.id.et_phoneNumber,R.id.iv_back,R.id.tv_ok_btn})
    public void OnClick(View view){

        switch(view.getId()){
            case R.id.iv_back:

                finish();

                break;
            case R.id.tv_ok_btn:

              getBankCard();

                break;
        }

    }

    private void getBankCard(){
        phone=etPhone.getText().toString().trim();
        String telRegex = "[1][345789]\\d{9}";//"[1]"代表第1位为数字1，"[358]"代表第二位可以为3、5、8中的一个，"\\d{9}"代表后面是可以是0～9的数字，有9位。
        if(!phone.matches(telRegex)){
            ToastUtils.showShortTost(ChangePhoneNumberActivity.this,"手机号码有误，请重填");
            return;
        }

        CallUtils.call(ChangePhoneNumberActivity.this, BankHttpUtils.getHttpRequestService().getPersonalData(PreferenceCache.getToken()), new CallUtils.MyCallListener() {
            @Override
            public void onRespnseSuccess(String jsonString) {
                bankUserModel=JSON.parseObject(jsonString,BankUserModel.class);
            }
        });
        CallUtil.call(ChangePhoneNumberActivity.this, InitHttpUtil.getHttpRequestService().checkMobile(phone), new CallUtil.MyCallListener() {
            @Override
            public void onRespnseSuccess(String jsonString) {
                BankCodeModel bankCodeModel=JSON.parseObject(jsonString,BankCodeModel.class);
                if(bankCodeModel.isStatus()){
                    //手机号码不存在数据库中
                    phoneCheck="1";

                    CallUtils.call(ChangePhoneNumberActivity.this, BankHttpUtils.getHttpRequestService().queryUserInf(PreferenceCache.getToken()), new CallUtils.MyCallListener() {
                        @Override
                        public void onRespnseSuccess(String jsonString) {
                            BankUserInfModel bankUserInfModel = JSON.parseObject(jsonString, BankUserInfModel.class);
                            if (bankUserInfModel.getUsermessage() != null) {
                                CallUtils.call(ChangePhoneNumberActivity.this, BankHttpUtil.getHttpRequestService().changePhone(phone, bankUserInfModel.getUsermessage().get(0).getMbl_no(),
                                        bankUserInfModel.getUsermessage().get(0).getUsr_nm(),"身份证",bankUserModel.getIdentNoA()), new CallUtils.MyCallListener() {
                                    @Override
                                    public void onRespnseSuccess(String jsonString) {
                                        NewRegBean newRegBean= JSON.parseObject(jsonString,NewRegBean.class);
                                        Intent i=new Intent(ChangePhoneNumberActivity.this,BankWebViewActivity.class);
                                        i.putExtra("newRegBean",newRegBean);
                                        i.putExtra("type",6);
                                        startActivity(i);

                                    }
                                });
                            }
                        }

                    });

                }else {
                    phoneCheck="0";
                    ToastUtils.showLongTost(ChangePhoneNumberActivity.this,"手机号码已存在或输入有误，请重填");
                }
            }

            @Override
            public void onRespnseFailure(Call<String> call, Throwable t) {
                phoneCheck="0";
                ToastUtils.showLongTost(ChangePhoneNumberActivity.this,"手机号码已存在或输入有误，请重填");
            }
        });

    }

}
