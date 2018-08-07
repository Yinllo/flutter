package com.desjf.dsjr.model;

/**
 * Created by Administrator on 2017/8/15 0015.
 */

public class AjaxEventModel {
    private String FAIR_VALUE;
    private String DISCOUNT_RATE;
    private String FEE;
    private String REAL_TRANSFER_VALUE;

    public String getFAIR_VALUE() {
        return FAIR_VALUE;
    }

    public void setFAIR_VALUE(String FAIR_VALUE) {
        this.FAIR_VALUE = FAIR_VALUE;
    }

    public String getDISCOUNT_RATE() {
        return DISCOUNT_RATE;
    }

    public void setDISCOUNT_RATE(String DISCOUNT_RATE) {
        this.DISCOUNT_RATE = DISCOUNT_RATE;
    }

    public String getFEE() {
        return FEE;
    }

    public void setFEE(String FEE) {
        this.FEE = FEE;
    }

    public String getREAL_TRANSFER_VALUE() {
        return REAL_TRANSFER_VALUE;
    }

    public void setREAL_TRANSFER_VALUE(String REAL_TRANSFER_VALUE) {
        this.REAL_TRANSFER_VALUE = REAL_TRANSFER_VALUE;
    }
}

