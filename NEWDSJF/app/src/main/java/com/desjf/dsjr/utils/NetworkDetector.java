package com.desjf.dsjr.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class NetworkDetector {

	/**
	 * 设备网络是否处于可用状态
	 * @param context
	 * @return
	 */
	public static boolean isNetworkAvailable(Context context) {
		ConnectivityManager manager = (ConnectivityManager) context
				.getApplicationContext().getSystemService(
						Context.CONNECTIVITY_SERVICE);
		if (manager == null) {
			return false;
		}
		
		NetworkInfo networkInfo = manager.getActiveNetworkInfo();
		if (networkInfo == null || !networkInfo.isAvailable()) {
			return false;
		}
		return true;
	}
	
	/**
	 * 设备wifi网络是否处于可用状态
	 * @param context
	 * @return
	 */
	public static boolean isWifiAvailable(Context context){
		ConnectivityManager manager = (ConnectivityManager) context
				.getApplicationContext().getSystemService(
						Context.CONNECTIVITY_SERVICE);
		if (manager == null) {
			return false;
		}
		
		NetworkInfo networkInfo = manager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
		if (networkInfo == null || !networkInfo.isAvailable()) {
			return false;
		}
		
		return true;
	}
	
	/**
	 * 设备数据网络是否可用
	 * @param context
	 * @return
	 */
	public static boolean isMobileAvailable(Context context){
		ConnectivityManager manager = (ConnectivityManager) context
				.getApplicationContext().getSystemService(
						Context.CONNECTIVITY_SERVICE);
		if (manager == null) {
			return false;
		}
		
		NetworkInfo networkInfo = manager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
		if (networkInfo == null || !networkInfo.isAvailable()) {
			return false;
		}
		
		return true;
	}
	
	/**
	 * 取得当前网络连接类型
	 * @param context
	 * @return
	 */
	public static int getConnectionType(Context context){
		ConnectivityManager manager = (ConnectivityManager) context
				.getApplicationContext().getSystemService(
						Context.CONNECTIVITY_SERVICE);
		if (manager == null) {
			return -1;
		}
		
		NetworkInfo networkInfo = manager.getActiveNetworkInfo();
		if (networkInfo == null || networkInfo.isAvailable()) {
			return networkInfo.getType();
		}
		
		return -1;
	}
}
