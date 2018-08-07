package com.desjf.dsjr.model.bankModel;

import java.util.List;

/**
 * @Author YinL
 * @Date 2018/3/28 0028
 * @Describe  用户投资中标的  实体类
 */

public class BankUserInvestmentedModel {


    /**
     * userRecoverMapToList : [{"TENDER_AMOUNT":647800,"name":"投资本金"}]
     * userTenderMap : {"TENDER_AMOUNT":647800}
     * userTenderList : null
     * counts : 17
     */

    private UserTenderMapBean userTenderMap;
    private Object userTenderList;
    private int counts;
    private List<UserRecoverMapToListBean> userRecoverMapToList;

    public UserTenderMapBean getUserTenderMap() {
        return userTenderMap;
    }

    public void setUserTenderMap(UserTenderMapBean userTenderMap) {
        this.userTenderMap = userTenderMap;
    }

    public Object getUserTenderList() {
        return userTenderList;
    }

    public void setUserTenderList(Object userTenderList) {
        this.userTenderList = userTenderList;
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

    public static class UserTenderMapBean {
        /**
         * TENDER_AMOUNT : 647800.0
         */

        private double TENDER_AMOUNT;

        public double getTENDER_AMOUNT() {
            return TENDER_AMOUNT;
        }

        public void setTENDER_AMOUNT(double TENDER_AMOUNT) {
            this.TENDER_AMOUNT = TENDER_AMOUNT;
        }
    }

    public static class UserRecoverMapToListBean {
        /**
         * TENDER_AMOUNT : 647800.0
         * name : 投资本金
         */

        private double TENDER_AMOUNT;
        private String name;

        public double getTENDER_AMOUNT() {
            return TENDER_AMOUNT;
        }

        public void setTENDER_AMOUNT(double TENDER_AMOUNT) {
            this.TENDER_AMOUNT = TENDER_AMOUNT;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
