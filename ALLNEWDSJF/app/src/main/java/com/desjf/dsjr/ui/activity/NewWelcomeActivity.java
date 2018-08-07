package com.desjf.dsjr.ui.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.net.http.SslError;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.KeyEvent;
import android.view.ViewGroup;
import android.webkit.DownloadListener;
import android.webkit.JsResult;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.desjf.dsjr.R;
import com.desjf.dsjr.utils.PreferenceCache;

import butterknife.BindView;
import butterknife.ButterKnife;

   /**
     * @Author YinL
     * @Date 2018/1/15 0015
     * @Describe APP第一次启动时的欢迎页面（加载H5页面）
     */
public class NewWelcomeActivity extends AppCompatActivity {
    @BindView(R.id.webview_guide)
    WebView guide;
    //是否显示引导界面
    boolean isShow = false;
    private String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_welcome);
        ButterKnife.bind(this);
        // 得到Preference存储的isShow数据
        isShow = PreferenceCache.getGuideBoolean();
        //isShow=false;调试的时候用的
        if (isShow) {
            initLog();
        } else {
            initData();
        }

        //设置不用系统浏览器打开,直接显示在当前Webview
        guide.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });

        //设置WebChromeClient类
        guide.setWebChromeClient(new WebChromeClient() {
            //获取网站标题
            @Override
            public void onReceivedTitle(WebView view, String title) {
                System.out.println("标题在这里");

            }

            //获取加载进度
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                if (newProgress < 100) {
                    String progress = newProgress + "%";
                } else if (newProgress == 100) {
                    String progress = newProgress + "%";
                }
            }
        });


        //设置WebViewClient类
        guide.setWebViewClient(new WebViewClient() {
            //设置加载前的函数
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                System.out.println("开始加载了");

            }

            //设置结束加载函数
            @Override
            public void onPageFinished(WebView view, String url) {
                System.out.println("结束加载了");

            }
        });
    }

       //如果当前不是第一次启动则 进入登录界面
       private void initLog() {
           startActivity(new Intent(this, MainActivity.class));
           finish();
       }

    @SuppressLint("JavascriptInterface")
    private void initData() {
        WebSettings settings = guide.getSettings();
        url="http://www.baidu.com/";
        guide.clearHistory();
        guide.clearCache(true);
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

        guide.requestFocus();
        guide.requestFocusFromTouch();

        guide.setWebChromeClient(new WebChromeClient() {
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

        guide.setWebViewClient(new WebViewClient() {

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

        guide.setDownloadListener(new DownloadListener() { // 资源下载
            @Override
            public void onDownloadStart(String url, String userAgent, String contentDisposition,
                                        String mimetype, long contentLength) {
                Uri uri = Uri.parse(url);
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });
        //        guide.loadData(url,"text/html","UTF-8");
        guide.loadUrl(url);
        //        Log.e("url",url);

        // 启用javascript
        guide.getSettings().setJavaScriptEnabled(true);
        guide.addJavascriptInterface(NewWelcomeActivity.this, "android");
    }

    //由于安全原因 targetSdkVersion>=17需要加 @JavascriptInterface
    //JS调用Android JAVA方法名和HTML中的按钮 onclick后的别名后面的名字对应
//    @JavascriptInterface
//    public void jsCallRegister() {
//        Intent intent = new Intent(this, RegistOneStepActivity.class);
//        startActivity(intent);
//
//    }



    public void onClick() {
        finish();
    }


    //点击返回上一页面而不是退出浏览器
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && guide.canGoBack()) {
            guide.goBack();
            return true;
        }

        return super.onKeyDown(keyCode, event);
    }

    //销毁Webview
    @Override
    protected void onDestroy() {
        if (guide != null) {
            guide.loadDataWithBaseURL(null, "", "text/html", "utf-8", null);
            guide.clearHistory();

            ((ViewGroup) guide.getParent()).removeView(guide);
            guide.destroy();
            guide = null;
        }
        super.onDestroy();
    }
}
