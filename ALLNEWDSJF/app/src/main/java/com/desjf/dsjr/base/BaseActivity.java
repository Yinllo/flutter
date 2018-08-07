package com.desjf.dsjr.base;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.widget.Toast;

import com.desjf.dsjr.ui.activity.LoginActivity;
import com.desjf.dsjr.widget.CustomClipLoading;
import com.desjf.dsjr.widget.MyToast;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;
import com.umeng.analytics.MobclickAgent;

import butterknife.Unbinder;

/**
 * @author YinL
 * @Describe 基础Activity(包含基础性配置)
 */


public class BaseActivity extends RxAppCompatActivity {
    protected BroadcastReceiver receiver;
    //能够跳转到登录界面
    protected boolean canShowLogin = true;
    protected boolean queryForFirstTime = false;
    protected boolean canQueryFromResume = true;

    private CustomClipLoading dialog;// 加载
    private MyToast mToast;
    private Unbinder unbinder;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        queryForFirstTime = true;
        //注册广播：用于接收用户token是否过期
        receiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                onReceiveBroadcast(intent);
            }
        };

        IntentFilter filter = new IntentFilter();
        filter.addAction("dsjr.action.token.expire"); //token过期
        registerReceiver(receiver, filter);
    }

    private void onReceiveBroadcast(Intent intent) {
        String action = intent.getAction();
        if (action.equals("dsjr.action.token.expire")) {//token过期
            //            if (DsjfConfig.TYPE == 1) {
            //                //                return;
            //                DsjfConfig.TYPE = 0;
            //            } else {
            //                ToastUtils.showTost(BaseActivity.this, "用户名密码过期，请重新登录");
            //            }

            /**
             * MainActivity 与 其他 Activity 都继承于BaseActivity ，当超时时 会显示出多个Login页面
             * 这里block住所有onPause的Activity(意在只保留当前显示的Activity) 防止重复出现多个login
             */
            if (canShowLogin) {
                Intent i = new Intent(BaseActivity.this, LoginActivity.class);
                i.putExtra("login", 2);
                startActivity(i);
            }
        }
    }

    //设置字体大小始终使用设定的字体大小，而不随手机系统字体大小的改变而改变
    @Override
    public Resources getResources() {
        Resources resources=super.getResources();
        Configuration configuration=new Configuration();
        configuration.setToDefaults();
        resources.updateConfiguration(configuration,resources.getDisplayMetrics());
        return resources;
    }

    @Override
    protected void onPause() {
        super.onPause();
        canShowLogin = false;
        //友盟
        MobclickAgent.onPause(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        canShowLogin = true;
        MobclickAgent.onResume(this);
    }

    @Override
    protected void onDestroy() {
        if (receiver != null) {
            unregisterReceiver(receiver);
        }
        if (unbinder != null) {
            unbinder.unbind();
        }
        super.onDestroy();
    }

    public void showToast(String text) {
        if (TextUtils.isEmpty(text)) {
            return;
        }
        if (mToast == null) {
            mToast = MyToast.makeTextToast(this, text, Toast.LENGTH_SHORT);
        }
        mToast.setText(text);
        mToast.show();
    }

    //    public WaitingView getWaitingView() {
    //        WaitingView waitingView = (WaitingView) findViewById(WaitingView.WAITING_VIEW_ID);
    //        if (waitingView == null) {
    //            throw new RuntimeException("your layout has not included the \"waitingView\"");
    //        } else {
    //            return waitingView;
    //        }
    //    }

    /**
     * 展示进度条
     */
    protected void showLoadingDialog() {
        if (dialog == null) {
            dialog = new CustomClipLoading(this);
            dialog.setOnOutSide(false);
        }
        if (!isFinishing()) {
            dialog.show();
        }
    }

    /**
     * 隐藏进度条
     */
    protected void hideLoadingDialog() {
        if (dialog != null) {
            dialog.dismiss();
            dialog = null;
        }
    }

    /**
     * 启动需要的Activity
     * @param cls
     */
    public void startAppcompatActivity(Class<?> cls) {
        startActivity(new Intent(this, cls));
    }

}

