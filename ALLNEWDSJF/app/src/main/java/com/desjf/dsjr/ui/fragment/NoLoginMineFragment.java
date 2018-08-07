package com.desjf.dsjr.ui.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;

import com.desjf.dsjr.R;
import com.desjf.dsjr.base.BaseFragment;
import com.desjf.dsjr.ui.activity.AccountFundDetailsActivity;
import com.desjf.dsjr.ui.activity.InvestmentReturnActivity;
import com.desjf.dsjr.ui.activity.LoginActivity;
import com.desjf.dsjr.utils.DialogUtil;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @Author YinL
 * @Date 2018/4/26
 * @Describe 未登录时 个人中心Fragment
 */

public class NoLoginMineFragment extends BaseFragment{

    @BindView(R.id.srl_mine)
    SwipeRefreshLayout srlMine;
    private Activity mActivity;

    //当前金额是否可见
    private boolean isOpen = true;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_no_login_mine;
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

    }

    //点击事件
    @OnClick({R.id.tv_prize_btn,R.id.tv_investment_btn,R.id.tv_invite,R.id.tv_fund
              ,R.id.tv_complaint,R.id.tv_call,R.id.tv_qq,R.id.tv_login})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_login:
                Intent toLogin=new Intent(getActivity(),LoginActivity.class);
                startActivity(toLogin);
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

}
