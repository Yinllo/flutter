package com.desjf.dsjr.activity;

import android.annotation.SuppressLint;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.desjf.dsjr.R;
import com.desjf.dsjr.base.BaseActivity;
import com.desjf.dsjr.biz.InviteFriendsBiz;
import com.desjf.dsjr.biz.exception.BizFailure;
import com.desjf.dsjr.biz.exception.ZYException;
import com.desjf.dsjr.biz.task.BizDataAsyncTask;
import com.desjf.dsjr.config.DsjrConfig;
import com.desjf.dsjr.model.InviteFriendsModel;
import com.desjf.dsjr.utils.BarUtils;
import com.mob.tools.utils.UIHandler;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.tencent.qq.QQ;
import cn.sharesdk.wechat.friends.Wechat;
import cn.sharesdk.wechat.moments.WechatMoments;

import static android.R.attr.action;

public class AccountInviteFriendsActivity extends BaseActivity implements PlatformActionListener, Handler.Callback {

    @BindView(R.id.iv_invite_back)
    ImageView ivInviteBack;
    @BindView(R.id.tv_invite_money)
    TextView tvInviteMoney;
    @BindView(R.id.tv_invite_count)
    TextView tvInviteCount;
    @BindView(R.id.ll_invite_wechat)
    LinearLayout llInviteWechat;
    @BindView(R.id.ll_invite_wechat_moment)
    LinearLayout llInviteWechatMoment;
    @BindView(R.id.ll_invite_qq)
    LinearLayout llInviteQq;
    private InviteFriendsModel inviteFriends;
    private String link="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_invite_friends);
        ButterKnife.bind(this);
        BarUtils.setColor(this, getResources().getColor(R.color.endmian), 0);
        getInvite();
    }

    private void getInvite() {
        showLoadingDialog();
        @SuppressLint("StaticFieldLeak") BizDataAsyncTask<InviteFriendsModel> invite   = new BizDataAsyncTask<InviteFriendsModel>() {
            @Override
            protected InviteFriendsModel doExecute() throws ZYException, BizFailure {
                return InviteFriendsBiz.getInvite();
            }

            @Override
            protected void onExecuteSucceeded(InviteFriendsModel inviteFriendsModel) {
                hideLoadingDialog();
                initInfo(inviteFriendsModel);
            }

            @Override
            protected void OnExecuteFailed(String error) {
                hideLoadingDialog();
            }
        };
        invite.execute();
    }

    private void initInfo(InviteFriendsModel inviteFriendsModel) {
        inviteFriends = inviteFriendsModel;
        if(inviteFriendsModel.getRECEIVED_AMOUNT().equals("0.00")){
            tvInviteMoney.setText("");
        }else{
            tvInviteMoney.setText(inviteFriendsModel.getRECEIVED_AMOUNT());
        }
        tvInviteCount.setText("邀请人数:"+inviteFriendsModel.getFRIEND_COUNT()+"人");
        link = inviteFriendsModel.getINVITE_FRIEND_URL();
    }

    @OnClick({R.id.iv_invite_back, R.id.ll_invite_wechat, R.id.ll_invite_wechat_moment, R.id.ll_invite_qq})
    public void onClick(View view) {
        Platform.ShareParams sp = new Platform.ShareParams();
        Platform plat = null;
        Resources res = this.getResources();
        Bitmap bmp = BitmapFactory.decodeResource(res, R.mipmap.ic_launcher);
        switch (view.getId()) {
            case R.id.iv_invite_back:
                finish();
                break;
            case R.id.ll_invite_wechat:
//                Log.e("aaaa","111111111");
                sp.setShareType(Platform.SHARE_WEBPAGE);
                sp.setTitle("德晟金服");
                sp.setUrl(link);
                //sp.setImageData(bmp);
                sp.setImageUrl(DsjrConfig.BASE_IMG);
                sp.setText("邀请您加入德晟金服，乐享优质便利的投资产品");
                plat = ShareSDK.getPlatform(Wechat.NAME);
                plat.setPlatformActionListener(this);
                plat.share(sp);
                break;
            case R.id.ll_invite_wechat_moment:
//                Log.e("aaaa","111111111");
                //朋友圈
                sp.setShareType(Platform.SHARE_WEBPAGE);
                sp.setTitle("德晟金服");
                sp.setUrl(link);
                //sp.setImageData(bmp);
                sp.setImageUrl(DsjrConfig.BASE_IMG);
                plat = ShareSDK.getPlatform(WechatMoments.NAME);
                plat.setPlatformActionListener(this);
                plat.share(sp);
                break;
            case R.id.ll_invite_qq:
                //qq
                sp.setTitle("德晟金服");
                sp.setTitleUrl(link);
                sp.setText("邀请您加入德晟金服，乐享优质便利的投资产品");
                sp.setImageUrl(DsjrConfig.BASE_IMG);
                plat = ShareSDK.getPlatform(QQ.NAME);
                plat.setPlatformActionListener(this);
                plat.share(sp);
                break;
        }
    }

    @Override
    public boolean handleMessage(Message message) {
        String text = "";
        switch (message.arg1) {
            case 1: {
                // 成功
                Platform plat = (Platform) message.obj;
                // text = plat.getName() + "share completed ";
                text = "分享成功 ";
            }
            break;
            case 2: {
                // 失败
                if ("WechatClientNotExistException".equals(message.obj.getClass().getSimpleName())) {
                    text = this.getString(R.string.wechat_client_inavailable);
                } else if ("WechatTimelineNotSupportedException".equals(message.obj.getClass().getSimpleName())) {
                    text = this.getString(R.string.wechat_client_inavailable);
                } else {
                    text = this.getString(R.string.share_failed);
                }
            }
            break;
            case 3: {
                // 取消
                Platform plat = (Platform) message.obj;
                // text = plat.getName() + " canceled ";
                text = "取消分享 ";
            }
            break;
        }

        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
        return false;
    }

    @Override
    public void onComplete(Platform platform, int i, HashMap<String, Object> hashMap) {
        Message msg = new Message();
        msg.arg1 = 1;
        msg.arg2 = action;
        msg.obj = platform;
        UIHandler.sendMessage(msg, this);
    }

    @Override
    public void onError(Platform platform, int i, Throwable throwable) {
        throwable.printStackTrace();
        Message msg = new Message();
        msg.arg1 = 2;
        msg.arg2 = action;
        msg.obj = throwable;
        UIHandler.sendMessage(msg, this);
    }

    @Override
    public void onCancel(Platform platform, int i) {
        Message msg = new Message();
        msg.arg1 = 3;
        msg.arg2 = action;
        msg.obj = platform;
        UIHandler.sendMessage(msg, this);
    }
}
