package com.desjf.dsjr.model.bankModel;

import java.util.List;

/**
 * @Author YinL
 * @Date 2018/3/26 0026
 * @Describe  资金流水
 */

public class BankAccountFundDetailsModel {


    /**
     * num : 42
     * returnList : [{"FUND_TYPE":"001","REVENUE_EXPEND_TYPE":"R","USABLE_AMOUNT":0,"AMOUNT":0,"INS_DATE":1522029458000},{"FUND_TYPE":"054","REVENUE_EXPEND_TYPE":"E","USABLE_AMOUNT":950249.21,"AMOUNT":88888,"INS_DATE":1519884235000},{"FUND_TYPE":"015","REVENUE_EXPEND_TYPE":"R","USABLE_AMOUNT":1039137.21,"AMOUNT":100000,"INS_DATE":1519722080000},{"FUND_TYPE":"016","REVENUE_EXPEND_TYPE":"R","USABLE_AMOUNT":939137.21,"AMOUNT":1166.67,"INS_DATE":1519722061000},{"FUND_TYPE":"016","REVENUE_EXPEND_TYPE":"R","USABLE_AMOUNT":937970.54,"AMOUNT":1166.67,"INS_DATE":1519722002000},{"FUND_TYPE":"014","REVENUE_EXPEND_TYPE":"E","USABLE_AMOUNT":936803.87,"AMOUNT":99990,"INS_DATE":1519721968000},{"FUND_TYPE":"012","REVENUE_EXPEND_TYPE":"E","USABLE_AMOUNT":936803.87,"AMOUNT":99990,"INS_DATE":1519721958000},{"FUND_TYPE":"015","REVENUE_EXPEND_TYPE":"R","USABLE_AMOUNT":985627.19,"AMOUNT":50000,"INS_DATE":1519721577000},{"FUND_TYPE":"016","REVENUE_EXPEND_TYPE":"R","USABLE_AMOUNT":986210.53,"AMOUNT":583.34,"INS_DATE":1519721577000},{"FUND_TYPE":"015","REVENUE_EXPEND_TYPE":"R","USABLE_AMOUNT":1036210.53,"AMOUNT":50000,"INS_DATE":1519721577000},{"FUND_TYPE":"016","REVENUE_EXPEND_TYPE":"R","USABLE_AMOUNT":1036793.87,"AMOUNT":583.34,"INS_DATE":1519721577000},{"FUND_TYPE":"014","REVENUE_EXPEND_TYPE":"E","USABLE_AMOUNT":935627.19,"AMOUNT":50000,"INS_DATE":1519721526000},{"FUND_TYPE":"014","REVENUE_EXPEND_TYPE":"E","USABLE_AMOUNT":935627.19,"AMOUNT":49990,"INS_DATE":1519721526000},{"FUND_TYPE":"012","REVENUE_EXPEND_TYPE":"E","USABLE_AMOUNT":935627.19,"AMOUNT":50000,"INS_DATE":1519721526000},{"FUND_TYPE":"012","REVENUE_EXPEND_TYPE":"E","USABLE_AMOUNT":985627.19,"AMOUNT":49990,"INS_DATE":1519721490000},{"FUND_TYPE":"016","REVENUE_EXPEND_TYPE":"R","USABLE_AMOUNT":1035617.19,"AMOUNT":1166.67,"INS_DATE":1519721387000},{"FUND_TYPE":"015","REVENUE_EXPEND_TYPE":"R","USABLE_AMOUNT":1034450.52,"AMOUNT":100000,"INS_DATE":1519721387000},{"FUND_TYPE":"014","REVENUE_EXPEND_TYPE":"E","USABLE_AMOUNT":934450.52,"AMOUNT":100000,"INS_DATE":1519721332000},{"FUND_TYPE":"012","REVENUE_EXPEND_TYPE":"E","USABLE_AMOUNT":934450.52,"AMOUNT":100000,"INS_DATE":1519721331000},{"FUND_TYPE":"016","REVENUE_EXPEND_TYPE":"R","USABLE_AMOUNT":1034450.52,"AMOUNT":349.96,"INS_DATE":1519720997000}]
     */

    private String num;
    private List<ReturnListBean> returnList;

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public List<ReturnListBean> getReturnList() {
        return returnList;
    }

    public void setReturnList(List<ReturnListBean> returnList) {
        this.returnList = returnList;
    }

    public static class ReturnListBean {
        /**
         * FUND_TYPE : 001
         * REVENUE_EXPEND_TYPE : R
         * USABLE_AMOUNT : 0.0
         * AMOUNT : 0.0
         * INS_DATE : 1522029458000
         * REMARK:详细描述信息
         */

        private String FUND_TYPE;
        private String REVENUE_EXPEND_TYPE;
        private String USABLE_AMOUNT;
        private String AMOUNT;
        private long INS_DATE;
        private String REMARK;

        public String getREMARK() {
            return REMARK;
        }

        public void setREMARK(String REMARK) {
            this.REMARK = REMARK;
        }

        public String getFUND_TYPE() {
            return FUND_TYPE;
        }

        public void setFUND_TYPE(String FUND_TYPE) {
            this.FUND_TYPE = FUND_TYPE;
        }

        public String getREVENUE_EXPEND_TYPE() {
            return REVENUE_EXPEND_TYPE;
        }

        public void setREVENUE_EXPEND_TYPE(String REVENUE_EXPEND_TYPE) {
            this.REVENUE_EXPEND_TYPE = REVENUE_EXPEND_TYPE;
        }

        public long getINS_DATE() {
            return INS_DATE;
        }

        public void setINS_DATE(long INS_DATE) {
            this.INS_DATE = INS_DATE;
        }

        public String getUSABLE_AMOUNT() {
            return USABLE_AMOUNT == null ? "" : USABLE_AMOUNT;
        }

        public void setUSABLE_AMOUNT(String USABLE_AMOUNT) {
            this.USABLE_AMOUNT = USABLE_AMOUNT;
        }

        public String getAMOUNT() {
            return AMOUNT == null ? "" : AMOUNT;
        }

        public void setAMOUNT(String AMOUNT) {
            this.AMOUNT = AMOUNT;
        }
    }
}