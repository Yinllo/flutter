package com.desjf.dsjr.activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.desjf.dsjr.R;
import com.desjf.dsjr.base.BaseActivity;
import com.desjf.dsjr.base.BaseApplication;
import com.desjf.dsjr.biz.LoginBiz;
import com.desjf.dsjr.biz.exception.BizFailure;
import com.desjf.dsjr.biz.exception.ZYException;
import com.desjf.dsjr.biz.task.BizDataAsyncTask;
import com.desjf.dsjr.config.DsjrConfig;
import com.desjf.dsjr.model.CaptchaImageModel;
import com.desjf.dsjr.utils.PreferenceCache;
import com.desjf.dsjr.utils.StringUtil;
import com.desjf.dsjr.utils.ToastUtils;
import com.desjf.dsjr.widget.PromptOkCancel;

import org.kobjects.base64.Base64;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends BaseActivity {
    @BindView(R.id.cb_account)
    CheckBox cbAccount;
    @BindView(R.id.et_login_yzm)
    EditText etLoginYzm;
    @BindView(R.id.iv_login_txm)
    ImageView ivLoginTxm;
    @BindView(R.id.rl_getyzm)
    RelativeLayout rlGetyzm;
    private Context context = this;
    @BindView(R.id.tv_forget_psw)
    TextView tvForgetPsw;
    @BindView(R.id.tv_login)
    TextView tvLogin;
    @BindView(R.id.tv_register)
    TextView tvRegister;
    @BindView(R.id.iv_login_back)
    ImageView ivLoginBack;
    @BindView(R.id.et_login_account) //手机号码
    EditText etLoginAccount;
    @BindView(R.id.et_login_psw)//用户密码
    EditText etLoginPsw;
    @BindView(R.id.iv_login_psw)
    ImageView ivLoginPsw;
    private boolean isPsw = true;
    private boolean isAccount = true;
    private boolean tokenOverDue;
    private Bitmap bit;
    private String jym="1";
    private int i;
    private int loginNumber=-1;//记录用户错误登录次数

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        loginNumber=3;
        Intent intent = getIntent();
        i = intent.getIntExtra("login",-1);
        initData();
    }

    private void initData() {
        //是否记住用户名
        cbAccount.setChecked(PreferenceCache.isAutoLogin());
        etLoginAccount.setText(PreferenceCache.getUsername());
    }

    @OnClick({R.id.tv_forget_psw, R.id.tv_login, R.id.tv_register, R.id.iv_login_back, R.id.iv_login_psw, R.id.cb_account,R.id.iv_login_txm})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_forget_psw:
                //忘记密码
                Intent intent2 = new Intent(LoginActivity.this, ForgetLoginPswOneActivity.class);
                startActivity(intent2);
                finish();
                break;
            case R.id.tv_login:
                //登录
                Login();
                break;
            case R.id.tv_register:
                //立即注册
                Intent intent = new Intent(LoginActivity.this, RegistActivity.class);
                startActivity(intent);
                finish();
                break;
            case R.id.iv_login_back:
                //返回
                if (i==1) {
                    DsjrConfig.WHERE=2;
                    Intent two = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(two);
//                    Util.gotoMain(LoginActivity.this);
                    finish();
                    i=-1;
                } else if (i==2){
                    PreferenceCache.putToken("");
//                    Util.showLogin(AccountSettingActivity.this);
                    PreferenceCache.putGestureFlag(false);
                    Intent over = new Intent(context, MainActivity.class);
//                    DsjrConfig.TYPE = 1;
                    DsjrConfig.WHERE = 10;
//                    PreferencesUtil.writeInt(AccountSettingActivity.this, "where", 2);
                    startActivity(over);
                    finish();
                }else  {
                Intent two = new Intent(LoginActivity.this, InvestmentDetailsActivity.class);
                finish();
            }
                break;
            case R.id.iv_login_psw:
                //隐藏密码
                if (isPsw) {
                    etLoginPsw.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    ivLoginPsw.setImageResource(R.mipmap.see_psw);
                    isPsw = !isPsw;
                } else {
                    etLoginPsw.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    ivLoginPsw.setImageResource(R.mipmap.unsea_psw);
                    isPsw = !isPsw;
                }
                break;
            case R.id.cb_account:
                break;
            case R.id.iv_login_txm:
                initgetCaptchaImage();
                break;
        }
    }


    private void Login() {
        if (etLoginAccount.getText().toString().isEmpty()) {
            ToastUtils.showTost(context, "请输入手机号");
            return;
        }
        if (etLoginPsw.getText().toString().isEmpty()) {
            ToastUtils.showTost(context, "请输入密码");
            return;
        }
        showLoadingDialog();
        @SuppressLint("StaticFieldLeak") BizDataAsyncTask<String> getLogin = new BizDataAsyncTask<String>() {
            @Override
            protected String doExecute() throws ZYException, BizFailure {
                return LoginBiz.getLoign(etLoginAccount.getText().toString(), etLoginPsw.getText().toString(),"2");
            }
            @Override
            protected void onExecuteSucceeded(String s) {
                hideLoadingDialog();
                if (s.toString().equals("0")) {
                    loginNumber--;
                    if(loginNumber>0){
                        ToastUtils.showTost(LoginActivity.this,"请注意：如果3次登录失败，账户将被锁定！您还有"+loginNumber+"次机会");
                    }else{
                        ToastUtils.showTost(LoginActivity.this,"您的账户已经被锁定，请联系客服");
                    }
                    rlGetyzm.setVisibility(View.VISIBLE);
                    initgetCaptchaImage();
                } else {
                    if (jym.equals("1")){
                        PreferenceCache.putToken(s); // 持久化缓存token
                        PreferenceCache.putPhoneNum(etLoginAccount.getEditableText().toString());//保存用户名
                        PreferenceCache.putAutoLogin(cbAccount.isChecked());
                        if (cbAccount.isChecked()) {
                            PreferenceCache.putUsername(etLoginAccount.getEditableText().toString());
                        } else {
                            PreferenceCache.putUsername("");
                        }
                        //判断有手势密码, 是否清空手势密码
                        if (PreferenceCache.getGestureFlag() && !StringUtil.isEmpty(PreferenceCache.getPhoneNum()) &&
                                PreferenceCache.getPhoneNum().equals(etLoginAccount.getEditableText().toString())) {
                            PreferenceCache.putGestureFlag(false);//确认后清空手势密码
                            loginSuccess();
//                            PromptOkCancel dialog = new PromptOkCancel(LoginActivity.this) {
//                                @Override
//                                protected void onOk() {
//                                    PreferenceCache.putGestureFlag(false);//确认后清空手势密码
//                                    loginSuccess();
//                                }
//
//                                @Override
//                                protected void onCancel() {
//                                    super.onCancel();//取消后的操作
//                                    //TODO 取消后下次进入不显示手势密码
////									loginSuccess();
//                                    finish();
//
//                                }
//                            };
//                            dialog.show("登录成功", "是否清空当前设备手势密码?");
                        } else {
//                            Log.e("TAG5654654", "onSuccess: " + "未进入手势判断");
                            loginSuccess();
                        }
                    }else if (jym.equals(etLoginYzm.getText().toString())){
                        PreferenceCache.putToken(s); // 持久化缓存token
                        PreferenceCache.putPhoneNum(etLoginAccount.getEditableText().toString());//保存用户名
                        PreferenceCache.putAutoLogin(cbAccount.isChecked());
                        if (cbAccount.isChecked()) {
                            PreferenceCache.putUsername(etLoginAccount.getEditableText().toString());
                        } else {
                            PreferenceCache.putUsername("");
                        }
                        //判断有手势密码, 是否清空手势密码
                        if (PreferenceCache.getGestureFlag() && !StringUtil.isEmpty(PreferenceCache.getPhoneNum()) &&
                                PreferenceCache.getPhoneNum().equals(etLoginAccount.getEditableText().toString())) {
                            PromptOkCancel dialog = new PromptOkCancel(LoginActivity.this) {
                                @Override
                                protected void onOk() {
                                    PreferenceCache.putGestureFlag(false);//确认后清空手势密码
                                    loginSuccess();
                                }

                                @Override
                                protected void onCancel() {
                                    super.onCancel();//取消后的操作
                                    //TODO 取消后下次进入不显示手势密码
//									loginSuccess();
                                    finish();

                                }
                            };
                            dialog.show("登录成功", "是否清空当前设备手势密码?");
                        } else {

                            loginSuccess();
                        }
                    }

                }

            }

            @Override
            protected void OnExecuteFailed(String error) {
                hideLoadingDialog();
                ToastUtils.showTost(context, error.toString());
            }
        };
        getLogin.execute();
    }

    private void initgetCaptchaImage() {
        showLoadingDialog();
        BizDataAsyncTask<CaptchaImageModel> getImage = new BizDataAsyncTask<CaptchaImageModel>() {
            @Override
            protected CaptchaImageModel doExecute() throws ZYException, BizFailure {
                return LoginBiz.getImage();
            }

            @Override
            protected void onExecuteSucceeded(CaptchaImageModel captchaImageModel) {
                hideLoadingDialog();
                byte[] srtbyte = Base64.decode(captchaImageModel.getBYTE());
                bit = getBitmapFromByte(srtbyte);
                ivLoginTxm.setImageBitmap(bit);
                ivLoginTxm.setScaleType(ImageView.ScaleType.FIT_XY);
                jym = captchaImageModel.getCODE();
            }

            @Override
            protected void OnExecuteFailed(String error) {
                hideLoadingDialog();
                ToastUtils.showTost(LoginActivity.this, error.toString());
            }
        };
        getImage.execute();
    }

    private void loginSuccess() {
        PreferenceCache.putPhoneNum(etLoginAccount.getEditableText().toString());

        if (BaseApplication.forgetGesturePwd == 1) {
            PreferenceCache.putGestureFlag(false);
            BaseApplication.forgetGesturePwd = 0;
        }

        if (PreferenceCache.getLoginFlag()) {
            //判断是不是债权详情或投资详情跳入的
            BaseApplication.globalIndex = 1;
            PreferenceCache.putLoginFlag(false);
        } else {
            BaseApplication.globalIndex = 2;
        }
//        Util.gotoMain(LoginActivity.this);
        //发送广播  刷新个人中心数据
        Intent i = new Intent();
        i.setAction("withdraw");
        BaseApplication.getAppContext().sendBroadcast(i);
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        DsjrConfig.WHERE = 2;
        DsjrConfig.TO_MY=2;
        DsjrConfig.GOFRESH = 10;
        startActivity(intent);
        finish();
    }
    public Bitmap getBitmapFromByte(byte[] temp) {
        if (temp != null) {
            Bitmap bitmap = BitmapFactory.decodeByteArray(temp, 0, temp.length);
            return bitmap;
        } else {
            return null;
        }
    }
    @Override
    public void onBackPressed() {
        PreferenceCache.putToken("");
//                    Util.showLogin(AccountSettingActivity.this);
        PreferenceCache.putGestureFlag(false);
        Intent over = new Intent(context, MainActivity.class);
//                    DsjrConfig.TYPE = 1;
        DsjrConfig.WHERE = 10;
//                    PreferencesUtil.writeInt(AccountSettingActivity.this, "where", 2);
        startActivity(over);
        finish();
    }
}
