package com.desjf.dsjr.ui.activity;


import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Process;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.FileProvider;
import android.text.format.Time;
import android.view.KeyEvent;
import android.widget.RadioGroup;
import android.widget.RemoteViews;
import android.widget.Toast;

import com.desjf.dsjr.R;
import com.desjf.dsjr.base.BaseActivity;
import com.desjf.dsjr.base.BaseApplication;
import com.desjf.dsjr.base.BaseConfig;
import com.desjf.dsjr.base.BaseFragment;
import com.desjf.dsjr.bean.VersionDescription;
import com.desjf.dsjr.net.EasyLocalTask;
import com.desjf.dsjr.ui.fragment.HomeFragment;
import com.desjf.dsjr.ui.fragment.MineFragment;
import com.desjf.dsjr.ui.fragment.NoLoginMineFragment;
import com.desjf.dsjr.ui.fragment.StandardpowderFragment;
import com.desjf.dsjr.utils.DownloadUtil;
import com.desjf.dsjr.utils.StringUtil;
import com.desjf.dsjr.widget.PromptOkCancel;

import java.io.File;
import java.io.IOException;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 全新的项目设计
 * @author YinL  新建于2018.1.13日
 * @Describe 项目主Activity   由于三个主界面不需要左右滑动的效果，因此使用RadioGroup
 * 来切换
 */

public class MainActivity extends BaseActivity {
    @BindView(R.id.main_tab_group)
    RadioGroup mainTabGroup;
    // 定义FragmentManager对象管理器
    android.support.v4.app.FragmentManager fragmentManager;
    //Fragment集合
    private List<BaseFragment> list_fragment;
    //首页、投资、我的、未登录下的我的
    private HomeFragment homeFragment;
    private StandardpowderFragment standardpowderFragment;
//    private MyMainFragment myMainFragment;
    private MineFragment mineFragment;
    private NoLoginMineFragment noLoginMineFragment;
    private Context context = this;
    //记录用户点击退出按钮时间 用于存放上一点击“返回键”的时刻
    private long lastBackKeyDownTime = 0;
    //退出登录跳转广播
    private BroadcastReceiver receiver;
    //更新进度
    private int progress;
    //APP后台更新——1表示正在下载、2表示下载完毕、3表示下载失败
    private static final int DOWNLOADING = 1;
    private static final int DOWNLOADED = 2;
    private static final int DOWNLOAD_FAILED = 3;
    //Apk后台下载通知
    private NotificationManager notificationManager;
    private Notification notification;
    RemoteViews view = null;
    //APK版本信息实体类
    private VersionDescription vd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        //注册广播（退出应用）
        receiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                onReceiveBroadcast(intent);
            }
        };
        IntentFilter filter = new IntentFilter();
        filter.addAction("exit"); //token过期
        registerReceiver(receiver, filter);

        //初始化操作
        initView();
    }

    private void initView() {
        //实例化
        fragmentManager=getSupportFragmentManager();
        //默认选中项
        mainTabGroup.check(R.id.btn_home);
        setFragmentSelect(0);

        mainTabGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i) {
                    case R.id.btn_home:
                        setFragmentSelect(0);
                        break;
                    case R.id.btn_invest:
                        setFragmentSelect(1);
                        break;
                    case R.id.btn_mime:
                        if (BaseConfig.WHERE == 10) {
                            return;
                        } else {
                            //没有登录
                            if (BaseConfig.NoLogin==1) {
//                                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
//                                intent.putExtra("login", 1);
//                                startActivity(intent);
//                                mainTabGroup.check(R.id.btn_home);
//                                getSupportFragmentManager().beginTransaction()
//                                        .show(list_fragment.get(0))
//                                        .hide(list_fragment.get(1))
//                                        .hide(list_fragment.get(2))
//                                        .commit();
                                setFragmentSelect(3);
                            } else {
                                setFragmentSelect(2);
                            }
                        }
                        break;
                }
            }
        });
    }

    //初始化三个Fragment  懒加载方式
    private void setFragmentSelect(int index){
        FragmentTransaction fragmentTransaction= fragmentManager.beginTransaction();
        //首先将所有的Fragment隐藏
        hideFragments(fragmentTransaction);
        switch(index){
            case 0:
                if(null==homeFragment){
                    homeFragment=new HomeFragment();
                    fragmentTransaction.add(R.id.fragment_layout,homeFragment);
                }else{
                    fragmentTransaction.show(homeFragment);
                }
            break;
            case 1:
                if(null==standardpowderFragment){
                    standardpowderFragment=new StandardpowderFragment();
                    fragmentTransaction.add(R.id.fragment_layout,standardpowderFragment);
                }else{
                    fragmentTransaction.show(standardpowderFragment);
                }
            break;
            case 2:
                if(null==mineFragment){
                    mineFragment=new MineFragment();
                    fragmentTransaction.add(R.id.fragment_layout,mineFragment);
                }else{
                    fragmentTransaction.show(mineFragment);
                }
            break;
            case 3:
                if(null==noLoginMineFragment){
                    noLoginMineFragment=new NoLoginMineFragment();
                    fragmentTransaction.add(R.id.fragment_layout,noLoginMineFragment);
                }else{
                    fragmentTransaction.show(noLoginMineFragment);
                }
                break;
        }
        //提交
        fragmentTransaction.commitAllowingStateLoss();
    }

    //将已经实例化的对象隐藏
    private void hideFragments(FragmentTransaction fragmentTransaction){
       if(homeFragment!=null){
           fragmentTransaction.hide(homeFragment);
       }
       if(standardpowderFragment!=null){
           fragmentTransaction.hide(standardpowderFragment);
       }
       if(mineFragment!=null){
           fragmentTransaction.hide(mineFragment);
       }
       if(noLoginMineFragment!=null){
           fragmentTransaction.hide(noLoginMineFragment);
       }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (BaseConfig.WHERE == 1) {
            mainTabGroup.check(R.id.btn_mime);
            setFragmentSelect(2);
            BaseApplication.globalIndex = 0;
            BaseConfig.WHERE = 0;
        } else if (BaseConfig.WHERE == 2) {
            mainTabGroup.check(R.id.btn_home);
            setFragmentSelect(0);
            BaseConfig.WHERE = 0;
        }
    }

    //MainActivity 的启动模式为singleTask
    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        if (BaseConfig.WHERE == 10) {
            mainTabGroup.check(R.id.btn_home);
            BaseConfig.WHERE = 0;
        }
    }

    //接收广播
    private void onReceiveBroadcast(Intent intent) {
        String action = intent.getAction();
        if (action.equals("exit")) {
            mainTabGroup.check(R.id.btn_home);
        }
    }

    //权限判断，开始apk新版本的更新
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            //弹出更新Dialog
            new PromptOkCancel(MainActivity.this) {
                @Override
                protected void onOk() {
                    if (StringUtil.isEmpty(vd.getAndroidDownloadLink())) {
                        return;
                    }
                    //点击开始下载更新apk
                    downloadApk(vd.getAndroidDownloadLink());
                }
            }.show(MainActivity.this.getString(R.string.new_version_detected), vd.getAndroidUpdateMessage(),R.string.download_background,
                    R.string.remind_me_later, vd.getAndroidForceUpdate(),false);//如果给传递值就强制下载:为"1"时强制更新，为""则不强制更新
        }
    }

    //下载更新apk
    private void downloadApk(final String url) {
        new EasyLocalTask<Void, File>() {
            @Override
            protected File doInBackground(Void... params) {
                File file = new File(BaseApplication.CACHE_ROOT_CACHE_DIR + File.separator + BaseConfig.APK_NAME);
                try {
                    notification();
                    DownloadUtil.downloadFile(url, file, new DownloadUtil.IDownloadCallback() {
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
                    install(MainActivity.this, result);
                }
            }
        }.execute();
    }

    //下载完apk后自动安装
    public void install(Context context, File file) {
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

    //更新通知
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

    //点击退出
    @Override
    public void onBackPressed() {
        if (System.currentTimeMillis() - lastBackKeyDownTime > 2000) { // 两秒钟内双击返回键关闭主界面
            //大于2000ms则认为是误操作，使用Toast进行提示
            Toast.makeText(this, R.string.double_tap_to_exit, Toast.LENGTH_SHORT).show();
            //并记录下本次点击“返回键”的时刻，以便下次进行判断
            lastBackKeyDownTime = System.currentTimeMillis();
        } else {
            //小于2000ms则认为是用户确实希望退出程序-调用System.exit()方法进行退出
            Process.killProcess(Process.myPid());
            super.onBackPressed();
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



}
