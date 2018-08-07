package com.desjf.dsjr.activity;

import android.app.Dialog;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.content.FileProvider;
import android.text.format.Time;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RemoteViews;
import android.widget.TextView;

import com.desjf.dsjr.R;
import com.desjf.dsjr.base.BaseActivity;
import com.desjf.dsjr.base.BaseApplication;
import com.desjf.dsjr.biz.TransactionPasswordBiz;
import com.desjf.dsjr.biz.exception.BizFailure;
import com.desjf.dsjr.biz.exception.ZYException;
import com.desjf.dsjr.biz.task.BizDataAsyncTask;
import com.desjf.dsjr.biz.task.EasyLocalTask;
import com.desjf.dsjr.config.DsjrConfig;
import com.desjf.dsjr.cymodel.utils.VersionUtils;
import com.desjf.dsjr.model.VersionDescription;
import com.desjf.dsjr.util.HttpUtil;
import com.desjf.dsjr.utils.PreferenceCache;
import com.desjf.dsjr.utils.StringUtil;
import com.desjf.dsjr.widget.PromptOkCancel;

import java.io.File;
import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AccountSettingActivity extends BaseActivity {
    @BindView(R.id.ll_phone_us)
    LinearLayout llPhoneUs;
    @BindView(R.id.gesture_button_close)
    ImageView gestureButtonClose;
    @BindView(R.id.ll_change_gesture)
    LinearLayout llChangeGesture;
    @BindView(R.id.gesture_button_open)
    ImageView gestureButtonOpen;
    @BindView(R.id.view_line)
    View viewLine;
    private Context context = this;
    @BindView(R.id.ll_about_us)
    LinearLayout llAboutUs;
    @BindView(R.id.iv_setting_back)
    ImageView ivSettingBack;
    @BindView(R.id.ll_ssmm)
    LinearLayout llSsmm;
    @BindView(R.id.ll_reset_login_psw)
    LinearLayout llResetLoginPsw;
//    @BindView(R.id.ll_reset_jiaoyi_psw)
//    LinearLayout llResetJiaoyiPsw;
    @BindView(R.id.tv_setting_tuichu)
    TextView tvSettingTuichu;

    TextView tv_version;
//    private BaseDialog dialog_progress, dialog_version, dialog_download;
    private VersionDescription vd;
    private  boolean flag=false;//是否有交易密码
    private int progress;
    private Notification notification;
    private NotificationManager notificationManager;
    RemoteViews view = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_setting);

        tv_version = (TextView) findViewById(R.id.tv_version);
        tv_version.setText(VersionUtils.getVersionName(this));


//        dialog_version = new BaseDialog(this);
//        dialog_version.config(dialog_version, Gravity.CENTER, -1, false);
//        dialog_version.findViewById(R.id.tv_version_cancel).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                dialog_version.dismiss();
//            }
//        });
//        dialog_version.findViewById(R.id.tv_upgrade).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                dialog_version.dismiss();
//            }
//        });


//        findViewById(R.id.layout_version).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                new EasyLocalTask<Void, VersionDescription>() {
//
//                    @Override
//                    protected VersionDescription doInBackground(Void... params) {
//                        try {
//                            return HomeBiz.detectNewVersion();
//                        } catch (ZYException e) {
//                            return null;
//                        }
//
//                    }
//
//                    @Override
//                    protected void onPostExecute(VersionDescription result) {
//                        super.onPostExecute(result);
//                        if (result != null) {
//                            if (!DeviceUtil.getVesionName(AccountSettingActivity.this).equals(result.getAndroid())) {
//                                vd = result;
//                                if (ContextCompat.checkSelfPermission(AccountSettingActivity.this,
//                                        Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
//
//                                    ActivityCompat.requestPermissions(AccountSettingActivity.this,
//                                            new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 100);
//                                } else {
//                                    new PromptOkCancel(AccountSettingActivity.this) {
//                                        @Override
//                                        protected void onOk() {
//
//                                            if (StringUtil.isEmpty(vd.getAndroidDownloadLink())) {
//                                                return;
//                                            }
//                                            downloadApk(vd.getAndroidDownloadLink());
//                                        }
//                                    }.show(AccountSettingActivity.this.getString(R.string.new_version_detected), vd.getAndroidUpdateMessage(),
//                                            R.string.download_background, R.string.remind_me_later, vd.getAndroidForceUpdate(), false);// 如果给传递值就强制下载
//
//                                }
//
//                            }
//                        }
//
//                    }
//                }.execute();
////                CallUtils.callOrigin(this, dialog_progress, InitUtils.getHttpRequestService().getVersion(1), new CallUtils.CallOriginListener() {
////                    @Override
////                    public void onRespnse(String jsonString) {
////                        JSONObject jsonObject = JSON.parseObject(jsonString);
////                        if (VersionUtils.getVersionCode(SettingActivity.this) < Integer.valueOf(jsonObject.getString("v_num"))) {
////                            dialog_version.show();
////                            url_apk=jsonObject.getString("url");
////                        }else {
////                            showToast("已是最新版本");
////                        }
////                    }
////                });
//            }
//        });
        ButterKnife.bind(this);

    }

    @Override
    protected void onResume() {
        super.onResume();

        if (PreferenceCache.getGestureFlag() == false) {
            gestureButtonClose.setVisibility(View.VISIBLE);
            gestureButtonOpen.setVisibility(View.GONE);
            llChangeGesture.setVisibility(View.GONE);
            viewLine.setVisibility(View.GONE);
        } else {
            gestureButtonClose.setVisibility(View.GONE);
            gestureButtonOpen.setVisibility(View.VISIBLE);
            llChangeGesture.setVisibility(View.VISIBLE);
            viewLine.setVisibility(View.VISIBLE);
            llChangeGesture.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent1 = new Intent(AccountSettingActivity.this, GestureChangeActivity.class);
                    intent1.putExtra("gestureFlg", 2);
                    startActivity(intent1);
                }
            });
            gestureButtonOpen.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    gestureButtonClose.setVisibility(View.VISIBLE);
                    gestureButtonOpen.setVisibility(View.GONE);
                    PreferenceCache.putGestureFlag(false);
                    llChangeGesture.setVisibility(View.GONE);

                }
            });
        }
        gestureButtonClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(AccountSettingActivity.this, GestureSetActivity.class);
                startActivity(intent1);
            }
        });
    }

//    private void init() {
//
//    }

    @OnClick({R.id.ll_about_us, R.id.iv_setting_back, R.id.ll_ssmm, R.id.ll_reset_login_psw,R.id.tv_setting_tuichu, R.id.ll_phone_us})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_about_us:
                //关于我们
                Intent intent = new Intent(AccountSettingActivity.this, AccountAboutUsActivity.class);
                startActivity(intent);
                break;
            case R.id.iv_setting_back:
                finish();
                break;
            case R.id.ll_ssmm:
                //手势密码
//                if (PreferenceCache.getGestureFlag()) {
//                    Intent intent1 = new Intent(this, GestureTestActivity.class);
//                    intent1.putExtra("gestureFlg", 1);
//                    startActivity(intent1);
//                } else {
//                    Intent intent1 = new Intent(this, GestureSetActivity.class);
//                    startActivity(intent1);
//                }
                break;
            case R.id.ll_reset_login_psw:
                //修改登录密码
                Intent intent_reSetPsw = new Intent(this, ResetLoginPswActivity.class);
                startActivity(intent_reSetPsw);
                break;
//            case R.id.ll_reset_jiaoyi_psw:
//                //修改交易密码 判断是否设置了交易密码 没有则进入设置界面  否则进入修改界面
//                ifsetPayPsw();
//                break;
            case R.id.tv_setting_tuichu:
                //退出登录
                showDialog("是否退出登录?", 3);
                break;
            case R.id.ll_phone_us:
                //联系我们
                Intent intent_us = new Intent(this, PhoneUsActivity.class);
                startActivity(intent_us);
                break;
        }
    }


    //先判断是否设置了交易密码
    public  void ifsetPayPsw(){
//        showLoadingDialog();
        BizDataAsyncTask<String> ifsetPayPsw = new BizDataAsyncTask<String>() {
            @Override
            protected String doExecute() throws ZYException, BizFailure {
                return  TransactionPasswordBiz.ifSettedPayPassword();
            }

            @Override
            protected void onExecuteSucceeded(String s) {
//                hideLoadingDialog();
                Intent  intent_reJyPsw = new Intent(AccountSettingActivity.this, ReSetJYPswActivity.class);
                startActivity(intent_reJyPsw);
            }

            @Override
            protected void OnExecuteFailed(String error) {
//                hideLoadingDialog();
                Intent intent_setJyPsw = new Intent(AccountSettingActivity.this, SetJYPswActivity.class);
                startActivity(intent_setJyPsw);
            }
        };
        ifsetPayPsw.execute();

//        return flag;
    }



    private void showDialog(String msg, final int flg) {
        final Dialog dialog = new Dialog(this, R.style.My_Dialog);
        dialog.setContentView(R.layout.dialog_auth_name);
        dialog.setCancelable(false);
        TextView tv_msg = (TextView) dialog.findViewById(R.id.tv_msg);
        tv_msg.setText(msg);
        dialog.findViewById(R.id.tv_cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        dialog.findViewById(R.id.tv_ok).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (flg == 1) {
                    //未实名
                    Intent intent = new Intent(context, AccountRealNameActivity.class);
                    startActivity(intent);
                } else if (flg == 2) {
                    //未绑卡
                    Intent inBankCard = new Intent(context, AccountAddbanksActivity.class);
                    startActivity(inBankCard);
                } else if (flg == 3) {
                    //退出账户
                    PreferenceCache.putToken("");
//                    Util.showLogin(AccountSettingActivity.this);
                    PreferenceCache.putGestureFlag(false);
                    Intent over = new Intent(context, MainActivity.class);
//                    DsjrConfig.TYPE = 1;
                     DsjrConfig.WHERE = 10;
//                    PreferencesUtil.writeInt(AccountSettingActivity.this, "where", 2);
                    startActivity(over);
                    finish();
                }
                dialog.dismiss();
            }
        });
        dialog.show();
        DisplayMetrics dm = context.getResources().getDisplayMetrics();
        int displayWidth = dm.widthPixels;
        int displayHeight = dm.heightPixels;
        android.view.WindowManager.LayoutParams p = dialog.getWindow().getAttributes(); //获取对话框当前的参数值
        p.width = (int) (displayWidth * 0.8); //宽度设置为屏幕的0.5
        p.height = (int) (displayHeight * 0.3); //宽度设置为屏幕的0.25
        dialog.setCanceledOnTouchOutside(false);// 设置点击屏幕Dialog不消失
        dialog.getWindow().setAttributes(p);  //设置生效
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            new PromptOkCancel(AccountSettingActivity.this) {

                @Override
                protected void onOk() {
                    if (StringUtil.isEmpty(vd.getAndroidDownloadLink())) {
                        return;
                    }
                    downloadApk(vd.getAndroidDownloadLink());
                }
            }.show(AccountSettingActivity.this.getString(R.string.new_version_detected), vd.getAndroidUpdateMessage(),
                    R.string.download_background, R.string.remind_me_later, vd.getAndroidForceUpdate(), false);// 如果给传递值就强制下载
        }
    }

    private void downloadApk(final String url) {
        new EasyLocalTask<Void, File>() {
            @Override
            protected File doInBackground(Void... params) {
                File file = new File(BaseApplication.CACHE_ROOT_CACHE_DIR + File.separator + DsjrConfig.APK_NAME);
                try {
                    notification();
                    HttpUtil.downloadFile(url, file, new HttpUtil.IDownloadCallback() {
                        int i = 0;

                        @Override
                        public void onProgress(long currentSize, long totalSize) {

                            progress = (int) (((float) currentSize / totalSize) * 100);

                            if ((int) (progress / 10) > i) {
                                i = (int) (progress / 10);
                                // 更改进度条
                                notification.contentView.setProgressBar(R.id.progress, (int) (totalSize / 1024 / 1000),
                                        (int) (currentSize / 1024 / 1000), false);
                                // 发送消息
                                notificationManager.notify(101, notification);
                            }
                        }
                    });
                    // HttpUtil.downloadFile(url, file);
                } catch (IOException e) {
                    file = null;
                }

                return file;
            }

            @Override
            protected void onPostExecute(File result) {
                super.onPostExecute(result);
                if (result != null) {
                    notificationManager.cancel(101);// notification关闭不显示
//                    installApk(result);
                    install(AccountSettingActivity.this, result);
                }
            }
        }.execute();
    }

    public void install(Context context, File file) {
//		File file = new File(
//				Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)
//				, JiadhConfig.APK_NAME);
        Intent intent = new Intent(Intent.ACTION_VIEW);
        // 由于没有在Activity环境下启动Activity,设置下面的标签
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        if (Build.VERSION.SDK_INT >= 24) { //判读版本是否在7.0以上
            //参数1 上下文, 参数2 Provider主机地址 和配置文件中保持一致   参数3  共享的文件
            Uri apkUri = FileProvider.getUriForFile(context, "com.dsjr.fileprovider", file);
            //添加这一句表示对目标应用临时授权该Uri所代表的文件
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            intent.setDataAndType(apkUri, "application/vnd.android.package-archive");
        } else {
            intent.setDataAndType(Uri.fromFile(file),
                    "application/vnd.android.package-archive");
        }
        context.startActivity(intent);
    }

    private void notification() {
        notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        notification = new Notification(R.mipmap.ic_launcher, "下载新版本", System.currentTimeMillis());

        if (view == null) {
            view = new RemoteViews(getPackageName(), R.layout.notification);
            notification.contentView = view;
            notification.contentView.setProgressBar(R.id.progress, 100, 0, false);
        }
        PendingIntent contentIntent = PendingIntent.getActivity(this, R.string.app_name, new Intent(),
                PendingIntent.FLAG_UPDATE_CURRENT);
        notification.contentIntent = contentIntent;
        notification.flags |= Notification.FLAG_ONGOING_EVENT;// 滑动或者clear都不会清空
        // 获取系统当前时间
        Time t = new Time();
        t.setToNow(); // 取得系统时间。
        int hour = t.hour;
        int minute = t.minute;
        if (hour >= 12) {
            if (hour == 12) {
                notification.contentView.setTextViewText(R.id.time, "下午" + hour + ":" + minute);
            }
            notification.contentView.setTextViewText(R.id.time, "下午" + (hour - 12) + ":" + minute);
        } else {
            notification.contentView.setTextViewText(R.id.time, "上午" + hour + ":" + minute);
        }
        notificationManager.notify(101, notification);
    }

}
