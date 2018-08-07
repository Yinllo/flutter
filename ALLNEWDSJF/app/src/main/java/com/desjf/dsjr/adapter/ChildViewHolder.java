package com.desjf.dsjr.adapter;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.desjf.dsjr.R;
import com.desjf.dsjr.bean.NoticeBean;


/**
 * 下拉TextView
 * 子布局ViewHolder
 */

public class ChildViewHolder extends BaseViewHolder {

    private Context mContext;
    private View view;
    private TextView childTitleTxt;
    private TextView  childContentTxt;
    private TextView childAuthorTxt;
//    private TextView  childDataTxt;


    public ChildViewHolder(Context context, View itemView) {
        super(itemView);
        this.mContext = context;
        this.view = itemView;
    }

    public void bindView(final NoticeBean dataBean, final int pos){

        childTitleTxt = view.findViewById(R.id.tv_title);
        childContentTxt =view.findViewById(R.id.tv_content);
        childAuthorTxt= view.findViewById(R.id.tv_author);
//        childDataTxt=view.findViewById(R.id.tv_data);

        childTitleTxt.setText(dataBean.getChildTitleTxt());
        childContentTxt.setText(dataBean.getChildContentTxt());
        childAuthorTxt.setText(dataBean.getChildAuthorTxt());
//        childDataTxt.setText(dataBean.getChildDataTxt());


    }
}
