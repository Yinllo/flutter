package com.desjf.dsjr.model;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;


/**
 * Created by Administrator on 2017/7/25 0025.
 */

public class AccountModel implements Serializable{
    /**
     * BALANCE : 2,695,540.00
     * FROZE_AMOUNT : 300,200.00
     * AWAIT : 4,363.09
     * TOTAL_AMOUNT : 3000103.09
     * PHONE_NUMBER : 13354268902
     * PHONE_NUMBER_CONCEAL : 133****8902
     * PWD_SAME_FLG : 0
     * USER_NAME : 测试
     * USER_NAME_CONCEAL : 测**
     * ID_CARD : 510922197506228379
     * ID_CARD_CONCEAL : 5******9
     * ID_CARD_VERIFY_FLG : 1
     * EMAIL_VERIFY_FLG : 0
     * BANK_REAL_NAME : 0310
     * BRANCH : 测试的话
     * BANK_FLG : 1
     * BANK_CARD_NO : 6229569889955544
     * BANK_CARD_NO_CONCEAL : 6229********5544
     * BANK_ID : 0310
     * PROVINCE_ID : 1
     * PROVINCE_NAME : 北京市
     * CITY_ID : 37
     * CITY_NAME : 东城区
     * BANK_IMG : http://192.168.1.103:8082/E146_WebService/images/bankImages/0310.png
     * AUTO_FLG : 0
     * AUTO_BID_AUTH_STATUS : 1
     * USER_HEAD_IMG : http://192.168.1.103:8082/E146_WebService/images/toxiang.png
     * MIN_ACCOUNT_ONE : 0.01
     * CASH_WITHDRAWAL_AMOUNT : 2695540.00
     * MESSAGE_EXIST_FLG : 1
     * MESSAGE_TOTAL_NO : 31
     * USER_INTEGRAL : 533.00
     * POINT_INFO_LIST : [{"INS_DATE":"17/06/27 15:07","POINT_DESCRIPTION":"投资回报积分","POINT":"+100.00"},{"INS_DATE":"17/06/27 15:07","POINT_DESCRIPTION":"投资回报积分","POINT":"+83.00"},{"INS_DATE":"17/06/27 15:05","POINT_DESCRIPTION":"投资回报积分","POINT":"+100.00"},{"INS_DATE":"17/06/27 15:03","POINT_DESCRIPTION":"投资回报积分","POINT":"+250.00"}]
     * IS_VIP : 0
     */

    @SerializedName("BALANCE")
    private String BALANCE;
    @SerializedName("FROZE_AMOUNT")
    private String FROZEAMOUNT;
    @SerializedName("AWAIT")
    private String AWAIT;
    @SerializedName("TOTAL_AMOUNT")
    private String TOTALAMOUNT;
    @SerializedName("PHONE_NUMBER")
    private String PHONENUMBER;
    @SerializedName("PHONE_NUMBER_CONCEAL")
    private String PHONENUMBERCONCEAL;
    @SerializedName("PWD_SAME_FLG")
    private String PWDSAMEFLG;
    @SerializedName("USER_NAME")
    private String USERNAME;
    @SerializedName("USER_NAME_CONCEAL")
    private String USERNAMECONCEAL;
    @SerializedName("ID_CARD")
    private String IDCARD;
    @SerializedName("ID_CARD_CONCEAL")
    private String IDCARDCONCEAL;
    @SerializedName("ID_CARD_VERIFY_FLG")
    private String IDCARDVERIFYFLG;
    @SerializedName("EMAIL_VERIFY_FLG")
    private String EMAILVERIFYFLG;
    @SerializedName("BANK_REAL_NAME")
    private String BANKREALNAME;
    @SerializedName("BRANCH")
    private String BRANCH;
    @SerializedName("BANK_FLG")
    private String BANKFLG;
    @SerializedName("BANK_CARD_NO")
    private String BANKCARDNO;
    @SerializedName("BANK_CARD_NO_CONCEAL")
    private String BANKCARDNOCONCEAL;
    @SerializedName("BANK_ID")
    private String BANKID;
    @SerializedName("PROVINCE_ID")
    private String PROVINCEID;
    @SerializedName("PROVINCE_NAME")
    private String PROVINCENAME;
    @SerializedName("CITY_ID")
    private String CITYID;
    @SerializedName("CITY_NAME")
    private String CITYNAME;
    @SerializedName("BANK_IMG")
    private String BANKIMG;
    @SerializedName("AUTO_FLG")
    private String AUTOFLG;
    @SerializedName("AUTO_BID_AUTH_STATUS")
    private String AUTOBIDAUTHSTATUS;
    @SerializedName("USER_HEAD_IMG")
    private String USERHEADIMG;
    @SerializedName("MIN_ACCOUNT_ONE")
    private String MINACCOUNTONE;
    @SerializedName("CASH_WITHDRAWAL_AMOUNT")
    private String CASHWITHDRAWALAMOUNT;
    @SerializedName("MESSAGE_EXIST_FLG")
    private String MESSAGEEXISTFLG;
    @SerializedName("MESSAGE_TOTAL_NO")
    private int MESSAGETOTALNO;
    @SerializedName("USER_INTEGRAL")
    private String USERINTEGRAL;
    @SerializedName("IS_VIP")
    private String ISVIP;
    @SerializedName("OPEN_FORE")
    private String OPENFORE;
    @SerializedName("POINT_INFO_LIST")
    private List<POINTINFOLISTBean> POINTINFOLIST;
    @SerializedName("MIN_WITHDRAW")
    private String MINWITHDRAW;

    public String getMINWITHDRAW() {
        return MINWITHDRAW;
    }

    public void setMINWITHDRAW(String MINWITHDRAW) {
        this.MINWITHDRAW = MINWITHDRAW;
    }

    public String getOPENFORE() {
        return OPENFORE;
    }

    public void setOPENFORE(String OPENFORE) {
        this.OPENFORE = OPENFORE;
    }

    public String getBALANCE() {
        return BALANCE;
    }

    public void setBALANCE(String BALANCE) {
        this.BALANCE = BALANCE;
    }

    public String getFROZEAMOUNT() {
        return FROZEAMOUNT;
    }

    public void setFROZEAMOUNT(String FROZEAMOUNT) {
        this.FROZEAMOUNT = FROZEAMOUNT;
    }

    public String getAWAIT() {
        return AWAIT;
    }

    public void setAWAIT(String AWAIT) {
        this.AWAIT = AWAIT;
    }

    public String getTOTALAMOUNT() {
        return TOTALAMOUNT;
    }

    public void setTOTALAMOUNT(String TOTALAMOUNT) {
        this.TOTALAMOUNT = TOTALAMOUNT;
    }

    public String getPHONENUMBER() {
        return PHONENUMBER;
    }

    public void setPHONENUMBER(String PHONENUMBER) {
        this.PHONENUMBER = PHONENUMBER;
    }

    public String getPHONENUMBERCONCEAL() {
        return PHONENUMBERCONCEAL;
    }

    public void setPHONENUMBERCONCEAL(String PHONENUMBERCONCEAL) {
        this.PHONENUMBERCONCEAL = PHONENUMBERCONCEAL;
    }

    public String getPWDSAMEFLG() {
        return PWDSAMEFLG;
    }

    public void setPWDSAMEFLG(String PWDSAMEFLG) {
        this.PWDSAMEFLG = PWDSAMEFLG;
    }

    public String getUSERNAME() {
        return USERNAME;
    }

    public void setUSERNAME(String USERNAME) {
        this.USERNAME = USERNAME;
    }

    public String getUSERNAMECONCEAL() {
        return USERNAMECONCEAL;
    }

    public void setUSERNAMECONCEAL(String USERNAMECONCEAL) {
        this.USERNAMECONCEAL = USERNAMECONCEAL;
    }

    public String getIDCARD() {
        return IDCARD;
    }

    public void setIDCARD(String IDCARD) {
        this.IDCARD = IDCARD;
    }

    public String getIDCARDCONCEAL() {
        return IDCARDCONCEAL;
    }

    public void setIDCARDCONCEAL(String IDCARDCONCEAL) {
        this.IDCARDCONCEAL = IDCARDCONCEAL;
    }

    public String getIDCARDVERIFYFLG() {
        return IDCARDVERIFYFLG;
    }

    public void setIDCARDVERIFYFLG(String IDCARDVERIFYFLG) {
        this.IDCARDVERIFYFLG = IDCARDVERIFYFLG;
    }

    public String getEMAILVERIFYFLG() {
        return EMAILVERIFYFLG;
    }

    public void setEMAILVERIFYFLG(String EMAILVERIFYFLG) {
        this.EMAILVERIFYFLG = EMAILVERIFYFLG;
    }

    public String getBANKREALNAME() {
        return BANKREALNAME;
    }

    public void setBANKREALNAME(String BANKREALNAME) {
        this.BANKREALNAME = BANKREALNAME;
    }

    public String getBRANCH() {
        return BRANCH;
    }

    public void setBRANCH(String BRANCH) {
        this.BRANCH = BRANCH;
    }

    public String getBANKFLG() {
        return BANKFLG;
    }

    public void setBANKFLG(String BANKFLG) {
        this.BANKFLG = BANKFLG;
    }

    public String getBANKCARDNO() {
        return BANKCARDNO;
    }

    public void setBANKCARDNO(String BANKCARDNO) {
        this.BANKCARDNO = BANKCARDNO;
    }

    public String getBANKCARDNOCONCEAL() {
        return BANKCARDNOCONCEAL;
    }

    public void setBANKCARDNOCONCEAL(String BANKCARDNOCONCEAL) {
        this.BANKCARDNOCONCEAL = BANKCARDNOCONCEAL;
    }

    public String getBANKID() {
        return BANKID;
    }

    public void setBANKID(String BANKID) {
        this.BANKID = BANKID;
    }

    public String getPROVINCEID() {
        return PROVINCEID;
    }

    public void setPROVINCEID(String PROVINCEID) {
        this.PROVINCEID = PROVINCEID;
    }

    public String getPROVINCENAME() {
        return PROVINCENAME;
    }

    public void setPROVINCENAME(String PROVINCENAME) {
        this.PROVINCENAME = PROVINCENAME;
    }

    public String getCITYID() {
        return CITYID;
    }

    public void setCITYID(String CITYID) {
        this.CITYID = CITYID;
    }

    public String getCITYNAME() {
        return CITYNAME;
    }

    public void setCITYNAME(String CITYNAME) {
        this.CITYNAME = CITYNAME;
    }

    public String getBANKIMG() {
        return BANKIMG;
    }

    public void setBANKIMG(String BANKIMG) {
        this.BANKIMG = BANKIMG;
    }

    public String getAUTOFLG() {
        return AUTOFLG;
    }

    public void setAUTOFLG(String AUTOFLG) {
        this.AUTOFLG = AUTOFLG;
    }

    public String getAUTOBIDAUTHSTATUS() {
        return AUTOBIDAUTHSTATUS;
    }

    public void setAUTOBIDAUTHSTATUS(String AUTOBIDAUTHSTATUS) {
        this.AUTOBIDAUTHSTATUS = AUTOBIDAUTHSTATUS;
    }

    public String getUSERHEADIMG() {
        return USERHEADIMG;
    }

    public void setUSERHEADIMG(String USERHEADIMG) {
        this.USERHEADIMG = USERHEADIMG;
    }

    public String getMINACCOUNTONE() {
        return MINACCOUNTONE;
    }

    public void setMINACCOUNTONE(String MINACCOUNTONE) {
        this.MINACCOUNTONE = MINACCOUNTONE;
    }

    public String getCASHWITHDRAWALAMOUNT() {
        return CASHWITHDRAWALAMOUNT;
    }

    public void setCASHWITHDRAWALAMOUNT(String CASHWITHDRAWALAMOUNT) {
        this.CASHWITHDRAWALAMOUNT = CASHWITHDRAWALAMOUNT;
    }

    public String getMESSAGEEXISTFLG() {
        return MESSAGEEXISTFLG;
    }

    public void setMESSAGEEXISTFLG(String MESSAGEEXISTFLG) {
        this.MESSAGEEXISTFLG = MESSAGEEXISTFLG;
    }

    public int getMESSAGETOTALNO() {
        return MESSAGETOTALNO;
    }

    public void setMESSAGETOTALNO(int MESSAGETOTALNO) {
        this.MESSAGETOTALNO = MESSAGETOTALNO;
    }

    public String getUSERINTEGRAL() {
        return USERINTEGRAL;
    }

    public void setUSERINTEGRAL(String USERINTEGRAL) {
        this.USERINTEGRAL = USERINTEGRAL;
    }

    public String getISVIP() {
        return ISVIP;
    }

    public void setISVIP(String ISVIP) {
        this.ISVIP = ISVIP;
    }

    public List<POINTINFOLISTBean> getPOINTINFOLIST() {
        return POINTINFOLIST;
    }

    public void setPOINTINFOLIST(List<POINTINFOLISTBean> POINTINFOLIST) {
        this.POINTINFOLIST = POINTINFOLIST;
    }

    public static class POINTINFOLISTBean implements Serializable{
        /**
         * INS_DATE : 17/06/27 15:07
         * POINT_DESCRIPTION : 投资回报积分
         * POINT : +100.00
         */

        @SerializedName("INS_DATE")
        private String INSDATE;
        @SerializedName("POINT_DESCRIPTION")
        private String POINTDESCRIPTION;
        @SerializedName("POINT")
        private String POINT;

        public String getINSDATE() {
            return INSDATE;
        }

        public void setINSDATE(String INSDATE) {
            this.INSDATE = INSDATE;
        }

        public String getPOINTDESCRIPTION() {
            return POINTDESCRIPTION;
        }

        public void setPOINTDESCRIPTION(String POINTDESCRIPTION) {
            this.POINTDESCRIPTION = POINTDESCRIPTION;
        }

        public String getPOINT() {
            return POINT;
        }

        public void setPOINT(String POINT) {
            this.POINT = POINT;
        }
    }

}
