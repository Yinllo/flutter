package com.desjf.dsjr.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.desjf.dsjr.R;
import com.desjf.dsjr.base.BaseActivity;
import com.desjf.dsjr.biz.LoginBiz;
import com.desjf.dsjr.biz.RegistBiz;
import com.desjf.dsjr.biz.exception.BizFailure;
import com.desjf.dsjr.biz.exception.ZYException;
import com.desjf.dsjr.biz.task.BizDataAsyncTask;
import com.desjf.dsjr.utils.PreferenceCache;
import com.desjf.dsjr.utils.ToastUtils;
import com.umeng.analytics.MobclickAgent;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.desjf.dsjr.R.id.tv_mobile_commite;

public class RegesterThirdActivity extends BaseActivity {
    private Context context = this;
    @BindView(tv_mobile_commite)
    TextView tvMobileCommite;
    @BindView(R.id.iv_register_third_back)
    ImageView ivRegisterThirdBack;
    @BindView(R.id.et_psw)
    EditText etPsw;
    @BindView(R.id.iv_psw_see)
    ImageView ivPswSee;
    @BindView(R.id.et_invite_phone)
    EditText etInvitePhone;
    @BindView(R.id.tv_agreement)
    TextView tvAgreement;
    private boolean isSea=true;
    private String phone;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regester_third);
        ButterKnife.bind(this);
        initData();
    }

    private void initData() {
        Intent intent = getIntent();
        phone = intent.getStringExtra("phone");
    }


    @OnClick({R.id.iv_register_third_back, R.id.iv_psw_see, R.id.tv_agreement, tv_mobile_commite})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_register_third_back:
                finish();
                break;
            case R.id.iv_psw_see:
                if (isSea){
                    etPsw.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    ivPswSee.setImageResource(R.mipmap.see_psw);
                    isSea=!isSea;
                }else{
                    etPsw.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    ivPswSee.setImageResource(R.mipmap.unsea_psw);
                    isSea=!isSea;
                }
                break;
            case R.id.tv_agreement:
                //跳转到服务协议
                Intent intent = new Intent(RegesterThirdActivity.this,WebViewActivity.class);
                intent.putExtra("web",5);
                startActivity(intent);
                break;
            case tv_mobile_commite:
                showLoadingDialog();
                BizDataAsyncTask<String> regist = new BizDataAsyncTask<String>() {
                    @Override
                    protected String doExecute() throws ZYException, BizFailure {

                        return RegistBiz.regist(phone,etPsw.getText().toString(),etInvitePhone.getText().toString(),"2");
                    }

                    @Override
                    protected void onExecuteSucceeded(String s) {
                        MobclickAgent.onEvent(RegesterThirdActivity.this,"注册");

                        hideLoadingDialog();
                        //完成
                        //直接登录
                        initLogin();
//                        if (PreferenceCache.getGestureFlag() && !StringUtil.isEmpty(PreferenceCache.getPhoneNum())) {
//                            PromptOkCancel dialog = new PromptOkCancel(RegesterThirdActivity.this) {
//                                @Override
//                                protected void onOk() {
//                                    PreferenceCache.putGestureFlag(false);//确认清空手势密码
//                                    PreferenceCache.putUsername(PreferenceCache.getPhoneNum().toString());
//                                    PreferenceCache.putPhoneNum(PreferenceCache.getPhoneNum().toString());
//                                    //注册成功
//                                    Intent itSetGesture = new Intent(RegesterThirdActivity.this, GestureSetActivity.class);
//                                    itSetGesture.putExtra("jumpFlg", 1);
//                                    startActivity(itSetGesture);
//                                    finish();
//
//                                }
//
//                                @Override
//                                protected void onCancel() {
//                                    super.onCancel();
//                                    PreferenceCache.putUsername(PreferenceCache.getPhoneNum().toString());
//                                    PreferenceCache.putPhoneNum(PreferenceCache.getPhoneNum().toString());
//                                    //注册成功
//                                    Intent itSetGesture = new Intent(RegesterThirdActivity.this, GestureSetActivity.class);
//                                    itSetGesture.putExtra("jumpFlg", 1);
//                                    startActivity(itSetGesture);
//                                    finish();
//
//                                }
//                            };
//                            dialog.show("注册成功", "是否清空当前设备手势密码?");
//                        } else {
//                            PreferenceCache.putUsername(PreferenceCache.getPhoneNum().toString());
//                            PreferenceCache.putPhoneNum(PreferenceCache.getPhoneNum().toString());
//                            //注册成功
//                            Intent itSetGesture = new Intent(RegesterThirdActivity.this, GestureSetActivity.class);
//                            itSetGesture.putExtra("jumpFlg", 1);
//                            startActivity(itSetGesture);
//                            finish();
//                        }
                        Intent intent = new Intent(RegesterThirdActivity.this, GestureSetActivity.class);
                        intent.putExtra("jumpFlg",1);
                        startActivity(intent);
                        finish();
                    }

                    @Override
                    protected void OnExecuteFailed(String error) {
                        hideLoadingDialog();
                        ToastUtils.showTost(context,error.toString());
                    }
                };
                regist.execute();

                break;
        }
    }

    private void initLogin() {
        showLoadingDialog();
        BizDataAsyncTask<String> getLogin = new BizDataAsyncTask<String>() {
            @Override
            protected String doExecute() throws ZYException, BizFailure {
                return LoginBiz.getLoign(phone, etPsw.getText().toString(),"2");
            }

            @Override
            protected void onExecuteSucceeded(String s) {
                hideLoadingDialog();
                PreferenceCache.putToken(s); // 持久化缓存token
                PreferenceCache.putPhoneNum(phone);//保存用户名

            }

            @Override
            protected void OnExecuteFailed(String error) {
                hideLoadingDialog();
                ToastUtils.showTost(context, error.toString());
            }
        };
        getLogin.execute();
    }
}
