package com.desjf.dsjr.biz;

import com.desjf.dsjr.biz.exception.BizFailure;
import com.desjf.dsjr.biz.exception.ZYException;
import com.desjf.dsjr.biz.task.SoapProcessor;

/**
 * Created by Administrator on 2017/12/8 0008.
 */

public class ComplaintAdviceBiz {

    public static String ComplaintAdvice(String type,String imageURL,String explain) throws BizFailure, ZYException {

        SoapProcessor ksoap2 = new SoapProcessor("Service", "insComplaintAdvice", true);
        ksoap2.setProperty("type", type, SoapProcessor.PropertyType.TYPE_STRING);
        ksoap2.setProperty("imageURL", imageURL, SoapProcessor.PropertyType.TYPE_STRING);
        ksoap2.setProperty("explain", explain, SoapProcessor.PropertyType.TYPE_STRING);
        return ksoap2.request().getAsString();


    }

}
