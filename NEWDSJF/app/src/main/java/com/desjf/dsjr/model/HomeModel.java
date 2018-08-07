package com.desjf.dsjr.model;

import java.util.List;

/**
 * Created by Administrator on 2017/7/24 0024.
 */

public class HomeModel {
    private String NOVICE_GUIDE_URL;
    private String ABOUT_US_URL;
    private String READ_STATUS;
    private String FINANCE_STATUS;

    public String getFINANCE_STATUS() {
        return FINANCE_STATUS;
    }

    public void setFINANCE_STATUS(String FINANCE_STATUS) {
        this.FINANCE_STATUS = FINANCE_STATUS;
    }

    public String getNOVICE_GUIDE_URL() {
        return NOVICE_GUIDE_URL;
    }

    public void setNOVICE_GUIDE_URL(String NOVICE_GUIDE_URL) {
        this.NOVICE_GUIDE_URL = NOVICE_GUIDE_URL;
    }

    public String getABOUT_US_URL() {
        return ABOUT_US_URL;
    }

    public void setABOUT_US_URL(String ABOUT_US_URL) {
        this.ABOUT_US_URL = ABOUT_US_URL;
    }

    public String getREAD_STATUS() {
        return READ_STATUS;
    }

    public void setREAD_STATUS(String READ_STATUS) {
        this.READ_STATUS = READ_STATUS;
    }

    //理财计划
    private List<BORROWLISTBean> BORROW_LIST;

    public List<BORROWLISTBean> getBORROW_LIST() {
        return BORROW_LIST;
    }

    public void setBORROW_LIST(List<BORROWLISTBean> BORROW_LIST) {
        this.BORROW_LIST = BORROW_LIST;
    }

    //散标列表
    private List<SCATTEREDLISTBean> SCATTERED_LIST;

    //新手标
    private List<NEWFINANCELISTBean> NEW_FINANCE_LIST;

    public List<NEWFINANCELISTBean> getNEW_FINANCE_LIST() {
        return NEW_FINANCE_LIST;
    }

    public void setNEW_FINANCE_LIST(List<NEWFINANCELISTBean> NEW_FINANCE_LIST) {
        this.NEW_FINANCE_LIST = NEW_FINANCE_LIST;
    }

    public List<SCATTEREDLISTBean> getSCATTERED_LIST() {
        return SCATTERED_LIST;
    }

    public void setSCATTERED_LIST(List<SCATTEREDLISTBean> SCATTERED_LIST) {
        this.SCATTERED_LIST = SCATTERED_LIST;
    }

    //活期列表
    private List<CURRENTLISTBean> CURRENT_LIST;

    public List<CURRENTLISTBean> getCURRENT_LIST() {
        return CURRENT_LIST;
    }

    public void setCURRENT_LIST(List<CURRENTLISTBean> CURRENT_LIST) {
        this.CURRENT_LIST = CURRENT_LIST;
    }

    //广告条数据
    private List<ADVERTISINGLISTBean> ADVERTISING_LIST;

    public List<ADVERTISINGLISTBean> getADVERTISING_LIST() {
        return ADVERTISING_LIST;
    }

    public void setADVERTISING_LIST(List<ADVERTISINGLISTBean> ADVERTISING_LIST) {
        this.ADVERTISING_LIST = ADVERTISING_LIST;
    }
    //活期列表集合
    public static class CURRENTLISTBean{
        private String PRODUCTS_TITLE;
        private String INTEREST_RATE_TYPE;
        private String MILLION_INCOME;
        private String BORROW_STATUS;
        private String BORROW_TIME_STATUS;
        private String LIST_TYPE;

        public String getLIST_TYPE() {
            return LIST_TYPE;
        }

        public void setLIST_TYPE(String LIST_TYPE) {
            this.LIST_TYPE = LIST_TYPE;
        }
        public String getPRODUCTS_TITLE() {
            return PRODUCTS_TITLE;
        }

        public void setPRODUCTS_TITLE(String PRODUCTS_TITLE) {
            this.PRODUCTS_TITLE = PRODUCTS_TITLE;
        }

        public String getINTEREST_RATE_TYPE() {
            return INTEREST_RATE_TYPE;
        }

        public void setINTEREST_RATE_TYPE(String INTEREST_RATE_TYPE) {
            this.INTEREST_RATE_TYPE = INTEREST_RATE_TYPE;
        }

        public String getMILLION_INCOME() {
            return MILLION_INCOME;
        }

        public void setMILLION_INCOME(String MILLION_INCOME) {
            this.MILLION_INCOME = MILLION_INCOME;
        }

        public String getBORROW_STATUS() {
            return BORROW_STATUS;
        }

        public void setBORROW_STATUS(String BORROW_STATUS) {
            this.BORROW_STATUS = BORROW_STATUS;
        }

        public String getBORROW_TIME_STATUS() {
            return BORROW_TIME_STATUS;
        }

        public void setBORROW_TIME_STATUS(String BORROW_TIME_STATUS) {
            this.BORROW_TIME_STATUS = BORROW_TIME_STATUS;
        }
    }
    //散标列表
    public static class SCATTEREDLISTBean{
        private String NOVICE_FLG;
        private String PRODUCTS_TITLE;
        private String OID_PLATFORM_PRODUCTS_ID;
        private String FINANCE_INTEREST_RATE1;
        private String FINANCE_PERIOD;
        private String INTEREST_RATE_TYPE;
        private String PRODUCTS_STATUS;
        private String COM_FLG;
        private String FINANCE_AMOUNT_SCALE;
        private String TENDER_AWARD_FLG;
        private String TENDER_AWARD_SCALE;
        private String FINANCE_AMOUNT;
        private String FINANCE_AMOUNT_WAIT;
        private String FINANCE_START_DATE;
        private String FINANCE_END_DATE;
        private String SYSTEM_DATE;
        private String FULL_TIP;//满标人信息
        private  String FULL_AWARD;//满标人奖励金额
        private  String ADVERTISE_WORD;//广告位文字显示
        private  String addRate;//加息

        public String getAddRate() {
            return addRate == null ? "" : addRate;
        }

        public void setAddRate(String addRate) {
            this.addRate = addRate;
        }

        public String getADVERTISE_WORD() {
            return ADVERTISE_WORD;
        }

        public void setADVERTISE_WORD(String ADVERTISE_WORD) {
            this.ADVERTISE_WORD = ADVERTISE_WORD;
        }
        public String getFULL_TIP() {
            return FULL_TIP;
        }

        public void setFULL_TIP(String FULL_TIP) {
            this.FULL_TIP = FULL_TIP;
        }

        public String getFULL_AWARD() {
            return FULL_AWARD;
        }

        public void setFULL_AWARD(String FULL_AWARD) {
            this.FULL_AWARD = FULL_AWARD;
        }

        public String getNOVICE_FLG() {
            return NOVICE_FLG;
        }

        public void setNOVICE_FLG(String NOVICE_FLG) {
            this.NOVICE_FLG = NOVICE_FLG;
        }

        public String getPRODUCTS_TITLE() {
            return PRODUCTS_TITLE;
        }

        public void setPRODUCTS_TITLE(String PRODUCTS_TITLE) {
            this.PRODUCTS_TITLE = PRODUCTS_TITLE;
        }

        public String getOID_PLATFORM_PRODUCTS_ID() {
            return OID_PLATFORM_PRODUCTS_ID;
        }

        public void setOID_PLATFORM_PRODUCTS_ID(String OID_PLATFORM_PRODUCTS_ID) {
            this.OID_PLATFORM_PRODUCTS_ID = OID_PLATFORM_PRODUCTS_ID;
        }

        public String getFINANCE_INTEREST_RATE1() {
            return FINANCE_INTEREST_RATE1;
        }

        public void setFINANCE_INTEREST_RATE1(String FINANCE_INTEREST_RATE1) {
            this.FINANCE_INTEREST_RATE1 = FINANCE_INTEREST_RATE1;
        }

        public String getFINANCE_PERIOD() {
            return FINANCE_PERIOD;
        }

        public void setFINANCE_PERIOD(String FINANCE_PERIOD) {
            this.FINANCE_PERIOD = FINANCE_PERIOD;
        }

        public String getINTEREST_RATE_TYPE() {
            return INTEREST_RATE_TYPE;
        }

        public void setINTEREST_RATE_TYPE(String INTEREST_RATE_TYPE) {
            this.INTEREST_RATE_TYPE = INTEREST_RATE_TYPE;
        }

        public String getPRODUCTS_STATUS() {
            return PRODUCTS_STATUS;
        }

        public void setPRODUCTS_STATUS(String PRODUCTS_STATUS) {
            this.PRODUCTS_STATUS = PRODUCTS_STATUS;
        }

        public String getCOM_FLG() {
            return COM_FLG;
        }

        public void setCOM_FLG(String COM_FLG) {
            this.COM_FLG = COM_FLG;
        }

        public String getFINANCE_AMOUNT_SCALE() {
            return FINANCE_AMOUNT_SCALE;
        }

        public void setFINANCE_AMOUNT_SCALE(String FINANCE_AMOUNT_SCALE) {
            this.FINANCE_AMOUNT_SCALE = FINANCE_AMOUNT_SCALE;
        }

        public String getTENDER_AWARD_FLG() {
            return TENDER_AWARD_FLG;
        }

        public void setTENDER_AWARD_FLG(String TENDER_AWARD_FLG) {
            this.TENDER_AWARD_FLG = TENDER_AWARD_FLG;
        }

        public String getTENDER_AWARD_SCALE() {
            return TENDER_AWARD_SCALE;
        }

        public void setTENDER_AWARD_SCALE(String TENDER_AWARD_SCALE) {
            this.TENDER_AWARD_SCALE = TENDER_AWARD_SCALE;
        }

        public String getFINANCE_AMOUNT() {
            return FINANCE_AMOUNT;
        }

        public void setFINANCE_AMOUNT(String FINANCE_AMOUNT) {
            this.FINANCE_AMOUNT = FINANCE_AMOUNT;
        }

        public String getFINANCE_AMOUNT_WAIT() {
            return FINANCE_AMOUNT_WAIT;
        }

        public void setFINANCE_AMOUNT_WAIT(String FINANCE_AMOUNT_WAIT) {
            this.FINANCE_AMOUNT_WAIT = FINANCE_AMOUNT_WAIT;
        }

        public String getFINANCE_START_DATE() {
            return FINANCE_START_DATE;
        }

        public void setFINANCE_START_DATE(String FINANCE_START_DATE) {
            this.FINANCE_START_DATE = FINANCE_START_DATE;
        }

        public String getFINANCE_END_DATE() {
            return FINANCE_END_DATE;
        }

        public void setFINANCE_END_DATE(String FINANCE_END_DATE) {
            this.FINANCE_END_DATE = FINANCE_END_DATE;
        }

        public String getSYSTEM_DATE() {
            return SYSTEM_DATE;
        }

        public void setSYSTEM_DATE(String SYSTEM_DATE) {
            this.SYSTEM_DATE = SYSTEM_DATE;
        }
    }

    //新手标
    public static class NEWFINANCELISTBean{
        private String NOVICE_FLG;
        private String PRODUCTS_TITLE;
        private String OID_PLATFORM_PRODUCTS_ID;
        private String FINANCE_INTEREST_RATE1;
        private String FINANCE_PERIOD;
        private String INTEREST_RATE_TYPE;
        private String PRODUCTS_STATUS;
        private String COM_FLG;
        private String FINANCE_AMOUNT_SCALE;
        private String TENDER_AWARD_FLG;
        private String TENDER_AWARD_SCALE;
        private String FINANCE_AMOUNT;
        private String FINANCE_AMOUNT_WAIT;
        private String FINANCE_START_DATE;
        private String FINANCE_END_DATE;
        private String SYSTEM_DATE;
        private String FULL_TIP;//满标人信息
        private  String FULL_AWARD;//满标人奖励金额
        private  String ADVERTISE_WORD;//广告位文字显示
        private  String addRate;//加息

        public String getAddRate() {
            return addRate == null ? "" : addRate;
        }

        public void setAddRate(String addRate) {
            this.addRate = addRate;
        }
        public String getADVERTISE_WORD() {
            return ADVERTISE_WORD;
        }

        public void setADVERTISE_WORD(String ADVERTISE_WORD) {
            this.ADVERTISE_WORD = ADVERTISE_WORD;
        }
        public String getFULL_TIP() {
            return FULL_TIP;
        }

        public void setFULL_TIP(String FULL_TIP) {
            this.FULL_TIP = FULL_TIP;
        }

        public String getFULL_AWARD() {
            return FULL_AWARD;
        }

        public void setFULL_AWARD(String FULL_AWARD) {
            this.FULL_AWARD = FULL_AWARD;
        }
        public String getNOVICE_FLG() {
            return NOVICE_FLG;
        }

        public void setNOVICE_FLG(String NOVICE_FLG) {
            this.NOVICE_FLG = NOVICE_FLG;
        }

        public String getPRODUCTS_TITLE() {
            return PRODUCTS_TITLE;
        }

        public void setPRODUCTS_TITLE(String PRODUCTS_TITLE) {
            this.PRODUCTS_TITLE = PRODUCTS_TITLE;
        }

        public String getOID_PLATFORM_PRODUCTS_ID() {
            return OID_PLATFORM_PRODUCTS_ID;
        }

        public void setOID_PLATFORM_PRODUCTS_ID(String OID_PLATFORM_PRODUCTS_ID) {
            this.OID_PLATFORM_PRODUCTS_ID = OID_PLATFORM_PRODUCTS_ID;
        }

        public String getFINANCE_INTEREST_RATE1() {
            return FINANCE_INTEREST_RATE1;
        }

        public void setFINANCE_INTEREST_RATE1(String FINANCE_INTEREST_RATE1) {
            this.FINANCE_INTEREST_RATE1 = FINANCE_INTEREST_RATE1;
        }

        public String getFINANCE_PERIOD() {
            return FINANCE_PERIOD;
        }

        public void setFINANCE_PERIOD(String FINANCE_PERIOD) {
            this.FINANCE_PERIOD = FINANCE_PERIOD;
        }

        public String getINTEREST_RATE_TYPE() {
            return INTEREST_RATE_TYPE;
        }

        public void setINTEREST_RATE_TYPE(String INTEREST_RATE_TYPE) {
            this.INTEREST_RATE_TYPE = INTEREST_RATE_TYPE;
        }

        public String getPRODUCTS_STATUS() {
            return PRODUCTS_STATUS;
        }

        public void setPRODUCTS_STATUS(String PRODUCTS_STATUS) {
            this.PRODUCTS_STATUS = PRODUCTS_STATUS;
        }

        public String getCOM_FLG() {
            return COM_FLG;
        }

        public void setCOM_FLG(String COM_FLG) {
            this.COM_FLG = COM_FLG;
        }

        public String getFINANCE_AMOUNT_SCALE() {
            return FINANCE_AMOUNT_SCALE;
        }

        public void setFINANCE_AMOUNT_SCALE(String FINANCE_AMOUNT_SCALE) {
            this.FINANCE_AMOUNT_SCALE = FINANCE_AMOUNT_SCALE;
        }

        public String getTENDER_AWARD_FLG() {
            return TENDER_AWARD_FLG;
        }

        public void setTENDER_AWARD_FLG(String TENDER_AWARD_FLG) {
            this.TENDER_AWARD_FLG = TENDER_AWARD_FLG;
        }

        public String getTENDER_AWARD_SCALE() {
            return TENDER_AWARD_SCALE;
        }

        public void setTENDER_AWARD_SCALE(String TENDER_AWARD_SCALE) {
            this.TENDER_AWARD_SCALE = TENDER_AWARD_SCALE;
        }

        public String getFINANCE_AMOUNT() {
            return FINANCE_AMOUNT;
        }

        public void setFINANCE_AMOUNT(String FINANCE_AMOUNT) {
            this.FINANCE_AMOUNT = FINANCE_AMOUNT;
        }

        public String getFINANCE_AMOUNT_WAIT() {
            return FINANCE_AMOUNT_WAIT;
        }

        public void setFINANCE_AMOUNT_WAIT(String FINANCE_AMOUNT_WAIT) {
            this.FINANCE_AMOUNT_WAIT = FINANCE_AMOUNT_WAIT;
        }

        public String getFINANCE_START_DATE() {
            return FINANCE_START_DATE;
        }

        public void setFINANCE_START_DATE(String FINANCE_START_DATE) {
            this.FINANCE_START_DATE = FINANCE_START_DATE;
        }

        public String getFINANCE_END_DATE() {
            return FINANCE_END_DATE;
        }

        public void setFINANCE_END_DATE(String FINANCE_END_DATE) {
            this.FINANCE_END_DATE = FINANCE_END_DATE;
        }

        public String getSYSTEM_DATE() {
            return SYSTEM_DATE;
        }

        public void setSYSTEM_DATE(String SYSTEM_DATE) {
            this.SYSTEM_DATE = SYSTEM_DATE;
        }
    }

    //理财计划列表
    public static  class BORROWLISTBean{
        private String BORROW_TITLE;
        private String BORROW_RATE;
        private String BORROW_PROID;
        private String BORROW_PROID_TYPE;
        private String BORROW_STATUS;
        private String BORROW_TIME_STATUS;
        private String LIST_TYPE;

        public String getLIST_TYPE() {
            return LIST_TYPE;
        }

        public void setLIST_TYPE(String LIST_TYPE) {
            this.LIST_TYPE = LIST_TYPE;
        }
        public String getBORROW_TITLE() {
            return BORROW_TITLE;
        }

        public void setBORROW_TITLE(String BORROW_TITLE) {
            this.BORROW_TITLE = BORROW_TITLE;
        }

        public String getBORROW_RATE() {
            return BORROW_RATE;
        }

        public void setBORROW_RATE(String BORROW_RATE) {
            this.BORROW_RATE = BORROW_RATE;
        }

        public String getBORROW_PROID() {
            return BORROW_PROID;
        }

        public void setBORROW_PROID(String BORROW_PROID) {
            this.BORROW_PROID = BORROW_PROID;
        }

        public String getBORROW_PROID_TYPE() {
            return BORROW_PROID_TYPE;
        }

        public void setBORROW_PROID_TYPE(String BORROW_PROID_TYPE) {
            this.BORROW_PROID_TYPE = BORROW_PROID_TYPE;
        }

        public String getBORROW_STATUS() {
            return BORROW_STATUS;
        }

        public void setBORROW_STATUS(String BORROW_STATUS) {
            this.BORROW_STATUS = BORROW_STATUS;
        }

        public String getBORROW_TIME_STATUS() {
            return BORROW_TIME_STATUS;
        }

        public void setBORROW_TIME_STATUS(String BORROW_TIME_STATUS) {
            this.BORROW_TIME_STATUS = BORROW_TIME_STATUS;
        }
    }

    //广告条集合
    public static class ADVERTISINGLISTBean{
        private String IMG_PATH;
        private String LINK_URL;

        public String getIMG_PATH() {
            return IMG_PATH;
        }

        public void setIMG_PATH(String IMG_PATH) {
            this.IMG_PATH = IMG_PATH;
        }

        public String getLINK_URL() {
            return LINK_URL;
        }

        public void setLINK_URL(String LINK_URL) {
            this.LINK_URL = LINK_URL;
        }
    }
}
