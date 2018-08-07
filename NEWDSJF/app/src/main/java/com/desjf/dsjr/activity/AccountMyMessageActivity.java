package com.desjf.dsjr.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.desjf.dsjr.R;
import com.desjf.dsjr.adapter.AccountMyMessageAdapter;
import com.desjf.dsjr.base.BaseActivity;
import com.desjf.dsjr.base.BaseApplication;
import com.desjf.dsjr.base.ExtraConfig;
import com.desjf.dsjr.biz.MessageBiz;
import com.desjf.dsjr.biz.exception.BizFailure;
import com.desjf.dsjr.biz.exception.ZYException;
import com.desjf.dsjr.biz.task.BizDataAsyncTask;
import com.desjf.dsjr.model.MessageModel;
import com.desjf.dsjr.utils.PreferenceCache;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AccountMyMessageActivity extends BaseActivity {
    @BindView(R.id.iv_message_back)
    ImageView ivMessageBack;
    @BindView(R.id.srl_message)
    SwipeRefreshLayout srlMessage;
    private AccountMyMessageAdapter accountMyMessageAdapter;
    @BindView(R.id.rv_account_my_message)
    RecyclerView rvAccountMyMessage;
    private Context context = this;
    private List<String> list_id = new ArrayList<>();
    private List<MessageModel> messageModel = new ArrayList<>();
    private int clickPosition = 0;
    private int i=0;
    private int lastVisibleItem;
    private LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_my_message);
        ButterKnife.bind(this);
        //获取消息
        if (PreferenceCache.getToken().equals("")) {
            Intent intent = new Intent(AccountMyMessageActivity.this, LoginActivity.class);
            startActivity(intent);
//            ShouyeMessageActivity.mInstance.finish();
            finish();
        } else {
            getMessage();

        }
        initData();



    }

    private void getMessage() {
        showLoadingDialog();
        BizDataAsyncTask<List<MessageModel>> getMessage = new BizDataAsyncTask<List<MessageModel>>() {
            @Override
            protected List<MessageModel> doExecute() throws ZYException, BizFailure {
                return MessageBiz.getMessageInfo(String.valueOf(i), "20");
            }

            @Override
            protected void onExecuteSucceeded(List<MessageModel> messageModels) {
                hideLoadingDialog();
                initMessage(messageModels);
                srlMessage.setRefreshing(false);

            }

            @Override
            protected void OnExecuteFailed(String error) {
                hideLoadingDialog();
                srlMessage.setRefreshing(false);
            }
        };
        getMessage.execute();
    }

    private void initMessage(List<MessageModel> messageModels) {

        if (i==0){
            messageModel.clear();
            messageModel.addAll(messageModels);
            accountMyMessageAdapter = new AccountMyMessageAdapter(messageModel, this);
            rvAccountMyMessage.setAdapter(accountMyMessageAdapter);
            rvAccountMyMessage.setLayoutManager(linearLayoutManager);
            accountMyMessageAdapter.notifyDataSetChanged();
        }else{
//            messageModel.addAll(messageModels);
            accountMyMessageAdapter.addData(messageModels);
            accountMyMessageAdapter.notifyDataSetChanged();
        }


    }

    @Override
    protected void onResume() {
        super.onResume();
        i=0;
        getMessage();
    }

    private void initData() {
        rvAccountMyMessage.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent = new Intent(AccountMyMessageActivity.this, AccountMessageDetailsActivity.class);
                intent.putExtra("id", messageModel.get(position).getID());
                if (messageModel.get(position).getMAIL_STATUS().equals("未读")) {
                    position = clickPosition;
                    intent.putExtra("status", messageModel.get(position).getMAIL_STATUS());

                }
                startActivityForResult(intent, ExtraConfig.RequestCode.REQUEST_CODE_FOR_MESSAGE_INFO);
            }
        });
        srlMessage.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                i=0;
                getMessage();
            }
        });
        srlMessage.setColorSchemeResources(R.color.main);
        rvAccountMyMessage.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == RecyclerView.SCROLL_STATE_IDLE
                        && lastVisibleItem + 1 == accountMyMessageAdapter.getItemCount()&&accountMyMessageAdapter.getItemCount()>=19) {
                    i++;
                   getMessage();
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                lastVisibleItem = linearLayoutManager.findLastVisibleItemPosition();
            }
        });

    }

    @OnClick(R.id.iv_message_back)
    public void onClick() {
        //发送广播 刷新消息显示图标
        Intent i = new Intent();
        i.setAction("withdraw");
        BaseApplication.getAppContext().sendBroadcast(i);
        finish();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == ExtraConfig.RequestCode.REQUEST_CODE_FOR_MESSAGE_INFO) {

            if (resultCode == RESULT_OK) {
                //MessageModel messageItemTwo = messageModel.get(clickPosition);
                //  messageModel.get(clickPosition).setOPEN_FLG("1");
//                accountMyMessageAdapter.setOPEN_FLG("1");
//                accountMyMessageAdapter = new AccountMyMessageAdapter(messageModel, this);
//                rvAccountMyMessage.setAdapter(accountMyMessageAdapter);
//                accountMyMessageAdapter.notifyDataSetChanged();
            }

        }
    }
}
