package com.desjf.dsjr.model;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/7/29 0029.
 */

public class ProjectInfoModel implements Serializable {
    private String PRODUCTS_TITLE;
    private String FINANCE_INTEREST_RATE;
    private String FINANCE_PERIOD;
    private String INIEREST_RATE_TYPE;
    private String FINANCE_AMOUNT;
    private String SURPLUS_AMOUNT;
    private String FINANCE_AMOUNT_SCALE;
    private String SURPLUS_FEN;
    private String START_INTEREST_TYPE;
    private String GUARANTEE_TYPE;
    private String FINANCE_START_DATE;
    private String FINANCE_END_DATE;
    private String SYSTEM_DATE_NOW;
    private String BALANCE;
    private String MOBILE;
    private String DIRECTIONAL_FLG;
    private String ID_CARD_VERIFY_FLG;
    private String BANK_FLG;
    private String INTEREST_RATE_TYPE;
    private String FINANCE_REPAY_TYPE;
    private String WANDER_FLG;
    private String PART_AMOUNT;
    private String RED_PACKET_CNT;
    private String COUPON_RATE_CNT;
    private String FINANCE_AMOUNT_UNIT;
    private String PRODUCTS_STATUS;
    private String TENDER_MAX_CAPTION;
    private String TENDER_MIN_CAPTION;
    private String TENDER_AWARD_SCALE;
    private  String FINANCE_STATUS;//状态
    private  String TOTAL_COUPON_NUM;//哦红包和加息券的总数
    private String FULL_TIP;//满标人信息
    private String NOVICE_FLG;//  新手标  1是新手标 0是非新手标
    private  String FULL_AWARD;//满标人奖励金额
    private  String ADVERTISE_WORD;//广告位文字显示
    private String noviceLimit; //新手标可投额度
    private String oidFinanceProductsTypeId;
    private  String addRate;//加息

    public String getAddRate() {
        return addRate == null ? "" : addRate;
    }

    public void setAddRate(String addRate) {
        this.addRate = addRate;
    }

    public String getOidFinanceProductsTypeId() {
        return oidFinanceProductsTypeId == null ? "" : oidFinanceProductsTypeId;
    }

    public void setOidFinanceProductsTypeId(String oidFinanceProductsTypeId) {
        this.oidFinanceProductsTypeId = oidFinanceProductsTypeId;
    }

    public String getNoviceLimit() {
        return noviceLimit;
    }

    public void setNoviceLimit(String noviceLimit) {
        this.noviceLimit = noviceLimit;
    }

    public String getADVERTISE_WORD() {
        return ADVERTISE_WORD;
    }

    public void setADVERTISE_WORD(String ADVERTISE_WORD) {
        this.ADVERTISE_WORD = ADVERTISE_WORD;
    }

    public String getFULL_AWARD() {
        return FULL_AWARD;
    }

    public void setFULL_AWARD(String FULL_AWARD) {
        this.FULL_AWARD = FULL_AWARD;
    }

    public String getNOVICE_FLG() {
        return NOVICE_FLG;
    }

    public void setNOVICE_FLG(String NOVICE_FLG) {
        this.NOVICE_FLG = NOVICE_FLG;
    }

    public String getFINANCE_STATUS() {
        return FINANCE_STATUS;
    }

    public void setFINANCE_STATUS(String FINANCE_STATUS) {
        this.FINANCE_STATUS = FINANCE_STATUS;
    }

    public String getTOTAL_COUPON_NUM() {
        return TOTAL_COUPON_NUM;
    }

    public void setTOTAL_COUPON_NUM(String TOTAL_COUPON_NUM) {
        this.TOTAL_COUPON_NUM = TOTAL_COUPON_NUM;
    }

    public String getFULL_TIP() {
        return FULL_TIP;
    }

    public void setFULL_TIP(String FULL_TIP) {
        this.FULL_TIP = FULL_TIP;
    }

    public String getTENDER_AWARD_SCALE() {
        return TENDER_AWARD_SCALE;
    }

    public void setTENDER_AWARD_SCALE(String TENDER_AWARD_SCALE) {
        this.TENDER_AWARD_SCALE = TENDER_AWARD_SCALE;
    }

    public String getTENDER_MAX_CAPTION() {
        return TENDER_MAX_CAPTION;
    }

    public void setTENDER_MAX_CAPTION(String TENDER_MAX_CAPTION) {
        this.TENDER_MAX_CAPTION = TENDER_MAX_CAPTION;
    }

    public String getTENDER_MIN_CAPTION() {
        return TENDER_MIN_CAPTION;
    }

    public void setTENDER_MIN_CAPTION(String TENDER_MIN_CAPTION) {
        this.TENDER_MIN_CAPTION = TENDER_MIN_CAPTION;
    }

    public String getPRODUCTS_STATUS() {
        return PRODUCTS_STATUS;
    }

    public void setPRODUCTS_STATUS(String PRODUCTS_STATUS) {
        this.PRODUCTS_STATUS = PRODUCTS_STATUS;
    }

    public String getFINANCE_AMOUNT_UNIT() {
        return FINANCE_AMOUNT_UNIT;
    }

    public void setFINANCE_AMOUNT_UNIT(String FINANCE_AMOUNT_UNIT) {
        this.FINANCE_AMOUNT_UNIT = FINANCE_AMOUNT_UNIT;
    }

    public String getRED_PACKET_CNT() {
        return RED_PACKET_CNT;
    }

    public void setRED_PACKET_CNT(String RED_PACKET_CNT) {
        this.RED_PACKET_CNT = RED_PACKET_CNT;
    }

    public String getCOUPON_RATE_CNT() {
        return COUPON_RATE_CNT;
    }

    public void setCOUPON_RATE_CNT(String COUPON_RATE_CNT) {
        this.COUPON_RATE_CNT = COUPON_RATE_CNT;
    }

    public String getWANDER_FLG() {
        return WANDER_FLG;
    }

    public void setWANDER_FLG(String WANDER_FLG) {
        this.WANDER_FLG = WANDER_FLG;
    }

    public String getPART_AMOUNT() {
        return PART_AMOUNT;
    }

    public void setPART_AMOUNT(String PART_AMOUNT) {
        this.PART_AMOUNT = PART_AMOUNT;
    }

    public String getFINANCE_REPAY_TYPE() {
        return FINANCE_REPAY_TYPE;
    }

    public void setFINANCE_REPAY_TYPE(String FINANCE_REPAY_TYPE) {
        this.FINANCE_REPAY_TYPE = FINANCE_REPAY_TYPE;
    }

    public String getINTEREST_RATE_TYPE() {
        return INTEREST_RATE_TYPE;
    }

    public void setINTEREST_RATE_TYPE(String INTEREST_RATE_TYPE) {
        this.INTEREST_RATE_TYPE = INTEREST_RATE_TYPE;
    }

    public String getID_CARD_VERIFY_FLG() {
        return ID_CARD_VERIFY_FLG;
    }

    public void setID_CARD_VERIFY_FLG(String ID_CARD_VERIFY_FLG) {
        this.ID_CARD_VERIFY_FLG = ID_CARD_VERIFY_FLG;
    }

    public String getBANK_FLG() {
        return BANK_FLG;
    }

    public void setBANK_FLG(String BANK_FLG) {
        this.BANK_FLG = BANK_FLG;
    }

    public String getBALANCE() {
        return BALANCE;
    }

    public void setBALANCE(String BALANCE) {
        this.BALANCE = BALANCE;
    }

    public String getMOBILE() {
        return MOBILE;
    }

    public void setMOBILE(String MOBILE) {
        this.MOBILE = MOBILE;
    }

    public String getDIRECTIONAL_FLG() {
        return DIRECTIONAL_FLG;
    }

    public void setDIRECTIONAL_FLG(String DIRECTIONAL_FLG) {
        this.DIRECTIONAL_FLG = DIRECTIONAL_FLG;
    }

    public String getPRODUCTS_TITLE() {
        return PRODUCTS_TITLE;
    }

    public void setPRODUCTS_TITLE(String PRODUCTS_TITLE) {
        this.PRODUCTS_TITLE = PRODUCTS_TITLE;
    }

    public String getFINANCE_INTEREST_RATE() {
        return FINANCE_INTEREST_RATE;
    }

    public void setFINANCE_INTEREST_RATE(String FINANCE_INTEREST_RATE) {
        this.FINANCE_INTEREST_RATE = FINANCE_INTEREST_RATE;
    }

    public String getFINANCE_PERIOD() {
        return FINANCE_PERIOD;
    }

    public void setFINANCE_PERIOD(String FINANCE_PERIOD) {
        this.FINANCE_PERIOD = FINANCE_PERIOD;
    }

    public String getINIEREST_RATE_TYPE() {
        return INIEREST_RATE_TYPE;
    }

    public void setINIEREST_RATE_TYPE(String INIEREST_RATE_TYPE) {
        this.INIEREST_RATE_TYPE = INIEREST_RATE_TYPE;
    }

    public String getFINANCE_AMOUNT() {
        return FINANCE_AMOUNT;
    }

    public void setFINANCE_AMOUNT(String FINANCE_AMOUNT) {
        this.FINANCE_AMOUNT = FINANCE_AMOUNT;
    }

    public String getSURPLUS_AMOUNT() {
        return SURPLUS_AMOUNT;
    }

    public void setSURPLUS_AMOUNT(String SURPLUS_AMOUNT) {
        this.SURPLUS_AMOUNT = SURPLUS_AMOUNT;
    }

    public String getFINANCE_AMOUNT_SCALE() {
        return FINANCE_AMOUNT_SCALE;
    }

    public void setFINANCE_AMOUNT_SCALE(String FINANCE_AMOUNT_SCALE) {
        this.FINANCE_AMOUNT_SCALE = FINANCE_AMOUNT_SCALE;
    }

    public String getSURPLUS_FEN() {
        return SURPLUS_FEN;
    }

    public void setSURPLUS_FEN(String SURPLUS_FEN) {
        this.SURPLUS_FEN = SURPLUS_FEN;
    }

    public String getSTART_INTEREST_TYPE() {
        return START_INTEREST_TYPE;
    }

    public void setSTART_INTEREST_TYPE(String START_INTEREST_TYPE) {
        this.START_INTEREST_TYPE = START_INTEREST_TYPE;
    }

    public String getGUARANTEE_TYPE() {
        return GUARANTEE_TYPE;
    }

    public void setGUARANTEE_TYPE(String GUARANTEE_TYPE) {
        this.GUARANTEE_TYPE = GUARANTEE_TYPE;
    }

    public String getFINANCE_START_DATE() {
        return FINANCE_START_DATE;
    }

    public void setFINANCE_START_DATE(String FINANCE_START_DATE) {
        this.FINANCE_START_DATE = FINANCE_START_DATE;
    }

    public String getFINANCE_END_DATE() {
        return FINANCE_END_DATE;
    }

    public void setFINANCE_END_DATE(String FINANCE_END_DATE) {
        this.FINANCE_END_DATE = FINANCE_END_DATE;
    }

    public String getSYSTEM_DATE_NOW() {
        return SYSTEM_DATE_NOW;
    }

    public void setSYSTEM_DATE_NOW(String SYSTEM_DATE_NOW) {
        this.SYSTEM_DATE_NOW = SYSTEM_DATE_NOW;
    }
}
