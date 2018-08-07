package com.desjf.dsjr.biz;

import android.util.Log;

import com.desjf.dsjr.biz.exception.BizFailure;
import com.desjf.dsjr.biz.exception.ZYException;
import com.desjf.dsjr.biz.task.SoapProcessor;
import com.desjf.dsjr.config.DsjrConfig;
import com.desjf.dsjr.model.HomeModel;
import com.desjf.dsjr.model.VersionDescription;
import com.desjf.dsjr.utils.HttpUtil;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;

/**
 * Created by Administrator on 2017/7/24 0024.
 */

public class HomeBiz {
    //首页广告条数据
    public static HomeModel getCaptchaImage() throws BizFailure,
            ZYException {
        SoapProcessor ksoap2 = new SoapProcessor("Service", "getHomePage", true);
        JsonElement element = ksoap2.request();
        Gson gson = new GsonBuilder().create();
        return gson.fromJson(element, HomeModel.class);
    }

    //检测版本更新
    public static VersionDescription detectNewVersion() throws ZYException{
        try {
            String result = HttpUtil.postRespString(DsjrConfig.VERSION_DETECTION_URL);
            Gson gson = new Gson();

            Log.e("版本",result);
            return gson.fromJson(result,VersionDescription.class);
        }catch (Exception e){
            throw new ZYException();
        }
    }
}
