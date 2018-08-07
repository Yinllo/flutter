package com.desjf.dsjr.adapter;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.TextAppearanceSpan;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.desjf.dsjr.R;
import com.desjf.dsjr.bean.ShouyeListBean;
import com.desjf.dsjr.model.AssignmentModel;

import java.util.List;

/**
 * Created by Administrator on 2017/7/18 0018.
 */

public class AssignmentAdapter extends BaseQuickAdapter<AssignmentModel.RESULTLISTBean,BaseViewHolder> {
    private Context context;
    public AssignmentAdapter( @Nullable List<AssignmentModel.RESULTLISTBean> data,Context context) {
        super(R.layout.item_shouye_list, data);
        this.context=context;
    }

    @Override
    protected void convert(BaseViewHolder helper, AssignmentModel.RESULTLISTBean item) {
        //图标
        ImageView icon = helper.getView(R.id.iv_shouye_icon);
        //题目
        TextView title = helper.getView(R.id.tv_shouye_list_title);
        //标类型
        ImageView novice = helper.getView(R.id.iv_shouye_type);
        //预期年化
        TextView yqnh = helper.getView(R.id.tv_shouye_yqnh);
        //预期年化文字
        TextView yqnhtext = helper.getView(R.id.tv_shouye_yqnh_text);
        //万份收益元
        TextView wfsyy = helper.getView(R.id.tv_shouye_wfsyy);
        //万份收益、借款期限
        TextView wfsy = helper.getView(R.id.tv_shouye_wfsy);
        //立即转入
        TextView ljzr = helper.getView(R.id.tv_shouye_ljzr);
        //进度条
        ProgressBar progressbar = helper.getView(R.id.pb_shouye);
        //最底布局
        LinearLayout ll_under = helper.getView(R.id.ll_under);
        //剩余金额
        TextView syje = helper.getView(R.id.tv_shouye_jine);
        //百分比
        TextView bfb = helper.getView(R.id.tv_shouye_bfb);
        TextView abc = helper.getView(R.id.tv_abc);
        TextView abc_ll = helper.getView(R.id.tv_abc_ll);
        TextView tzql = helper.getView(R.id.tv_touzijiangli);
        TextView heheh = helper.getView(R.id.tv_shouye_yqnh_bfb);

        tzql.setVisibility(View.GONE);
        heheh.setVisibility(View.GONE);
        icon.setImageResource(R.mipmap.zhaizhuan);
        title.setText(item.getTRANSFERCONTRACTID());
        abc.setVisibility(View.VISIBLE);
        abc_ll.setVisibility(View.VISIBLE);
        abc_ll.setText(item.getDISCOUNTSCALE());

        String str_yqnh = item.getFINANCEINTERESTRATE()+"%";
        SpannableString ss = new SpannableString(str_yqnh);
        ss.setSpan(new TextAppearanceSpan(context,R.style.style4),str_yqnh.length()-4,str_yqnh.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        ss.setSpan(new TextAppearanceSpan(context,R.style.style3),0,str_yqnh.length()-4, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        yqnh.setText(ss, TextView.BufferType.SPANNABLE);
        yqnhtext.setText("原标年化");

        String str_wfs = item.getTRANSFERPERIOD()+item.getINTERESTRATETYPE();
        SpannableString ss_wfs = new SpannableString(str_wfs);
        ss_wfs.setSpan(new TextAppearanceSpan(context,R.style.style4),item.getTRANSFERPERIOD().length(),str_wfs.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        ss_wfs.setSpan(new TextAppearanceSpan(context,R.style.style3),0,item.getTRANSFERPERIOD().length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        wfsyy.setText(ss_wfs, TextView.BufferType.SPANNABLE);
        wfsy.setText("剩余期限");
        if (item.getTRANSFERCAPITALWAIT().equals("0")){
            syje.setText("已完成");
        }else{
            syje.setText(item.getTRANSFERCAPITALWAIT());
        }
        if (item.getTRANSFERSTATUS().equals("0")){
            //转让中
            ljzr.setText("立即购买");
            ljzr.setBackgroundResource(R.drawable.textview_cricle_btn);
        }else{
            //转让成功
            ljzr.setText("转让成功");
            ljzr.setBackgroundResource(R.drawable.textforbtn_gray);
        }
        bfb.setText(item.getTRANSFERCAPITALSCALE());
        String tenderawardscale = item.getTRANSFERCAPITALSCALE();
        if (tenderawardscale.equals("0%")){
            progressbar.setProgress(0);
        }else{
            String str = tenderawardscale.substring(0,tenderawardscale.length()-4);
            int i = Integer.parseInt(str);
            progressbar.setProgress(i);
        }
        novice.setVisibility(View.GONE);

    }
}
