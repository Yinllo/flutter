package com.desjf.dsjr.biz;

import com.desjf.dsjr.biz.exception.BizFailure;
import com.desjf.dsjr.biz.exception.ZYException;
import com.desjf.dsjr.biz.task.SoapProcessor;
import com.desjf.dsjr.model.AllCouponAndInterestModle;
import com.desjf.dsjr.model.AllCouponModel;
import com.desjf.dsjr.model.AllInterestModel;
import com.desjf.dsjr.model.BestProductModel;
import com.desjf.dsjr.model.CouponListForTenderModel;
import com.desjf.dsjr.model.SongNameModel;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;

import java.util.List;

/**
 * Created by Administrator on 2017/7/27 0027.
 */

public class AllCouponBiz {

    //获取当前所有可用的红包 和 加息券
    public static List<AllCouponAndInterestModle> getCouponAndInterest(String period,String firstIdx, String maxCount, String oidFinanceProductsTypeId) throws BizFailure,
            ZYException {
        SoapProcessor ksoap2 = new SoapProcessor("Service", "jsonUnusedCouponAndInterestRate", true);
        ksoap2.setProperty("period", period, SoapProcessor.PropertyType.TYPE_STRING);
        ksoap2.setProperty("firstIdx", firstIdx, SoapProcessor.PropertyType.TYPE_STRING);
        ksoap2.setProperty("maxCount", maxCount, SoapProcessor.PropertyType.TYPE_STRING);
        ksoap2.setProperty("oidFinanceProductsTypeId", oidFinanceProductsTypeId, SoapProcessor.PropertyType.TYPE_STRING);
        JsonElement element = ksoap2.request();
        Gson gson = new GsonBuilder().create();

        TypeToken<List<AllCouponAndInterestModle>> tt = new TypeToken<List<AllCouponAndInterestModle>>() {
        };
        List<AllCouponAndInterestModle> fs = gson.fromJson(element, tt.getType());
        return fs;
    }

    //通过某一个红包或者加息券来    匹配红包/加息券最优使用标的信息
    public static BestProductModel getBestProduct(String type, String couponId) throws BizFailure, ZYException {
        SoapProcessor ksoap2 = new SoapProcessor("Service", "jsonBestProduct", true);
        ksoap2.setProperty("type", type, SoapProcessor.PropertyType.TYPE_STRING);
        ksoap2.setProperty("couponId", couponId, SoapProcessor.PropertyType.TYPE_STRING);

        JsonElement element = ksoap2.request();
        Gson gson = new GsonBuilder().create();
        return gson.fromJson(element, BestProductModel.class);
    }


    //或全部红包
    public static List<AllCouponModel> getCoupon(String firstIdx, String maxCount) throws BizFailure,
            ZYException {
        SoapProcessor ksoap2 = new SoapProcessor("Service", "getAllCoupon", true);
        ksoap2.setProperty("firstIdx", firstIdx, SoapProcessor.PropertyType.TYPE_STRING);
        ksoap2.setProperty("maxCount", maxCount, SoapProcessor.PropertyType.TYPE_STRING);
        JsonElement element = ksoap2.request();
        Gson gson = new GsonBuilder().create();

        TypeToken<List<AllCouponModel>> tt = new TypeToken<List<AllCouponModel>>() {
        };
        List<AllCouponModel> fs = gson.fromJson(element, tt.getType());
        return fs;
    }
    //兑换码兑换
    public static String getCouponExchange( String exchangeCode) throws BizFailure, ZYException {

        SoapProcessor ksoap2 = new SoapProcessor("Service", "couponExchange", true);
        ksoap2.setProperty("exchangeCode", exchangeCode, SoapProcessor.PropertyType.TYPE_STRING);
        return ksoap2.request().getAsString();
    }


    //获取全部加息劵
    public static List<AllInterestModel> getInterest(String firstIdx, String maxCount) throws BizFailure,
            ZYException {
        SoapProcessor ksoap2 = new SoapProcessor("Service", "getAllInterestRate", true);
        ksoap2.setProperty("firstIdx", firstIdx, SoapProcessor.PropertyType.TYPE_STRING);
        ksoap2.setProperty("maxCount", maxCount, SoapProcessor.PropertyType.TYPE_STRING);
        JsonElement element = ksoap2.request();
        Gson gson = new GsonBuilder().create();

        TypeToken<List<AllInterestModel>> tt = new TypeToken<List<AllInterestModel>>() {
        };
        List<AllInterestModel> fs = gson.fromJson(element, tt.getType());
        return fs;

    }
    //获取投资红包列表
    public static List<CouponListForTenderModel> getCoupnList( String oidPlatformProductsId) throws BizFailure,
            ZYException {
        SoapProcessor ksoap2 = new SoapProcessor("Service", "getCouponListForTender", true);
        ksoap2.setProperty("oidPlatformProductsId", oidPlatformProductsId, SoapProcessor.PropertyType.TYPE_STRING);
        JsonElement element = ksoap2.request();
        Gson gson = new GsonBuilder().create();

        TypeToken<List<CouponListForTenderModel>> tt = new TypeToken<List<CouponListForTenderModel>>() {
        };
        List<CouponListForTenderModel> fs = gson.fromJson(element, tt.getType());
        return fs;

    }

    //获取投资加息劵列表
    public static List<CouponListForTenderModel> getJXJ( String oidPlatformProductsId) throws BizFailure,
            ZYException {
        SoapProcessor ksoap2 = new SoapProcessor("Service", "getInterestRateListForTender", true);
        ksoap2.setProperty("oidPlatformProductsId", oidPlatformProductsId, SoapProcessor.PropertyType.TYPE_STRING);
        JsonElement element = ksoap2.request();
        Gson gson = new GsonBuilder().create();

        TypeToken<List<CouponListForTenderModel>> tt = new TypeToken<List<CouponListForTenderModel>>() {
        };
        List<CouponListForTenderModel> fs = gson.fromJson(element, tt.getType());
        return fs;

    }

    //红包兑现
    public static String getCouponToCash( String redPacketTempletId,String redPacketLogId) throws BizFailure, ZYException {

        SoapProcessor ksoap2 = new SoapProcessor("Service", "couponToCash", true);
        ksoap2.setProperty("redPacketTempletId", redPacketTempletId, SoapProcessor.PropertyType.TYPE_STRING);
        ksoap2.setProperty("redPacketLogId", redPacketLogId, SoapProcessor.PropertyType.TYPE_STRING);
        return ksoap2.request().getAsString();
    }
    //加息劵赠送人信息
    public static SongNameModel getMessage(String doneeMobile) throws BizFailure,
            ZYException {
        SoapProcessor ksoap2 = new SoapProcessor("Service", "checkDoneeInfo", true);
        ksoap2.setProperty("doneeMobile", doneeMobile, SoapProcessor.PropertyType.TYPE_STRING);
        JsonElement element = ksoap2.request();
        Gson gson = new GsonBuilder().create();
        return gson.fromJson(element, SongNameModel.class);
    }
    //确认赠送
    public static String getQueren( String doneeMobile,String rateCouponSendId) throws BizFailure, ZYException {

        SoapProcessor ksoap2 = new SoapProcessor("Service", "interestRateTransfer", true);
        ksoap2.setProperty("doneeMobile", doneeMobile, SoapProcessor.PropertyType.TYPE_STRING);
        ksoap2.setProperty("rateCouponSendId", rateCouponSendId, SoapProcessor.PropertyType.TYPE_STRING);
        return ksoap2.request().getAsString();
    }

    //加息券兑换
    public static String getDuihuan( String exchangeCode) throws BizFailure, ZYException {

        SoapProcessor ksoap2 = new SoapProcessor("Service", "interestRateExchange", true);
        ksoap2.setProperty("exchangeCode", exchangeCode, SoapProcessor.PropertyType.TYPE_STRING);
        return ksoap2.request().getAsString();
    }

}
