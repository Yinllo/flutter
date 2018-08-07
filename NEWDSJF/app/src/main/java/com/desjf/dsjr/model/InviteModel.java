package com.desjf.dsjr.model;

import java.util.List;

/**
 * @Author YinL
 * @Date 2018/5/14 0014
 * @Describe
 */

public class InviteModel {


    /**
     * InviteAwardList : [{"USER_NAME":"能玉","MOBILE":"13000000031","INS_DATE":1526290542000,"TENDER_AMOUNT":1000,"INVITE_AWARD_AMOUNT":50},{"USER_NAME":"能玉","MOBILE":"13000000031","INS_DATE":1526290204000,"TENDER_AMOUNT":1000,"INVITE_AWARD_AMOUNT":20},{"USER_NAME":"汪烨烁","MOBILE":"13000000030","INS_DATE":1526290082000,"TENDER_AMOUNT":1000,"INVITE_AWARD_AMOUNT":20},{"USER_NAME":"雍钲","MOBILE":"13000000032","INS_DATE":1526289254000,"TENDER_AMOUNT":null,"INVITE_AWARD_AMOUNT":20},{"USER_NAME":"能玉","MOBILE":"13000000031","INS_DATE":1526288742000,"TENDER_AMOUNT":null,"INVITE_AWARD_AMOUNT":20},{"USER_NAME":"汪烨烁","MOBILE":"13000000030","INS_DATE":1526288488000,"TENDER_AMOUNT":null,"INVITE_AWARD_AMOUNT":20},{"USER_NAME":"赵乐咏","MOBILE":"13000000029","INS_DATE":1526287987000,"TENDER_AMOUNT":null,"INVITE_AWARD_AMOUNT":20},{"USER_NAME":"金安福","MOBILE":"13000000028","INS_DATE":1526287462000,"TENDER_AMOUNT":null,"INVITE_AWARD_AMOUNT":20},{"USER_NAME":"江弘懿","MOBILE":"13000000027","INS_DATE":1526286466000,"TENDER_AMOUNT":null,"INVITE_AWARD_AMOUNT":20}]
     * InviteAwardCountMap : {"COUNT":9}
     */

    private InviteAwardCountMapBean InviteAwardCountMap;
    private List<InviteAwardListBean> InviteAwardList;

    public InviteAwardCountMapBean getInviteAwardCountMap() {
        return InviteAwardCountMap;
    }

    public void setInviteAwardCountMap(InviteAwardCountMapBean InviteAwardCountMap) {
        this.InviteAwardCountMap = InviteAwardCountMap;
    }

    public List<InviteAwardListBean> getInviteAwardList() {
        return InviteAwardList;
    }

    public void setInviteAwardList(List<InviteAwardListBean> InviteAwardList) {
        this.InviteAwardList = InviteAwardList;
    }

    public static class InviteAwardCountMapBean {
        /**
         * COUNT : 9
         */

        private int COUNT;

        public int getCOUNT() {
            return COUNT;
        }

        public void setCOUNT(int COUNT) {
            this.COUNT = COUNT;
        }
    }

    public static class InviteAwardListBean {
        /**
         * USER_NAME : 能玉
         * MOBILE : 13000000031
         * INS_DATE : 1526290542000
         * TENDER_AMOUNT : 1000.0
         * INVITE_AWARD_AMOUNT : 50.0
         */

        private String USER_NAME;
        private String MOBILE;
        private long INS_DATE;
        private String TENDER_AMOUNT;
        private String INVITE_AWARD_AMOUNT;
        private String FINANCE_PERIOD;

        public String getFINANCE_PERIOD() {
            return FINANCE_PERIOD == null ? "" : FINANCE_PERIOD;
        }

        public void setFINANCE_PERIOD(String FINANCE_PERIOD) {
            this.FINANCE_PERIOD = FINANCE_PERIOD;
        }

        public String getUSER_NAME() {
            return USER_NAME;
        }

        public void setUSER_NAME(String USER_NAME) {
            this.USER_NAME = USER_NAME;
        }

        public String getMOBILE() {
            return MOBILE;
        }

        public void setMOBILE(String MOBILE) {
            this.MOBILE = MOBILE;
        }

        public long getINS_DATE() {
            return INS_DATE;
        }

        public void setINS_DATE(long INS_DATE) {
            this.INS_DATE = INS_DATE;
        }

        public String getTENDER_AMOUNT() {
            return TENDER_AMOUNT;
        }

        public void setTENDER_AMOUNT(String TENDER_AMOUNT) {
            this.TENDER_AMOUNT = TENDER_AMOUNT;
        }

        public String getINVITE_AWARD_AMOUNT() {
            return INVITE_AWARD_AMOUNT;
        }

        public void setINVITE_AWARD_AMOUNT(String INVITE_AWARD_AMOUNT) {
            this.INVITE_AWARD_AMOUNT = INVITE_AWARD_AMOUNT;
        }
    }
}
