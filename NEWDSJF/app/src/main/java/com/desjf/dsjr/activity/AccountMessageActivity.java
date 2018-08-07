package com.desjf.dsjr.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.desjf.dsjr.R;
import com.desjf.dsjr.activity.cyaccount.BankModifyActiviy;
import com.desjf.dsjr.base.BaseActivity;
import com.desjf.dsjr.base.BaseApplication;
import com.desjf.dsjr.model.AccountModel;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AccountMessageActivity extends BaseActivity {
    @BindView(R.id.iv_back_account_message)
    ImageView ivBackAccountMessage;
    @BindView(R.id.tv_project_title)
    TextView tvProjectTitle;
    @BindView(R.id.tv_real_or_not)
    TextView tvRealOrNot;
    @BindView(R.id.ll_account_realname)
    LinearLayout llAccountRealname;
    @BindView(R.id.tv_account_phone)
    TextView tvAccountPhone;
    @BindView(R.id.ll_account_phonenum)
    LinearLayout llAccountPhonenum;
    @BindView(R.id.iv_account_message_more)
    ImageView ivAccountMessageMore;
    @BindView(R.id.iv_account_bank_more)
    ImageView ivAccountBankMore;
    @BindView(R.id.tv_account_bank_more)
    TextView tvAccountBankMore;
    @BindView(R.id.ll_account_addbanks)
    LinearLayout llAccountAddbanks;
    @BindView(R.id.ll_account_marks)
    LinearLayout llAccountMarks;

    private AccountModel accountModel;
    private Context context = this;
    public static AccountMessageActivity mInstace = null;

    String msg="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_message);
        BaseApplication baseApplication = (BaseApplication) getApplication();
        accountModel = baseApplication.getAccountModel();
        mInstace = this;
        ButterKnife.bind(this);

        initData();

    }

    private void initData() {
        if (accountModel != null) {
            if (accountModel.getIDCARDVERIFYFLG().toString().equals("0")) {
                tvRealOrNot.setText("未认证");
                ivAccountMessageMore.setVisibility(View.VISIBLE);
            } else {
                tvRealOrNot.setText(accountModel.getIDCARDCONCEAL());
                ivAccountMessageMore.setVisibility(View.GONE);
            }

            if (accountModel.getBANKFLG().equals("0")) {
                //银行账号未绑定
                tvAccountBankMore.setText("添加银行卡");
            } else {
                //银行账号已绑定
                tvAccountBankMore.setText(accountModel.getBANKCARDNOCONCEAL());
            }
            tvAccountPhone.setText(accountModel.getPHONENUMBERCONCEAL());
        }

        ivBackAccountMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }

    @OnClick({R.id.ll_account_addbanks, R.id.ll_account_marks, R.id.ll_account_realname})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_account_addbanks:
                //添加银行卡
                if (accountModel != null) {
                    if (accountModel.getIDCARDVERIFYFLG().toString().equals("0") || accountModel.getBANKFLG().toString().equals("0")) {
                        Intent intent = new Intent(AccountMessageActivity.this, AccountRealNameActivity.class);
                        intent.putExtra("RealName", 1);
                        intent.putExtra("UserName", accountModel.getUSERNAME());
                        intent.putExtra("UserID", accountModel.getIDCARD());
                        startActivity(intent);
                        return;
                    } else {
//                        showToast("银行卡已绑定");

                        Intent intent = new Intent(this, BankModifyActiviy.class);
                        intent.putExtra("RealName", 1);
                        intent.putExtra("UserName", accountModel.getUSERNAME());
                        intent.putExtra("UserID", accountModel.getIDCARD());
                        startActivity(intent);
                        finish();
                    }
                }


                break;
            case R.id.ll_account_marks:
                //我的积分
                if (accountModel != null) {
                    Intent intent_one = new Intent(AccountMessageActivity.this, AccountMyMarksActivity.class);
                    startActivity(intent_one);
                }
                break;
            case R.id.ll_account_realname:
                //实名认证
                if (accountModel != null) {
                    if (accountModel.getIDCARDVERIFYFLG().toString().equals("0")) {
                        Intent intent_two = new Intent(AccountMessageActivity.this, AccountRealNameActivity.class);
                        intent_two.putExtra("RealName", 3);
                        startActivity(intent_two);
                    } else {
                        showToast("您已实名认证");
                        return;
                    }
                }

                break;


        }

    }




}
