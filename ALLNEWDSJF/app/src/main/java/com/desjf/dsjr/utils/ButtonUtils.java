package com.desjf.dsjr.utils;

/**
 * @Author YinL
 * @Date 2018/2/9 0009
 * @Describe   防止按钮重复点击
 */

public class ButtonUtils {
    // 两次点击按钮之间的点击间隔不能少于3000毫秒
    private static final int MIN_CLICK_DELAY_TIME = 3000;
    private static long lastClickTime;

    public static boolean isFastClick() {
        boolean flag = false;
        long curClickTime = System.currentTimeMillis();
        if ((curClickTime - lastClickTime) >= MIN_CLICK_DELAY_TIME) {
            flag = true;
        }
        lastClickTime = curClickTime;
        return flag;
    }


}

