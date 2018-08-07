package com.desjf.dsjr.fragment.IndividualCenter;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.desjf.dsjr.R;
import com.desjf.dsjr.activity.AccountMessageActivity;
import com.desjf.dsjr.activity.AccountMyInComeActivity;
import com.desjf.dsjr.activity.AccountMyInvestActivity;
import com.desjf.dsjr.activity.AccountSettingActivity;
import com.desjf.dsjr.activity.AccountWithDrawActivity;
import com.desjf.dsjr.activity.BankWebViewActivity;
import com.desjf.dsjr.activity.CalenderAllActivity;
import com.desjf.dsjr.activity.ComplainActivity;
import com.desjf.dsjr.activity.HelpCenterActivity;
import com.desjf.dsjr.activity.InviteFriendsWebViewActivity;
import com.desjf.dsjr.activity.LoginActivity;
import com.desjf.dsjr.activity.MainActivity;
import com.desjf.dsjr.activity.NewAccountFundDetailsActivity;
import com.desjf.dsjr.activity.ShouyeMessageActivity;
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
import com.desjf.dsjr.biz.retrofit.CallUtils;
import com.desjf.dsjr.biz.task.BizDataAsyncTask;
import com.desjf.dsjr.config.DsjrConfig;
import com.desjf.dsjr.model.AccountModel;
import com.desjf.dsjr.util.DialogUtil;
import com.desjf.dsjr.utils.BitmapCircleTransformation;
import com.desjf.dsjr.utils.PreferenceCache;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 老账户
 */
public class OldWodeFragment extends BaseFragment {

    @BindView(R.id.tv_wode_phonenum)
    TextView tvWodePhonenum;
    @BindView(R.id.iv_wode_flag)
    ImageView ivWodeFlag;
    @BindView(R.id.tv_wode_total_money)
    TextView tvWodeTotalMoney;
    @BindView(R.id.tv_usable_money)
    TextView tvUsableMoney;

    @BindView(R.id.tv_dongjie_money)
    TextView tvDongjieMoney;

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
    //    @BindView(R.id.ll_wode_charge)
    //    LinearLayout llWodeCharge;
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

//    @BindView(R.id.main_tab_group)
//    RadioGroup mainTabGroup;
    @BindView(R.id.btn_normal_user)
    Button normalUser;//普通账户
    @BindView(R.id.btn_bank_user)
    Button bankUser;//存管账户

    //R.mipmap.zidongtoubiao,
    private int[] icon_list = {R.mipmap.wodetouzi,
            R.mipmap.jiaoyijilu,R.mipmap.tousujianyi, R.mipmap.wodeshouyi, R.mipmap.bangzhuzhongxin};
    private String[] title_list = {"我的投资","资金明细","我的收益", "投诉建议与争议举报", "帮助中心"};
    private boolean isOpen = true;
    protected boolean isVisible;
    private AccountModel account;
    private BroadcastReceiver receiver;//刷新页面广播
    Identity identity;

    private View rootView;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (null == rootView) {
            rootView = inflater.inflate(R.layout.fragment_old_wo_de, null);
            ButterKnife.bind(this, rootView);
            initWodeMessage();
            receiver = new BroadcastReceiver() {
                @Override
                public void onReceive(Context context, Intent intent) {
                    onReceiveBroadcast(intent);
                }
            };
            IntentFilter filter = new IntentFilter();
            filter.addAction("withdraw"); //token过期
            getActivity().registerReceiver(receiver, filter);
            initData();
            initWodeMessage();
        }

        ViewGroup parent = (ViewGroup)rootView.getParent();
        if (parent != null) {
            parent.removeView(rootView);
        }
        return rootView;

//        View view = inflater.inflate(R.layout.fragment_old_wo_de, container, false);
//        ButterKnife.bind(this, view);
////        initWodeMessage();
//        receiver = new BroadcastReceiver() {
//            @Override
//            public void onReceive(Context context, Intent intent) {
//                onReceiveBroadcast(intent);
//            }
//        };
//
//        IntentFilter filter = new IntentFilter();
//        filter.addAction("withdraw"); //token过期
//        getActivity().registerReceiver(receiver, filter);
//
//        initData();
//        initWodeMessage();
//        return view;


    }

    private void onReceiveBroadcast(Intent intent) {
        String action = intent.getAction();
        if (action.equals("withdraw")) {
            initWodeMessage();
        }
    }

    @SuppressLint("NewApi")
    @Override
    public void onResume() {
        super.onResume();
        if (DsjrConfig.GOFRESH == 10) {
            initWodeMessage();
            DsjrConfig.GOFRESH = 0;
        }
        bankUser.setBackground(getResources().getDrawable(R.drawable.shape_corner_right_y));
        bankUser.setTextColor(getResources().getColor(R.color.white));
        normalUser.setBackground(getResources().getDrawable(R.drawable.shape_corner_left_w));
        normalUser.setTextColor(getResources().getColor(R.color.main));
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
        }
    }
    //获取我的数据
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
                BaseApplication baseApplication = (BaseApplication) getActivity().getApplication();
                baseApplication.setAccountModel(accountModel);
                //处理页面
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
        if (accountModel.getUSERHEADIMG().isEmpty()) {
            ivWodeIcon.setImageResource(R.mipmap.wode_icon);
        } else {
            Glide.with(getActivity()).load(accountModel.getUSERHEADIMG()).transform(new BitmapCircleTransformation(getActivity())).error(R.mipmap.wode_icon).into(ivWodeIcon);
        }
        tvWodePhonenum.setText(accountModel.getPHONENUMBERCONCEAL());
        tvWodeTotalMoney.setText(String.valueOf(accountModel.getTOTALAMOUNT()));
        tvUsableMoney.setText(String.valueOf(accountModel.getBALANCE()));
        tvDongjieMoney.setText(String.valueOf(accountModel.getFROZEAMOUNT()));//冻结金额
        tvDaishouMoney.setText(String.valueOf(accountModel.getAWAIT()));
        if (accountModel.getMESSAGEEXISTFLG().equals("0")) {
            //有新消息
            ivWodeMessage.setImageResource(R.mipmap.wodehavemessage);
        } else {
            ivWodeMessage.setImageResource(R.mipmap.wodenomessage);
        }
        //如果没有开通存管则显示 开通存管的提示
        //如果是老用户且未开通存管则进入普通账户页面
        CallUtils.call(getActivity(), BankHttpUtil.getHttpRequestService().getIdentity(accountModel.getPHONENUMBER()), new CallUtils.MyCallListener() {
            @Override
            public void onRespnseSuccess(String jsonString) {
                identity= JSON.parseObject(jsonString,Identity.class);
                if (identity.getMessageType().equals("0002")) {
                    //未认证
                    llRealName.setVisibility(View.VISIBLE);
                } else {
                    llRealName.setVisibility(View.GONE);
                }
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
                    //我的投资
                    intent = new Intent(getActivity(), AccountMyInvestActivity.class);
                }
                //                else if (position == 1) {
                //                    //我的转让
                //                    intent = new Intent(getActivity()(), AccountMyZhuanRActivity.class);
                //                }
                //                else if (position == 1) {
                //                    //红包卡劵
                //                    intent = new Intent(getActivity(), AccountRedBagActivity.class);
                //                }
                //                else if (position == 2) {
                //                    //自动投标
                //                    intent = new Intent(getActivity()(), AccountAutoTenderMainActivity.class);
                //                }
                else if (position == 1) {
                    //资金明细
                    intent = new Intent(getActivity(), NewAccountFundDetailsActivity.class);
                } else if (position == 2) {
                    //我的收益
                    intent = new Intent(getActivity(), AccountMyInComeActivity.class);
                }
                else if (position == 3){
                    //投诉建议
                    intent = new Intent(getActivity(), ComplainActivity.class);
                }
                else{
                    //帮助中心
                    intent = new Intent(getActivity(), HelpCenterActivity.class);
                }
                startActivity(intent);
            }
        });

        srlWode.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                srlWode.setRefreshing(false);
                initWodeMessage();
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
                        tvDongjieMoney.setText("****");
                        ivWodeFlag.setImageResource(R.mipmap.wode_unsea_white);
                        isOpen = !isOpen;
                    } else {
                        tvWodeTotalMoney.setText(String.valueOf(account.getTOTALAMOUNT()));
                        tvUsableMoney.setText(String.valueOf(account.getBALANCE()));
                        tvDaishouMoney.setText(String.valueOf(account.getAWAIT()));
                        tvDongjieMoney.setText(String.valueOf(account.getFROZEAMOUNT()));
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
    @OnClick({R.id.iv_wode_icon, R.id.iv_wode_message, R.id.ll_calendar, R.id.iv_wode_setting, R.id.ll_wode_withdraw,
            R.id.ll_invite_friends, R.id.iv_wode_flag, R.id.ll_real_name,R.id.btn_normal_user,R.id.btn_bank_user})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_normal_user:
                //点击跳转到普通账户信息
                normalUser.setBackground(getResources().getDrawable(R.drawable.shape_corner_left_w));
                normalUser.setTextColor(getResources().getColor(R.color.main));
                bankUser.setBackground(getResources().getDrawable(R.drawable.shape_corner_right_y));
                bankUser.setTextColor(getResources().getColor(R.color.white));

                break;
            case R.id.btn_bank_user:
                //点击跳转到存管账户信息
                bankUser.setBackground(getResources().getDrawable(R.drawable.shape_corner_right_w));
                bankUser.setTextColor(getResources().getColor(R.color.main));
                normalUser.setBackground(getResources().getDrawable(R.drawable.shape_corner_left_y));
                normalUser.setTextColor(getResources().getColor(R.color.white));

//                如果没有开通存管则弹框提示 开通存管，开通了则直接进入存管账户
                if (identity!=null) {
                    if(identity.getMessageType().equals("0002")){
                        DialogUtil.getDialogInstance(getActivity(),"投资需开通存管，是否前往开通？","取消","前往开通")
                                .setOnDefineClickListener(new DialogUtil.onDefineClickListener() {
                                    @Override
                                    public void onLeftBtnClick(View v) {

                                    }
                                    @Override
                                    public void onRightBtnClick(View v) {
                                        //开通存管
                                        getBank();
                                    }
                                });
                    }else {
                        DsjrConfig.WHERE = 1;
                        DsjrConfig.TO_MY = 2;//跳转到存管账户
                        Intent i = new Intent(getActivity(), MainActivity.class);
                        startActivity(i);
                    }
                }
                break;
            case R.id.iv_wode_icon:
                //点击跳转到账户信息
                if (PreferenceCache.getToken().isEmpty()) {
                    Intent intent_ten = new Intent(getActivity(), LoginActivity.class);
                    startActivity(intent_ten);
                } else {
                    Intent intent_real = new Intent(getActivity(), AccountMessageActivity.class);
                    startActivity(intent_real);
                }

                break;
            case R.id.iv_wode_message:
                //消息
                Intent intent = new Intent(getActivity(), ShouyeMessageActivity.class);
                startActivity(intent);
                break;
            //            case R.id.ll_wode_charge:
            //                if (account != null) {
            //                    if (account.getIDCARDVERIFYFLG().toString().equals("0") || account.getBANKFLG().toString().equals("0")) {
            //                        Intent intent_one = new Intent(getActivity(), AccountRealNameActivity.class);
            //                        intent_one.putExtra("RealName", 1);
            //                        intent_one.putExtra("UserName", account.getUSERNAME());
            //                        intent_one.putExtra("UserID", account.getIDCARD());
            //                        startActivity(intent_one);
            //                        ToastUtils.showTost(getActivity(), "请先验证四要素");
            //                    } else {
            //                        Intent intent_two = new Intent(getActivity(), AccountChargeActivity.class);
            //                        startActivity(intent_two);
            //                    }
            //                }
            //
            //                break;
            case R.id.ll_calendar:
                //跳转到日历
                Intent intent_three = new Intent(getActivity(), CalenderAllActivity.class);
                startActivity(intent_three);
                break;
            case R.id.iv_wode_setting:
                //设置
                Intent intent_four = new Intent(getActivity(), AccountSettingActivity.class);
                startActivity(intent_four);
                break;
            case R.id.ll_wode_withdraw:

//                if (account != null) {
//                    if (account.getIDCARDVERIFYFLG().toString().equals("0") || account.getBANKFLG().toString().equals("0")) {
//                        Intent intent_five = new Intent(getActivity(), AccountRealNameActivity.class);
//                        intent_five.putExtra("RealName", 1);
//                        intent_five.putExtra("UserName", account.getUSERNAME());
//                        intent_five.putExtra("UserID", account.getIDCARD());
//                        startActivity(intent_five);
//                        ToastUtils.showTost(getActivity(), "请先验证四要素");
//                    } else {
                        Intent intent_six = new Intent(getActivity(), AccountWithDrawActivity.class);
                        startActivity(intent_six);
//                    }
//                }
                break;
            case R.id.ll_invite_friends:
                //邀请好友
                Intent intent_seven = new Intent(getActivity(), InviteFriendsWebViewActivity.class);
                //                Intent intent_seven = new Intent(   getActivity()(), ShareActivity.class);

                startActivity(intent_seven);
                break;
            case R.id.ll_real_name:
                //前往开通存管
                getBank();

                //                if (account != null) {
                //                    //实名认证
                //                    if (account.getIDCARDVERIFYFLG().toString().equals("0") || account.getBANKFLG().toString().equals("0")) {
                //                        Intent intent_eight = new Intent(getActivity(), AccountRealNameActivity.class);
                //                        intent_eight.putExtra("RealName", 1);
                //                        intent_eight.putExtra("UserName", account.getUSERNAME());
                //                        intent_eight.putExtra("UserID", account.getIDCARD());
                //                        startActivity(intent_eight);
                //                        ToastUtils.showTost(getActivity(), "请先验证四要素");
                //                    }
                //                }
                break;
//            case R.id.btn_shouye:
//                Intent in=new Intent(getActivity(),MainActivity.class);
//                DsjrConfig.WHERE = 2;
//                startActivity(in);
//                break;
//            case R.id.btn_invest:
//                Intent inten=new Intent(getActivity(),MainActivity.class);
//                DsjrConfig.WHERE = 3;
//                startActivity(inten);
//                break;
        }
    }

    private void getBank(){

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
    }

}
