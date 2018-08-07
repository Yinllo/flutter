package com.desjf.dsjr.biz;

import com.desjf.dsjr.biz.exception.BizFailure;
import com.desjf.dsjr.biz.exception.ZYException;
import com.desjf.dsjr.biz.task.SoapProcessor;
import com.desjf.dsjr.model.AllTenderModel;
import com.desjf.dsjr.model.ProjectInfoModel;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;

/**
 * Created by Administrator on 2017/7/31 0031.
 */

public class AllTenderBiz {
    //全额投资
    public static AllTenderModel getAllTender(String productsId,String rateAmountTo) throws BizFailure,
            ZYException {
        SoapProcessor ksoap2 = new SoapProcessor("Service", "allTender", true);
        ksoap2.setProperty("productsId", productsId, SoapProcessor.PropertyType.TYPE_STRING);
        ksoap2.setProperty("rateAmountTo", rateAmountTo, SoapProcessor.PropertyType.TYPE_STRING);
        JsonElement element = ksoap2.request();
        Gson gson = new GsonBuilder().create();
        return gson.fromJson(element, AllTenderModel.class);
    }
}
