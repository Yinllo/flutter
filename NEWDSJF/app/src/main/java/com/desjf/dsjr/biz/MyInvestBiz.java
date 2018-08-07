package com.desjf.dsjr.biz;

import com.desjf.dsjr.biz.exception.BizFailure;
import com.desjf.dsjr.biz.exception.ZYException;
import com.desjf.dsjr.biz.task.SoapProcessor;
import com.desjf.dsjr.model.InvestorsModel;
import com.desjf.dsjr.model.MyInvestModel;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;

import java.util.List;

/**
 * 投资记录
 */


public class MyInvestBiz {
    //我的投资
    public static List<MyInvestModel> getInvest(String status,String firstIdx,String maxCount) throws BizFailure,
            ZYException {
        SoapProcessor ksoap2 = new SoapProcessor("Service", "myInvest", true);
        ksoap2.setProperty("status", status, SoapProcessor.PropertyType.TYPE_STRING);
        ksoap2.setProperty("firstIdx", firstIdx, SoapProcessor.PropertyType.TYPE_STRING);
        ksoap2.setProperty("maxCount", maxCount, SoapProcessor.PropertyType.TYPE_STRING);
        JsonElement element = ksoap2.request();
        Gson gson = new GsonBuilder().create();

        TypeToken<List<MyInvestModel>> tt = new TypeToken<List<MyInvestModel>>() {
        };
        List<MyInvestModel> fs = gson.fromJson(element, tt.getType());
        return fs;
    }

    //投资记录
    public static List<InvestorsModel> getInvestors(String borrowId, String firstIdx, String maxCount) throws BizFailure,
            ZYException {
        SoapProcessor ksoap2 = new SoapProcessor("Service", "getInvestors", false);
        ksoap2.setProperty("borrowId", borrowId, SoapProcessor.PropertyType.TYPE_STRING);
        ksoap2.setProperty("firstIdx", firstIdx, SoapProcessor.PropertyType.TYPE_STRING);
        ksoap2.setProperty("maxCount", maxCount, SoapProcessor.PropertyType.TYPE_STRING);
        JsonElement element = ksoap2.request();
        Gson gson = new GsonBuilder().create();

        TypeToken<List<InvestorsModel>> tt = new TypeToken<List<InvestorsModel>>() {
        };
        List<InvestorsModel> fs = gson.fromJson(element, tt.getType());
        return fs;
    }
}
