package com.desjf.dsjr.bean;

import android.os.Parcel;
import android.os.Parcelable;

import com.chad.library.adapter.base.entity.MultiItemEntity;

import java.util.List;

/**
 * @Author YinL
 * @Date 2018/6/19 0019
 * @Describe  标的信息
 */

public class SubjectBean {


    /**
     * pageNum : 1
     * pageSize : 1
     * size : 1
     * startRow : 1
     * endRow : 1
     * total : 227
     * pages : 227
     * list : [{"FINANCE_REPAY_TYPE":"4","FINANCE_AMOUNT":0.1,"FINANCE_PERIOD":1,"PRODUCTS_STATUS":"1","MOBILE":"186****2843","NOVICE_FLG":"0","FINANCE_FULL_FLG":"1","PRODUCTS_TITLE":"测试渤海建标111","FINANCE_AMOUNT_WAIT":0,"FINANCE_AMOUNT_SCALE":100,"FINANCE_INTEREST_RATE":15,"OID_PLATFORM_PRODUCTS_ID":"3d4a9b44bc614590bcfb3544c6f28dc0"}]
     * prePage : 0
     * nextPage : 2
     * isFirstPage : true
     * isLastPage : false
     * hasPreviousPage : false
     * hasNextPage : true
     * navigatePages : 8
     * navigatepageNums : [1,2,3,4,5,6,7,8]
     * navigateFirstPage : 1
     * navigateLastPage : 8
     * firstPage : 1
     * lastPage : 8
     */

    private int pageNum;
    private int pageSize;
    private int size;
    private int startRow;
    private int endRow;
    private int total;
    private int pages;
    private int prePage;
    private int nextPage;
    private boolean isFirstPage;
    private boolean isLastPage;
    private boolean hasPreviousPage;
    private boolean hasNextPage;
    private int navigatePages;
    private int navigateFirstPage;
    private int navigateLastPage;
    private int firstPage;
    private int lastPage;
    private List<ListBean> list;
    private List<Integer> navigatepageNums;

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getStartRow() {
        return startRow;
    }

    public void setStartRow(int startRow) {
        this.startRow = startRow;
    }

    public int getEndRow() {
        return endRow;
    }

    public void setEndRow(int endRow) {
        this.endRow = endRow;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public int getPrePage() {
        return prePage;
    }

    public void setPrePage(int prePage) {
        this.prePage = prePage;
    }

    public int getNextPage() {
        return nextPage;
    }

    public void setNextPage(int nextPage) {
        this.nextPage = nextPage;
    }

    public boolean isIsFirstPage() {
        return isFirstPage;
    }

    public void setIsFirstPage(boolean isFirstPage) {
        this.isFirstPage = isFirstPage;
    }

    public boolean isIsLastPage() {
        return isLastPage;
    }

    public void setIsLastPage(boolean isLastPage) {
        this.isLastPage = isLastPage;
    }

    public boolean isHasPreviousPage() {
        return hasPreviousPage;
    }

    public void setHasPreviousPage(boolean hasPreviousPage) {
        this.hasPreviousPage = hasPreviousPage;
    }

    public boolean isHasNextPage() {
        return hasNextPage;
    }

    public void setHasNextPage(boolean hasNextPage) {
        this.hasNextPage = hasNextPage;
    }

    public int getNavigatePages() {
        return navigatePages;
    }

    public void setNavigatePages(int navigatePages) {
        this.navigatePages = navigatePages;
    }

    public int getNavigateFirstPage() {
        return navigateFirstPage;
    }

    public void setNavigateFirstPage(int navigateFirstPage) {
        this.navigateFirstPage = navigateFirstPage;
    }

    public int getNavigateLastPage() {
        return navigateLastPage;
    }

    public void setNavigateLastPage(int navigateLastPage) {
        this.navigateLastPage = navigateLastPage;
    }

    public int getFirstPage() {
        return firstPage;
    }

    public void setFirstPage(int firstPage) {
        this.firstPage = firstPage;
    }

    public int getLastPage() {
        return lastPage;
    }

    public void setLastPage(int lastPage) {
        this.lastPage = lastPage;
    }

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public List<Integer> getNavigatepageNums() {
        return navigatepageNums;
    }

    public void setNavigatepageNums(List<Integer> navigatepageNums) {
        this.navigatepageNums = navigatepageNums;
    }

    public static class ListBean implements Parcelable, MultiItemEntity {
        /**
         * FINANCE_REPAY_TYPE : 4
         * FINANCE_AMOUNT : 0.1
         * FINANCE_PERIOD : 1
         * PRODUCTS_STATUS : 1
         * MOBILE : 186****2843
         * NOVICE_FLG : 0
         * FINANCE_FULL_FLG : 1
         * PRODUCTS_TITLE : 测试渤海建标111
         * FINANCE_AMOUNT_WAIT : 0
         * FINANCE_AMOUNT_SCALE : 100
         * FINANCE_INTEREST_RATE : 15
         * OID_PLATFORM_PRODUCTS_ID : 3d4a9b44bc614590bcfb3544c6f28dc0
         */

        private String FINANCE_REPAY_TYPE;
        private double FINANCE_AMOUNT;
        private String FINANCE_PERIOD;
        private String PRODUCTS_STATUS;
        private String MOBILE;
        private String NOVICE_FLG;
        private String FINANCE_FULL_FLG;
        private String PRODUCTS_TITLE;
        private String FINANCE_AMOUNT_WAIT;
        private int FINANCE_AMOUNT_SCALE;
        private String FINANCE_INTEREST_RATE;
        private String OID_PLATFORM_PRODUCTS_ID;

        public String getFINANCE_REPAY_TYPE() {
            return FINANCE_REPAY_TYPE;
        }

        public void setFINANCE_REPAY_TYPE(String FINANCE_REPAY_TYPE) {
            this.FINANCE_REPAY_TYPE = FINANCE_REPAY_TYPE;
        }

        public double getFINANCE_AMOUNT() {
            return FINANCE_AMOUNT;
        }

        public void setFINANCE_AMOUNT(double FINANCE_AMOUNT) {
            this.FINANCE_AMOUNT = FINANCE_AMOUNT;
        }

        public String getFINANCE_PERIOD() {
            return FINANCE_PERIOD;
        }

        public void setFINANCE_PERIOD(String FINANCE_PERIOD) {
            this.FINANCE_PERIOD = FINANCE_PERIOD;
        }

        public String getPRODUCTS_STATUS() {
            return PRODUCTS_STATUS;
        }

        public void setPRODUCTS_STATUS(String PRODUCTS_STATUS) {
            this.PRODUCTS_STATUS = PRODUCTS_STATUS;
        }

        public String getMOBILE() {
            return MOBILE;
        }

        public void setMOBILE(String MOBILE) {
            this.MOBILE = MOBILE;
        }

        public String getNOVICE_FLG() {
            return NOVICE_FLG;
        }

        public void setNOVICE_FLG(String NOVICE_FLG) {
            this.NOVICE_FLG = NOVICE_FLG;
        }

        public String getFINANCE_FULL_FLG() {
            return FINANCE_FULL_FLG;
        }

        public void setFINANCE_FULL_FLG(String FINANCE_FULL_FLG) {
            this.FINANCE_FULL_FLG = FINANCE_FULL_FLG;
        }

        public String getPRODUCTS_TITLE() {
            return PRODUCTS_TITLE;
        }

        public void setPRODUCTS_TITLE(String PRODUCTS_TITLE) {
            this.PRODUCTS_TITLE = PRODUCTS_TITLE;
        }

        public String getFINANCE_AMOUNT_WAIT() {
            return FINANCE_AMOUNT_WAIT;
        }

        public void setFINANCE_AMOUNT_WAIT(String FINANCE_AMOUNT_WAIT) {
            this.FINANCE_AMOUNT_WAIT = FINANCE_AMOUNT_WAIT;
        }

        public int getFINANCE_AMOUNT_SCALE() {
            return FINANCE_AMOUNT_SCALE;
        }

        public void setFINANCE_AMOUNT_SCALE(int FINANCE_AMOUNT_SCALE) {
            this.FINANCE_AMOUNT_SCALE = FINANCE_AMOUNT_SCALE;
        }

        public String getFINANCE_INTEREST_RATE() {
            return FINANCE_INTEREST_RATE;
        }

        public void setFINANCE_INTEREST_RATE(String FINANCE_INTEREST_RATE) {
            this.FINANCE_INTEREST_RATE = FINANCE_INTEREST_RATE;
        }

        public String getOID_PLATFORM_PRODUCTS_ID() {
            return OID_PLATFORM_PRODUCTS_ID;
        }

        public void setOID_PLATFORM_PRODUCTS_ID(String OID_PLATFORM_PRODUCTS_ID) {
            this.OID_PLATFORM_PRODUCTS_ID = OID_PLATFORM_PRODUCTS_ID;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {

        }

        @Override
        public int getItemType() {
            return 0;
        }
    }
}
