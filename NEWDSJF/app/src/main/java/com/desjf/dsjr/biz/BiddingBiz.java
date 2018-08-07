package com.desjf.dsjr.biz;

import com.desjf.dsjr.biz.exception.BizFailure;
import com.desjf.dsjr.biz.exception.ZYException;
import com.desjf.dsjr.biz.task.SoapProcessor;

/**
 * Created by Administrator on 2017/7/29 0029.
 */

public class BiddingBiz {
    //投资
    public static String getBidding(String productsId, String tenderAccount,String directionalPwd,String redPackId,
                                    String rateCouponSendId,String transactionPassword) throws BizFailure, ZYException {

        SoapProcessor ksoap2 = new SoapProcessor("Service", "bidding", true);
        ksoap2.setProperty("productsId", productsId, SoapProcessor.PropertyType.TYPE_STRING);
        ksoap2.setProperty("tenderAccount", tenderAccount, SoapProcessor.PropertyType.TYPE_STRING);
        ksoap2.setProperty("directionalPwd", directionalPwd, SoapProcessor.PropertyType.TYPE_STRING);
        ksoap2.setProperty("redPackId", redPackId, SoapProcessor.PropertyType.TYPE_STRING);
        ksoap2.setProperty("rateCouponSendId", rateCouponSendId, SoapProcessor.PropertyType.TYPE_STRING);
        ksoap2.setProperty("transactionPassword", transactionPassword, SoapProcessor.PropertyType.TYPE_STRING);
        return ksoap2.request().getAsString();
    }
}
