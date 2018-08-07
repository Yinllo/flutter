package com.desjf.dsjr.model;

/**
 * Created by Administrator on 2017/8/15 0015.
 */

public class UserTransferInfoModel {
    private String PRODUCTS_TITLE;
    private String FAIR_VALUE;
    private String ENABLE_AMOUNT;
    private String PERIOD;
    private String FEE_RATE;

    public String getPRODUCTS_TITLE() {
        return PRODUCTS_TITLE;
    }

    public void setPRODUCTS_TITLE(String PRODUCTS_TITLE) {
        this.PRODUCTS_TITLE = PRODUCTS_TITLE;
    }

    public String getFAIR_VALUE() {
        return FAIR_VALUE;
    }

    public void setFAIR_VALUE(String FAIR_VALUE) {
        this.FAIR_VALUE = FAIR_VALUE;
    }

    public String getENABLE_AMOUNT() {
        return ENABLE_AMOUNT;
    }

    public void setENABLE_AMOUNT(String ENABLE_AMOUNT) {
        this.ENABLE_AMOUNT = ENABLE_AMOUNT;
    }

    public String getPERIOD() {
        return PERIOD;
    }

    public void setPERIOD(String PERIOD) {
        this.PERIOD = PERIOD;
    }

    public String getFEE_RATE() {
        return FEE_RATE;
    }

    public void setFEE_RATE(String FEE_RATE) {
        this.FEE_RATE = FEE_RATE;
    }
}
