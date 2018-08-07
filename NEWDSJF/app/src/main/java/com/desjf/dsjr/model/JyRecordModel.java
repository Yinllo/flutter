package com.desjf.dsjr.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Administrator on 2017/8/3 0003.
 * 交易记录
 */

public class JyRecordModel {


    /**
     * YM_KEY : 2017-06
     * YM_VAL : [{"OID_USER_ID":"2a016613fe06404988799137a4e503de","FUND_TYPE":"003","REVENUE_EXPEND_TYPE":"E","AMOUNT":33.75,"TOTAL_AMOUNT":253010.91,"USABLE_AMOUNT":203010.91,"FROZE_AMOUNT":0,"REMARK":"融资还款管理费扣除。金额：33.75元 。","INS_DATE":"2017-06-27"},{"OID_USER_ID":"2a016613fe06404988799137a4e503de","FUND_TYPE":"005","REVENUE_EXPEND_TYPE":"E","AMOUNT":225,"TOTAL_AMOUNT":253044.66,"USABLE_AMOUNT":203044.66,"FROZE_AMOUNT":0,"REMARK":"融资还款利息扣除。金额：225.00元 。","INS_DATE":"2017-06-27"},{"OID_USER_ID":"2a016613fe06404988799137a4e503de","FUND_TYPE":"004","REVENUE_EXPEND_TYPE":"E","AMOUNT":10000,"TOTAL_AMOUNT":253269.66,"USABLE_AMOUNT":203269.66,"FROZE_AMOUNT":0,"REMARK":"融资还款本金扣除。金额：10000.00元 。","INS_DATE":"2017-06-27"},{"OID_USER_ID":"2a016613fe06404988799137a4e503de","FUND_TYPE":"009","REVENUE_EXPEND_TYPE":"E","AMOUNT":100,"TOTAL_AMOUNT":263269.66,"USABLE_AMOUNT":213269.66,"FROZE_AMOUNT":0,"REMARK":"扣除融资成交费：100.00元。","INS_DATE":"2017-06-27"},{"OID_USER_ID":"2a016613fe06404988799137a4e503de","FUND_TYPE":"011","REVENUE_EXPEND_TYPE":"R","AMOUNT":10000,"TOTAL_AMOUNT":263369.66,"USABLE_AMOUNT":213369.66,"FROZE_AMOUNT":0,"REMARK":"融资金额入账。融资金额：10000.00元 ","INS_DATE":"2017-06-27"},{"OID_USER_ID":"2a016613fe06404988799137a4e503de","FUND_TYPE":"003","REVENUE_EXPEND_TYPE":"E","AMOUNT":45,"TOTAL_AMOUNT":253369.66,"USABLE_AMOUNT":203369.66,"FROZE_AMOUNT":0,"REMARK":"融资还款管理费扣除。金额：45.00元 。","INS_DATE":"2017-06-27"},{"OID_USER_ID":"2a016613fe06404988799137a4e503de","FUND_TYPE":"005","REVENUE_EXPEND_TYPE":"E","AMOUNT":300,"TOTAL_AMOUNT":253414.66,"USABLE_AMOUNT":203414.66,"FROZE_AMOUNT":0,"REMARK":"融资还款利息扣除。金额：300.00元 。","INS_DATE":"2017-06-27"},{"OID_USER_ID":"2a016613fe06404988799137a4e503de","FUND_TYPE":"009","REVENUE_EXPEND_TYPE":"E","AMOUNT":600,"TOTAL_AMOUNT":253714.66,"USABLE_AMOUNT":203714.66,"FROZE_AMOUNT":0,"REMARK":"扣除融资成交费：600.00元。","INS_DATE":"2017-06-27"},{"OID_USER_ID":"2a016613fe06404988799137a4e503de","FUND_TYPE":"011","REVENUE_EXPEND_TYPE":"R","AMOUNT":60000,"TOTAL_AMOUNT":254314.66,"USABLE_AMOUNT":204314.66,"FROZE_AMOUNT":0,"REMARK":"融资金额入账。融资金额：60000.00元 ","INS_DATE":"2017-06-27"},{"OID_USER_ID":"2a016613fe06404988799137a4e503de","FUND_TYPE":"009","REVENUE_EXPEND_TYPE":"E","AMOUNT":500,"TOTAL_AMOUNT":194314.66,"USABLE_AMOUNT":144314.66,"FROZE_AMOUNT":0,"REMARK":"扣除融资成交费：500.00元。","INS_DATE":"2017-06-27"},{"OID_USER_ID":"2a016613fe06404988799137a4e503de","FUND_TYPE":"011","REVENUE_EXPEND_TYPE":"R","AMOUNT":50000,"TOTAL_AMOUNT":194814.66,"USABLE_AMOUNT":144814.66,"FROZE_AMOUNT":0,"REMARK":"融资金额入账。融资金额：50000.00元 ","INS_DATE":"2017-06-27"},{"OID_USER_ID":"2a016613fe06404988799137a4e503de","FUND_TYPE":"003","REVENUE_EXPEND_TYPE":"E","AMOUNT":28.5,"TOTAL_AMOUNT":144814.66,"USABLE_AMOUNT":94814.66,"FROZE_AMOUNT":0,"REMARK":"融资还款管理费扣除。金额：28.50元 。","INS_DATE":"2017-06-27"}]
     */

    @SerializedName("YM_KEY")
    private String YMKEY;
    @SerializedName("YM_VAL")
    private List<YMVALBean> YMVAL;

    public String getYMKEY() {
        return YMKEY;
    }

    public void setYMKEY(String YMKEY) {
        this.YMKEY = YMKEY;
    }

    public List<YMVALBean> getYMVAL() {
        return YMVAL;
    }

    public void setYMVAL(List<YMVALBean> YMVAL) {
        this.YMVAL = YMVAL;
    }

    public static class YMVALBean {
        /**
         * OID_USER_ID : 2a016613fe06404988799137a4e503de
         * FUND_TYPE : 003
         * REVENUE_EXPEND_TYPE : E
         * AMOUNT : 33.75
         * TOTAL_AMOUNT : 253010.91
         * USABLE_AMOUNT : 203010.91
         * FROZE_AMOUNT : 0.0
         * REMARK : 融资还款管理费扣除。金额：33.75元 。
         * INS_DATE : 2017-06-27
         */

        @SerializedName("OID_USER_ID")
        private String OIDUSERID;
        @SerializedName("FUND_TYPE")
        private String FUNDTYPE;
        @SerializedName("REVENUE_EXPEND_TYPE")
        private String REVENUEEXPENDTYPE;
        @SerializedName("AMOUNT")
        private String AMOUNT;
        @SerializedName("TOTAL_AMOUNT")
        private String TOTALAMOUNT;
        @SerializedName("USABLE_AMOUNT")
        private String USABLEAMOUNT;
        @SerializedName("FROZE_AMOUNT")
        private String FROZEAMOUNT;
        @SerializedName("REMARK")
        private String REMARK;
        @SerializedName("INS_DATE")
        private String INSDATE;

        public String getOIDUSERID() {
            return OIDUSERID;
        }

        public void setOIDUSERID(String OIDUSERID) {
            this.OIDUSERID = OIDUSERID;
        }

        public String getFUNDTYPE() {
            return FUNDTYPE;
        }

        public void setFUNDTYPE(String FUNDTYPE) {
            this.FUNDTYPE = FUNDTYPE;
        }

        public String getREVENUEEXPENDTYPE() {
            return REVENUEEXPENDTYPE;
        }

        public void setREVENUEEXPENDTYPE(String REVENUEEXPENDTYPE) {
            this.REVENUEEXPENDTYPE = REVENUEEXPENDTYPE;
        }

        public String getAMOUNT() {
            return AMOUNT;
        }

        public void setAMOUNT(String AMOUNT) {
            this.AMOUNT = AMOUNT;
        }

        public String getTOTALAMOUNT() {
            return TOTALAMOUNT;
        }

        public void setTOTALAMOUNT(String TOTALAMOUNT) {
            this.TOTALAMOUNT = TOTALAMOUNT;
        }

        public String getUSABLEAMOUNT() {
            return USABLEAMOUNT;
        }

        public void setUSABLEAMOUNT(String USABLEAMOUNT) {
            this.USABLEAMOUNT = USABLEAMOUNT;
        }

        public String getFROZEAMOUNT() {
            return FROZEAMOUNT;
        }

        public void setFROZEAMOUNT(String FROZEAMOUNT) {
            this.FROZEAMOUNT = FROZEAMOUNT;
        }

        public String getREMARK() {
            return REMARK;
        }

        public void setREMARK(String REMARK) {
            this.REMARK = REMARK;
        }

        public String getINSDATE() {
            return INSDATE;
        }

        public void setINSDATE(String INSDATE) {
            this.INSDATE = INSDATE;
        }
    }
}
