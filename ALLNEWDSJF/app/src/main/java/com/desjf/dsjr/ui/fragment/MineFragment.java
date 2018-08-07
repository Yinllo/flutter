package com.desjf.dsjr.ui.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.desjf.dsjr.R;
import com.desjf.dsjr.base.BaseFragment;
import com.desjf.dsjr.ui.activity.AccountFundDetailsActivity;
import com.desjf.dsjr.ui.activity.InvestmentReturnActivity;
import com.desjf.dsjr.ui.activity.RechargeActivity;
import com.desjf.dsjr.ui.activity.SecuritySettingsActivity;
import com.desjf.dsjr.ui.activity.WithdrawActivity;
import com.desjf.dsjr.utils.DialogUtil;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @Author YinL
 * @Date 2018/1/15 0015
 * @Describe 个人中心Fragment
 */

public class MineFragment extends BaseFragment{

    @BindView(R.id.iv_money_visible)
    ImageView ivWodeFlag;
    @BindView(R.id.rl_mine)
    RelativeLayout rlMine;//点击头像个人信息进入安全设置


    @BindView(R.id.srl_mine)
    SwipeRefreshLayout srlMine;
    private Context context = getActivity();
    @BindView(R.id.iv_mine)
    ImageView ivWodeIcon;
    @BindView(R.id.iv_message)
    ImageView ivWodeMessage;

    private Activity mActivity;
    //当前金额是否可见
    private boolean isOpen = true;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_mine;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
          initData();
    }


    //处理RecyclerView
    private void initData() {
        srlMine.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
//                initWodeMessage();
            }
        });
        srlMine.setColorSchemeResources(R.color.main);

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

    //点击事件
    @OnClick({R.id.tv_prize_btn,R.id.tv_investment_btn,R.id.tv_invite,R.id.tv_fund
              ,R.id.tv_complaint,R.id.tv_call,R.id.tv_qq,R.id.rl_mine
              ,R.id.tv_withdraw,R.id.tv_recharge})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rl_mine:
                //点击跳转到安全设置
                Intent securitySettings = new Intent(getActivity(), SecuritySettingsActivity.class);
                startActivity(securitySettings);
                break;
            case R.id.tv_withdraw:
                //提现
                Intent withdraw = new Intent(getActivity(), WithdrawActivity.class);
                startActivity(withdraw);
                break;
            case R.id.tv_recharge:
                //充值
                Intent recharge = new Intent(getActivity(), RechargeActivity.class);
                startActivity(recharge);
                break;
            case R.id.tv_prize_btn:
                //点击跳转到红包卡券
                break;
            case R.id.tv_investment_btn:
                //点击跳转到投资管理
                Intent investmentReturn = new Intent(getActivity(), InvestmentReturnActivity.class);
                startActivity(investmentReturn);
                break;
            case R.id.tv_invite:
                //邀请有礼
                break;
            case R.id.tv_fund:
                //资金流水
                Intent accountFundDetails = new Intent(getActivity(), AccountFundDetailsActivity.class);
                startActivity(accountFundDetails);
                break;
            case R.id.tv_complaint:
                //投诉建议
                break;
            case R.id.tv_call:
                //电话客服
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
                break;
            case R.id.tv_qq:
                //QQ客服
                DialogUtil.getDialogInstance(getActivity(),"准备启动QQ联系客服","取消","确定")
                        .setOnDefineClickListener(new DialogUtil.onDefineClickListener() {
                    @Override
                    public void onLeftBtnClick(View v) {

                    }
                    @Override
                    public void onRightBtnClick(View v) {
                        //自动打开QQ 进入与客服聊天界面
                        String url="mqqwpa://im/chat?chat_type=wpa&uin=800186146";
                        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url)));
                    }
                });
                break;
        }


    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.mActivity = (Activity)context;
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if(hidden){
            return;
        }else{
            //重新加载数据
        }
    }

}
