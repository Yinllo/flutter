package com.desjf.dsjr.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Administrator on 2017/8/19 0019.
 */

public class VersionDescription {

    /**
     * iphone : 1.0.0
     * android : 1.0.0
     * iphone_update_message : 鏂扮増鏈洿鏂�(V1.0.0):
     1.淇宸茬煡bug;
     2.鐣岄潰浼樺寲
     * android_update_message : 鏂扮増鏈彂甯�(V1.0.0):
     1.淇宸茬煡bug;
     2.鐣岄潰浼樺寲
     * iphone_download_link :
     * android_download_link : http://www.xxx.com/mobile/APP/TPRXV20.apk
     * android_force_update :
     */

    @SerializedName("iphone")
    private String iphone;
    @SerializedName("android")
    private String android;
    @SerializedName("iphone_update_message")
    private String iphoneUpdateMessage;
    @SerializedName("android_update_message")
    private String androidUpdateMessage;
    @SerializedName("iphone_download_link")
    private String iphoneDownloadLink;
    @SerializedName("android_download_link")
    private String androidDownloadLink;
    @SerializedName("android_force_update")
    private String androidForceUpdate;  //是否强制更新字段  为1时强制更新，为""则不强制更新

    public String getIphone() {
        return iphone;
    }

    public void setIphone(String iphone) {
        this.iphone = iphone;
    }

    public String getAndroid() {
        return android;
    }

    public void setAndroid(String android) {
        this.android = android;
    }

    public String getIphoneUpdateMessage() {
        return iphoneUpdateMessage;
    }

    public void setIphoneUpdateMessage(String iphoneUpdateMessage) {
        this.iphoneUpdateMessage = iphoneUpdateMessage;
    }

    public String getAndroidUpdateMessage() {
        return androidUpdateMessage;
    }

    public void setAndroidUpdateMessage(String androidUpdateMessage) {
        this.androidUpdateMessage = androidUpdateMessage;
    }

    public String getIphoneDownloadLink() {
        return iphoneDownloadLink;
    }

    public void setIphoneDownloadLink(String iphoneDownloadLink) {
        this.iphoneDownloadLink = iphoneDownloadLink;
    }

    public String getAndroidDownloadLink() {
        return androidDownloadLink;
    }

    public void setAndroidDownloadLink(String androidDownloadLink) {
        this.androidDownloadLink = androidDownloadLink;
    }

    public String getAndroidForceUpdate() {
        return androidForceUpdate;
    }

    public void setAndroidForceUpdate(String androidForceUpdate) {
        this.androidForceUpdate = androidForceUpdate;
    }
}
