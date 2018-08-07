package com.desjf.dsjr.model;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/8/4 0004.
 */

public class CalendarMonDetailsModel implements Serializable {
    private String PRODUCTS_TITLE;
    private String FINANCE_INTEREST_RATE;
    private String FINANCE_PERIOD;
    private String INTEREST_RATE_TYPE;
    private String RECOVER_DATE;
    private String CURRENT_PERIOD;
    private String RECOVER_AMOUNT_TOTAL;
    private String RECOVER_AMOUNT_CAPITAL;
    private String RECOVER_AMOUNT_CAPITAL_WEI;//本金
    private String RECOVER_AMOUNT_INTEREST;
    private String OVERDUE_FLG;
    private String RECOVER_FLG;
    private String CONCRETE_DATE;
    private String RECOVER_YEAR;
    private String RECOVER_MONTH;
    private String RECOVER_DAY;
    private String OID_TENDER_ID;
    private String TENDER_FROM;
    private String TRANSFER_CONTRACT_ID;

    public String getRECOVER_AMOUNT_CAPITAL_WEI() {
        return RECOVER_AMOUNT_CAPITAL_WEI;
    }

    public void setRECOVER_AMOUNT_CAPITAL_WEI(String RECOVER_AMOUNT_CAPITAL_WEI) {
        this.RECOVER_AMOUNT_CAPITAL_WEI = RECOVER_AMOUNT_CAPITAL_WEI;
    }

    public String getOID_TENDER_ID() {
        return OID_TENDER_ID;
    }

    public void setOID_TENDER_ID(String OID_TENDER_ID) {
        this.OID_TENDER_ID = OID_TENDER_ID;
    }

    public String getTENDER_FROM() {
        return TENDER_FROM;
    }

    public void setTENDER_FROM(String TENDER_FROM) {
        this.TENDER_FROM = TENDER_FROM;
    }

    public String getTRANSFER_CONTRACT_ID() {
        return TRANSFER_CONTRACT_ID;
    }

    public void setTRANSFER_CONTRACT_ID(String TRANSFER_CONTRACT_ID) {
        this.TRANSFER_CONTRACT_ID = TRANSFER_CONTRACT_ID;
    }

    public String getRECOVER_YEAR() {
        return RECOVER_YEAR;
    }

    public void setRECOVER_YEAR(String RECOVER_YEAR) {
        this.RECOVER_YEAR = RECOVER_YEAR;
    }

    public String getRECOVER_MONTH() {
        return RECOVER_MONTH;
    }

    public void setRECOVER_MONTH(String RECOVER_MONTH) {
        this.RECOVER_MONTH = RECOVER_MONTH;
    }

    public String getRECOVER_DAY() {
        return RECOVER_DAY;
    }

    public void setRECOVER_DAY(String RECOVER_DAY) {
        this.RECOVER_DAY = RECOVER_DAY;
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

    public String getINTEREST_RATE_TYPE() {
        return INTEREST_RATE_TYPE;
    }

    public void setINTEREST_RATE_TYPE(String INTEREST_RATE_TYPE) {
        this.INTEREST_RATE_TYPE = INTEREST_RATE_TYPE;
    }

    public String getRECOVER_DATE() {
        return RECOVER_DATE;
    }

    public void setRECOVER_DATE(String RECOVER_DATE) {
        this.RECOVER_DATE = RECOVER_DATE;
    }

    public String getCURRENT_PERIOD() {
        return CURRENT_PERIOD;
    }

    public void setCURRENT_PERIOD(String CURRENT_PERIOD) {
        this.CURRENT_PERIOD = CURRENT_PERIOD;
    }

    public String getRECOVER_AMOUNT_TOTAL() {
        return RECOVER_AMOUNT_TOTAL;
    }

    public void setRECOVER_AMOUNT_TOTAL(String RECOVER_AMOUNT_TOTAL) {
        this.RECOVER_AMOUNT_TOTAL = RECOVER_AMOUNT_TOTAL;
    }

    public String getRECOVER_AMOUNT_CAPITAL() {
        return RECOVER_AMOUNT_CAPITAL;
    }

    public void setRECOVER_AMOUNT_CAPITAL(String RECOVER_AMOUNT_CAPITAL) {
        this.RECOVER_AMOUNT_CAPITAL = RECOVER_AMOUNT_CAPITAL;
    }

    public String getRECOVER_AMOUNT_INTEREST() {
        return RECOVER_AMOUNT_INTEREST;
    }

    public void setRECOVER_AMOUNT_INTEREST(String RECOVER_AMOUNT_INTEREST) {
        this.RECOVER_AMOUNT_INTEREST = RECOVER_AMOUNT_INTEREST;
    }

    public String getOVERDUE_FLG() {
        return OVERDUE_FLG;
    }

    public void setOVERDUE_FLG(String OVERDUE_FLG) {
        this.OVERDUE_FLG = OVERDUE_FLG;
    }

    public String getRECOVER_FLG() {
        return RECOVER_FLG;
    }

    public void setRECOVER_FLG(String RECOVER_FLG) {
        this.RECOVER_FLG = RECOVER_FLG;
    }

    public String getCONCRETE_DATE() {
        return CONCRETE_DATE;
    }

    public void setCONCRETE_DATE(String CONCRETE_DATE) {
        this.CONCRETE_DATE = CONCRETE_DATE;
    }
}
