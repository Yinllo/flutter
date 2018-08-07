package com.desjf.dsjr.model.bankModel;

import java.util.List;

/**
 * @Author YinL
 * @Date 2018/4/4 0004
 * @Describe  我的投资：冻结
 */

public class BankFreezeModel {

    /**
     * userRecoverMapToList : [{"TENDER_AMOUNT":15500,"name":"冻结本金"}]
     * userTenderMap : {"TENDER_AMOUNT":15500}
     * userTenderList : [{"OID_PLATFORM_PRODUCTS_ID":"09edf7bdb0e04772a254b751ec24f337","PRODUCTS_TITLE":"4555555","INS_DATE":1520931219000,"FINANCE_INTEREST_RATE":12,"FINANCE_PERIOD":3,"TENDER_AMOUNT":"1 000.00"}]
     * counts : 26
     */

    private UserTenderMapBean userTenderMap;
    private int counts;
    private List<UserRecoverMapToListBean> userRecoverMapToList;
    private List<UserTenderListBean> userTenderList;

    public UserTenderMapBean getUserTenderMap() {
        return userTenderMap;
    }

    public void setUserTenderMap(UserTenderMapBean userTenderMap) {
        this.userTenderMap = userTenderMap;
    }

    public int getCounts() {
        return counts;
    }

    public void setCounts(int counts) {
        this.counts = counts;
    }

    public List<UserRecoverMapToListBean> getUserRecoverMapToList() {
        return userRecoverMapToList;
    }

    public void setUserRecoverMapToList(List<UserRecoverMapToListBean> userRecoverMapToList) {
        this.userRecoverMapToList = userRecoverMapToList;
    }

    public List<UserTenderListBean> getUserTenderList() {
        return userTenderList;
    }

    public void setUserTenderList(List<UserTenderListBean> userTenderList) {
        this.userTenderList = userTenderList;
    }

    public static class UserTenderMapBean {
        /**
         * TENDER_AMOUNT : 15500.0
         */

        private String TENDER_AMOUNT;

        public String getTENDER_AMOUNT() {
            return TENDER_AMOUNT == null ? "0.00" : TENDER_AMOUNT;
        }

        public void setTENDER_AMOUNT(String TENDER_AMOUNT) {
            this.TENDER_AMOUNT = TENDER_AMOUNT;
        }
    }

    public static class UserRecoverMapToListBean {
        /**
         * TENDER_AMOUNT : 15500.0
         * name : 冻结本金
         */

        private String TENDER_AMOUNT;
        private String name;

        public String getTENDER_AMOUNT() {
            return TENDER_AMOUNT == null ? "0.00" : TENDER_AMOUNT;
        }

        public void setTENDER_AMOUNT(String TENDER_AMOUNT) {
            this.TENDER_AMOUNT = TENDER_AMOUNT;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    public static class UserTenderListBean {
        /**
         * OID_PLATFORM_PRODUCTS_ID : 09edf7bdb0e04772a254b751ec24f337
         * PRODUCTS_TITLE : 4555555
         * INS_DATE : 1520931219000
         * FINANCE_INTEREST_RATE : 12.0
         * FINANCE_PERIOD : 3
         * TENDER_AMOUNT : 1 000.00
         */

        private String OID_PLATFORM_PRODUCTS_ID;
        private String PRODUCTS_TITLE;
        private long INS_DATE;
        private double FINANCE_INTEREST_RATE;
        private int FINANCE_PERIOD;
        private String TENDER_AMOUNT;

        public String getOID_PLATFORM_PRODUCTS_ID() {
            return OID_PLATFORM_PRODUCTS_ID;
        }

        public void setOID_PLATFORM_PRODUCTS_ID(String OID_PLATFORM_PRODUCTS_ID) {
            this.OID_PLATFORM_PRODUCTS_ID = OID_PLATFORM_PRODUCTS_ID;
        }

        public String getPRODUCTS_TITLE() {
            return PRODUCTS_TITLE;
        }

        public void setPRODUCTS_TITLE(String PRODUCTS_TITLE) {
            this.PRODUCTS_TITLE = PRODUCTS_TITLE;
        }

        public long getINS_DATE() {
            return INS_DATE;
        }

        public void setINS_DATE(long INS_DATE) {
            this.INS_DATE = INS_DATE;
        }

        public double getFINANCE_INTEREST_RATE() {
            return FINANCE_INTEREST_RATE;
        }

        public void setFINANCE_INTEREST_RATE(double FINANCE_INTEREST_RATE) {
            this.FINANCE_INTEREST_RATE = FINANCE_INTEREST_RATE;
        }

        public int getFINANCE_PERIOD() {
            return FINANCE_PERIOD;
        }

        public void setFINANCE_PERIOD(int FINANCE_PERIOD) {
            this.FINANCE_PERIOD = FINANCE_PERIOD;
        }

        public String getTENDER_AMOUNT() {
            return TENDER_AMOUNT;
        }

        public void setTENDER_AMOUNT(String TENDER_AMOUNT) {
            this.TENDER_AMOUNT = TENDER_AMOUNT;
        }
    }
}
