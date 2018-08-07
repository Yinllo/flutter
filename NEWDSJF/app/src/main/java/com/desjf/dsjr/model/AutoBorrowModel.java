package com.desjf.dsjr.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2017/7/27 0027.
 */

public class AutoBorrowModel implements Serializable {

    /**
     * CAPITAL : 0
     * CAPITAL_UNIT : 元
     * INTTOTALCNT : 0
     * INTEREST : 0
     * INTEREST_UNIT : 元
     * AUTO_SET_LIST : [{"USING_FLG":"1","PERIOD_BEGIN":"1","PERIOD_END":"12","PERIOD_TYPE":"个月","RATE_BEGIN":"1","RATE_END":"24","LOAN_AMOUNT":"100","LOAN_AMOUNT_UNIT":"元","LOAN_AMOUNT_SIMPLE":"100.00","SEQ":"13","AWARD_FLG":"0","CHKBORROWMORTGAGE":"1","CHKBORROWWANDER":"2"}]
     */

    @SerializedName("CAPITAL")
    private String CAPITAL;
    @SerializedName("CAPITAL_UNIT")
    private String CAPITALUNIT;
    @SerializedName("INTTOTALCNT")
    private String INTTOTALCNT;
    @SerializedName("INTEREST")
    private String INTEREST;
    @SerializedName("INTEREST_UNIT")
    private String INTERESTUNIT;
    @SerializedName("AUTO_TENDER_MAX_RATE")
    private String AUTOTENDERMAXRATE;
    @SerializedName("AUTO_SET_LIST")
    private List<AUTOSETLISTBean> AUTOSETLIST;

    public String getAUTOTENDERMAXRATE() {
        return AUTOTENDERMAXRATE;
    }

    public void setAUTOTENDERMAXRATE(String AUTOTENDERMAXRATE) {
        this.AUTOTENDERMAXRATE = AUTOTENDERMAXRATE;
    }

    public String getCAPITAL() {
        return CAPITAL;
    }

    public void setCAPITAL(String CAPITAL) {
        this.CAPITAL = CAPITAL;
    }

    public String getCAPITALUNIT() {
        return CAPITALUNIT;
    }

    public void setCAPITALUNIT(String CAPITALUNIT) {
        this.CAPITALUNIT = CAPITALUNIT;
    }

    public String getINTTOTALCNT() {
        return INTTOTALCNT;
    }

    public void setINTTOTALCNT(String INTTOTALCNT) {
        this.INTTOTALCNT = INTTOTALCNT;
    }

    public String getINTEREST() {
        return INTEREST;
    }

    public void setINTEREST(String INTEREST) {
        this.INTEREST = INTEREST;
    }

    public String getINTERESTUNIT() {
        return INTERESTUNIT;
    }

    public void setINTERESTUNIT(String INTERESTUNIT) {
        this.INTERESTUNIT = INTERESTUNIT;
    }

    public List<AUTOSETLISTBean> getAUTOSETLIST() {
        return AUTOSETLIST;
    }

    public void setAUTOSETLIST(List<AUTOSETLISTBean> AUTOSETLIST) {
        this.AUTOSETLIST = AUTOSETLIST;
    }

    public static class AUTOSETLISTBean implements Serializable {
        /**
         * USING_FLG : 1
         * PERIOD_BEGIN : 1
         * PERIOD_END : 12
         * PERIOD_TYPE : 个月
         * RATE_BEGIN : 1
         * RATE_END : 24
         * LOAN_AMOUNT : 100
         * LOAN_AMOUNT_UNIT : 元
         * LOAN_AMOUNT_SIMPLE : 100.00
         * SEQ : 13
         * AWARD_FLG : 0
         * CHKBORROWMORTGAGE : 1
         * CHKBORROWWANDER : 2
         */

        @SerializedName("USING_FLG")
        private String USINGFLG;
        @SerializedName("PERIOD_BEGIN")
        private String PERIODBEGIN;
        @SerializedName("PERIOD_END")
        private String PERIODEND;
        @SerializedName("PERIOD_TYPE")
        private String PERIODTYPE;
        @SerializedName("RATE_BEGIN")
        private String RATEBEGIN;
        @SerializedName("RATE_END")
        private String RATEEND;
        @SerializedName("LOAN_AMOUNT")
        private String LOANAMOUNT;
        @SerializedName("LOAN_AMOUNT_UNIT")
        private String LOANAMOUNTUNIT;
        @SerializedName("LOAN_AMOUNT_SIMPLE")
        private String LOANAMOUNTSIMPLE;
        @SerializedName("SEQ")
        private String SEQ;
        @SerializedName("AWARD_FLG")
        private String AWARDFLG;
        @SerializedName("CHKBORROWMORTGAGE")
        private String CHKBORROWMORTGAGE;
        @SerializedName("CHKBORROWWANDER")
        private String CHKBORROWWANDER;
        private String FLG;//操作flg


        public String getFLG() {
            return FLG;
        }

        public void setFLG(String FLG) {
            this.FLG = FLG;
        }
        public String getUSINGFLG() {
            return USINGFLG;
        }

        public void setUSINGFLG(String USINGFLG) {
            this.USINGFLG = USINGFLG;
        }

        public String getPERIODBEGIN() {
            return PERIODBEGIN;
        }

        public void setPERIODBEGIN(String PERIODBEGIN) {
            this.PERIODBEGIN = PERIODBEGIN;
        }

        public String getPERIODEND() {
            return PERIODEND;
        }

        public void setPERIODEND(String PERIODEND) {
            this.PERIODEND = PERIODEND;
        }

        public String getPERIODTYPE() {
            return PERIODTYPE;
        }

        public void setPERIODTYPE(String PERIODTYPE) {
            this.PERIODTYPE = PERIODTYPE;
        }

        public String getRATEBEGIN() {
            return RATEBEGIN;
        }

        public void setRATEBEGIN(String RATEBEGIN) {
            this.RATEBEGIN = RATEBEGIN;
        }

        public String getRATEEND() {
            return RATEEND;
        }

        public void setRATEEND(String RATEEND) {
            this.RATEEND = RATEEND;
        }

        public String getLOANAMOUNT() {
            return LOANAMOUNT;
        }

        public void setLOANAMOUNT(String LOANAMOUNT) {
            this.LOANAMOUNT = LOANAMOUNT;
        }

        public String getLOANAMOUNTUNIT() {
            return LOANAMOUNTUNIT;
        }

        public void setLOANAMOUNTUNIT(String LOANAMOUNTUNIT) {
            this.LOANAMOUNTUNIT = LOANAMOUNTUNIT;
        }

        public String getLOANAMOUNTSIMPLE() {
            return LOANAMOUNTSIMPLE;
        }

        public void setLOANAMOUNTSIMPLE(String LOANAMOUNTSIMPLE) {
            this.LOANAMOUNTSIMPLE = LOANAMOUNTSIMPLE;
        }

        public String getSEQ() {
            return SEQ;
        }

        public void setSEQ(String SEQ) {
            this.SEQ = SEQ;
        }

        public String getAWARDFLG() {
            return AWARDFLG;
        }

        public void setAWARDFLG(String AWARDFLG) {
            this.AWARDFLG = AWARDFLG;
        }

        public String getCHKBORROWMORTGAGE() {
            return CHKBORROWMORTGAGE;
        }

        public void setCHKBORROWMORTGAGE(String CHKBORROWMORTGAGE) {
            this.CHKBORROWMORTGAGE = CHKBORROWMORTGAGE;
        }

        public String getCHKBORROWWANDER() {
            return CHKBORROWWANDER;
        }

        public void setCHKBORROWWANDER(String CHKBORROWWANDER) {
            this.CHKBORROWWANDER = CHKBORROWWANDER;
        }
    }
}
