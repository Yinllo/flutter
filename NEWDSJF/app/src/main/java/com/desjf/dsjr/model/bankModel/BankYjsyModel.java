package com.desjf.dsjr.model.bankModel;

/**
 * @Author YinL
 * @Date 2018/4/11 0011
 * @Describe
 */

public class BankYjsyModel {


    /**
     * status : true
     * expectedInvest : 7.50
     */

    private boolean status;
    private String expectedInvest;

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getExpectedInvest() {
        return expectedInvest == null ? "" : expectedInvest;
    }

    public void setExpectedInvest(String expectedInvest) {
        this.expectedInvest = expectedInvest;
    }
}
