package com.desjf.dsjr.activity;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.desjf.dsjr.R;
import com.desjf.dsjr.base.BaseActivity;
import com.desjf.dsjr.base.BaseApplication;
import com.desjf.dsjr.biz.LoginBiz;
import com.desjf.dsjr.biz.PassWordBiz;
import com.desjf.dsjr.biz.exception.BizFailure;
import com.desjf.dsjr.biz.exception.ZYException;
import com.desjf.dsjr.biz.task.BizDataAsyncTask;
import com.desjf.dsjr.model.AccountModel;
import com.desjf.dsjr.util.MyDialogUtil;
import com.desjf.dsjr.utils.PreferenceCache;
import com.desjf.dsjr.utils.ToastUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ResetLoginPswActivity extends BaseActivity {
    private Context context = this;
    @BindView(R.id.iv_reset_loginpsw_back)
    ImageView ivResetLoginpswBack;
    @BindView(R.id.et_reset_login_psw)
    EditText etResetLoginPsw;
    @BindView(R.id.iv_reset_login_psw)
    ImageView ivResetLoginPsw;
    @BindView(R.id.et_new_login_psw)
    EditText etNewLoginPsw;
    @BindView(R.id.iv_new_login_psw)
    ImageView ivNewLoginPsw;
    @BindView(R.id.tv_reset_psw_login)
    TextView tvResetPswLogin;
    private boolean isPsw=false;
    private boolean isReSet=true;
    private boolean flag=false;
    private String pwd;
    private String jym="1";
    private AccountModel accountModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_login_psw);
        ButterKnife.bind(this);


        BaseApplication baseApplication = (BaseApplication) getApplication();
        accountModel= baseApplication.getAccountModel();

    }

    @OnClick({R.id.iv_reset_loginpsw_back, R.id.iv_reset_login_psw, R.id.iv_new_login_psw, R.id.tv_reset_psw_login})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_reset_loginpsw_back:
                finish();
                break;
            case R.id.iv_reset_login_psw:
                //隐藏原密码 默认为原密码可见
                if (isPsw) {
                    etResetLoginPsw.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    ivResetLoginPsw.setImageResource(R.mipmap.see_psw);
                    isPsw = !isPsw;


                } else {
                    etResetLoginPsw.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    ivResetLoginPsw.setImageResource(R.mipmap.unsea_psw);
                    isPsw = !isPsw;
                }
                break;
            case R.id.iv_new_login_psw:
//                隐藏新密码
                if (isReSet) {
                    etNewLoginPsw.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    ivNewLoginPsw.setImageResource(R.mipmap.see_psw);
                    isReSet = !isReSet;
                } else {
                    etNewLoginPsw.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    ivNewLoginPsw.setImageResource(R.mipmap.unsea_psw);
                    isReSet = !isReSet;
                }
                break;
            case R.id.tv_reset_psw_login:
                initNewPsw();
                break;
        }
    }

    private void initNewPsw() {
        if (etResetLoginPsw.getText().toString().isEmpty()){
            MyDialogUtil.showSimpleDialog(context,"原密码不能为空!","知道了");
            return;
        }
        if (etNewLoginPsw.getText().toString().isEmpty()){
            MyDialogUtil.showSimpleDialog(context,"新密码不能为空!","知道了");
            return;
        }

        showLoadingDialog();
        @SuppressLint("StaticFieldLeak") BizDataAsyncTask<String> retSetPsw = new BizDataAsyncTask<String>() {
            @Override
            protected String doExecute() throws ZYException, BizFailure {
                return PassWordBiz.reSetLoginPsw(etResetLoginPsw.getText().toString(),etNewLoginPsw.getText().toString());
            }

            @Override
            protected void onExecuteSucceeded(String s) {
                hideLoadingDialog();
//              自动重新登录
                Login();
                showDialog("登录密码修改成功！",3);
                etResetLoginPsw.setText("");
                etNewLoginPsw.setText("");

            }

            @Override
            protected void OnExecuteFailed(String error) {
                hideLoadingDialog();
                MyDialogUtil.showSimpleDialog(context,error.toString(),"知道了");
            }
        };
        retSetPsw.execute();
    }


    private void Login() {
        showLoadingDialog();
        @SuppressLint("StaticFieldLeak") BizDataAsyncTask<String> getLogin = new BizDataAsyncTask<String>() {
            @Override
            protected String doExecute() throws ZYException, BizFailure {
                return LoginBiz.getLoign(accountModel.getPHONENUMBER(), etNewLoginPsw.getText().toString(),"2");
            }

            @Override
            protected void onExecuteSucceeded(String s) {
                hideLoadingDialog();
                if (s.toString().equals("0")) {
                    ToastUtils.showTost(ResetLoginPswActivity.this,"用户名或密码错误");

                } else {
                    if (jym.equals("1")){
                        PreferenceCache.putToken(s); // 持久化缓存token
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

    private void showDialog(String msg, final int flg) {
        final Dialog dialog = new Dialog(this, R.style.My_Dialog);
        dialog.setContentView(R.layout.dialog_if_login);
        dialog.setCancelable(false);
        TextView tv_msg = (TextView) dialog.findViewById(R.id.tv_msg);
        tv_msg.setText(msg);
        dialog.findViewById(R.id.tv_ok).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        dialog.findViewById(R.id.tv_cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (flg == 1) {
                    //未实名
                    Intent intent = new Intent(context, AccountRealNameActivity.class);
                    startActivity(intent);
                } else if (flg == 2) {
                    //未绑卡
                    Intent inBankCard = new Intent(context, AccountAddbanksActivity.class);
                    startActivity(inBankCard);
                } else if (flg == 3) {
                    //退出账户
                    PreferenceCache.putToken("");
                    PreferenceCache.putGestureFlag(false);
                    Intent over = new Intent(context, LoginActivity.class);
                    startActivity(over);
                    finish();
                }
                dialog.dismiss();
            }
        });
        dialog.show();
        DisplayMetrics dm = context.getResources().getDisplayMetrics();
        int displayWidth = dm.widthPixels;
        int displayHeight = dm.heightPixels;
        android.view.WindowManager.LayoutParams p = dialog.getWindow().getAttributes(); //获取对话框当前的参数值
        p.width = (int) (displayWidth * 0.8); //宽度设置为屏幕的0.5
        p.height = (int) (displayHeight * 0.3); //宽度设置为屏幕的0.25
        dialog.setCanceledOnTouchOutside(false);// 设置点击屏幕Dialog不消失
        dialog.getWindow().setAttributes(p);  //设置生效
    }



}
