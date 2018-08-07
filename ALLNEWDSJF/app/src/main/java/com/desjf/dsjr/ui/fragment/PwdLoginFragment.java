package com.desjf.dsjr.ui.fragment;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.desjf.dsjr.R;
import com.desjf.dsjr.base.BaseConfig;
import com.desjf.dsjr.base.BaseFragment;
import com.desjf.dsjr.bean.LoginBean;
import com.desjf.dsjr.net.RxRetrofitManager;
import com.desjf.dsjr.net.common.BasicObserver;
import com.desjf.dsjr.ui.activity.MainActivity;
import com.desjf.dsjr.ui.activity.RegisterActivity;
import com.desjf.dsjr.ui.activity.ResetLoginPwdActivity;
import com.desjf.dsjr.utils.ButtonUtils;
import com.desjf.dsjr.utils.PreferenceCache;
import com.desjf.dsjr.utils.ToastUtils;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * @author YinL
 * @Describe  密码登录
 */

public class PwdLoginFragment extends BaseFragment {

    private Activity mActivity;

    @BindView(R.id.et_phone)
    EditText etPhone;
    @BindView(R.id.et_code_pwd)
    EditText etPwd;
    @BindView(R.id.iv_pwd)
    ImageView ivPwd;

    //密码是否可见
    private boolean isPsw = true;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_pwd_login;
    }

    @Override
    protected void init(Bundle savedInstanceState) {

        initView();

    }

    //登录
    private void Login(){

        if(("").equals(etPhone.getText().toString())){
            ToastUtils.showShortTost(mActivity,"请输入手机号码");
            return;
        }
        if(("").equals(etPwd.getText().toString())){
            ToastUtils.showShortTost(mActivity,"请输入登录密码");
            return;
        }

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
                        Intent toMian=new Intent(mActivity,MainActivity.class);
                        BaseConfig.NoLogin=0;
                        BaseConfig.WHERE = 1;
                        startActivity(toMian);
                    }

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                        //如果账号或密码输入错误，则弹出图形验证码
                        if(e.getMessage().toString().contains("帐户或密码输入错误")){

                        }
                    }
                });
    }



    @OnClick({R.id.tv_login_btn,R.id.tv_fast_reg,R.id.tv_forget_pwd,R.id.iv_pwd})
    public void OnClick(View view){
        switch(view.getId()){
            case R.id.tv_login_btn:
                 //登录
                if(ButtonUtils.isFastClick()){
                    Login();
                }
                break;
            case R.id.tv_fast_reg:
                //快速注册
                Intent reg=new Intent(mActivity, RegisterActivity.class);
                startActivity(reg);
                break;
            case R.id.tv_forget_pwd:
                //忘记密码
                Intent forget=new Intent(mActivity, ResetLoginPwdActivity.class);
                startActivity(forget);
            case R.id.iv_pwd:
                //密码是否可见
                if(isPsw){
                    etPwd.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    ivPwd.setImageResource(R.mipmap.invisible);
                    isPsw = ! isPsw;
                }else{
                    etPwd.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    ivPwd.setImageResource(R.mipmap.visible);
                    isPsw = ! isPsw;
                }
                break;
        }

    }


    @SuppressLint("ClickableViewAccessibility")
    private void initView(){
        /**
         * EditText drawableRight图片点击事件监听
         */
        etPhone.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                // et.getCompoundDrawables()得到一个长度为4的数组，分别表示左右上下四张图片
                Drawable drawable = etPhone.getCompoundDrawables()[2];
                //如果右边没有图片，不再处理
                if (drawable == null) {
                    return false;
                }
                //如果不是按下事件，不再处理
                if (event.getAction() != MotionEvent.ACTION_UP) {
                    return false;
                }
                if (event.getX() > etPhone.getWidth()
                        - etPhone.getPaddingRight()
                        - drawable.getIntrinsicWidth()){
                    //设置点击EditText右侧图标EditText失去焦点，
                    // 防止点击EditText右侧图标EditText获得焦点，软键盘弹出
                    //                   etPhone.setFocusableInTouchMode(false);
                    etPhone.setText("");
                }
                return false;
            }
        });
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.mActivity = (Activity)context;
    }

}
