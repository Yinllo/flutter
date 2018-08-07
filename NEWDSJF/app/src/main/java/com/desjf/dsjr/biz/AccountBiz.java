package com.desjf.dsjr.biz;

import com.desjf.dsjr.biz.exception.BizFailure;
import com.desjf.dsjr.biz.exception.ZYException;
import com.desjf.dsjr.biz.task.SoapProcessor;
import com.desjf.dsjr.model.AccountModel;
import com.desjf.dsjr.model.HomeModel;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;

/**
 * Created by Administrator on 2017/7/25 0025.
 */

public class AccountBiz {
    //获取我的页面数据
    public static AccountModel getWodeMessage() throws BizFailure,
            ZYException {
        SoapProcessor ksoap2 = new SoapProcessor("Service", "myAccount", true);
        JsonElement element = ksoap2.request();
        Gson gson = new GsonBuilder().create();
        return gson.fromJson(element, AccountModel.class);
    }
    //实名认证
    public static String getRealName(String realName,String cardId, String bankCardNo, String bankNo, String provinceId, String cityId, String branchName,String mobile, String hidReqSn,String txtVerifycode
    ) throws BizFailure, ZYException {
        SoapProcessor ksoap2 = new SoapProcessor("Service", "realNameAuthNew", true);
        ksoap2.setProperty("realName", realName, SoapProcessor.PropertyType.TYPE_STRING);
        ksoap2.setProperty("cardId", cardId, SoapProcessor.PropertyType.TYPE_STRING);
        ksoap2.setProperty("bankCardNo", bankCardNo, SoapProcessor.PropertyType.TYPE_STRING);
        ksoap2.setProperty("bankNo", bankNo, SoapProcessor.PropertyType.TYPE_STRING);
        ksoap2.setProperty("provinceId", provinceId, SoapProcessor.PropertyType.TYPE_STRING);
        ksoap2.setProperty("cityId", cityId, SoapProcessor.PropertyType.TYPE_STRING);
        ksoap2.setProperty("branchName", branchName, SoapProcessor.PropertyType.TYPE_STRING);
        ksoap2.setProperty("mobile", mobile, SoapProcessor.PropertyType.TYPE_STRING);
        ksoap2.setProperty("hidReqSn", hidReqSn, SoapProcessor.PropertyType.TYPE_STRING);
        ksoap2.setProperty("txtVerifycode", txtVerifycode, SoapProcessor.PropertyType.TYPE_STRING);
        return ksoap2.request().getAsString();
    }
    //实名认证短信
    public static String getRealNameSms(String cardName, String cardId,String mobile,String bankCode,String accountNo) throws BizFailure, ZYException {
        SoapProcessor ksoap2 = new SoapProcessor("Service", "getTongLianMobileCode", false);
        ksoap2.setProperty("cardName", cardName, SoapProcessor.PropertyType.TYPE_STRING);
        ksoap2.setProperty("cardId", cardId, SoapProcessor.PropertyType.TYPE_STRING);
        ksoap2.setProperty("mobile", mobile, SoapProcessor.PropertyType.TYPE_STRING);
        ksoap2.setProperty("bankCode", bankCode, SoapProcessor.PropertyType.TYPE_STRING);
        ksoap2.setProperty("accountNo", accountNo, SoapProcessor.PropertyType.TYPE_STRING);
        return ksoap2.request().getAsString();
    }

    //第二次校验验证码
    public static String getChargeSmsSecond(String mobile, String operateType, String verifyCode) throws BizFailure, ZYException {
        SoapProcessor ksoap2 = new SoapProcessor("Service", "checkPhoneVerifyCode", false);
        ksoap2.setProperty("mobile", mobile, SoapProcessor.PropertyType.TYPE_STRING);
        ksoap2.setProperty("operateType", operateType, SoapProcessor.PropertyType.TYPE_STRING);
        ksoap2.setProperty("verifyCode", verifyCode, SoapProcessor.PropertyType.TYPE_STRING);
        return ksoap2.request().getAsString();
    }
    //验证实名认证短信
    public static String getRealNameSmsYz(String reqSn, String VerCode) throws BizFailure, ZYException {
        SoapProcessor ksoap2 = new SoapProcessor("Service", "checkShiMingVerCode", false);
        ksoap2.setProperty("reqSn", reqSn, SoapProcessor.PropertyType.TYPE_STRING);
        ksoap2.setProperty("VerCode", VerCode, SoapProcessor.PropertyType.TYPE_STRING);
        return ksoap2.request().getAsString();
    }
}
