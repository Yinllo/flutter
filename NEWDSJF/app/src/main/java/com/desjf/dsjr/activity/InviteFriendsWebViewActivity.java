package com.desjf.dsjr.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.net.http.SslError;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.DisplayMetrics;
import android.webkit.DownloadListener;
import android.webkit.JavascriptInterface;
import android.webkit.JsResult;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
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

public class InviteFriendsWebViewActivity extends BaseActivity implements PlatformActionListener, Handler.Callback{

    @BindView(R.id.iv_web_back)
    ImageView ivWebBack;
    @BindView(R.id.tv_web_title)
    TextView tvWebTitle;
    @BindView(R.id.wv_webview)
    WebView wvWebview;
    private String url;//打开的h5地址

    private InviteFriendsModel inviteFriends;
    private String link="";  //邀请地址
    Platform.ShareParams sp;
    Platform plat = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invite_friends_web_view);
        ButterKnife.bind(this);

        initView();
        initData();
        getInvite();

    }

    private void initView() {
        //分享初始化
        sp = new Platform.ShareParams();
        Resources res = this.getResources();
        Bitmap bmp = BitmapFactory.decodeResource(res, R.mipmap.ic_launcher);

       url = "https://www.dsp2p.com/m/activityfive.html";

    }

    private void initData() {
        WebSettings settings = wvWebview.getSettings();
        wvWebview.clearHistory();
        wvWebview.clearCache(true);
        settings.setJavaScriptEnabled(true);
        settings.setJavaScriptCanOpenWindowsAutomatically(true); // 支持通过JS打开新窗口
        //        settings.setCacheMode(WebSettings.LOAD_NO_CACHE);//不使用缓存
        settings.setAllowFileAccess(true);
        settings.setSupportMultipleWindows(true);
        settings.setSupportZoom(true);
        settings.setDomStorageEnabled(true); // 必须进行这个设置
        settings.setBuiltInZoomControls(true); // 支持缩放
        settings.setLoadWithOverviewMode(true); // 初始加载时，是web页面自适应屏幕

        settings.setDisplayZoomControls(false);
        settings.setPluginState(WebSettings.PluginState.ON);

        int screenDensity = getResources().getDisplayMetrics().densityDpi;
        WebSettings.ZoomDensity zoomDensity = WebSettings.ZoomDensity.MEDIUM;
        switch (screenDensity) {
            case DisplayMetrics.DENSITY_LOW:
                zoomDensity = WebSettings.ZoomDensity.CLOSE;
                break;
            case DisplayMetrics.DENSITY_MEDIUM:
                zoomDensity = WebSettings.ZoomDensity.MEDIUM;
                break;
            case DisplayMetrics.DENSITY_HIGH:
                zoomDensity = WebSettings.ZoomDensity.FAR;
                break;
        }
        settings.setDefaultZoom(zoomDensity);

        wvWebview.requestFocus();
        wvWebview.requestFocusFromTouch();

        wvWebview.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);

            }

            @Override
            public boolean onJsAlert(WebView view, String url, String message,
                                     JsResult result) {
                return super.onJsAlert(view, url, message, result);
            }
        });

        wvWebview.setWebViewClient(new WebViewClient() {

            @Override
            public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
                handler.proceed();
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                    view.loadUrl(url);
                return true;
            }

            @Override
            public void doUpdateVisitedHistory(WebView view, String url, boolean isReload) {
                super.doUpdateVisitedHistory(view, url, isReload);
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                showLoadingDialog();
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                hideLoadingDialog();
            }
        });

        wvWebview.setDownloadListener(new DownloadListener() { // 资源下载
            @Override
            public void onDownloadStart(String url, String userAgent, String contentDisposition,
                                        String mimetype, long contentLength) {
                Uri uri = Uri.parse(url);
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });

        wvWebview.loadUrl(url);
        // 启用javascript
        wvWebview.getSettings().setJavaScriptEnabled(true);
        wvWebview.addJavascriptInterface(InviteFriendsWebViewActivity.this, "android");
    }

    //由于安全原因 targetSdkVersion>=17需要加 @JavascriptInterface
    //JS调用Android JAVA方法名和HTML中的按钮 onclick后的别名后面的名字对应
    @JavascriptInterface
    public void AndroidInviteWechat() {
        sp.setShareType(Platform.SHARE_WEBPAGE);
        sp.setTitle("德晟金服");
        sp.setUrl(link);
        //sp.setImageData(bmp);
        sp.setImageUrl(DsjrConfig.BASE_IMG);
        sp.setText("邀请您加入德晟金服，乐享优质便利的投资产品");
        plat = ShareSDK.getPlatform(Wechat.NAME);
        plat.setPlatformActionListener(this);
        plat.share(sp);
    }

    @JavascriptInterface
    public void AndroidInviteWechatMoment() {
        //朋友圈
        sp.setShareType(Platform.SHARE_WEBPAGE);
        sp.setTitle("德晟金服");
        sp.setUrl(link);
        //sp.setImageData(bmp);
        sp.setImageUrl(DsjrConfig.BASE_IMG);
        plat = ShareSDK.getPlatform(WechatMoments.NAME);
        plat.setPlatformActionListener(this);
        plat.share(sp);
    }

    @JavascriptInterface
    public void AndroidInviteQQ() {
        sp.setTitle("德晟金服");
        sp.setTitleUrl(link);
        sp.setText("邀请您加入德晟金服，乐享优质便利的投资产品");
        sp.setImageUrl(DsjrConfig.BASE_IMG);
        plat = ShareSDK.getPlatform(QQ.NAME);
        plat.setPlatformActionListener(this);
        plat.share(sp);
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
        link = inviteFriendsModel.getINVITE_FRIEND_URL();
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



    @OnClick(R.id.iv_web_back)
    public void onClick() {
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }

}
