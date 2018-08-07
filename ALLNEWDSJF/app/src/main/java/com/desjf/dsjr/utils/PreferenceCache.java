package com.desjf.dsjr.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.desjf.dsjr.base.BaseApplication;

/**
 * 缓存应用程序的配置信息和必要的业务数据
 * Created by YinL on 2018/1/15 0015.
 */

public class PreferenceCache {

    /**
     * 是否显示欢迎界面,true表示显示，false表示不显示（只有第一次启动app时显示欢迎页面）
     */
    public static final String SHOW_GUIDE = "show_guide";//引导页
    public static final String PF_TOKEN = "token";    //用户token：记录用户是否登录
    public static final String PF_POSITION_ONE = "p_one"; //我要出借下拉框位置
    public static final String PF_POSITION_TWO = "p_two";
    public static final String PF_AUTO_INVESTMENT = "auto_investment"; // 投资协议

    /**
     * 获得SharedPreferences
     */
    private static SharedPreferences getSharedPreferences() {
        BaseApplication application= (BaseApplication) BaseApplication.getAppContext();
        return application.getSharedPreferences("DSJF",Context.MODE_PRIVATE);
    }

    //保存用户token
    public static void putToken(String token) {
        SharedPreferences preferences = getSharedPreferences();
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(PF_TOKEN, token);
        editor.commit();
    }
    //获取用户token
    public static String getToken() {
        return getSharedPreferences().getString(PF_TOKEN, "");
    }

    //保存当前是否已经启动了欢迎页
    public static void setGuideBoolean(boolean value) {
        // 得到SharedPreferences
        SharedPreferences preferences = getSharedPreferences();
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean(SHOW_GUIDE, value);
        editor.commit();
    }
    public static boolean getGuideBoolean() {
        return getSharedPreferences().getBoolean(SHOW_GUIDE, false);
    }

    //投资界面协议选择
    public static void putAutoInvestment(boolean isAutoInvestment){
        SharedPreferences preferences = getSharedPreferences();
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean(PF_AUTO_INVESTMENT, isAutoInvestment);
        editor.commit();
    }
    public static boolean isAutoInvestment(){
        return getSharedPreferences().getBoolean(PF_AUTO_INVESTMENT, false);
    }







    /**
     * 投资列表选择界面  筛选方式存储
     * @param position
     */
    public static void putPfPositionOne(String position) {
        SharedPreferences preferences = getSharedPreferences();
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(PF_POSITION_ONE, position);
        editor.commit();
    }
    public static String getPfPositionOne() {
        return getSharedPreferences().getString(PF_POSITION_ONE,"");
    }

    public static void putPfPositionTwo(String position) {
        SharedPreferences preferences = getSharedPreferences();
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(PF_POSITION_TWO, position);
        editor.commit();
    }
    public static String getPfPositionTwo() {
        return getSharedPreferences().getString(PF_POSITION_TWO,"");
    }
}
