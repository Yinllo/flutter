package com.desjf.dsjr.utils;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.Uri;
import android.net.wifi.WifiManager;
import android.provider.Settings.Secure;
import android.telephony.TelephonyManager;

import java.io.File;


public class DeviceUtil {
	public static String getDeviceId(Context context){
		return getTelephonyManager(context).getDeviceId();
	}
	
	public static String getMacAddress(Context context){
		return getWifiManager(context).getConnectionInfo().getMacAddress();
	}
	
	public static String getAndroidId(Context context){
		return Secure.getString(context.getContentResolver(), Secure.ANDROID_ID);
	}
	
	public static String getSimSerialNumber(Context context){
		return getTelephonyManager(context).getSimSerialNumber();
	}
	
	public static String getPhoneSubscriberId(Context context){
		return getTelephonyManager(context).getSubscriberId();
	}
	
	public static String getPhoneNumber(Context context){
		return getTelephonyManager(context).getLine1Number();
	}
	
	private static TelephonyManager getTelephonyManager(Context context){
		return (TelephonyManager)context.getSystemService(Context.TELEPHONY_SERVICE);
	}
	
	private static WifiManager getWifiManager(Context context){
		return (WifiManager)context.getSystemService(Context.WIFI_SERVICE);
	}
	
	public static void install(Context context, File file){
		Intent intent = new Intent(Intent.ACTION_VIEW);
		intent.setDataAndType(Uri.fromFile(file), "application/vnd.android.package-archive");
		context.startActivity(intent);
	}
	
	public static String getVesionName(Context context){
		PackageInfo info;
		try {
			info = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
		} catch (NameNotFoundException e) {
			throw new RuntimeException(e);
		}
		return info.versionName;
	}
	
	public static int getVersionCode(Context context){
		PackageInfo info;
		try {
			info = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
		} catch (NameNotFoundException e) {
			throw new RuntimeException(e);
		}
		return info.versionCode;
	}
	
}
