package com.desjf.dsjr.biz;

import com.desjf.dsjr.biz.exception.BizFailure;
import com.desjf.dsjr.biz.exception.ZYException;
import com.desjf.dsjr.biz.task.SoapProcessor;
import com.desjf.dsjr.model.AreaListModel;
import com.desjf.dsjr.model.BanksModel;
import com.desjf.dsjr.model.LoginModel;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;

import java.util.List;

/**
 * Created by Administrator on 2017/7/27 0027.
 */

public class AreaBiz {
    //获取省市信息
    public static List<AreaListModel> getArea(String parentId) throws BizFailure,
            ZYException {
        SoapProcessor ksoap2 = new SoapProcessor("Service", "getAreaList", false);
        ksoap2.setProperty("parentId", parentId, SoapProcessor.PropertyType.TYPE_STRING);
        JsonElement element = ksoap2.request();
        Gson gson = new GsonBuilder().create();

        TypeToken<List<AreaListModel>> tt = new TypeToken<List<AreaListModel>>() {
        };
        List<AreaListModel> fs = gson.fromJson(element, tt.getType());
        return fs;
    }

}
