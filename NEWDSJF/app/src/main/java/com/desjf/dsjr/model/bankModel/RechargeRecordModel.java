package com.desjf.dsjr.model.bankModel;

import java.util.List;

/**
 * @Author YinL
 * @Date 2018/3/26 0026
 * @Describe
 */

public class RechargeRecordModel {


    /**
     * getRechargeList : [{"TransAmt":800,"status":"0","MerBillNo":"800075500010005o180326090000667","updDate":1522026001000,"MerFeeAmt":2},{"TransAmt":500,"status":"0","MerBillNo":"800075500010005o180325164445451","updDate":1521967485000,"MerFeeAmt":2},{"TransAmt":80000,"status":"1","MerBillNo":"800075500010005o180325133741743","updDate":1521956296000,"MerFeeAmt":2},{"TransAmt":800,"status":"0","MerBillNo":"800075500010005o180324205306524","updDate":1521895986000,"MerFeeAmt":2},{"TransAmt":800,"status":"0","MerBillNo":"800075500010005o180324205241534","updDate":1521895962000,"MerFeeAmt":2},{"TransAmt":800,"status":"1","MerBillNo":"800075500010005o180324204622305","updDate":1521895623000,"MerFeeAmt":2},{"TransAmt":800,"status":"1","MerBillNo":"800075500010005o180324203644144","updDate":1521895067000,"MerFeeAmt":2},{"TransAmt":800,"status":"0","MerBillNo":"800075500010005o180324203609631","updDate":1521894969000,"MerFeeAmt":2},{"TransAmt":800,"status":"0","MerBillNo":"800075500010005o180324203156195","updDate":1521894716000,"MerFeeAmt":2},{"TransAmt":10000,"status":"0","MerBillNo":"800075500010005O180319115947946","updDate":1521431987000,"MerFeeAmt":1}]
     * rechargeNum : 34
     */

    private int rechargeNum;
    private List<GetRechargeListBean> getRechargeList;

    public int getRechargeNum() {
        return rechargeNum;
    }

    public void setRechargeNum(int rechargeNum) {
        this.rechargeNum = rechargeNum;
    }

    public List<GetRechargeListBean> getGetRechargeList() {
        return getRechargeList;
    }

    public void setGetRechargeList(List<GetRechargeListBean> getRechargeList) {
        this.getRechargeList = getRechargeList;
    }

    public static class GetRechargeListBean {
        /**
         * TransAmt : 800.0
         * status : 0
         * MerBillNo : 800075500010005o180326090000667
         * updDate : 1522026001000
         * MerFeeAmt : 2.0
         */

        private String TransAmt;
        private String status;
        private String MerBillNo;
        private long updDate;
        private String MerFeeAmt;
        private String USABLE_AMOUNT;



        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getMerBillNo() {
            return MerBillNo;
        }

        public void setMerBillNo(String MerBillNo) {
            this.MerBillNo = MerBillNo;
        }

        public long getUpdDate() {
            return updDate;
        }

        public void setUpdDate(long updDate) {
            this.updDate = updDate;
        }

        public String getTransAmt() {
            return TransAmt == null ? "" : TransAmt;
        }

        public void setTransAmt(String transAmt) {
            TransAmt = transAmt;
        }

        public String getMerFeeAmt() {
            return MerFeeAmt == null ? "" : MerFeeAmt;
        }

        public void setMerFeeAmt(String merFeeAmt) {
            MerFeeAmt = merFeeAmt;
        }

        public String getUSABLE_AMOUNT() {
            return USABLE_AMOUNT == null ? "" : USABLE_AMOUNT;
        }

        public void setUSABLE_AMOUNT(String USABLE_AMOUNT) {
            this.USABLE_AMOUNT = USABLE_AMOUNT;
        }
    }
}
