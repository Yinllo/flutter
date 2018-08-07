package com.desjf.dsjr.ui.activity;

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

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
/**
 * @author YinL
 * @Describe  加载相关的H5页面
 */
public class WebViewActivity extends BaseActivity {

    @BindView(R.id.iv_back)
    ImageView ivWebBack;
    @BindView(R.id.tv_title)
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
        //获得url和标题信息
        String webUrl = intent.getStringExtra("webUrl");
        String title = intent.getStringExtra("title");

        url = webUrl;
        tvWebTitle.setText(title);

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
//        wvWebview.loadData(url,"text/html","UTF-8");
        wvWebview.loadUrl(url);

        // 启用javascript
        wvWebview.getSettings().setJavaScriptEnabled(true);
        wvWebview.addJavascriptInterface(WebViewActivity.this, "android");
    }

    //由于安全原因 targetSdkVersion>=17需要加 @JavascriptInterface
    //JS调用Android JAVA方法名和HTML中的按钮 onclick后的别名后面的名字对应
    @JavascriptInterface
    public void jsCallRegister() {
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();

    }

    @OnClick(R.id.iv_back)
    public void onClick() {
        finish();
    }
}
