package com.desjf.dsjr.biz;

import com.desjf.dsjr.biz.exception.BizFailure;
import com.desjf.dsjr.biz.exception.ZYException;
import com.desjf.dsjr.biz.task.SoapProcessor;
import com.desjf.dsjr.model.MessageModel;
import com.desjf.dsjr.model.NoticeModel;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;

import java.util.List;

/**
 * Created by Administrator on 2017/7/26 0026.
 */

public class NoticeBiz {
    //获取消息详情
    public static List<NoticeModel> getNoticeInfo(String childType,String firstIdx, String maxCount) throws BizFailure,
            ZYException {
        SoapProcessor ksoap2 = new SoapProcessor("Service", "getNoticeList", false);
        ksoap2.setProperty("childType", childType, SoapProcessor.PropertyType.TYPE_STRING);
        ksoap2.setProperty("firstIdx", firstIdx, SoapProcessor.PropertyType.TYPE_STRING);
        ksoap2.setProperty("maxCount", maxCount, SoapProcessor.PropertyType.TYPE_STRING);
        JsonElement element = ksoap2.request();
        Gson gson = new GsonBuilder().create();

        TypeToken<List<NoticeModel>> tt = new TypeToken<List<NoticeModel>>() {
        };
        List<NoticeModel> fs = gson.fromJson(element, tt.getType());
        return fs;
    }
}
