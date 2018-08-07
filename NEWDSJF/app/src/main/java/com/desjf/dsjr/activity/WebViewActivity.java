package com.desjf.dsjr.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.net.http.SslError;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
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
import com.desjf.dsjr.config.DsjrConfig;
import com.desjf.dsjr.utils.PreferenceCache;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class WebViewActivity extends BaseActivity {

    @BindView(R.id.iv_web_back)
    ImageView ivWebBack;
    @BindView(R.id.tv_web_title)
    TextView tvWebTitle;
    @BindView(R.id.wv_webview)
    WebView wvWebview;
    private String url;
    private String news;
    private String gonggao;

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
        //公告跳入
        int web = intent.getIntExtra("web", -1);
        if (web == 1) {
            //公告
            url = intent.getStringExtra("1");
        } else if (web == 2) {
            url = intent.getStringExtra("2");
            tvWebTitle.setText("新手指引");
        } else if (web == 3) {
            url = intent.getStringExtra("3");
            tvWebTitle.setText("关于德晟");
        } else if (web == 4) {
            url = intent.getStringExtra("4");
            tvWebTitle.setText("活动专区");
        } else if (web == 5) {
            url = DsjrConfig.WS_BASE_DOMAIN + "iloanWebService/agreement.html";
            tvWebTitle.setText("服务协议");
        } else if (web == 6) {
            url = DsjrConfig.WS_BASE_DOMAIN + "iloanWebService/withOutAbstract.html";
            tvWebTitle.setText("提现说明");
        } else if (web == 7) {
            url = DsjrConfig.WS_BASE_DOMAIN + "iloanWebService/comProblem.html";
            tvWebTitle.setText("常见问题");
        } else if (web == 8) {
            url = DsjrConfig.WS_BASE_DOMAIN + "iloanWebService/tariffDes.html";
            tvWebTitle.setText("资费说明");
        } else if (web == 9) {
            url = DsjrConfig.WS_BASE_DOMAIN + "iloanWebService/polAndReg.html";
            tvWebTitle.setText("政策法规");
        }
        else if (web == 10) {
            url ="https://www.dsp2p.com/m/risk_companyInfo.html";//公司简介
            tvWebTitle.setText("关于我们");
        }
        else if (web == 11) {
            url ="https://www.dsp2p.com/m/share_platform.html";//平台信息
            tvWebTitle.setText("关于我们");
        }
        else if (web == 12) {
            url ="https://www.dsp2p.com/m/shareHolder.html";//股东背景
            tvWebTitle.setText("关于我们");
        }
        else if (web == 13) {
            url ="https://www.dsp2p.com/m/share_team.html";//管理团队
            tvWebTitle.setText("关于我们");
        }
        else if (web == 14) {
            url ="https://www.dsp2p.com/m/share_framework.html";//组织架构
            tvWebTitle.setText("关于我们");
        }
        else if (web == 15) {
            url ="https://www.dsp2p.com/m/share_honor.html";//荣誉资质
            tvWebTitle.setText("关于我们");
        }
        else if (web == 16) {
            url ="https://www.dsp2p.com/m/share_Investment.html";//投资产品
            tvWebTitle.setText("关于我们");
        }
        else if (web == 17) {
            url ="https://www.dsp2p.com/m/share_cooperation.html";//合作伙伴
            tvWebTitle.setText("关于我们");
        } else if (web == 18) {
            url ="https://www.dsp2p.com/m/share_map.html";//联系我们
            tvWebTitle.setText("关于我们");
        }
        else if (web == 19) {
            url ="https://www.dsp2p.com/m/share_bank.html";//银行存管
            tvWebTitle.setText("风险管理");
        }
        else if (web == 20) {
            url ="https://www.dsp2p.com/m/risk_management.html";//风控流程
            tvWebTitle.setText("风险管理");
        }
        else if (web == 21) {
            url ="https://www.dsp2p.com/m/share_data.html";//数据安全
            tvWebTitle.setText("风险管理");
        }

        else if (web == 22) {
            url ="https://www.dsp2p.com/m/share_InvestmentPeople.html";//投资须知
            tvWebTitle.setText("风险管理");
        }
        else if (web == 23) {
            url ="https://www.dsp2p.com/m/risk.html";//合同存证
            tvWebTitle.setText("风险管理");
        }
        else if (web == 24) {
            url ="https://www.dsp2p.com/m/share_audit.html";//审计报告
            tvWebTitle.setText("审计报告");
        }
        else if (web == 25) {
            url ="https://www.dsp2p.com/m/share_censor.html";//法律意见书
            tvWebTitle.setText("法律意见书");
        }
        else if (web == 26) {
            url ="https://www.dsp2p.com/m/operational.html";//运营数据
            tvWebTitle.setText("运营数据");
        }

        else if (web == 27) {
            url ="https://www.dsp2p.com/m/share_risk.html";//风险提示书
            tvWebTitle.setText("风险提示书");
        }

        else if (web == 28) {
            url ="https://www.dsp2p.com/m/share_contract.html";//电子合同签署授权书
            tvWebTitle.setText("电子签章授权");
        }
        else if (web == 29) {
            url ="http://www.dsp2p.com/m/riskAssessment1.html";//风险评估及提示
            tvWebTitle.setText("风险评估及提示");
        }
        else if (web == 30) {
            url ="http://www.dsp2p.com/m/riskAssessment2.html";//贷后管理
            tvWebTitle.setText("贷后管理");
        }else if (web == 31) {
            url ="https://www.dsp2p.com/m/share_compliance.html";//合规进程
            tvWebTitle.setText("合规进程");
        }else if (web == 32) {
            url ="https://www.dsp2p.com/m/share_none.html";//备案登记
            tvWebTitle.setText("备案登记");
        }else if (web == 33) {
            url ="https://www.dsp2p.com/m/share_none1.html";//粤ICP证
            tvWebTitle.setText("粤ICP证");
        }else if (web == 34) {
            url ="https://www.dsp2p.com/m/share_none2.html";//信披确认书
            tvWebTitle.setText("信披确认书");
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
            if(url != null && url.contains("/openApp?mine")){
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

        //给H5传递token
        if(null!=url) {
            if (url.contains("phonePager")) {
                if (("").equals(PreferenceCache.getToken())) {
                    url = url + "?token=no";
                } else {
                    url = url + "?token=" + PreferenceCache.getToken();
                }
            }
        }
        wvWebview.loadUrl(url);
        // 启用javascript
        wvWebview.getSettings().setJavaScriptEnabled(true);
        wvWebview.addJavascriptInterface(WebViewActivity.this, "android");
    }

    //由于安全原因 targetSdkVersion>=17需要加 @JavascriptInterface
    //JS调用Android JAVA方法名和HTML中的按钮 onclick后的别名后面的名字对应
    @JavascriptInterface
    public void jsCallInvite() {
        Intent intent = new Intent(this, AccountInviteFriendsActivity.class);
        startActivity(intent);
    }

    //跳转到个人中心
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


    private void syncCookie(Context context, String url){
        try{
            CookieSyncManager.createInstance(context);
            CookieManager cookieManager = CookieManager.getInstance();
            cookieManager.setAcceptCookie(true);
            cookieManager.removeSessionCookie();// 移除
            cookieManager.removeAllCookie();
            String cookieValue = "WLZBS_TOKEN="+ PreferenceCache.getToken();
            cookieManager.setCookie(url, cookieValue);
            CookieSyncManager.getInstance().sync();
         }catch(Exception e){
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        gonggao = "";
        news = "";
    }

    @OnClick(R.id.iv_web_back)
    public void onClick() {
//        wvWebview.loadUrl(DsjrConfig.WS_BASE_DOMAIN + "iloanWebService/polAndReg.html");
        finish();
    }
}
