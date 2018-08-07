package com.desjf.dsjr.model.bankModel;

import java.util.List;

/**
 * @Author YinL
 * @Date 2018/4/12 0012
 * @Describe
 */

public class BankInvestRecordModel {


    /**
     * investorCount : 15
     * status : true
     * investorList : [{"OID_TENDER_ID":"a7247d5000a445ac826019c1dc190d56","OID_PLATFORM_PRODUCTS_ID":"4f73833ed8574dbb98ef14def322fd25","OID_USER_ID":"ba34eeb422304d3fb5e5f8a64c8fab43","TENDER_AMOUNT":100,"RECOVER_AMOUNT_TOTAL":103,"RECOVER_AMOUNT_INTEREST":3,"RECOVER_AMOUNT_CAPITAL":100,"RECOVER_AMOUNT_TOTAL_YES":0,"RECOVER_AMOUNT_INTEREST_YES":0,"RECOVER_AMOUNT_CAPITAL_YES":0,"RECOVER_AMOUNT_TOTAL_WAIT":103,"RECOVER_AMOUNT_INTEREST_WAIT":3,"RECOVER_AMOUNT_CAPITAL_WAIT":100,"RECOVER_CNT":0,"RECOVER_AMOUNT_COUPON":0,"RECOVER_AMOUNT_COUPON_YES":0,"RECOVER_AMOUNT_COUPON_WAIT":0,"COUPON_RATE":0,"RATE_COUPON_SEND_ID":null,"OVERDUE_INTEREST":0,"OVERDUE_FORFEIT":0,"TRANSFER_AMOUNT_SUCCESS":0,"TRANSFER_AMOUNT_IN_PROGRESS":0,"TENDER_STATUS":"6","AUTO_STATUS":"0","REQUEST_NO":"4f73833ed8574dbb98ef14def322fd25","INS_DATE":1523525843000,"TAG_END_MODE":"2","MOBILE":"15675122816","EMAIL":"","USER_TYPE":"0","RED_PACKET_USE_LOG_ID":null,"AMOUNT":null,"OPERATE_TYPE":null,"userId":"156****2816","tenderAmount":"100","tenderDate":"2018-04-12 17:37:23","terminal":"2"}]
     */

    private int investorCount;
    private boolean status;
    private List<InvestorListBean> investorList;

    public int getInvestorCount() {
        return investorCount;
    }

    public void setInvestorCount(int investorCount) {
        this.investorCount = investorCount;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public List<InvestorListBean> getInvestorList() {
        return investorList;
    }

    public void setInvestorList(List<InvestorListBean> investorList) {
        this.investorList = investorList;
    }

    public static class InvestorListBean {
        /**
         * OID_TENDER_ID : a7247d5000a445ac826019c1dc190d56
         * OID_PLATFORM_PRODUCTS_ID : 4f73833ed8574dbb98ef14def322fd25
         * OID_USER_ID : ba34eeb422304d3fb5e5f8a64c8fab43
         * TENDER_AMOUNT : 100.0
         * RECOVER_AMOUNT_TOTAL : 103.0
         * RECOVER_AMOUNT_INTEREST : 3.0
         * RECOVER_AMOUNT_CAPITAL : 100.0
         * RECOVER_AMOUNT_TOTAL_YES : 0.0
         * RECOVER_AMOUNT_INTEREST_YES : 0.0
         * RECOVER_AMOUNT_CAPITAL_YES : 0.0
         * RECOVER_AMOUNT_TOTAL_WAIT : 103.0
         * RECOVER_AMOUNT_INTEREST_WAIT : 3.0
         * RECOVER_AMOUNT_CAPITAL_WAIT : 100.0
         * RECOVER_CNT : 0
         * RECOVER_AMOUNT_COUPON : 0.0
         * RECOVER_AMOUNT_COUPON_YES : 0.0
         * RECOVER_AMOUNT_COUPON_WAIT : 0.0
         * COUPON_RATE : 0.0
         * RATE_COUPON_SEND_ID : null
         * OVERDUE_INTEREST : 0.0
         * OVERDUE_FORFEIT : 0.0
         * TRANSFER_AMOUNT_SUCCESS : 0.0
         * TRANSFER_AMOUNT_IN_PROGRESS : 0.0
         * TENDER_STATUS : 6
         * AUTO_STATUS : 0
         * REQUEST_NO : 4f73833ed8574dbb98ef14def322fd25
         * INS_DATE : 1523525843000
         * TAG_END_MODE : 2
         * MOBILE : 15675122816
         * EMAIL :
         * USER_TYPE : 0
         * RED_PACKET_USE_LOG_ID : null
         * AMOUNT : null
         * OPERATE_TYPE : null
         * userId : 156****2816
         * tenderAmount : 100
         * tenderDate : 2018-04-12 17:37:23
         * terminal : 2
         */

        private String OID_TENDER_ID;
        private String OID_PLATFORM_PRODUCTS_ID;
        private String OID_USER_ID;
        private double TENDER_AMOUNT;
        private double RECOVER_AMOUNT_TOTAL;
        private double RECOVER_AMOUNT_INTEREST;
        private double RECOVER_AMOUNT_CAPITAL;
        private double RECOVER_AMOUNT_TOTAL_YES;
        private double RECOVER_AMOUNT_INTEREST_YES;
        private double RECOVER_AMOUNT_CAPITAL_YES;
        private double RECOVER_AMOUNT_TOTAL_WAIT;
        private double RECOVER_AMOUNT_INTEREST_WAIT;
        private double RECOVER_AMOUNT_CAPITAL_WAIT;
        private int RECOVER_CNT;
        private double RECOVER_AMOUNT_COUPON;
        private double RECOVER_AMOUNT_COUPON_YES;
        private double RECOVER_AMOUNT_COUPON_WAIT;
        private double COUPON_RATE;
        private Object RATE_COUPON_SEND_ID;
        private double OVERDUE_INTEREST;
        private double OVERDUE_FORFEIT;
        private double TRANSFER_AMOUNT_SUCCESS;
        private double TRANSFER_AMOUNT_IN_PROGRESS;
        private String TENDER_STATUS;
        private String AUTO_STATUS;
        private String REQUEST_NO;
        private long INS_DATE;
        private String TAG_END_MODE;
        private String MOBILE;
        private String EMAIL;
        private String USER_TYPE;
        private Object RED_PACKET_USE_LOG_ID;
        private Object AMOUNT;
        private Object OPERATE_TYPE;
        private String userId;
        private String tenderAmount;
        private String tenderDate;
        private String terminal;

        public String getOID_TENDER_ID() {
            return OID_TENDER_ID;
        }

        public void setOID_TENDER_ID(String OID_TENDER_ID) {
            this.OID_TENDER_ID = OID_TENDER_ID;
        }

        public String getOID_PLATFORM_PRODUCTS_ID() {
            return OID_PLATFORM_PRODUCTS_ID;
        }

        public void setOID_PLATFORM_PRODUCTS_ID(String OID_PLATFORM_PRODUCTS_ID) {
            this.OID_PLATFORM_PRODUCTS_ID = OID_PLATFORM_PRODUCTS_ID;
        }

        public String getOID_USER_ID() {
            return OID_USER_ID;
        }

        public void setOID_USER_ID(String OID_USER_ID) {
            this.OID_USER_ID = OID_USER_ID;
        }

        public double getTENDER_AMOUNT() {
            return TENDER_AMOUNT;
        }

        public void setTENDER_AMOUNT(double TENDER_AMOUNT) {
            this.TENDER_AMOUNT = TENDER_AMOUNT;
        }

        public double getRECOVER_AMOUNT_TOTAL() {
            return RECOVER_AMOUNT_TOTAL;
        }

        public void setRECOVER_AMOUNT_TOTAL(double RECOVER_AMOUNT_TOTAL) {
            this.RECOVER_AMOUNT_TOTAL = RECOVER_AMOUNT_TOTAL;
        }

        public double getRECOVER_AMOUNT_INTEREST() {
            return RECOVER_AMOUNT_INTEREST;
        }

        public void setRECOVER_AMOUNT_INTEREST(double RECOVER_AMOUNT_INTEREST) {
            this.RECOVER_AMOUNT_INTEREST = RECOVER_AMOUNT_INTEREST;
        }

        public double getRECOVER_AMOUNT_CAPITAL() {
            return RECOVER_AMOUNT_CAPITAL;
        }

        public void setRECOVER_AMOUNT_CAPITAL(double RECOVER_AMOUNT_CAPITAL) {
            this.RECOVER_AMOUNT_CAPITAL = RECOVER_AMOUNT_CAPITAL;
        }

        public double getRECOVER_AMOUNT_TOTAL_YES() {
            return RECOVER_AMOUNT_TOTAL_YES;
        }

        public void setRECOVER_AMOUNT_TOTAL_YES(double RECOVER_AMOUNT_TOTAL_YES) {
            this.RECOVER_AMOUNT_TOTAL_YES = RECOVER_AMOUNT_TOTAL_YES;
        }

        public double getRECOVER_AMOUNT_INTEREST_YES() {
            return RECOVER_AMOUNT_INTEREST_YES;
        }

        public void setRECOVER_AMOUNT_INTEREST_YES(double RECOVER_AMOUNT_INTEREST_YES) {
            this.RECOVER_AMOUNT_INTEREST_YES = RECOVER_AMOUNT_INTEREST_YES;
        }

        public double getRECOVER_AMOUNT_CAPITAL_YES() {
            return RECOVER_AMOUNT_CAPITAL_YES;
        }

        public void setRECOVER_AMOUNT_CAPITAL_YES(double RECOVER_AMOUNT_CAPITAL_YES) {
            this.RECOVER_AMOUNT_CAPITAL_YES = RECOVER_AMOUNT_CAPITAL_YES;
        }

        public double getRECOVER_AMOUNT_TOTAL_WAIT() {
            return RECOVER_AMOUNT_TOTAL_WAIT;
        }

        public void setRECOVER_AMOUNT_TOTAL_WAIT(double RECOVER_AMOUNT_TOTAL_WAIT) {
            this.RECOVER_AMOUNT_TOTAL_WAIT = RECOVER_AMOUNT_TOTAL_WAIT;
        }

        public double getRECOVER_AMOUNT_INTEREST_WAIT() {
            return RECOVER_AMOUNT_INTEREST_WAIT;
        }

        public void setRECOVER_AMOUNT_INTEREST_WAIT(double RECOVER_AMOUNT_INTEREST_WAIT) {
            this.RECOVER_AMOUNT_INTEREST_WAIT = RECOVER_AMOUNT_INTEREST_WAIT;
        }

        public double getRECOVER_AMOUNT_CAPITAL_WAIT() {
            return RECOVER_AMOUNT_CAPITAL_WAIT;
        }

        public void setRECOVER_AMOUNT_CAPITAL_WAIT(double RECOVER_AMOUNT_CAPITAL_WAIT) {
            this.RECOVER_AMOUNT_CAPITAL_WAIT = RECOVER_AMOUNT_CAPITAL_WAIT;
        }

        public int getRECOVER_CNT() {
            return RECOVER_CNT;
        }

        public void setRECOVER_CNT(int RECOVER_CNT) {
            this.RECOVER_CNT = RECOVER_CNT;
        }

        public double getRECOVER_AMOUNT_COUPON() {
            return RECOVER_AMOUNT_COUPON;
        }

        public void setRECOVER_AMOUNT_COUPON(double RECOVER_AMOUNT_COUPON) {
            this.RECOVER_AMOUNT_COUPON = RECOVER_AMOUNT_COUPON;
        }

        public double getRECOVER_AMOUNT_COUPON_YES() {
            return RECOVER_AMOUNT_COUPON_YES;
        }

        public void setRECOVER_AMOUNT_COUPON_YES(double RECOVER_AMOUNT_COUPON_YES) {
            this.RECOVER_AMOUNT_COUPON_YES = RECOVER_AMOUNT_COUPON_YES;
        }

        public double getRECOVER_AMOUNT_COUPON_WAIT() {
            return RECOVER_AMOUNT_COUPON_WAIT;
        }

        public void setRECOVER_AMOUNT_COUPON_WAIT(double RECOVER_AMOUNT_COUPON_WAIT) {
            this.RECOVER_AMOUNT_COUPON_WAIT = RECOVER_AMOUNT_COUPON_WAIT;
        }

        public double getCOUPON_RATE() {
            return COUPON_RATE;
        }

        public void setCOUPON_RATE(double COUPON_RATE) {
            this.COUPON_RATE = COUPON_RATE;
        }

        public Object getRATE_COUPON_SEND_ID() {
            return RATE_COUPON_SEND_ID;
        }

        public void setRATE_COUPON_SEND_ID(Object RATE_COUPON_SEND_ID) {
            this.RATE_COUPON_SEND_ID = RATE_COUPON_SEND_ID;
        }

        public double getOVERDUE_INTEREST() {
            return OVERDUE_INTEREST;
        }

        public void setOVERDUE_INTEREST(double OVERDUE_INTEREST) {
            this.OVERDUE_INTEREST = OVERDUE_INTEREST;
        }

        public double getOVERDUE_FORFEIT() {
            return OVERDUE_FORFEIT;
        }

        public void setOVERDUE_FORFEIT(double OVERDUE_FORFEIT) {
            this.OVERDUE_FORFEIT = OVERDUE_FORFEIT;
        }

        public double getTRANSFER_AMOUNT_SUCCESS() {
            return TRANSFER_AMOUNT_SUCCESS;
        }

        public void setTRANSFER_AMOUNT_SUCCESS(double TRANSFER_AMOUNT_SUCCESS) {
            this.TRANSFER_AMOUNT_SUCCESS = TRANSFER_AMOUNT_SUCCESS;
        }

        public double getTRANSFER_AMOUNT_IN_PROGRESS() {
            return TRANSFER_AMOUNT_IN_PROGRESS;
        }

        public void setTRANSFER_AMOUNT_IN_PROGRESS(double TRANSFER_AMOUNT_IN_PROGRESS) {
            this.TRANSFER_AMOUNT_IN_PROGRESS = TRANSFER_AMOUNT_IN_PROGRESS;
        }

        public String getTENDER_STATUS() {
            return TENDER_STATUS;
        }

        public void setTENDER_STATUS(String TENDER_STATUS) {
            this.TENDER_STATUS = TENDER_STATUS;
        }

        public String getAUTO_STATUS() {
            return AUTO_STATUS;
        }

        public void setAUTO_STATUS(String AUTO_STATUS) {
            this.AUTO_STATUS = AUTO_STATUS;
        }

        public String getREQUEST_NO() {
            return REQUEST_NO;
        }

        public void setREQUEST_NO(String REQUEST_NO) {
            this.REQUEST_NO = REQUEST_NO;
        }

        public long getINS_DATE() {
            return INS_DATE;
        }

        public void setINS_DATE(long INS_DATE) {
            this.INS_DATE = INS_DATE;
        }

        public String getTAG_END_MODE() {
            return TAG_END_MODE;
        }

        public void setTAG_END_MODE(String TAG_END_MODE) {
            this.TAG_END_MODE = TAG_END_MODE;
        }

        public String getMOBILE() {
            return MOBILE;
        }

        public void setMOBILE(String MOBILE) {
            this.MOBILE = MOBILE;
        }

        public String getEMAIL() {
            return EMAIL;
        }

        public void setEMAIL(String EMAIL) {
            this.EMAIL = EMAIL;
        }

        public String getUSER_TYPE() {
            return USER_TYPE;
        }

        public void setUSER_TYPE(String USER_TYPE) {
            this.USER_TYPE = USER_TYPE;
        }

        public Object getRED_PACKET_USE_LOG_ID() {
            return RED_PACKET_USE_LOG_ID;
        }

        public void setRED_PACKET_USE_LOG_ID(Object RED_PACKET_USE_LOG_ID) {
            this.RED_PACKET_USE_LOG_ID = RED_PACKET_USE_LOG_ID;
        }

        public Object getAMOUNT() {
            return AMOUNT;
        }

        public void setAMOUNT(Object AMOUNT) {
            this.AMOUNT = AMOUNT;
        }

        public Object getOPERATE_TYPE() {
            return OPERATE_TYPE;
        }

        public void setOPERATE_TYPE(Object OPERATE_TYPE) {
            this.OPERATE_TYPE = OPERATE_TYPE;
        }

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public String getTenderAmount() {
            return tenderAmount;
        }

        public void setTenderAmount(String tenderAmount) {
            this.tenderAmount = tenderAmount;
        }

        public String getTenderDate() {
            return tenderDate;
        }

        public void setTenderDate(String tenderDate) {
            this.tenderDate = tenderDate;
        }

        public String getTerminal() {
            return terminal;
        }

        public void setTerminal(String terminal) {
            this.terminal = terminal;
        }
    }
}
