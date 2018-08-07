package com.desjf.dsjr.biz;

import com.desjf.dsjr.biz.exception.BizFailure;
import com.desjf.dsjr.biz.exception.ZYException;
import com.desjf.dsjr.biz.task.SoapProcessor;

/**
 * Created by Administrator on 2017/7/24 0024.
 */

public class RegistBiz {

    //填写手机号
    public static String getCheckPhone(String phoneNumber) throws BizFailure, ZYException {

        SoapProcessor ksoap2 = new SoapProcessor("Service", "checkPhone", false);
        ksoap2.setProperty("phoneNumber", phoneNumber, SoapProcessor.PropertyType.TYPE_STRING);
        return ksoap2.request().getAsString();
    }
    //获取手机验证码
    public static String getVoicePhone(String mobile) throws BizFailure, ZYException {

        SoapProcessor ksoap2 = new SoapProcessor("Service", "getRegMobileCode", false);
        ksoap2.setProperty("mobile", mobile, SoapProcessor.PropertyType.TYPE_STRING);
        //return 的结果为：1
        return ksoap2.request().getAsString();

    }
    //验证手机验证码
    public static String checkVerifyCode(String phoneNumber,String verifyCode) throws BizFailure, ZYException {

        SoapProcessor ksoap2 = new SoapProcessor("Service", "checkVerifyCode", false);
        ksoap2.setProperty("phoneNumber", phoneNumber, SoapProcessor.PropertyType.TYPE_STRING);
        ksoap2.setProperty("verifyCode", verifyCode, SoapProcessor.PropertyType.TYPE_STRING);
        return ksoap2.request().getAsString();
    }
    //创建密码
    public static String regist(String phoneNumber,String passWord,String introducer,String tagEnd) throws BizFailure, ZYException {
        SoapProcessor ksoap2 = new SoapProcessor("Service", "registNew", false);
        ksoap2.setProperty("phoneNumber", phoneNumber, SoapProcessor.PropertyType.TYPE_STRING);
        ksoap2.setProperty("passWord", passWord, SoapProcessor.PropertyType.TYPE_STRING);
        ksoap2.setProperty("introducer", introducer, SoapProcessor.PropertyType.TYPE_STRING);
        ksoap2.setProperty("tagEnd", tagEnd, SoapProcessor.PropertyType.TYPE_STRING);
        return ksoap2.request().getAsString();
    }
}
