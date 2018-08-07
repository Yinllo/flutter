package com.desjf.dsjr.activity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.desjf.dsjr.R;
import com.desjf.dsjr.base.BaseActivity;
import com.desjf.dsjr.biz.LoginBiz;
import com.desjf.dsjr.biz.RegistBiz;
import com.desjf.dsjr.biz.exception.BizFailure;
import com.desjf.dsjr.biz.exception.ZYException;
import com.desjf.dsjr.biz.retrofit.CallUtil;
import com.desjf.dsjr.biz.retrofit.InitHttpUtil;
import com.desjf.dsjr.biz.task.BizDataAsyncTask;
import com.desjf.dsjr.model.bankModel.BankCodeModel;
import com.desjf.dsjr.util.MyDialogUtil;
import com.desjf.dsjr.utils.ButtonUtils;
import com.desjf.dsjr.utils.PreferenceCache;
import com.desjf.dsjr.utils.ToastUtils;

import java.lang.ref.WeakReference;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;

import static com.desjf.dsjr.R.id.et_psw;
import static com.desjf.dsjr.R.id.tv_mobile_commite;

public class RegistActivity extends BaseActivity {
    @BindView(R.id.iv_clear)
    ImageView ivClear;
    //获取手机号码
    private Context context = this;
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.et_phone)
    EditText etPhone;
    @BindView(R.id.et_invite_phone)
    EditText etiPhone;
    //获取验证码
    @BindView(R.id.et_verify_code)
    EditText etVerifyCode;
    private String phone;//手机号码
    private String inviterPhone;//邀请人手机号码
    private String code;//手机验证码
    private String pwd;//用户密码
    @BindView(R.id.getmobile)
    TextView getmobile;
    private Timer timer;
    private TimerTask timerTask;
    private int count = 60;
    private int toRegist = 0;
    String phoneCheck="";//在点击确认注册时用来再一次判断手机号码是否正确：1为正确(可用号码  未注册)   0为错误
    String inviterphoneCheck="";//在点击确认注册时用来再一次判断邀请人手机号码是否存在：1为不存在  0为存在
    String codeCheck="";//在点击确认注册时用来再一次判断手机验证码是否正确：1为正确   0为错误
    boolean xieyi=false;  //判断是否点击同意协议  一开始为 false
    Boolean flag=false;//用户密码类型 不为纯数字或者纯字母  则为false

    AlertDialog alertDialog;

//    private Handler mHandler = new Handler() {
//        @Override
//        public void dispatchMessage(Message msg) {
//            if (count >= 0) {
//                getmobile.setText("重新发送(" + count + ")");
//                getmobile.setClickable(false);
//                count--;
//            } else {
//                resetTimer();
//            }
//        }
//    };
    //密码和邀请人
    @BindView(tv_mobile_commite)
    TextView tvMobileCommite;
    @BindView(et_psw)
    EditText etPsw;
    @BindView(R.id.iv_psw_see)
    ImageView ivPswSee;
    @BindView(R.id.xieyi)
    ImageView imxieyi;
    @BindView(R.id.tv_agreement)
    TextView tvAgreement;
    private boolean isSea=true;
    @SuppressLint("HandlerLeak")
    private final Handler mHandler = new Handler(){
        @Override
        public void dispatchMessage(Message msg) {
            if (count >= 0) {
                getmobile.setText("重新发送(" + count + ")");
                getmobile.setClickable(false);
                count--;
            } else {
                resetTimer();
            }
        }
    };
    private final Handler handler = new MyHandler(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regester);
        ButterKnife.bind(this);
        initData();
    }

    private static class MyHandler extends Handler {
        private final WeakReference<RegistActivity> mActivity;

        public MyHandler(RegistActivity activity) {
            mActivity = new WeakReference<RegistActivity>(activity);
        }

    }
    /**
     * 延迟线程，看是否还有下一个字符输入
     */
    Runnable phonedelayRun = new Runnable() {
        @Override
        public void run() {
            //在这里调用服务器的接口，获取数据
//            onPhoneCheck();
        }
    };
    Runnable iphonedelayRun = new Runnable() {
        @Override
        public void run() {
            //在这里调用服务器的接口，获取数据
            onInviterPhoneCheck();
        }
    };
    Runnable codedelayRun = new Runnable() {
        @Override
        public void run() {
            //在这里调用服务器的接口，获取数据
//          onVoiceCodeCheck();
        }
    };

    //手机号码填写
    private void initData() {
        //手机号码填写edit内容监听  // 判断手机号码正确性
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
//                double  mill = System.currentTimeMillis();
//                if (phonedelayRun != null) {
//                    //每次editText有变化的时候，则移除上次发出的延迟线程
//                    handler.removeCallbacks(phonedelayRun);
//                }
//
//                //延迟800ms，如果不再输入字符，则执行该线程的run方法
//                if (!TextUtils.isEmpty(etPhone.getText())) {
//                    handler.postDelayed(phonedelayRun, 1000);
//                }

            }
        });
        // 判断邀请人手机号码正确性
        etiPhone.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }
            @Override
            public void afterTextChanged(final Editable editable) {

                double  mill = System.currentTimeMillis();
                if (iphonedelayRun != null) {
                    //每次editText有变化的时候，则移除上次发出的延迟线程
                    handler.removeCallbacks(iphonedelayRun);
                }

                //延迟800ms，如果不再输入字符，则执行该线程的run方法
                if (!TextUtils.isEmpty(etiPhone.getText())) {
                    handler.postDelayed(iphonedelayRun, 800);
                }

            }
        });
        etVerifyCode.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }
            @Override
            public void afterTextChanged(final Editable editable) {

                double  mill = System.currentTimeMillis();
                if (codedelayRun != null) {
                    //每次editText有变化的时候，则移除上次发出的延迟线程
                    handler.removeCallbacks(codedelayRun);
                }

                //延迟800ms，如果不再输入字符，则执行该线程的run方法
                if (!TextUtils.isEmpty(etVerifyCode.getText())) {
                    handler.postDelayed(codedelayRun, 800);
                }

            }
        });
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }


//    @OnTextChanged(R.id.et_phone)
    public void onPhoneCheck() {
        phone=etPhone.getText().toString();
        if (phone.isEmpty()) {
            ToastUtils.showTost(this, "请输入手机号");
            return;
        }
//        String telRegex = "[1][345789]\\d{9}";//"[1]"代表第1位为数字1，"[358]"代表第二位可以为3、5、8中的一个，"\\d{9}"代表后面是可以是0～9的数字，有9位。
//        if(!phone.matches(telRegex)){
//            ToastUtils.showShortTost(RegistActivity.this,"手机号码输入有误，请重填");
//            return;
//        }
        CallUtil.call(RegistActivity.this, InitHttpUtil.getHttpRequestService().checkMobile(phone), new CallUtil.MyCallListener() {
            @Override
            public void onRespnseSuccess(String jsonString) {
                BankCodeModel bankCodeModel=JSON.parseObject(jsonString,BankCodeModel.class);
                if(bankCodeModel.isStatus()){
                    phoneCheck="1";
                    getVoiceCode();
//                    ToastUtils.showTost(context, "手机号码验证成功！");
                }else {
                    phoneCheck="0";
                    ToastUtils.showTost(context, "手机号码有误或者已经注册，请重填");
                }

            }

            @Override
            public void onRespnseFailure(Call<String> call, Throwable t) {
                phoneCheck="0";
                ToastUtils.showTost(context, "手机号码验证失败，请重试");
            }
        });

    }


//    @OnTextChanged(R.id.et_invite_phone)
    public void onInviterPhoneCheck() {
        inviterPhone=etiPhone.getText().toString();

        if (inviterPhone.isEmpty()) {
            ToastUtils.showTost(this, "请输入手机号");
            return;
        }
        CallUtil.call(RegistActivity.this, InitHttpUtil.getHttpRequestService().checkMobile(inviterPhone), new CallUtil.MyCallListener() {
            @Override
            public void onRespnseSuccess(String jsonString) {
                BankCodeModel bankCodeModel=JSON.parseObject(jsonString,BankCodeModel.class);
                if(bankCodeModel.isStatus()){
                    inviterphoneCheck="1";
                    ToastUtils.showTost(context, "请检查手机号码是否输入正确");
                }else {
                    inviterphoneCheck="0";

                }

            }

            @Override
            public void onRespnseFailure(Call<String> call, Throwable t) {
                ToastUtils.showTost(context, "手机号码验证失败，请重试");
            }
        });

    }


     //验证码相关
     @OnClick({R.id.getmobile})
     public void onClick(View view) {
         switch (view.getId()) {
             case R.id.getmobile:
                 //获取验证码
                 onPhoneCheck();
                 break;
         }
     }

    //获取验证码
    private void getVoiceCode() {
        phone=etPhone.getText().toString();
        if(phone.isEmpty()){
            ToastUtils.showShortTost(RegistActivity.this,"请输入手机号码");
            return;
        }
        showLoadingDialog();
        CallUtil.call(RegistActivity.this, InitHttpUtil.getHttpRequestService().sendVerifyCode(phone), new CallUtil.MyCallListener() {
            @Override
            public void onRespnseSuccess(String jsonString) {
                hideLoadingDialog();
                runTimerTask();
                getmobile.setClickable(false);
                getmobile.setFocusable(false);
            }

            @Override
            public void onRespnseFailure(Call<String> call, Throwable t) {
                hideLoadingDialog();
                ToastUtils.showTost(context, "验证码发送失败");
            }
        });

    }

    private void runTimerTask() {
        timer = new Timer();
        timerTask = new TimerTask() {
            @Override
            public void run() {
                mHandler.sendEmptyMessage(0);
            }
        };
        timer.schedule(timerTask, 1000, 1000);
    }

    private void resetTimer() {
        getmobile.setText(R.string.find_getverifycode);
        getmobile.setClickable(true);
        count = 60;
        timerTask.cancel();
        timer.cancel();
        timerTask = null;
        timer = null;
    }

//    @OnTextChanged(R.id.et_verify_code)
    public void onVoiceCodeCheck(){
        phone=etPhone.getText().toString();
        code=etVerifyCode.getText().toString();
        if (code.isEmpty()) {
            ToastUtils.showTost(context, "请输入验证码");
            return;
        }
//        showLoadingDialog();

        BizDataAsyncTask<String> getVerifyCode = new BizDataAsyncTask<String>() {
            @Override
            protected String doExecute() throws ZYException, BizFailure {
                return RegistBiz.checkVerifyCode(phone,code);
            }
            @Override
            protected void onExecuteSucceeded(String s) {
                ToastUtils.showTost(context, "验证码校验成功！");
                codeCheck="1";
            }
            @Override
            protected void OnExecuteFailed(String error) {
                ToastUtils.showTost(context,"验证码错误，请重新填写");
                codeCheck="0";
            }
        };
        getVerifyCode.execute();
     }

    /**
     * 密码填写、邀请人、协议、完成注册
     */

    //判断输入的密码是否为纯数字或者纯字母
    private boolean checkPwd(){
        pwd=etPsw.getText().toString();
        String nreg="^\\d+$";//数字
        boolean n=pwd.matches(nreg);

        String reg="^[a-zA-Z]+$";//字母
        boolean s=pwd.matches(reg);
        boolean length=pwd.length()<8;
        boolean lengths=pwd.length()>20;
        if(n||s||length||lengths){
            flag=true;
        }else {
         flag=false;
        }
        return flag;
    }

    @OnClick({R.id.iv_psw_see, R.id.tv_agreement,R.id.xieyi})
    public void onEndClick(View view) {
        switch (view.getId()) {
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
                Intent intent = new Intent(RegistActivity.this,WebViewActivity.class);
                intent.putExtra("web",5);
                startActivity(intent);
                break;
            case R.id.xieyi:
                if (xieyi){
                    xieyi=false;
                    imxieyi.setImageResource(R.mipmap.unremberuser);
                }else{
                    xieyi=true;
                    imxieyi.setImageResource(R.mipmap.remberuser);
                }
                break;
        }
    }

    //注册完成
    @OnClick(R.id.tv_mobile_commite)
    public   void commitClink(View view) {
        switch (view.getId()) {
            case R.id.tv_mobile_commite:
                if (ButtonUtils.isFastClick()) {
                    Reg();
                }
                break;
                }
        }

    private void Reg(){
        if ("".equals(etVerifyCode.getText().toString()) || "".equals(etPhone.getText().toString()) || "".equals(etPsw.getText().toString())) {
            MyDialogUtil.showSimpleDialog(context, "请您正确填写注册信息！", "知道了");
            return;
        }
        if (phoneCheck.equals("0")) {
                MyDialogUtil.showSimpleDialog(context, "手机号码输入错误或已注册！", "知道了");
                return;
            }
        if (inviterphoneCheck.equals("1")) {
                MyDialogUtil.showSimpleDialog(context, "您输入的邀请人信息错误，请重新填写！", "知道了");
                return;
            }
        if (checkPwd()) {
                MyDialogUtil.showSimpleDialog(context, "为保证您的财产安全，请勿使用单一或者长度过短的密码!", "知道了");
                return;
            }
        if (xieyi && checkPwd() == false && phoneCheck.equals("1")) {
                showLoadingDialog();
                CallUtil.call(RegistActivity.this, InitHttpUtil.getHttpRequestService().regist(phone,
                        etPsw.getText().toString(), etVerifyCode.getText().toString(), etiPhone.getText().toString(), "", "2"), new CallUtil.MyCallListener() {
                    @Override
                    public void onRespnseSuccess(String jsonString) {

                        BankCodeModel bankCodeModel = JSON.parseObject(jsonString, BankCodeModel.class);
                        if (bankCodeModel.isStatus()) {
                            //直接登录
                            initLogin();
                            showSimpleDialog(context, "恭喜您注册成功！", "知道了");
                        } else {
                            MyDialogUtil.showSimpleDialog(context, bankCodeModel.getMessage(), "知道了");
                        }
                        //                        ToastUtils.showTost(context, "恭喜您注册成功！");
                        hideLoadingDialog();
                    }

                    @Override
                    public void onRespnseFailure(Call<String> call, Throwable t) {
                        hideLoadingDialog();
                        ToastUtils.showLongTost(RegistActivity.this, "注册失败，请稍后重试");
                    }
                });

        } else {
                MyDialogUtil.showSimpleDialog(context, "注册需要您同意德晟金服协议！", "知道了");
                return;
            }

    }

    public void showSimpleDialog(Context context , String message,String ok) {
        View view = LayoutInflater.from(context).inflate(R.layout.mydialog, null);
        TextView confirm; //确定按钮
        final TextView content; //内容
        confirm = (TextView) view.findViewById(R.id.tv_dialog_ok);
        content = (TextView) view.findViewById(R.id.tv_dialog_message);confirm.setText(ok);
        content.setText(message);
        final Dialog dialog = new Dialog(context,R.style.My_Dialog);
        dialog.setContentView(view);
        dialog.setCancelable(false);
//        dialog.getWindow().setBackgroundDrawableResource(R.color.bg_white);

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegistActivity.this, GestureSetActivity.class);
                intent.putExtra("phoneNumber", etPhone.getText().toString());
                intent.putExtra("jumpFlg", 1);
                startActivity(intent);
                finish();
            }
        });
        dialog.show();
        DisplayMetrics dm = context.getResources().getDisplayMetrics();
        int displayWidth = dm.widthPixels;
        int displayHeight = dm.heightPixels;
        android.view.WindowManager.LayoutParams p = dialog.getWindow().getAttributes(); //获取对话框当前的参数值
        p.width = (int) (displayWidth * 0.8); //宽度设置为屏幕的0.5
        p.height = (int) (displayHeight * 0.25); //宽度设置为屏幕的0.25
        dialog.setCanceledOnTouchOutside(false);// 设置点击屏幕Dialog不消失
        dialog.getWindow().setAttributes(p);  //设置生效

    }


    private void initLogin() {
        showLoadingDialog();
        @SuppressLint("StaticFieldLeak") BizDataAsyncTask<String> getLogin = new BizDataAsyncTask<String>() {
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

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mHandler.removeCallbacksAndMessages(null);
    }
}
