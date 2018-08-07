package com.desjf.dsjr.biz;

import com.desjf.dsjr.biz.exception.BizFailure;
import com.desjf.dsjr.biz.exception.ZYException;
import com.desjf.dsjr.biz.task.SoapProcessor;
import com.desjf.dsjr.model.CalendarDayDetailsModel;
import com.desjf.dsjr.model.CalendarMonDetailsModel;
import com.desjf.dsjr.model.CalendarMonHeadModel;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;

import java.util.List;

/**
 * Created by Administrator on 2017/8/4 0004.
 */

public class CalendarBiz {
    //回款日历---单月、全部
    public static CalendarMonHeadModel getPayment(String monOrAllFlg, String monDate) throws BizFailure,
            ZYException {
        SoapProcessor ksoap2 = new SoapProcessor("Service", "paymentCalendarMonHead", true);
        ksoap2.setProperty("monOrAllFlg", monOrAllFlg, SoapProcessor.PropertyType.TYPE_STRING);
        ksoap2.setProperty("monDate", monDate, SoapProcessor.PropertyType.TYPE_STRING);
        JsonElement element = ksoap2.request();
        Gson gson = new GsonBuilder().create();
        return gson.fromJson(element, CalendarMonHeadModel.class);
    }

    //回款日历---单月、全部明细
    public static List<CalendarMonDetailsModel> getDetails(String monOrAllFlg,String firstIdx,String maxCount,
                                                           String monDate,String diffFlg) throws BizFailure,
            ZYException {
        SoapProcessor ksoap2 = new SoapProcessor("Service", "paymentCalendarMonDetails", true);
        ksoap2.setProperty("monOrAllFlg", monOrAllFlg, SoapProcessor.PropertyType.TYPE_STRING);
        ksoap2.setProperty("firstIdx", firstIdx, SoapProcessor.PropertyType.TYPE_STRING);
        ksoap2.setProperty("maxCount", maxCount, SoapProcessor.PropertyType.TYPE_STRING);
        ksoap2.setProperty("monDate", monDate, SoapProcessor.PropertyType.TYPE_STRING);
        ksoap2.setProperty("diffFlg", diffFlg, SoapProcessor.PropertyType.TYPE_STRING);
        JsonElement element = ksoap2.request();
        Gson gson = new GsonBuilder().create();
        TypeToken<List<CalendarMonDetailsModel>> tt = new TypeToken<List<CalendarMonDetailsModel>>() {};
        List<CalendarMonDetailsModel> fs = gson.fromJson(element, tt.getType());

        return fs;
    }

    //单日明细
    public static List<CalendarDayDetailsModel> getDayDetails(String firstIdx, String maxCount, String dayDate) throws BizFailure,
            ZYException {
        SoapProcessor ksoap2 = new SoapProcessor("Service", "paymentCalendarDayDetails", true);
        ksoap2.setProperty("firstIdx", firstIdx, SoapProcessor.PropertyType.TYPE_STRING);
        ksoap2.setProperty("maxCount", maxCount, SoapProcessor.PropertyType.TYPE_STRING);
        ksoap2.setProperty("dayDate", dayDate, SoapProcessor.PropertyType.TYPE_STRING);
        JsonElement element = ksoap2.request();
        Gson gson = new GsonBuilder().create();
        TypeToken<List<CalendarDayDetailsModel>> tt = new TypeToken<List<CalendarDayDetailsModel>>() {
        };
        List<CalendarDayDetailsModel> fs = gson.fromJson(element, tt.getType());
        return fs;
    }
}
