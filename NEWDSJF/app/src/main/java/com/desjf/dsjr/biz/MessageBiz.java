package com.desjf.dsjr.biz;

import com.desjf.dsjr.biz.exception.BizFailure;
import com.desjf.dsjr.biz.exception.ZYException;
import com.desjf.dsjr.biz.task.SoapProcessor;
import com.desjf.dsjr.model.MessageInfoModel;
import com.desjf.dsjr.model.MessageModel;
import com.desjf.dsjr.model.NewsCenterModel;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;
import java.util.List;

/**
 * Created by Administrator on 2017/7/26 0026.
 */
public class MessageBiz {

    //消息中心
    public static NewsCenterModel getNewsCenter() throws BizFailure,
            ZYException {
        SoapProcessor ksoap2 = new SoapProcessor("Service", "getNewsCenter", true);
        JsonElement element = ksoap2.request();
        Gson gson = new GsonBuilder().create();
        return gson.fromJson(element, NewsCenterModel.class);
    }

    //获取消息详情
    public static List<MessageModel> getMessageInfo(String firstIdx,String maxCount) throws BizFailure,
            ZYException {
        SoapProcessor ksoap2 = new SoapProcessor("Service", "getMessageCenterList", true);
        ksoap2.setProperty("firstIdx", firstIdx, SoapProcessor.PropertyType.TYPE_STRING);
        ksoap2.setProperty("maxCount", maxCount, SoapProcessor.PropertyType.TYPE_STRING);
        JsonElement element = ksoap2.request();
        Gson gson = new GsonBuilder().create();

        TypeToken<List<MessageModel>> tt = new TypeToken<List<MessageModel>>() {
        };
        List<MessageModel> fs = gson.fromJson(element, tt.getType());
        return fs;
    }

    //详细消息内容
    public static MessageInfoModel getNewsInfo(String messageId) throws BizFailure,
            ZYException {
        SoapProcessor ksoap2 = new SoapProcessor("Service", "getMessageInfo", false);
        ksoap2.setProperty("messageId", messageId, SoapProcessor.PropertyType.TYPE_STRING);
        JsonElement element = ksoap2.request();
        Gson gson = new GsonBuilder().create();
        return gson.fromJson(element, MessageInfoModel.class);
    }
}
