package com.desjf.dsjr.ui.fragment;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.desjf.dsjr.R;
import com.desjf.dsjr.base.BaseConfig;
import com.desjf.dsjr.base.BaseFragment;

import butterknife.BindView;
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

    private boolean isOpen = true;
    protected boolean isVisible;

    private BroadcastReceiver receiver;//刷新页面广播

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_old_wo_de;
    }

    @Override
    protected void init(Bundle savedInstanceState) {

        IntentFilter filter = new IntentFilter();
        filter.addAction("withdraw"); //token过期
        getActivity().registerReceiver(receiver, filter);

        initData();
        initWodeMessage();
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
        if (BaseConfig.GOFRESH == 10) {
            initWodeMessage();
            BaseConfig.GOFRESH = 0;
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

    }

    private void initView() {

    }

    //处理RecyclerView
    private void initData() {

        srlWode.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                initWodeMessage();
            }
        });
        srlWode.setColorSchemeResources(R.color.main);


        ivWodeFlag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //密码显隐开关
//                if (account != null) {
//                    if (isOpen) {
//                        tvWodeTotalMoney.setText("****");
//                        tvUsableMoney.setText("****");
//                        tvDaishouMoney.setText("****");
//                        ivWodeFlag.setImageResource(R.mipmap.wode_unsea_white);
//                        isOpen = !isOpen;
//                    } else {
//                        tvWodeTotalMoney.setText(String.valueOf(account.getTOTALAMOUNT()));
//                        tvUsableMoney.setText(String.valueOf(account.getBALANCE()));
//                        tvDaishouMoney.setText(String.valueOf(account.getAWAIT()));
//                        ivWodeFlag.setImageResource(R.mipmap.sea_white);
//                        isOpen = !isOpen;
//
//                    }
//                } else {
//                    return;
//                }
            }
        });

    }

    @SuppressLint("NewApi")
    @OnClick({R.id.iv_wode_icon, R.id.iv_wode_message, R.id.ll_calendar, R.id.iv_wode_setting, R.id.ll_wode_withdraw,
            R.id.ll_invite_friends, R.id.iv_wode_flag, R.id.ll_real_name, R.id.btn_normal_user, R.id.btn_bank_user})
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

//
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }


}
