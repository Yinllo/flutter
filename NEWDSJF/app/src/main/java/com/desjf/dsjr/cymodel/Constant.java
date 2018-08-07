package com.desjf.dsjr.cymodel;

/**
 * Created by lenovo on 2017/5/17.
 */

public class Constant {
    public static final String KEY_INTENT_MODIFY_BANK_INFO = "key_intent_modify_bank_info";//更改银行卡信息
    public static final String KEY_BUNDLE_MODIFY_BANK_INFO = "key_bundle_modify_bank_info";//更改银行卡信息

    public static final int REQUEST_PHOTO = 10;//相机请求码
    public static final int REQUEST_ALBUM = 11;//相册请求码
    public static final int PICTURE_CUT = 12;//裁剪请求码
    //?????????????????????????????????????

    public static final int REQUEST_CAMERA_2_SCAN = 15;//扫描二维码相机权限请求码
    public static final int REQUEST_CAMERA_PEMISSION = 100;//相机权限请求码
    public static final int REQUEST_CAMERA_SCAN_PEMISSION = 104;//扫描二维码相机权限请求码
    public static final int PHOTO_WRITE_EXTERNAL_PEMISSION = 101;//相机存储请求码
    public static final int ALBUM_WRITE_EXTERNAL_PEMISSION = 102;//相机存储请求码
    public static final int CRASH_WRITE_EXTERNAL_PEMISSION = 103;//相机存储请求码
    public static final int WHAT_PHOTO_ALBUM_CUT_COMPRESS = 0;//相机相册裁剪后压缩 handler
}
