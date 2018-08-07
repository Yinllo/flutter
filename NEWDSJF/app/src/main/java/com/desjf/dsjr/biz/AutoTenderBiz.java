package com.desjf.dsjr.biz;

import com.desjf.dsjr.biz.exception.BizFailure;
import com.desjf.dsjr.biz.exception.ZYException;
import com.desjf.dsjr.biz.task.SoapProcessor;
import com.desjf.dsjr.model.AutoBorrowModel;
import com.desjf.dsjr.model.AutoRecordModel;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;

/**
 * Created by Administrator on 2017/7/27 0027.
 */

public class AutoTenderBiz {
    //全部自动标
    public static AutoBorrowModel getAutoBorrow() throws BizFailure,
            ZYException {
        SoapProcessor ksoap2 = new SoapProcessor("Service", "getAutoBorrowList", true);
        JsonElement element = ksoap2.request();
        Gson gson = new GsonBuilder().create();
        return gson.fromJson(element, AutoBorrowModel.class);
    }
    //添加一标
    public static String addAutoBorrow(String seq, String loanAmount,String retainAmount,String periodBegin, String periodEnd
    ,String awardFlg,String rateBegin,String rateEnd,String chkBorrowMortgage,String chkBorrowWander,String usingflg) throws BizFailure, ZYException {

        SoapProcessor ksoap2 = new SoapProcessor("Service", "autoBorrow", true);
        ksoap2.setProperty("seq", seq, SoapProcessor.PropertyType.TYPE_STRING);
        ksoap2.setProperty("loanAmount", loanAmount, SoapProcessor.PropertyType.TYPE_STRING);
        ksoap2.setProperty("retainAmount", retainAmount, SoapProcessor.PropertyType.TYPE_STRING);
        ksoap2.setProperty("periodBegin", periodBegin, SoapProcessor.PropertyType.TYPE_STRING);
        ksoap2.setProperty("periodEnd", periodEnd, SoapProcessor.PropertyType.TYPE_STRING);
        ksoap2.setProperty("awardFlg", awardFlg, SoapProcessor.PropertyType.TYPE_STRING);
        ksoap2.setProperty("rateBegin", rateBegin, SoapProcessor.PropertyType.TYPE_STRING);
        ksoap2.setProperty("rateEnd", rateEnd, SoapProcessor.PropertyType.TYPE_STRING);
        ksoap2.setProperty("chkBorrowMortgage", chkBorrowMortgage, SoapProcessor.PropertyType.TYPE_STRING);
        ksoap2.setProperty("chkBorrowWander", chkBorrowWander, SoapProcessor.PropertyType.TYPE_STRING);
        ksoap2.setProperty("usingflg", usingflg, SoapProcessor.PropertyType.TYPE_STRING);
        return ksoap2.request().getAsString();
    }

    //删除一条
    public static String delAutoBorrow(String seq) throws BizFailure, ZYException {

        SoapProcessor ksoap2 = new SoapProcessor("Service", "delAutoBorrowInfo", true);
        ksoap2.setProperty("seq", seq, SoapProcessor.PropertyType.TYPE_STRING);
        return ksoap2.request().getAsString();
    }

    //自动投标按钮开启
    public static String AutoBorrowSwitch(String seq,String usingflg) throws BizFailure, ZYException {

        SoapProcessor ksoap2 = new SoapProcessor("Service", "getAutoBorrowSwitch", true);
        ksoap2.setProperty("seq", seq, SoapProcessor.PropertyType.TYPE_STRING);
        ksoap2.setProperty("usingflg", usingflg, SoapProcessor.PropertyType.TYPE_STRING);
        return ksoap2.request().getAsString();
    }

    //自动投标记录
    public static AutoRecordModel getAutoRecord(String firstIdx,String maxCount) throws BizFailure,
            ZYException {
        SoapProcessor ksoap2 = new SoapProcessor("Service", "autoRecord", true);
        ksoap2.setProperty("firstIdx", firstIdx, SoapProcessor.PropertyType.TYPE_STRING);
        ksoap2.setProperty("maxCount", maxCount, SoapProcessor.PropertyType.TYPE_STRING);
        JsonElement element = ksoap2.request();
        Gson gson = new GsonBuilder().create();
        return gson.fromJson(element, AutoRecordModel.class);
    }
}
