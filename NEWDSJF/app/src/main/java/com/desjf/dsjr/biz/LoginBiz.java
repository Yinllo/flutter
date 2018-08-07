package com.desjf.dsjr.biz;

import android.util.Log;

import com.desjf.dsjr.biz.exception.BizFailure;
import com.desjf.dsjr.biz.exception.ZYException;
import com.desjf.dsjr.biz.task.SoapProcessor;
import com.desjf.dsjr.model.CaptchaImageModel;
import com.desjf.dsjr.model.LoginFailureModel;
import com.desjf.dsjr.model.LoginModel;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;

/**
 * Created by Administrator on 2017/7/25 0025.
 */

public class LoginBiz {
    //登录数据
    public static LoginModel getLogin(String userName,String passWord) throws BizFailure,
            ZYException {
        SoapProcessor ksoap2 = new SoapProcessor("Service", "login", false);
        ksoap2.setProperty("userName", userName, SoapProcessor.PropertyType.TYPE_STRING);
        ksoap2.setProperty("passWord", passWord, SoapProcessor.PropertyType.TYPE_STRING);
        JsonElement element = ksoap2.request();
        Gson gson = new GsonBuilder().create();
        return gson.fromJson(element, LoginModel.class);
    }
    //登录数据
    public static String getLoign(String userName, String passWord,String tagEnd) throws BizFailure, ZYException {
        SoapProcessor ksoap2 = new SoapProcessor("Service", "loginNew", false);
        ksoap2.setProperty("userName", userName, SoapProcessor.PropertyType.TYPE_STRING);
        ksoap2.setProperty("passWord", passWord, SoapProcessor.PropertyType.TYPE_STRING);
        ksoap2.setProperty("tagEnd", tagEnd, SoapProcessor.PropertyType.TYPE_STRING);
        Log.e("login","ksoap2.request().getAsString()-----"+ksoap2.request().toString());
        return ksoap2.request().getAsString();
    }

    public static LoginFailureModel getLoigns(String userName, String passWord, String tagEnd) throws BizFailure, ZYException {
        SoapProcessor ksoap2 = new SoapProcessor("Service", "loginNew", false);
        ksoap2.setProperty("userName", userName, SoapProcessor.PropertyType.TYPE_STRING);
        ksoap2.setProperty("passWord", passWord, SoapProcessor.PropertyType.TYPE_STRING);
        ksoap2.setProperty("tagEnd", tagEnd, SoapProcessor.PropertyType.TYPE_STRING);
        Log.e("login","ksoap2.request().getAsString()-----"+ksoap2.request().toString());
        JsonElement element = ksoap2.request();
        Gson gson = new GsonBuilder().create();
        return gson.fromJson(element, LoginFailureModel.class);
    }


    //图形校验码
    public static CaptchaImageModel getImage() throws BizFailure,
            ZYException {
        SoapProcessor ksoap2 = new SoapProcessor("Service", "captchaImage", false);
        JsonElement element = ksoap2.request();
        Gson gson = new GsonBuilder().create();
        return gson.fromJson(element, CaptchaImageModel.class);
    }


}
