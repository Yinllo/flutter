package com.desjf.dsjr.bean;

/**
 * @Author YinL
 * @Date 2018/4/28 0028
 * @Describe
 */

public class NoticeBean {

    public static final int PARENT_ITEM = 0;//父布局
    public static final int CHILD_ITEM = 1;//子布局

    private int type;// 显示类型
    private boolean isExpand;// 是否展开
    private boolean isRead;//是否阅读
    private NoticeBean childBean;

    private String ID;//消息id
    private String parentTitleTxt;
    private String parentDataTxt;
    private String childTitleTxt;
    private String childContentTxt;
    private String childAuthorTxt;
    private String childDataTxt;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public boolean isExpand() {
        return isExpand;
    }

    public void setExpand(boolean expand) {
        isExpand = expand;
    }

    public boolean isRead() {
        return isRead;
    }

    public void setRead(boolean read) {
        isRead = read;
    }

    public NoticeBean getChildBean() {
        return childBean;
    }

    public void setChildBean(NoticeBean childBean) {
        this.childBean = childBean;
    }

    public String getID() {
        return ID == null ? "" : ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getParentTitleTxt() {
        return parentTitleTxt == null ? "" : parentTitleTxt;
    }

    public void setParentTitleTxt(String parentTitleTxt) {
        this.parentTitleTxt = parentTitleTxt;
    }

    public String getParentDataTxt() {
        return parentDataTxt == null ? "" : parentDataTxt;
    }

    public void setParentDataTxt(String parentDataTxt) {
        this.parentDataTxt = parentDataTxt;
    }

    public String getChildTitleTxt() {
        return childTitleTxt == null ? "" : childTitleTxt;
    }

    public void setChildTitleTxt(String childTitleTxt) {
        this.childTitleTxt = childTitleTxt;
    }

    public String getChildContentTxt() {
        return childContentTxt == null ? "" : childContentTxt;
    }

    public void setChildContentTxt(String childContentTxt) {
        this.childContentTxt = childContentTxt;
    }

    public String getChildAuthorTxt() {
        return childAuthorTxt == null ? "德晟金服运营团队" : childAuthorTxt;
    }

    public void setChildAuthorTxt(String childAuthorTxt) {
        this.childAuthorTxt = childAuthorTxt;
    }

    public String getChildDataTxt() {
        return childDataTxt == null ? "" : childDataTxt;
    }

    public void setChildDataTxt(String childDataTxt) {
        this.childDataTxt = childDataTxt;
    }
}
