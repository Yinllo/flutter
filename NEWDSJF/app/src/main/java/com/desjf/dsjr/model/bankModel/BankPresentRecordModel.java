package com.desjf.dsjr.model.bankModel;

import java.util.List;

/**
 * @Author YinL
 * @Date 2018/3/26 0026
 * @Describe
 */

public class BankPresentRecordModel {


    /**
     * withdrawNum : 36
     * getwithdrawList : [{"TransAmt":2000000,"status":"0","MerBillNo":"800075500010005P180319134413164","updDate":1521438253000,"MerFeeAmt":2},{"TransAmt":59001,"status":"1","MerBillNo":"800075500010005P180319134226982","updDate":1521438173000,"MerFeeAmt":2},{"TransAmt":10001,"status":"0","MerBillNo":"800075500010005P180319133955156","updDate":1521437995000,"MerFeeAmt":2},{"TransAmt":10010,"status":"0","MerBillNo":"800075500010005P180319133931679","updDate":1521437971000,"MerFeeAmt":2},{"TransAmt":10000,"status":"0","MerBillNo":"800075500010005P180319120024937","updDate":1521432024000,"MerFeeAmt":2},{"TransAmt":10000,"status":"0","MerBillNo":"800075500010005P180319120024751","updDate":1521432024000,"MerFeeAmt":2},{"TransAmt":100000,"status":"0","MerBillNo":"800075500010005P180319115619746","updDate":1521431779000,"MerFeeAmt":2},{"TransAmt":100000,"status":"0","MerBillNo":"800075500010005P180319115612245","updDate":1521431772000,"MerFeeAmt":2},{"TransAmt":10000,"status":"0","MerBillNo":"800075500010005P180319093734142","updDate":1521423454000,"MerFeeAmt":2},{"TransAmt":10000,"status":"0","MerBillNo":"800075500010005P180319093529641","updDate":1521423329000,"MerFeeAmt":2},{"TransAmt":10000,"status":"0","MerBillNo":"800075500010005P180319093417500","updDate":1521423257000,"MerFeeAmt":2},{"TransAmt":10000,"status":"0","MerBillNo":"800075500010005P180319093408896","updDate":1521423248000,"MerFeeAmt":2},{"TransAmt":10000,"status":"0","MerBillNo":"800075500010005P180319093408725","updDate":1521423248000,"MerFeeAmt":2},{"TransAmt":10000,"status":"0","MerBillNo":"800075500010005P180319093408428","updDate":1521423248000,"MerFeeAmt":2},{"TransAmt":10000,"status":"0","MerBillNo":"800075500010005P180319093408997","updDate":1521423248000,"MerFeeAmt":2},{"TransAmt":10021,"status":"0","MerBillNo":"800075500010005P180316174914668","updDate":1521193754000,"MerFeeAmt":2},{"TransAmt":10010,"status":"0","MerBillNo":"800075500010005P180316174905257","updDate":1521193745000,"MerFeeAmt":2},{"TransAmt":10001,"status":"0","MerBillNo":"800075500010005P180316172223714","updDate":1521192143000,"MerFeeAmt":2},{"TransAmt":100000,"status":"1","MerBillNo":"800075500010005P180316161927826","updDate":1521188391000,"MerFeeAmt":2},{"TransAmt":10010,"status":"0","MerBillNo":"800075500010005P180316093502900","updDate":1521164102000,"MerFeeAmt":2}]
     */

    private int withdrawNum;
    private List<GetwithdrawListBean> getwithdrawList;

    public int getWithdrawNum() {
        return withdrawNum;
    }

    public void setWithdrawNum(int withdrawNum) {
        this.withdrawNum = withdrawNum;
    }

    public List<GetwithdrawListBean> getGetwithdrawList() {
        return getwithdrawList;
    }

    public void setGetwithdrawList(List<GetwithdrawListBean> getwithdrawList) {
        this.getwithdrawList = getwithdrawList;
    }

    public static class GetwithdrawListBean {
        /**
         * TransAmt : 2000000.0
         * status : 0
         * MerBillNo : 800075500010005P180319134413164
         * updDate : 1521438253000
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
