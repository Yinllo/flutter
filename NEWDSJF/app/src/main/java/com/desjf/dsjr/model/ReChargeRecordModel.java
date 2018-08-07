package com.desjf.dsjr.model;

/**
 * Created by Administrator on 2017/9/7 0007.
 */

public class ReChargeRecordModel {
    private String RECHARGE_AMOUNT_FORMAT;
    private String ACTUAL_AMOUNT_FORMAT;
    private String ACTUAL_AMOUNT_FORMAT_UNIT;
    private String RECHARGE_DATE;
    private String RECHARGE_STATUS;

    public String getRECHARGE_AMOUNT_FORMAT() {
        return RECHARGE_AMOUNT_FORMAT;
    }

    public void setRECHARGE_AMOUNT_FORMAT(String RECHARGE_AMOUNT_FORMAT) {
        this.RECHARGE_AMOUNT_FORMAT = RECHARGE_AMOUNT_FORMAT;
    }

    public String getACTUAL_AMOUNT_FORMAT() {
        return ACTUAL_AMOUNT_FORMAT;
    }

    public void setACTUAL_AMOUNT_FORMAT(String ACTUAL_AMOUNT_FORMAT) {
        this.ACTUAL_AMOUNT_FORMAT = ACTUAL_AMOUNT_FORMAT;
    }

    public String getACTUAL_AMOUNT_FORMAT_UNIT() {
        return ACTUAL_AMOUNT_FORMAT_UNIT;
    }

    public void setACTUAL_AMOUNT_FORMAT_UNIT(String ACTUAL_AMOUNT_FORMAT_UNIT) {
        this.ACTUAL_AMOUNT_FORMAT_UNIT = ACTUAL_AMOUNT_FORMAT_UNIT;
    }

    public String getRECHARGE_DATE() {
        return RECHARGE_DATE;
    }

    public void setRECHARGE_DATE(String RECHARGE_DATE) {
        this.RECHARGE_DATE = RECHARGE_DATE;
    }

    public String getRECHARGE_STATUS() {
        return RECHARGE_STATUS;
    }

    public void setRECHARGE_STATUS(String RECHARGE_STATUS) {
        this.RECHARGE_STATUS = RECHARGE_STATUS;
    }
}
