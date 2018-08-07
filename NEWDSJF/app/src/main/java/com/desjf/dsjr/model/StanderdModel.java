package com.desjf.dsjr.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Administrator on 2017/8/1 0001.
 */

public class StanderdModel {

    @SerializedName("RESULT_LIST")
    private List<RESULTLISTBean> RESULTLIST;

    public List<RESULTLISTBean> getRESULTLIST() {
        return RESULTLIST;
    }

    public void setRESULTLIST(List<RESULTLISTBean> RESULTLIST) {
        this.RESULTLIST = RESULTLIST;
    }

    public static class RESULTLISTBean {
        /**
         * NOVICE_FLG : 0
         * COM_FLG : 2
         * PRODUCTS_TITLE : 散标未开始，倒计时20170728001
         * OID_PLATFORM_PRODUCTS_ID : 2485e946e4be4c3da0bb0a8cff7bc161
         * TENDER_AWARD_FLG : 1
         * TENDER_AWARD_SCALE : 20.00%
         * FINANCE_AMOUNT : 500000.00
         * FINANCE_AMOUNT_WAIT : 剩余500,000.00元
         * FINANCE_PERIOD : 5
         * INTEREST_RATE_TYPE : 个月
         * FINANCE_INTEREST_RATE1 : 24.00
         * FINANCE_AMOUNT_SCALE : 0.00%
         * FINANCE_STATUS    标的状态
         */

        @SerializedName("NOVICE_FLG")
        private String NOVICEFLG;
        @SerializedName("COM_FLG")
        private String COMFLG;
        @SerializedName("PRODUCTS_TITLE")
        private String PRODUCTSTITLE;
        @SerializedName("OID_PLATFORM_PRODUCTS_ID")
        private String OIDPLATFORMPRODUCTSID;
        @SerializedName("TENDER_AWARD_FLG")
        private String TENDERAWARDFLG;
        @SerializedName("TENDER_AWARD_SCALE")
        private String TENDERAWARDSCALE;
        @SerializedName("FINANCE_AMOUNT")
        private String FINANCEAMOUNT;
        @SerializedName("FINANCE_AMOUNT_WAIT")
        private String FINANCEAMOUNTWAIT;
        @SerializedName("FINANCE_PERIOD")
        private String FINANCEPERIOD;
        @SerializedName("INTEREST_RATE_TYPE")
        private String INTERESTRATETYPE;
        @SerializedName("FINANCE_INTEREST_RATE1")
        private String FINANCEINTERESTRATE1;
        @SerializedName("FINANCE_AMOUNT_SCALE")
        private String FINANCEAMOUNTSCALE;
        @SerializedName("PRODUCTS_STATUS")
        private String PRODUCTSSTATUS;
        @SerializedName("FINANCE_START_DATE")
        private String FINANCESTARTDATE;
        @SerializedName("FINANCE_END_DATE")
        private String FINANCEENDDATE;
        @SerializedName("SYSTEM_DATE")
        private String SYSTEMDATE;
        @SerializedName("FINANCE_STATUS")//标的状态
        private String FINANCESTATUS;
        @SerializedName("FULL_TIP")//满标人描述信息
        private String FULLTIP;
        @SerializedName("FULL_AWARD")
        private String FULLAWARD;//满标人奖励金额
        @SerializedName("ADVERTISE_WORD")
        private  String ADVERTISEWORD;//广告位文字显示
        private  String addRate;//加息

        public String getAddRate() {
            return addRate == null ? "" : addRate;
        }

        public void setAddRate(String addRate) {
            this.addRate = addRate;
        }

        public String getADVERTISEWORD() {
            return ADVERTISEWORD;
        }

        public void setADVERTISE_WORD(String ADVERTISE_WORD) {
            this.ADVERTISEWORD = ADVERTISE_WORD;
        }

        public String getFULLAWARD() {
            return FULLAWARD;
        }

        public void setFULLAWARD(String FULLAWARD) {
            this.FULLAWARD = FULLAWARD;
        }

        public String getFULLTIP() {
            return FULLTIP;
        }

        public void setFULLTIP(String FULLTIP) {
            this.FULLTIP = FULLTIP;
        }

        public String getFINANCESTATUS() {
            return FINANCESTATUS;
        }

        public void setFINANCESTATUS(String FINANCESTATUS) {
            this.FINANCESTATUS = FINANCESTATUS;
        }

        public String getPRODUCTSSTATUS() {
            return PRODUCTSSTATUS;
        }

        public void setPRODUCTSSTATUS(String PRODUCTSSTATUS) {
            this.PRODUCTSSTATUS = PRODUCTSSTATUS;
        }

        public String getFINANCESTARTDATE() {
            return FINANCESTARTDATE;
        }

        public void setFINANCESTARTDATE(String FINANCESTARTDATE) {
            this.FINANCESTARTDATE = FINANCESTARTDATE;
        }

        public String getFINANCEENDDATE() {
            return FINANCEENDDATE;
        }

        public void setFINANCEENDDATE(String FINANCEENDDATE) {
            this.FINANCEENDDATE = FINANCEENDDATE;
        }

        public String getSYSTEMDATE() {
            return SYSTEMDATE;
        }

        public void setSYSTEMDATE(String SYSTEMDATE) {
            this.SYSTEMDATE = SYSTEMDATE;
        }

        public String getNOVICEFLG() {
            return NOVICEFLG;
        }

        public void setNOVICEFLG(String NOVICEFLG) {
            this.NOVICEFLG = NOVICEFLG;
        }

        public String getCOMFLG() {
            return COMFLG;
        }

        public void setCOMFLG(String COMFLG) {
            this.COMFLG = COMFLG;
        }

        public String getPRODUCTSTITLE() {
            return PRODUCTSTITLE;
        }

        public void setPRODUCTSTITLE(String PRODUCTSTITLE) {
            this.PRODUCTSTITLE = PRODUCTSTITLE;
        }

        public String getOIDPLATFORMPRODUCTSID() {
            return OIDPLATFORMPRODUCTSID;
        }

        public void setOIDPLATFORMPRODUCTSID(String OIDPLATFORMPRODUCTSID) {
            this.OIDPLATFORMPRODUCTSID = OIDPLATFORMPRODUCTSID;
        }

        public String getTENDERAWARDFLG() {
            return TENDERAWARDFLG;
        }

        public void setTENDERAWARDFLG(String TENDERAWARDFLG) {
            this.TENDERAWARDFLG = TENDERAWARDFLG;
        }

        public String getTENDERAWARDSCALE() {
            return TENDERAWARDSCALE;
        }

        public void setTENDERAWARDSCALE(String TENDERAWARDSCALE) {
            this.TENDERAWARDSCALE = TENDERAWARDSCALE;
        }

        public String getFINANCEAMOUNT() {
            return FINANCEAMOUNT;
        }

        public void setFINANCEAMOUNT(String FINANCEAMOUNT) {
            this.FINANCEAMOUNT = FINANCEAMOUNT;
        }

        public String getFINANCEAMOUNTWAIT() {
            return FINANCEAMOUNTWAIT;
        }

        public void setFINANCEAMOUNTWAIT(String FINANCEAMOUNTWAIT) {
            this.FINANCEAMOUNTWAIT = FINANCEAMOUNTWAIT;
        }

        public String getFINANCEPERIOD() {
            return FINANCEPERIOD;
        }

        public void setFINANCEPERIOD(String FINANCEPERIOD) {
            this.FINANCEPERIOD = FINANCEPERIOD;
        }

        public String getINTERESTRATETYPE() {
            return INTERESTRATETYPE;
        }

        public void setINTERESTRATETYPE(String INTERESTRATETYPE) {
            this.INTERESTRATETYPE = INTERESTRATETYPE;
        }

        public String getFINANCEINTERESTRATE1() {
            return FINANCEINTERESTRATE1;
        }

        public void setFINANCEINTERESTRATE1(String FINANCEINTERESTRATE1) {
            this.FINANCEINTERESTRATE1 = FINANCEINTERESTRATE1;
        }

        public String getFINANCEAMOUNTSCALE() {
            return FINANCEAMOUNTSCALE;
        }

        public void setFINANCEAMOUNTSCALE(String FINANCEAMOUNTSCALE) {
            this.FINANCEAMOUNTSCALE = FINANCEAMOUNTSCALE;
        }
    }
}
