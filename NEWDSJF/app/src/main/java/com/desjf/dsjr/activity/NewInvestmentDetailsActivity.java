package com.desjf.dsjr.activity;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.RecyclerView;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.TextAppearanceSpan;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.desjf.dsjr.R;
import com.desjf.dsjr.activity.bankActivity.RechargeActivity;
import com.desjf.dsjr.adapter.NewDialogRedBagAdapter;
import com.desjf.dsjr.base.BaseActivity;
import com.desjf.dsjr.base.BaseApplication;
import com.desjf.dsjr.bean.Identity;
import com.desjf.dsjr.bean.NewRegBean;
import com.desjf.dsjr.biz.AccountBiz;
import com.desjf.dsjr.biz.AllCouponBiz;
import com.desjf.dsjr.biz.TenderForTransferBiz;
import com.desjf.dsjr.biz.exception.BizFailure;
import com.desjf.dsjr.biz.exception.ZYException;
import com.desjf.dsjr.biz.retrofit.BankHttpUtil;
import com.desjf.dsjr.biz.retrofit.BankHttpUtils;
import com.desjf.dsjr.biz.retrofit.CallUtil;
import com.desjf.dsjr.biz.retrofit.CallUtils;
import com.desjf.dsjr.biz.retrofit.InitHttpUtil;
import com.desjf.dsjr.biz.task.BizDataAsyncTask;
import com.desjf.dsjr.config.DsjrConfig;
import com.desjf.dsjr.model.AccountModel;
import com.desjf.dsjr.model.AllCouponAndInterestModle;
import com.desjf.dsjr.model.AllTenderModel;
import com.desjf.dsjr.model.CouponListForTenderModel;
import com.desjf.dsjr.model.GradeModel;
import com.desjf.dsjr.model.InterestTotalModel;
import com.desjf.dsjr.model.ProjectInfoModel;
import com.desjf.dsjr.model.bankModel.BankCodeModel;
import com.desjf.dsjr.model.bankModel.BankUserBalanceModel;
import com.desjf.dsjr.model.bankModel.BankUserInfModel;
import com.desjf.dsjr.model.bankModel.BankYjsyModel;
import com.desjf.dsjr.util.Constans;
import com.desjf.dsjr.util.MyDialogUtil;
import com.desjf.dsjr.utils.ButtonUtils;
import com.desjf.dsjr.utils.DataUtil;
import com.desjf.dsjr.utils.PreferenceCache;
import com.desjf.dsjr.utils.SoftInputUtil;
import com.desjf.dsjr.utils.StringUtil;
import com.desjf.dsjr.utils.ToastUtils;
import com.desjf.dsjr.widget.CouponListener;
import com.desjf.dsjr.widget.Listener;
import com.desjf.dsjr.widget.WrapContentLinearLayoutManager;
import com.yitong.android.keyboard.AllKeyBoard;
import com.yitong.android.keyboard.CrptoSixKeyUtil;
import com.yitong.android.keyboard.CryptoKeyUtil;
import com.yitong.android.keyboard.util.RandomUtils;
import com.yitong.entity.KeyVo;
import com.yitong.service.APPResponseHandler;
import com.yitong.service.APPRestClient;
import com.yitong.service.YTRequestParams;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;

/**
 * 投资详情的立即投资界面
 */

public class NewInvestmentDetailsActivity extends BaseActivity implements Listener, CouponListener {
    @BindView(R.id.iv_ljtz_back)
    ImageView ivLjtzBack;
    @BindView(R.id.title_tv)
    TextView titleTv;
    @BindView(R.id.tv_ljtz_jkqx)
    TextView tvLjtzJkqx;
    @BindView(R.id.tv_ljtz_zhye)
    TextView tvLjtzZhye;
    @BindView(R.id.tv_ljtz_chongzhi)
    Button tvLjtzChongzhi;
    @BindView(R.id.et_ljtz_back)
    EditText etLjtzBack;
    @BindView(R.id.tv_ljtz_qetz)
    TextView tvLjtzQetz;
    @BindView(R.id.iv_ljtz_wjjymm)
    TextView ivLjtzWjjymm;
    @BindView(R.id.et_ljtz_qsrdxmm)
    EditText etLjtzQsrdxmm;
    @BindView(R.id.tv_ljtz_touzi)
    TextView tvLjtzTouzi;
    @BindView(R.id.tv_tzxq_yqnh)
    TextView tvTzxqYqnh;
    @BindView(R.id.ll_ljtz_redbag_more)
    LinearLayout llLjtzRedbagMore;

    @BindView(R.id.ll_remaining)
    LinearLayout llRemaining; //显示新手标的剩余可投额度总布局
    @BindView(R.id.tv_remaining)
    TextView tv_remaining;//显示新手标的剩余可投额度

    @BindView(R.id.tv_qxzhb)
    TextView tvQxzhb;
    @BindView(R.id.tv_qxzhb_jine)
    TextView tvQxzhbJine;

    @BindView(R.id.et_litj_jymm)
    EditText etLitjJymm;
    @BindView(R.id.ll_dxmm)
    LinearLayout llDxmm; //定向密码
    @BindView(R.id.ll_payPwd)
    LinearLayout llPwd;//动态口令

    @BindView(R.id.ll_Pwd)
    LinearLayout payPwd;//存管密码
    @BindView(R.id.et_pwd)
    EditText etPwd;//输入存管密码
    @BindView(R.id.tv_forget)
    TextView forget;//忘记存管密码

    @BindView(R.id.ll_agreement)
    LinearLayout llAgreement; //投资协议
    @BindView(R.id.tv_set_word)
    TextView tvWord;//提醒文字（未设置交易密码）
    @BindView(R.id.tv_tzxq_tzje_text)
    TextView tvTzxqTzjeText;
    @BindView(R.id.tv_ljtz_hongbao)
    TextView tvLjtzHongbao;

    @BindView(R.id.tv_ljtz_yjsy)
    TextView tvLjtzYjsy;
    @BindView(R.id.ll_ssjss)
    LinearLayout llSsjss;
    @BindView(R.id.tv_jine)
    TextView residue;    //剩余可投金额
    @BindView(R.id.real_pay)
    TextView realPay;    //实际付款金额
    @BindView(R.id.tv_type)//红包还是加息券
    TextView tvType;

    @BindView(R.id.cb_choose)
    CheckBox checkBox;
    @BindView(R.id.tv_code)//动态口令识别码
    TextView tvCode;
    @BindView(R.id.tv_jiaxi)
    TextView jiaxi;


    private ProjectInfoModel projectInfoModel;
    private Context context = this;
    private List<CouponListForTenderModel> redBag_list = new ArrayList<>();
    private List<CouponListForTenderModel> coupon_list = new ArrayList<>();
    private List<AllCouponAndInterestModle> allCouponModel = new ArrayList<>();
    private String redId = "";
    private String couponId = "";
    private String id = "";//标的id
    String coupon;//加息券额度
    private  boolean flag;//是否有交易密码
    private boolean type=false;//用于判断当前按钮的文字   //为true 是代表为立即投资  为0 则代表为开通存管
    private double redMoney;//红包金额
    private String tzje = "";
    private String pwdResult="";//密码键盘加密结果
    private NewDialogRedBagAdapter dialogRedBagAdapter;
    private boolean isCheck=true;
    private boolean isCCheck=true;
    private int i=-1;
    AllTenderModel allTenderModel;

    private  int checkPosition=-1;//记录当前被选中的项，根据此变量值和当前点击的项的位置对比，不同则将isCheck设置为true（解决按钮需点击两次才能改变状态问题）
    private int ifTest=-1;//记录当前用户是否已经进行了风险测评   1代表已经测评  2代表未测评
    private Timer timer;
    private TimerTask timerTask;
    private int count = 60;

    private int j = 0;
    private String value="";
    double c;//用户输入的金额
    String keyong="";//当前账户的可用余额
    private AccountModel accountModels;
    Dialog dialog;
    private String checkType="";//判断当前选中的是红包还是加息券 0代表红包  1代表加息券
    private String checkId="";//存储当前选中的红包或者加息券id
    Identity identity;
    private AllKeyBoard allKeyBoard, numKeyboard,cardPwdKeyboard;// 密码键盘
    BankCodeModel bankCodeModel;
    BankYjsyModel yjsyModel;

    @SuppressLint("HandlerLeak")
    private final Handler mHandler = new Handler(){
        @Override
        public void dispatchMessage(Message msg) {
            if (count >= 0) {
                ivLjtzWjjymm.setText("重发("+count+")");
                ivLjtzWjjymm.setClickable(false);
                count--;
            } else {
                resetTimer();
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_investment_details);
        ButterKnife.bind(this);

        PreferenceCache.putRedBag("");//将红包按钮初始化为未选中

        Intent intent = getIntent();
        projectInfoModel = (ProjectInfoModel) intent.getSerializableExtra("Info");
        id = intent.getStringExtra("ID");//标的ID

        // 判断当前是否开通存管   并对相关布局进行隐藏或者显示
        ifOpenBank();
        initData();

    }
    //初始化
    private void initData() {
        //键盘view的初始化操作
        cardPwdKeyboard = new AllKeyBoard(this, etPwd, true, 2, "9");
        cardPwdKeyboard.showKeyboard(6);

        //可用余额
        tvLjtzZhye.setText("0.00元");
        initWodeMessage();//初始化个人信息  使accountModel不为空
        //初始化投资 协议是否同意
        checkBox.setChecked(PreferenceCache.isAutoInvestment());
        etLjtzBack.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {

                if (b) {

                } else {
                    if (tzje.equals(etLjtzBack.getText().toString())) {

                    } else {
                        tzje = etLjtzBack.getText().toString();
                        //判断当前数值是否为50的整数倍
                        if (!StringUtil.isEmpty(tzje)) {
                        }
                        if (StringUtil.isEmpty(tzje)) {
                            etLjtzBack.setText("");
                        }else{
                            //计算当前实际付款金额
                            double input=Double.parseDouble(etLjtzBack.getText().toString());
                            if(tvType.getText().toString().equals("%加息券")&&!("").equals(tvQxzhbJine.getText().toString())){
                                realPay.setText("实际付款："+input+"元");
                                initInterestInfo(tvQxzhbJine.getText().toString());
                            }else if(!("").equals(tvQxzhbJine.getText().toString())){
                                initInterestInfo();
                                double redmoney=Double.parseDouble(tvQxzhbJine.getText().toString());
                                double real=input-redmoney;
                                realPay.setText("实际付款："+real+"元");
                            }
                            else{
                                initInterestInfo();
                                realPay.setText("实际付款："+input+"元");
                            }
                        }
                    }
                    SoftInputUtil.hideSoftKeyboard(etLjtzBack);
                }
            }
        });
        llSsjss.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                llSsjss.setFocusable(true);
                llSsjss.setFocusableInTouchMode(true);
                llSsjss.requestFocus();
                return false;
            }
        });

        //判断当前标的是不是新手标，新手标则不能使用红包或者加息券
        if(projectInfoModel.getNOVICE_FLG().toString().equals("0")){
            //非新手标
            //显示当前可用红包的个数
            if (projectInfoModel.getTOTAL_COUPON_NUM().equals("0")) {
                tvLjtzHongbao.setText("暂无可用奖励");
            } else {
                tvLjtzHongbao.setText(projectInfoModel.getTOTAL_COUPON_NUM()+ "个可用");
            }
        }else{
            //新手标
            llLjtzRedbagMore.setVisibility(View.GONE);//隐藏选择红包按钮
            tvLjtzHongbao.setText("新手标不能使用奖励券");

            //显示  当前用户 新手标的可投额度
            llRemaining.setVisibility(View.VISIBLE);
            tv_remaining.setText(projectInfoModel.getNoviceLimit());

        }
        //预期年化
        tvTzxqYqnh.setText(projectInfoModel.getFINANCE_INTEREST_RATE());

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

        if (projectInfoModel.getDIRECTIONAL_FLG().equals("0")) {
            llDxmm.setVisibility(View.VISIBLE);
           } else {
            llDxmm.setVisibility(View.GONE);
        }
        //判断是不是流转标
        if (projectInfoModel.getWANDER_FLG().equals("0")) {
            //非流转标
            //剩余可投金额
            residue.setText(projectInfoModel.getSURPLUS_AMOUNT());
            if (projectInfoModel.getTENDER_MIN_CAPTION() != null) {
                etLjtzBack.setHint( projectInfoModel.getTENDER_MIN_CAPTION() + "起投,50的整数倍递增");
            } else {
                etLjtzBack.setHint("");
            }
        } else {
            //剩余可投金额
            residue.setText(projectInfoModel.getSURPLUS_FEN());
            tvTzxqTzjeText.setText("投资份数");
            etLjtzBack.setHint("每份金额" + projectInfoModel.getTENDER_MIN_CAPTION() + "元");
        }



    }
    //先判断是否开通了存管
    public  void ifOpenBank(){
        CallUtils.call(NewInvestmentDetailsActivity.this, BankHttpUtils.getHttpRequestService().queryUserInf(PreferenceCache.getToken()), new CallUtils.MyCallListener() {
            @Override
            public void onRespnseSuccess(String jsonString) {
                BankUserInfModel bankUserInfModel=JSON.parseObject(jsonString,BankUserInfModel.class);
                if (bankUserInfModel.getUsermessage()!=null) {
                    //                    tvWodePhonenum.setText(bankUserInfModel.getUsermessage().get(0).getMbl_no());
                    //如果是老用户且未开通存管则进入普通账户页面
                    CallUtils.call(NewInvestmentDetailsActivity.this, BankHttpUtil.getHttpRequestService().getIdentity( bankUserInfModel.getUsermessage().get(0).getMbl_no()), new CallUtils.MyCallListener() {
                        @Override
                        public void onRespnseSuccess(String jsonString) {
                            identity= JSON.parseObject(jsonString,Identity.class);
                            /**
                             * 判断当前是新用户还是老用户，据此来判断是否显示新、旧账户切换按钮
                             * 0001 请登录   0002 老用户未实  0004  新用户未实名  0005  新用户已实名
                             0003  老用户已实名
                             */
                            if (identity.getMessageType().equals("0002")||identity.getMessageType().equals("0004")) {
                                //如果是新用户 则隐藏
                                llPwd.setVisibility(View.GONE);//隐藏动态口令
                                payPwd.setVisibility(View.GONE);//隐藏存管密码
                                llAgreement.setVisibility(View.GONE);//隐藏投资协议
                                tvWord.setVisibility(View.VISIBLE);//提醒文字显示
                                tvLjtzTouzi.setText("开通存管");//按钮文字设置
                                tvLjtzChongzhi.setVisibility(View.GONE);//隐藏充值按钮
                                type=false;
                            } else {
                                tvWord.setVisibility(View.GONE);//提醒文字隐藏
                                tvLjtzTouzi.setText("立即投资");//按钮文字设置
                                type=true;
                            }
                        }
                    });
                }
                //如果是新用户但是未实名就不会查询到用户个人信息
                else{
                    //如果是新用户 则隐藏
                    llPwd.setVisibility(View.GONE);//隐藏动态口令
                    llAgreement.setVisibility(View.GONE);//隐藏投资协议
                    payPwd.setVisibility(View.GONE);//隐藏存管密码
                    tvWord.setVisibility(View.VISIBLE);//提醒文字显示
                    tvLjtzTouzi.setText("开通存管");//按钮文字设置
                    tvLjtzChongzhi.setVisibility(View.GONE);//隐藏充值按钮
                    type=false;
                }
            }
        });
    }

    //获取我的数据
    private void initWodeMessage() {
        showLoadingDialog();
        @SuppressLint("StaticFieldLeak")
        BizDataAsyncTask<AccountModel> shouyeMessage = new BizDataAsyncTask<AccountModel>() {
            @Override
            protected AccountModel doExecute() throws ZYException, BizFailure {
                return AccountBiz.getWodeMessage();
            }
            @Override
            protected void onExecuteSucceeded(AccountModel accountModel) {
                hideLoadingDialog();
                //将AccountModel存到BaseApplication
                BaseApplication baseApplication = (BaseApplication)getApplication();
                baseApplication.setAccountModel(accountModel);
                //赋值
                accountModels=accountModel;
                //判断是否已经进行了测评
                ifTest();
            }
            @Override
            protected void OnExecuteFailed(String error) {
                hideLoadingDialog();
            }
        };
        shouyeMessage.execute();
        //获取存管账户余额
        CallUtils.call(NewInvestmentDetailsActivity.this, BankHttpUtils.getHttpRequestService().QueryBalance(PreferenceCache.getToken()), new CallUtils.MyCallListener() {
            @Override
            public void onRespnseSuccess(String jsonString) {
                BankUserBalanceModel bankUserBalanceModel=JSON.parseObject(jsonString,BankUserBalanceModel.class);
                if(bankUserBalanceModel.getUsermessage()!=null) {
                    bankUserBalanceModel.getUsermessage();
                    for(int i=0;i<bankUserBalanceModel.getUsermessage().size();i++){
                        if(bankUserBalanceModel.getUsermessage().get(i).getCap_typ().equals("1")){
                            if(String.valueOf(bankUserBalanceModel.getUsermessage().get(i).getAvlBal()).equals("")||String.valueOf(bankUserBalanceModel.getUsermessage().get(i).getAvlBal()).equals("0")) {
                                tvLjtzZhye.setText("0.00元");
                            }else{
                                //可用余额
                                tvLjtzZhye.setText(DataUtil.toMoney(Float.parseFloat(bankUserBalanceModel.getUsermessage().get(i).getAvlBal())));
                            }
                        }
                    }
                }
            }
        });
    }

    //  计算使用红包时的预计收益
    public void initInterestInfo() {
        getYjsy();
//        showLoadingDialog();
//        @SuppressLint("StaticFieldLeak")
//        BizDataAsyncTask<InterestTotalModel> getRedYjsy = new BizDataAsyncTask<InterestTotalModel>() {
//            @Override
//            protected InterestTotalModel doExecute() throws ZYException, BizFailure {
//                return TenderForTransferBiz.getYjsy(id, etLjtzBack.getText().toString(), projectInfoModel.getAddRate());
//            }
//
//            @Override
//            protected void onExecuteSucceeded(InterestTotalModel interestTotalModel) {
//                hideLoadingDialog();
//                if(tvType.getText().toString().equals("元红包")&&!tvQxzhbJine.getText().toString().equals("")){
//                    double redbag=Double.parseDouble(tvQxzhbJine.getText().toString());
//                    double yjsy=Double.parseDouble(interestTotalModel.getEXPECTED_INTEREST());
//                    double money=redbag+yjsy;
//                    BigDecimal b = new  BigDecimal(money);
//                    double  redbags  = b.setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();//固定取小数位后两位
//                    tvLjtzYjsy.setText(""+redbags+ "元");
//                }else {
//                    tvLjtzYjsy.setText(""+interestTotalModel.getEXPECTED_INTEREST() + "元");
//                }
//            }
//
//            @Override
//            protected void OnExecuteFailed(String error) {
//                hideLoadingDialog();
//                ToastUtils.showTost(context,error);
//            }
//        };
//        getRedYjsy.execute();
    }

    //  计算使用加息券时的预计收益
    private void initInterestInfo(String jxq) {
        coupon=jxq;
        showLoadingDialog();
        @SuppressLint("StaticFieldLeak")
        BizDataAsyncTask<InterestTotalModel> getCouponYjsy = new BizDataAsyncTask<InterestTotalModel>() {
            @Override
            protected InterestTotalModel doExecute() throws ZYException, BizFailure {
                return TenderForTransferBiz.getYjsy(id, etLjtzBack.getText().toString(),coupon);
            }

            @Override
            protected void onExecuteSucceeded(InterestTotalModel interestTotalModel) {
                hideLoadingDialog();
                tvLjtzYjsy.setText("" + interestTotalModel.getEXPECTED_INTEREST() + "元");
            }

            @Override
            protected void OnExecuteFailed(String error) {
                hideLoadingDialog();
                ToastUtils.showTost(context,error);
            }
        };
        getCouponYjsy.execute();
    }


    @OnClick({R.id.iv_ljtz_back,R.id.tv_ljtz_chongzhi,R.id.tv_ljtz_qetz, R.id.iv_ljtz_wjjymm, R.id.tv_ljtz_touzi, R.id.ll_ljtz_redbag_more
    ,R.id.tv_book,R.id.tv_agreement,R.id.tv_forget})  //, R.id.ll_ljtz_jxj_more
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_ljtz_chongzhi:
                Intent intent = new Intent(NewInvestmentDetailsActivity.this, RechargeActivity.class);
                startActivity(intent);
                break;
            case R.id.tv_book:
               //查看风险提示书
                Intent intent3 = new Intent(NewInvestmentDetailsActivity.this, WebViewActivity.class);
                intent3.putExtra("web", 27);
                startActivity(intent3);
                break;
            case R.id.tv_agreement:
               //查看电子签章协议
                Intent intent5 = new Intent(NewInvestmentDetailsActivity.this, WebViewActivity.class);
                intent5.putExtra("web", 28);
                startActivity(intent5);
                break;
            case R.id.iv_ljtz_back:
                finish();
                break;
            case R.id.tv_ljtz_qetz:
                if (type) {
                    getQETZ();
                }
                break;
            case R.id.iv_ljtz_wjjymm:
                ivLjtzWjjymm.setClickable(false);
                showLoadingDialog();
                CallUtil.call(NewInvestmentDetailsActivity.this, BankHttpUtils.getHttpRequestService().dynamicpassword(PreferenceCache.getToken()), new CallUtil.MyCallListener() {
                    @Override
                    public void onRespnseSuccess(String jsonString) {
                        hideLoadingDialog();
                        BankCodeModel bankCodeModel=JSON.parseObject(jsonString,BankCodeModel.class);
                        tvCode.setVisibility(View.VISIBLE);
                        if(bankCodeModel.isStatus()){
                            //如果发送成功 启动倒计时
                            runTimerTask();
                        }else{
                            ivLjtzWjjymm.setClickable(true);
                        }
                        tvCode.setText("识别码:"+bankCodeModel.getMessage());
                    }

                    @Override
                    public void onRespnseFailure(Call<String> call, Throwable t) {
                        hideLoadingDialog();
                        ivLjtzWjjymm.setClickable(true);
                        ToastUtils.showShortTost(NewInvestmentDetailsActivity.this,"获取失败,请稍后再试");
                    }
                });
                break;
            case R.id.tv_ljtz_touzi:
                //如果当前用户开通了存管
                if(type){
                    if (ButtonUtils.isFastClick()) {
                        // 进行点击事件后的逻辑操作
                        initLJtz();
                    }
                }else {
                    //前往开通存管
                    CallUtils.call(NewInvestmentDetailsActivity.this, BankHttpUtil.getHttpRequestService().bankReg(accountModels.getPHONENUMBER()), new CallUtils.MyCallListener() {
                        @Override
                        public void onRespnseSuccess(String jsonString) {
                            NewRegBean newRegBean= JSON.parseObject(jsonString,NewRegBean.class);
                            Intent i=new Intent(NewInvestmentDetailsActivity.this,BankWebViewActivity.class);
                            i.putExtra("type",1);
                            i.putExtra("newRegBean",newRegBean);
                            startActivity(i);

                        }
                    });
                }

                break;
            case R.id.tv_forget:
                //找回密码
                CallUtils.call(NewInvestmentDetailsActivity.this, BankHttpUtil.getHttpRequestService().changePwd(accountModels.getPHONENUMBER()), new CallUtils.MyCallListener() {
                    @Override
                    public void onRespnseSuccess(String jsonString) {
                        NewRegBean newRegBean= JSON.parseObject(jsonString,NewRegBean.class);
                        Intent i=new Intent(NewInvestmentDetailsActivity.this,BankWebViewActivity.class);
                        i.putExtra("newRegBean",newRegBean);
                        i.putExtra("type",7);
                        startActivity(i);
                    }
                });
                break;
            case R.id.ll_ljtz_redbag_more:
                if (projectInfoModel.getTOTAL_COUPON_NUM().equals("0")) {
                    ToastUtils.showTost(context, "暂无可用红包");
                } else {
                        getAllBag(j);
                }
                break;
        }
    }

    //获得当前所有满足该标的的红包和加息券
    private void getAllBag(final int j) {
        showLoadingDialog();
        @SuppressLint("StaticFieldLeak")
        BizDataAsyncTask<List<AllCouponAndInterestModle>> getCoupon = new BizDataAsyncTask<List<AllCouponAndInterestModle>>() {
            @Override
            protected List<AllCouponAndInterestModle> doExecute() throws ZYException, BizFailure {
                return AllCouponBiz.getCouponAndInterest(projectInfoModel.getFINANCE_PERIOD(),String.valueOf(j), "200",projectInfoModel.getOidFinanceProductsTypeId());
            }

            @Override
            protected void onExecuteSucceeded(final List<AllCouponAndInterestModle> allCouponModels) {
                hideLoadingDialog();
                allCouponModel.clear();
                init(allCouponModels);
            }

            @Override
            protected void OnExecuteFailed(String error) {
                hideLoadingDialog();
            }
        };
        getCoupon.execute();
    }

    //初始化  红包弹框选择器
    private void init(final List<AllCouponAndInterestModle> allCouponModels) {
        allCouponModel.addAll(allCouponModels);
        if (allCouponModel.size() == 0) {
            ToastUtils.showTost(context, "暂无可用奖励");
        } else {
            dialog = new Dialog(NewInvestmentDetailsActivity.this, R.style.My_Dialog);
            dialog.setContentView(R.layout.item_choose_redbag);
            dialog.setCancelable(false);
            dialog.show();
            //设置Dialog的宽度铺满屏幕 并从底部弹出
            Window window = dialog.getWindow();
            window.setGravity(Gravity.BOTTOM); //可设置dialog的位置
            window.getDecorView().setPadding(0, 0, 0, 0); //消除边距
            WindowManager.LayoutParams lp = window.getAttributes();
            lp.width = WindowManager.LayoutParams.MATCH_PARENT;   //设置宽度充满屏幕
            lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
            window.setAttributes(lp);

            if (projectInfoModel.getWANDER_FLG().equals("1")){
                //是流转表
                if((("").equals(etLjtzBack.getText().toString()))){
                    c=0;
                }else {
                     c = Double.parseDouble(etLjtzBack.getText().toString());
                }
                double d = Double.parseDouble(projectInfoModel.getPART_AMOUNT());
                double e = c * d;
                double f= (double) e;
                value  = String.valueOf(f);
            }else{
                //非流转标
                value = etLjtzBack.getText().toString();
            }

            ImageView iv_back = (ImageView) dialog.findViewById(R.id.iv_choose_redbag_back);
            RecyclerView rl_redbag = (RecyclerView) dialog.findViewById(R.id.rl_choose_redbag);
            TextView tv_queren = (TextView) dialog.findViewById(R.id.tv_choose_redbag_btn);
            dialogRedBagAdapter = new NewDialogRedBagAdapter(allCouponModel,NewInvestmentDetailsActivity.this,value,projectInfoModel.getFINANCE_PERIOD(),projectInfoModel.getINTEREST_RATE_TYPE());
            rl_redbag.setAdapter(dialogRedBagAdapter);
            rl_redbag.setLayoutManager(new WrapContentLinearLayoutManager(NewInvestmentDetailsActivity.this));


            //判断当前是否有已经选中的按钮，有则将该按钮设置为选中状态
            if(!("").equals(PreferenceCache.getRedBag())){
                dialogRedBagAdapter.setFlg("1");
                int butPosition=Integer.parseInt(PreferenceCache.getRedBag());
                i=butPosition;
                dialogRedBagAdapter.setI(butPosition);
            }
            dialogRedBagAdapter.notifyDataSetChanged();
            iv_back.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (i==-1) {
                        checkId="";
                        redMoney=0;
                        tvQxzhbJine.setVisibility(View.GONE);
                        tvQxzhb.setText("请选择");
                        if(!("").equals(etLjtzBack.getText().toString())){
                            double input=Double.parseDouble(etLjtzBack.getText().toString());
                            realPay.setText("实际付款："+input+"元");
                            initInterestInfo();
                            tvQxzhbJine.setText("");
                            tvLjtzHongbao.setText("请选择奖励");
                        }else{
                            tvQxzhbJine.setText("");
                            realPay.setText("实际付款：0元");
                            tvLjtzHongbao.setText("请选择奖励");
                        }
                        tvType.setText("奖励");
                        dialog.dismiss();
                    } else {
                        dialog.dismiss();
                    }
                }
            });
            rl_redbag.addOnItemTouchListener(new OnItemClickListener() {
                @Override
                public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {
                    //如果当前点击的红包位置和已经选择了的红包位置不同
                    if(checkPosition!=position){
                        isCheck=true;
                    }

                    double i1 = Double.parseDouble(projectInfoModel.getFINANCE_PERIOD());
                    String qi_tou_period = allCouponModel.get(position).getPERIOD().substring(1,2);
//                    qi_tou_period = qi_tou_period.substring(0,qi_tou_period.length()-2);
                    double i2 = Double.parseDouble(qi_tou_period);
                    double mini = Double.parseDouble(allCouponModel.get(position).getMINIMUM_TENDER_AMOUNT().substring(1,allCouponModel.get(position).getMINIMUM_TENDER_AMOUNT().length()-1));
                    double money=-1;
                    double cc;
                    if (projectInfoModel.getWANDER_FLG().equals("1")){
                        //是流转表
                        if(("").equals(etLjtzBack.getText().toString())){
                           cc=0;
                        }else{
                           cc = Double.parseDouble(etLjtzBack.getText().toString());
                        }
                        double d = Double.parseDouble(projectInfoModel.getPART_AMOUNT());
                        double e = c * d;
                        double f= (double) e;
                        money = f;
                    }else{
                        //非流转标
                        if(("").equals(etLjtzBack.getText().toString())){
                            money=0;
                        }else{
                            money = Double.parseDouble(etLjtzBack.getText().toString());
                        }
                    }
                    if(money==0){
                        if (isCheck){
                            PreferenceCache.putRedBag(position+"");
                            dialogRedBagAdapter.setFlg("1");
                            dialogRedBagAdapter.setI(position);
                            checkPosition=position;
                            i=position;
                            dialogRedBagAdapter.notifyDataSetChanged();
                            isCheck =!isCheck;
                        }else{
                            PreferenceCache.putRedBag("");
                            checkPosition=position;
                            i=-1;
                            dialogRedBagAdapter.setI(position);
                            dialogRedBagAdapter.setFlg("0");
                            dialogRedBagAdapter.notifyDataSetChanged();
                            isCheck =!isCheck;
                        }
                    }else{
                        if (money>=mini&&i1>=i2){
                            if (isCheck){
                                PreferenceCache.putRedBag(position+"");
                                dialogRedBagAdapter.setFlg("1");
                                dialogRedBagAdapter.setI(position);
                                checkPosition=position;
                                i=position;
                                dialogRedBagAdapter.notifyDataSetChanged();
                                isCheck =!isCheck;
                            }else{
                                PreferenceCache.putRedBag("");
                                checkPosition=position;
                                i=-1;
                                dialogRedBagAdapter.setI(position);
                                dialogRedBagAdapter.setFlg("0");
                                dialogRedBagAdapter.notifyDataSetChanged();
                                isCheck =!isCheck;
                            }
                        }else{
                            return;
                        }
                    }
                }
            });
            tv_queren.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //如果当前没有选中红包 则清空相应数据，并还原到初始状态
                    if (i==-1){
                        dialog.dismiss();
                        checkId="";
                        checkType="";
                        redMoney=0;
                        tvQxzhbJine.setVisibility(View.GONE);
                        tvQxzhb.setText("请选择");
                        if(!("").equals(etLjtzBack.getText().toString())){
                            double input=Double.parseDouble(etLjtzBack.getText().toString());
                            realPay.setText("实际付款："+input+"元");
                            initInterestInfo();
                            tvQxzhbJine.setText("");
                            tvLjtzHongbao.setText("请选择奖励");
                        }else{
                            tvQxzhbJine.setText("");
                            realPay.setText("实际付款：0元");
                            tvLjtzHongbao.setText("请选择奖励");
                        }
                        tvType.setText("奖励");
                    }else{
                        String red_amount = allCouponModel.get(i).getNUMBER();
                        tvQxzhb.setText("已选择");
                        tvQxzhbJine.setText(red_amount);
                        tvQxzhbJine.setVisibility(View.VISIBLE);

                        //计算当前实际付款金额
                        if(("").equals(etLjtzBack.getText().toString())){
                           //金额输入为空时不进行预计收益的计算
                        }else{
//                            double input=Double.parseDouble(etLjtzBack.getText().toString());
//                            double redmoney=Double.parseDouble(red_amount);
//                            double real;
//                            //红包 or 加息券
//                            if(allCouponModel.get(i).getTYPE().equals("0")){//红包
//                                real=input-redmoney;
//                            }else{//加息券
//                                real=input;
//                            }
////                            initInterestInfo();
//                            realPay.setText("实际付款："+real+"元");
                            initInterestInfo();
                        }
                        //提示当前最低投资金额
                        tvLjtzHongbao.setTextColor(context.getResources().getColor(R.color.nmainOrign));
                        tvLjtzHongbao.setText("需投资"+allCouponModel.get(i).getMINIMUM_TENDER_AMOUNT());
                        //红包 or 加息券
                        if(allCouponModel.get(i).getTYPE().equals("0")){//红包

                            tvType.setText(allCouponModels.get(i).getWORD());
                            redMoney=Double.parseDouble(allCouponModel.get(i).getMINIMUM_TENDER_AMOUNT().substring(1,allCouponModel.get(i).getMINIMUM_TENDER_AMOUNT().length()-1));
//                            initInterestInfo();
//                            redId = allCouponModel.get(i).getRED_PACKET_LOG_ID();
                            checkType="0";
                            checkId=allCouponModel.get(i).getRED_PACKET_LOG_ID();

                        }else{//加息券
                            tvType.setText(allCouponModels.get(i).getWORD());
                            initInterestInfo(allCouponModel.get(i).getNUMBER());
                            redMoney=Double.parseDouble(allCouponModel.get(i).getMINIMUM_TENDER_AMOUNT().substring(1,allCouponModel.get(i).getMINIMUM_TENDER_AMOUNT().length()-1));
//                            redId = allCouponModel.get(i).getRATE_COUPON_SEND_ID();
//                            couponId= allCouponModel.get(i).getRATE_COUPON_SEND_ID();
                            checkType="1";
                            checkId= allCouponModel.get(i).getRATE_COUPON_SEND_ID();

                        }
                        i=-1;
                        dialog.dismiss();
                    }
                }

//                        }
            });
        }

    }
       //计算预计收益
    private void  getQETZ() {
        showLoadingDialog();
        CallUtil.call(NewInvestmentDetailsActivity.this, InitHttpUtil.getHttpRequestService().allTenderNew(PreferenceCache.getToken(),id),new CallUtil.MyCallListener() {
            @Override
            public void onRespnseSuccess(String jsonString) {
                hideLoadingDialog();
               bankCodeModel=JSON.parseObject(jsonString,BankCodeModel.class);
                if (bankCodeModel.isStatus()) {
                    etLjtzBack.setText(bankCodeModel.getMessage());
                }
             getYjsy();
            }
            @Override
            public void onRespnseFailure(Call<String> call, Throwable t) {

            }
        });
    }

    //实际付款
    private void   actually(){
//        if(!("").equals(etLjtzBack.getText().toString())){
            double inpu=Double.parseDouble(etLjtzBack.getText().toString());
            BigDecimal in=new BigDecimal(inpu);
//        }
        if(tvType.getText().toString().equals("元红包")&&!tvQxzhbJine.getText().toString().equals("")){
            double redbag=Double.parseDouble(tvQxzhbJine.getText().toString());
            double yjsy=Double.parseDouble(yjsyModel.getExpectedInvest());
            tvLjtzYjsy.setText("" + (redbag+yjsy) + "元");
            double redmone=Double.parseDouble(tvQxzhbJine.getText().toString());
            double rea=inpu-redmone;
            BigDecimal r=new BigDecimal(rea);
            realPay.setText("实际付款："+r+"元");

        }else {
            tvLjtzYjsy.setText(yjsyModel.getExpectedInvest() + "元");
            realPay.setText("实际付款："+in+"元");

        }

    }

    //新接口计算预计收益
    private  void getYjsy(){
        CallUtil.call(NewInvestmentDetailsActivity.this, InitHttpUtil.getHttpRequestService().getExpectedInvest(id,etLjtzBack.getText().toString(),
                projectInfoModel.getAddRate()), new CallUtil.MyCallListener() {
            @Override
            public void onRespnseSuccess(String jsonString) {
                if (JSON.parseObject(jsonString,BankYjsyModel.class).isStatus()) {
                    yjsyModel=JSON.parseObject(jsonString,BankYjsyModel.class);
                }
                actually();
            }

            @Override
            public void onRespnseFailure(Call<String> call, Throwable t) {

            }
        });

    }


    private void initLJtz() {

        if (etLjtzBack.getText().toString().isEmpty()) {
            ToastUtils.showTost(context, "请输入投资金额");
            return;
        }
        if (etLitjJymm.getText().toString().isEmpty()) {
            ToastUtils.showTost(context, "请输入动态口令");
            return;
        }
        if (etPwd.getText().toString().isEmpty()) {
            ToastUtils.showTost(context, "请输入存管密码");
            return;
        }

        //保存投资协议选中的状态
        PreferenceCache.putAutoInvestment(checkBox.isChecked());
        if (!checkBox.isChecked()==true) {
            ToastUtils.showTost(context, "请您确认并勾选协议");
            return;
        }
        //判断当前用户是否进行了测评  没有测评则弹框提示进入测评  否则不能投资
        if (ifTest==2) {
           showDialog("为了您的权益，请进行风险测评");
            return;
        }

        double m=Double.parseDouble(etLjtzBack.getText().toString());
        if(m<redMoney){
            ToastUtils.showTost(context, "您当前输入的金额不满足当前红包使用条件");
            return;
        }
        //获得当前传入的id，并对其进行判断  红包和加息券不能同时使用
//        if(checkType.equals("0")){
//            redId=checkId;
//            couponId="";
//        }
//        if(checkType.equals("1")){
//            couponId=checkId;
//            redId="";
//        }

//        int visibility = llDxmm.getVisibility();
//        if (visibility == 0) {
//            if (etLjtzQsrdxmm.getText().toString().isEmpty()) {
//                ToastUtils.showTost(context, "请输入定向密码");
//                return;
//            }
//        }

        //获取加密结果进行投资
        pwdResult="";
        getCardPwdKeyboardValue();

    }
    private void getCardPwdKeyboardValue(){
        //将按钮 改为不可点击  文字改为 正在投资
        tvLjtzTouzi.setClickable(false);
        tvLjtzTouzi.setText("正在投资...");
        showLoadingDialog();
        String content = etPwd.getText().toString();
        if (content.length() < 1) {
            return;
        }
        YTRequestParams reqParams = new YTRequestParams(
                YTRequestParams.PARAM_TYPE_JSON);
        String key = YTRequestParams.genRandomKey();
        APPRestClient.post(NewInvestmentDetailsActivity.this, Constans.URL,
                reqParams, new APPResponseHandler<KeyVo>(KeyVo.class, key) {
                    @Override
                    public void onSuccess(KeyVo result) {
                        if (result != null && "1".equals(result.getSTATUS())) {
                            String pubKey = result.getPubKey();
                            String random8 = RandomUtils.generateNumberString(8);
                            StringBuffer inputValue = new StringBuffer(etPwd.getTag().toString());
                            pwdResult = random8+CrptoSixKeyUtil.decrypt(pubKey, random8, CryptoKeyUtil.genRandomNum(32),inputValue );
                            cardPwdKeyboard.clear();
                            CallUtil.call(NewInvestmentDetailsActivity.this, InitHttpUtil.getHttpRequestService().investment(PreferenceCache.getToken(),
                                    id, etLitjJymm.getText().toString(), etLjtzBack.getText().toString(), checkType,
                                    checkId,pwdResult,etLjtzQsrdxmm.getText().toString(),"2"), new CallUtil.MyCallListener() {
                                @Override
                                public void onRespnseSuccess(String jsonString) {
                                    hideLoadingDialog();
                                    BankCodeModel bankCodeModel=JSON.parseObject(jsonString,BankCodeModel.class);
                                    if(bankCodeModel!=null){
                                        if (bankCodeModel.isStatus()) {
                                            showSkipDialog(context,"恭喜您投资成功！","知道了");
                                        } else {
                                            MyDialogUtil.showSimpleDialog(context,bankCodeModel.getMessage(),"知道了");
                                        }
                                    }
                                    //将按钮 改为可点击  文字改为 立即投资
                                    tvLjtzTouzi.setClickable(true);
                                    tvLjtzTouzi.setText("立即投资");
                                }

                                @Override
                                public void onRespnseFailure(Call<String> call, Throwable t) {
                                    hideLoadingDialog();
                                    ToastUtils.showTost(context,"投资失败，请您稍后再试");
                                    tvLjtzTouzi.setClickable(true);
                                    tvLjtzTouzi.setText("立即投资");
                                }
                            });

                        }

                    }

                    @Override
                    public void onFailure(String s, String s1) {
                        //将按钮 改为可点击  文字改为 立即投资
                        tvLjtzTouzi.setClickable(true);
                        tvLjtzTouzi.setText("立即投资");

                    }

                    @Override
                    public void onFinish() {
                        super.onFinish();

                    }
                }, key);
    }


    /**
     * 查看当前用户是否测评，如果已经测评  类型是什么
     */
    private void  ifTest(){

        CallUtils.call(NewInvestmentDetailsActivity.this, InitHttpUtil.getHttpRequestService().ifTest(accountModels.getPHONENUMBER()), new CallUtils.MyCallListener() {
            @Override
            public void onRespnseSuccess(String jsonString) {
                GradeModel gradeModel= JSON.parseObject(jsonString,GradeModel.class);
                /**
                 *  Message : 0 代表保守型   1 代表稳健型  2代表积极型
                 *
                 *  ""代表没有进行测评
                 */
                if(!gradeModel.getMessage().equals("")){
                    ifTest=1;
                }else {
                    ifTest=2;
                    //判断当前用户是否进行了测评  没有测评则弹框提示进入测评  否则不能投资
                    showDialog("为了您的权益，请进行风险测评");

                }
            }
        });


    }

    /**
     * 风险测评  弹框
     * @param msg
     */
    private void showDialog(String msg) {

        final Dialog dialog = new Dialog(this, R.style.My_Dialog);
        dialog.setContentView(R.layout.dialog_auth_name);
        dialog.setCancelable(false);
        TextView tv_msg = (TextView) dialog.findViewById(R.id.tv_msg);
        tv_msg.setText(msg);
        TextView tv_ok = (TextView) dialog.findViewById(R.id.tv_ok);
        tv_ok.setText("前往测评");
        dialog.findViewById(R.id.tv_cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        dialog.findViewById(R.id.tv_ok).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //调用授权方法
                Intent i=new Intent(context,RiskEvaluationActivity.class);
                startActivity(i);
                finish();
                dialog.dismiss();
            }
        });
        dialog.show();
        WindowManager m = (WindowManager) this.getSystemService(Context.WINDOW_SERVICE);
        Display d = m.getDefaultDisplay();  //为获取屏幕宽、高
        android.view.WindowManager.LayoutParams p = dialog.getWindow().getAttributes();  //获取对话框当前的参数值
        p.height = (int) (d.getHeight() * 0.3);   //高度设置为屏幕的0.3
        p.width = (int) (d.getWidth() * 0.8);    //宽度设置为屏幕的0.5
        dialog.setCanceledOnTouchOutside(false);// 设置点击屏幕Dialog不消失
        dialog.getWindow().setAttributes(p);     //设置生效

    }

     //投资是否成功提示框
    public  void showSkipDialog(Context context , String message,String ok) {
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

                //跳转到 我的 页面
                Intent intent = new Intent(NewInvestmentDetailsActivity.this,MainActivity.class);
                DsjrConfig.WHERE =1;
                DsjrConfig.TO_MY=2;
                startActivity(intent);
                //发送广播  刷新个人中心数据
                Intent i = new Intent();
                i.setAction("withdraw");
                BaseApplication.getAppContext().sendBroadcast(i);

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

    @Override
    public void send(int s) {
        String red_amount = redBag_list.get(s).getRED_AMOUNT();
        tvQxzhb.setText("已选择");
        tvQxzhbJine.setText(red_amount);
        tvQxzhbJine.setVisibility(View.VISIBLE);
        redId = redBag_list.get(s).getRED_PACKET_LOG_ID();
    }


    @Override
    public void sendCoupon(int s) {
        String rate_coupon_position = coupon_list.get(s).getRATE_COUPON_POSITION();
        couponId = coupon_list.get(s).getRATE_COUPON_ID();
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
        ivLjtzWjjymm.setText(R.string.find_getverifycode);
        ivLjtzWjjymm.setClickable(true);
        count = 60;
        timerTask.cancel();
        timer.cancel();
        timerTask = null;
        timer = null;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mHandler.removeCallbacksAndMessages(null);
    }

}
