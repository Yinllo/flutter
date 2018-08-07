package com.desjf.dsjr.activity;

import android.content.Context;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.desjf.dsjr.R;
import com.desjf.dsjr.base.BaseActivity;
import com.desjf.dsjr.biz.PassWordBiz;
import com.desjf.dsjr.biz.exception.BizFailure;
import com.desjf.dsjr.biz.exception.ZYException;
import com.desjf.dsjr.biz.task.BizDataAsyncTask;
import com.desjf.dsjr.utils.ToastUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ForgetJYPswStepTwoActivity extends BaseActivity {
    @BindView(R.id.iv_forget_two_back)
    ImageView ivForgetTwoBack;
    @BindView(R.id.et_forget_two)
    EditText etForgetTwo;
    @BindView(R.id.tv_forget_two_over)
    TextView tvForgetTwoOver;
    @BindView(R.id.iv_findpsw_see)
    ImageView ivFindpswSee;
    private Context context = this;
    private boolean isPsw =true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_jypsw_step_two);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.iv_forget_two_back, R.id.tv_forget_two_over,R.id.iv_findpsw_see})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_forget_two_back:
                finish();
                break;
            case R.id.tv_forget_two_over:
                initOver();
                break;
            case R.id.iv_findpsw_see:
                if (isPsw) {
                    etForgetTwo.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    ivFindpswSee.setImageResource(R.mipmap.see_psw);
                    isPsw = !isPsw;
                } else {
                    etForgetTwo.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    ivFindpswSee.setImageResource(R.mipmap.unsea_psw);
                    isPsw = !isPsw;
                }
                break;
        }
    }

    private void initOver() {
        showLoadingDialog();
        BizDataAsyncTask<String> findPayPsw = new BizDataAsyncTask<String>() {
            @Override
            protected String doExecute() throws ZYException, BizFailure {
                return PassWordBiz.findPayPassword(etForgetTwo.getText().toString());
            }

            @Override
            protected void onExecuteSucceeded(String s) {
                hideLoadingDialog();
                ToastUtils.showTost(context, "修改成功");
                finish();
            }

            @Override
            protected void OnExecuteFailed(String error) {
                hideLoadingDialog();
                ToastUtils.showTost(context, error.toString());
            }
        };
        findPayPsw.execute();
    }
}
