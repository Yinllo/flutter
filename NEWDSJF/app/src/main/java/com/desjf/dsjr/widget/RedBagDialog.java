package com.desjf.dsjr.widget;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.net.http.SslError;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.DisplayMetrics;
import android.webkit.JsResult;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.desjf.dsjr.R;

/**
 * Created by Administrator on 2017/8/14 0014.
 */

public class RedBagDialog extends Dialog {
    private WebView wvWebview;
    private String str;
    private Context context;
    public RedBagDialog(@NonNull Context context,String str) {
        super(context, R.style.My_Dialog);
        this.str = str;
        this.context = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item_dialog_redbag);
        initData();
    }

    private void initData() {
        wvWebview = (WebView) findViewById(R.id.wv_webview);
        WebSettings settings = wvWebview.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setJavaScriptCanOpenWindowsAutomatically(true); // 支持通过JS打开新窗口
//        settings.setCacheMode(WebSettings.LOAD_NO_CACHE);//不使用缓存
        settings.setAllowFileAccess(true);
        settings.setSupportMultipleWindows(true);
        settings.setSupportZoom(true);
        settings.setDomStorageEnabled(true); // 必须进行这个设置
        settings.setBuiltInZoomControls(true); // 支持缩放
        settings.setLoadWithOverviewMode(true); // 初始加载时，是web页面自适应屏幕
        int screenDensity =context.getResources().getDisplayMetrics().densityDpi;
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
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
            }
        });


        wvWebview.loadData(str,"text/html","UTF-8");
//        wvWebview.loadUrl(url);
    }
}
