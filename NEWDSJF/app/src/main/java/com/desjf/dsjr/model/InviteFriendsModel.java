package com.desjf.dsjr.model;

/**
 * Created by Administrator on 2017/7/28 0028.
 */

public class InviteFriendsModel {
    private String FRIEND_COUNT;
    private String RECEIVED_AMOUNT;
    private String INVITE_FRIEND_URL;

    public String getFRIEND_COUNT() {
        return FRIEND_COUNT;
    }

    public void setFRIEND_COUNT(String FRIEND_COUNT) {
        this.FRIEND_COUNT = FRIEND_COUNT;
    }

    public String getRECEIVED_AMOUNT() {
        return RECEIVED_AMOUNT;
    }

    public void setRECEIVED_AMOUNT(String RECEIVED_AMOUNT) {
        this.RECEIVED_AMOUNT = RECEIVED_AMOUNT;
    }

    public String getINVITE_FRIEND_URL() {
        return INVITE_FRIEND_URL;
    }

    public void setINVITE_FRIEND_URL(String INVITE_FRIEND_URL) {
        this.INVITE_FRIEND_URL = INVITE_FRIEND_URL;
    }
}
