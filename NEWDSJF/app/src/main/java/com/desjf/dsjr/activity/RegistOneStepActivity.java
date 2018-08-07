package com.desjf.dsjr.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.desjf.dsjr.R;
import com.desjf.dsjr.base.BaseActivity;
import com.desjf.dsjr.biz.RegistBiz;
import com.desjf.dsjr.biz.exception.BizFailure;
import com.desjf.dsjr.biz.exception.ZYException;
import com.desjf.dsjr.biz.task.BizDataAsyncTask;
import com.desjf.dsjr.utils.PreferenceCache;
import com.desjf.dsjr.utils.ToastUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RegistOneStepActivity extends BaseActivity {
    @BindView(R.id.iv_clear)
    ImageView ivClear;
    private Context context = this;
    @BindView(R.id.tv_register_next)
    TextView tvRegisterNext;
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.et_phone)
    EditText etPhone;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regist_one_step);
        ButterKnife.bind(this);
        initData();
    }
    private void initData() {
        //edit内容监听
        etPhone.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                ivClear.setVisibility(View.INVISIBLE);
            }
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }
            @Override
            public void afterTextChanged(final Editable editable) {
                ivClear.setVisibility(View.VISIBLE);
                ivClear.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        editable.clear();
                        ivClear.setVisibility(View.INVISIBLE);
                    }
                });
            }
        });
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e("","");
                finish();
            }
        });
    }

    @OnClick(R.id.tv_register_next)
    public void onClick() {
        if (etPhone.getText().toString().isEmpty()) {
            ToastUtils.showTost(this, "请输入手机号");
            return;
        }
        showLoadingDialog();
        BizDataAsyncTask<String> checkPhone = new BizDataAsyncTask<String>() {
            @Override
            protected String doExecute() throws ZYException, BizFailure {
                return RegistBiz.getCheckPhone(etPhone.getText().toString());
            }
            @Override
            protected void onExecuteSucceeded(String s) {
                hideLoadingDialog();
                //下一步
                Intent intent = new Intent(RegistOneStepActivity.this, RegistSecondStepActivity.class);
                PreferenceCache.putPhoneNum(etPhone.getText().toString());
                intent.putExtra("phone", etPhone.getText().toString());
                startActivity(intent);
                finish();
            }
            @Override
            protected void OnExecuteFailed(String error) {
                hideLoadingDialog();
                ToastUtils.showTost(context, error.toString());
            }
        };
        checkPhone.execute();
    }

}
