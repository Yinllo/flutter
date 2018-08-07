package com.desjf.dsjr.model.bankModel;

import java.util.List;

/**
 * @Author YinL
 * @Date 2018/3/26 0026
 * @Describe  投资
 */

public class BankInvestmentModel {


    /**
     * userRecoverMapToList : [{"TENDER_AMOUNT":200000,"name":"投资本金"}]
     * userTenderMap : {"TENDER_AMOUNT":200000}
     * userTenderList : [{"OID_PLATFORM_PRODUCTS_ID":"cd278641c6ad410ebadf64c2a368c9c7","OID_TENDER_ID":"d3380c8d1102414996fc962d5bd5fa7e","TENDER_STATUS":"1","PRODUCTS_TITLE":"20万对私建标","INS_DATE":1522140800000,"FINANCE_INTEREST_RATE":15,"FINANCE_PERIOD":1,"TENDER_AMOUNT":200000}]
     * counts : 1
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
         * TENDER_AMOUNT : 200000.0
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
         * TENDER_AMOUNT : 200000.0
         * name : 投资本金
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
            return name == null ? "" : name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    public static class UserTenderListBean {
        /**
         * OID_PLATFORM_PRODUCTS_ID : cd278641c6ad410ebadf64c2a368c9c7
         * OID_TENDER_ID : d3380c8d1102414996fc962d5bd5fa7e
         * TENDER_STATUS : 1
         * PRODUCTS_TITLE : 20万对私建标
         * INS_DATE : 1522140800000
         * FINANCE_INTEREST_RATE : 15.0
         * FINANCE_PERIOD : 1
         * TENDER_AMOUNT : 200000.0
         */

        private String OID_PLATFORM_PRODUCTS_ID;
        private String OID_TENDER_ID;
        private String TENDER_STATUS;
        private String PRODUCTS_TITLE;
        private long INS_DATE;
        private String FINANCE_INTEREST_RATE;
        private int FINANCE_PERIOD;
        private String TENDER_AMOUNT;

        public String getOID_PLATFORM_PRODUCTS_ID() {
            return OID_PLATFORM_PRODUCTS_ID;
        }

        public void setOID_PLATFORM_PRODUCTS_ID(String OID_PLATFORM_PRODUCTS_ID) {
            this.OID_PLATFORM_PRODUCTS_ID = OID_PLATFORM_PRODUCTS_ID;
        }

        public String getOID_TENDER_ID() {
            return OID_TENDER_ID;
        }

        public void setOID_TENDER_ID(String OID_TENDER_ID) {
            this.OID_TENDER_ID = OID_TENDER_ID;
        }

        public String getTENDER_STATUS() {
            return TENDER_STATUS;
        }

        public void setTENDER_STATUS(String TENDER_STATUS) {
            this.TENDER_STATUS = TENDER_STATUS;
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

        public int getFINANCE_PERIOD() {
            return FINANCE_PERIOD;
        }

        public void setFINANCE_PERIOD(int FINANCE_PERIOD) {
            this.FINANCE_PERIOD = FINANCE_PERIOD;
        }

        public String getFINANCE_INTEREST_RATE() {
            return FINANCE_INTEREST_RATE == null ? "" : FINANCE_INTEREST_RATE;
        }

        public void setFINANCE_INTEREST_RATE(String FINANCE_INTEREST_RATE) {
            this.FINANCE_INTEREST_RATE = FINANCE_INTEREST_RATE;
        }

        public String getTENDER_AMOUNT() {
            return TENDER_AMOUNT == null ? "" : TENDER_AMOUNT;
        }

        public void setTENDER_AMOUNT(String TENDER_AMOUNT) {
            this.TENDER_AMOUNT = TENDER_AMOUNT;
        }
    }
}
