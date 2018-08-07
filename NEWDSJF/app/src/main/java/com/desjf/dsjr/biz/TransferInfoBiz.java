package com.desjf.dsjr.biz;

import com.desjf.dsjr.biz.exception.BizFailure;
import com.desjf.dsjr.biz.exception.ZYException;
import com.desjf.dsjr.biz.task.SoapProcessor;
import com.desjf.dsjr.model.AreaListModel;
import com.desjf.dsjr.model.ProjectInfoModel;
import com.desjf.dsjr.model.TransferInfoModel;
import com.desjf.dsjr.model.TransferInvestorsModel;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;

import java.util.List;

/**
 * Created by Administrator on 2017/7/31 0031.
 */

public class TransferInfoBiz {
    //获取转让详情
    public static TransferInfoModel getTransferInfo(String transferId) throws BizFailure,
            ZYException {
        SoapProcessor ksoap2 = new SoapProcessor("Service", "getTransferInfo", true);
        ksoap2.setProperty("transferId", transferId, SoapProcessor.PropertyType.TYPE_STRING);
        JsonElement element = ksoap2.request();
        Gson gson = new GsonBuilder().create();
        return gson.fromJson(element, TransferInfoModel.class);
    }

    //债转列表

    public static List<TransferInvestorsModel> getTransfer(String transferId,String firstIdx,String maxCount,String type) throws BizFailure,
            ZYException {
        SoapProcessor ksoap2 = new SoapProcessor("Service", "getTransferInvestors", false);
        ksoap2.setProperty("transferId", transferId, SoapProcessor.PropertyType.TYPE_STRING);
        ksoap2.setProperty("firstIdx", firstIdx, SoapProcessor.PropertyType.TYPE_STRING);
        ksoap2.setProperty("maxCount", maxCount, SoapProcessor.PropertyType.TYPE_STRING);
        ksoap2.setProperty("type", type, SoapProcessor.PropertyType.TYPE_STRING);
        JsonElement element = ksoap2.request();
        Gson gson = new GsonBuilder().create();
        TypeToken<List<TransferInvestorsModel>> tt = new TypeToken<List<TransferInvestorsModel>>() {
        };
        List<TransferInvestorsModel> fs = gson.fromJson(element, tt.getType());
        return fs;
    }
}
