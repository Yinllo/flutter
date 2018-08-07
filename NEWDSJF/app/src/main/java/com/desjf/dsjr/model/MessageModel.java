package com.desjf.dsjr.model;

/**
 * Created by Administrator on 2017/7/26 0026.
 */

public class MessageModel {
    private String INS_DATE;
    private String ID;
    private String MAIL_STATUS;
    private String TITLE;
    private String CONTENTS;
   private  String OPEN_FLG;

    public String getOPEN_FLG() {
        return OPEN_FLG;
    }

    public void setOPEN_FLG(String OPEN_FLG) {
        this.OPEN_FLG = OPEN_FLG;
    }

    public String getINS_DATE() {
        return INS_DATE;
    }

    public void setINS_DATE(String INS_DATE) {
        this.INS_DATE = INS_DATE;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getMAIL_STATUS() {
        return MAIL_STATUS;
    }

    public void setMAIL_STATUS(String MAIL_STATUS) {
        this.MAIL_STATUS = MAIL_STATUS;
    }

    public String getTITLE() {
        return TITLE;
    }

    public void setTITLE(String TITLE) {
        this.TITLE = TITLE;
    }

    public String getCONTENTS() {
        return CONTENTS;
    }

    public void setCONTENTS(String CONTENTS) {
        this.CONTENTS = CONTENTS;
    }
}
