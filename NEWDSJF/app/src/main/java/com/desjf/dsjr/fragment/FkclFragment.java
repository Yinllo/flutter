package com.desjf.dsjr.fragment;


import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.net.http.SslError;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.DownloadListener;
import android.webkit.JsResult;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.bumptech.glide.Glide;
import com.desjf.dsjr.R;
import com.desjf.dsjr.activity.BankWebViewActivity;
import com.desjf.dsjr.activity.LoginActivity;
import com.desjf.dsjr.activity.NewInvestmentDetailsActivity;
import com.desjf.dsjr.activity.PhotoActivity;
import com.desjf.dsjr.activity.ProjectDetailsActivity;
import com.desjf.dsjr.base.BaseFragment;
import com.desjf.dsjr.bean.NewRegBean;
import com.desjf.dsjr.biz.ProjectInfoBiz;
import com.desjf.dsjr.biz.exception.BizFailure;
import com.desjf.dsjr.biz.exception.ZYException;
import com.desjf.dsjr.biz.retrofit.BankHttpUtil;
import com.desjf.dsjr.biz.retrofit.CallUtils;
import com.desjf.dsjr.biz.task.BizDataAsyncTask;
import com.desjf.dsjr.model.InvestmentInfoModel;
import com.desjf.dsjr.model.ProjectInfoModel;
import com.desjf.dsjr.utils.PreferenceCache;
import com.desjf.dsjr.utils.ToastUtils;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class FkclFragment extends BaseFragment {

    @BindView(R.id.iv_toleft)
    ImageView ivToleft;
    @BindView(R.id.iv_photo)
    ImageView ivPhoto;
    @BindView(R.id.iv_toright)
    ImageView ivToright;
    @BindView(R.id.ll_photo)
    LinearLayout llPhoto;
    @BindView(R.id.tv_content)
    WebView tvContent;
    @BindView(R.id.tv_empty)
    TextView tvEmpty;
    @BindView(R.id.sl_hua)
    ScrollView slHua;
    @BindView(R.id.tv_details_login)
    TextView tvDetailsLogin;
    private InvestmentInfoModel investmentInfoModels;
    private String id;
    private ArrayList<String> img = new ArrayList<>();
    private ArrayList<String> content = new ArrayList<>();
    private int i = 0;
    private ProjectInfoModel projectInfoModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_fkcl, container, false);
        ButterKnife.bind(this, view);
        initData();
        getDetails();
        return view;
    }

    private void initData() {
        if (PreferenceCache.getToken().isEmpty()) {
            tvDetailsLogin.setText("立即登录");
            tvDetailsLogin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (PreferenceCache.getToken().isEmpty()) {
                        Intent i = new Intent(getActivity(), LoginActivity.class);
                        startActivity(i);
                    } else {
//                            if (projectInfoModel.getID_CARD_VERIFY_FLG().equals("0")) {
//                               goBank();
//                            } else {
                                Intent i = new Intent(getActivity(), NewInvestmentDetailsActivity.class);
                                i.putExtra("Info", projectInfoModel);
                                i.putExtra("ID", id);
                                startActivity(i);

//                        }
                    }
                }
            });
        } else {
            if (projectInfoModel.getPRODUCTS_STATUS().toString().equals("未开始")) {
                tvDetailsLogin.setText("即将发布");
                tvDetailsLogin.setClickable(false);
            } else if (projectInfoModel.getPRODUCTS_STATUS().toString().equals("立即投资")) {
                tvDetailsLogin.setText("立即投资");
                tvDetailsLogin.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (PreferenceCache.getToken().isEmpty()) {
                            Intent i = new Intent(getActivity(), LoginActivity.class);
                            startActivity(i);
                        } else {
//                                if (projectInfoModel.getID_CARD_VERIFY_FLG().equals("0")) {
//                                   goBank();
//                                } else {
                                    Intent i = new Intent(getActivity(), NewInvestmentDetailsActivity.class);
                                    i.putExtra("Info", projectInfoModel);
                                    i.putExtra("ID", id);
                                    startActivity(i);
                                }

//                        }
                    }
                });
            } else if (projectInfoModel.getPRODUCTS_STATUS().toString().equals("满标待审")) {
                tvDetailsLogin.setText("已满标");
                tvDetailsLogin.setClickable(false);
                tvDetailsLogin.setBackgroundColor(getResources().getColor(R.color.navy_gray));
            } else if (projectInfoModel.getPRODUCTS_STATUS().toString().equals("已完成")) {
                tvDetailsLogin.setText("已还清");
                tvDetailsLogin.setClickable(false);
                tvDetailsLogin.setBackgroundColor(getResources().getColor(R.color.navy_gray));
            } else if (projectInfoModel.getPRODUCTS_STATUS().toString().equals("还款中")) {
                tvDetailsLogin.setText("收益中");
                tvDetailsLogin.setClickable(false);
                tvDetailsLogin.setBackgroundColor(getResources().getColor(R.color.navy_gray));
            }

        }

    }

    private void getDetails() {
        showLoadingDialog();
        BizDataAsyncTask<InvestmentInfoModel> getInfo = new BizDataAsyncTask<InvestmentInfoModel>() {
            @Override
            protected InvestmentInfoModel doExecute() throws ZYException, BizFailure {
                return ProjectInfoBiz.getInvestInfo(id);
            }

            @Override
            protected void onExecuteSucceeded(InvestmentInfoModel investmentInfoModel) {
                hideLoadingDialog();
                investmentInfoModels = investmentInfoModel;
                initDetails(investmentInfoModel);
            }

            @Override
            protected void OnExecuteFailed(String error) {
                hideLoadingDialog();
            }
        };
        getInfo.execute();

    }

    private void initDetails(InvestmentInfoModel investmentInfoModel) {
        img.clear();
        content.clear();
        for (int i = 0; i < investmentInfoModel.getRISKDESCRIPTIONLIST().size(); i++) {
            img.add(investmentInfoModel.getRISKDESCRIPTIONLIST().get(i).getIMGURL());
            content.add(investmentInfoModel.getRISKDESCRIPTIONLIST().get(i).getIMGTITLE());
        }
        if (img.size() != 0) {
            Glide.with(getActivity()).load(img.get(i)).crossFade().into(ivPhoto);
            llPhoto.setVisibility(View.VISIBLE);
            ivToleft.setVisibility(View.VISIBLE);
            ivToright.setVisibility(View.VISIBLE);
            tvEmpty.setVisibility(View.GONE);
        } else {
//            slHua.setVisibility(View.GONE);
            llPhoto.setVisibility(View.GONE);
            ivToleft.setVisibility(View.GONE);
            ivToright.setVisibility(View.GONE);
//            tvEmpty.setVisibility(View.VISIBLE);
        }
        if (investmentInfoModel.getPRODUCTSDESCRIPTIONLISTW().size()!=0){
            tvContent.setVisibility(View.VISIBLE);
        }else{
            tvContent.setVisibility(View.GONE);
        }
        if (img.size()==0&&investmentInfoModel.getPRODUCTSDESCRIPTIONLISTW().get(0).getDETAILDESCRIPTION().isEmpty()){
            tvEmpty.setVisibility(View.VISIBLE);
            llPhoto.setVisibility(View.GONE);
            slHua.setVisibility(View.GONE);
        }

        ivPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), PhotoActivity.class);
                intent.putStringArrayListExtra("images", img);
                intent.putStringArrayListExtra("content", content);
                intent.putExtra("currentItem", img.get(i));
                startActivity(intent);
            }
        });
        ivToright.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (i < img.size() - 1) {
                    i++;
                    Glide.with(getActivity()).load(img.get(i)).crossFade().into(ivPhoto);
                } else {
                    ToastUtils.showTost(getActivity(), "最后一张");
                }

            }
        });
        ivToleft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (i > 0) {
                    i--;
                    Glide.with(getActivity()).load(img.get(i)).crossFade().into(ivPhoto);
                } else {
                    ToastUtils.showTost(getActivity(), "第一张");
                }
            }
        });
        initWebView(investmentInfoModel);
//        tvContent.setText(Html.fromHtml(investmentInfoModels.getRISKDESCRIPTIONLISTW().get(0).getDETAILDESCRIPTION()));

    }
    private void initWebView(InvestmentInfoModel investmentInfoModel) {
        WebSettings settings = tvContent.getSettings();
        tvContent.clearCache(true);
        tvContent.clearHistory();
        settings.setJavaScriptEnabled(true);
        settings.setJavaScriptCanOpenWindowsAutomatically(true); // 支持通过JS打开新窗口
        settings.setCacheMode(WebSettings.LOAD_NO_CACHE);//不使用缓存
        settings.setAllowFileAccess(true);
        settings.setSupportMultipleWindows(true);
        settings.setSupportZoom(true);
        settings.setDisplayZoomControls(false);
        settings.setPluginState(WebSettings.PluginState.ON);
        settings.setDomStorageEnabled(true); // 必须进行这个设置
        settings.setBuiltInZoomControls(true); // 支持缩放
        settings.setLoadWithOverviewMode(true); // 初始加载时，是web页面自适应屏幕
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
        tvContent.getSettings().setDefaultTextEncodingName("UTF -8");
        tvContent.requestFocus();
        tvContent.requestFocusFromTouch();

        tvContent.setWebChromeClient(new WebChromeClient() {
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

        tvContent.setWebViewClient(new WebViewClient() {

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

        tvContent.setDownloadListener(new DownloadListener() { // 资源下载
            @Override
            public void onDownloadStart(String url, String userAgent, String contentDisposition,
                                        String mimetype, long contentLength) {
                Uri uri = Uri.parse(url);
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });
        tvContent.loadData(investmentInfoModel.getRISKDESCRIPTIONLISTW().get(0).getDETAILDESCRIPTION(),"text/html; charset=UTF-8", null);
//        tvContent.loadUrl();
//        Log.e("TAG33333333", "initWebView: "+ investmentInfoModel.getRISKDESCRIPTIONLISTW().get(0).getDETAILDESCRIPTION());
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        id = ((ProjectDetailsActivity) activity).getId();
        projectInfoModel = ((ProjectDetailsActivity) activity).getProjectInfoModel();
    }
    private void goBank(){
        //前往开通存管
        CallUtils.call(getActivity(), BankHttpUtil.getHttpRequestService().bankReg(projectInfoModel.getMOBILE()), new CallUtils.MyCallListener() {
            @Override
            public void onRespnseSuccess(String jsonString) {
                NewRegBean newRegBean= JSON.parseObject(jsonString,NewRegBean.class);
                Intent i=new Intent(getActivity(),BankWebViewActivity.class);
                i.putExtra("type",1);
                i.putExtra("newRegBean",newRegBean);
                startActivity(i);

            }
        });
    }
}
