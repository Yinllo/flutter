package com.desjf.dsjr.biz;

import com.desjf.dsjr.biz.exception.BizFailure;
import com.desjf.dsjr.biz.exception.ZYException;
import com.desjf.dsjr.biz.task.SoapProcessor;
import com.desjf.dsjr.model.IncomeListModel;
import com.desjf.dsjr.model.IncomeModel;
import com.desjf.dsjr.model.LoginModel;
import com.desjf.dsjr.model.MessageModel;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;

import java.util.List;

/**
 * Created by Administrator on 2017/7/26 0026.
 */

public class IncomeBiz {
    //个人损益合计
    public static IncomeModel getTotalIncome() throws BizFailure,
            ZYException {
        SoapProcessor ksoap2 = new SoapProcessor("Service", "userProfitAndLosses", true);
        JsonElement element = ksoap2.request();
        Gson gson = new GsonBuilder().create();
        return gson.fromJson(element, IncomeModel.class);
    }

    //个人损益集合
    public static List<IncomeListModel> getIncomeList(String firstIdx, String maxCount) throws BizFailure,
            ZYException {
        SoapProcessor ksoap2 = new SoapProcessor("Service", "userProfitAndLossesDetail", true);
        ksoap2.setProperty("firstIdx", firstIdx, SoapProcessor.PropertyType.TYPE_STRING);
        ksoap2.setProperty("maxCount", maxCount, SoapProcessor.PropertyType.TYPE_STRING);
        JsonElement element = ksoap2.request();
        Gson gson = new GsonBuilder().create();

        TypeToken<List<IncomeListModel>> tt = new TypeToken<List<IncomeListModel>>() {
        };
        List<IncomeListModel> fs = gson.fromJson(element, tt.getType());
        return fs;
    }
}
