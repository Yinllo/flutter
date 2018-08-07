package com.desjf.dsjr.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Administrator on 2017/7/28 0028.
 */

public class AllInterestModel {

    /**
     * TYPE : 加息券
     * RATE_COUPON_ID : 747
     * RATE_COUPON_POSITION : 6%
     * COUPON_ORIGIN_AVENUE : 151****9420(赠送)
     * INS_DATE : 2017-08-08
     * MINIMUM_TENDER_AMOUNT : 200
     * MINIMUM_TENDER_AMOUNT_UNIT : 元
     * START_DATE : 17-08-08
     * END_DATE : 17-09-07
     * EFFECTIVE_DATE : 17-08-08~17-09-07
     * USED_FLG : 0
     * OVERDUE_FLG : 0
     * DIFF_FLG : 0
     * TRANSFER_ABLE_FLG : 1
     * SEND_OUT_FLG : 0
     */

    @SerializedName("TYPE")
    private String TYPE;
    @SerializedName("RATE_COUPON_ID")
    private String RATECOUPONID;
    @SerializedName("RATE_COUPON_POSITION")
    private String RATECOUPONPOSITION;
    @SerializedName("COUPON_ORIGIN_AVENUE")
    private String COUPONORIGINAVENUE;
    @SerializedName("INS_DATE")
    private String INSDATE;
    @SerializedName("MINIMUM_TENDER_AMOUNT")
    private String MINIMUMTENDERAMOUNT;
    @SerializedName("MINIMUM_TENDER_AMOUNT_UNIT")
    private String MINIMUMTENDERAMOUNTUNIT;
    @SerializedName("START_DATE")
    private String STARTDATE;
    @SerializedName("END_DATE")
    private String ENDDATE;
    @SerializedName("EFFECTIVE_DATE")
    private String EFFECTIVEDATE;
    @SerializedName("USED_FLG")
    private String USEDFLG;
    @SerializedName("OVERDUE_FLG")
    private String OVERDUEFLG;
    @SerializedName("DIFF_FLG")
    private String DIFFFLG;
    @SerializedName("TRANSFER_ABLE_FLG")
    private String TRANSFERABLEFLG;
    @SerializedName("SEND_OUT_FLG")
    private String SENDOUTFLG;
    private String RULE;
    private String USE_WAY_FLG;//加息劵的判断规则

    private String TRANSFER_DATE;

    public String getTRANSFER_DATE() {
        return TRANSFER_DATE;
    }

    public void setTRANSFER_DATE(String TRANSFER_DATE) {
        this.TRANSFER_DATE = TRANSFER_DATE;
    }

    public String getUSE_WAY_FLG() {
        return USE_WAY_FLG;
    }

    public void setUSE_WAY_FLG(String USE_WAY_FLG) {
        this.USE_WAY_FLG = USE_WAY_FLG;
    }

    public String getRULE() {
        return RULE;
    }

    public void setRULE(String RULE) {
        this.RULE = RULE;
    }

    public String getTYPE() {
        return TYPE;
    }

    public void setTYPE(String TYPE) {
        this.TYPE = TYPE;
    }

    public String getRATECOUPONID() {
        return RATECOUPONID;
    }

    public void setRATECOUPONID(String RATECOUPONID) {
        this.RATECOUPONID = RATECOUPONID;
    }

    public String getRATECOUPONPOSITION() {
        return RATECOUPONPOSITION;
    }

    public void setRATECOUPONPOSITION(String RATECOUPONPOSITION) {
        this.RATECOUPONPOSITION = RATECOUPONPOSITION;
    }

    public String getCOUPONORIGINAVENUE() {
        return COUPONORIGINAVENUE;
    }

    public void setCOUPONORIGINAVENUE(String COUPONORIGINAVENUE) {
        this.COUPONORIGINAVENUE = COUPONORIGINAVENUE;
    }

    public String getINSDATE() {
        return INSDATE;
    }

    public void setINSDATE(String INSDATE) {
        this.INSDATE = INSDATE;
    }

    public String getMINIMUMTENDERAMOUNT() {
        return MINIMUMTENDERAMOUNT;
    }

    public void setMINIMUMTENDERAMOUNT(String MINIMUMTENDERAMOUNT) {
        this.MINIMUMTENDERAMOUNT = MINIMUMTENDERAMOUNT;
    }

    public String getMINIMUMTENDERAMOUNTUNIT() {
        return MINIMUMTENDERAMOUNTUNIT;
    }

    public void setMINIMUMTENDERAMOUNTUNIT(String MINIMUMTENDERAMOUNTUNIT) {
        this.MINIMUMTENDERAMOUNTUNIT = MINIMUMTENDERAMOUNTUNIT;
    }

    public String getSTARTDATE() {
        return STARTDATE;
    }

    public void setSTARTDATE(String STARTDATE) {
        this.STARTDATE = STARTDATE;
    }

    public String getENDDATE() {
        return ENDDATE;
    }

    public void setENDDATE(String ENDDATE) {
        this.ENDDATE = ENDDATE;
    }

    public String getEFFECTIVEDATE() {
        return EFFECTIVEDATE;
    }

    public void setEFFECTIVEDATE(String EFFECTIVEDATE) {
        this.EFFECTIVEDATE = EFFECTIVEDATE;
    }

    public String getUSEDFLG() {
        return USEDFLG;
    }

    public void setUSEDFLG(String USEDFLG) {
        this.USEDFLG = USEDFLG;
    }

    public String getOVERDUEFLG() {
        return OVERDUEFLG;
    }

    public void setOVERDUEFLG(String OVERDUEFLG) {
        this.OVERDUEFLG = OVERDUEFLG;
    }

    public String getDIFFFLG() {
        return DIFFFLG;
    }

    public void setDIFFFLG(String DIFFFLG) {
        this.DIFFFLG = DIFFFLG;
    }

    public String getTRANSFERABLEFLG() {
        return TRANSFERABLEFLG;
    }

    public void setTRANSFERABLEFLG(String TRANSFERABLEFLG) {
        this.TRANSFERABLEFLG = TRANSFERABLEFLG;
    }

    public String getSENDOUTFLG() {
        return SENDOUTFLG;
    }

    public void setSENDOUTFLG(String SENDOUTFLG) {
        this.SENDOUTFLG = SENDOUTFLG;
    }
}
