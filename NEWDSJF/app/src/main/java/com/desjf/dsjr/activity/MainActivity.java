package com.desjf.dsjr.activity;

import android.Manifest;
import android.app.Dialog;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Process;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.text.format.Time;
import android.util.DisplayMetrics;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RemoteViews;
import android.widget.TextView;
import android.widget.Toast;

import com.desjf.dsjr.R;
import com.desjf.dsjr.base.BaseActivity;
import com.desjf.dsjr.base.BaseApplication;
import com.desjf.dsjr.base.BaseFragment;
import com.desjf.dsjr.biz.HomeBiz;
import com.desjf.dsjr.biz.exception.ZYException;
import com.desjf.dsjr.biz.task.EasyLocalTask;
import com.desjf.dsjr.config.DsjrConfig;
import com.desjf.dsjr.fragment.IndividualCenter.MyMainFragment;
import com.desjf.dsjr.fragment.IndividualCenter.WodeFragment;
import com.desjf.dsjr.fragment.ShouyeFragment;
import com.desjf.dsjr.fragment.invest.InvestFragment;
import com.desjf.dsjr.model.VersionDescription;
import com.desjf.dsjr.util.HttpUtil;
import com.desjf.dsjr.utils.DeviceUtil;
import com.desjf.dsjr.utils.PreferenceCache;
import com.desjf.dsjr.utils.StringUtil;
import com.desjf.dsjr.widget.PromptOkCancel;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity {
    @BindView(R.id.btn_wode)
    RadioButton btnWode;
    private List<BaseFragment> list_fragment;
    private Context context = this;
    @BindView(R.id.main_tab_group)
    RadioGroup mainTabGroup;
    long lastBackKeyDownTime = 0;
    private int where;
    private Intent intent;

    // 定义FragmentManager对象管理器
    private android.support.v4.app.FragmentManager fragmentManager;
    private ShouyeFragment shoyeFragment;
    private InvestFragment investfragment;//投资  带有头部导航栏
//    private StandardpowderFragment standardpowderFragment;//投资
    private WodeFragment wodeFragment;
    private MyMainFragment myMainFragment;


    private BroadcastReceiver receiver;//退出登录跳转广播
    private int progress;
    private static final int DOWNLOADING = 1; // 表示正在下载
    private static final int DOWNLOADED = 2; // 下载完毕
    private static final int DOWNLOAD_FAILED = 3; // 下载失败
    private boolean cancelFlag = false;
    private NotificationManager notificationManager;
    private Notification notification;
    RemoteViews view = null;
    private VersionDescription vd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        //注册广播
        receiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                onReceiveBroadcast(intent);
            }
        };

        IntentFilter filter = new IntentFilter();
        filter.addAction("exit"); //token过期
        registerReceiver(receiver, filter);

//        initData();   老方法 将同时加载三个Fragment ，导致加载速度缓慢

        //实例化
        fragmentManager=getSupportFragmentManager();
        initView();
        detectUpgrade();

    }


    private void detectUpgrade() {
        new EasyLocalTask<Void, VersionDescription>() {

            @Override
            protected VersionDescription doInBackground(Void... params) {
                try {
                    return HomeBiz.detectNewVersion();
                } catch (ZYException e) {
                    return null;
                }

            }

            @Override
            protected void onPostExecute(VersionDescription result) {
                super.onPostExecute(result);
                if (result != null) {
                    if (!DeviceUtil.getVesionName(MainActivity.this).equals(result.getAndroid())) {
                        vd = result;

                        //进行权限检测 ：没有权限则提示用户授权，有权限则直接进行更新
                        installProcess();

                    }
                }
            }
        }.execute();
    }

    public  void showSimpleDialog(Context context , String message,String ok,int type) {
        View view = LayoutInflater.from(context).inflate(R.layout.mydialog, null);
        TextView confirm; //确定按钮
        final TextView content; //内容
        confirm = (TextView) view.findViewById(R.id.tv_dialog_ok);
        content = (TextView) view.findViewById(R.id.tv_dialog_message);confirm.setText(ok);
        content.setText(message);
        final Dialog dialog = new Dialog(context,R.style.My_Dialog);
        dialog.setContentView(view);
        dialog.setCancelable(false);

        if (type==1) {
            confirm.setOnClickListener(new View.OnClickListener() {
                @RequiresApi(api = Build.VERSION_CODES.O)
                @Override
                public void onClick(View v) {
                    startInstallPermissionSettingActivity();
                    dialog.dismiss();
                }
            });
        } else {
            confirm.setOnClickListener(new View.OnClickListener() {
                @RequiresApi(api = Build.VERSION_CODES.O)
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                    ActivityCompat.requestPermissions(MainActivity.this,
                            new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 100);
                }
            });
        }
        dialog.show();
        DisplayMetrics dm = context.getResources().getDisplayMetrics();
        int displayWidth = dm.widthPixels;
        int displayHeight = dm.heightPixels;
        android.view.WindowManager.LayoutParams p = dialog.getWindow().getAttributes(); //获取对话框当前的参数值
        p.width = (int) (displayWidth * 0.8); //宽度设置为屏幕的0.5
        p.height = (int) (displayHeight * 0.25); //宽度设置为屏幕的0.25
        dialog.setCanceledOnTouchOutside(false);// 设置点击屏幕Dialog不消失
        dialog.getWindow().setAttributes(p);  //设置生效

    }

    //打开设置——允许安装未知来源的应用
    @RequiresApi(api = Build.VERSION_CODES.O)
    private void startInstallPermissionSettingActivity() {
        //注意这个是8.0新API
        Intent intent = new Intent(Settings.ACTION_MANAGE_UNKNOWN_APP_SOURCES);
        startActivityForResult(intent, 10086);
    }


    //接收广播
    private void onReceiveBroadcast(Intent intent) {
        String action = intent.getAction();
        if (action.equals("exit")) {
            mainTabGroup.check(R.id.btn_shouye);
//            getSupportFragmentManager().beginTransaction()
//                    .show(list_fragment.get(0))
//                    .hide(list_fragment.get(1))
//                    .hide(list_fragment.get(2))
//                    .commit();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 10086) {
            installProcess();//再次执行安装流程，包含权限判等
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    private void initView() {
        //默认选中首页
        mainTabGroup.check(R.id.btn_shouye);
        setFragmentChioce(0);

        mainTabGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
//                for (int j = 0; j < list_fragment.size(); j++) {
//                    getSupportFragmentManager().beginTransaction()
//                            .hide(list_fragment.get(j))
//                            .commitAllowingStateLoss();
//                }
                switch (i) {
                    case R.id.btn_shouye:
                        setFragmentChioce(0);
//                        getSupportFragmentManager().beginTransaction()
//                                .show(list_fragment.get(0))
//                                .commitAllowingStateLoss();
                        break;
                    case R.id.btn_invest:
                        setFragmentChioce(1);
//                        getSupportFragmentManager().beginTransaction()
//                                .show(list_fragment.get(1))
//                                .commitAllowingStateLoss();
                        break;
                    case R.id.btn_wode:
                        if (DsjrConfig.WHERE == 10) {
                            return;
                        } else {
                            if (PreferenceCache.getToken().equals("")) {
                                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                                intent.putExtra("login", 1);
                                startActivity(intent);
                                mainTabGroup.check(R.id.btn_shouye);
                                setFragmentChioce(0);
//                                getSupportFragmentManager().beginTransaction()
//                                        .show(list_fragment.get(0))
//                                        .hide(list_fragment.get(1))
//                                        .hide(list_fragment.get(2))
//                                        .commitAllowingStateLoss();
                            } else {
                                setFragmentChioce(2);
//                                getSupportFragmentManager().beginTransaction()
//                                        .show(list_fragment.get(2))
//                                        .commitAllowingStateLoss();
                            }
                        }
                        break;
                }
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (DsjrConfig.WHERE == 1) {
            mainTabGroup.check(R.id.btn_wode);
            setFragmentChioce(2);

//            getSupportFragmentManager().beginTransaction()
//                    .show(list_fragment.get(2))
//                    .hide(list_fragment.get(0))
//                    .hide(list_fragment.get(1))
//                    .commitAllowingStateLoss();

            BaseApplication.globalIndex = 0;
            DsjrConfig.WHERE = 0;

        } else if (DsjrConfig.WHERE == 2) {
            mainTabGroup.check(R.id.btn_shouye);
            setFragmentChioce(0);

//            getSupportFragmentManager().beginTransaction()
//                    .show(list_fragment.get(0))
//                    .hide(list_fragment.get(1))
//                    .hide(list_fragment.get(2))
//                    .commitAllowingStateLoss();

            DsjrConfig.WHERE = 0;

        } else if (DsjrConfig.WHERE == 3) {
            mainTabGroup.check(R.id.btn_invest);
            setFragmentChioce(1);

//            getSupportFragmentManager().beginTransaction()
//                    .show(list_fragment.get(1))
//                    .hide(list_fragment.get(0))
//                    .hide(list_fragment.get(2))
//                    .commitAllowingStateLoss();

            DsjrConfig.WHERE = 0;
        }
    }

    /**
     * 设置Fragment 的选择事件
     */
    private void setFragmentChioce(int index){
        FragmentTransaction fragmentTransaction= fragmentManager.beginTransaction();
        //隐藏Fragment
        hideFragments(fragmentTransaction);
        switch(index){
            case 0:
                //如果没有则实例化,并添加到界面上
                if(null==shoyeFragment){
                  shoyeFragment=new ShouyeFragment();
                  fragmentTransaction.add(R.id.fragment_layout,shoyeFragment);
                }else{
                  fragmentTransaction.show(shoyeFragment);
                }
            break;
            case 1:
                if(null==investfragment){
                    investfragment=new InvestFragment();
                    fragmentTransaction.add(R.id.fragment_layout,investfragment);
                }else{
                    fragmentTransaction.show(investfragment);
                }
            break;
            case 2:
                if(null==myMainFragment){
                    myMainFragment=new MyMainFragment();
                    fragmentTransaction.add(R.id.fragment_layout,myMainFragment);
                }else{
                    fragmentTransaction.show(myMainFragment);
                }
            break;
        }
        //提交
        fragmentTransaction.commitAllowingStateLoss();
    }

    /**
     * 隐藏Fragment
     * @param fragmentTransaction
     */
    private void hideFragments(FragmentTransaction fragmentTransaction){
       //已经实例化的对象隐藏
        if(null!=shoyeFragment){
           fragmentTransaction.hide(shoyeFragment);
        }
        if(null!=investfragment){
           fragmentTransaction.hide(investfragment);
        }
        if(null!=myMainFragment){
           fragmentTransaction.hide(myMainFragment);
        }
    }


    private void initData() {
        shoyeFragment = new ShouyeFragment();
        investfragment = new InvestFragment();
        myMainFragment = new MyMainFragment();
//        wodeFragment=new WodeFragment();
        list_fragment = new ArrayList<>();
        list_fragment.add(shoyeFragment);
        list_fragment.add(investfragment);
        list_fragment.add(myMainFragment);
        for (int i = 0; i < list_fragment.size(); i++) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragment_layout, list_fragment.get(i), "BaseFragment" + i)
                    .show(list_fragment.get(0))
                    .hide(list_fragment.get(i))
                    .commitAllowingStateLoss();
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        return super.onKeyDown(keyCode, event);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(receiver);
    }

    @Override
    public void onBackPressed() {
        if (System.currentTimeMillis() - lastBackKeyDownTime > 2000) { // 两秒钟内双击返回键关闭主界面
            Toast.makeText(this, R.string.double_tap_to_exit, Toast.LENGTH_SHORT).show();
            lastBackKeyDownTime = System.currentTimeMillis();

        } else {
            Process.killProcess(Process.myPid());
            super.onBackPressed();
        }
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        if (DsjrConfig.WHERE == 10) {
            mainTabGroup.check(R.id.btn_shouye);

//            getSupportFragmentManager().beginTransaction()
//                    .show(list_fragment.get(0))
//                    .hide(list_fragment.get(1))
//                    .hide(list_fragment.get(2))
//                    .commit();

            DsjrConfig.WHERE = 0;
        }
    }
//    VersionDescription vd;

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            new PromptOkCancel(MainActivity.this) {

                @Override
                protected void onOk() {
                    if (StringUtil.isEmpty(vd.getAndroidDownloadLink())) {
                        return;
                    }
                    downloadApk(vd.getAndroidDownloadLink());
                }
            }.show(MainActivity.this.getString(R.string.new_version_detected), vd.getAndroidUpdateMessage(),
                    R.string.download_background, R.string.remind_me_later, vd.getAndroidForceUpdate(), false);// 如果给传递值就强制下载
        }else{

          showSimpleDialog(context,"更新失败！您拒绝了存储权限，将无法正常更新和使用应用，请您授予该项权限!","知道了",2);
//            ToastUtils.showLongTost();

        }
    }

    private void downloadApk(final String url) {
        new EasyLocalTask<Void, File>() {
            @Override
            protected File doInBackground(Void... params) {
                File file = new File(BaseApplication.CACHE_ROOT_CACHE_DIR + File.separator + DsjrConfig.APK_NAME);
                try {
                    //如果当前系统是8.0以上的，则创建 通知通道
                    if (Build.VERSION.SDK_INT>=26){
                        showChannel1Notification();
                    }else{
                        notification();
                    }
                    HttpUtil.downloadFile(url, file, new HttpUtil.IDownloadCallback() {
                        int i = 0;

                        @Override
                        public void onProgress(long currentSize, long totalSize) {

                            progress = (int) (((float) currentSize / totalSize) * 100);

                            //每下载四分之一通知进度条更新进度
                            if ((int) (progress / 25) > i) {
                                i = (int) (progress / 25);
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

            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            protected void onPostExecute(File result) {
                super.onPostExecute(result);
                if (result != null) {
                    notificationManager.cancel(101);// notification关闭不显示
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                        //关闭通知通道
                        notificationManager.deleteNotificationChannel("1");
                    }
//                    installApk(result);
                    install(MainActivity.this, result);
                }
            }
        }.execute();
    }

    //权限检测  没有相应权限则需要用户开启后  方可实现更新
    public  void installProcess() {
//        boolean haveInstallPermission;
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//            //先获取是否有安装未知来源应用的权限
//            haveInstallPermission = getPackageManager().canRequestPackageInstalls();
//            if (!haveInstallPermission) {//没有权限 则去开启
//                showSimpleDialog(context,"安装更新需要  允许安装未知来源应用  的权限，否则无法完成更新。请您去设置中开启权限！","前往开启",1);
//                //请求安装未知应用来源的权限
////                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.REQUEST_INSTALL_PACKAGES}, 200);
//                return;
//            }
//        }

        if (ContextCompat.checkSelfPermission(MainActivity.this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(MainActivity.this,
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 100);

        }
        else {
            new PromptOkCancel(MainActivity.this) {
                @Override
                protected void onOk() {

                    if (StringUtil.isEmpty(vd.getAndroidDownloadLink())) {
                        return;
                    }
                    downloadApk(vd.getAndroidDownloadLink());
                }
            }.show(MainActivity.this.getString(R.string.new_version_detected), vd.getAndroidUpdateMessage(),
                    R.string.download_background, R.string.remind_me_later, vd.getAndroidForceUpdate(), false);// 如果给传递值就强制下载
        }
    }


    //安装更新
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
            intent.setDataAndType(Uri.fromFile(file), "application/vnd.android.package-archive");
        }
        context.startActivity(intent);
    }

    //如果当前系统是8.0以上的，则需要使用新的通知创建方法来适配
    @RequiresApi(api = Build.VERSION_CODES.O)
    private void showChannel1Notification(){
        notificationManager= (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
//        notificationManager.createNotificationChannelGroup(new NotificationChannelGroup("a", "a"));

        //创建 通知通道  channelid和channelname是必须的
        NotificationChannel channel = new NotificationChannel("1",
                "Channel1", NotificationManager.IMPORTANCE_DEFAULT);
        channel.enableLights(true);//是否在桌面icon右上角展示小红点
        channel.setLightColor(Color.GREEN);//小红点颜色
        channel.setShowBadge(true); //是否在久按桌面图标时显示此渠道的通知
        notificationManager.createNotificationChannel(channel);

        int notificationId = 0x1234;
        Notification.Builder builder = new Notification.Builder(context,"1");

        builder.setSmallIcon(R.mipmap.ic_launcher)
                .setContentText("正在下载新版本，请稍后...")
//                .setOnlyAlertOnce(true)//设置通知提示音的次数，只允许提示一次
                .setAutoCancel(true);

        notification=builder.build();
        //设置通知提示音的次数，只允许提示一次
        notification.flags = Notification.FLAG_ONLY_ALERT_ONCE;
        notificationManager.notify(notificationId,notification);

        if (view == null) {
            view = new RemoteViews(getPackageName(), R.layout.notification);
            notification.contentView = view;
            notification.contentView.setProgressBar(R.id.progress, 100, 0, false);
        }

        PendingIntent contentIntent = PendingIntent.getActivity(this, R.string.app_name, new Intent(),
                PendingIntent.FLAG_UPDATE_CURRENT);
        notification.contentIntent = contentIntent;
        notification.flags |= Notification.FLAG_ONGOING_EVENT;// 滑动或者clear都不会清空
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
