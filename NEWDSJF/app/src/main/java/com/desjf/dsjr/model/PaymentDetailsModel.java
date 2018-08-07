package com.desjf.dsjr.model;

/**
 * Created by Administrator on 2017/8/9 0009.
 */

public class PaymentDetailsModel {
    private String RECOVER_CNT;
    private String RECOVER_DATE;
    private String RECOVER_AMOUNT_CAPITAL;
    private String RECOVER_AMOUNT_INTEREST;
    private String OVERDUE_FLG;
    private String SUB_STATUS;
    private String OVERDUE_DAY;
    private String OVERDUE_INTEREST;
    private String OVERDUE_FORFEIT;

    public String getOVERDUE_FORFEIT() {
        return OVERDUE_FORFEIT;
    }

    public void setOVERDUE_FORFEIT(String OVERDUE_FORFEIT) {
        this.OVERDUE_FORFEIT = OVERDUE_FORFEIT;
    }

    public String getRECOVER_CNT() {
        return RECOVER_CNT;
    }

    public void setRECOVER_CNT(String RECOVER_CNT) {
        this.RECOVER_CNT = RECOVER_CNT;
    }

    public String getRECOVER_DATE() {
        return RECOVER_DATE;
    }

    public void setRECOVER_DATE(String RECOVER_DATE) {
        this.RECOVER_DATE = RECOVER_DATE;
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

    public String getSUB_STATUS() {
        return SUB_STATUS;
    }

    public void setSUB_STATUS(String SUB_STATUS) {
        this.SUB_STATUS = SUB_STATUS;
    }

    public String getOVERDUE_DAY() {
        return OVERDUE_DAY;
    }

    public void setOVERDUE_DAY(String OVERDUE_DAY) {
        this.OVERDUE_DAY = OVERDUE_DAY;
    }

    public String getOVERDUE_INTEREST() {
        return OVERDUE_INTEREST;
    }

    public void setOVERDUE_INTEREST(String OVERDUE_INTEREST) {
        this.OVERDUE_INTEREST = OVERDUE_INTEREST;
    }


}
