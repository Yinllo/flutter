package com.desjf.dsjr.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.net.http.SslError;
import android.os.Bundle;
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

import com.desjf.dsjr.R;
import com.desjf.dsjr.base.BaseActivity;
import com.desjf.dsjr.base.BaseApplication;
import com.desjf.dsjr.bean.NewRegBean;
import com.desjf.dsjr.config.DsjrConfig;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class BankWebViewActivity extends BaseActivity {

    @BindView(R.id.iv_web_back)
    ImageView ivWebBack;
    @BindView(R.id.tv_web_title)
    TextView tvWebTitle;
    @BindView(R.id.wv_webview)
    WebView wvWebview;
    private String url;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);
        ButterKnife.bind(this);
        initView();
        initData();
    }

    private void initView() {
        Intent intent = getIntent();
            //渤海银行 开户
            NewRegBean newRegBean= (NewRegBean) intent.getSerializableExtra("newRegBean");

        int type=intent.getIntExtra("type",-1);

        if(type==1){
            //开户
            url=DsjrConfig.BANK_BASE_DOMAIN+"?Transid=CBHBNetLoanRegister&\n" +
                    "NetLoanInfo="+newRegBean.getRes();
            tvWebTitle.setText("存管开户");
        } if(type==2){
            //充值
            tvWebTitle.setText("存管充值");
            url=DsjrConfig.BANK_BASE_DOMAIN+"?Transid=CBHBNetLoanRecharge&NetLoanInfo="+newRegBean.getRes();
        }else if(type==3){
            //提现
            tvWebTitle.setText("存管提现");
            url=DsjrConfig.BANK_BASE_DOMAIN+"?Transid=CBHBNetLoanWithdraw&NetLoanInfo="+newRegBean.getRes();
        }else if(type==4){
            //改银行卡
            tvWebTitle.setText("修改绑定银行卡");
            url=DsjrConfig.BANK_BASE_DOMAIN+"?Transid=CBHBNetLoanBindCardMessage&NetLoanInfo="+newRegBean.getRes();
        }
        else if(type==5){
            //改密码
            tvWebTitle.setText("修改存管密码");
            url=DsjrConfig.BANK_BASE_DOMAIN+"?Transid=CBHBNetLoanUpdatePassword&NetLoanInfo="+newRegBean.getRes();
        }
        else if(type==6){
            //改手机号
            tvWebTitle.setText("修改存管手机号");
            url=DsjrConfig.BANK_BASE_DOMAIN+"?Transid=CBHBNetLoanUpdatePhone&NetLoanInfo="+newRegBean.getRes();
        }
        else if(type==7){
            //找回密码
            tvWebTitle.setText("找回存管密码");
            url=DsjrConfig.BANK_BASE_DOMAIN+"?Transid=CBHBNetLoanGetPassword&NetLoanInfo="+newRegBean.getRes();
        }
        else if(type==8){
            //对公开户
            tvWebTitle.setText("对公开户");
            url=DsjrConfig.BANK_BASE_DOMAIN+"?Transid=CBHBNetLoanRegisterPublic&NetLoanInfo="+newRegBean.getRes();
        }
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
                //H5点击按钮 进行相应跳转
                if (url != null && url.contains("/openApp?index")) {
                    //首页
                    goIndex();
                    finish();
                }else if(url != null && url.contains("/openApp?mime")){
                    //个人中心
                    personerCenter();
                    finish();
                }else {
                    view.loadUrl(url);
                }
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
//        wvWebview.loadData(url,"text/html","GBK");
        wvWebview.loadUrl(url);
//        Log.e("url",url);

        // 启用javascript
        wvWebview.getSettings().setJavaScriptEnabled(true);
        wvWebview.addJavascriptInterface(BankWebViewActivity.this, "android");
        wvWebview.addJavascriptInterface(BankWebViewActivity.this, "android");

    }

    //由于安全原因 targetSdkVersion>=17需要加 @JavascriptInterface
    //JS调用Android JAVA方法名和HTML中的按钮 onclick后的别名后面的名字对应
    @JavascriptInterface
    public void jsCallRegister(){
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);

    }
    @JavascriptInterface
    public void goIndex(){
        Intent intent = new Intent(this, MainActivity.class);
        DsjrConfig.WHERE=2;
        startActivity(intent);

    }
    @JavascriptInterface
    public void personerCenter(){
        Intent intent = new Intent(this, MainActivity.class);
        Intent i = new Intent();
        i.setAction("withdraw");
        BaseApplication.getAppContext().sendBroadcast(i);
        DsjrConfig.WHERE=1;
        DsjrConfig.TO_MY=2;
        startActivity(intent);

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();

    }

    @OnClick(R.id.iv_web_back)
    public void onClick() {
//        wvWebview.loadUrl(DsjrConfig.WS_BASE_DOMAIN + "iloanWebService/polAndReg.html");
        Intent intent = new Intent();
        intent.setAction("withdraw");
        BaseApplication.getAppContext().sendBroadcast(intent);
        finish();
    }
}
