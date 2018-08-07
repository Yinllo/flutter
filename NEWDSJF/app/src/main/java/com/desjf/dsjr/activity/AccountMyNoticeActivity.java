package com.desjf.dsjr.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.desjf.dsjr.R;
import com.desjf.dsjr.adapter.NoticeAdapter;
import com.desjf.dsjr.base.BaseActivity;
import com.desjf.dsjr.base.BaseApplication;
import com.desjf.dsjr.biz.NoticeBiz;
import com.desjf.dsjr.biz.exception.BizFailure;
import com.desjf.dsjr.biz.exception.ZYException;
import com.desjf.dsjr.biz.task.BizDataAsyncTask;
import com.desjf.dsjr.config.DsjrConfig;
import com.desjf.dsjr.model.NoticeModel;
import com.desjf.dsjr.utils.ToastUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AccountMyNoticeActivity extends BaseActivity {
    private NoticeAdapter noticeAdapter;
    @BindView(R.id.iv_notice_back)
    ImageView ivNoticeBack;
    @BindView(R.id.rv_notice)
    RecyclerView rvNotice;
    private Context context = this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_my_notice);
        ButterKnife.bind(this);
        initNotice();
    }

    private void initNotice() {
        showLoadingDialog();
        BizDataAsyncTask<List<NoticeModel>> getNotice = new BizDataAsyncTask<List<NoticeModel>>() {
            @Override
            protected List<NoticeModel> doExecute() throws ZYException, BizFailure {
                return NoticeBiz.getNoticeInfo("","0","20");
            }

            @Override
            protected void onExecuteSucceeded(List<NoticeModel> noticeModels) {
                hideLoadingDialog();
                initData(noticeModels);
            }

            @Override
            protected void OnExecuteFailed(String error) {
                hideLoadingDialog();
                ToastUtils.showTost(context,error.toString());
            }
        };
        getNotice.execute();
    }

    private void initData(final List<NoticeModel> noticeModels) {
        noticeAdapter = new NoticeAdapter(noticeModels,this);
        rvNotice.setAdapter(noticeAdapter);
        rvNotice.setLayoutManager(new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL));
        noticeAdapter.notifyDataSetChanged();
        //对应item的点击监听
        rvNotice.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent = new Intent(AccountMyNoticeActivity.this,WebViewActivity.class);
                intent.putExtra("1", DsjrConfig.NOTICE_URL+noticeModels.get(position).getID());
                intent.putExtra("web",1);
                startActivity(intent);
            }
        });
    }

    @OnClick(R.id.iv_notice_back)
    public void onClick() {
        //发送广播 刷新消息显示图标
        Intent i = new Intent();
        i.setAction("withdraw");
        BaseApplication.getAppContext().sendBroadcast(i);
        finish();
    }
}
