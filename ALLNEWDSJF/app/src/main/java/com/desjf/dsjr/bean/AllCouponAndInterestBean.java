package com.desjf.dsjr.bean;

/**
 * 红包和加息券实体类
 */

//所有可用红包和加息券
public class AllCouponAndInterestBean {
    private String TYPE; //类型  加息券或者红包
    private String NUMBER;   //红包金额/加息券加息利率
    private String MINIMUM_TENDER_AMOUNT; //投资金额
    private String PERIOD; //标的期限
    private String FROM; //奖励来自
    private String END_DATE; //有效期
    private String RED_PACKET_ID;//红包ID   寻找最优标时使用
    private String RED_PACKET_LOG_ID;//红包记录表的ID   用于立即投资时传参
    private String RATE_COUPON_SEND_ID;//加息券ID
    private String OID_PLATFORM_PRODUCTS_ID;//标的ID
    private String TOTAL_COUPON_NUM;//红包和加息券总个数
    private String WORD;//当前是红包还是加息券

    public String getWORD() {
        return WORD;
    }

    public void setWORD(String WORD) {
        this.WORD = WORD;
    }

    public String getRED_PACKET_LOG_ID() {
        return RED_PACKET_LOG_ID;
    }

    public void setRED_PACKET_LOG_ID(String RED_PACKET_LOG_ID) {
        this.RED_PACKET_LOG_ID = RED_PACKET_LOG_ID;
    }

    public String getOID_PLATFORM_PRODUCTS_ID() {
        return OID_PLATFORM_PRODUCTS_ID;
    }

    public void setOID_PLATFORM_PRODUCTS_ID(String OID_PLATFORM_PRODUCTS_ID) {
        this.OID_PLATFORM_PRODUCTS_ID = OID_PLATFORM_PRODUCTS_ID;
    }

    public String getTOTAL_COUPON_NUM() {
        return TOTAL_COUPON_NUM;
    }

    public void setTOTAL_COUPON_NUM(String TOTAL_COUPON_NUM) {
        this.TOTAL_COUPON_NUM = TOTAL_COUPON_NUM;
    }

    public String getTYPE() {
        return TYPE;
    }

    public void setTYPE(String TYPE) {
        this.TYPE = TYPE;
    }

    public String getNUMBER() {
        return NUMBER;
    }

    public void setNUMBER(String NUMBER) {
        this.NUMBER = NUMBER;
    }

    public String getMINIMUM_TENDER_AMOUNT() {
        return MINIMUM_TENDER_AMOUNT;
    }

    public void setMINIMUM_TENDER_AMOUNT(String MINIMUM_TENDER_AMOUNT) {
        this.MINIMUM_TENDER_AMOUNT = MINIMUM_TENDER_AMOUNT;
    }

    public String getPERIOD() {
        return PERIOD;
    }

    public void setPERIOD(String PERIOD) {
        this.PERIOD = PERIOD;
    }

    public String getFROM() {
        return FROM;
    }

    public void setFROM(String FROM) {
        this.FROM = FROM;
    }

    public String getEND_DATE() {
        return END_DATE;
    }

    public void setEND_DATE(String END_DATE) {
        this.END_DATE = END_DATE;
    }

    public String getRED_PACKET_ID() {
        return RED_PACKET_ID;
    }

    public void setRED_PACKET_ID(String RED_PACKET_ID) {
        this.RED_PACKET_ID = RED_PACKET_ID;
    }

    public String getRATE_COUPON_SEND_ID() {
        return RATE_COUPON_SEND_ID;
    }

    public void setRATE_COUPON_SEND_ID(String RATE_COUPON_SEND_ID) {
        this.RATE_COUPON_SEND_ID = RATE_COUPON_SEND_ID;
    }
}
