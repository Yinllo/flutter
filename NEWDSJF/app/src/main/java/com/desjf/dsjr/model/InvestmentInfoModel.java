package com.desjf.dsjr.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Administrator on 2017/8/9 0009.
 */

public class InvestmentInfoModel {

    @SerializedName("PRODUCTS_DESCRIPTION_LIST")
    private List<PRODUCTSDESCRIPTIONLISTBean> PRODUCTSDESCRIPTIONLIST;
    @SerializedName("PRODUCTS_DESCRIPTION_LISTW")
    private List<PRODUCTSDESCRIPTIONLISTWBean> PRODUCTSDESCRIPTIONLISTW;
    @SerializedName("FINANCE_DESCRIPTION_LIST")
    private List<FINANCEDESCRIPTIONLISTBean> FINANCEDESCRIPTIONLIST;
    @SerializedName("FINANCE_DESCRIPTION_LISTW")
    private List<FINANCEDESCRIPTIONLISTWBean> FINANCEDESCRIPTIONLISTW;
    @SerializedName("RISK_DESCRIPTION_LIST")
    private List<RISKDESCRIPTIONLISTBean> RISKDESCRIPTIONLIST;
    @SerializedName("RISK_DESCRIPTION_LISTW")
    private List<RISKDESCRIPTIONLISTWBean> RISKDESCRIPTIONLISTW;

    public List<PRODUCTSDESCRIPTIONLISTBean> getPRODUCTSDESCRIPTIONLIST() {
        return PRODUCTSDESCRIPTIONLIST;
    }

    public void setPRODUCTSDESCRIPTIONLIST(List<PRODUCTSDESCRIPTIONLISTBean> PRODUCTSDESCRIPTIONLIST) {
        this.PRODUCTSDESCRIPTIONLIST = PRODUCTSDESCRIPTIONLIST;
    }

    public List<PRODUCTSDESCRIPTIONLISTWBean> getPRODUCTSDESCRIPTIONLISTW() {
        return PRODUCTSDESCRIPTIONLISTW;
    }

    public void setPRODUCTSDESCRIPTIONLISTW(List<PRODUCTSDESCRIPTIONLISTWBean> PRODUCTSDESCRIPTIONLISTW) {
        this.PRODUCTSDESCRIPTIONLISTW = PRODUCTSDESCRIPTIONLISTW;
    }

    public List<FINANCEDESCRIPTIONLISTBean> getFINANCEDESCRIPTIONLIST() {
        return FINANCEDESCRIPTIONLIST;
    }

    public void setFINANCEDESCRIPTIONLIST(List<FINANCEDESCRIPTIONLISTBean> FINANCEDESCRIPTIONLIST) {
        this.FINANCEDESCRIPTIONLIST = FINANCEDESCRIPTIONLIST;
    }

    public List<FINANCEDESCRIPTIONLISTWBean> getFINANCEDESCRIPTIONLISTW() {
        return FINANCEDESCRIPTIONLISTW;
    }

    public void setFINANCEDESCRIPTIONLISTW(List<FINANCEDESCRIPTIONLISTWBean> FINANCEDESCRIPTIONLISTW) {
        this.FINANCEDESCRIPTIONLISTW = FINANCEDESCRIPTIONLISTW;
    }

    public List<RISKDESCRIPTIONLISTBean> getRISKDESCRIPTIONLIST() {
        return RISKDESCRIPTIONLIST;
    }

    public void setRISKDESCRIPTIONLIST(List<RISKDESCRIPTIONLISTBean> RISKDESCRIPTIONLIST) {
        this.RISKDESCRIPTIONLIST = RISKDESCRIPTIONLIST;
    }

    public List<RISKDESCRIPTIONLISTWBean> getRISKDESCRIPTIONLISTW() {
        return RISKDESCRIPTIONLISTW;
    }

    public void setRISKDESCRIPTIONLISTW(List<RISKDESCRIPTIONLISTWBean> RISKDESCRIPTIONLISTW) {
        this.RISKDESCRIPTIONLISTW = RISKDESCRIPTIONLISTW;
    }

    public static class PRODUCTSDESCRIPTIONLISTBean {
        /**
         * IMG_TITLE :
         * IMG_URL : http://localhost:8082/upload/images/179ddd472bad49989239479dd36fcdfe/cd7fd1517e323f26c6f1b0b6b96e3b3d.JPG
         */

        @SerializedName("IMG_TITLE")
        private String IMGTITLE;
        @SerializedName("IMG_URL")
        private String IMGURL;

        public String getIMGTITLE() {
            return IMGTITLE;
        }

        public void setIMGTITLE(String IMGTITLE) {
            this.IMGTITLE = IMGTITLE;
        }

        public String getIMGURL() {
            return IMGURL;
        }

        public void setIMGURL(String IMGURL) {
            this.IMGURL = IMGURL;
        }
    }

    public static class PRODUCTSDESCRIPTIONLISTWBean {
        /**
         * DETAIL_DESCRIPTION : 产品详情<span>产品详情</span><span>产品详情</span><span>产品详情</span><span>产品详情</span><span>产品详情</span>
         */

        @SerializedName("DETAIL_DESCRIPTION")
        private String DETAILDESCRIPTION;

        public String getDETAILDESCRIPTION() {
            return DETAILDESCRIPTION;
        }

        public void setDETAILDESCRIPTION(String DETAILDESCRIPTION) {
            this.DETAILDESCRIPTION = DETAILDESCRIPTION;
        }
    }

    public static class FINANCEDESCRIPTIONLISTBean {
        /**
         * IMG_TITLE :
         * IMG_URL : http://localhost:8082/upload/images/38fc1d27ea8c4aa988bbe34cca2479ca/4c0d13d3ad6cc317017872e51d01b238.JPG
         */

        @SerializedName("IMG_TITLE")
        private String IMGTITLE;
        @SerializedName("IMG_URL")
        private String IMGURL;

        public String getIMGTITLE() {
            return IMGTITLE;
        }

        public void setIMGTITLE(String IMGTITLE) {
            this.IMGTITLE = IMGTITLE;
        }

        public String getIMGURL() {
            return IMGURL;
        }

        public void setIMGURL(String IMGURL) {
            this.IMGURL = IMGURL;
        }
    }

    public static class FINANCEDESCRIPTIONLISTWBean {
        /**
         * DETAIL_DESCRIPTION : 借款资料<span>借款资料</span><span>借款资料</span><span>借款资料</span><span>借款资料</span><span>借款资料</span>
         */

        @SerializedName("DETAIL_DESCRIPTION")
        private String DETAILDESCRIPTION;

        public String getDETAILDESCRIPTION() {
            return DETAILDESCRIPTION;
        }

        public void setDETAILDESCRIPTION(String DETAILDESCRIPTION) {
            this.DETAILDESCRIPTION = DETAILDESCRIPTION;
        }
    }

    public static class RISKDESCRIPTIONLISTBean {
        /**
         * IMG_TITLE :
         * IMG_URL : http://localhost:8082/upload/images/296ac3cdf1094333b2b2156ca9820e3f/3d2f8900f2e49c02b481c2f717aa9020.JPG
         */

        @SerializedName("IMG_TITLE")
        private String IMGTITLE;
        @SerializedName("IMG_URL")
        private String IMGURL;

        public String getIMGTITLE() {
            return IMGTITLE;
        }

        public void setIMGTITLE(String IMGTITLE) {
            this.IMGTITLE = IMGTITLE;
        }

        public String getIMGURL() {
            return IMGURL;
        }

        public void setIMGURL(String IMGURL) {
            this.IMGURL = IMGURL;
        }
    }

    public static class RISKDESCRIPTIONLISTWBean {
        /**
         * DETAIL_DESCRIPTION : 风控流程<span>风控流程</span><span>风控流程</span><span>风控流程</span>
         */

        @SerializedName("DETAIL_DESCRIPTION")
        private String DETAILDESCRIPTION;

        public String getDETAILDESCRIPTION() {
            return DETAILDESCRIPTION;
        }

        public void setDETAILDESCRIPTION(String DETAILDESCRIPTION) {
            this.DETAILDESCRIPTION = DETAILDESCRIPTION;
        }
    }
}
