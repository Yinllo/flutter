package com.desjf.dsjr.ui.fragment;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.desjf.dsjr.R;
import com.desjf.dsjr.base.BaseConfig;
import com.desjf.dsjr.base.BaseFragment;
import com.desjf.dsjr.bean.LoginBean;
import com.desjf.dsjr.net.RxRetrofitManager;
import com.desjf.dsjr.net.common.BasicObserver;
import com.desjf.dsjr.ui.activity.MainActivity;
import com.desjf.dsjr.ui.activity.RegisterActivity;
import com.desjf.dsjr.utils.ButtonUtils;
import com.desjf.dsjr.utils.PreferenceCache;
import com.desjf.dsjr.utils.StringUtil;
import com.desjf.dsjr.utils.ToastUtils;
import com.desjf.dsjr.widget.TimerCountManager;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * @author YinL
 * @Describe  验证码登录
 */

public class CodeLoginFragment extends BaseFragment {

    private Activity mActivity;

    @BindView(R.id.et_pwd_phone)
    EditText etPhone;
    @BindView(R.id.et_pwd_pwd)
    EditText etPwd;
    @BindView(R.id.btn_send)
    Button sendCode;

    //短信60s倒计时
    private int count = 60;
    TimerCountManager t;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_code_login;
    }

    @Override
    protected void init(Bundle savedInstanceState) {

       initView();

    }

    //验证码登录
    private void codeLogin(){

        if(StringUtil.isEmpty(etPhone.getText().toString())){
            ToastUtils.showShortTost(mActivity,"请输入手机号码");
            return;
        }
        if(StringUtil.isEmpty(etPwd.getText().toString())){
            ToastUtils.showShortTost(mActivity,"请输入验证码");
            return;
        }

        //获取参数
        final Map<String,Object> map=new HashMap<>();
        map.put("mobile",etPhone.getText().toString().trim());
        map.put("verifyCode",etPwd.getText().toString().trim());
        //登录
        RxRetrofitManager.getHttpRequestService()
                .login(map)
                .subscribeOn(Schedulers.io())
                .compose(this.<LoginBean>bindToLifecycle())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BasicObserver<LoginBean>() {
                    @Override
                    public void onSuccess(LoginBean response) {
                        //保存token到本地
                        PreferenceCache.putToken(response.getToken());
                        //跳转到已登录的个人中心
                        Intent toMian=new Intent(getActivity(),MainActivity.class);
                        BaseConfig.NoLogin=0;
                        BaseConfig.WHERE = 1;
                        startActivity(toMian);

                    }
                });
    }

    //获取验证码
    private void getCode() {
        RxRetrofitManager.getHttpRequestService()
                .getloginCode(etPhone.getText().toString())
                .subscribeOn(Schedulers.io())
                .compose(this.bindToLifecycle())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BasicObserver<Object>() {
                    @Override
                    public void onSuccess(Object response) {
                        ToastUtils.showShortTost(mActivity,"验证码已发送");
                        //倒计时
                        t=new TimerCountManager(mActivity,sendCode,60000,1000);
                        t.start();
                    }
                });

    }


    @OnClick({R.id.tv_login_btn,R.id.btn_send,R.id.tv_fast_reg})
    public void OnClick(View view){
        switch(view.getId()){
            case R.id.tv_login_btn:
                //登录
                if(ButtonUtils.isFastClick()){
                    codeLogin();
                }
                break;
            case R.id.btn_send:
                //获取验证码
                if(StringUtil.isEmpty(etPhone.getText().toString())){
                    ToastUtils.showShortTost(mActivity,"请输入手机号码");
                    return;
                }else{
                    if(ButtonUtils.isFastClick()) {
                        getCode();
                    }
                }
                break;
            case R.id.tv_fast_reg:
                //快速注册
                Intent reg=new Intent(mActivity, RegisterActivity.class);
                startActivity(reg);
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
        this.mActivity= (Activity) context;
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
