package com.desjf.dsjr.model.bankModel;

import java.util.List;

/**
 * @Author YinL
 * @Date 2018/3/26 0026
 * @Describe  用户个人信息
 */

public class BankUserInfModel {


    private List<UsermessageBean> usermessage;

    public List<UsermessageBean> getUsermessage() {
        return usermessage;
    }

    public void setUsermessage(List<UsermessageBean> usermessage) {
        this.usermessage = usermessage;
    }

    public static class UsermessageBean {
        /**
         *cap_corg开户行
         cap_crd_no卡号,
         mbl_no绑定手机号
         usr_nm姓名
         *
         * mbl_no : 15600000000
         * cap_crd_no : 6228482222222222222
         * cap_corg : ABC
         * usr_nm : 杨冰巧
         * PlaCustId : 000510******6035
         * OpenAcctId : 6225****5556
         */

        private String mbl_no;
        private String cap_crd_no;
        private String cap_corg;
        private String usr_nm;
        private String PlaCustId;
        private String OpenAcctId;

        public String getMbl_no() {
            return mbl_no;
        }

        public void setMbl_no(String mbl_no) {
            this.mbl_no = mbl_no;
        }

        public String getCap_crd_no() {
            return cap_crd_no;
        }

        public void setCap_crd_no(String cap_crd_no) {
            this.cap_crd_no = cap_crd_no;
        }

        public String getCap_corg() {
            return cap_corg;
        }

        public void setCap_corg(String cap_corg) {
            this.cap_corg = cap_corg;
        }

        public String getUsr_nm() {
            return usr_nm;
        }

        public void setUsr_nm(String usr_nm) {
            this.usr_nm = usr_nm;
        }

        public String getPlaCustId() {
            return PlaCustId;
        }

        public void setPlaCustId(String PlaCustId) {
            this.PlaCustId = PlaCustId;
        }

        public String getOpenAcctId() {
            return OpenAcctId;
        }

        public void setOpenAcctId(String OpenAcctId) {
            this.OpenAcctId = OpenAcctId;
        }
    }
}
