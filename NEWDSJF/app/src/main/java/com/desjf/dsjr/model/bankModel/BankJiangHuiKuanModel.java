package com.desjf.dsjr.model.bankModel;

import java.util.List;

/**
 * @Author YinL
 * @Date 2018/4/4 0004
 * @Describe   我的投资：将回款
 */

public class BankJiangHuiKuanModel {


    /**
     * userRecoverMapToList : [{"RECOVER_AMOUNT_CAPITAL":27300,"name":"将回本金"},{"name":"将 回利息","RECOVER_AMOUNT_INTEREST":809},{"name":"将回 款","RECOVER_AMOUNT_TOTAL":28109}]
     * userRecoverList : [{"OID_PLATFORM_PRODUCTS_ID":"417133846b5544ed9eca0fb0650ec887","OID_TENDER_ID":"e7cfe22f99 bb430bb87d5e12bfdc431e","PRODUCTS_TITLE":"还款测试0001","RECOVER_DATE":"2018-06- 15","RECOVER_CNT":3,"FINANCE_PERIOD":3,"RECOVER_AMOUNT_CAPITAL":1000,"RECOVER_AMOUNT_INT EREST":10,"RECOVER_AMOUNT_TOTAL":1010}]
     * counts : 63
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
         * RECOVER_AMOUNT_CAPITAL : 27300.0
         * name : 将回本金
         * RECOVER_AMOUNT_INTEREST : 809.0
         * RECOVER_AMOUNT_TOTAL : 28109.0
         */

        private String RECOVER_AMOUNT_CAPITAL;
        private String name;
        private String RECOVER_AMOUNT_INTEREST;
        private String RECOVER_AMOUNT_TOTAL;

        public String getRECOVER_AMOUNT_CAPITAL() {
            return RECOVER_AMOUNT_CAPITAL == null ? "0.00" : RECOVER_AMOUNT_CAPITAL;
        }

        public void setRECOVER_AMOUNT_CAPITAL(String RECOVER_AMOUNT_CAPITAL) {
            this.RECOVER_AMOUNT_CAPITAL = RECOVER_AMOUNT_CAPITAL;
        }

        public String getRECOVER_AMOUNT_INTEREST() {
            return RECOVER_AMOUNT_INTEREST == null ? "0.00" : RECOVER_AMOUNT_INTEREST;
        }

        public void setRECOVER_AMOUNT_INTEREST(String RECOVER_AMOUNT_INTEREST) {
            this.RECOVER_AMOUNT_INTEREST = RECOVER_AMOUNT_INTEREST;
        }

        public String getRECOVER_AMOUNT_TOTAL() {
            return RECOVER_AMOUNT_TOTAL == null ? "0.00" : RECOVER_AMOUNT_TOTAL;
        }

        public void setRECOVER_AMOUNT_TOTAL(String RECOVER_AMOUNT_TOTAL) {
            this.RECOVER_AMOUNT_TOTAL = RECOVER_AMOUNT_TOTAL;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

    }

    public static class UserRecoverListBean {
        /**
         * OID_PLATFORM_PRODUCTS_ID : 417133846b5544ed9eca0fb0650ec887
         * OID_TENDER_ID : e7cfe22f99 bb430bb87d5e12bfdc431e
         * PRODUCTS_TITLE : 还款测试0001
         * RECOVER_DATE : 2018-06- 15
         * RECOVER_CNT : 3
         * FINANCE_PERIOD : 3
         * RECOVER_AMOUNT_CAPITAL : 1000.0
         * RECOVER_AMOUNT_INT EREST : 10.0
         * RECOVER_AMOUNT_TOTAL : 1010.0
         */

        private String OID_PLATFORM_PRODUCTS_ID;
        private String OID_TENDER_ID;
        private String PRODUCTS_TITLE;
        private String RECOVER_DATE;
        private int RECOVER_CNT;
        private int FINANCE_PERIOD;
        private String RECOVER_AMOUNT_CAPITAL;
//        @SerializedName("RECOVER_AMOUNT_INTEREST")
        private String RECOVER_AMOUNT_INTEREST; // FIXME check this code
        private String RECOVER_AMOUNT_TOTAL;

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

        public String getPRODUCTS_TITLE() {
            return PRODUCTS_TITLE;
        }

        public void setPRODUCTS_TITLE(String PRODUCTS_TITLE) {
            this.PRODUCTS_TITLE = PRODUCTS_TITLE;
        }

        public String getRECOVER_DATE() {
            return RECOVER_DATE;
        }

        public void setRECOVER_DATE(String RECOVER_DATE) {
            this.RECOVER_DATE = RECOVER_DATE;
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

        public String getRECOVER_AMOUNT_CAPITAL() {
            return RECOVER_AMOUNT_CAPITAL == null ? "" : RECOVER_AMOUNT_CAPITAL;
        }

        public void setRECOVER_AMOUNT_CAPITAL(String RECOVER_AMOUNT_CAPITAL) {
            this.RECOVER_AMOUNT_CAPITAL = RECOVER_AMOUNT_CAPITAL;
        }

        public String getRECOVER_AMOUNT_INTEREST() {
            return RECOVER_AMOUNT_INTEREST == null ? "" : RECOVER_AMOUNT_INTEREST;
        }

        public void setRECOVER_AMOUNT_INTEREST(String RECOVER_AMOUNT_INTEREST) {
            this.RECOVER_AMOUNT_INTEREST = RECOVER_AMOUNT_INTEREST;
        }

        public String getRECOVER_AMOUNT_TOTAL() {
            return RECOVER_AMOUNT_TOTAL == null ? "" : RECOVER_AMOUNT_TOTAL;
        }

        public void setRECOVER_AMOUNT_TOTAL(String RECOVER_AMOUNT_TOTAL) {
            this.RECOVER_AMOUNT_TOTAL = RECOVER_AMOUNT_TOTAL;
        }
    }
}
