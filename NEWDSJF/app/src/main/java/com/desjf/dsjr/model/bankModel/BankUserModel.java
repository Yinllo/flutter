package com.desjf.dsjr.model.bankModel;

/**
 * @Author YinL
 * @Date 2018/4/16 0016
 * @Describe
 */

public class BankUserModel {


    /**
     * RISK : 0
     * identNo : 3******4
     * adress :
     * email :
     * name : 姜**
     * realMobile : 15675122816
     * mobileNoA : 15675122816
     * license :
     * identNoA : 370522197406159224
     * NameA : 姜红云
     * mobile : 156*****816
     */

    private String RISK;
    private String identNo;
    private String adress;
    private String email;
    private String name;
    private String realMobile;
    private String mobileNoA;
    private String license;
    private String identNoA;
    private String NameA;
    private String mobile;

    public String getRISK() {
        return RISK == null ? "" : RISK;
    }

    public void setRISK(String RISK) {
        this.RISK = RISK;
    }

    public String getIdentNo() {
        return identNo == null ? "" : identNo;
    }

    public void setIdentNo(String identNo) {
        this.identNo = identNo;
    }

    public String getAdress() {
        return adress == null ? "" : adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public String getEmail() {
        return email == null ? "" : email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name == null ? "" : name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRealMobile() {
        return realMobile == null ? "" : realMobile;
    }

    public void setRealMobile(String realMobile) {
        this.realMobile = realMobile;
    }

    public String getMobileNoA() {
        return mobileNoA == null ? "" : mobileNoA;
    }

    public void setMobileNoA(String mobileNoA) {
        this.mobileNoA = mobileNoA;
    }

    public String getLicense() {
        return license == null ? "" : license;
    }

    public void setLicense(String license) {
        this.license = license;
    }

    public String getIdentNoA() {
        return identNoA == null ? "" : identNoA;
    }

    public void setIdentNoA(String identNoA) {
        this.identNoA = identNoA;
    }

    public String getNameA() {
        return NameA == null ? "" : NameA;
    }

    public void setNameA(String nameA) {
        NameA = nameA;
    }

    public String getMobile() {
        return mobile == null ? "" : mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
}
