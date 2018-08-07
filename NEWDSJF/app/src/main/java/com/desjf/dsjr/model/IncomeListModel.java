package com.desjf.dsjr.model;

/**
 * Created by Administrator on 2017/7/26 0026.
 */

public class IncomeListModel {
    private String FUND_TYPE;
    private String AMOUNT;
    private String DATE;
    private String REVENUE_EXPEND_TYPE;

    public String getFUND_TYPE() {
        return FUND_TYPE;
    }

    public void setFUND_TYPE(String FUND_TYPE) {
        this.FUND_TYPE = FUND_TYPE;
    }

    public String getAMOUNT() {
        return AMOUNT;
    }

    public void setAMOUNT(String AMOUNT) {
        this.AMOUNT = AMOUNT;
    }

    public String getDATE() {
        return DATE;
    }

    public void setDATE(String DATE) {
        this.DATE = DATE;
    }

    public String getREVENUE_EXPEND_TYPE() {
        return REVENUE_EXPEND_TYPE;
    }

    public void setREVENUE_EXPEND_TYPE(String REVENUE_EXPEND_TYPE) {
        this.REVENUE_EXPEND_TYPE = REVENUE_EXPEND_TYPE;
    }
}
