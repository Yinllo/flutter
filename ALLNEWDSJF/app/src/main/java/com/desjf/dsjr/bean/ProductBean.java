package com.desjf.dsjr.bean;

/**
 * @author YinL
 * @Describe  标的：项目详情
 */
public class ProductBean {

    /**
     * result : {"ENTERPRISE_INFORMATION_ID":"c38f1f7050d0406f874851f50d48d223","OID_PLATFORM_PRODUCTS_ID":"4238861dfd2644c5adfa7fb9e95ba739","BORROWING_VENTURE":"****工程公司","CREDIT_BALANCE":"20万","BORROWING_DESCRIBE":"借款企业项目位于深圳，借款企业营收状况良好，主要从事 消防工程的设计与施工；消防器材、建筑材料、装饰材料的销售；消防工程技术咨询；国内贸易，货物 及技术进出口。（法律、行政法规、国务院决定规定在登记前须经批准的项目除外）。此次借款用于资 金周转。","BORRPWING_PURPOSE":"资金周转","REPAYING_SOURCE":"第一还款来源为借款企业正常经营收入；第二还款来源为企业 法定代表人个人还款。","SECURITY_MEASURE":"借款企业法定代表人承担连带责任；","TRADE_NAME":"****工程公司","BUSINESS_LICENSE_NUMBER":"*********1610*","LEGAL_REPRESENTATIVE":"刘** ","BORROW_NUMBER_YES":" 1次","HISTORY":"0次流标，0次失败","REPAYMENT_NUMBER_YES":" 1次","PAY_NUMBER":" 1次","OVERDUE_PAY_NUMBER":"","OVERDUE_PAY_NUMBER_ABOVE":"","PROJECT_RICK_ASSESSMENT":"","REGISTERED_ASSETS":"","REGISTERED_ADDRESS":"","ESTABLISHED_TIME":"","INDUSTRY_INVOLVED":"","INCOME_AND_LIABLITIES":"","OVERDUE_CREDIT_REPORT":"","CONTRIBUTED_CAPITAL":"","SHAREHOLDER_INFORMATION":"","LEGAL_REPRESENTATIVE_CREDIT_INFO":"","OFFICE_LOCATION":"","INS_DATE":1514857802000,"UPD_DATE":1514858086000,"UserType":"1"}
     * status : true
     * message : 项目详情
     */

    private ResultBean result;
    private boolean status;
    private String message;

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public static class ResultBean {
        /**
         * ENTERPRISE_INFORMATION_ID : c38f1f7050d0406f874851f50d48d223
         * OID_PLATFORM_PRODUCTS_ID : 4238861dfd2644c5adfa7fb9e95ba739
         * BORROWING_VENTURE : ****工程公司
         * CREDIT_BALANCE : 20万
         * BORROWING_DESCRIBE : 借款企业项目位于深圳，借款企业营收状况良好，主要从事 消防工程的设计与施工；消防器材、建筑材料、装饰材料的销售；消防工程技术咨询；国内贸易，货物 及技术进出口。（法律、行政法规、国务院决定规定在登记前须经批准的项目除外）。此次借款用于资 金周转。
         * BORRPWING_PURPOSE : 资金周转
         * REPAYING_SOURCE : 第一还款来源为借款企业正常经营收入；第二还款来源为企业 法定代表人个人还款。
         * SECURITY_MEASURE : 借款企业法定代表人承担连带责任；
         * TRADE_NAME : ****工程公司
         * BUSINESS_LICENSE_NUMBER : *********1610*
         * LEGAL_REPRESENTATIVE : 刘**
         * BORROW_NUMBER_YES :  1次
         * HISTORY : 0次流标，0次失败
         * REPAYMENT_NUMBER_YES :  1次
         * PAY_NUMBER :  1次
         * OVERDUE_PAY_NUMBER :
         * OVERDUE_PAY_NUMBER_ABOVE :
         * PROJECT_RICK_ASSESSMENT :
         * REGISTERED_ASSETS :
         * REGISTERED_ADDRESS :
         * ESTABLISHED_TIME :
         * INDUSTRY_INVOLVED :
         * INCOME_AND_LIABLITIES :
         * OVERDUE_CREDIT_REPORT :
         * CONTRIBUTED_CAPITAL :
         * SHAREHOLDER_INFORMATION :
         * LEGAL_REPRESENTATIVE_CREDIT_INFO :
         * OFFICE_LOCATION :
         * INS_DATE : 1514857802000
         * UPD_DATE : 1514858086000
         * UserType : 1
         */

        private String ENTERPRISE_INFORMATION_ID;
        private String OID_PLATFORM_PRODUCTS_ID;
        private String BORROWING_VENTURE;
        private String CREDIT_BALANCE;
        private String BORROWING_DESCRIBE;
        private String BORRPWING_PURPOSE;
        private String REPAYING_SOURCE;
        private String SECURITY_MEASURE;
        private String TRADE_NAME;
        private String BUSINESS_LICENSE_NUMBER;
        private String LEGAL_REPRESENTATIVE;
        private String BORROW_NUMBER_YES;
        private String HISTORY;
        private String REPAYMENT_NUMBER_YES;
        private String PAY_NUMBER;
        private String OVERDUE_PAY_NUMBER;
        private String OVERDUE_PAY_NUMBER_ABOVE;
        private String PROJECT_RICK_ASSESSMENT;
        private String REGISTERED_ASSETS;
        private String REGISTERED_ADDRESS;
        private String ESTABLISHED_TIME;
        private String INDUSTRY_INVOLVED;
        private String INCOME_AND_LIABLITIES;
        private String OVERDUE_CREDIT_REPORT;
        private String CONTRIBUTED_CAPITAL;
        private String SHAREHOLDER_INFORMATION;
        private String LEGAL_REPRESENTATIVE_CREDIT_INFO;
        private String OFFICE_LOCATION;
        private String UserType;

        public String getENTERPRISE_INFORMATION_ID() {
            return ENTERPRISE_INFORMATION_ID;
        }

        public void setENTERPRISE_INFORMATION_ID(String ENTERPRISE_INFORMATION_ID) {
            this.ENTERPRISE_INFORMATION_ID = ENTERPRISE_INFORMATION_ID;
        }

        public String getOID_PLATFORM_PRODUCTS_ID() {
            return OID_PLATFORM_PRODUCTS_ID;
        }

        public void setOID_PLATFORM_PRODUCTS_ID(String OID_PLATFORM_PRODUCTS_ID) {
            this.OID_PLATFORM_PRODUCTS_ID = OID_PLATFORM_PRODUCTS_ID;
        }

        public String getBORROWING_VENTURE() {
            return BORROWING_VENTURE;
        }

        public void setBORROWING_VENTURE(String BORROWING_VENTURE) {
            this.BORROWING_VENTURE = BORROWING_VENTURE;
        }

        public String getCREDIT_BALANCE() {
            return CREDIT_BALANCE;
        }

        public void setCREDIT_BALANCE(String CREDIT_BALANCE) {
            this.CREDIT_BALANCE = CREDIT_BALANCE;
        }

        public String getBORROWING_DESCRIBE() {
            return BORROWING_DESCRIBE;
        }

        public void setBORROWING_DESCRIBE(String BORROWING_DESCRIBE) {
            this.BORROWING_DESCRIBE = BORROWING_DESCRIBE;
        }

        public String getBORRPWING_PURPOSE() {
            return BORRPWING_PURPOSE;
        }

        public void setBORRPWING_PURPOSE(String BORRPWING_PURPOSE) {
            this.BORRPWING_PURPOSE = BORRPWING_PURPOSE;
        }

        public String getREPAYING_SOURCE() {
            return REPAYING_SOURCE;
        }

        public void setREPAYING_SOURCE(String REPAYING_SOURCE) {
            this.REPAYING_SOURCE = REPAYING_SOURCE;
        }

        public String getSECURITY_MEASURE() {
            return SECURITY_MEASURE;
        }

        public void setSECURITY_MEASURE(String SECURITY_MEASURE) {
            this.SECURITY_MEASURE = SECURITY_MEASURE;
        }

        public String getTRADE_NAME() {
            return TRADE_NAME;
        }

        public void setTRADE_NAME(String TRADE_NAME) {
            this.TRADE_NAME = TRADE_NAME;
        }

        public String getBUSINESS_LICENSE_NUMBER() {
            return BUSINESS_LICENSE_NUMBER;
        }

        public void setBUSINESS_LICENSE_NUMBER(String BUSINESS_LICENSE_NUMBER) {
            this.BUSINESS_LICENSE_NUMBER = BUSINESS_LICENSE_NUMBER;
        }

        public String getLEGAL_REPRESENTATIVE() {
            return LEGAL_REPRESENTATIVE;
        }

        public void setLEGAL_REPRESENTATIVE(String LEGAL_REPRESENTATIVE) {
            this.LEGAL_REPRESENTATIVE = LEGAL_REPRESENTATIVE;
        }

        public String getBORROW_NUMBER_YES() {
            return BORROW_NUMBER_YES;
        }

        public void setBORROW_NUMBER_YES(String BORROW_NUMBER_YES) {
            this.BORROW_NUMBER_YES = BORROW_NUMBER_YES;
        }

        public String getHISTORY() {
            return HISTORY;
        }

        public void setHISTORY(String HISTORY) {
            this.HISTORY = HISTORY;
        }

        public String getREPAYMENT_NUMBER_YES() {
            return REPAYMENT_NUMBER_YES;
        }

        public void setREPAYMENT_NUMBER_YES(String REPAYMENT_NUMBER_YES) {
            this.REPAYMENT_NUMBER_YES = REPAYMENT_NUMBER_YES;
        }

        public String getPAY_NUMBER() {
            return PAY_NUMBER;
        }

        public void setPAY_NUMBER(String PAY_NUMBER) {
            this.PAY_NUMBER = PAY_NUMBER;
        }

        public String getOVERDUE_PAY_NUMBER() {
            return OVERDUE_PAY_NUMBER;
        }

        public void setOVERDUE_PAY_NUMBER(String OVERDUE_PAY_NUMBER) {
            this.OVERDUE_PAY_NUMBER = OVERDUE_PAY_NUMBER;
        }

        public String getOVERDUE_PAY_NUMBER_ABOVE() {
            return OVERDUE_PAY_NUMBER_ABOVE;
        }

        public void setOVERDUE_PAY_NUMBER_ABOVE(String OVERDUE_PAY_NUMBER_ABOVE) {
            this.OVERDUE_PAY_NUMBER_ABOVE = OVERDUE_PAY_NUMBER_ABOVE;
        }

        public String getPROJECT_RICK_ASSESSMENT() {
            return PROJECT_RICK_ASSESSMENT;
        }

        public void setPROJECT_RICK_ASSESSMENT(String PROJECT_RICK_ASSESSMENT) {
            this.PROJECT_RICK_ASSESSMENT = PROJECT_RICK_ASSESSMENT;
        }

        public String getREGISTERED_ASSETS() {
            return REGISTERED_ASSETS;
        }

        public void setREGISTERED_ASSETS(String REGISTERED_ASSETS) {
            this.REGISTERED_ASSETS = REGISTERED_ASSETS;
        }

        public String getREGISTERED_ADDRESS() {
            return REGISTERED_ADDRESS;
        }

        public void setREGISTERED_ADDRESS(String REGISTERED_ADDRESS) {
            this.REGISTERED_ADDRESS = REGISTERED_ADDRESS;
        }

        public String getESTABLISHED_TIME() {
            return ESTABLISHED_TIME;
        }

        public void setESTABLISHED_TIME(String ESTABLISHED_TIME) {
            this.ESTABLISHED_TIME = ESTABLISHED_TIME;
        }

        public String getINDUSTRY_INVOLVED() {
            return INDUSTRY_INVOLVED;
        }

        public void setINDUSTRY_INVOLVED(String INDUSTRY_INVOLVED) {
            this.INDUSTRY_INVOLVED = INDUSTRY_INVOLVED;
        }

        public String getINCOME_AND_LIABLITIES() {
            return INCOME_AND_LIABLITIES;
        }

        public void setINCOME_AND_LIABLITIES(String INCOME_AND_LIABLITIES) {
            this.INCOME_AND_LIABLITIES = INCOME_AND_LIABLITIES;
        }

        public String getOVERDUE_CREDIT_REPORT() {
            return OVERDUE_CREDIT_REPORT;
        }

        public void setOVERDUE_CREDIT_REPORT(String OVERDUE_CREDIT_REPORT) {
            this.OVERDUE_CREDIT_REPORT = OVERDUE_CREDIT_REPORT;
        }

        public String getCONTRIBUTED_CAPITAL() {
            return CONTRIBUTED_CAPITAL;
        }

        public void setCONTRIBUTED_CAPITAL(String CONTRIBUTED_CAPITAL) {
            this.CONTRIBUTED_CAPITAL = CONTRIBUTED_CAPITAL;
        }

        public String getSHAREHOLDER_INFORMATION() {
            return SHAREHOLDER_INFORMATION;
        }

        public void setSHAREHOLDER_INFORMATION(String SHAREHOLDER_INFORMATION) {
            this.SHAREHOLDER_INFORMATION = SHAREHOLDER_INFORMATION;
        }

        public String getLEGAL_REPRESENTATIVE_CREDIT_INFO() {
            return LEGAL_REPRESENTATIVE_CREDIT_INFO;
        }

        public void setLEGAL_REPRESENTATIVE_CREDIT_INFO(String LEGAL_REPRESENTATIVE_CREDIT_INFO) {
            this.LEGAL_REPRESENTATIVE_CREDIT_INFO = LEGAL_REPRESENTATIVE_CREDIT_INFO;
        }

        public String getOFFICE_LOCATION() {
            return OFFICE_LOCATION;
        }

        public void setOFFICE_LOCATION(String OFFICE_LOCATION) {
            this.OFFICE_LOCATION = OFFICE_LOCATION;
        }

        public String getUserType() {
            return UserType;
        }

        public void setUserType(String UserType) {
            this.UserType = UserType;
        }
    }
}
