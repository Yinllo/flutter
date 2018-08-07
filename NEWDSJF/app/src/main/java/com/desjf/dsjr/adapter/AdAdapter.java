package com.desjf.dsjr.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.signature.StringSignature;
import com.desjf.dsjr.R;
import com.jude.rollviewpager.RollPagerView;
import com.jude.rollviewpager.adapter.LoopPagerAdapter;

import java.util.List;


/**
 * Created by Administrator on 2017/7/17 0017.
 */

public class AdAdapter extends LoopPagerAdapter {
    private Context context;
    private List<String> stringList;
    public AdAdapter(RollPagerView viewPager,List<String> stringList,Context context) {
        super(viewPager);
        this.context = context;
        this.stringList=stringList;
    }

    @Override
    public View getView(ViewGroup container, int position) {
        ImageView view = new ImageView(context);
        view.setScaleType(ImageView.ScaleType.CENTER_CROP);
        view.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        Glide.with(context).load(stringList.get(position))
                //图片加上signature的标识,解决加载同一url时会加载缓存图片问题
                .signature(new StringSignature(String.valueOf(System.currentTimeMillis())))
                //加载出图片之前显示的占位图（默认加载图片）
                .placeholder(R.mipmap.banner)
                //加载错误时显示的占位图
                .error(R.mipmap.banner).into(view);
        return view;
    }

    @Override
    public int getRealCount() {
        return stringList.size();
    }
}
