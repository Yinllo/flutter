package com.desjf.dsjr.model.bankModel;

import java.util.List;

/**
 * @Author YinL
 * @Date 2018/3/26 0026
 * @Describe  用户余额
 */

public class BankUserBalanceModel {


    private List<UsermessageBean> usermessage;

    public List<UsermessageBean> getUsermessage() {
        return usermessage;
    }

    public void setUsermessage(List<UsermessageBean> usermessage) {
        this.usermessage = usermessage;
    }

    public static class UsermessageBean {
        /**
         * cap_typ资金种类(1投资2融资)
         AvlBal 可用余额
         AcctBal账户余额
         FrzBal冻结余额
         *
         *
         * AcctBal : 11324518
         * cap_typ : 1
         * FrzBal : 222401
         * AvlBal : 11102117
         */

        private String AcctBal;
        private String cap_typ;
        private String FrzBal;
        private String AvlBal;

        public String getAcctBal() {
            return AcctBal;
        }

        public void setAcctBal(String AcctBal) {
            this.AcctBal = AcctBal;
        }

        public String getCap_typ() {
            return cap_typ;
        }

        public void setCap_typ(String cap_typ) {
            this.cap_typ = cap_typ;
        }

        public String getFrzBal() {
            return FrzBal;
        }

        public void setFrzBal(String FrzBal) {
            this.FrzBal = FrzBal;
        }

        public String getAvlBal() {
            return AvlBal;
        }

        public void setAvlBal(String AvlBal) {
            this.AvlBal = AvlBal;
        }
    }
}
