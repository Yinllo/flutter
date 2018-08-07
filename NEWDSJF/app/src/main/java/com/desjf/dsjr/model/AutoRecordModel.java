package com.desjf.dsjr.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Administrator on 2017/7/27 0027.
 */

public class AutoRecordModel {

    @SerializedName("AUTO_SET_LIST")
    private List<AUTOSETLISTBean> AUTOSETLIST;

    public List<AUTOSETLISTBean> getAUTOSETLIST() {
        return AUTOSETLIST;
    }

    public void setAUTOSETLIST(List<AUTOSETLISTBean> AUTOSETLIST) {
        this.AUTOSETLIST = AUTOSETLIST;
    }

    public static class AUTOSETLISTBean {
        /**
         * BORROW_TITLE : 20170810-07
         * BORROW_RATE : 10.00
         * BORROW_PERIOD : 5
         * INTEREST_RATE_TYPE : 个月
         * VALID_ACCOUNT_TENDER : 1
         * VALID_ACCOUNT_UNIT : 万
         * VERIFYSTATUS : 复审通过
         */

        @SerializedName("BORROW_TITLE")
        private String BORROWTITLE;
        @SerializedName("BORROW_RATE")
        private String BORROWRATE;
        @SerializedName("BORROW_PERIOD")
        private String BORROWPERIOD;
        @SerializedName("INTEREST_RATE_TYPE")
        private String INTERESTRATETYPE;
        @SerializedName("VALID_ACCOUNT_TENDER")
        private String VALIDACCOUNTTENDER;
        @SerializedName("VALID_ACCOUNT_UNIT")
        private String VALIDACCOUNTUNIT;
        @SerializedName("VERIFYSTATUS")
        private String VERIFYSTATUS;

        public String getBORROWTITLE() {
            return BORROWTITLE;
        }

        public void setBORROWTITLE(String BORROWTITLE) {
            this.BORROWTITLE = BORROWTITLE;
        }

        public String getBORROWRATE() {
            return BORROWRATE;
        }

        public void setBORROWRATE(String BORROWRATE) {
            this.BORROWRATE = BORROWRATE;
        }

        public String getBORROWPERIOD() {
            return BORROWPERIOD;
        }

        public void setBORROWPERIOD(String BORROWPERIOD) {
            this.BORROWPERIOD = BORROWPERIOD;
        }

        public String getINTERESTRATETYPE() {
            return INTERESTRATETYPE;
        }

        public void setINTERESTRATETYPE(String INTERESTRATETYPE) {
            this.INTERESTRATETYPE = INTERESTRATETYPE;
        }

        public String getVALIDACCOUNTTENDER() {
            return VALIDACCOUNTTENDER;
        }

        public void setVALIDACCOUNTTENDER(String VALIDACCOUNTTENDER) {
            this.VALIDACCOUNTTENDER = VALIDACCOUNTTENDER;
        }

        public String getVALIDACCOUNTUNIT() {
            return VALIDACCOUNTUNIT;
        }

        public void setVALIDACCOUNTUNIT(String VALIDACCOUNTUNIT) {
            this.VALIDACCOUNTUNIT = VALIDACCOUNTUNIT;
        }

        public String getVERIFYSTATUS() {
            return VERIFYSTATUS;
        }

        public void setVERIFYSTATUS(String VERIFYSTATUS) {
            this.VERIFYSTATUS = VERIFYSTATUS;
        }
    }
}
