package com.desjf.dsjr.biz;

import com.desjf.dsjr.biz.exception.BizFailure;
import com.desjf.dsjr.biz.exception.ZYException;
import com.desjf.dsjr.biz.task.SoapProcessor;
import com.desjf.dsjr.model.InterestTotalForTransferModel;
import com.desjf.dsjr.model.InterestTotalModel;
import com.desjf.dsjr.model.TenderForTransferModel;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;

/**
 * Created by Administrator on 2017/7/31 0031.
 */

public class TenderForTransferBiz {
    //债权转让全额投资
    public static TenderForTransferModel getTender(String transferId) throws BizFailure,
            ZYException {
        SoapProcessor ksoap2 = new SoapProcessor("Service", "allTenderForTransfer", true);
        ksoap2.setProperty("transferId", transferId, SoapProcessor.PropertyType.TYPE_STRING);
        JsonElement element = ksoap2.request();
        Gson gson = new GsonBuilder().create();
        return gson.fromJson(element, TenderForTransferModel.class);
    }

    //债权转让输入投资
    public static InterestTotalForTransferModel getInterest(String transferId,String transferAmount) throws BizFailure,
            ZYException {
        SoapProcessor ksoap2 = new SoapProcessor("Service", "getInterestTotalForTransfer", true);
        ksoap2.setProperty("transferId", transferId, SoapProcessor.PropertyType.TYPE_STRING);
        ksoap2.setProperty("transferAmount", transferAmount, SoapProcessor.PropertyType.TYPE_STRING);
        JsonElement element = ksoap2.request();
        Gson gson = new GsonBuilder().create();
        return gson.fromJson(element, InterestTotalForTransferModel.class);
    }

    //购买债权转让
    public static String biddingForTransfer(String transferId,String transferAmount,String transactionPassword) throws BizFailure, ZYException {

        SoapProcessor ksoap2 = new SoapProcessor("Service", "biddingForTransfer", true);
        ksoap2.setProperty("transferId", transferId, SoapProcessor.PropertyType.TYPE_STRING);
        ksoap2.setProperty("transferAmount", transferAmount, SoapProcessor.PropertyType.TYPE_STRING);
        ksoap2.setProperty("transactionPassword", transactionPassword, SoapProcessor.PropertyType.TYPE_STRING);
        return ksoap2.request().getAsString();
    }

    //预期收益
    public static InterestTotalModel getYjsy(String productsId,String tenderAmount,String rateAmountTo) throws BizFailure,
            ZYException {
        SoapProcessor ksoap2 = new SoapProcessor("Service", "getInterestTotal", true);
        ksoap2.setProperty("productsId", productsId, SoapProcessor.PropertyType.TYPE_STRING);
        ksoap2.setProperty("tenderAmount", tenderAmount, SoapProcessor.PropertyType.TYPE_STRING);
        ksoap2.setProperty("rateAmountTo", rateAmountTo, SoapProcessor.PropertyType.TYPE_STRING);
        JsonElement element = ksoap2.request();
        Gson gson = new GsonBuilder().create();
        return gson.fromJson(element, InterestTotalModel.class);
    }

    //预期收益  未登录时计算
    public static InterestTotalModel getNoLoginYjsyk(String productsId,String tenderAmount,String rateAmountTo) throws BizFailure,
            ZYException {
        SoapProcessor ksoap2 = new SoapProcessor("Service", "getInterestTotalNoLogin",false);
        ksoap2.setProperty("productsId", productsId, SoapProcessor.PropertyType.TYPE_STRING);
        ksoap2.setProperty("tenderAmount", tenderAmount, SoapProcessor.PropertyType.TYPE_STRING);
        ksoap2.setProperty("rateAmountTo", rateAmountTo, SoapProcessor.PropertyType.TYPE_STRING);
        JsonElement element = ksoap2.request();
        Gson gson = new GsonBuilder().create();
        return gson.fromJson(element, InterestTotalModel.class);
    }
}
