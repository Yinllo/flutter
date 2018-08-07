package com.desjf.dsjr.utils;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Author YinL
 * @Date 2018/3/26 0026
 * @Describe  时间戳转换成时间
 */

public class DataUtil {
    /**
     * 将时间戳转换为时间
     * @param s
     * @return
     */
    public static String stampToDate(String s) {
        //        String res;
        //        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //        long lt = new Long(s);
        //        Date date = new Date(lt);
        //        res = simpleDateFormat.format(date);
        //        return res;
        //        String timeString = null;
        //        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //        long  l = Long.valueOf(s);
        //        timeString = sdf.format(new Date(l));//单位秒
        //        return timeString;
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(Long.parseLong(s));
        SimpleDateFormat fmat = new SimpleDateFormat("yy-MM-dd HH:mm");
        String time = fmat.format(calendar.getTime());
        return time;

    }

    public static String stampToDates(String s) {
        //        String res;
        //        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //        long lt = new Long(s);
        //        Date date = new Date(lt);
        //        res = simpleDateFormat.format(date);
        //        return res;
        //        String timeString = null;
        //        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //        long  l = Long.valueOf(s);
        //        timeString = sdf.format(new Date(l));//单位秒
        //        return timeString;
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(Long.parseLong(s));
        SimpleDateFormat fmat = new SimpleDateFormat("yy-MM-dd HH:mm:ss");
        String time = fmat.format(calendar.getTime());
        return time;

    }

    public static String ToDate(String s) {
        //        String res;
        //        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //        long lt = new Long(s);
        //        Date date = new Date(lt);
        //        res = simpleDateFormat.format(date);
        //        return res;
        //        String timeString = null;
        //        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //        long  l = Long.valueOf(s);
        //        timeString = sdf.format(new Date(l));//单位秒
        //        return timeString;
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(Long.parseLong(s));
        SimpleDateFormat fmat = new SimpleDateFormat("yy-MM-dd");
        String time = fmat.format(calendar.getTime());
        return time;

    }

    /**
     * 将时间转换成周几
     * @param time
     * @return
     */

    public static String getWeek(String time) {
        String Week = "";
        SimpleDateFormat format = new SimpleDateFormat("yy-MM-dd");
        Calendar c = Calendar.getInstance();
        try {
            c.setTime(format.parse(time));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        int wek=c.get(Calendar.DAY_OF_WEEK);

        if (wek == 1) {
            Week += "周日";
        }
        if (wek == 2) {
            Week += "周一";
        }
        if (wek == 3) {
            Week += "周二";
        }
        if (wek == 4) {
            Week += "周三";
        }
        if (wek == 5) {
            Week += "周四";
        }
        if (wek == 6) {
            Week += "周五";
        }
        if (wek == 7) {
            Week += "周六";
        }
        return Week;
    }


    /**
     * 将钱 转换成每隔三位加一个，
     */
    public static String to(String money){
        Pattern p = Pattern.compile("(?<=\\d)(?=(\\d\\d\\d)+$)");
        Matcher m = p.matcher(money);
        return m.replaceAll(",");
    }

    public static String toMoney(float data) {
        DecimalFormat df = new DecimalFormat("##,###,###,###,##0.00");
        return df.format(data);
    }
    public static String toMoneys(BigDecimal data) {
        DecimalFormat df = new DecimalFormat("##,###,###,###,##0.00");
        return df.format(data);
    }
}
