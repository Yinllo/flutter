package com.desjf.dsjr.adapter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.desjf.dsjr.R;

import java.util.List;

/**
 * @Author YinL
 * @Date 2018/3/15 0015
 * @Describe  我要出借界面 两个下拉框适配器
 */

public class SelectPopupAdapter extends BaseAdapter {
    private String flg="0";
    private Context context;
    private int tag;//标志位来判断用哪个布局文件
    private int i=-1;
    private  List list;
    View v;
    //当前的位置
    public int getI() {
        return i;
    }
    public void setI(int i) {
        this.i = i;
    }

    //是否被选中的状态：flg.equals("1")代表被选中，则将图片设置为被选中的图片
    public String getFlg() {return flg;}
    public void setFlg(String flg) {this.flg = flg;}


    public SelectPopupAdapter(@Nullable List<String> data, Context context,int tag) {
        this.context=context;
        this.list=data;
        this.tag=tag;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater layoutInflater=LayoutInflater.from(context);

        if(tag==1){
            v= layoutInflater.inflate(R.layout.popup_text_item_one, null);
        }else{
            v= layoutInflater.inflate(R.layout.popup_text_item_two, null);
        }
        TextView tv=v.findViewById(R.id.tv_describe);
        tv.setText(list.get(position).toString());

        if (this.i ==position){
            if (flg.equals("1")){
                tv.setTextColor(context.getResources().getColor(R.color.number));
            }else{
                tv.setTextColor(context.getResources().getColor(R.color.nfont));
            }
        }else{
            tv.setTextColor(context.getResources().getColor(R.color.nfont));
        }

        return v;
    }
}
