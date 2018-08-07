package com.desjf.dsjr.model;

/**
 * Created by Administrator on 2017/8/10 0010.
 */

public class InvestorsModel {
    private String USER_PHONE;
    private String TENDER_AMOUNT_FORMAT;
    private String TENDER_DATE;
    private String TENDER_TYPE;

    public String getUSER_PHONE() {
        return USER_PHONE;
    }

    public void setUSER_PHONE(String USER_PHONE) {
        this.USER_PHONE = USER_PHONE;
    }

    public String getTENDER_AMOUNT_FORMAT() {
        return TENDER_AMOUNT_FORMAT;
    }

    public void setTENDER_AMOUNT_FORMAT(String TENDER_AMOUNT_FORMAT) {
        this.TENDER_AMOUNT_FORMAT = TENDER_AMOUNT_FORMAT;
    }

    public String getTENDER_DATE() {
        return TENDER_DATE;
    }

    public void setTENDER_DATE(String TENDER_DATE) {
        this.TENDER_DATE = TENDER_DATE;
    }

    public String getTENDER_TYPE() {
        return TENDER_TYPE;
    }

    public void setTENDER_TYPE(String TENDER_TYPE) {
        this.TENDER_TYPE = TENDER_TYPE;
    }
}
