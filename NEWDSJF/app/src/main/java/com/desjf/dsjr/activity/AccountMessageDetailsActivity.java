package com.desjf.dsjr.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.desjf.dsjr.R;
import com.desjf.dsjr.base.BaseActivity;
import com.desjf.dsjr.biz.MessageBiz;
import com.desjf.dsjr.biz.exception.BizFailure;
import com.desjf.dsjr.biz.exception.ZYException;
import com.desjf.dsjr.biz.task.BizDataAsyncTask;
import com.desjf.dsjr.model.MessageInfoModel;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AccountMessageDetailsActivity extends BaseActivity {

    @BindView(R.id.iv_message_back)
    ImageView ivMessageBack;
    @BindView(R.id.tv_message_details_title)
    TextView tvMessageDetailsTitle;
    @BindView(R.id.tv_message_details_content)
    TextView tvMessageDetailsContent;
    private String id;
    private String status = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_message_details);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        id = intent.getStringExtra("id");
        status = getIntent().getStringExtra("status");
        if(status!=null)
        {
            Log.e("status",status);
        }

        getMessageInfo();
    }

    private void getMessageInfo() {
        showLoadingDialog();
        BizDataAsyncTask<MessageInfoModel> getInfo = new BizDataAsyncTask<MessageInfoModel>() {
            @Override
            protected MessageInfoModel doExecute() throws ZYException, BizFailure {
                return MessageBiz.getNewsInfo(id);
            }

            @Override
            protected void onExecuteSucceeded(MessageInfoModel messageInfoModel) {
                hideLoadingDialog();
                initData(messageInfoModel);
                if (status != null) {
                    setResult(RESULT_OK);
                }
            }

            @Override
            protected void OnExecuteFailed(String error) {
                hideLoadingDialog();
            }
        };
        getInfo.execute();
    }

    private void initData(MessageInfoModel messageInfoModel) {
        tvMessageDetailsTitle.setText(messageInfoModel.getTITLE());
        tvMessageDetailsContent.setText(messageInfoModel.getMSG_CONTENT());
    }

    @OnClick(R.id.iv_message_back)
    public void onClick() {
        finish();
    }
}
