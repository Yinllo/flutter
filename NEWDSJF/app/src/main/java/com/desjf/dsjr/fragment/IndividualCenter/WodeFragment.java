package com.desjf.dsjr.fragment.IndividualCenter;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.desjf.dsjr.R;
import com.desjf.dsjr.activity.AccountRedBagActivity;
import com.desjf.dsjr.activity.AccountSettingActivity;
import com.desjf.dsjr.activity.BankWebViewActivity;
import com.desjf.dsjr.activity.ComplainActivity;
import com.desjf.dsjr.activity.HelpCenterActivity;
import com.desjf.dsjr.activity.InviteFenRunActivity;
import com.desjf.dsjr.activity.InviteFriendsWebViewActivity;
import com.desjf.dsjr.activity.LoginActivity;
import com.desjf.dsjr.activity.MainActivity;
import com.desjf.dsjr.activity.ShouyeMessageActivity;
import com.desjf.dsjr.activity.bankActivity.BankAccountFundDetailsActivity;
import com.desjf.dsjr.activity.bankActivity.BankAccountMessageActivity;
import com.desjf.dsjr.activity.bankActivity.BankAccountMyInvestActivity;
import com.desjf.dsjr.activity.bankActivity.BankAccountWithDrawActivity;
import com.desjf.dsjr.activity.bankActivity.RechargeActivity;
import com.desjf.dsjr.adapter.WodeAdapter;
import com.desjf.dsjr.base.BaseApplication;
import com.desjf.dsjr.base.BaseFragment;
import com.desjf.dsjr.bean.Identity;
import com.desjf.dsjr.bean.NewRegBean;
import com.desjf.dsjr.bean.WodeRvBean;
import com.desjf.dsjr.biz.AccountBiz;
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
import com.desjf.dsjr.model.bankModel.BankDaiShouModel;
import com.desjf.dsjr.model.bankModel.BankUserBalanceModel;
import com.desjf.dsjr.util.DialogUtil;
import com.desjf.dsjr.util.MyDialogUtil;
import com.desjf.dsjr.utils.BitmapCircleTransformation;
import com.desjf.dsjr.utils.DataUtil;
import com.desjf.dsjr.utils.PreferenceCache;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;

/**
 * A simple {@link Fragment} subclass.
 */
public class WodeFragment extends BaseFragment {

    private View rootView;

    @BindView(R.id.tv_wode_phonenum)
    TextView tvWodePhonenum;
    @BindView(R.id.iv_wode_flag)
    ImageView ivWodeFlag;
    @BindView(R.id.tv_wode_total_money)
    TextView tvWodeTotalMoney;
    @BindView(R.id.tv_usable_money)
    TextView tvUsableMoney;

//    @BindView(R.id.tv_dongjie_money)
//    TextView tvDongjieMoney;

    @BindView(R.id.tv_daishou_money)
    TextView tvDaishouMoney;
    @BindView(R.id.ll_real_name)
    LinearLayout llRealName;
    @BindView(R.id.srl_wode)
    SwipeRefreshLayout srlWode;
    private WodeAdapter wodeAdapter;
    private List<WodeRvBean> rv_list;
    @BindView(R.id.iv_wode_icon)
    ImageView ivWodeIcon;
    @BindView(R.id.iv_wode_message)
    ImageView ivWodeMessage;
    @BindView(R.id.ll_wode_charge)
    LinearLayout llWodeCharge;
    @BindView(R.id.ll_calendar)
    LinearLayout llCalendar;
    @BindView(R.id.iv_wode_setting)
    ImageView ivWodeSetting;
    @BindView(R.id.ll_wode_withdraw)
    LinearLayout llWodeWithdraw;
    @BindView(R.id.ll_invite_friends)
    LinearLayout llInviteFriends;
    @BindView(R.id.rv_wode)
    RecyclerView rvWode;

    @BindView(R.id.rl_account_btn)
    RelativeLayout accountBtn;
    @BindView(R.id.btn_normal_user)
    Button normalUser;//普通账户
    @BindView(R.id.btn_bank_user)
    Button bankUser;//存管账户
    List<BankUserBalanceModel.UsermessageBean> list=null;

    //R.mipmap.zidongtoubiao,
    private int[] icon_list = {R.mipmap.hongbaokajuan,
            R.mipmap.jiaoyijilu,R.mipmap.mineinvite,R.mipmap.bangzhuzhongxin,R.mipmap.qqkefu,R.mipmap.phonekefu,R.mipmap.tousujianyi};
    private String[] title_list = {"红包卡券","资金明细","邀请分润","帮助中心", "QQ 客服", "电话客服","投诉建议与争议举报"};
    private boolean isOpen = true;
    protected boolean isVisible;
    private AccountModel account;
    private BroadcastReceiver receiver;//刷新页面广播
    Identity identity=null;
//    String dongjieMoney="";
    String totalMoney="";
    String keyongMoney="";
    String daishouMoney="";
    double keyong;
    double daishou;
    int ifBank=-1;//判断是否开通存管

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (null == rootView) {
            rootView = inflater.inflate(R.layout.fragment_wode, null);
            ButterKnife.bind(this, rootView);
            receiver = new BroadcastReceiver() {
                @Override
                public void onReceive(Context context, Intent intent) {
                    onReceiveBroadcast(intent);
                }
            };
            IntentFilter filter = new IntentFilter();
            filter.addAction("withdraw"); //token过期
            getActivity().registerReceiver(receiver, filter);
            initWodeMessage();
            initData();
            getBalance();
//            getUserInfo();
        }

        ViewGroup parent = (ViewGroup)rootView.getParent();
        if (parent != null) {
            parent.removeView(rootView);
        }
        return rootView;

    }

    private void onReceiveBroadcast(Intent intent) {
        String action = intent.getAction();
        if (action.equals("withdraw")) {
            initWodeMessage();
            getBalance();
            //            getUserInfo();
        }
    }

    @SuppressLint("NewApi")
    @Override
    public void onResume() {
        super.onResume();
        if (DsjrConfig.GOFRESH == 10) {
            initWodeMessage();
            getBalance();
//            getUserInfo();
            DsjrConfig.GOFRESH = 0;
        }
        bankUser.setBackground(getResources().getDrawable(R.drawable.shape_corner_right_w));
        bankUser.setTextColor(getResources().getColor(R.color.main));
        normalUser.setBackground(getResources().getDrawable(R.drawable.shape_corner_left_y));
        normalUser.setTextColor(getResources().getColor(R.color.white));
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        getActivity().unregisterReceiver(receiver);
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (hidden) {
            return;
        } else {
            initWodeMessage();
            getBalance();
//            getUserInfo();
        }
    }

    //获得当前账户余额
    private void getBalance(){
        //初始化个人账户信息
//        dongjieMoney="0.00";
        totalMoney="0.00";
        keyongMoney="0.00";
        daishouMoney="0.00";
        keyong=0;
        tvWodeTotalMoney.setText("0.00");
//        tvDongjieMoney.setText("0.00");
        tvUsableMoney.setText("0.00");
        tvDaishouMoney.setText("0.00");

        //总额 和可用余额
        CallUtil.call(getActivity(), BankHttpUtils.getHttpRequestService().QueryBalance(PreferenceCache.getToken()), new CallUtil.MyCallListener() {
            @Override
            public void onRespnseSuccess(String jsonString) {
                BankUserBalanceModel bankUserBalanceModel=JSON.parseObject(jsonString,BankUserBalanceModel.class);
                if(bankUserBalanceModel.getUsermessage()!=null) {
                    list = bankUserBalanceModel.getUsermessage();
                    initBalance(list);
                }
            }

            @Override
            public void onRespnseFailure(Call<String> call, Throwable t) {
            }
        });

    }


    private void initBalance(List<BankUserBalanceModel.UsermessageBean> list){
        for(int i=0;i<list.size();i++){
            if(list.get(i).getCap_typ().equals("1")){
                //总额
//                if(String.valueOf(list.get(i).getAcctBal()).equals("")||String.valueOf(list.get(i).getAcctBal()).equals("0")) {
//                    tvWodeTotalMoney.setText("0.00");
//                }else{
//                    tvWodeTotalMoney.setText(DataUtil.toMoney(Float.parseFloat(list.get(i).getAcctBal())));
//                    totalMoney=DataUtil.toMoney(Float.parseFloat(list.get(i).getAcctBal()));
//                }
                if(String.valueOf(list.get(i).getAvlBal()).equals("")||String.valueOf(list.get(i).getAvlBal()).equals("0")) {
                    tvUsableMoney.setText("0.00");
                    keyong=0;
                }else{
                    //可用余额
                    tvUsableMoney.setText(DataUtil.toMoney(Float.parseFloat(list.get(i).getAvlBal())));
                    keyongMoney=DataUtil.toMoney(Float.parseFloat(list.get(i).getAvlBal()));
                    keyong=Float.parseFloat(list.get(i).getAvlBal());
                }

//                if(String.valueOf(list.get(i).getFrzBal()).equals("")||String.valueOf(list.get(i).getFrzBal()).equals("0")) {
//                    tvDongjieMoney.setText("0.00");
//                }else{
//                    tvDongjieMoney.setText(DataUtil.toMoney(Float.parseFloat(list.get(i).getFrzBal())));
//                    dongjieMoney=DataUtil.toMoney(Float.parseFloat(list.get(i).getFrzBal()));
//                }
            }
        }


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
                BaseApplication baseApplication = (BaseApplication) getActivity().getApplication();
                baseApplication.setAccountModel(accountModel);
                //处理页面
                getDaiShou(accountModel.getPHONENUMBER());
                initView(accountModel);
                srlWode.setRefreshing(false);
            }

            @Override
            protected void OnExecuteFailed(String error) {
                hideLoadingDialog();
//                ToastUtils.showTost(context,error.toString());
                srlWode.setRefreshing(false);
            }
        };
        shouyeMessage.execute();
    }

    private void initView(final AccountModel accountModel) {
        account = accountModel;
        StringBuilder s=new StringBuilder(account.getPHONENUMBER());
        s.replace(3,7,"****");
        tvWodePhonenum.setText(s);
        if (accountModel.getUSERHEADIMG().isEmpty()) {
            ivWodeIcon.setImageResource(R.mipmap.wode_icon);
        } else {
            Glide.with(getActivity()).load(accountModel.getUSERHEADIMG()).transform(new BitmapCircleTransformation(getActivity())).error(R.mipmap.wode_icon).into(ivWodeIcon);
        }

        if (accountModel.getMESSAGEEXISTFLG().equals("0")) {
            //有新消息
            ivWodeMessage.setImageResource(R.mipmap.wodehavemessage);
        } else {
            ivWodeMessage.setImageResource(R.mipmap.wodenomessage);
        }

        //如果是老用户且未开通存管则进入普通账户页面
        CallUtils.call(getActivity(), BankHttpUtil.getHttpRequestService().getIdentity(account.getPHONENUMBER()), new CallUtils.MyCallListener() {
            @Override
            public void onRespnseSuccess(String jsonString) {
                identity= JSON.parseObject(jsonString,Identity.class);
                /**
                 * 判断当前是新用户还是老用户，据此来判断是否显示新、旧账户切换按钮
                 * 0001 请登录   0002 老用户未实  0004  新用户未实名  0005  新用户已实名
                 0003  老用户已实名
                 */
                if(identity.getMessageType().equals("0004")||identity.getMessageType().equals("0005")){
                    //如果是新用户 则隐藏
                    accountBtn.setVisibility(View.GONE);
                }else{
                    accountBtn.setVisibility(View.VISIBLE);
                }
                if (identity.getMessageType().equals("0002")||identity.getMessageType().equals("0004")) {
                    //未认证
                    llRealName.setVisibility(View.VISIBLE);
                    //充值、提现不能点击
                    ifBank=0;
                } else {
                    llRealName.setVisibility(View.GONE);
                    ifBank=1;
                }
            }
        });

    }

    private void getDaiShou(String phone){
        //待收金额
        CallUtils.call(getActivity(), InitHttpUtil.getHttpRequestService().selectAccount(phone), new CallUtils.MyCallListener() {
            @Override
            public void onRespnseSuccess(String jsonString) {
                BankDaiShouModel bankDaiShouModel=JSON.parseObject(jsonString,BankDaiShouModel.class);
                if(bankDaiShouModel.getUserDueIn().equals("")||bankDaiShouModel.getUserDueIn().equals("0")){
                    tvDaishouMoney.setText("0.00");
                    daishou=0;
                }else{
                    tvDaishouMoney.setText(DataUtil.toMoney(Float.parseFloat(bankDaiShouModel.getUserDueIn())));
                    daishouMoney=DataUtil.toMoney(Float.parseFloat(bankDaiShouModel.getUserDueIn()));
                    daishou=Float.parseFloat(bankDaiShouModel.getUserDueIn());
                }

                double total=keyong+daishou;
                BigDecimal t=new BigDecimal(total);
                if(total==0){
                    totalMoney="0.00";
                }else{
                    totalMoney=DataUtil.toMoneys(t);
                }
                tvWodeTotalMoney.setText(totalMoney);
            }
        });
    }

    //处理RecyclerView
    private void initData() {
        rv_list = new ArrayList<>();
        rv_list.clear();
        for (int i = 0; i < icon_list.length; i++) {
            WodeRvBean wrb = new WodeRvBean();
            wrb.setIcon(icon_list[i]);
            wrb.setTitle(title_list[i]);
            rv_list.add(wrb);
        }
        wodeAdapter = new WodeAdapter(rv_list, getActivity());
        rvWode.setAdapter(wodeAdapter);
        rvWode.setLayoutManager(new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL));
        wodeAdapter.notifyDataSetChanged();
        //点击监听
        rvWode.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent = null;
                if (position == 0) {
                    //红包卡劵
                    intent = new Intent(getActivity(), AccountRedBagActivity.class);
                    startActivity(intent);
                }
                else if (position ==1) {
                    //资金明细
                    intent = new Intent(getActivity(), BankAccountFundDetailsActivity.class);
                    startActivity(intent);
                }
                else if(position == 2){
                    //邀请分润
                    intent = new Intent(getActivity(), InviteFenRunActivity.class);
                    startActivity(intent);
                }
                else if(position == 3){
                    //帮助中心
                    intent = new Intent(getActivity(), HelpCenterActivity.class);
                    startActivity(intent);
                }
                else if(position == 4){
                    //进入qq
                    DialogUtil.getDialogInstance(getActivity(),"点击确定将复制QQ客服号码，进入QQ，您于搜索框粘贴号码搜索，即可找到客服QQ","取消","确定")
                            .setOnDefineClickListener(new DialogUtil.onDefineClickListener() {
                                @Override
                                public void onLeftBtnClick(View v) {

                                }
                                @Override
                                public void onRightBtnClick(View v) {
                                    //自动打开QQ 进入与客服聊天界面
                                    //800186146
//                                    String url="mqqwpa://im/chat?chat_type=wpa&uin=491558689";
//                                    String url="http://wpa.qq.com/msgrd?v=3&uin=2470636263&site=qq&menu=yes";
//                                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url)));

                                    ClipboardManager copy = (ClipboardManager) getActivity()
                                            .getSystemService(Context.CLIPBOARD_SERVICE);
                                    copy.setText("800186146");

                                    try {
                                        Intent intent = getActivity().getPackageManager().getLaunchIntentForPackage("com.tencent.mobileqq");
                                        startActivity(intent);
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                        MyDialogUtil.showSimpleDialog(getActivity(),"请检查是否安装QQ","知道了");
                                    }
                                }
                            });
                }
                else if(position == 5){
                    //进入拨打电话界面
                    DialogUtil.getDialogInstance(getActivity(),"呼叫客服 400-606-3099","取消","确定")
                            .setOnDefineClickListener(new DialogUtil.onDefineClickListener() {
                                @Override
                                public void onLeftBtnClick(View v) {

                                }
                                @Override
                                public void onRightBtnClick(View v) {
                                    //跳转到拨号界面
                                    Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + "4006063099"));
                                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                    startActivity(intent);
                                }
                            });
                }
                else if (position == 6){
                    //投诉建议
                    intent = new Intent(getActivity(), ComplainActivity.class);
                    startActivity(intent);
                }
            }
        });

        srlWode.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                srlWode.setRefreshing(false);
                initWodeMessage();
                getBalance();
//                getUserInfo();
            }
        });
        srlWode.setColorSchemeResources(R.color.main);

        ivWodeFlag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //密码显隐开关
                if (account != null) {
                    if (isOpen) {
                        tvWodeTotalMoney.setText("****");
                        tvUsableMoney.setText("****");
                        tvDaishouMoney.setText("****");
//                        tvDongjieMoney.setText("****");
                        ivWodeFlag.setImageResource(R.mipmap.wode_unsea_white);
                        isOpen = !isOpen;
                    } else {
                        tvWodeTotalMoney.setText(String.valueOf(totalMoney));
                        tvUsableMoney.setText(String.valueOf(keyongMoney));
                        tvDaishouMoney.setText(daishouMoney);
//                        tvDongjieMoney.setText(dongjieMoney);
                        ivWodeFlag.setImageResource(R.mipmap.sea_white);
                        isOpen = !isOpen;

                    }
                 } else {
                    return;
                }
            }
        });
    }

    @SuppressLint("NewApi")
    @OnClick({R.id.iv_wode_icon, R.id.iv_wode_message, R.id.ll_wode_charge, R.id.ll_calendar, R.id.iv_wode_setting, R.id.ll_wode_withdraw,
            R.id.ll_invite_friends, R.id.iv_wode_flag, R.id.ll_real_name,R.id.btn_normal_user,R.id.btn_bank_user})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_normal_user:
                //点击跳转到普通账户信息
                normalUser.setBackground(getResources().getDrawable(R.drawable.shape_corner_left_w));
                normalUser.setTextColor(getResources().getColor(R.color.main));
                bankUser.setBackground(getResources().getDrawable(R.drawable.shape_corner_right_y));
                bankUser.setTextColor(getResources().getColor(R.color.white));

                DsjrConfig.WHERE = 1;
                DsjrConfig.TO_MY = 1;//跳转到普通账户
                Intent i=new Intent(getActivity(), MainActivity.class);
                startActivity(i);
                break;

            case R.id.btn_bank_user:
                //点击跳转到存管账户信息
                bankUser.setBackground(getResources().getDrawable(R.drawable.shape_corner_right_w));
                bankUser.setTextColor(getResources().getColor(R.color.main));
                normalUser.setBackground(getResources().getDrawable(R.drawable.shape_corner_left_y));
                normalUser.setTextColor(getResources().getColor(R.color.white));
//                DsjrConfig.WHERE = 1;
                break;
            case R.id.iv_wode_icon:
                //点击跳转到账户信息
                if (PreferenceCache.getToken().isEmpty()) {
                    Intent intent_ten = new Intent(getActivity(), LoginActivity.class);
                    startActivity(intent_ten);
                } else {
                    Intent intent_real = new Intent(getActivity(), BankAccountMessageActivity.class);
                    startActivity(intent_real);
                }
                break;
            case R.id.iv_wode_message:
                //消息
                Intent intent = new Intent(getActivity(), ShouyeMessageActivity.class);
                startActivity(intent);
                break;
            case R.id.ll_wode_charge:
                //判断是否开通了存管
                if (ifBank==0) {
                    MyDialogUtil.showSimpleDialog(getActivity(),"请您先开通存管","知道了");
                    return;
                }
                 else {
                        Intent intent_two = new Intent(getActivity(), RechargeActivity.class);
                        //传入可用余额
                        intent_two.putExtra("money",keyongMoney);
                        startActivity(intent_two);
                    }
                break;
            case R.id.ll_calendar:
                //跳转到投资管理
                Intent intent_three =new Intent(getActivity(), BankAccountMyInvestActivity.class);;
                startActivity(intent_three);
                break;
            case R.id.iv_wode_setting:
                //设置
                Intent intent_four = new Intent(getActivity(), AccountSettingActivity.class);
                startActivity(intent_four);
                break;
            case R.id.ll_wode_withdraw:
                   //判断是否开通了存管
                    if (ifBank==0) {
                        MyDialogUtil.showSimpleDialog(getActivity(),"请您先开通存管","知道了");
                        return;
                    } else {
                        Intent intent_six = new Intent(getActivity(), BankAccountWithDrawActivity.class);
                        startActivity(intent_six);
                    }

                break;
            case R.id.ll_invite_friends:
                //邀请好友
                Intent intent_seven = new Intent(getActivity(), InviteFriendsWebViewActivity.class);
//                intent_seven.putExtra("webUrl","https://www.dsp2p.com/m/activityfive.html");
                startActivity(intent_seven);
                break;
            case R.id.ll_real_name:
                //前往开通存管
                CallUtils.call(getActivity(), BankHttpUtil.getHttpRequestService().bankReg(account.getPHONENUMBER()), new CallUtils.MyCallListener() {
                    @Override
                    public void onRespnseSuccess(String jsonString) {
                        NewRegBean newRegBean= JSON.parseObject(jsonString,NewRegBean.class);
                        Intent i=new Intent(getActivity(),BankWebViewActivity.class);
                        i.putExtra("type",1);
                        i.putExtra("newRegBean",newRegBean);
                        startActivity(i);

                    }
                });
                break;
        }

    }
}
