package com.desjf.dsjr.biz.task;

import com.desjf.dsjr.biz.exception.BizFailure;
import com.desjf.dsjr.biz.exception.ZYException;
import com.desjf.dsjr.model.AjaxEventModel;
import com.desjf.dsjr.model.AllTenderModel;
import com.desjf.dsjr.model.UserTransferInfoModel;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;

/**
 * Created by Administrator on 2017/8/15 0015.
 */

public class UserZRBiz {

    public static UserTransferInfoModel getUserInfer(String oidTenderId, String tenderFrom) throws BizFailure,
            ZYException {
        SoapProcessor ksoap2 = new SoapProcessor("Service", "getUserTransferInfo", true);
        ksoap2.setProperty("oidTenderId", oidTenderId, SoapProcessor.PropertyType.TYPE_STRING);
        ksoap2.setProperty("tenderFrom", tenderFrom, SoapProcessor.PropertyType.TYPE_STRING);
        JsonElement element = ksoap2.request();
        Gson gson = new GsonBuilder().create();
        return gson.fromJson(element, UserTransferInfoModel.class);
    }

    public static AjaxEventModel getUser(String oidTenderId, String tenderFrom,String transferAmount,String discountAmount) throws BizFailure,
            ZYException {
        SoapProcessor ksoap2 = new SoapProcessor("Service", "transferAjaxEvent", false);
        ksoap2.setProperty("oidTenderId", oidTenderId, SoapProcessor.PropertyType.TYPE_STRING);
        ksoap2.setProperty("tenderFrom", tenderFrom, SoapProcessor.PropertyType.TYPE_STRING);
        ksoap2.setProperty("transferAmount", transferAmount, SoapProcessor.PropertyType.TYPE_STRING);
        ksoap2.setProperty("discountAmount", discountAmount, SoapProcessor.PropertyType.TYPE_STRING);
        JsonElement element = ksoap2.request();
        Gson gson = new GsonBuilder().create();
        return gson.fromJson(element, AjaxEventModel.class);
    }

    public static String getTransfer(String oidTenderId,String tenderFrom,String transferAmount,String discountAmount) throws BizFailure, ZYException {

        SoapProcessor ksoap2 = new SoapProcessor("Service","transferBtnClick",true);
        ksoap2.setProperty("oidTenderId", oidTenderId, SoapProcessor.PropertyType.TYPE_STRING);
        ksoap2.setProperty("tenderFrom", tenderFrom, SoapProcessor.PropertyType.TYPE_STRING);
        ksoap2.setProperty("transferAmount", transferAmount, SoapProcessor.PropertyType.TYPE_STRING);
        ksoap2.setProperty("discountAmount", discountAmount, SoapProcessor.PropertyType.TYPE_STRING);
        return ksoap2.request().getAsString();
    }

}
