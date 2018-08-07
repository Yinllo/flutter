package com.desjf.dsjr.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Administrator on 2017/8/1 0001.
 */

public class AssignmentModel {
    @SerializedName("RESULT_LIST")
    private List<AssignmentModel.RESULTLISTBean> RESULTLIST;

    public List<AssignmentModel.RESULTLISTBean> getRESULTLIST() {
        return RESULTLIST;
    }

    public void setRESULTLIST(List<AssignmentModel.RESULTLISTBean> RESULTLIST) {
        this.RESULTLIST = RESULTLIST;
    }

    public static class RESULTLISTBean {


        @SerializedName("TRANSFER_CONTRACT_ID")
        private String TRANSFERCONTRACTID;
        @SerializedName("DISCOUNT_SCALE")
        private String DISCOUNTSCALE;
        @SerializedName("FINANCE_INTEREST_RATE")
        private String FINANCEINTERESTRATE;
        @SerializedName("TRANSFER_PERIOD")
        private String TRANSFERPERIOD;
        @SerializedName("INTEREST_RATE_TYPE")
        private String INTERESTRATETYPE;
        @SerializedName("TRANSFER_CAPITAL_WAIT")
        private String TRANSFERCAPITALWAIT;
        @SerializedName("OID_PLATFORM_PRODUCTS_ID")
        private String OIDPLATFORMPRODUCTSID;
        @SerializedName("TRANSFER_STATUS")
        private String TRANSFERSTATUS;
        @SerializedName("TRANSFER_CAPITAL_SCALE")
        private String TRANSFERCAPITALSCALE;



        public String getOIDPLATFORMPRODUCTSID() {
            return OIDPLATFORMPRODUCTSID;
        }

        public void setOIDPLATFORMPRODUCTSID(String OIDPLATFORMPRODUCTSID) {
            this.OIDPLATFORMPRODUCTSID = OIDPLATFORMPRODUCTSID;
        }

        public String getTRANSFERSTATUS() {
            return TRANSFERSTATUS;
        }

        public void setTRANSFERSTATUS(String TRANSFERSTATUS) {
            this.TRANSFERSTATUS = TRANSFERSTATUS;
        }

        public String getTRANSFERCAPITALSCALE() {
            return TRANSFERCAPITALSCALE;
        }

        public void setTRANSFERCAPITALSCALE(String TRANSFERCAPITALSCALE) {
            this.TRANSFERCAPITALSCALE = TRANSFERCAPITALSCALE;
        }

        public String getTRANSFERCONTRACTID() {
            return TRANSFERCONTRACTID;
        }

        public void setTRANSFERCONTRACTID(String TRANSFERCONTRACTID) {
            this.TRANSFERCONTRACTID = TRANSFERCONTRACTID;
        }

        public String getDISCOUNTSCALE() {
            return DISCOUNTSCALE;
        }

        public void setDISCOUNTSCALE(String DISCOUNTSCALE) {
            this.DISCOUNTSCALE = DISCOUNTSCALE;
        }

        public String getFINANCEINTERESTRATE() {
            return FINANCEINTERESTRATE;
        }

        public void setFINANCEINTERESTRATE(String FINANCEINTERESTRATE) {
            this.FINANCEINTERESTRATE = FINANCEINTERESTRATE;
        }

        public String getTRANSFERPERIOD() {
            return TRANSFERPERIOD;
        }

        public void setTRANSFERPERIOD(String TRANSFERPERIOD) {
            this.TRANSFERPERIOD = TRANSFERPERIOD;
        }

        public String getINTERESTRATETYPE() {
            return INTERESTRATETYPE;
        }

        public void setINTERESTRATETYPE(String INTERESTRATETYPE) {
            this.INTERESTRATETYPE = INTERESTRATETYPE;
        }

        public String getTRANSFERCAPITALWAIT() {
            return TRANSFERCAPITALWAIT;
        }

        public void setTRANSFERCAPITALWAIT(String TRANSFERCAPITALWAIT) {
            this.TRANSFERCAPITALWAIT = TRANSFERCAPITALWAIT;
        }
    }
}
