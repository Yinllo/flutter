package com.desjf.dsjr.model;

/**
 * Created by Administrator on 2017/7/26 0026.
 */

public class NewsCenterModel {
    private String NEWS_TITLE;
    private String PUBLISH_DATE;
    private String OPEN_FLG;
    private String MESSAGE_TITLE;
    private String MESSAGE_INS_DATE;

    public String getNEWS_TITLE() {
        return NEWS_TITLE;
    }

    public void setNEWS_TITLE(String NEWS_TITLE) {
        this.NEWS_TITLE = NEWS_TITLE;
    }

    public String getPUBLISH_DATE() {
        return PUBLISH_DATE;
    }

    public void setPUBLISH_DATE(String PUBLISH_DATE) {
        this.PUBLISH_DATE = PUBLISH_DATE;
    }

    public String getOPEN_FLG() {
        return OPEN_FLG;
    }

    public void setOPEN_FLG(String OPEN_FLG) {
        this.OPEN_FLG = OPEN_FLG;
    }

    public String getMESSAGE_TITLE() {
        return MESSAGE_TITLE;
    }

    public void setMESSAGE_TITLE(String MESSAGE_TITLE) {
        this.MESSAGE_TITLE = MESSAGE_TITLE;
    }

    public String getMESSAGE_INS_DATE() {
        return MESSAGE_INS_DATE;
    }

    public void setMESSAGE_INS_DATE(String MESSAGE_INS_DATE) {
        this.MESSAGE_INS_DATE = MESSAGE_INS_DATE;
    }
}
