package com.desjf.dsjr.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author YinL
 * @Date 2018/4/28 0028
 * @Describe  公告 下拉TextView 封装项
 */

public class EncapsulationItem {
    public static List<ItemData> lastBeanList = new ArrayList<>();
    //将展开的item添加到list中
    public static void addLastBeanData(ItemData beanData){
        lastBeanList.add(beanData);
    }
    //清空list
    public static void cleraListBeanData(){
        lastBeanList.clear();
    }
}
