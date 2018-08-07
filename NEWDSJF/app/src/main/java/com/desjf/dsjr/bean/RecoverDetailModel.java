package com.desjf.dsjr.bean;

import java.util.List;

/**
 * @author Ls
 * @Description:
 * @Package: com.batong.wealth.model
 * @data 2017/3/27 12:12
 */

public class RecoverDetailModel {


    /**
     * DATA_FLG : 0
     * DATE : 2017-04-04
     */

    private List<DATABean> DATA;

    public List<DATABean> getDATA() {
        return DATA;
    }

    public void setDATA(List<DATABean> DATA) {
        this.DATA = DATA;
    }

    public static class DATABean {
        private String DATA_FLG;
        private String DATE;
        private String PERIODS;
        private String CAPITAL;
        private String INTEREST;
        private String TITLE;
        private String OID_TENDER_ID;
        private String TENDER_FROM;
        private String RECOVER_TYPE;
        private String OVERDUE_FLG;

        public String getOVERDUE_FLG() {
            return OVERDUE_FLG;
        }

        public void setOVERDUE_FLG(String OVERDUE_FLG) {
            this.OVERDUE_FLG = OVERDUE_FLG;
        }

        public String getRECOVER_TYPE() {
            return RECOVER_TYPE;
        }

        public void setRECOVER_TYPE(String RECOVER_TYPE) {
            this.RECOVER_TYPE = RECOVER_TYPE;
        }

        public String getOID_TENDER_ID() {
            return OID_TENDER_ID;
        }

        public void setOID_TENDER_ID(String OID_TENDER_ID) {
            this.OID_TENDER_ID = OID_TENDER_ID;
        }

        public String getTENDER_FROM() {
            return TENDER_FROM;
        }

        public void setTENDER_FROM(String TENDER_FROM) {
            this.TENDER_FROM = TENDER_FROM;
        }

        public String getPERIODS() {
            return PERIODS;
        }

        public void setPERIODS(String PERIODS) {
            this.PERIODS = PERIODS;
        }

        public String getCAPITAL() {
            return CAPITAL;
        }

        public void setCAPITAL(String CAPITAL) {
            this.CAPITAL = CAPITAL;
        }

        public String getINTEREST() {
            return INTEREST;
        }

        public void setINTEREST(String INTEREST) {
            this.INTEREST = INTEREST;
        }

        public String getTITLE() {
            return TITLE;
        }

        public void setTITLE(String TITLE) {
            this.TITLE = TITLE;
        }

        public String getDATA_FLG() {
            return DATA_FLG;
        }

        public void setDATA_FLG(String DATA_FLG) {
            this.DATA_FLG = DATA_FLG;
        }

        public String getDATE() {
            return DATE;
        }

        public void setDATE(String DATE) {
            this.DATE = DATE;
        }
    }

}
