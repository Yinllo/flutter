package com.desjf.dsjr.utils;

/**
 * @Author YinL
 * @Date 2018/6/15 0015
 * @Describe  手机号码格式，登录密码格式等各种格式验证
 */

public class FormatUtils {

    //验证手机号码格式是否正确
   public   static boolean phoneCheck(String phone){
       //"[1]"代表第1位为数字1，"[358]"代表第二位可以为3、5、8中的一个，"\\d{9}"代表后面是可以是0～9的数字，有9位。
       String telRegex = "[1][3456789]\\d{9}";
       return phone.matches(telRegex);
   }

    //验证登录密码设置是否正确  8-20位数字+字母
    //判断输入的密码是否为纯数字或者纯字母
    public static boolean checkPwd(String password){
        boolean flag=false;

        String nreg="^\\d+$";//数字
        boolean n=password.matches(nreg);
        String reg="^[a-zA-Z]+$";//字母
        boolean s=password.matches(reg);
        boolean length=password.length()<8;
        boolean lengths=password.length()>20;
        if(n||s||length||lengths){
            flag=false;
        }else {
            flag=true;
        }
        return flag;
    }

}
