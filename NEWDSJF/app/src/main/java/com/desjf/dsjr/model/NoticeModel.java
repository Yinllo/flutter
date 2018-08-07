package com.desjf.dsjr.model;

/**
 * Created by Administrator on 2017/7/26 0026.
 */

public class NoticeModel {
    private String AUTHOR;
    private String PUBLISH_DATE;
    private String SUMMARY;
    private String TITLE;
    private String ID;

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getAUTHOR() {
        return AUTHOR;
    }

    public void setAUTHOR(String AUTHOR) {
        this.AUTHOR = AUTHOR;
    }

    public String getPUBLISH_DATE() {
        return PUBLISH_DATE;
    }

    public void setPUBLISH_DATE(String PUBLISH_DATE) {
        this.PUBLISH_DATE = PUBLISH_DATE;
    }

    public String getSUMMARY() {
        return SUMMARY;
    }

    public void setSUMMARY(String SUMMARY) {
        this.SUMMARY = SUMMARY;
    }

    public String getTITLE() {
        return TITLE;
    }

    public void setTITLE(String TITLE) {
        this.TITLE = TITLE;
    }
}
