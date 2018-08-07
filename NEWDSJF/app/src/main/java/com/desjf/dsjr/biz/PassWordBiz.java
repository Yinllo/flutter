package com.desjf.dsjr.biz;

import com.desjf.dsjr.biz.exception.BizFailure;
import com.desjf.dsjr.biz.exception.ZYException;
import com.desjf.dsjr.biz.task.SoapProcessor;

/**
 * Created by Administrator on 2017/7/26 0026.
 */

public class PassWordBiz {
    //修改登录密码
    public static String reSetLoginPsw(String oldPassword,String newPassword) throws BizFailure, ZYException {
        SoapProcessor ksoap2 = new SoapProcessor("Service", "changeLoginPassword", true);
        ksoap2.setProperty("oldPassword", oldPassword, SoapProcessor.PropertyType.TYPE_STRING);
        ksoap2.setProperty("newPassword", newPassword, SoapProcessor.PropertyType.TYPE_STRING);
        return ksoap2.request().getAsString();
    }

    //修改交易密码
    public static String reSetJyPsw(String oldPassword,String newPassword) throws BizFailure, ZYException {
        SoapProcessor ksoap2 = new SoapProcessor("Service", "changePayPassword", true);
        ksoap2.setProperty("oldPassword", oldPassword, SoapProcessor.PropertyType.TYPE_STRING);
        ksoap2.setProperty("newPassword", newPassword, SoapProcessor.PropertyType.TYPE_STRING);
        return ksoap2.request().getAsString();
    }

    //找回交易密码
    public static String getPhoneVerifyCode(String mobile,String operateType) throws BizFailure, ZYException {
        SoapProcessor ksoap2 = new SoapProcessor("Service", "getPhoneVerifyCode", false);
        ksoap2.setProperty("mobile", mobile, SoapProcessor.PropertyType.TYPE_STRING);
        ksoap2.setProperty("operateType", operateType, SoapProcessor.PropertyType.TYPE_STRING);
        return ksoap2.request().getAsString();
    }
    //验证密码
    public static String getCheckCode(String mobile,String operateType,String verifyCode) throws BizFailure, ZYException {
        SoapProcessor ksoap2 = new SoapProcessor("Service", "checkPhoneVerifyCode", false);
        ksoap2.setProperty("mobile", mobile, SoapProcessor.PropertyType.TYPE_STRING);
        ksoap2.setProperty("operateType", operateType, SoapProcessor.PropertyType.TYPE_STRING);
        ksoap2.setProperty("verifyCode", verifyCode, SoapProcessor.PropertyType.TYPE_STRING);
        return ksoap2.request().getAsString();
    }

    //找回交易密码
    public static String findPayPassword(String newPassword) throws BizFailure, ZYException {
        SoapProcessor ksoap2 = new SoapProcessor("Service", "findPayPassword", true);
        ksoap2.setProperty("newPassword", newPassword, SoapProcessor.PropertyType.TYPE_STRING);
        return ksoap2.request().getAsString();
    }
    //找回登录密码
    public static String findLoginPsw(String userId,String newPassword,String pwdType) throws BizFailure, ZYException {
        SoapProcessor ksoap2 = new SoapProcessor("Service", "findPassword", false);
        ksoap2.setProperty("userId", userId, SoapProcessor.PropertyType.TYPE_STRING);
        ksoap2.setProperty("newPassword", newPassword, SoapProcessor.PropertyType.TYPE_STRING);
        ksoap2.setProperty("pwdType", pwdType, SoapProcessor.PropertyType.TYPE_STRING);
        return ksoap2.request().getAsString();
    }
}
