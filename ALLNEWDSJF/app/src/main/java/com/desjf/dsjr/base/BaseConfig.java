package com.desjf.dsjr.base;

import android.os.Environment;

/**
 * @Author YinL
 * @Date 2018/1/16 0016
 * @Describe APP基础配置信息
 */

public class BaseConfig {
    //    public static final String WS_BASE_DOMAIN="http://192.168.1.235:8080/";
    //测试路径
    public static final String SERVER_IP="https://t.dsp2p.com/";

    //    正式路径
//        public static final String SERVER_IP="http://www.dsp2p.com/";

    //http://www.dsp2p.com/iloanWebService/services/Service?wsdl
    public static final String BASE_IMG = SERVER_IP +"iloanWebService/images/ic_launcher.png";
    //public static final String WS_BASE_URL = WS_BASE_DOMAIN + "E146_WebService/services/";
    public static final String WS_BASE_URL = SERVER_IP + "iloanWebService/services/";
    public static final String VERSION_DETECTION_URL = SERVER_IP + "iloanWebService/version.json";
    public static final String NOTICE_URL = SERVER_IP +"iloanWebService/content.html?newsId=";
    // 命名空间
    public static final String WS_NAME_SPACE = "http://impl.service.iloan.yingCanTechnology.com";
    public static final String fragment_investMENT_TEXT = SERVER_IP + "iloanWebService/borrowcontract_moban.html";
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
    /**
     *  记录从哪儿跳转至哪儿（例如:MainActivity中利用此来判断进入哪个Fragment）
     *   BaseConfig.WHERE == 1 跳转至首页
     *   BaseConfig.WHERE == 2 跳转至个人主页
     */
    public static int WHERE=0;
    /**
     *  记录跳转到投资回款界面（InvestmentReturnActivity）的哪个Fragment
     *   BaseConfig.FROM_IR == 1 跳转至已回款
     *   BaseConfig.FROM_IR == 2 跳转至已投资   以此类推
     */
    public static int FROM_PO=0;
    /**
     *  记录跳转到项目详情界面（ProjectDetailsActivity）的哪个Fragment
     *   BaseConfig.FROM_PO == 1 跳转至借款详情
     *   BaseConfig.FROM_PO == 2 跳转至证件合同   以此类推
     */
    //跳转至MyMainFragment中的某一个Fragment
    public static int TO_MY=0;
    //验证我的界面切换
    public static int NoLogin=1;

    public static int GOFRESH=0;
    public static int WHERE_BACK=0;
    public static int TYPE=0;

}
