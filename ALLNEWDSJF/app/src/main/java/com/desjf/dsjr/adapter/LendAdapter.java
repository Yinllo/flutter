package com.desjf.dsjr.adapter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.util.Log;
import android.view.View;
import android.view.ViewStub;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.desjf.dsjr.R;
import com.desjf.dsjr.bean.SubjectBean;
import com.desjf.dsjr.widget.CustomProgress;

import java.util.List;

/**
 * @Author YinL
 * @Date 2018/3/14 0014
 * @Describe  我要出借列表适配器
 */

public class LendAdapter extends BaseMultiItemQuickAdapter<SubjectBean.ListBean,BaseViewHolder> implements BaseQuickAdapter.SpanSizeLookup{

    Context context;
    ViewStub viewStub;
    boolean isInflate=false;


    public LendAdapter(@Nullable List<SubjectBean.ListBean> data, Context context) {
        super(data);
        setSpanSizeLookup(this);
        this.context = context;
        addItemType(0, R.layout.invest_recyclerview_item);
    }

    @Override
    protected void convert(BaseViewHolder helper, SubjectBean.ListBean subjectBean) {
        //      helper.setText(R.id.tv_money,"8888888888");  标准使用方法
        // helper.setTextColor(R.id.tv_money,context.getResources().getColor(R.color.font));
        TextView tvOne=helper.getView(R.id.tv_advertisement_one);
        TextView tvTwo=helper.getView(R.id.tv_advertisement_two);
        //标题
        helper.setText(R.id.tv_product_title,subjectBean.getPRODUCTS_TITLE());
        //广告位
        if(("").equals("")) {
            //隐藏广告位
            tvOne.setVisibility(View.GONE);
        }else {
            helper.setVisible(R.id.tv_advertisement_one, true);
            helper.setText(R.id.tv_advertisement_one,"");
        }
        if(("").equals("")) {
            tvOne.setVisibility(View.GONE);
        }else{
            helper.setVisible(R.id.tv_advertisement_two, true);
            helper.setText(R.id.tv_advertisement_two, "");
        }
        //利率
        helper.setText(R.id.tv_invest_rate,subjectBean.getFINANCE_INTEREST_RATE());
        //加息
        if (subjectBean.getNOVICE_FLG().equals("1")) {
            helper.setText(R.id.tv_invest_ad,"新手标");
        } else {
        }
        //期限
        helper.setText(R.id.tv_invest_limit,subjectBean.getFINANCE_PERIOD()+"个月");
        //还款方式
        if(subjectBean.getFINANCE_REPAY_TYPE().equals("1")){
            helper.setText(R.id.tv_invest_limit_text,"等额本金");
        }else if(subjectBean.getFINANCE_REPAY_TYPE().equals("2")){
            helper.setText(R.id.tv_invest_limit_text,"等额本息");
        }else if(subjectBean.getFINANCE_REPAY_TYPE().equals("3")){
            helper.setText(R.id.tv_invest_limit_text,"到期还本付息");
        }else if(subjectBean.getFINANCE_REPAY_TYPE().equals("4")){
            helper.setText(R.id.tv_invest_limit_text,"按月付息");
        }else if(subjectBean.getFINANCE_REPAY_TYPE().equals("5")){
            helper.setText(R.id.tv_invest_limit_text,"按季付息,到期还本");
        }
        //剩余金额
        helper.setText(R.id.tv_invest_residue,"剩余"+subjectBean.getFINANCE_AMOUNT_WAIT()+"元");
        //总额
        helper.setText(R.id.tv_invest_allMoney,"总额"+subjectBean.getFINANCE_AMOUNT()+"万");
        //进度条
        CustomProgress progress=helper.getView(R.id.progress);
        progress.setmTotalProgress(100);
        progress.setProgress(Integer.parseInt(String.valueOf(subjectBean.getFINANCE_AMOUNT_SCALE())));
        if(subjectBean.getPRODUCTS_STATUS().equals("1")){
            progress.setText("投标中");
        }else if(subjectBean.getPRODUCTS_STATUS().equals("2")){
            progress.setText("还款中");
        }else if(subjectBean.getPRODUCTS_STATUS().equals("3")){
            progress.setText("已完成");
        }

        //如果是新手标则显示 新手专享图片
        if(subjectBean.getNOVICE_FLG().equals("1")){
           //新手标
//            viewStub=helper.getView(R.id.vs_img);
//            viewStub.setOnInflateListener(new ViewStub.OnInflateListener() {
//                @Override
//                public void onInflate(ViewStub stub, View inflated) {
//                    isInflate = true;
//                }
//            });
//            if(!isInflate){//如果没有填充则执行inflate操作
//                viewStub.inflate();
//            }
            ImageView imageView=helper.getView(R.id.vs_img);
            imageView.setVisibility(View.VISIBLE);
            Log.e("aaa","---==wwwwwwwwww----"+subjectBean.getPRODUCTS_TITLE()+"===---"+helper.getPosition());

        }


    }

    @Override
    public int getSpanSize(GridLayoutManager gridLayoutManager, int position) {
        return 0;
    }
}
