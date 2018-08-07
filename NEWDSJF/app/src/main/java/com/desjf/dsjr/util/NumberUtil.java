package com.desjf.dsjr.util;

/**
 * @Author YinL
 * @Date 2018/4/18 0018
 * @Describe
 */

public class NumberUtil{

    public static int getNumber(String money){
        int n=-1;
        //判断小数点后位数
            //正则表达式判断是否为小数
        boolean b;
           if(money.contains(".")){
             b=true;
           }else{
             b=false;
           }
            if(b){                           //是小数则判断位数
                //获取小数点的位置
                int bitPos=money.indexOf(".");
                //字符串总长度减去小数点位置，再减去1，就是小数位数
                int numOfBits=money.length()-bitPos-1;
                n=numOfBits;
            }else{                        //不是小数，给出错误提示信息
              n=0;
            }
        return n;
    }

}
