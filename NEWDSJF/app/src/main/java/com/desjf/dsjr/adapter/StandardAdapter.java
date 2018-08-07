package com.desjf.dsjr.adapter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.style.TextAppearanceSpan;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.desjf.dsjr.R;
import com.desjf.dsjr.bean.TimerView;
import com.desjf.dsjr.model.StanderdModel;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by yuanchao.Zhao on 2017/7/18 0018.
 * 散标适配器
 */

public class StandardAdapter extends BaseQuickAdapter<StanderdModel.RESULTLISTBean,BaseViewHolder> {
    private Context context;
    private TimerView timerView;
    ProgressBar progress;
    TextView progesssValue;
    TextView type;
    int w;

    public StandardAdapter(@Nullable List<StanderdModel.RESULTLISTBean> data,Context context) {
        super(R.layout.new_item_shouye_list, data);
        this.context = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, StanderdModel.RESULTLISTBean item) {

//        helper.setText(R.id.tv_shouye_list_title,item.getName());
        RelativeLayout rl_daojishi = helper.getView(R.id.rl_daojishi);
        //图标
//        ImageView icon = helper.getView(R.id.iv_shouye_icon);

        //进度条布局
        LinearLayout lp=helper.getView(R.id.ll_progress);
        //题目
        TextView title = helper.getView(R.id.tv_shouye_list_title);
        //标类型
        type = helper.getView(R.id.tv_xinshoubiao);

        //预期年化
        TextView jiaxi = helper.getView(R.id.tv_jiaxi);

        //预期年化
        TextView yqnh = helper.getView(R.id.tv_shouye_yqnh);
        //万份收益元
        TextView wfsyy = helper.getView(R.id.tv_shouye_wfsyy);
        //万份收益、借款期限
        TextView wfsy = helper.getView(R.id.tv_shouye_wfsy);
        //立即转入
//        TextView ljzr = helper.getView(R.id.tv_shouye_ljzr);

        //总金额
        TextView allMoney=helper.getView(R.id.tv_shouye_je);
        //起投金额
//        TextView initMoney=helper.getView(R.id.tv_shouye_qtje);
        //满标人获十元奖励
        TextView desc=helper.getView(R.id.ten);
        //立即投资按钮
        Button button=helper.getView(R.id.button);

        //进度条
        progress = helper.getView(R.id.pb_shouye);
        //进度条百分比
        progesssValue = helper.getView(R.id.progesss_value1);
        //最底布局
        RelativeLayout ll_under = helper.getView(R.id.ll_under);
        //剩余金额
        TextView syje = helper.getView(R.id.tv_shouye_jine);
        //以前显示 百分比  现在显示 满标奖励
        TextView bfb = helper.getView(R.id.tv_shouye_bfb);
        TextView yqnhbfb = helper.getView(R.id.tv_shouye_yqnh_bfb);
        TextView tzjl = helper.getView(R.id.tv_touzijiangli);
        timerView = helper.getView(R.id.timerView_new);

//        TextView tv_xianzhi = helper.getView(R.id.tv_xinshoubiao);


//        if (item.getNOVICEFLG().toString().equals("0")) {
//            if (item.getCOMFLG().toString().equals("1")) {
//                //推荐表
//                type.setText("推荐");
//                type.setTextColor(context.getResources().getColor(R.color.type));
//                type.setBackgroundDrawable(context.getResources().getDrawable(R.drawable.text_view_type_border));
//            } else {
//
//            }
//        } else {
//            //新手标
//            type.setVisibility(View.VISIBLE);
////            novice.setVisibility(View.VISIBLE);
////            novice.setImageResource(R.mipmap.newsshare);
//
//        }


        /**
         * 1：新手标
         * 3:满标待审  已满标待计息
           4:立即投资  可投资
           5:还款中  已满标已计息
           6:已完成  已还本付息
         */
        if (item.getNOVICEFLG().toString().equals("0")) {
            //非新手标

            //如果广告位有 广告 则显示    否则隐藏

            if(("").equals(item.getADVERTISEWORD())){
                //推荐表
                type.setText("");
                type.setTextColor(context.getResources().getColor(R.color.white));
                type.setBackgroundDrawable(context.getResources().getDrawable(R.drawable.text_view_type_border_white));
            }else{
                type.setText(item.getADVERTISEWORD());
                type.setTextColor(context.getResources().getColor(R.color.skyblue));
                type.setBackgroundResource(R.drawable.text_view_type_border);
                type.setPadding(5,0,5,0);
            }



             //按钮显示状态
            if (item.getFINANCESTATUS().equals("3")) {
                button.setText("已满标");
                button.setBackgroundDrawable(context.getResources().getDrawable(R.drawable.textview_circle_btn_gray));
            }else if(item.getFINANCESTATUS().equals("4")){
                button.setText("立即投资");
                button.setBackgroundDrawable(context.getResources().getDrawable(R.drawable.textview_cricle_btn));
            }else if(item.getFINANCESTATUS().equals("5")){
                button.setText("收益中");
                button.setBackgroundDrawable(context.getResources().getDrawable(R.drawable.textview_circle_btn_gray));
            }else{
                button.setText("已还清");
                button.setBackgroundDrawable(context.getResources().getDrawable(R.drawable.textview_circle_btn_gray));
            }

//            if (item.getCOMFLG().toString().equals("1")&&item.getFINANCESTATUS().equals("4")) {
//                //推荐表
//                type.setText("推荐标");
//                type.setTextColor(context.getResources().getColor(R.color.type));
//                type.setBackgroundDrawable(context.getResources().getDrawable(R.drawable.text_view_type_border));
//            } else {
//                if (item.getFINANCESTATUS().equals("3")) {
//                    type.setText("已满标待计息");
//                    type.setTextColor(context.getResources().getColor(R.color.font_gray));
//                    type.setBackgroundDrawable(context.getResources().getDrawable(R.drawable.text_view_type_border_gray));
//                }else if(item.getFINANCESTATUS().equals("4")){
//                    type.setText("可投资");
//                    type.setTextColor(context.getResources().getColor(R.color.type));
//                    type.setBackgroundDrawable(context.getResources().getDrawable(R.drawable.text_view_type_border));
//                }else if(item.getFINANCESTATUS().equals("5")){
//                    type.setText("已满标已计息");
//                    type.setTextColor(context.getResources().getColor(R.color.font_gray));
//                    type.setBackgroundDrawable(context.getResources().getDrawable(R.drawable.text_view_type_border_gray));
//                }else{
//                    type.setText("已还本付息");
//                    type.setTextColor(context.getResources().getColor(R.color.font_gray));
//                    type.setBackgroundDrawable(context.getResources().getDrawable(R.drawable.text_view_type_border_gray));
//                }
//            }
        } else {
            //新手标
//            type.setText("新手标，每人限投两万");
            type.setText(item.getADVERTISEWORD());//读取后台的广告位信息
            type.setTextColor(context.getResources().getColor(R.color.red));
            type.setBackgroundResource(R.drawable.text_view_type_border_red);
            type.setPadding(5,0,5,0);
        }


        //是否显示加息
        if(item.getAddRate().equals("0")){
            jiaxi.setVisibility(View.INVISIBLE);
        }else{
            jiaxi.setVisibility(View.VISIBLE);
            jiaxi.setText("+"+item.getAddRate()+"%");
        }


        //        icon.setImageResource(R.mipmap.sanbiao);
        //标的名称
        title.setText(item.getPRODUCTSTITLE());
        //标的预期年化率
//        String str_yqnh = item.getFINANCEINTERESTRATE1();
//        SpannableString ss = new SpannableString(str_yqnh);
//        ss.setSpan(new TextAppearanceSpan(context, R.style.style4), str_yqnh.length() - 1, str_yqnh.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
//        ss.setSpan(new TextAppearanceSpan(context, R.style.style3), 0, str_yqnh.length() - 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
//        yqnh.setText(ss, TextView.BufferType.SPANNABLE);
        yqnh.setText(item.getFINANCEINTERESTRATE1());


        //标的借款期限
        String str_wfs = item.getFINANCEPERIOD() + item.getINTERESTRATETYPE();
        SpannableString ss_wfs = new SpannableString(str_wfs);
        ss_wfs.setSpan(new TextAppearanceSpan(context, R.style.style4), item.getFINANCEPERIOD().length(), str_wfs.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        ss_wfs.setSpan(new TextAppearanceSpan(context, R.style.style3), 0, item.getFINANCEPERIOD().length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        wfsyy.setText(ss_wfs, TextView.BufferType.SPANNABLE);
         //标的剩余可投金额
        //剩余金额
        String residue=(item.getFINANCEAMOUNTWAIT().substring(2,
                item.getFINANCEAMOUNTWAIT().length()-1));
        if(residue.equals("完")){
            syje.setText("0");
        }else{
            syje.setText(residue);
        }

        //标的总金额
        allMoney.setText(item.getFINANCEAMOUNT()+"万");
        //标的起投金额
//        initMoney.setText("200");
        //设置当前标的是否满标（满标奖励）
        desc.setText(item.getFULLTIP());
        //百分比设置
//        bfb.setText(item.getFINANCEAMOUNTSCALE());

        String tenderawardscale = item.getFINANCEAMOUNTSCALE();
        if (tenderawardscale.equals("0%")) {
            progress.setProgress(0);
            progesssValue.setText("0%");
            setPos();
        } else {
            String str = tenderawardscale.substring(0, tenderawardscale.indexOf("."));
            int i = Integer.parseInt(str);
            if(i<=3){
                progress.setProgress(i);
                progesssValue.setBackgroundResource(R.drawable.p_left);
                progesssValue.setText(i+"%");
            }else if (3<i&&i<93){
                progress.setProgress(i);
                progesssValue.setBackgroundResource(R.drawable.p_middle);
                progesssValue.setText(i+"%");
            }else{
                progress.setProgress(i);
                progesssValue.setBackgroundResource(R.drawable.p_right);
                progesssValue.setText(i+"%");
            }
            setPos();
        }



        if (item.getPRODUCTSSTATUS().toString().equals("未开始")){
            //即将发布
//             ljzr.setVisibility(View.GONE);
            rl_daojishi.setVisibility(View.VISIBLE);
            String financestartdate = item.getFINANCESTARTDATE();
            String systemdate = item.getSYSTEMDATE();
//             getData(systemdate,financestartdate);
            setTimerView(systemdate, financestartdate);
        }else if (item.getPRODUCTSSTATUS().toString().equals("立即投资")){
            //投资中
            button.setText("立即投资");
            button.setBackgroundDrawable(context.getResources().getDrawable(R.drawable.textview_cricle_btn));
//             ljzr.setText("立即投资");
//             ljzr.setBackgroundResource(R.drawable.textview_cricle_btn);
//             ljzr.setVisibility(View.VISIBLE);
            rl_daojishi.setVisibility(View.GONE);
        }else if (item.getPRODUCTSSTATUS().toString().equals("满标待审")){
            //还款中
            button.setText("已满标");
            button.setBackgroundDrawable(context.getResources().getDrawable(R.drawable.textview_circle_btn_gray));
//             ljzr.setText("满标待审");
//             ljzr.setBackgroundResource(R.drawable.textview_circle_btn_gray);
//             ljzr.setVisibility(View.VISIBLE);
             rl_daojishi.setVisibility(View.GONE);
        }else if (item.getPRODUCTSSTATUS().toString().equals("已完成")){
            //已完成
            button.setText("已还清");
            button.setBackgroundDrawable(context.getResources().getDrawable(R.drawable.textview_circle_btn_gray));
//             ljzr.setText("已完成");
//             ljzr.setBackgroundResource(R.drawable.textview_circle_btn_gray);
        }else if (item.getPRODUCTSSTATUS().toString().equals("还款中")){

            button.setText("收益中");
            button.setBackgroundDrawable(context.getResources().getDrawable(R.drawable.textview_circle_btn_gray));
//             ljzr.setText("还款中");
//             ljzr.setBackgroundResource(R.drawable.textview_circle_btn_gray);
//             ljzr.setVisibility(View.VISIBLE);
            rl_daojishi.setVisibility(View.GONE);
        }
        if (item.getTENDERAWARDFLG().equals("0")){
            yqnhbfb.setVisibility(View.GONE);
            tzjl.setVisibility(View.GONE);
        }else{
            if (item.getTENDERAWARDSCALE().toString().equals("0.00%")){
                yqnhbfb.setVisibility(View.GONE);
                tzjl.setVisibility(View.GONE);
            }else{
                yqnhbfb.setVisibility(View.VISIBLE);
                tzjl.setVisibility(View.VISIBLE);
                yqnhbfb.setText(item.getTENDERAWARDSCALE().toString());
            }

        }

    }

    /**
     * 设置进度显示在对应的位置
     */

    private void setPosWay1() {
        progesssValue.post(new Runnable() {
            @Override
            public void run() {
                setPos();
            }
        });
    }
    public void setPos(){
        //获得屏幕宽度
        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics dm = new DisplayMetrics();// 创建了一张白纸
        windowManager.getDefaultDisplay().getMetrics(dm);// 给白纸设置宽高

        //获得指示器的父布局
        ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) progesssValue.getLayoutParams();
        int pro = progress.getProgress();
        //获得矩形指示器（TextView）的宽度
        int ws = View.MeasureSpec.makeMeasureSpec(0,
                View.MeasureSpec.UNSPECIFIED);
        int h = View.MeasureSpec.makeMeasureSpec(0,
                View.MeasureSpec.UNSPECIFIED);
        progesssValue.measure(ws, h);
        int tW = progesssValue.getMeasuredWidth();

        if( progress.getProgress()==100){
            w =windowManager.getDefaultDisplay().getWidth()-(tW-5);
        }else if(progress.getProgress()<=7){
            w =windowManager.getDefaultDisplay().getWidth()-tW;
        }else if(progress.getProgress()>=93){
            w =windowManager.getDefaultDisplay().getWidth()-(tW+20 );
        }
        else{
            w =windowManager.getDefaultDisplay().getWidth()-tW;
        }
        //计算偏移量
        if (w * pro / 100 + tW * 0.3 > w) {
            params.leftMargin = (int) (w - tW * 1.1);
        }
        else if (w * pro / 100 < tW * 0.7) {
//            if (pro==0) {
                params.leftMargin = 0;
//            } else {
//                params.leftMargin = (int) (( tW * 0.7)-(w * pro / 100) );
//            }
        }
        else {
            params.leftMargin = (int) (w * pro / 100 - tW * 0.7);
        }
        progesssValue.setLayoutParams(params);
    }




    private String getDistanceTime(long diff) {
        long day = 0;
        long hour = 0;
        long min = 0;
        long sec = 0;

        day = diff / (24 * 60 * 60 * 1000);
        hour = ((diff / (60 * 60 * 1000)) - day * 24);
        min = ((diff / (60 * 1000)) - day * 24 * 60 - hour * 60);
        sec = (diff / 1000 - day * 24 * 60 * 60 - hour * 60 * 60 - min * 60);

//        if (day == 0 && hour == 0 && min == 0 & sec == 0) {
//            initHoldingList(true, true);
//        }

        if (day != 0) {
            return day + "天" + hour + ":" + min + ":" + sec;
        } else {
            return hour + ":" + min + ":" + sec;
        }

    }
    // 时间相减返回差值
    private String getData(String begin, String end) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String data = "";
        try {
            Date d1 = df.parse(end);
            Date d2 = df.parse(begin);
            long diff = d1.getTime() - d2.getTime();// 这样得到的差值是微秒级别
            data = getDistanceTime(diff);

        } catch (Exception ignored) {
        }
        return data;
    }

    private void setTimerView(String system_date_now, String finance_start_date) {
        if (!TextUtils.isEmpty(system_date_now) && !TextUtils.isEmpty(finance_start_date)) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date curDate = null;//当前时间
            Date endDate = null;//结束时间
            Long compTime = 0L;//差值时间
            try {
                curDate = sdf.parse(system_date_now);
                endDate = sdf.parse(finance_start_date);
                compTime = endDate.getTime() - curDate.getTime();//获取时间差
                Log.e("TAG", "时间差: "+compTime );
                if (compTime > 0) {
                    timerView .start(compTime);
                }else{

                    timerView.updateShow(0);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
