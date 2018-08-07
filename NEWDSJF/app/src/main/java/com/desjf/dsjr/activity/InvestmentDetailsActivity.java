package com.desjf.dsjr.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.style.TextAppearanceSpan;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.desjf.dsjr.R;
import com.desjf.dsjr.base.BaseActivity;
import com.desjf.dsjr.base.BaseApplication;
import com.desjf.dsjr.biz.AllTenderBiz;
import com.desjf.dsjr.biz.exception.BizFailure;
import com.desjf.dsjr.biz.exception.ZYException;
import com.desjf.dsjr.biz.retrofit.CallUtil;
import com.desjf.dsjr.biz.retrofit.InitHttpUtil;
import com.desjf.dsjr.biz.task.BizDataAsyncTask;
import com.desjf.dsjr.model.AccountModel;
import com.desjf.dsjr.model.AllTenderModel;
import com.desjf.dsjr.model.ProjectInfoModel;
import com.desjf.dsjr.model.bankModel.BankYjsyModel;
import com.desjf.dsjr.utils.SoftInputUtil;
import com.desjf.dsjr.utils.StringUtil;
import com.desjf.dsjr.utils.ToastUtils;
import com.desjf.dsjr.widget.Listener;

import java.lang.ref.WeakReference;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;

/**
 *      用户未登录时  投资详情的立即投资界面
 */

public class InvestmentDetailsActivity extends BaseActivity implements Listener{
    @BindView(R.id.iv_ljtz_back)
    ImageView ivLjtzBack;
    @BindView(R.id.title_tv)
    TextView titleTv;
    @BindView(R.id.tv_ljtz_jkqx) //期限
    TextView tvLjtzJkqx;
    @BindView(R.id.et_ljtz_back)//投资金额
    EditText etLjtzBack;
    @BindView(R.id.tv_jine)//剩余可投金额
    TextView jine;
    @BindView(R.id.login_first)//请先登录
    LinearLayout login;
    @BindView(R.id.tv_ljtz_touzi)//立即登录
    TextView tvLjtzTouzi;
    @BindView(R.id.zhuce)//注册
    TextView zhuce;
    @BindView(R.id.tv_tzxq_yqnh)//预期年化
    TextView tvTzxqYqnh;
    @BindView(R.id.yugu)//预计收益显示
    RelativeLayout yugu;
    @BindView(R.id.tv_ljtz_yjsy)//预计收益
    TextView tvLjtzYjsy;
    @BindView(R.id.tv_jiaxi)
    TextView jiaxi;

    private ProjectInfoModel projectInfoModel;
    private ProjectInfoModel couponInfo;
    private Context context = this;
    private String redId = "";
    private String couponId = "";
    private String id = "";//标的id
    private boolean type=false;//用于判断当前按钮的文字   //为true 是代表为立即投资  为0 则代表为设置交易密码
    private final Handler handler = new MyHandler(this);
    private String tzje = "";

    private String value="";
    private AccountModel accountModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_investment_details);
        ButterKnife.bind(this);

        BaseApplication baseApplication = (BaseApplication) getApplication();
        accountModel= baseApplication.getAccountModel();

        Intent intent = getIntent();
        projectInfoModel = (ProjectInfoModel) intent.getSerializableExtra("Info");
        id = intent.getStringExtra("ID");//标的ID

        initData();

//        bid=intent.getStringExtra("bid");//当前红包或者加息券的id
//        type=intent.getStringExtra("type");//是红包还是加息券

    }
    private static class MyHandler extends Handler {
        private final WeakReference<InvestmentDetailsActivity> mActivity;

        public MyHandler(InvestmentDetailsActivity activity) {
            mActivity = new WeakReference<InvestmentDetailsActivity>(activity);
        }

    }
//    Handler handler = new Handler();
    /**
     * 延迟线程，看是否还有下一个字符输入
     */
    Runnable delayRun = new Runnable() {
        @Override
        public void run() {
            //在这里调用服务器的接口，获取数据
             initInterestInfo();
        }
    };


    private void initData() {
        Runnable run;
        etLjtzBack.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
              double  mill = System.currentTimeMillis();
                if (delayRun != null) {
                    //每次editText有变化的时候，则移除上次发出的延迟线程
                    handler.removeCallbacks(delayRun);
                }

                //延迟800ms，如果不再输入字符，则执行该线程的run方法
                if (!TextUtils.isEmpty(etLjtzBack.getText())) {
                        handler.postDelayed(delayRun, 800);
                }
            }
        });


        etLjtzBack.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                Log.e("onFocusChange",b+"");
                if (b) {
                } else {
                    if (tzje.equals(etLjtzBack.getText().toString())) {
                    } else {
                        tzje = etLjtzBack.getText().toString();
                        //判断当前数值是否为50的整数倍
                        int money=Integer.parseInt(tzje);
                        if(money%50!=0){
                            Toast.makeText(context,"请输入50的整数倍的数值!",Toast.LENGTH_LONG).show();
                        }
                        if (StringUtil.isEmpty(tzje)) {
                            etLjtzBack.setText("xx");
                        }else{
                            Log.e("onFocusChange",b+"进入计算投资效益");
                            initInterestInfo();
                        }
                    }
                    SoftInputUtil.hideSoftKeyboard(etLjtzBack);
                }
            }
        });
//        llSsjss.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View view, MotionEvent motionEvent) {
//                llSsjss.setFocusable(true);
//                llSsjss.setFocusableInTouchMode(true);
//                llSsjss.requestFocus();
//                return false;
//            }
//        });

        //预期年化
        String yqbh = projectInfoModel.getFINANCE_INTEREST_RATE();
//        SpannableString style = new SpannableString(yqbh);
//        style.setSpan(new TextAppearanceSpan(this, R.style.style2), yqbh.length() - 1, yqbh.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
//        style.setSpan(new TextAppearanceSpan(this, R.style.style1), 0, yqbh.length() - 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        tvTzxqYqnh.setText(yqbh);

        //加息
        if(projectInfoModel.getAddRate().equals("0")){
            jiaxi.setVisibility(View.INVISIBLE);
        }else{
            jiaxi.setVisibility(View.VISIBLE);
            jiaxi.setText("+"+projectInfoModel.getAddRate()+"%");
        }


        //借款期限
        String jkqx = projectInfoModel.getFINANCE_PERIOD() + projectInfoModel.getINTEREST_RATE_TYPE();
        SpannableString ss = new SpannableString(jkqx);
        ss.setSpan(new TextAppearanceSpan(this, R.style.style2), projectInfoModel.getFINANCE_PERIOD().length(), jkqx.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        ss.setSpan(new TextAppearanceSpan(this, R.style.style1), 0, projectInfoModel.getFINANCE_PERIOD().length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        tvLjtzJkqx.setText(ss, TextView.BufferType.SPANNABLE);
        //账户余额
//        tvLjtzZhye.setText(projectInfoModel.getBALANCE() + "元");


        //判断是不是流转标
        if (projectInfoModel.getWANDER_FLG().equals("0")) {
            //非流转标
            //剩余可投金额
            //剩余可投金额
            jine.setText(projectInfoModel.getSURPLUS_AMOUNT());
            if (projectInfoModel.getTENDER_MIN_CAPTION() != null) {
                etLjtzBack.setHint("投资金额≥" + projectInfoModel.getTENDER_MIN_CAPTION() + "元且为50的倍数");
            } else {
                etLjtzBack.setHint("");
            }
        } else {
            //剩余可投金额
            jine.setText(projectInfoModel.getSURPLUS_AMOUNT());
            etLjtzBack.setHint("每份金额" + projectInfoModel.getTENDER_MIN_CAPTION() + "元");
        }


    }

    //  计算 预计收益
    private void initInterestInfo() {

        getYjsy();

//        showLoadingDialog();
//        BizDataAsyncTask<InterestTotalModel> getYjsy = new BizDataAsyncTask<InterestTotalModel>() {
//            @Override
//            protected InterestTotalModel doExecute() throws ZYException, BizFailure {
//                return TenderForTransferBiz.getNoLoginYjsyk(id, etLjtzBack.getText().toString(), projectInfoModel.getAddRate());
//            }
//
//            @Override
//            protected void onExecuteSucceeded(InterestTotalModel interestTotalModel) {
//                hideLoadingDialog();
//                tvLjtzYjsy.setText("" + interestTotalModel.getEXPECTED_INTEREST() + "元");
//            }
//
//            @Override
//            protected void OnExecuteFailed(String error) {
//                hideLoadingDialog();
//            }
//        };
//        getYjsy.execute();
    }

    //新接口计算预计收益
    private  void getYjsy(){
        CallUtil.call(InvestmentDetailsActivity.this, InitHttpUtil.getHttpRequestService().getExpectedInvest(id,etLjtzBack.getText().toString(),
                projectInfoModel.getAddRate()), new CallUtil.MyCallListener() {
            @Override
            public void onRespnseSuccess(String jsonString) {
                if (JSON.parseObject(jsonString,BankYjsyModel.class).isStatus()) {
                    BankYjsyModel  yjsyModel=JSON.parseObject(jsonString,BankYjsyModel.class);
                    tvLjtzYjsy.setText("" + yjsyModel.getExpectedInvest() + "元");
                }

            }

            @Override
            public void onRespnseFailure(Call<String> call, Throwable t) {

            }
        });

    }


    @OnClick({R.id.iv_ljtz_back,R.id.login_first, R.id.zhuce,R.id.tv_ljtz_touzi,R.id.yugu})  //, R.id.ll_ljtz_jxj_more
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_ljtz_back:
                finish();
                break;
            case R.id.login_first:
                Intent intent_login1 = new Intent(InvestmentDetailsActivity.this,LoginActivity.class);
                intent_login1.putExtra("login",3);
                startActivity(intent_login1);
                break;
            case R.id.zhuce:
                Intent intent_re = new Intent(InvestmentDetailsActivity.this,RegistActivity.class);
                startActivity(intent_re);
                break;
            case R.id.tv_ljtz_touzi:
                Intent intent_login2 = new Intent(InvestmentDetailsActivity.this,LoginActivity.class);
                intent_login2.putExtra("login",3);
                startActivity(intent_login2);
                break;
            case R.id.yugu:
               initInterestInfo();
                break;

        }
    }



    private void getQETZ() {
        showLoadingDialog();
        BizDataAsyncTask<AllTenderModel> getAll = new BizDataAsyncTask<AllTenderModel>() {
            @Override
            protected AllTenderModel doExecute() throws ZYException, BizFailure {
                return AllTenderBiz.getAllTender(id, "");
            }

            @Override
            protected void onExecuteSucceeded(AllTenderModel allTenderModel) {
                hideLoadingDialog();
                etLjtzBack.setText(allTenderModel.getALL_AMOUNT());
                tvLjtzYjsy.setText("" + allTenderModel.getEXPECTED_INTEREST() + "元");
            }

            @Override
            protected void OnExecuteFailed(String error) {
                hideLoadingDialog();
                ToastUtils.showTost(InvestmentDetailsActivity.this,error.toString());
            }
        };
        getAll.execute();
    }

    @Override
    public void send(int s) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        handler.removeCallbacksAndMessages(null);
    }
}
