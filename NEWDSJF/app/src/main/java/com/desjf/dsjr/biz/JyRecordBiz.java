package com.desjf.dsjr.biz;

import com.desjf.dsjr.biz.exception.BizFailure;
import com.desjf.dsjr.biz.exception.ZYException;
import com.desjf.dsjr.biz.task.SoapProcessor;
import com.desjf.dsjr.model.BanksModel;
import com.desjf.dsjr.model.JyRecordModel;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;

import java.util.List;

/**
 * Created by Administrator on 2017/8/3 0003.
 */

public class JyRecordBiz {
    //获取交易记录
    public static List<JyRecordModel> getRecord(String firstIdx,String maxCount,String appFundType) throws BizFailure,
            ZYException {
        SoapProcessor ksoap2 = new SoapProcessor("Service", "getTransactionRecord", true);
        ksoap2.setProperty("firstIdx", firstIdx, SoapProcessor.PropertyType.TYPE_STRING);
        ksoap2.setProperty("maxCount", maxCount, SoapProcessor.PropertyType.TYPE_STRING);
        ksoap2.setProperty("appFundType", appFundType, SoapProcessor.PropertyType.TYPE_STRING);
        JsonElement element = ksoap2.request();
        Gson gson = new GsonBuilder().create();
        TypeToken<List<JyRecordModel>> tt = new TypeToken<List<JyRecordModel>>() {
        };
        List<JyRecordModel> fs = gson.fromJson(element, tt.getType());
        return fs;
    }
}
