package com.desjf.dsjr.biz;

import com.desjf.dsjr.biz.exception.BizFailure;
import com.desjf.dsjr.biz.exception.ZYException;
import com.desjf.dsjr.biz.task.SoapProcessor;
import com.desjf.dsjr.model.MyInvestModel;
import com.desjf.dsjr.model.PaymentDetailsModel;
import com.desjf.dsjr.model.PaymentInfoModel;
import com.desjf.dsjr.model.StanderdModel;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;

import java.util.List;

/**
 * Created by Administrator on 2017/8/9 0009.
 */

public class PaymentDetailsBiz {
    public static List<PaymentDetailsModel> getPayment(String tenderId, String tenderType,String firstIdx, String maxCount) throws BizFailure,
            ZYException {
        SoapProcessor ksoap2 = new SoapProcessor("Service", "getPaymentDetailList", true);
        ksoap2.setProperty("tenderId", tenderId, SoapProcessor.PropertyType.TYPE_STRING);
        ksoap2.setProperty("tenderType", tenderType, SoapProcessor.PropertyType.TYPE_STRING);
        ksoap2.setProperty("firstIdx", firstIdx, SoapProcessor.PropertyType.TYPE_STRING);
        ksoap2.setProperty("maxCount", maxCount, SoapProcessor.PropertyType.TYPE_STRING);
        JsonElement element = ksoap2.request();
        Gson gson = new GsonBuilder().create();

        TypeToken<List<PaymentDetailsModel>> tt = new TypeToken<List<PaymentDetailsModel>>() {
        };
        List<PaymentDetailsModel> fs = gson.fromJson(element, tt.getType());
        return fs;
    }

    public static PaymentInfoModel getPaymentInfo(String tenderId,String tenderType) throws BizFailure,
            ZYException {
        SoapProcessor ksoap2 = new SoapProcessor("Service", "getPaymentDetails", false);
        ksoap2.setProperty("tenderId", tenderId, SoapProcessor.PropertyType.TYPE_STRING);
        ksoap2.setProperty("tenderType", tenderType, SoapProcessor.PropertyType.TYPE_STRING);
        JsonElement element = ksoap2.request();
        Gson gson = new GsonBuilder().create();
        return gson.fromJson(element, PaymentInfoModel.class);
    }
}
