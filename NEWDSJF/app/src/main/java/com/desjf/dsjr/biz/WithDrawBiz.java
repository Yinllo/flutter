package com.desjf.dsjr.biz;

import com.desjf.dsjr.biz.exception.BizFailure;
import com.desjf.dsjr.biz.exception.ZYException;
import com.desjf.dsjr.biz.task.SoapProcessor;
import com.desjf.dsjr.model.IncomeListModel;
import com.desjf.dsjr.model.LoginModel;
import com.desjf.dsjr.model.StanderdModel;
import com.desjf.dsjr.model.WithDrawModel;
import com.desjf.dsjr.model.WithDrawStageModel;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;

import java.util.List;

/**
 * Created by Administrator on 2017/8/19 0019.
 */

public class WithDrawBiz {
    //填写手机号
    public static String getWithDraw(String tradePwd,String withdrawMoney) throws BizFailure, ZYException {

        SoapProcessor ksoap2 = new SoapProcessor("Service", "withDraw", true);
        ksoap2.setProperty("tradePwd", tradePwd, SoapProcessor.PropertyType.TYPE_STRING);
        ksoap2.setProperty("withdrawMoney", withdrawMoney, SoapProcessor.PropertyType.TYPE_STRING);
        return ksoap2.request().getAsString();
    }
    //记录
    public static List<WithDrawModel> getWithDrawList(String firstIdx, String maxCount) throws BizFailure,
            ZYException {
        SoapProcessor ksoap2 = new SoapProcessor("Service", "getWithdrawOrderList", true);
        ksoap2.setProperty("firstIdx", firstIdx, SoapProcessor.PropertyType.TYPE_STRING);
        ksoap2.setProperty("maxCount", maxCount, SoapProcessor.PropertyType.TYPE_STRING);
        JsonElement element = ksoap2.request();
        Gson gson = new GsonBuilder().create();

        TypeToken<List<WithDrawModel>> tt = new TypeToken<List<WithDrawModel>>() {
        };
        List<WithDrawModel> fs = gson.fromJson(element, tt.getType());
        return fs;
    }
    //撤销
    public static String getWithDrawCx(String oidWithdrawId) throws BizFailure, ZYException {

        SoapProcessor ksoap2 = new SoapProcessor("Service", "undoWithdraw", true);
        ksoap2.setProperty("oidWithdrawId", oidWithdrawId, SoapProcessor.PropertyType.TYPE_STRING);
        return ksoap2.request().getAsString();
    }

    public static WithDrawStageModel getWithDrawStage() throws BizFailure,
            ZYException {
        SoapProcessor ksoap2 = new SoapProcessor("Service", "withdrawStage", true);
        JsonElement element = ksoap2.request();
        Gson gson = new GsonBuilder().create();
        return gson.fromJson(element, WithDrawStageModel.class);
    }
}
