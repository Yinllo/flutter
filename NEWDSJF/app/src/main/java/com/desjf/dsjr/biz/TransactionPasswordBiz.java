package com.desjf.dsjr.biz;

import com.desjf.dsjr.biz.exception.BizFailure;
import com.desjf.dsjr.biz.exception.ZYException;
import com.desjf.dsjr.biz.task.SoapProcessor;

/**
 * 交易密码相关新接口类
 */

public class TransactionPasswordBiz {

    //判断是否有交易密码
    public static String ifSettedPayPassword() throws BizFailure, ZYException {

        SoapProcessor ksoap2 = new SoapProcessor("Service", "ifSettedPayPassword", true);

        return ksoap2.request().getAsString();

    }
    //设置交易密码
    public static String SettingPayPassword(String paypwd) throws BizFailure, ZYException {
        SoapProcessor ksoap2 = new SoapProcessor("Service", "setPayPassword", true);
        ksoap2.setProperty("newPassword", paypwd, SoapProcessor.PropertyType.TYPE_STRING);
        return ksoap2.request().getAsString();

    }



}
