package com.desjf.dsjr.model;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/7/27 0027.
 */

public class AllCouponModel implements Serializable{
    private String ACQUISITION_AMOUNT;
    private String COUPON_ORIGIN_AVENUE;
    private String INS_DATE;
    private String END_DATE;
    private String MINIMUM_TENDER_AMOUNT;
    private String MINIMUN_TENDER_AMOUNT_UNIT;
    private String TO_CASH_FLG;
    private String VALID_FLG;
    private String USED_FLG;
    private String USED_DATE;
    private String RED_PACKET_LOG_ID;
    private String RED_PACKET_TEMPLET_ID;
    private String COUPON_USES;
    private String RULE;
    private String USE_WAY_FLG;//红包使用 使用 未使用 过期
    private String YONG_YU;//已使用红包的使用方式

    public String getYONG_YU() {
        return YONG_YU;
    }

    public void setYONG_YU(String YONG_YU) {
        this.YONG_YU = YONG_YU;
    }

    public String getUSE_WAY_FLG() {
        return USE_WAY_FLG;
    }

    public void setUSE_WAY_FLG(String USE_WAY_FLG) {
        this.USE_WAY_FLG = USE_WAY_FLG;
    }

    public String getRULE() {
        return RULE;
    }

    public void setRULE(String RULE) {
        this.RULE = RULE;
    }

    public String getCOUPON_USES() {
        return COUPON_USES;
    }

    public void setCOUPON_USES(String COUPON_USES) {
        this.COUPON_USES = COUPON_USES;
    }

    public String getUSED_DATE() {
        return USED_DATE;
    }

    public void setUSED_DATE(String USED_DATE) {
        this.USED_DATE = USED_DATE;
    }

    public String getRED_PACKET_LOG_ID() {
        return RED_PACKET_LOG_ID;
    }

    public void setRED_PACKET_LOG_ID(String RED_PACKET_LOG_ID) {
        this.RED_PACKET_LOG_ID = RED_PACKET_LOG_ID;
    }

    public String getRED_PACKET_TEMPLET_ID() {
        return RED_PACKET_TEMPLET_ID;
    }

    public void setRED_PACKET_TEMPLET_ID(String RED_PACKET_TEMPLET_ID) {
        this.RED_PACKET_TEMPLET_ID = RED_PACKET_TEMPLET_ID;
    }

    public String getACQUISITION_AMOUNT() {
        return ACQUISITION_AMOUNT;
    }

    public void setACQUISITION_AMOUNT(String ACQUISITION_AMOUNT) {
        this.ACQUISITION_AMOUNT = ACQUISITION_AMOUNT;
    }

    public String getCOUPON_ORIGIN_AVENUE() {
        return COUPON_ORIGIN_AVENUE;
    }

    public void setCOUPON_ORIGIN_AVENUE(String COUPON_ORIGIN_AVENUE) {
        this.COUPON_ORIGIN_AVENUE = COUPON_ORIGIN_AVENUE;
    }

    public String getINS_DATE() {
        return INS_DATE;
    }

    public void setINS_DATE(String INS_DATE) {
        this.INS_DATE = INS_DATE;
    }

    public String getEND_DATE() {
        return END_DATE;
    }

    public void setEND_DATE(String END_DATE) {
        this.END_DATE = END_DATE;
    }

    public String getMINIMUM_TENDER_AMOUNT() {
        return MINIMUM_TENDER_AMOUNT;
    }

    public void setMINIMUM_TENDER_AMOUNT(String MINIMUM_TENDER_AMOUNT) {
        this.MINIMUM_TENDER_AMOUNT = MINIMUM_TENDER_AMOUNT;
    }

    public String getMINIMUN_TENDER_AMOUNT_UNIT() {
        return MINIMUN_TENDER_AMOUNT_UNIT;
    }

    public void setMINIMUN_TENDER_AMOUNT_UNIT(String MINIMUN_TENDER_AMOUNT_UNIT) {
        this.MINIMUN_TENDER_AMOUNT_UNIT = MINIMUN_TENDER_AMOUNT_UNIT;
    }

    public String getTO_CASH_FLG() {
        return TO_CASH_FLG;
    }

    public void setTO_CASH_FLG(String TO_CASH_FLG) {
        this.TO_CASH_FLG = TO_CASH_FLG;
    }

    public String getVALID_FLG() {
        return VALID_FLG;
    }

    public void setVALID_FLG(String VALID_FLG) {
        this.VALID_FLG = VALID_FLG;
    }

    public String getUSED_FLG() {
        return USED_FLG;
    }

    public void setUSED_FLG(String USED_FLG) {
        this.USED_FLG = USED_FLG;
    }
}
