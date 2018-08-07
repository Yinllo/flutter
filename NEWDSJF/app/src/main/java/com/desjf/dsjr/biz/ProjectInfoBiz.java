package com.desjf.dsjr.biz;

import com.desjf.dsjr.biz.exception.BizFailure;
import com.desjf.dsjr.biz.exception.ZYException;
import com.desjf.dsjr.biz.task.SoapProcessor;
import com.desjf.dsjr.model.HomeModel;
import com.desjf.dsjr.model.InvestmentInfoModel;
import com.desjf.dsjr.model.ProjectInfoModel;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;

/**
 * Created by Administrator on 2017/7/29 0029.
 */

public class ProjectInfoBiz {
    //获取项目详情
    public static ProjectInfoModel getProjectInfo(String productsId) throws BizFailure,
            ZYException {
        SoapProcessor ksoap2 = new SoapProcessor("Service", "getTenderInfoOne", true);
        ksoap2.setProperty("productsId", productsId, SoapProcessor.PropertyType.TYPE_STRING);
        JsonElement element = ksoap2.request();
        Gson gson = new GsonBuilder().create();
        return gson.fromJson(element, ProjectInfoModel.class);
    }

    //获取项目详情内容
    public static InvestmentInfoModel getInvestInfo(String productsId) throws BizFailure,
            ZYException {
        SoapProcessor ksoap2 = new SoapProcessor("Service", "getInvestmentInfo", false);
        ksoap2.setProperty("productsId", productsId, SoapProcessor.PropertyType.TYPE_STRING);
        JsonElement element = ksoap2.request();
        Gson gson = new GsonBuilder().create();
        return gson.fromJson(element, InvestmentInfoModel.class);
    }
}
