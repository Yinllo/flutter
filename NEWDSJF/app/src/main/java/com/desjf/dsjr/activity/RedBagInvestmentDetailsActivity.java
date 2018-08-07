package com.desjf.dsjr.activity;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.TextAppearanceSpan;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.desjf.dsjr.R;
import com.desjf.dsjr.adapter.NewDialogRedBagAdapter;
import com.desjf.dsjr.base.BaseActivity;
import com.desjf.dsjr.base.BaseApplication;
import com.desjf.dsjr.biz.AccountBiz;
import com.desjf.dsjr.biz.AllCouponBiz;
import com.desjf.dsjr.biz.AllTenderBiz;
import com.desjf.dsjr.biz.BiddingBiz;
import com.desjf.dsjr.biz.TenderForTransferBiz;
import com.desjf.dsjr.biz.TransactionPasswordBiz;
import com.desjf.dsjr.biz.exception.BizFailure;
import com.desjf.dsjr.biz.exception.ZYException;
import com.desjf.dsjr.biz.task.BizDataAsyncTask;
import com.desjf.dsjr.model.AccountModel;
import com.desjf.dsjr.model.AllCouponAndInterestModle;
import com.desjf.dsjr.model.AllTenderModel;
import com.desjf.dsjr.model.CouponListForTenderModel;
import com.desjf.dsjr.model.InterestTotalModel;
import com.desjf.dsjr.model.ProjectInfoModel;
import com.desjf.dsjr.utils.PreferenceCache;
import com.desjf.dsjr.utils.SoftInputUtil;
import com.desjf.dsjr.utils.StringUtil;
import com.desjf.dsjr.utils.ToastUtils;
import com.desjf.dsjr.widget.CouponDialog;
import com.desjf.dsjr.widget.CouponListener;
import com.desjf.dsjr.widget.Listener;
import com.desjf.dsjr.widget.WrapContentLinearLayoutManager;
import com.umeng.analytics.MobclickAgent;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 投资详情的立即投资界面
 */

public class RedBagInvestmentDetailsActivity extends BaseActivity implements Listener, CouponListener {
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

    //    @BindView(R.id.ll_ljtz_jxj_more)
//    LinearLayout llLjtzJxjMore;
    @BindView(R.id.tv_qxzhb)
    TextView tvQxzhb;
    @BindView(R.id.tv_qxzhb_jine)
    TextView tvQxzhbJine;
    //    @BindView(R.id.tv_qxzjxj)
//    TextView tvQxzjxj;
//    @BindView(R.id.tv_qxzjxj_lil)
//    TextView tvQxzjxjLil;
    @BindView(R.id.et_litj_jymm)
    EditText etLitjJymm;
    @BindView(R.id.ll_dxmm)
    LinearLayout llDxmm; //定向密码
    @BindView(R.id.ll_payPwd)
    LinearLayout llPwd; //交易密码
    @BindView(R.id.tv_set_word)
    TextView tvWord;//提醒文字（未设置交易密码）
    @BindView(R.id.tv_tzxq_tzje_text)
    TextView tvTzxqTzjeText;
    @BindView(R.id.tv_ljtz_hongbao)
    TextView tvLjtzHongbao;
    //    @BindView(R.id.tv_ljtz_jiaxijuan)
//    TextView tvLjtzJiaxijuan;
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

    private ProjectInfoModel projectInfoModel;
    private ProjectInfoModel couponInfo;
    private CouponDialog couponDialog;
    private Context context = this;
    private List<CouponListForTenderModel> redBag_list = new ArrayList<>();
    private List<CouponListForTenderModel> coupon_list = new ArrayList<>();
    private List<AllCouponAndInterestModle> allCouponModel = new ArrayList<>();
    private String redId = "";
    private String couponId = "";
    private String id = "";//标的id
    String coupon;//加息券额度
    private String rid = "";//红包或卡券id
    private String redLogId="";//红包使用时传递给后台用来扣除红包的id
    private String rtype = "";//当前是红包还是加息券
    private String number = "";//当前红包或加息券的金额
    private String min = "";//当前红包或加息券的最小投资金额
    private String miaoshu = "";
    private  boolean flag;//是否有交易密码
    private boolean type=false;//用于判断当前按钮的文字   //为true 是代表为立即投资  为0 则代表为设置交易密码
    private double redMoney;//红包金额
    private String tzje = "";
    private NewDialogRedBagAdapter dialogRedBagAdapter;
    private boolean isCheck=true;
    private boolean isCCheck=true;
    private int i=-1;
    private static int checkPosition=-1;
    private int j = 0;
    private String value="";
    double c;//用户输入的金额
    private AccountModel accountModels;
    Dialog dialog;
    int first=-1;
    private int checkType=-1;//判断当前选中的是红包还是加息券 1代表红包  2代表加息券
    private String checkId="";//存储当前选中的红包或者加息券id

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_investment_details);
        ButterKnife.bind(this);
        first=1;//判断当前是不是第一次跳转进来  只有第一次才自动填写

//        BaseApplication baseApplication = (BaseApplication) getApplication();
//        accountModels= baseApplication.getAccountModel();
        Intent intent = getIntent();
        projectInfoModel = (ProjectInfoModel) intent.getSerializableExtra("Info");
        id = intent.getStringExtra("ID");//标的ID

        //传递过来的红包相关参数
        rid =intent.getStringExtra("id");
        rtype=intent.getStringExtra("type");
        number=intent.getStringExtra("number");
        min =intent.getStringExtra("min");
        miaoshu=intent.getStringExtra("miaoshu");
        redLogId=intent.getStringExtra("redLogId");
        initData();

//        bid=intent.getStringExtra("bid");//当前红包或者加息券的id
//        type=intent.getStringExtra("type");//是红包还是加息券

    }

    //初始化
    private void initData() {
        initWodeMessage();//初始化个人信息  使accountModel不为空
        //从红包卡券点击红包跳转到当前页面时 将获得的红包或者加息券的信息初始化到界面上
        tvQxzhb.setText("已选择");
        tvQxzhbJine.setVisibility(View.VISIBLE);
        tvQxzhbJine.setText(number);
        tvType.setText(miaoshu);
        etLjtzBack.setText(min.substring(1,min.length()-1));//投资金额设置
        tvLjtzHongbao.setTextColor(context.getResources().getColor(R.color.nmainOrign));
        tvLjtzHongbao.setText("需投资"+min);
        double input=Double.parseDouble(etLjtzBack.getText().toString());
        if(tvType.getText().toString().equals("%加息券")&&!("").equals(tvQxzhbJine.getText().toString())){
            realPay.setText("实际付款"+input+"元");
            redMoney=Double.parseDouble(min.substring(1,min.length()-1));
            initInterestInfo(tvQxzhbJine.getText().toString());
        }else if(!("").equals(tvQxzhbJine.getText().toString())){
            initInterestInfo();
            redMoney=Double.parseDouble(min.substring(1,min.length()-1));
            double redmoney=Double.parseDouble(tvQxzhbJine.getText().toString());
            double real=input-redmoney;
            realPay.setText("实际付款"+real+"元");
        }



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
                            int money = Integer.parseInt(tzje);
                            if(money%50!=0){
                                Toast.makeText(context,"请输入50的整数倍的数值!",Toast.LENGTH_LONG).show();
                            }
                        }
                        if (StringUtil.isEmpty(tzje)) {
                            etLjtzBack.setText("");
                        }else{
                            //计算当前实际付款金额
                            double input=Double.parseDouble(etLjtzBack.getText().toString());
                            if(tvType.getText().toString().equals("%加息券")&&!("").equals(tvQxzhbJine.getText().toString())){
                                realPay.setText("实际付款"+input+"元");
                                initInterestInfo(tvQxzhbJine.getText().toString());
                            }else if(!("").equals(tvQxzhbJine.getText().toString())){
                                initInterestInfo();
                                double redmoney=Double.parseDouble(tvQxzhbJine.getText().toString());
                                double real=input-redmoney;
                                realPay.setText("实际付款"+real+"元");
                            }
                            else{
                                initInterestInfo();
                                realPay.setText("实际付款"+input+"元");

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
//        if(projectInfoModel.getNOVICE_FLG().toString().equals("0")){
//            //非新手标
//            //显示当前可用红包的个数
//            if (projectInfoModel.getTOTAL_COUPON_NUM().equals("0")) {
//                tvLjtzHongbao.setText("暂无可用奖励");
//            } else {
//                tvLjtzHongbao.setText(projectInfoModel.getTOTAL_COUPON_NUM()+ "个可用");
//            }
//        }else{
//            //新手标
//            llLjtzRedbagMore.setVisibility(View.GONE);//隐藏选择红包按钮
//            tvLjtzHongbao.setText("新手标不能使用奖励券");
////            realPay.setVisibility(View.GONE);//隐藏实际付款计算
//        }




        //预期年化
        String yqbh = projectInfoModel.getFINANCE_INTEREST_RATE() + "%";
        SpannableString style = new SpannableString(yqbh);
        style.setSpan(new TextAppearanceSpan(this, R.style.style2), yqbh.length() - 1, yqbh.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        style.setSpan(new TextAppearanceSpan(this, R.style.style1), 0, yqbh.length() - 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        tvTzxqYqnh.setText(style, TextView.BufferType.SPANNABLE);
        //借款期限
        String jkqx = projectInfoModel.getFINANCE_PERIOD() + projectInfoModel.getINTEREST_RATE_TYPE();
        SpannableString ss = new SpannableString(jkqx);
        ss.setSpan(new TextAppearanceSpan(this, R.style.style2), projectInfoModel.getFINANCE_PERIOD().length(), jkqx.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        ss.setSpan(new TextAppearanceSpan(this, R.style.style1), 0, projectInfoModel.getFINANCE_PERIOD().length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        tvLjtzJkqx.setText(ss, TextView.BufferType.SPANNABLE);
        //账户余额
        tvLjtzZhye.setText(projectInfoModel.getBALANCE() + "元");

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

        // 判断当前是否有交易密码   并对相关布局进行隐藏或者显示
        ifsetPayPsw();

    }

    //先判断是否设置了交易密码
    public  void ifsetPayPsw(){
//        showLoadingDialog();
        BizDataAsyncTask<String> ifsetPayPsw = new BizDataAsyncTask<String>() {
            @Override
            protected String doExecute() throws ZYException, BizFailure {
                return  TransactionPasswordBiz.ifSettedPayPassword();
            }

            @Override
            protected void onExecuteSucceeded(String s) {
//                hideLoadingDialog();
                tvWord.setVisibility(View.GONE);//提醒文字隐藏
                tvLjtzTouzi.setText("立即投资");//按钮文字设置
                type=true;
            }

            @Override
            protected void OnExecuteFailed(String error) {
//                hideLoadingDialog();
                llPwd.setVisibility(View.GONE);
                tvWord.setVisibility(View.VISIBLE);//提醒文字隐藏
                tvLjtzTouzi.setText("设置交易密码");//按钮文字设置
                type=false;
            }
        };
        ifsetPayPsw.execute();
    }


    //  计算使用红包时的预计收益

    public void initInterestInfo() {
        showLoadingDialog();
        BizDataAsyncTask<InterestTotalModel> getRedYjsy = new BizDataAsyncTask<InterestTotalModel>() {
            @Override
            protected InterestTotalModel doExecute() throws ZYException, BizFailure {
                return TenderForTransferBiz.getYjsy(id, etLjtzBack.getText().toString(), "");
            }

            @Override
            protected void onExecuteSucceeded(InterestTotalModel interestTotalModel) {
                hideLoadingDialog();
                if(tvType.getText().toString().equals("元红包")&&!tvQxzhbJine.getText().toString().equals("")){
                    double redbag=Double.parseDouble(tvQxzhbJine.getText().toString());
                    double yjsy=Double.parseDouble(interestTotalModel.getEXPECTED_INTEREST());
                    double money=redbag+yjsy;
                    BigDecimal b = new  BigDecimal(money);
                    double  redbags  = b.setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();//固定取小数位后两位
                    tvLjtzYjsy.setText("" + redbags+ "元");
                }else {
                    tvLjtzYjsy.setText("" + interestTotalModel.getEXPECTED_INTEREST() + "元");
                }
            }

            @Override
            protected void OnExecuteFailed(String error) {
                hideLoadingDialog();
                ToastUtils.showTost(context,error);
            }
        };
        getRedYjsy.execute();
    }


    //  计算使用加息券时的预计收益
    private void initInterestInfo(String jxq) {
        coupon=jxq;
        showLoadingDialog();
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


    @OnClick({R.id.iv_ljtz_back,R.id.tv_ljtz_chongzhi,R.id.tv_ljtz_qetz, R.id.iv_ljtz_wjjymm, R.id.tv_ljtz_touzi, R.id.ll_ljtz_redbag_more})  //, R.id.ll_ljtz_jxj_more
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_ljtz_chongzhi:
                //原逻辑通过判断 accountModel 中的值来判断是否进行了四要素验证 ，但是accountModel为空

                if (accountModels!=null){
//                if ( projectInfoModel.getID_CARD_VERIFY_FLG().equals("0")){
                    if (accountModels.getIDCARDVERIFYFLG().toString().equals("0")||accountModels.getBANKFLG().toString().equals("0")){
                        Intent intent = new Intent(RedBagInvestmentDetailsActivity.this, AccountRealNameActivity.class);
                        intent.putExtra("RealName",1);
                        intent.putExtra("UserName",accountModels.getUSERNAME());
                        intent.putExtra("UserID",accountModels.getIDCARD());
                        showToast("请先验证四要素");
                        startActivity(intent);
                    }else{

                        Intent intent = new Intent(RedBagInvestmentDetailsActivity.this, AccountChargeActivity.class);
                        startActivity(intent);
                    }
                }
                break;
            case R.id.iv_ljtz_back:
                finish();
                break;
            case R.id.tv_ljtz_qetz:
                getQETZ();
                break;
            case R.id.iv_ljtz_wjjymm:
                Intent intent_mm = new Intent(RedBagInvestmentDetailsActivity.this, ForgetJYPswStepOneActivity.class);
                startActivity(intent_mm);
                break;
            case R.id.tv_ljtz_touzi:
                if(type){
                    initLJtz();
                }else {     //当前
                    if(accountModels!=null){
                        if (accountModels.getIDCARDVERIFYFLG().toString().equals("0")||accountModels.getBANKFLG().toString().equals("0")) {
                            Intent intent = new Intent(RedBagInvestmentDetailsActivity.this, AccountRealNameActivity.class);
                            intent.putExtra("RealName", 1);
                            intent.putExtra("UserName", accountModels.getUSERNAME());
                            intent.putExtra("UserID", accountModels.getIDCARD());
                            showToast("请先验证四要素");
                            startActivity(intent);
                        }
                        else{
                            //跳转到设置交易密码的界面 并传入返回需要的参数
                            Intent intent_setJyPsw = new Intent(this, SetJYPswActivity.class);
                            intent_setJyPsw.putExtra("ID",id);
                            intent_setJyPsw.putExtra("Info", projectInfoModel);
                            startActivity(intent_setJyPsw);
                        }
                    }
                    else{
                        ToastUtils.showTost(RedBagInvestmentDetailsActivity.this,"请确认您是否已经完成实名认证！");
                        //跳转到设置交易密码的界面 并传入返回需要的参数
//                        Intent intent_setJyPsw = new Intent(this, SetJYPswActivity.class);
//                        intent_setJyPsw.putExtra("ID",id);
//                        intent_setJyPsw.putExtra("Info", projectInfoModel);
//                        startActivity(intent_setJyPsw);
                    }
                }
                break;
            case R.id.ll_ljtz_redbag_more:
                if (projectInfoModel.getTOTAL_COUPON_NUM().equals("0")) {
                    ToastUtils.showTost(context, "暂无可用红包");
                } else {
//                    if (etLjtzBack.getText().toString().isEmpty()) {
//                        ToastUtils.showTost(context, "请输入投资金额");
//                        return;
//                    } else {
                    //获取当前红包和加息券
                    getAllBag(j);
//                    }
                }
                break;
        }
    }

    //获得当前所有满足该标的的红包和加息券
    private void getAllBag(final int j) {
        showLoadingDialog();
        BizDataAsyncTask<List<AllCouponAndInterestModle>> getCoupon = new BizDataAsyncTask<List<AllCouponAndInterestModle>>() {
            @Override
            protected List<AllCouponAndInterestModle> doExecute() throws ZYException, BizFailure {
                return AllCouponBiz.getCouponAndInterest(projectInfoModel.getFINANCE_PERIOD(),String.valueOf(j), "30",projectInfoModel.getOidFinanceProductsTypeId());
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
            dialog = new Dialog(RedBagInvestmentDetailsActivity.this, R.style.My_Dialog);
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
            dialogRedBagAdapter = new NewDialogRedBagAdapter(allCouponModel,RedBagInvestmentDetailsActivity.this,value,projectInfoModel.getFINANCE_PERIOD(),projectInfoModel.getINTEREST_RATE_TYPE());
            rl_redbag.setAdapter(dialogRedBagAdapter);
            rl_redbag.setLayoutManager(new WrapContentLinearLayoutManager(RedBagInvestmentDetailsActivity.this));

            //遍历当前allCouponModel集合 找到跳转红包的位置  并将id值传递
            if(first==1) {
                for (int k = 0; k < allCouponModel.size(); k++) {
                    first--;
                    if (rtype.equals("0")) {
                        if (allCouponModel.get(k).getTYPE().equals("0")) {
                            if (allCouponModel.get(k).getRED_PACKET_ID().equals(rid)) {
                                i = k;
                                PreferenceCache.putRedBag(k+"");
                                dialogRedBagAdapter.setFlg("1");
                                dialogRedBagAdapter.setI(i);
                                redId=allCouponModel.get(k).getRED_PACKET_LOG_ID();
                            }
                        }
                    } else {
                        if (allCouponModel.get(k).getTYPE().equals("1")) {
                            if (allCouponModel.get(k).getRATE_COUPON_SEND_ID().equals(rid)) {
                                i = k;
                                PreferenceCache.putRedBag(k+"");
                                dialogRedBagAdapter.setFlg("1");
                                dialogRedBagAdapter.setI(i);
                                couponId= allCouponModel.get(k).getRATE_COUPON_SEND_ID();
                            }
                        }
                    }
                }
            }
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
                            realPay.setText("实际付款"+input+"元");
                            initInterestInfo();
                            tvQxzhbJine.setText("");
                            tvLjtzHongbao.setText("请选择奖励");
                        }else{
                            tvQxzhbJine.setText("");
                            realPay.setText("实际付款0元");
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
                            i=position;
                            dialogRedBagAdapter.notifyDataSetChanged();
                            isCheck =!isCheck;
                        }else{
                            PreferenceCache.putRedBag("");
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
                                i=position;
                                dialogRedBagAdapter.notifyDataSetChanged();
                                isCheck =!isCheck;
                            }else{
                                PreferenceCache.putRedBag("");
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
                        redMoney=0;
                        checkId="";
                        tvQxzhbJine.setVisibility(View.GONE);
                        tvQxzhb.setText("请选择");
                        if(!("").equals(etLjtzBack.getText().toString())){
                            double input=Double.parseDouble(etLjtzBack.getText().toString());
                            realPay.setText("实际付款"+input+"元");
                            initInterestInfo();
                            tvQxzhbJine.setText("");
                            tvLjtzHongbao.setText("请选择奖励");
                        }else{
                            tvQxzhbJine.setText("");
                            realPay.setText("实际付款0元");
                            tvLjtzHongbao.setText("请选择奖励");
                        }
                        tvType.setText("奖励");
                    }else{
//                                if (allCouponModel.get(i).getCHAI_FEN_FLG().equals("0")){
//                                    //是拆分红包
//                                    String red_amount = redBag_list.get(i).getRED_AMOUNT();
//                                    String amount = red_amount.substring(0,red_amount.length()-1);
//                                    double real_amount = Double.parseDouble(amount);
//                                    Log.e("TAG", "onClick: "+real_amount+"");
//                                    String str  = etLjtzBack.getText().toString();
//                                    String substring = str.substring(0,str.length());
//                                    double money = Double.parseDouble(substring);
//                                    double bili = Double.parseDouble(redBag_list.get(i).getCHAI_FEN_BI_LI());
//                                    double a = (money*bili)/1000;
//                                    int b = (int) a;
//                                    if (real_amount>=a){
//                                        tvQxzhbJine.setText(a+"元");
//                                    }else{
//                                        tvQxzhbJine.setText(real_amount+"元");
//                                    }
//                                    tvQxzhb.setText("已选择");
//                                    tvQxzhbJine.setVisibility(View.VISIBLE);
//                                    redId = redBag_list.get(i).getRED_PACKET_LOG_ID();
//                                    i=-1;
//                                    dialog.dismiss();
//                                }else{
                        //不是拆分红包

                        String red_amount = allCouponModel.get(i).getNUMBER();
                        tvQxzhb.setText("已选择");
                        tvQxzhbJine.setText(red_amount);
                        tvQxzhbJine.setVisibility(View.VISIBLE);

                        //计算当前实际付款金额
                        if(("").equals(etLjtzBack.getText().toString())){

                        }else{
                            double input=Double.parseDouble(etLjtzBack.getText().toString());
                            double redmoney=Double.parseDouble(red_amount);
                            double real;
                            //红包 or 加息券
                            if(allCouponModel.get(i).getTYPE().equals("0")){//红包
                                real=input-redmoney;
                            }else{//加息券
                                real=input;
                            }
//                            initInterestInfo();
                            realPay.setText("实际付款"+real+"元");
                        }
                        //提示当前最低投资金额
                        tvLjtzHongbao.setTextColor(context.getResources().getColor(R.color.nmainOrign));
                        tvLjtzHongbao.setText("需投资"+allCouponModel.get(i).getMINIMUM_TENDER_AMOUNT());
                        //红包 or 加息券
                        if(allCouponModel.get(i).getTYPE().equals("0")){//红包

                            tvType.setText(allCouponModels.get(i).getWORD());
                            redMoney=Double.parseDouble(allCouponModel.get(i).getMINIMUM_TENDER_AMOUNT().substring(1,allCouponModel.get(i).getMINIMUM_TENDER_AMOUNT().length()-1));
                            initInterestInfo();
//                            redId = allCouponModel.get(i).getRED_PACKET_LOG_ID();
                            checkType=1;
                            checkId=allCouponModel.get(i).getRED_PACKET_LOG_ID();
                        }else{//加息券
                            tvType.setText(allCouponModels.get(i).getWORD());
                            initInterestInfo(allCouponModel.get(i).getNUMBER());
                            redMoney=Double.parseDouble(allCouponModel.get(i).getMINIMUM_TENDER_AMOUNT().substring(1,allCouponModel.get(i).getMINIMUM_TENDER_AMOUNT().length()-1));
//                            redId = allCouponModel.get(i).getRATE_COUPON_SEND_ID();
//                            couponId= allCouponModel.get(i).getRATE_COUPON_SEND_ID();
                            checkType=2;
                            checkId= allCouponModel.get(i).getRATE_COUPON_SEND_ID();
                        }
                        i=-1;
                        dialog.dismiss();
                    }
                }
            });
        }
    }

    private void initLJtz() {
        //判断当前是不是直接从红包卡券进来  没有经过点击选择红包的操作就直接投资  是滴话直接给上id值
       if(first==1) {
           if (rtype.equals("0")) {
               redId = redLogId;
           } else {
               couponId = rid;
           }
       }else{
           redId="";
           couponId="";
       }
        if (etLjtzBack.getText().toString().isEmpty()) {
            ToastUtils.showTost(context, "请输入投资金额");
            return;
        }
        if (etLitjJymm.getText().toString().isEmpty()) {
            ToastUtils.showTost(context, "请输入交易密码");
            return;
        }
        double m=Double.parseDouble(etLjtzBack.getText().toString());
        if(m<redMoney){
            ToastUtils.showTost(context, "您当前输入的金额不满足当前红包使用条件");
            return;
        }

        //获得当前传入的id，并对其进行判断
        if(checkType==1){
            redId=checkId;
        }
        if(checkType==2){
            couponId=checkId;
        }
//        int visibility = llDxmm.getVisibility();
//        if (visibility == 0) {
//            if (etLjtzQsrdxmm.getText().toString().isEmpty()) {
//                ToastUtils.showTost(context, "请输入定向密码");
//                return;
//            }
//        }


        showLoadingDialog();
        BizDataAsyncTask<String> bindding = new BizDataAsyncTask<String>() {
            @Override
            protected String doExecute() throws ZYException, BizFailure {
                //统计投资次数
                return BiddingBiz.getBidding(id, etLjtzBack.getText().toString(), etLjtzQsrdxmm.getText().toString()
                        , redId, couponId, etLitjJymm.getText().toString());
            }

            @Override
            protected void onExecuteSucceeded(String s) {
                MobclickAgent.onEvent(RedBagInvestmentDetailsActivity.this,"立即投资");

                hideLoadingDialog();
                showSkipDialog(context,"恭喜您投资成功！","知道了");
//                finish();

            }

            @Override
            protected void OnExecuteFailed(String error) {
                hideLoadingDialog();
                ToastUtils.showTost(context, error.toString());
            }
        };
        bindding.execute();
    }

    //计算预计收益
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
                double inpu=Double.parseDouble(etLjtzBack.getText().toString());
                if(tvType.getText().toString().equals("元红包")&&!tvQxzhbJine.getText().toString().equals("")){
                    double redbag=Double.parseDouble(tvQxzhbJine.getText().toString());
                    double yjsy=Double.parseDouble( allTenderModel.getEXPECTED_INTEREST());
                    double money=redbag+yjsy;
                    BigDecimal b = new  BigDecimal(money);
                    double  redbags  = b.setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();//固定取小数位后两位
                    tvLjtzYjsy.setText(""+redbags+ "元");
                    double redmone=Double.parseDouble(tvQxzhbJine.getText().toString());
                    double rea=inpu-redmone;
                    realPay.setText("实际付款"+rea+"元");
                }else {
                    tvLjtzYjsy.setText("" +  allTenderModel.getEXPECTED_INTEREST() + "元");
                    realPay.setText("实际付款"+inpu+"元");
                }
                //                tvLjtzYjsy.setText("预计收益" + allTenderModel.getEXPECTED_INTEREST() + "元");
            }

            @Override
            protected void OnExecuteFailed(String error) {
                hideLoadingDialog();
                ToastUtils.showTost(RedBagInvestmentDetailsActivity.this,error.toString());
            }
        };
        getAll.execute();
    }


    //获取我的数据   在当前界面需要获取当前用户是否进行四要素认证等
    private void initWodeMessage() {
        showLoadingDialog();
        BizDataAsyncTask<AccountModel> shouyeMessage = new BizDataAsyncTask<AccountModel>() {
            @Override
            protected AccountModel doExecute() throws ZYException, BizFailure {
                return AccountBiz.getWodeMessage();
            }

            @Override
            protected void onExecuteSucceeded(AccountModel accountModel) {
                hideLoadingDialog();
                //将AccountModel存到BaseApplication
                BaseApplication baseApplication = (BaseApplication) getApplication();
                baseApplication.setAccountModel(accountModel);
                accountModels=accountModel;
            }

            @Override
            protected void OnExecuteFailed(String error) {
                hideLoadingDialog();
//                ToastUtils.showTost(context,error.toString());
            }
        };
        shouyeMessage.execute();
    }


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
                Intent i = new Intent();
                i.setAction("shuaxin");
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
//        tvQxzjxj.setText("已选择");
//        tvQxzjxjLil.setText(rate_coupon_position);
//        tvQxzjxjLil.setVisibility(View.VISIBLE);
        couponId = coupon_list.get(s).getRATE_COUPON_ID();
    }






//
//    private void getCouponList() {
//        showLoadingDialog();
//        BizDataAsyncTask<List<CouponListForTenderModel>> getJxj = new BizDataAsyncTask<List<CouponListForTenderModel>>() {
//            @Override
//            protected List<CouponListForTenderModel> doExecute() throws ZYException, BizFailure {
//                return AllCouponBiz.getJXJ(id);
//            }
//
//            @Override
//            protected void onExecuteSucceeded(final List<CouponListForTenderModel> couponListForTenderModels) {
//                hideLoadingDialog();
//                coupon_list = couponListForTenderModels;
//                if (couponListForTenderModels.size() == 0) {
//                    ToastUtils.showTost(context, "暂无加息劵");
//                } else {
//                    final Dialog dialog = new Dialog(RedBagInvestmentDetailsActivity.this, R.style.My_Dialog);
//                    dialog.setContentView(R.layout.item_choose_redbag);
//                    dialog.setCancelable(false);
//                    dialog.show();
//
//                    if (projectInfoModel.getWANDER_FLG().equals("1")){
//                        //是流转表
//                        double c = Double.parseDouble(etLjtzBack.getText().toString());
//                        double d = Double.parseDouble(projectInfoModel.getPART_AMOUNT());
//                        double e = c * d;
//                        int f= (int) e;
//                        value  = String.valueOf(f);
//                    }else{
//                        //非流转标
//                        value = etLjtzBack.getText().toString();
//                    }
//                    ImageView iv_back = (ImageView) dialog.findViewById(R.id.iv_choose_redbag_back);
//                    RecyclerView rl_redbag = (RecyclerView) dialog.findViewById(R.id.rl_choose_redbag);
//                    TextView tv_queren = (TextView) dialog.findViewById(R.id.tv_choose_redbag_btn);
//                    dialogCouponAdapter = new DialogCouponAdapter(couponListForTenderModels,RedBagInvestmentDetailsActivity.this,value,projectInfoModel.getFINANCE_PERIOD(),projectInfoModel.getINTEREST_RATE_TYPE());
//                    rl_redbag.setAdapter(dialogCouponAdapter);
//                    rl_redbag.setLayoutManager(new WrapContentLinearLayoutManager(RedBagInvestmentDetailsActivity.this));
//                    dialogCouponAdapter.notifyDataSetChanged();
//
//                    iv_back.setOnClickListener(new View.OnClickListener() {
//                        @Override
//                        public void onClick(View view) {
//                            dialog.dismiss();
//                        }
//                    });
//                    rl_redbag.addOnItemTouchListener(new OnItemClickListener() {
//                        @Override
//                        public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {
////                            adapter.getItem(position)
//                            String qi_tou_period = couponListForTenderModels.get(position).getQI_TOU_PERIOD();
//                            qi_tou_period = qi_tou_period.substring(0,qi_tou_period.length()-2);
//                            int i2 = Integer.parseInt(qi_tou_period);
//                            int i1 = Integer.parseInt(projectInfoModel.getFINANCE_PERIOD());
//
//
//                            int mini = Integer.parseInt(coupon_list.get(position).getCONDITIONS());
//                            int money=-1;
//                            if (projectInfoModel.getWANDER_FLG().equals("1")){
//                                //是流转表
//                                double c = Double.parseDouble(etLjtzBack.getText().toString());
//                                double d = Double.parseDouble(projectInfoModel.getPART_AMOUNT());
//                                double e = c * d;
//                                int f= (int) e;
//                                money = f;
//                            }else{
//                                //非流转标
//                                money = Integer.parseInt(etLjtzBack.getText().toString());
//                            }
//                            if (money>=mini&&i1>i2){
//                                if (isCCheck){
//                                    dialogCouponAdapter.setFlg("1");
//                                    dialogCouponAdapter.setI(position);
//                                    i=position;
//                                    dialogCouponAdapter.notifyDataSetChanged();
//                                    isCCheck =!isCCheck;
//                                }else{
//                                    i=position;
//                                    dialogCouponAdapter.setI(position);
//                                    dialogCouponAdapter.setFlg("0");
//                                    dialogCouponAdapter.notifyDataSetChanged();
//                                    isCCheck =!isCCheck;
//                                }
//                            }else{
//                                return;
//                            }
//
//
//                        }
//                    });
//                    tv_queren.setOnClickListener(new View.OnClickListener() {
//                        @Override
//                        public void onClick(View view) {
//                            if (i==-1){
//                                dialog.dismiss();
//                                couponId="";
////                                tvQxzjxjLil.setVisibility(View.GONE);
//                                tvQxzhb.setText("请选择");
//                            }else{
//                                String red_amount = coupon_list.get(i).getRATE_COUPON_POSITION();
//                                tvQxzhb.setText("已选择");
////                                tvQxzjxjLil.setText(red_amount+"%");
////                                tvQxzjxjLil.setVisibility(View.VISIBLE);
//                                couponId = coupon_list.get(i).getRATE_COUPON_ID();
//                                i=-1;
//                                dialog.dismiss();
//                            }
//
//                        }
//                    });
//                }
//            }
//
//            @Override
//            protected void OnExecuteFailed(String error) {
//                hideLoadingDialog();
//            }
//        };
//        getJxj.execute();
//    }
//
//    private void getRedBagList() {
//        showLoadingDialog();
//        BizDataAsyncTask<List<CouponListForTenderModel>> getList = new BizDataAsyncTask<List<CouponListForTenderModel>>() {
//            @Override
//            protected List<CouponListForTenderModel> doExecute() throws ZYException, BizFailure {
//                return AllCouponBiz.getCoupnList(id);
//            }
//
//            @Override
//            protected void onExecuteSucceeded(final List<CouponListForTenderModel> couponListForTenderModels) {
//                hideLoadingDialog();
//                redBag_list = couponListForTenderModels;
//                if (couponListForTenderModels.size() == 0) {
//                    ToastUtils.showTost(context, "暂无红包");
//                } else {
//                    //重写dialog
//                    final Dialog dialog = new Dialog(RedBagInvestmentDetailsActivity.this, R.style.My_Dialog);
//                    dialog.setContentView(R.layout.item_choose_redbag);
//                    dialog.setCancelable(false);
//                    dialog.show();
//                    ImageView iv_back = (ImageView) dialog.findViewById(R.id.iv_choose_redbag_back);
//                    RecyclerView rl_redbag = (RecyclerView) dialog.findViewById(R.id.rl_choose_redbag);
//                    TextView tv_queren = (TextView) dialog.findViewById(R.id.tv_choose_redbag_btn);
//
//                    if (projectInfoModel.getWANDER_FLG().equals("1")){
//                        //是流转表
//                        double c = Double.parseDouble(etLjtzBack.getText().toString());
//                        double d = Double.parseDouble(projectInfoModel.getPART_AMOUNT());
//                        double e = c * d;
//                        int f= (int) e;
//                        value  = String.valueOf(f);
//                    }else{
//                        //非流转标
//                        value = etLjtzBack.getText().toString();
//                    }
//                    dialogRedBagAdapter = new DialogRedBagAdapter(couponListForTenderModels,RedBagInvestmentDetailsActivity.this,value,projectInfoModel.getFINANCE_PERIOD(),projectInfoModel.getINTEREST_RATE_TYPE());
//                    rl_redbag.setAdapter(dialogRedBagAdapter);
//                    rl_redbag.setLayoutManager(new WrapContentLinearLayoutManager(RedBagInvestmentDetailsActivity.this));
//                    dialogRedBagAdapter.notifyDataSetChanged();
//                    iv_back.setOnClickListener(new View.OnClickListener() {
//                        @Override
//                        public void onClick(View view) {
//                            dialog.dismiss();
//                        }
//                    });
//                    rl_redbag.addOnItemTouchListener(new OnItemClickListener() {
//                        @Override
//                        public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {
//                            int i1 = Integer.parseInt(projectInfoModel.getFINANCE_PERIOD());
//                            String qi_tou_period = couponListForTenderModels.get(position).getQI_TOU_PERIOD();
//                            qi_tou_period = qi_tou_period.substring(0,qi_tou_period.length()-2);
//                            int i2 = Integer.parseInt(qi_tou_period);
//
//                            int mini = Integer.parseInt(couponListForTenderModels.get(position).getMINI_TENDER());
//                            int money=-1;
//                            if (projectInfoModel.getWANDER_FLG().equals("1")){
//                                //是流转表
//                                double c = Double.parseDouble(etLjtzBack.getText().toString());
//                                double d = Double.parseDouble(projectInfoModel.getPART_AMOUNT());
//                                double e = c * d;
//                                int f= (int) e;
//                                money = f;
//                            }else{
//                                //非流转标
//                                money = Integer.parseInt(etLjtzBack.getText().toString());
//                            }
//
//                            if (money>=mini&&i1>=i2){
//                                if (isCheck){
//                                    dialogRedBagAdapter.setFlg("1");
//                                    dialogRedBagAdapter.setI(position);
//                                    i=position;
//                                    dialogRedBagAdapter.notifyDataSetChanged();
//                                    isCheck =!isCheck;
//                                }else{
//                                    i=position;
//                                    dialogRedBagAdapter.setI(position);
//                                    dialogRedBagAdapter.setFlg("0");
//                                    dialogRedBagAdapter.notifyDataSetChanged();
//                                    isCheck =!isCheck;
//                                }
//                            }else{
//                                return;
//                            }
//
//
//                        }
//                    });
//                    tv_queren.setOnClickListener(new View.OnClickListener() {
//                        @Override
//                        public void onClick(View view) {
//                            if (i==-1){
//                                dialog.dismiss();
//                                redId="";
//                                tvQxzhbJine.setVisibility(View.GONE);
//                                tvQxzhb.setText("请选择");
//                            }else{
//                                if (redBag_list.get(i).getCHAI_FEN_FLG().equals("0")){
//                                    //是拆分红包
//                                    String red_amount = redBag_list.get(i).getRED_AMOUNT();
//                                    String amount = red_amount.substring(0,red_amount.length()-1);
//                                    double real_amount = Double.parseDouble(amount);
//                                    Log.e("TAG", "onClick: "+real_amount+"");
//                                    String str  = etLjtzBack.getText().toString();
//                                    String substring = str.substring(0,str.length());
//                                    double money = Double.parseDouble(substring);
//                                    double bili = Double.parseDouble(redBag_list.get(i).getCHAI_FEN_BI_LI());
//                                    double a = (money*bili)/1000;
//                                    int b = (int) a;
//                                    if (real_amount>=a){
//                                        tvQxzhbJine.setText(a+"元");
//                                    }else{
//                                        tvQxzhbJine.setText(real_amount+"元");
//                                    }
//                                    tvQxzhb.setText("已选择");
//                                    tvQxzhbJine.setVisibility(View.VISIBLE);
//                                    redId = redBag_list.get(i).getRED_PACKET_LOG_ID();
//                                    i=-1;
//                                    dialog.dismiss();
//                                }else{
//                                    //不是拆分红包
//                                    String red_amount = redBag_list.get(i).getRED_AMOUNT();
//                                    tvQxzhb.setText("已选择");
//                                    tvQxzhbJine.setText(red_amount);
//                                    tvQxzhbJine.setVisibility(View.VISIBLE);
//                                    redId = redBag_list.get(i).getRED_PACKET_LOG_ID();
//                                    i=-1;
//                                    dialog.dismiss();
//                                }
//
//                            }
//
//                        }
//                    });
//                }
//            }
//
//            @Override
//            protected void OnExecuteFailed(String error) {
//                hideLoadingDialog();
//            }
//        };
//        getList.execute();
//    }


}
