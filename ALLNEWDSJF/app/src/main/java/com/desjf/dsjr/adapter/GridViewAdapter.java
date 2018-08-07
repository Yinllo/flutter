package com.desjf.dsjr.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.desjf.dsjr.R;

import java.util.List;


/**
 * 添加上传图片适配器     yl
 */

public class GridViewAdapter extends android.widget.BaseAdapter {
    
    private Context mContext;
    private List<String> mList;
    private LayoutInflater inflater;
    int p;

    public GridViewAdapter(Context mContext, List<String> mList) {
        this.mContext = mContext;
        this.mList = mList;
        inflater = LayoutInflater.from(mContext);
    }

    @Override
    public int getCount() {
        //return mList.size() + 1;//因为最后多了一个添加图片的ImageView 
        int count = mList == null ? 1 : mList.size() + 1;
        if (count > 5) {  //最多上传五张
            return mList.size();
        } else {
            return count;
        }
    }

    @Override
    public Object getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
    

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        convertView = inflater.inflate(R.layout.grid_item, parent,false);
        ImageView clear = (ImageView) convertView.findViewById(R.id.clear);
        if(mList.size()>0) {

            clear.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    p=position;
                    mList.remove(p);
                    notifyDataSetChanged();
                }
            });
        }
        ImageView iv = (ImageView) convertView.findViewById(R.id.pic_iv);
        if (position < mList.size()) {
            clear.setVisibility(View.VISIBLE);
            //代表+号之前的需要正常显示图片
            String picUrl = mList.get(position); //图片路径
            Glide.with(mContext).load(picUrl).into(iv);
        } else {
            iv.setImageResource(R.mipmap.tianjia);//最后一个显示加号图片
        }
        return convertView;
    }
}  
