package com.desjf.dsjr.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * SharedPreferences工具类
 */
public final class PreferencesUtil {

    private PreferencesUtil() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    /**
     * 取得SharedPreference对象
     * @param con
     * @return
     */
    private static SharedPreferences pref(Context con) {
        return PreferenceManager.getDefaultSharedPreferences(con);
    }

    /**
     * 移除指定的key
     * @param con
     * @param key
     */
    public static void remove(Context con, String key) {
        try {
            pref(con).edit().remove(key).commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 判断SharedPreferences是否包含特定key
     * @param con
     * @param key
     * @return
     */
    public static boolean contains(Context con, String key) {
        try {
            return pref(con).contains(key);
        } catch(Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * String型数据的取得
     * @param con
     * @param key
     * @param defValue
     * @return
     */
    public static String getString(Context con, String key, String defValue) {
        try {
            return pref(con).getString(key, defValue);
        } catch (Exception e) {
            e.printStackTrace();
            return defValue;
        }
    }

    /**
     * boolean型数据的取得
     * @param con
     * @param key
     * @param defValue
     * @return
     */
    public static boolean getBoolean(Context con, String key, boolean defValue) {
        try{
            return pref(con).getBoolean(key, defValue);
        } catch (Exception e) {
            e.printStackTrace();
            return defValue;
        }
    }

    /**
     * int型数据的取得
     * @param con
     * @param key
     * @param defValue
     * @return
     */
    public static int getInt(Context con, String key, int defValue) {
        try{
            return pref(con).getInt(key, defValue);
        } catch (Exception e) {
            e.printStackTrace();
            return defValue;
        }
    }

    /**
     * long型数据的取得
     * @param con
     * @param key
     * @param defValue
     * @return
     */
    public static long getLong(Context con, String key, long defValue) {
        try{
            return pref(con).getLong(key, defValue);
        } catch (Exception e) {
            e.printStackTrace();
            return defValue;
        }
    }

    /**
     * float型数据的取得
     * @param con
     * @param key
     * @param defValue
     * @return
     */
    public static float getFloat(Context con, String key, float defValue) {
        try{
            return pref(con).getFloat(key, defValue);
        } catch (Exception e) {
            e.printStackTrace();
            return defValue;
        }
    }

    /**
     * String型数据的写入
     * @param con
     * @param key
     * @param value
     */
    public static void writeString(Context con, String key, String value) {
        try {
            pref(con).edit().putString(key, value).commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * boolean型数据的写入
     * @param con
     * @param key
     * @param value
     */
    public static void writeBoolean(Context con, String key, boolean value) {
        try {
            pref(con).edit().putBoolean(key, value).commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * int型数据的写入
     * @param con
     * @param key
     * @param value
     */
    public static void writeInt(Context con, String key, int value) {
        try {
            pref(con).edit().putInt(key, value).commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * long型数据的写入
     * @param con
     * @param key
     * @param value
     */
    public static void writeLong(Context con, String key, long value) {
        try {
            pref(con).edit().putLong(key, value).commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * float型数据的写入
     * @param con
     * @param key
     * @param value
     */
    public static void writeFloat(Context con, String key, float value) {
        try {
            pref(con).edit().putFloat(key, value).commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

