package com.desjf.dsjr.model;

/**
 * Created by Administrator on 2017/8/14 0014.
 */

public class TransferInvestorsModel {
    private String RECOVER_DATE_FORMAT;
    private String RECOVER_PERIOD;
    private String RECOVER_AMOUNT_FORMAT;
    private String BUY_DATE;
    private String BUY_AMOUNT;
    private String REAL_AMOUNT;

    public String getRECOVER_DATE_FORMAT() {
        return RECOVER_DATE_FORMAT;
    }

    public void setRECOVER_DATE_FORMAT(String RECOVER_DATE_FORMAT) {
        this.RECOVER_DATE_FORMAT = RECOVER_DATE_FORMAT;
    }

    public String getRECOVER_PERIOD() {
        return RECOVER_PERIOD;
    }

    public void setRECOVER_PERIOD(String RECOVER_PERIOD) {
        this.RECOVER_PERIOD = RECOVER_PERIOD;
    }

    public String getRECOVER_AMOUNT_FORMAT() {
        return RECOVER_AMOUNT_FORMAT;
    }

    public void setRECOVER_AMOUNT_FORMAT(String RECOVER_AMOUNT_FORMAT) {
        this.RECOVER_AMOUNT_FORMAT = RECOVER_AMOUNT_FORMAT;
    }

    public String getBUY_DATE() {
        return BUY_DATE;
    }

    public void setBUY_DATE(String BUY_DATE) {
        this.BUY_DATE = BUY_DATE;
    }

    public String getBUY_AMOUNT() {
        return BUY_AMOUNT;
    }

    public void setBUY_AMOUNT(String BUY_AMOUNT) {
        this.BUY_AMOUNT = BUY_AMOUNT;
    }

    public String getREAL_AMOUNT() {
        return REAL_AMOUNT;
    }

    public void setREAL_AMOUNT(String REAL_AMOUNT) {
        this.REAL_AMOUNT = REAL_AMOUNT;
    }
}
