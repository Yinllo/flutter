package com.desjf.dsjr.ui.fragment.ProjectDetails;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.desjf.dsjr.R;
import com.desjf.dsjr.base.BaseFragment;

import butterknife.BindView;

/**
        * @Author YinL
        * @Date 2018/1/24 0015
        * @Describe  项目详情中的   风险评估及提示
        */
public class RiskEvaluationFragment extends BaseFragment {

          @BindView(R.id.web_view)
          WebView mWebview;
          private WebSettings mWebSettings;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_risk_evaluation;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        //加载H5
        initWebView("http://www.dsp2p.com/m/riskAssessment1.html");
    }

    private void initWebView(String url){

              mWebSettings = mWebview.getSettings();
              mWebview.loadUrl(url);

              //设置不用系统浏览器打开,直接显示在当前Webview
              mWebview.setWebViewClient(new WebViewClient() {
                  @Override
                  public boolean shouldOverrideUrlLoading(WebView view, String url) {
                      view.loadUrl(url);
                      return true;
                  }
              });


              //设置WebViewClient类
              mWebview.setWebViewClient(new WebViewClient() {
                  //设置加载前的函数
                  @Override
                  public void onPageStarted(WebView view, String url, Bitmap favicon) {
                   showLoadingDialog();
                  }

                  //设置结束加载函数
                  @Override
                  public void onPageFinished(WebView view, String url) {
                   hideLoadingDialog();
                  }
              });
          }

    //销毁Webview
    @Override
    public void onDestroy() {
        if (mWebview != null) {
            mWebview.loadDataWithBaseURL(null, "", "text/html", "utf-8", null);
            mWebview.clearHistory();

            ((ViewGroup) mWebview.getParent()).removeView(mWebview);
            mWebview.destroy();
            mWebview = null;
        }
        super.onDestroy();
    }
}
