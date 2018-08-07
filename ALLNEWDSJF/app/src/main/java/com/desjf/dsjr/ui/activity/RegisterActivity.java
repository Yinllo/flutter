package com.desjf.dsjr.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.desjf.dsjr.R;
import com.desjf.dsjr.base.BaseActivity;
import com.desjf.dsjr.base.BaseConfig;
import com.desjf.dsjr.bean.LoginBean;
import com.desjf.dsjr.net.RxRetrofitManager;
import com.desjf.dsjr.net.common.BasicObserver;
import com.desjf.dsjr.utils.ButtonUtils;
import com.desjf.dsjr.utils.FormatUtils;
import com.desjf.dsjr.utils.PreferenceCache;
import com.desjf.dsjr.utils.ToastUtils;
import com.desjf.dsjr.widget.EditTextDrawableClick;
import com.desjf.dsjr.widget.TimerCountManager;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * @author YinL
 * @Describe  注册
 */
public class RegisterActivity extends BaseActivity {

    @BindView(R.id.rv_title)
    RelativeLayout tvTitle;//标题
    @BindView(R.id.et_phone)
    EditTextDrawableClick etPhone;//注册手机号
    @BindView(R.id.et_pwd)
    EditText etPwd;//密码
    @BindView(R.id.iv_pwd)
    ImageView ivPwd;//密码是否可见
    @BindView(R.id.et_inviter)
    EditText etInviter;//推荐人
    @BindView(R.id.et_code)
    EditText etCode;//验证码
    @BindView(R.id.btn_send)
    Button btnSend;//获取验证码
    @BindView(R.id.cb_choose)
    CheckBox cbChoose;

    boolean register_Protocol=false;  //判断是否点击同意协议  一开始为 false
    private boolean isSea=true;//密码是否可见
    TimerCountManager t;//验证码倒计时


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);

        initData();

    }


    private void initData(){
        //输入手机号码 右侧清空图片点击事件监听
        etPhone.setDrawableRightListener(new EditTextDrawableClick.DrawableRightListener() {
            @Override
            public void onDrawableRightClick(View view) {
               etPhone.setText("");
            }
        });

    }

    //注册
    private void Reg(){
        if(("").equals(etPhone.getText().toString().trim())){
            ToastUtils.showShortTost(this,"请输入手机号码");
            return;
        }
        if(("").equals(etPhone.getText().toString().trim())||!FormatUtils.phoneCheck(etPhone.getText().toString().trim())){
            ToastUtils.showShortTost(this,"注册手机号码输入有误，请重填");
            return;
        }
        if(("").equals(etPwd.getText().toString().trim())){
            ToastUtils.showShortTost(this,"请输入密码");
            return;
        }
        if(("").equals(etPwd.getText().toString().trim())||!FormatUtils.checkPwd(etPwd.getText().toString().trim())){
            ToastUtils.showShortTost(this,"为保证更安全，请输入8-20位数字+字母组合密码");
            return;
        }
        if(!("").equals(etInviter.getText().toString().trim())&&!FormatUtils.phoneCheck(etInviter.getText().toString().trim())){
            ToastUtils.showShortTost(this,"邀请人手机号码输入有误，请重填");
            return;
        }
        if(("").equals(etCode.getText().toString().trim())){
            ToastUtils.showShortTost(this,"请输入验证码");
            return;
        }
        if(!register_Protocol){
            ToastUtils.showShortTost(this,"请您阅读并同意 注册协议");
            return;
        }

        //注册参数
        final Map<String,Object> map=new HashMap<>();
        map.put("mobile",etPhone.getText().toString().trim());
        map.put("password",etPwd.getText().toString().trim());
        map.put("verifyCode",etCode.getText().toString().trim());
        map.put("introducerMobile",etInviter.getText().toString().trim());
        map.put("placeid","");//渠道id
        map.put("tagEndMode","2");//终端
        //注册
        RxRetrofitManager.getHttpRequestService()
                .regist(map)
                .subscribeOn(Schedulers.io())
                .compose(this.bindToLifecycle())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BasicObserver<Object>() {
                    @Override
                    public void onSuccess(Object response) {
                        //注册成功，直接登录
                        Log.e("qqq", "注册成功");
                        Login();
                    }

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                    }
                });

    }
    //注册成功后立即登录
    private void Login(){
        //参数
        final Map<String,Object> map=new HashMap<>();
        map.put("mobile",etPhone.getText().toString().trim());
        map.put("password",etPwd.getText().toString().trim());

        RxRetrofitManager.getHttpRequestService()
                .login(map)
                .subscribeOn(Schedulers.io())
                .compose(this.<LoginBean>bindToLifecycle())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BasicObserver<LoginBean>() {
                    @Override
                    public void onSuccess(LoginBean response) {
                        //保存token
                        PreferenceCache.putToken(response.getToken());
                        Intent toMian=new Intent(RegisterActivity.this,MainActivity.class);
                        BaseConfig.NoLogin=0;
                        BaseConfig.WHERE = 1;
                        startActivity(toMian);
                    }

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);

                    }
                });
    }

    //获取注册验证码
    private void getRegCode(){
        if(("").equals(etPhone.getText().toString().trim())){
            ToastUtils.showShortTost(this,"请输入手机号码");
            return;
        }
        if(("").equals(etPhone.getText().toString().trim())||!FormatUtils.phoneCheck(etPhone.getText().toString().trim())){
            ToastUtils.showShortTost(this,"注册手机号码输入有误，请重填");
            return;
        }
        if(("").equals(etPwd.getText().toString().trim())){
            ToastUtils.showShortTost(this,"请输入密码");
            return;
        }
        if(("").equals(etPwd.getText().toString().trim())||!FormatUtils.checkPwd(etPwd.getText().toString().trim())){
            ToastUtils.showShortTost(this,"为保证更安全，请输入8-20位数字+字母组合密码");
            return;
        }
        if(!("").equals(etInviter.getText().toString().trim())&&!FormatUtils.phoneCheck(etInviter.getText().toString().trim())){
            ToastUtils.showShortTost(this,"邀请人手机号码输入有误，请重填");
            return;
        }
        //获取验证码
        RxRetrofitManager.getHttpRequestService()
                .getregCode(etPhone.getText().toString())
                .subscribeOn(Schedulers.io())
                .compose(this.bindToLifecycle())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BasicObserver<Object>() {
                    @Override
                    public void onSuccess(Object response) {
                        ToastUtils.showShortTost(RegisterActivity.this,"验证码已发送");
                        //启动倒计时
                        t=new TimerCountManager(RegisterActivity.this,btnSend,60000,1000);
                        t.start();
                    }
                });
    }

    //点击事件监听
    @OnClick({R.id.rv_title,R.id.btn_send,R.id.cb_choose,R.id.tv_reg_btn,R.id.tv_fast_login,R.id.iv_pwd})
    public void OnClick(View view){
        switch (view.getId()){
            case R.id.rv_title:
                //返回首页
                Intent toHome=new Intent(this,MainActivity.class);
                BaseConfig.WHERE=2;
                startActivity(toHome);
                break;
            case R.id.btn_send:
                //获取验证码
                    if(ButtonUtils.isFastClick()){
                       getRegCode();
                    }
                break;
            case R.id.iv_pwd:
                //密码可见切换
                if (isSea){
                    etPwd.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    ivPwd.setImageResource(R.mipmap.invisible);
                    isSea=!isSea;
                }else{
                    etPwd.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    ivPwd.setImageResource(R.mipmap.visible);
                    isSea=!isSea;
                }
                break;
            case R.id.cb_choose:
                //注册协议
                if(cbChoose.isChecked()) register_Protocol=true;
                else register_Protocol=false;
                break;
            case R.id.tv_reg_btn:
                //注册
                if(ButtonUtils.isFastClick()){
                    Reg();
                }
                break;
            case R.id.tv_fast_login:
                //快速登录
                Intent toLogin=new Intent(this,LoginActivity.class);
                startActivity(toLogin);
                break;
        }

    }



    @Override
    public void onDestroy() {
        //及时
        if(t!=null){
            t.cancel();
            t=null;
        }
        super.onDestroy();
    }

}
