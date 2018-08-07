package com.desjf.dsjr.model.bankModel;

import java.util.List;

/**
 * @Author YinL
 * @Date 2018/3/28 0028
 * @Describe  用户已回款信息
 */

public class BankHuikuanModel {


    /**
     * userRecoverMapToList : [{"name":"已回本金","RECOVER_AMOUNT_CAPITAL_YES":200000},{"name":"已回利息","RECOVER_AMOUNT_INTEREST_YES":2500},{"RECOVER_AMOUNT_TOTAL_YES":202500,"name":"已回款"}]
     * userRecoverList : [{"OID_TENDER_ID":"d3380c8d1102414996fc962d5bd5fa7e","PRODUCTS_TITLE":"20万对私建标","RECOVER_DATE_YES":"2018-03-27","RECOVER_CNT":1,"FINANCE_PERIOD":1,"RECOVER_AMOUNT_CAPITAL_YES":200000,"RECOVER_AMOUNT_INTEREST_YES":2500,"RECOVER_AMOUNT_TOTAL_YES":202500,"OID_PLATFORM_PRODUCTS_ID":"6e0a8666d29f7fe48042c9b8e9a3bdca90623871861487fe01ffc762fd0e6aaa9327d136d10e0581"}]
     * counts : 1
     */

    private int counts;
    private List<UserRecoverMapToListBean> userRecoverMapToList;
    private List<UserRecoverListBean> userRecoverList;

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

    public List<UserRecoverListBean> getUserRecoverList() {
        return userRecoverList;
    }

    public void setUserRecoverList(List<UserRecoverListBean> userRecoverList) {
        this.userRecoverList = userRecoverList;
    }

    public static class UserRecoverMapToListBean {
        /**
         * name : 已回本金
         * RECOVER_AMOUNT_CAPITAL_YES : 200000.0
         * RECOVER_AMOUNT_INTEREST_YES : 2500.0
         * RECOVER_AMOUNT_TOTAL_YES : 202500.0
         */

        private String name;
        private String RECOVER_AMOUNT_CAPITAL_YES;
        private String RECOVER_AMOUNT_INTEREST_YES;
        private String RECOVER_AMOUNT_TOTAL_YES;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getRECOVER_AMOUNT_CAPITAL_YES() {
            return RECOVER_AMOUNT_CAPITAL_YES == null ? "0.00" : RECOVER_AMOUNT_CAPITAL_YES;
        }

        public void setRECOVER_AMOUNT_CAPITAL_YES(String RECOVER_AMOUNT_CAPITAL_YES) {
            this.RECOVER_AMOUNT_CAPITAL_YES = RECOVER_AMOUNT_CAPITAL_YES;
        }

        public String getRECOVER_AMOUNT_INTEREST_YES() {
            return RECOVER_AMOUNT_INTEREST_YES == null ? "0.00" : RECOVER_AMOUNT_INTEREST_YES;
        }

        public void setRECOVER_AMOUNT_INTEREST_YES(String RECOVER_AMOUNT_INTEREST_YES) {
            this.RECOVER_AMOUNT_INTEREST_YES = RECOVER_AMOUNT_INTEREST_YES;
        }

        public String getRECOVER_AMOUNT_TOTAL_YES() {
            return RECOVER_AMOUNT_TOTAL_YES == null ? "0.00" : RECOVER_AMOUNT_TOTAL_YES;
        }

        public void setRECOVER_AMOUNT_TOTAL_YES(String RECOVER_AMOUNT_TOTAL_YES) {
            this.RECOVER_AMOUNT_TOTAL_YES = RECOVER_AMOUNT_TOTAL_YES;
        }
    }

    public static class UserRecoverListBean {
        /**
         * OID_TENDER_ID : d3380c8d1102414996fc962d5bd5fa7e
         * PRODUCTS_TITLE : 20万对私建标
         * RECOVER_DATE_YES : 2018-03-27
         * RECOVER_CNT : 1
         * FINANCE_PERIOD : 1
         * RECOVER_AMOUNT_CAPITAL_YES : 200000.0
         * RECOVER_AMOUNT_INTEREST_YES : 2500.0
         * RECOVER_AMOUNT_TOTAL_YES : 202500.0
         * OID_PLATFORM_PRODUCTS_ID : 6e0a8666d29f7fe48042c9b8e9a3bdca90623871861487fe01ffc762fd0e6aaa9327d136d10e0581
         */

        private String OID_TENDER_ID;
        private String PRODUCTS_TITLE;
        private String RECOVER_DATE_YES;
        private int RECOVER_CNT;
        private int FINANCE_PERIOD;
        private String RECOVER_AMOUNT_CAPITAL_YES;
        private String RECOVER_AMOUNT_INTEREST_YES;
        private String RECOVER_AMOUNT_TOTAL_YES;
        private String OID_PLATFORM_PRODUCTS_ID;

        public String getOID_TENDER_ID() {
            return OID_TENDER_ID;
        }

        public void setOID_TENDER_ID(String OID_TENDER_ID) {
            this.OID_TENDER_ID = OID_TENDER_ID;
        }

        public String getPRODUCTS_TITLE() {
            return PRODUCTS_TITLE;
        }

        public void setPRODUCTS_TITLE(String PRODUCTS_TITLE) {
            this.PRODUCTS_TITLE = PRODUCTS_TITLE;
        }

        public String getRECOVER_DATE_YES() {
            return RECOVER_DATE_YES;
        }

        public void setRECOVER_DATE_YES(String RECOVER_DATE_YES) {
            this.RECOVER_DATE_YES = RECOVER_DATE_YES;
        }

        public int getRECOVER_CNT() {
            return RECOVER_CNT;
        }

        public void setRECOVER_CNT(int RECOVER_CNT) {
            this.RECOVER_CNT = RECOVER_CNT;
        }

        public int getFINANCE_PERIOD() {
            return FINANCE_PERIOD;
        }

        public void setFINANCE_PERIOD(int FINANCE_PERIOD) {
            this.FINANCE_PERIOD = FINANCE_PERIOD;
        }

        public String getOID_PLATFORM_PRODUCTS_ID() {
            return OID_PLATFORM_PRODUCTS_ID;
        }

        public void setOID_PLATFORM_PRODUCTS_ID(String OID_PLATFORM_PRODUCTS_ID) {
            this.OID_PLATFORM_PRODUCTS_ID = OID_PLATFORM_PRODUCTS_ID;
        }

        public String getRECOVER_AMOUNT_CAPITAL_YES() {
            return RECOVER_AMOUNT_CAPITAL_YES == null ? "" : RECOVER_AMOUNT_CAPITAL_YES;
        }

        public void setRECOVER_AMOUNT_CAPITAL_YES(String RECOVER_AMOUNT_CAPITAL_YES) {
            this.RECOVER_AMOUNT_CAPITAL_YES = RECOVER_AMOUNT_CAPITAL_YES;
        }

        public String getRECOVER_AMOUNT_INTEREST_YES() {
            return RECOVER_AMOUNT_INTEREST_YES == null ? "" : RECOVER_AMOUNT_INTEREST_YES;
        }

        public void setRECOVER_AMOUNT_INTEREST_YES(String RECOVER_AMOUNT_INTEREST_YES) {
            this.RECOVER_AMOUNT_INTEREST_YES = RECOVER_AMOUNT_INTEREST_YES;
        }

        public String getRECOVER_AMOUNT_TOTAL_YES() {
            return RECOVER_AMOUNT_TOTAL_YES == null ? "" : RECOVER_AMOUNT_TOTAL_YES;
        }

        public void setRECOVER_AMOUNT_TOTAL_YES(String RECOVER_AMOUNT_TOTAL_YES) {
            this.RECOVER_AMOUNT_TOTAL_YES = RECOVER_AMOUNT_TOTAL_YES;
        }
    }
}
