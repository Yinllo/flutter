package com.desjf.dsjr.biz;

import com.desjf.dsjr.biz.exception.BizFailure;
import com.desjf.dsjr.biz.exception.ZYException;
import com.desjf.dsjr.biz.task.SoapProcessor;
import com.desjf.dsjr.model.ReChargeRecordModel;
import com.desjf.dsjr.model.RechargeSmsModel;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;

import java.util.List;

/**
 * Created by Administrator on 2017/9/7 0007.
 */

public class ChargeBiz {
    //通联支付实名付申请及短信发送
    public static RechargeSmsModel getRechargeSms(String reqSn,String rechargeAmount) throws BizFailure,
            ZYException {
        SoapProcessor ksoap2 = new SoapProcessor("Service", "allinpayRechargeSmss", true);
        ksoap2.setProperty("reqSn", reqSn, SoapProcessor.PropertyType.TYPE_STRING);
        ksoap2.setProperty("rechargeAmount", rechargeAmount, SoapProcessor.PropertyType.TYPE_STRING);
        JsonElement element = ksoap2.request();
        Gson gson = new GsonBuilder().create();
        return gson.fromJson(element, RechargeSmsModel.class);
    }

    public static RechargeSmsModel getRechargeSmsSecond(String money,String mobile) throws BizFailure,
            ZYException {
        SoapProcessor ksoap2 = new SoapProcessor("Service", "rechSendCode", false);
        ksoap2.setProperty("money", money, SoapProcessor.PropertyType.TYPE_STRING);
        ksoap2.setProperty("mobile", mobile, SoapProcessor.PropertyType.TYPE_STRING);
        JsonElement element = ksoap2.request();
        Gson gson = new GsonBuilder().create();
        return gson.fromJson(element, RechargeSmsModel.class);
    }

    public static String getCheckCodeSecond(String money,String mobile) throws BizFailure, ZYException {

        SoapProcessor ksoap2 = new SoapProcessor("Service", "rechSendCode", false);
        ksoap2.setProperty("money", money, SoapProcessor.PropertyType.TYPE_STRING);
        ksoap2.setProperty("mobile", mobile, SoapProcessor.PropertyType.TYPE_STRING);
        return ksoap2.request().getAsString();
    }

    public static RechargeSmsModel getRealNameSmsSecond(String mobile, String bankCode, String accountNo, String cardName, String cardId) throws BizFailure,
            ZYException {
        SoapProcessor ksoap2 = new SoapProcessor("Service", "lnkSendVerCode", false);
        ksoap2.setProperty("mobile", mobile, SoapProcessor.PropertyType.TYPE_STRING);
        ksoap2.setProperty("bankCode", bankCode, SoapProcessor.PropertyType.TYPE_STRING);
        ksoap2.setProperty("accountNo", accountNo, SoapProcessor.PropertyType.TYPE_STRING);
        ksoap2.setProperty("cardName", cardName, SoapProcessor.PropertyType.TYPE_STRING);
        ksoap2.setProperty("cardId", cardId, SoapProcessor.PropertyType.TYPE_STRING);
        JsonElement element = ksoap2.request();
        Gson gson = new GsonBuilder().create();
        return gson.fromJson(element, RechargeSmsModel.class);
    }
    //通联支付 四要素校验
    public static String getCheckCode(String vercode,String reqSn) throws BizFailure, ZYException {

        SoapProcessor ksoap2 = new SoapProcessor("Service", "allinpayCheckCode", true);
        ksoap2.setProperty("vercode", vercode, SoapProcessor.PropertyType.TYPE_STRING);
        ksoap2.setProperty("reqSn", reqSn, SoapProcessor.PropertyType.TYPE_STRING);
        return ksoap2.request().getAsString();
    }

    //充值
    public static String getRecharge(String rechargeAmount) throws BizFailure, ZYException {
        SoapProcessor ksoap2 = new SoapProcessor("Service", "allinpayRechargeNew", true);
        ksoap2.setProperty("rechargeAmount", rechargeAmount, SoapProcessor.PropertyType.TYPE_STRING);
        return ksoap2.request().getAsString();
    }

    //充值记录
    public static List<ReChargeRecordModel> getRechargeRecord( String firstIdx, String maxCount) throws BizFailure,
            ZYException {
        SoapProcessor ksoap2 = new SoapProcessor("Service", "rechargeRecord", true);
        ksoap2.setProperty("firstIdx", firstIdx, SoapProcessor.PropertyType.TYPE_STRING);
        ksoap2.setProperty("maxCount", maxCount, SoapProcessor.PropertyType.TYPE_STRING);
        JsonElement element = ksoap2.request();
        Gson gson = new GsonBuilder().create();
        TypeToken<List<ReChargeRecordModel>> tt = new TypeToken<List<ReChargeRecordModel>>() {
        };
        List<ReChargeRecordModel> fs = gson.fromJson(element, tt.getType());
        return fs;
    }
}
