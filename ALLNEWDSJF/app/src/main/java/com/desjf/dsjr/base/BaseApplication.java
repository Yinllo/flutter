package com.desjf.dsjr.base;

import android.app.Application;
import android.content.Context;

import com.desjf.dsjr.R;
import com.desjf.dsjr.utils.Utils;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.umeng.analytics.MobclickAgent;
import com.umeng.commonsdk.UMConfigure;

import java.io.File;

import cn.sharesdk.framework.ShareSDK;

/**
 * Created by YinL on 2018/1/13 0013.
 */

public class BaseApplication extends Application {

    private static final String CACHE_ROOT_DIR = BaseConfig.EXT_STORAGE_ROOT + File.separator + BaseConfig.CACHE_ROOT_NAME;
    public static final String CACHE_PIC_ROOT_DIR = CACHE_ROOT_DIR + File.separator + BaseConfig.CACHE_PIC_ROOT_NAME;
    public static final String CACHE_ROOT_CACHE_DIR = CACHE_ROOT_DIR + File.separator + BaseConfig.CACHE_ROOT_CACHE_NAME;

    public static Context mAppContext;
    public static int forgetGesturePwd = 0;
    public static int globalIndex = 0;

    @Override
    public void onCreate() {
        super.onCreate();

        Utils.init(this);

        mAppContext = getApplicationContext();
        buildCacheDir();
        initImageLoader(mAppContext);
        //初始化SharedSDK
        ShareSDK.initSDK(getApplicationContext());

        //友盟统计,用户行为分析
        MobclickAgent.setScenarioType(getApplicationContext(), MobclickAgent.EScenarioType.E_UM_NORMAL);
        UMConfigure.init(getApplicationContext(), null, null,  UMConfigure.DEVICE_TYPE_PHONE, null);

        
    }

    public static void buildCacheDir() {
        File rootDir = new File(CACHE_ROOT_DIR);
        if (!rootDir.exists()) {
            rootDir.mkdir();
        }

        File cacheDir = new File(CACHE_ROOT_CACHE_DIR);
        if (!cacheDir.exists()) {
            cacheDir.mkdir();
        }

        File picRootDir = new File(CACHE_PIC_ROOT_DIR);
        if (!picRootDir.exists()) {
            picRootDir.mkdir();
        }
    }

    public static Context getAppContext() {
        return mAppContext;
    }

    public static void initImageLoader(Context context) {
        DisplayImageOptions defaultOptions = new DisplayImageOptions
                .Builder()
                .showImageForEmptyUri(R.mipmap.ic_launcher)
                .showImageOnFail(R.mipmap.ic_launcher)
                .cacheInMemory(true)
                .cacheOnDisc(true)
                .build();
        ImageLoaderConfiguration config = new ImageLoaderConfiguration
                .Builder(context)
                .defaultDisplayImageOptions(defaultOptions)
                .discCacheSize(50 * 1024 * 1024)//
                .discCacheFileCount(100)//缓存一百张图片
                .writeDebugLogs()
                .build();
        ImageLoader.getInstance().init(config);
    }
}

