package com.desjf.dsjr.model;

/**
 * Created by yc.Zhao on 2017/10/9 0009.
 */

public class WithDrawModel {
//    WITHDRAW_REQUEST_NO订单号
//            WITHDRAW_AMOUNT提现金额
//    ACTUAL_AMOUNT到账金额
//    INS_DATE_Y提现时间yyyy/MM/dd
//    INS_DATE_H提现时间HH:mm:ss
//    WITHDRAW_STATUS状态：0:未审核; 1:提现成功; 2：提现失败
//            OID_WITHDRAW_ID撤销接口用这个ID
//    SHOW_FLG撤销键是否显示：0隐藏，1显示
    private String  WITHDRAW_REQUEST_NO;
    private String  WITHDRAW_AMOUNT;
    private String  ACTUAL_AMOUNT;
    private String  INS_DATE_Y;
    private String  INS_DATE_H;
    private String  WITHDRAW_STATUS;
    private String  OID_WITHDRAW_ID;
    private String   SHOW_FLG;

    public String getWITHDRAW_REQUEST_NO() {
        return WITHDRAW_REQUEST_NO;
    }

    public void setWITHDRAW_REQUEST_NO(String WITHDRAW_REQUEST_NO) {
        this.WITHDRAW_REQUEST_NO = WITHDRAW_REQUEST_NO;
    }

    public String getWITHDRAW_AMOUNT() {
        return WITHDRAW_AMOUNT;
    }

    public void setWITHDRAW_AMOUNT(String WITHDRAW_AMOUNT) {
        this.WITHDRAW_AMOUNT = WITHDRAW_AMOUNT;
    }

    public String getACTUAL_AMOUNT() {
        return ACTUAL_AMOUNT;
    }

    public void setACTUAL_AMOUNT(String ACTUAL_AMOUNT) {
        this.ACTUAL_AMOUNT = ACTUAL_AMOUNT;
    }

    public String getINS_DATE_Y() {
        return INS_DATE_Y;
    }

    public void setINS_DATE_Y(String INS_DATE_Y) {
        this.INS_DATE_Y = INS_DATE_Y;
    }

    public String getINS_DATE_H() {
        return INS_DATE_H;
    }

    public void setINS_DATE_H(String INS_DATE_H) {
        this.INS_DATE_H = INS_DATE_H;
    }

    public String getWITHDRAW_STATUS() {
        return WITHDRAW_STATUS;
    }

    public void setWITHDRAW_STATUS(String WITHDRAW_STATUS) {
        this.WITHDRAW_STATUS = WITHDRAW_STATUS;
    }

    public String getOID_WITHDRAW_ID() {
        return OID_WITHDRAW_ID;
    }

    public void setOID_WITHDRAW_ID(String OID_WITHDRAW_ID) {
        this.OID_WITHDRAW_ID = OID_WITHDRAW_ID;
    }

    public String getSHOW_FLG() {
        return SHOW_FLG;
    }

    public void setSHOW_FLG(String SHOW_FLG) {
        this.SHOW_FLG = SHOW_FLG;
    }
}
