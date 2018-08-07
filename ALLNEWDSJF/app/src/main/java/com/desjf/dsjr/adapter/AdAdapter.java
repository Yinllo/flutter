package com.desjf.dsjr.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.desjf.dsjr.R;
import com.jude.rollviewpager.RollPagerView;
import com.jude.rollviewpager.adapter.LoopPagerAdapter;

import java.util.List;


/**
 * @Author YinL
 * @Describe 首页广告轮播适配器
 */

public class AdAdapter extends LoopPagerAdapter {
    private Context context;
    private List<String> stringList;
    public AdAdapter(RollPagerView viewPager, List<String> stringList, Context context) {
        super(viewPager);
        this.context = context;
        this.stringList=stringList;
    }

    @Override
    public View getView(ViewGroup container, int position) {
        ImageView view = new ImageView(context);
        view.setScaleType(ImageView.ScaleType.CENTER_CROP);
        view.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        Glide.with(context).load(stringList.get(position)).placeholder(R.mipmap.banner).error(R.mipmap.banner).into(view);
        return view;
    }

    @Override
    public int getRealCount() {
        return stringList.size();
    }
}
