package com.desjf.dsjr.base;

import android.app.ProgressDialog;
import android.os.Environment;

public class DSJRConfig {
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
	public static final String APK_NAME = "DSJR.apk";

	/**
	 * 本地存储图片根目录名
	 */
	public static final String CACHE_PIC_ROOT_NAME = "德晟金服";

	public static final String ACTION_BASE_PREFIX = "dsjr.action.";



}
