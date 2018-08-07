package com.desjf.dsjr.config;

import android.os.Environment;

/**
 * Created by Administrator on 2017/7/24 0024.
 */

public class DsjrConfig {
//    public static final String WS_BASE_DOMAIN="http://192.168.1.235:8080/";

     //测试路径
//    public static final String WS_BASE_DOMAIN="http://t.dsp2p.com/";

    //    正式路径
    public static final String WS_BASE_DOMAIN="https://www.dsp2p.com/";

    //银行界面测试路径
//    public static final String BANK_BASE_DOMAIN="http://mtest.cbhb.com.cn/pmobile227/static/index.html";

    //银行界面正式路劲             //还需修改 请求公钥的接口     util包中的Constans
    public static final String BANK_BASE_DOMAIN="https://m.cbhb.com.cn/pmobile/static/index.html";


    //http://www.dsp2p.com/iloanWebService/services/Service?wsdl
    public static final String BASE_IMG = WS_BASE_DOMAIN+"iloanWebService/images/ic_launcher.png";
//    public static final String WS_BASE_URL = WS_BASE_DOMAIN + "E146_WebService/services/";
    public static final String WS_BASE_URL = WS_BASE_DOMAIN + "iloanWebService/services/";
    public static final String VERSION_DETECTION_URL = WS_BASE_DOMAIN + "iloanWebService/version.json";
    public static final String NOTICE_URL = WS_BASE_DOMAIN+"iloanWebService/content.html?newsId=";
    // 命名空间
    public static final String WS_NAME_SPACE = "http://impl.service.iloan.yingCanTechnology.com";
    public static final String fragment_investMENT_TEXT = WS_BASE_DOMAIN + "iloanWebService/borrowcontract_moban.html";
    /**
     * 本地存储的根路径
     */
    public static final String EXT_STORAGE_ROOT = Environment.getExternalStorageDirectory().getAbsolutePath();

    /**
     * 本地存储根目录名
     */
    public static final String CACHE_ROOT_NAME = "DSJR";

    /**
     * 本地存储缓存根目录名
     */
    public static final String CACHE_ROOT_CACHE_NAME = "cache";

    /**
     * apk安装包名称
     */
    public static final String APK_NAME = "DSJR20.apk";

    /**
     * 本地存储图片根目录名
     */
    public static final String CACHE_PIC_ROOT_NAME = "德晟金服";
    public static final String ACTION_BASE_PREFIX = "DSJR.action.";
    //跳转至MainActivity中的某一个Fragment
    public static int WHERE=0;
    //跳转至MyMainFragment中的某一个Fragment
    public static int TO_MY=0;

    public static int GOFRESH=0;
    public static int WHERE_BACK=0;
    public static int TYPE=0;

    /**
     * 定义跳转到的页面  跳转到项目详情的某一项
     * 1代表产品详情、2代表证件合同、3代表风控流程、4代表投资记录
     */
    public static int FROM_PD=0;


}
