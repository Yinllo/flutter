package com.desjf.dsjr.biz;

import com.desjf.dsjr.biz.exception.BizFailure;
import com.desjf.dsjr.biz.exception.ZYException;
import com.desjf.dsjr.biz.task.SoapProcessor;
import com.desjf.dsjr.model.BanksModel;
import com.desjf.dsjr.model.HomeModel;
import com.desjf.dsjr.model.NoticeModel;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;

import java.util.List;

/**
 * Created by Administrator on 2017/7/27 0027.
 */

public class BanksBiz {
    //获取银行数据
    public static List<BanksModel> getBanks() throws BizFailure,
            ZYException {
        SoapProcessor ksoap2 = new SoapProcessor("Service", "getSiteBankList", false);
        JsonElement element = ksoap2.request();
        Gson gson = new GsonBuilder().create();

        TypeToken<List<BanksModel>> tt = new TypeToken<List<BanksModel>>() {
        };
        List<BanksModel> fs = gson.fromJson(element, tt.getType());
        return fs;
    }
    //绑定银行卡
    public static String getbindBank(String bankNo, String provinceId,String cityId,String branchName,String bankCardNo) throws BizFailure, ZYException {

        SoapProcessor ksoap2 = new SoapProcessor("Service", "bindBank", true);
        ksoap2.setProperty("bankNo", bankNo, SoapProcessor.PropertyType.TYPE_STRING);
        ksoap2.setProperty("provinceId", provinceId, SoapProcessor.PropertyType.TYPE_STRING);
        ksoap2.setProperty("cityId", cityId, SoapProcessor.PropertyType.TYPE_STRING);
        ksoap2.setProperty("branchName", branchName, SoapProcessor.PropertyType.TYPE_STRING);
        ksoap2.setProperty("bankCardNo", bankCardNo, SoapProcessor.PropertyType.TYPE_STRING);
        return ksoap2.request().getAsString();
    }

    //更改银行卡
    public static String getChange(String provinceId, String cityId,String branchName,String bankNo) throws BizFailure, ZYException {

        SoapProcessor ksoap2 = new SoapProcessor("Service", "changeUserBankInfo", true);
        ksoap2.setProperty("provinceId", provinceId, SoapProcessor.PropertyType.TYPE_STRING);
        ksoap2.setProperty("cityId", cityId, SoapProcessor.PropertyType.TYPE_STRING);
        ksoap2.setProperty("branchName", branchName, SoapProcessor.PropertyType.TYPE_STRING);
        ksoap2.setProperty("bankNo", bankNo, SoapProcessor.PropertyType.TYPE_STRING);
        return ksoap2.request().getAsString();
    }

}
