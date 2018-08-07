package com.desjf.dsjr.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.desjf.dsjr.R;
import com.desjf.dsjr.base.BaseActivity;
import com.desjf.dsjr.biz.MessageBiz;
import com.desjf.dsjr.biz.exception.BizFailure;
import com.desjf.dsjr.biz.exception.ZYException;
import com.desjf.dsjr.biz.task.BizDataAsyncTask;
import com.desjf.dsjr.model.NewsCenterModel;
import com.desjf.dsjr.utils.ToastUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ShouyeMessageActivity extends BaseActivity {
    @BindView(R.id.ll_about_us_info)
    LinearLayout llAboutUsInfo;
    private Context mContext = this;
    @BindView(R.id.ll_about_us_message)
    LinearLayout llAboutUsMessage;
    @BindView(R.id.tv_project_title)
    TextView tvProjectTitle;
    @BindView(R.id.iv_aboutus_back)
    ImageView ivAboutusBack;
    @BindView(R.id.iv_gonggao_redpoint)
    ImageView ivGonggaoRedpoint;
    @BindView(R.id.tv_about_us_gonggao)
    TextView tvAboutUsGonggao;
    @BindView(R.id.tv_about_us_gonggao_time)
    TextView tvAboutUsGonggaoTime;
    @BindView(R.id.iv_aboutus_message_point)
    ImageView ivAboutusMessagePoint;
    @BindView(R.id.tv_about_us_message)
    TextView tvAboutUsMessage;
    @BindView(R.id.tv_about_us_message_time)
    TextView tvAboutUsMessageTime;
    public static ShouyeMessageActivity mInstance;
    BroadcastReceiver receivers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shouye_message);
        ButterKnife.bind(this);
        mInstance = this;
        //取消息和公告
        initData();
    }

    private void initData() {
        showLoadingDialog();
       BizDataAsyncTask<NewsCenterModel> getNews = new BizDataAsyncTask<NewsCenterModel>() {
           @Override
           protected NewsCenterModel doExecute() throws ZYException, BizFailure {
               return MessageBiz.getNewsCenter();
           }

           @Override
           protected void onExecuteSucceeded(NewsCenterModel newsCenterModel) {
                hideLoadingDialog();
               //处理消息中心
               initMessage(newsCenterModel);
           }

           @Override
           protected void OnExecuteFailed(String error) {
                hideLoadingDialog();
               ToastUtils.showTost(mContext,error.toString());
           }
       };
       getNews.execute();
    }

    //处理消息
    private void initMessage(NewsCenterModel newsCenterModel) {
        if (newsCenterModel!=null) {
            if (newsCenterModel.getOPEN_FLG().toString().equals("0")) {
                ivAboutusMessagePoint.setVisibility(View.VISIBLE);
            } else {
                ivAboutusMessagePoint.setVisibility(View.GONE);
            }
            tvAboutUsMessage.setText(newsCenterModel.getMESSAGE_TITLE());
            tvAboutUsMessageTime.setText(newsCenterModel.getMESSAGE_INS_DATE());
            tvAboutUsGonggao.setText(newsCenterModel.getNEWS_TITLE());
            tvAboutUsGonggaoTime.setText(newsCenterModel.getPUBLISH_DATE());
        }


    }

    @OnClick({R.id.ll_about_us_message, R.id.iv_aboutus_back,R.id.ll_about_us_info})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_about_us_message:
                //消息
                Intent intent = new Intent(ShouyeMessageActivity.this, AccountMyMessageActivity.class);
                startActivity(intent);
                break;
            case R.id.iv_aboutus_back:
                finish();
                break;
            case R.id.ll_about_us_info:
                //公告
                Intent notice_intent = new Intent(ShouyeMessageActivity.this,AccountMyNoticeActivity.class);
                startActivity(notice_intent);
                break;
        }

    }
}
