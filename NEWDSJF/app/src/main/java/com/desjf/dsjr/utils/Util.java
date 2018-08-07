package com.desjf.dsjr.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;
import android.util.DisplayMetrics;

import com.desjf.dsjr.activity.LoginActivity;
import com.desjf.dsjr.activity.MainActivity;
import com.desjf.dsjr.base.ExtraConfig;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Util {


    public static int getDisplayWith(Activity context) {
        DisplayMetrics metric = new DisplayMetrics();
        context.getWindowManager().getDefaultDisplay().getMetrics(metric);
        int width = metric.widthPixels; // 屏幕宽度（像素）
        // int height = metric.heightPixels; // 屏幕高度（像素）
        // float density = metric.density; // 屏幕密度（0.75 / 1.0 / 1.5）
        // int densityDpi = metric.densityDpi; // 屏幕密度DPI（120 / 160 / 240）

        return width;
    }

    public static int Dp2Px(Context context, float dp) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dp * scale + 0.5f);
    }

    public static int Px2Dp(Context context, float px) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (px / scale + 0.5f);
    }


    /**
     * 获取SpannableString 最后一个缩小
     *
     * @return
     */
    public static SpannableString getGlobalSpanString(float textSize,
                                                      String content) {
        int stringLength = content.length();
        SpannableString ss = new SpannableString(content);
        if (stringLength > 0) {
            ss.setSpan(new StyleSpan(Typeface.BOLD), 0, stringLength - 1,
                    Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            ss.setSpan(new AbsoluteSizeSpan((int) (textSize * 0.75)),
                    stringLength - 1, stringLength,
                    Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            return ss;
        } else {
            return new SpannableString("");
        }
    }

    /**
     * 获取SpannableString 最后2个缩小
     *
     * @return
     */
    public static SpannableString getGlobalSpanStringLast2(float textSize,
                                                           String content) {
        int stringLength = content.length();
        SpannableString ss = new SpannableString(content);
        if (stringLength > 0) {
            ss.setSpan(new StyleSpan(Typeface.BOLD), 0, stringLength - 2,
                    Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            ss.setSpan(new AbsoluteSizeSpan((int) (textSize * 0.75)),
                    stringLength - 2, stringLength,
                    Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            return ss;
        } else {
            return new SpannableString("");
        }
    }

    /**
     * 获取SpannableString 通过 From(int)分割字符串
     * @return
     */
    public static SpannableString getGlobalSpanString(float textSize,
                                                      String content, int from) {
        int stringLength = content.length();
        SpannableString ss = new SpannableString(content);
        if (stringLength > 0) {
            ss.setSpan(new StyleSpan(Typeface.BOLD), 0, from,
                    Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            ss.setSpan(new AbsoluteSizeSpan((int) (textSize * 0.75)), from,
                    stringLength, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            return ss;
        } else {
            return new SpannableString("");
        }
    }

    /**
     * 获取SpannableString 最后一个字符 缩小 改变颜色
     *
     * @param color  颜色
     * @return
     */
    public static SpannableString getGlobalSpanStringWithColor(float textSize,
                                                               String content, int color) {
        int stringLength = content.length();
        SpannableString ss = new SpannableString(content);
        if (stringLength > 0) {
            ss.setSpan(new StyleSpan(Typeface.BOLD), 0, stringLength - 1,
                    Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            ss.setSpan(new ForegroundColorSpan(color), stringLength - 1,
                    stringLength, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            ss.setSpan(new AbsoluteSizeSpan((int) (textSize * 0.75)),
                    stringLength - 1, stringLength,
                    Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            return ss;
        } else {
            return new SpannableString("");
        }
    }

    /**
     * 获取SpannableString
     *
     * @param from
     * @param color
     * @return
     */
    public static SpannableString getGlobalSpanStringWithColor(float textSize,
                                                               String content, int from, int color) {
        int stringLength = content.length();
        SpannableString ss = new SpannableString(content);
        if (stringLength > 0) {
            ss.setSpan(new StyleSpan(Typeface.BOLD), 0, stringLength - 1,
                    Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            ss.setSpan(new ForegroundColorSpan(color), stringLength - 1,
                    stringLength, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            ss.setSpan(new AbsoluteSizeSpan((int) (textSize * 0.75)),
                    stringLength - 1, stringLength,
                    Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            return ss;
        } else {
            return new SpannableString("");
        }
    }


    /**
     * 半角转为全角
     *
     * @param input
     * @return
     */


    public static String ToSBC(String input) {
        char c[] = input.toCharArray();
        for (int i = 0; i < c.length; i++) {
            if (c[i] == ' ') {
                c[i] = '\u3000';
            } else if (c[i] < '\177') {
                c[i] = (char) (c[i] + 65248);
            }
        }
        return new String(c);
    }


    /**
     * 去除特殊字符或将所有中文标号替换为英文标号
     *
     * @param str
     * @return
     */

    public static String stringFilter(String str) {
        str = str.replaceAll("【", "[").replaceAll("】", "]")
                .replaceAll("！", "!").replaceAll("：", ":");// 替换中文标号
       /* String regEx = "[『』]"; // 清除掉特殊字符
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(str);
        return m.replaceAll("").trim();*/
        return str;
    }


    public static void gotoMain(Activity currentActiviy) {
        Intent it = new Intent(currentActiviy, MainActivity.class);
        currentActiviy.startActivity(it);
        currentActiviy.finish();
    }

    public static void showLogin(Activity currentActiviy) {
        Intent it = new Intent(currentActiviy, LoginActivity.class);
        //if (currentActiviy instanceof MainActivity ){//|| currentActiviy instanceof ActivityWithdraw){
        it.putExtra(ExtraConfig.IntentExtraKey.LOGIN_FROM_MAIN, true);
        //}
        currentActiviy.startActivity(it);
    }

    /**
     * 密码登录规则
     *
     * @param userid
     * @return
     */
    public static boolean checkPwd(String userid) {
        boolean flag = false;
        try {
            //String check = "^(\\d+[A-Za-z]+[A-Za-z0-9]*)|([A-Za-z]+\\d+[A-Za-z0-9]*)$";
            String check = "^(?![0-9]+$)(?![a-zA-Z]+$)(?![`~!@#$%^&*()_+=\\\\|{}\\[\\]'\":;,.<>/?-]+$)[`~!@#$%^&*()_+=\\\\|{}\\[\\]'\":;,.<>/?0-9a-zA-Z-]{6,20}$";
            Pattern regex = Pattern.compile(check);
            Matcher matcher = regex.matcher(userid);
            flag = matcher.matches();
        } catch (Exception e) {
            flag = false;
        }

        return flag;
    }

}
