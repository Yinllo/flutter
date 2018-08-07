package com.desjf.dsjr.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/7/28 0028.
 */

public class MyInvestModel implements Serializable{

    /**
     * OID_TENDER_ID : 65e4943f5cf942b08f3d8df9ba2ab78a
     * TRANSFER_CONTRACT_ID :
     * OID_PLATFORM_PRODUCTS_ID : ac9e0186ee5d499aa660d1a8adecc028
     * OID_USER_ID : 2a016613fe06404988799137a4e503de
     * TENDER_AMOUNT : 100.0
     * RECOVER_AMOUNT_TOTAL : 103.25
     * RECOVER_AMOUNT_TOTAL_YES : 0.0
     * RECOVER_AMOUNT_TOTAL_WAIT : 103.25
     * RECOVER_AMOUNT_INTEREST : 3.25
     * RECOVER_AMOUNT_COUPON : 0.0
     * OVERDUE_INTEREST : 0.0
     * OVERDUE_FORFEIT : 0.0
     * INS_DATE : 2017-06-23 11:08:34.0
     *
     * INTEREST_RATE_TYPE : 1
     * FINANCE_PERIOD : 5
     * FINANCE_FULL_FLG : 0
     * PRODUCTS_TITLE : 99999999999
     * RECOVER_DATE : 2017-11-23
     * TENDER_FROM : 1
     * OID_TRANSFER_ID :
     * FINANCE_PERIOD_FORMAT : 5
     * INS_DATE_FORMAT : 2017-06-23
     * RECOVER_DATE_FORMAT : 2017-11-23
     * STATUS : 投资中
     */
    @SerializedName("RATE")
    private String RATE;
    @SerializedName("OID_TENDER_ID")
    private String OIDTENDERID;
    @SerializedName("TRANSFER_CONTRACT_ID")
    private String TRANSFERCONTRACTID;
    @SerializedName("OID_PLATFORM_PRODUCTS_ID")
    private String OIDPLATFORMPRODUCTSID;
    @SerializedName("OID_USER_ID")
    private String OIDUSERID;
    @SerializedName("TENDER_AMOUNT")
    private String TENDERAMOUNT;
    @SerializedName("RECOVER_AMOUNT_TOTAL")
    private String RECOVERAMOUNTTOTAL;
    @SerializedName("RECOVER_AMOUNT_TOTAL_YES")
    private String RECOVERAMOUNTTOTALYES;
    @SerializedName("RECOVER_AMOUNT_TOTAL_WAIT")
    private String RECOVERAMOUNTTOTALWAIT;
    @SerializedName("RECOVER_AMOUNT_INTEREST")
    private String RECOVERAMOUNTINTEREST;
    @SerializedName("RECOVER_AMOUNT_COUPON")
    private String RECOVERAMOUNTCOUPON;
    @SerializedName("OVERDUE_INTEREST")
    private String OVERDUEINTEREST;
    @SerializedName("OVERDUE_FORFEIT")
    private String OVERDUEFORFEIT;
    @SerializedName("INS_DATE")
    private String INSDATE;
    @SerializedName("FINANCE_INTEREST_RATE")
    private String FINANCEINTERESTRATE;
    @SerializedName("INTEREST_RATE_TYPE")
    private String INTERESTRATETYPE;
    @SerializedName("FINANCE_PERIOD")
    private String FINANCEPERIOD;
    @SerializedName("FINANCE_FULL_FLG")
    private String FINANCEFULLFLG;
    @SerializedName("PRODUCTS_TITLE")
    private String PRODUCTSTITLE;
    @SerializedName("RECOVER_DATE")
    private String RECOVERDATE;
    @SerializedName("TENDER_FROM")
    private String TENDERFROM;
    @SerializedName("OID_TRANSFER_ID")
    private String OIDTRANSFERID;
    @SerializedName("FINANCE_PERIOD_FORMAT")
    private String FINANCEPERIODFORMAT;
    @SerializedName("INS_DATE_FORMAT")
    private String INSDATEFORMAT;
    @SerializedName("RECOVER_DATE_FORMAT")
    private String RECOVERDATEFORMAT;
    @SerializedName("STATUS")
    private String STATUS;
    @SerializedName("PERIOD")
    private String PERIOD;
    @SerializedName("TENDER_AMOUNT_UNIT")
    private String TENDERAMOUNTUNIT;
    @SerializedName("REMAIN_DAYS")
    private String REMAINDAYS;

    @SerializedName("TRANSFER_CAPITAL_FORMAT")
    private String TRANSFERCAPITALFORMAT;

    @SerializedName("TRANSFER_CAPITAL_YES")
    private String TRANSFERCAPITALYES;

    @SerializedName("REMAIN_TIME")
    private String REMAINTIME;

    @SerializedName("TRANSFER_CAPITAL")
    private String TRANSFERCAPITAL;

    @SerializedName("TRANSFER_AMOUNT")
    private String TRANSFERAMOUNT;

    @SerializedName("CAPITAL_WAIT")
    private String CAPITALWAIT;

    @SerializedName("TRANSFER_DETAIL")
    private TRANSFERDETAILBean TRANSFERDETAIL;

    @SerializedName("RECOVER_AMOUNT_INTEREST_YES")
    private String RECOVERAMOUNTINTERESTYES;

    @SerializedName("RECOVER_AMOUNT_COUPON_YES")
    private String RECOVERAMOUNTCOUPONYES;

    public String getRECOVERAMOUNTINTERESTYES() {
        return RECOVERAMOUNTINTERESTYES;
    }

    public void setRECOVERAMOUNTINTERESTYES(String RECOVERAMOUNTINTERESTYES) {
        this.RECOVERAMOUNTINTERESTYES = RECOVERAMOUNTINTERESTYES;
    }

    public String getRECOVERAMOUNTCOUPONYES() {
        return RECOVERAMOUNTCOUPONYES;
    }

    public void setRECOVERAMOUNTCOUPONYES(String RECOVERAMOUNTCOUPONYES) {
        this.RECOVERAMOUNTCOUPONYES = RECOVERAMOUNTCOUPONYES;
    }

    public TRANSFERDETAILBean getTRANSFERDETAIL() {
        return TRANSFERDETAIL;
    }

    public void setTRANSFERDETAIL(TRANSFERDETAILBean TRANSFERDETAIL) {
        this.TRANSFERDETAIL = TRANSFERDETAIL;
    }

    public static class TRANSFERDETAILBean implements Serializable{
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

    public String getCAPITALWAIT() {
        return CAPITALWAIT;
    }

    public void setCAPITALWAIT(String CAPITALWAIT) {
        this.CAPITALWAIT = CAPITALWAIT;
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

    public String getREMAINDAYS() {
        return REMAINDAYS;
    }

    public void setREMAINDAYS(String REMAINDAYS) {
        this.REMAINDAYS = REMAINDAYS;
    }

    public String getTENDERAMOUNTUNIT() {
        return TENDERAMOUNTUNIT;
    }

    public void setTENDERAMOUNTUNIT(String TENDERAMOUNTUNIT) {
        this.TENDERAMOUNTUNIT = TENDERAMOUNTUNIT;
    }

    public String getPERIOD() {
        return PERIOD;
    }

    public void setPERIOD(String PERIOD) {
        this.PERIOD = PERIOD;
    }

    public String getRATE() {
        return RATE;
    }

    public void setRATE(String RATE) {
        this.RATE = RATE;
    }

    public String getOIDTENDERID() {
        return OIDTENDERID;
    }

    public void setOIDTENDERID(String OIDTENDERID) {
        this.OIDTENDERID = OIDTENDERID;
    }

    public String getTRANSFERCONTRACTID() {
        return TRANSFERCONTRACTID;
    }

    public void setTRANSFERCONTRACTID(String TRANSFERCONTRACTID) {
        this.TRANSFERCONTRACTID = TRANSFERCONTRACTID;
    }

    public String getOIDPLATFORMPRODUCTSID() {
        return OIDPLATFORMPRODUCTSID;
    }

    public void setOIDPLATFORMPRODUCTSID(String OIDPLATFORMPRODUCTSID) {
        this.OIDPLATFORMPRODUCTSID = OIDPLATFORMPRODUCTSID;
    }

    public String getOIDUSERID() {
        return OIDUSERID;
    }

    public void setOIDUSERID(String OIDUSERID) {
        this.OIDUSERID = OIDUSERID;
    }

    public String getTENDERAMOUNT() {
        return TENDERAMOUNT;
    }

    public void setTENDERAMOUNT(String TENDERAMOUNT) {
        this.TENDERAMOUNT = TENDERAMOUNT;
    }

    public String getRECOVERAMOUNTTOTAL() {
        return RECOVERAMOUNTTOTAL;
    }

    public void setRECOVERAMOUNTTOTAL(String RECOVERAMOUNTTOTAL) {
        this.RECOVERAMOUNTTOTAL = RECOVERAMOUNTTOTAL;
    }

    public String getRECOVERAMOUNTTOTALYES() {
        return RECOVERAMOUNTTOTALYES;
    }

    public void setRECOVERAMOUNTTOTALYES(String RECOVERAMOUNTTOTALYES) {
        this.RECOVERAMOUNTTOTALYES = RECOVERAMOUNTTOTALYES;
    }

    public String getRECOVERAMOUNTTOTALWAIT() {
        return RECOVERAMOUNTTOTALWAIT;
    }

    public void setRECOVERAMOUNTTOTALWAIT(String RECOVERAMOUNTTOTALWAIT) {
        this.RECOVERAMOUNTTOTALWAIT = RECOVERAMOUNTTOTALWAIT;
    }

    public String getRECOVERAMOUNTINTEREST() {
        return RECOVERAMOUNTINTEREST;
    }

    public void setRECOVERAMOUNTINTEREST(String RECOVERAMOUNTINTEREST) {
        this.RECOVERAMOUNTINTEREST = RECOVERAMOUNTINTEREST;
    }

    public String getRECOVERAMOUNTCOUPON() {
        return RECOVERAMOUNTCOUPON;
    }

    public void setRECOVERAMOUNTCOUPON(String RECOVERAMOUNTCOUPON) {
        this.RECOVERAMOUNTCOUPON = RECOVERAMOUNTCOUPON;
    }

    public String getOVERDUEINTEREST() {
        return OVERDUEINTEREST;
    }

    public void setOVERDUEINTEREST(String OVERDUEINTEREST) {
        this.OVERDUEINTEREST = OVERDUEINTEREST;
    }

    public String getOVERDUEFORFEIT() {
        return OVERDUEFORFEIT;
    }

    public void setOVERDUEFORFEIT(String OVERDUEFORFEIT) {
        this.OVERDUEFORFEIT = OVERDUEFORFEIT;
    }

    public String getINSDATE() {
        return INSDATE;
    }

    public void setINSDATE(String INSDATE) {
        this.INSDATE = INSDATE;
    }

    public String getFINANCEINTERESTRATE() {
        return FINANCEINTERESTRATE;
    }

    public void setFINANCEINTERESTRATE(String FINANCEINTERESTRATE) {
        this.FINANCEINTERESTRATE = FINANCEINTERESTRATE;
    }

    public String getINTERESTRATETYPE() {
        return INTERESTRATETYPE;
    }

    public void setINTERESTRATETYPE(String INTERESTRATETYPE) {
        this.INTERESTRATETYPE = INTERESTRATETYPE;
    }

    public String getFINANCEPERIOD() {
        return FINANCEPERIOD;
    }

    public void setFINANCEPERIOD(String FINANCEPERIOD) {
        this.FINANCEPERIOD = FINANCEPERIOD;
    }

    public String getFINANCEFULLFLG() {
        return FINANCEFULLFLG;
    }

    public void setFINANCEFULLFLG(String FINANCEFULLFLG) {
        this.FINANCEFULLFLG = FINANCEFULLFLG;
    }

    public String getPRODUCTSTITLE() {
        return PRODUCTSTITLE;
    }

    public void setPRODUCTSTITLE(String PRODUCTSTITLE) {
        this.PRODUCTSTITLE = PRODUCTSTITLE;
    }

    public String getRECOVERDATE() {
        return RECOVERDATE;
    }

    public void setRECOVERDATE(String RECOVERDATE) {
        this.RECOVERDATE = RECOVERDATE;
    }

    public String getTENDERFROM() {
        return TENDERFROM;
    }

    public void setTENDERFROM(String TENDERFROM) {
        this.TENDERFROM = TENDERFROM;
    }

    public String getOIDTRANSFERID() {
        return OIDTRANSFERID;
    }

    public void setOIDTRANSFERID(String OIDTRANSFERID) {
        this.OIDTRANSFERID = OIDTRANSFERID;
    }

    public String getFINANCEPERIODFORMAT() {
        return FINANCEPERIODFORMAT;
    }

    public void setFINANCEPERIODFORMAT(String FINANCEPERIODFORMAT) {
        this.FINANCEPERIODFORMAT = FINANCEPERIODFORMAT;
    }

    public String getINSDATEFORMAT() {
        return INSDATEFORMAT;
    }

    public void setINSDATEFORMAT(String INSDATEFORMAT) {
        this.INSDATEFORMAT = INSDATEFORMAT;
    }

    public String getRECOVERDATEFORMAT() {
        return RECOVERDATEFORMAT;
    }

    public void setRECOVERDATEFORMAT(String RECOVERDATEFORMAT) {
        this.RECOVERDATEFORMAT = RECOVERDATEFORMAT;
    }

    public String getSTATUS() {
        return STATUS;
    }

    public void setSTATUS(String STATUS) {
        this.STATUS = STATUS;
    }
}
