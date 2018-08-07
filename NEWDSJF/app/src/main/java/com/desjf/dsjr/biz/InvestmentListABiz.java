package com.desjf.dsjr.biz;

import com.desjf.dsjr.biz.exception.BizFailure;
import com.desjf.dsjr.biz.exception.ZYException;
import com.desjf.dsjr.biz.task.SoapProcessor;
import com.desjf.dsjr.model.AssignmentModel;
import com.desjf.dsjr.model.AutoBorrowModel;
import com.desjf.dsjr.model.InvestmentListsAModel;
import com.desjf.dsjr.model.LoginModel;
import com.desjf.dsjr.model.MessageModel;
import com.desjf.dsjr.model.StanderdModel;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;

import java.util.List;

/**
 * Created by Administrator on 2017/8/1 0001.
 */

public class InvestmentListABiz {
    //获取散标列表
    public static StanderdModel getInvestmentList(String firstIdx, String maxCount, String searchType,
                                                        String InvestmentTerm, String repaymentType) throws BizFailure,
            ZYException {
        SoapProcessor ksoap2 = new SoapProcessor("Service", "getInvestmentListsA", false);
        ksoap2.setProperty("firstIdx", firstIdx, SoapProcessor.PropertyType.TYPE_STRING);
        ksoap2.setProperty("maxCount", maxCount, SoapProcessor.PropertyType.TYPE_STRING);
        ksoap2.setProperty("searchType", searchType, SoapProcessor.PropertyType.TYPE_STRING);
        ksoap2.setProperty("InvestmentTerm", InvestmentTerm, SoapProcessor.PropertyType.TYPE_STRING);
        ksoap2.setProperty("repaymentType", repaymentType, SoapProcessor.PropertyType.TYPE_STRING);
        JsonElement element = ksoap2.request();
        Gson gson = new GsonBuilder().create();
        return gson.fromJson(element, StanderdModel.class);
    }

    //获取债转列表
    public static AssignmentModel getZhaiz(String firstIdx, String maxCount, String searchType,
                                           String InvestmentTerm, String repaymentType) throws BizFailure,
            ZYException {
        SoapProcessor ksoap2 = new SoapProcessor("Service", "getInvestmentListsA", false);
        ksoap2.setProperty("firstIdx", firstIdx, SoapProcessor.PropertyType.TYPE_STRING);
        ksoap2.setProperty("maxCount", maxCount, SoapProcessor.PropertyType.TYPE_STRING);
        ksoap2.setProperty("searchType", searchType, SoapProcessor.PropertyType.TYPE_STRING);
        ksoap2.setProperty("InvestmentTerm", InvestmentTerm, SoapProcessor.PropertyType.TYPE_STRING);
        ksoap2.setProperty("repaymentType", repaymentType, SoapProcessor.PropertyType.TYPE_STRING);
        JsonElement element = ksoap2.request();
        Gson gson = new GsonBuilder().create();
        return gson.fromJson(element, AssignmentModel.class);
    }
}
