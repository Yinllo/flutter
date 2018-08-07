package com.desjf.dsjr.model;

/**
 * Created by Administrator on 2017/7/31 0031.
 */

public class InterestTotalForTransferModel {
    private String DISCOUNT;
    private String ACTUAL_ACCOUNT;
    private String EXPECTED_INTEREST;
    private String INTEREST_TOTAL;

    public String getINTEREST_TOTAL() {
        return INTEREST_TOTAL;
    }

    public void setINTEREST_TOTAL(String INTEREST_TOTAL) {
        this.INTEREST_TOTAL = INTEREST_TOTAL;
    }

    public String getDISCOUNT() {
        return DISCOUNT;
    }

    public void setDISCOUNT(String DISCOUNT) {
        this.DISCOUNT = DISCOUNT;
    }

    public String getACTUAL_ACCOUNT() {
        return ACTUAL_ACCOUNT;
    }

    public void setACTUAL_ACCOUNT(String ACTUAL_ACCOUNT) {
        this.ACTUAL_ACCOUNT = ACTUAL_ACCOUNT;
    }

    public String getEXPECTED_INTEREST() {
        return EXPECTED_INTEREST;
    }

    public void setEXPECTED_INTEREST(String EXPECTED_INTEREST) {
        this.EXPECTED_INTEREST = EXPECTED_INTEREST;
    }
}
