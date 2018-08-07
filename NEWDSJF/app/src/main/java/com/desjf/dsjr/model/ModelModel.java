package com.desjf.dsjr.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Administrator on 2017/8/15 0015.
 */

public class ModelModel {

    /**
     * TRANSFER_CONTRACT_ID : DESJF1708150004Z
     * PRODUCTS_TITLE : zhuangrangbiao
     * FINANCE_INTEREST_RATE : 10.00
     * FINANCE_PERIOD_FORMAT : 10个月
     * TRANSFER_CAPITAL_FORMAT : 1000.00/0.00
     * TRANSFER_CAPITAL_YES : 0.00
     * REMAIN_TIME : 22小时39分钟
     * TENDER_AMOUNT : 1000.00
     * TRANSFER_CAPITAL : 1000.00
     * TRANSFER_AMOUNT : 1004.30
     * TRANSFER_DETAIL : {"DETAIL_TENDER_AMOUNT":"1000.00","DETAIL_TRANSFER_CAPITAL":"1000.00","DETAIL_FAIR_VALUE":"1004.30","DETAIL_TRANSFER_AMOUNT":"1004.30","DETAIL_DISCOUNT_SCALE":"0","DETAIL_DISCOUNT_AMOUNT":"0.00","DETAIL_TRANSFER_TIME":"2017-08-15 14:54:21","DETAIL_TRANSFER_MANAGE_FEE":"100.00","DETAIL_TRANSFER_STATUS":"转让中"}
     */

    @SerializedName("TRANSFER_CONTRACT_ID")
    private String TRANSFERCONTRACTID;
    @SerializedName("PRODUCTS_TITLE")
    private String PRODUCTSTITLE;
    @SerializedName("FINANCE_INTEREST_RATE")
    private String FINANCEINTERESTRATE;
    @SerializedName("FINANCE_PERIOD_FORMAT")
    private String FINANCEPERIODFORMAT;
    @SerializedName("TRANSFER_CAPITAL_FORMAT")
    private String TRANSFERCAPITALFORMAT;
    @SerializedName("TRANSFER_CAPITAL_YES")
    private String TRANSFERCAPITALYES;
    @SerializedName("REMAIN_TIME")
    private String REMAINTIME;
    @SerializedName("TENDER_AMOUNT")
    private String TENDERAMOUNT;
    @SerializedName("TRANSFER_CAPITAL")
    private String TRANSFERCAPITAL;
    @SerializedName("TRANSFER_AMOUNT")
    private String TRANSFERAMOUNT;
    @SerializedName("TRANSFER_DETAIL")
    private TRANSFERDETAILBean TRANSFERDETAIL;

    public String getTRANSFERCONTRACTID() {
        return TRANSFERCONTRACTID;
    }

    public void setTRANSFERCONTRACTID(String TRANSFERCONTRACTID) {
        this.TRANSFERCONTRACTID = TRANSFERCONTRACTID;
    }

    public String getPRODUCTSTITLE() {
        return PRODUCTSTITLE;
    }

    public void setPRODUCTSTITLE(String PRODUCTSTITLE) {
        this.PRODUCTSTITLE = PRODUCTSTITLE;
    }

    public String getFINANCEINTERESTRATE() {
        return FINANCEINTERESTRATE;
    }

    public void setFINANCEINTERESTRATE(String FINANCEINTERESTRATE) {
        this.FINANCEINTERESTRATE = FINANCEINTERESTRATE;
    }

    public String getFINANCEPERIODFORMAT() {
        return FINANCEPERIODFORMAT;
    }

    public void setFINANCEPERIODFORMAT(String FINANCEPERIODFORMAT) {
        this.FINANCEPERIODFORMAT = FINANCEPERIODFORMAT;
    }

    public String getTRANSFERCAPITALFORMAT() {
        return TRANSFERCAPITALFORMAT;
    }

    public void setTRANSFERCAPITALFORMAT(String TRANSFERCAPITALFORMAT) {
        this.TRANSFERCAPITALFORMAT = TRANSFERCAPITALFORMAT;
    }

    public String getTRANSFERCAPITALYES() {
        return TRANSFERCAPITALYES;
    }

    public void setTRANSFERCAPITALYES(String TRANSFERCAPITALYES) {
        this.TRANSFERCAPITALYES = TRANSFERCAPITALYES;
    }

    public String getREMAINTIME() {
        return REMAINTIME;
    }

    public void setREMAINTIME(String REMAINTIME) {
        this.REMAINTIME = REMAINTIME;
    }

    public String getTENDERAMOUNT() {
        return TENDERAMOUNT;
    }

    public void setTENDERAMOUNT(String TENDERAMOUNT) {
        this.TENDERAMOUNT = TENDERAMOUNT;
    }

    public String getTRANSFERCAPITAL() {
        return TRANSFERCAPITAL;
    }

    public void setTRANSFERCAPITAL(String TRANSFERCAPITAL) {
        this.TRANSFERCAPITAL = TRANSFERCAPITAL;
    }

    public String getTRANSFERAMOUNT() {
        return TRANSFERAMOUNT;
    }

    public void setTRANSFERAMOUNT(String TRANSFERAMOUNT) {
        this.TRANSFERAMOUNT = TRANSFERAMOUNT;
    }

    public TRANSFERDETAILBean getTRANSFERDETAIL() {
        return TRANSFERDETAIL;
    }

    public void setTRANSFERDETAIL(TRANSFERDETAILBean TRANSFERDETAIL) {
        this.TRANSFERDETAIL = TRANSFERDETAIL;
    }

    public static class TRANSFERDETAILBean {
        /**
         * DETAIL_TENDER_AMOUNT : 1000.00
         * DETAIL_TRANSFER_CAPITAL : 1000.00
         * DETAIL_FAIR_VALUE : 1004.30
         * DETAIL_TRANSFER_AMOUNT : 1004.30
         * DETAIL_DISCOUNT_SCALE : 0
         * DETAIL_DISCOUNT_AMOUNT : 0.00
         * DETAIL_TRANSFER_TIME : 2017-08-15 14:54:21
         * DETAIL_TRANSFER_MANAGE_FEE : 100.00
         * DETAIL_TRANSFER_STATUS : 转让中
         */

        @SerializedName("DETAIL_TENDER_AMOUNT")
        private String DETAILTENDERAMOUNT;
        @SerializedName("DETAIL_TRANSFER_CAPITAL")
        private String DETAILTRANSFERCAPITAL;
        @SerializedName("DETAIL_FAIR_VALUE")
        private String DETAILFAIRVALUE;
        @SerializedName("DETAIL_TRANSFER_AMOUNT")
        private String DETAILTRANSFERAMOUNT;
        @SerializedName("DETAIL_DISCOUNT_SCALE")
        private String DETAILDISCOUNTSCALE;
        @SerializedName("DETAIL_DISCOUNT_AMOUNT")
        private String DETAILDISCOUNTAMOUNT;
        @SerializedName("DETAIL_TRANSFER_TIME")
        private String DETAILTRANSFERTIME;
        @SerializedName("DETAIL_TRANSFER_MANAGE_FEE")
        private String DETAILTRANSFERMANAGEFEE;
        @SerializedName("DETAIL_TRANSFER_STATUS")
        private String DETAILTRANSFERSTATUS;

        public String getDETAILTENDERAMOUNT() {
            return DETAILTENDERAMOUNT;
        }

        public void setDETAILTENDERAMOUNT(String DETAILTENDERAMOUNT) {
            this.DETAILTENDERAMOUNT = DETAILTENDERAMOUNT;
        }

        public String getDETAILTRANSFERCAPITAL() {
            return DETAILTRANSFERCAPITAL;
        }

        public void setDETAILTRANSFERCAPITAL(String DETAILTRANSFERCAPITAL) {
            this.DETAILTRANSFERCAPITAL = DETAILTRANSFERCAPITAL;
        }

        public String getDETAILFAIRVALUE() {
            return DETAILFAIRVALUE;
        }

        public void setDETAILFAIRVALUE(String DETAILFAIRVALUE) {
            this.DETAILFAIRVALUE = DETAILFAIRVALUE;
        }

        public String getDETAILTRANSFERAMOUNT() {
            return DETAILTRANSFERAMOUNT;
        }

        public void setDETAILTRANSFERAMOUNT(String DETAILTRANSFERAMOUNT) {
            this.DETAILTRANSFERAMOUNT = DETAILTRANSFERAMOUNT;
        }

        public String getDETAILDISCOUNTSCALE() {
            return DETAILDISCOUNTSCALE;
        }

        public void setDETAILDISCOUNTSCALE(String DETAILDISCOUNTSCALE) {
            this.DETAILDISCOUNTSCALE = DETAILDISCOUNTSCALE;
        }

        public String getDETAILDISCOUNTAMOUNT() {
            return DETAILDISCOUNTAMOUNT;
        }

        public void setDETAILDISCOUNTAMOUNT(String DETAILDISCOUNTAMOUNT) {
            this.DETAILDISCOUNTAMOUNT = DETAILDISCOUNTAMOUNT;
        }

        public String getDETAILTRANSFERTIME() {
            return DETAILTRANSFERTIME;
        }

        public void setDETAILTRANSFERTIME(String DETAILTRANSFERTIME) {
            this.DETAILTRANSFERTIME = DETAILTRANSFERTIME;
        }

        public String getDETAILTRANSFERMANAGEFEE() {
            return DETAILTRANSFERMANAGEFEE;
        }

        public void setDETAILTRANSFERMANAGEFEE(String DETAILTRANSFERMANAGEFEE) {
            this.DETAILTRANSFERMANAGEFEE = DETAILTRANSFERMANAGEFEE;
        }

        public String getDETAILTRANSFERSTATUS() {
            return DETAILTRANSFERSTATUS;
        }

        public void setDETAILTRANSFERSTATUS(String DETAILTRANSFERSTATUS) {
            this.DETAILTRANSFERSTATUS = DETAILTRANSFERSTATUS;
        }
    }
}
