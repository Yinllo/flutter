package com.desjf.dsjr.adapter;

import android.content.Context;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.style.TextAppearanceSpan;
import android.util.DisplayMetrics;
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
import com.desjf.dsjr.model.HomeModel;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2017/7/17 0017.
 */

public class ShouyeAdapter extends BaseQuickAdapter<Object,BaseViewHolder> {
    private TimerView timerView;
    private Context context;
    ProgressBar progress ;
    Button button;
    int w;//屏幕宽度
    //进度条百分比
    TextView progesssValue;
    public ShouyeAdapter(List<Object> data,Context context) {
        super(R.layout.new_item_shouye_list,data);
        this.context = context;
    }
    public int getItemCount() {
        return mData.size() + getFooterViewsCount();
    }
    @Override
    protected void convert(BaseViewHolder helper, Object item) {
        //实例化各个控件
        RelativeLayout rl_daojishi = helper.getView(R.id.rl_daojishi);
        //分割线
        View fenge=helper.getView(R.id.view_fenge);

        //图标
//        ImageView icon = helper.getView(R.id.iv_shouye_icon);

        //预期年化
        TextView jiaxi = helper.getView(R.id.tv_jiaxi);


        //进度条布局
        LinearLayout lp=helper.getView(R.id.ll_progress);
        //题目
        TextView title = helper.getView(R.id.tv_shouye_list_title);
        //标类型
//        ImageView novice = helper.getView(R.id.iv_shouye_type);
        //预期年化
        TextView yqnh = helper.getView(R.id.tv_shouye_yqnh);
        //万份收益元
        TextView wfsyy = helper.getView(R.id.tv_shouye_wfsyy);
        //万份收益元
        TextView all = helper.getView(R.id.tv_shouye_je);
        //万份收益、借款期限
        TextView wfsy = helper.getView(R.id.tv_shouye_wfsy);
        //满标人获十元奖励
        TextView desc=helper.getView(R.id.ten);
        //立即投资按钮
        button=helper.getView(R.id.button);

        //立即转入
//        TextView ljzr = helper.getView(R.id.tv_shouye_ljzr);

        //进度条
        progress = helper.getView(R.id.pb_shouye);
        //进度条百分比
        progesssValue = helper.getView(R.id.progesss_value1);
        //最底布局
        RelativeLayout ll_under = helper.getView(R.id.ll_under);
        //剩余金额
        TextView syje = helper.getView(R.id.tv_shouye_jine);
        //百分比
        TextView bfb = helper.getView(R.id.tv_shouye_bfb);
        TextView yqnhbfb = helper.getView(R.id.tv_shouye_yqnh_bfb);
        TextView tzjl = helper.getView(R.id.tv_touzijiangli);

        timerView = helper.getView(R.id.timerView_new);
        TextView tv_xianzhi = helper.getView(R.id.tv_xinshoubiao);


//        if (item instanceof HomeModel.CURRENTLISTBean){
//            icon.setImageResource(R.mipmap.activity_pig);
//            title.setText(((HomeModel.CURRENTLISTBean) item).getPRODUCTS_TITLE());
//            novice.setVisibility(View.INVISIBLE);
//            yqnh.setText(((HomeModel.CURRENTLISTBean) item).getINTEREST_RATE_TYPE());
//            wfsyy.setText(((HomeModel.CURRENTLISTBean) item).getMILLION_INCOME());
//            wfsy.setText("万份收益");
//            ljzr.setText("立即转入");
//            progressbar.setVisibility(View.GONE);
//            ll_under.setVisibility(View.GONE);
//
//        }else if (item instanceof HomeModel.SCATTEREDLISTBean){
//            icon.setImageResource(R.mipmap.sanbiao);
//            title.setText(((HomeModel.SCATTEREDLISTBean) item).getPRODUCTS_TITLE());
//            if (((HomeModel.SCATTEREDLISTBean) item).getNOVICE_FLG().toString().equals("0")){
//                //非新手标
//                novice.setImageResource(R.mipmap.hottuijian);
//            }else{
//                novice.setImageResource(R.mipmap.newsshare);
//            }
//            yqnh.setText(((HomeModel.SCATTEREDLISTBean) item).getFINANCE_INTEREST_RATE());
//            wfsyy.setText(((HomeModel.SCATTEREDLISTBean) item).getFINANCE_VALID_DAY());
//            wfsy.setText("借款期限");
//            ljzr.setText("立即投资");
//            progressbar.setVisibility(View.VISIBLE);
//            progressbar.setMax(100);
//            progressbar.setProgress(50);
//            ll_under.setVisibility(View.VISIBLE);
//
//        }else if (item instanceof HomeModel.BORROWLISTBean){
//            icon.setImageResource(R.mipmap.licai_plan);
//            title.setText(((HomeModel.BORROWLISTBean) item).getBORROW_TITLE());
//            novice.setVisibility(View.INVISIBLE);
//            yqnh.setText(((HomeModel.BORROWLISTBean) item).getBORROW_RATE());
//            wfsyy.setText(((HomeModel.BORROWLISTBean) item).getBORROW_PROID());
//            wfsy.setText("借款期限");
//            ljzr.setText("立即转入");
//            progressbar.setVisibility(View.VISIBLE);
//            ll_under.setVisibility(View.VISIBLE);
//        }




        if (item instanceof HomeModel.SCATTEREDLISTBean){
            if (((HomeModel.SCATTEREDLISTBean) item).getNOVICE_FLG().toString().equals("1")){
                //新手标
//                tv_xianzhi.setText("新手标，每人限投两万");
                tv_xianzhi.setText(((HomeModel.SCATTEREDLISTBean) item).getADVERTISE_WORD());
                tv_xianzhi.setTextColor(context.getResources().getColor(R.color.red));
                tv_xianzhi.setBackgroundResource(R.drawable.text_view_type_border_red);
                tv_xianzhi.setPadding(5,0,5,0);

//                novice.setImageResource(R.mipmap.newsshare);
            }else{
                //非新手标
//                if (((HomeModel.SCATTEREDLISTBean) item).getCOM_FLG().toString().equals("1")){
                    //推荐表
                //广告位设置
                if(("").equals(((HomeModel.SCATTEREDLISTBean) item).getADVERTISE_WORD())) {
                    tv_xianzhi.setText("");
                    tv_xianzhi.setTextColor(context.getResources().getColor(R.color.white));
                    tv_xianzhi.setBackgroundDrawable(context.getResources().getDrawable(R.drawable.text_view_type_border_white));
                }else{
                    tv_xianzhi.setText(((HomeModel.SCATTEREDLISTBean) item).getADVERTISE_WORD());
                    tv_xianzhi.setTextColor(context.getResources().getColor(R.color.skyblue));
                    tv_xianzhi.setBackgroundResource(R.drawable.text_view_type_border);
                    tv_xianzhi.setPadding(5,0,5,0);
                }

            }

            //是否显示加息
            if(((HomeModel.SCATTEREDLISTBean) item).getAddRate().equals("0")){
                jiaxi.setVisibility(View.INVISIBLE);
            }else{
                jiaxi.setVisibility(View.VISIBLE);
                jiaxi.setText("+"+((HomeModel.SCATTEREDLISTBean) item).getAddRate()+"%");
            }



            //            icon.setImageResource(R.mipmap.sanbiao);
            title.setText(((HomeModel.SCATTEREDLISTBean) item).getPRODUCTS_TITLE());//标题
            //设置当前标的是否满标（满标奖励）
            desc.setText(((HomeModel.SCATTEREDLISTBean) item).getFULL_TIP());
            //总金额
            all.setText(((HomeModel.SCATTEREDLISTBean) item).getFINANCE_AMOUNT()+"万");

            String str_yqnh = ((HomeModel.SCATTEREDLISTBean) item).getFINANCE_INTEREST_RATE1();
//            SpannableString ss = new SpannableString(str_yqnh);
//            ss.setSpan(new TextAppearanceSpan(context,R.style.style4),str_yqnh.length()-1,str_yqnh.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
//            ss.setSpan(new TextAppearanceSpan(context,R.style.style3),0,str_yqnh.length()-1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
//            yqnh.setText(ss, TextView.BufferType.SPANNABLE);
            yqnh.setText(str_yqnh);

            String str_wfs = ((HomeModel.SCATTEREDLISTBean) item).getFINANCE_PERIOD()+((HomeModel.SCATTEREDLISTBean) item).getINTEREST_RATE_TYPE();
            SpannableString ss_wfs = new SpannableString(str_wfs);
            ss_wfs.setSpan(new TextAppearanceSpan(context,R.style.style4),((HomeModel.SCATTEREDLISTBean) item).getFINANCE_PERIOD().length(),str_wfs.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            ss_wfs.setSpan(new TextAppearanceSpan(context,R.style.style3),0,((HomeModel.SCATTEREDLISTBean) item).getFINANCE_PERIOD().length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            wfsyy.setText(ss_wfs, TextView.BufferType.SPANNABLE);

            wfsy.setText("借款期限");
            //剩余金额
            String residue=((HomeModel.SCATTEREDLISTBean) item).getFINANCE_AMOUNT_WAIT().substring(2,
                    ((HomeModel.SCATTEREDLISTBean) item).getFINANCE_AMOUNT_WAIT().length()-1);
            if(residue.equals("完")){
                syje.setText("0");
            }else{
                syje.setText(residue);
            }



//            bfb.setText(((HomeModel.SCATTEREDLISTBean) item).getFINANCE_AMOUNT_SCALE());
            String tenderawardscale =((HomeModel.SCATTEREDLISTBean) item).getFINANCE_AMOUNT_SCALE();
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

//            String tenderawardscale = ((HomeModel.SCATTEREDLISTBean) item).getFINANCE_AMOUNT_SCALE();
//            if (tenderawardscale.equals("0%")){
//                progressbar.setProgress(0);
//            }else{
//                String str = tenderawardscale.substring(0,tenderawardscale.length()-4);
//                int i = Integer.parseInt(str);
//                progressbar.setProgress(i);
//            }
            if (((HomeModel.SCATTEREDLISTBean) item).getPRODUCTS_STATUS().toString().equals("未开始")){
                //即将发布
//                ljzr.setVisibility(View.GONE);
                rl_daojishi.setVisibility(View.VISIBLE);
                String financestartdate = ((HomeModel.SCATTEREDLISTBean) item).getFINANCE_START_DATE();
                String systemdate = ((HomeModel.SCATTEREDLISTBean) item).getSYSTEM_DATE();
                setTimerView(systemdate, financestartdate);
            }else if (((HomeModel.SCATTEREDLISTBean) item).getPRODUCTS_STATUS().toString().equals("立即投资")){
                //投资中
                button.setText("立即投资");
                button.setBackgroundResource(R.drawable.textview_cricle_btn);
            }else if (((HomeModel.SCATTEREDLISTBean) item).getPRODUCTS_STATUS().toString().equals("还款中")){
                //还款中
                button.setText("收益中");
                button.setBackgroundResource(R.drawable.textview_circle_btn_gray);
            }else if (((HomeModel.SCATTEREDLISTBean) item).getPRODUCTS_STATUS().toString().equals("还款完了")){
                //已完成
                button.setText("已还清");
                button.setBackgroundResource(R.drawable.textview_circle_btn_gray);
            }else if (((HomeModel.SCATTEREDLISTBean) item).getPRODUCTS_STATUS().toString().equals("满标待审")){
                button.setText("已满标");
                button.setBackgroundResource(R.drawable.textview_circle_btn_gray);
            }
            if (((HomeModel.SCATTEREDLISTBean) item).getTENDER_AWARD_FLG().equals("0")){
                yqnhbfb.setVisibility(View.GONE);
                tzjl.setVisibility(View.GONE);
            }else{
                if (((HomeModel.SCATTEREDLISTBean) item).getTENDER_AWARD_SCALE().toString().equals("0.00%")){
                    yqnhbfb.setVisibility(View.GONE);
                    tzjl.setVisibility(View.GONE);
                }else{
                    yqnhbfb.setVisibility(View.VISIBLE);
                    tzjl.setVisibility(View.VISIBLE);
                    yqnhbfb.setText(((HomeModel.SCATTEREDLISTBean) item).getTENDER_AWARD_SCALE().toString());
                }

            }
        }
        if (item instanceof HomeModel.NEWFINANCELISTBean){
            if (((HomeModel.NEWFINANCELISTBean) item).getNOVICE_FLG().toString().equals("1")){
                //新手标
//                    tv_xianzhi.setText("新手标，每人限投两万");
                    tv_xianzhi.setText(((HomeModel.NEWFINANCELISTBean) item).getADVERTISE_WORD());
                    tv_xianzhi.setTextColor(context.getResources().getColor(R.color.red));
                    tv_xianzhi.setBackgroundResource(R.drawable.text_view_type_border_red);
                    tv_xianzhi.setPadding(5,0,5,0);



//                novice.setImageResource(R.mipmap.newsshare);
            }else{
                if(("").equals(((HomeModel.NEWFINANCELISTBean) item).getADVERTISE_WORD())) {
                    tv_xianzhi.setText("");
                    tv_xianzhi.setTextColor(context.getResources().getColor(R.color.white));
                    tv_xianzhi.setBackgroundDrawable(context.getResources().getDrawable(R.drawable.text_view_type_border_white));
                }else{
                    tv_xianzhi.setText(((HomeModel.NEWFINANCELISTBean) item).getADVERTISE_WORD());
                    tv_xianzhi.setTextColor(context.getResources().getColor(R.color.type));
                    tv_xianzhi.setBackgroundResource(R.drawable.text_view_type_border);
                    tv_xianzhi.setPadding(5,0,5,0);
                }
            }

            //是否显示加息
            if(((HomeModel.NEWFINANCELISTBean) item).getAddRate().equals("0")){
                jiaxi.setVisibility(View.INVISIBLE);
            }else{
                jiaxi.setVisibility(View.VISIBLE);
                jiaxi.setText("+"+((HomeModel.NEWFINANCELISTBean) item).getAddRate()+"%");
            }

//            icon.setImageResource(R.mipmap.sanbiao);
            title.setText(((HomeModel.NEWFINANCELISTBean) item).getPRODUCTS_TITLE());
            //设置当前标的是否满标（满标奖励）
            desc.setText(((HomeModel.NEWFINANCELISTBean) item).getFULL_TIP());
            //总金额
            all.setText(((HomeModel.NEWFINANCELISTBean) item).getFINANCE_AMOUNT()+"万");

            String str_yqnh = ((HomeModel.NEWFINANCELISTBean) item).getFINANCE_INTEREST_RATE1();
//            SpannableString ss = new SpannableString(str_yqnh);
//            ss.setSpan(new TextAppearanceSpan(context,R.style.style4),str_yqnh.length()-1,str_yqnh.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
//            ss.setSpan(new TextAppearanceSpan(context,R.style.style3),0,str_yqnh.length()-1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
//            yqnh.setText(ss, TextView.BufferType.SPANNABLE);
            yqnh.setText(str_yqnh);

            String str_wfs = ((HomeModel.NEWFINANCELISTBean) item).getFINANCE_PERIOD()+((HomeModel.NEWFINANCELISTBean) item).getINTEREST_RATE_TYPE();
            SpannableString ss_wfs = new SpannableString(str_wfs);
            ss_wfs.setSpan(new TextAppearanceSpan(context,R.style.style4),((HomeModel.NEWFINANCELISTBean) item).getFINANCE_PERIOD().length(),str_wfs.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            ss_wfs.setSpan(new TextAppearanceSpan(context,R.style.style3),0,((HomeModel.NEWFINANCELISTBean) item).getFINANCE_PERIOD().length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            wfsyy.setText(ss_wfs, TextView.BufferType.SPANNABLE);

            wfsy.setText("借款期限");
            String residue=(((HomeModel.NEWFINANCELISTBean) item).getFINANCE_AMOUNT_WAIT().substring(2,
                    ((HomeModel.NEWFINANCELISTBean) item).getFINANCE_AMOUNT_WAIT().length()-1));
            if(residue.equals("完")){
                syje.setText("0");
            }else{
                syje.setText(residue);
            }

//            syje.setText(((HomeModel.NEWFINANCELISTBean) item).getFINANCE_AMOUNT_WAIT());

//            bfb.setText(((HomeModel.NEWFINANCELISTBean) item).getFINANCE_AMOUNT_SCALE());
            String tenderawardscale = ((HomeModel.NEWFINANCELISTBean) item).getFINANCE_AMOUNT_SCALE();
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
            if (((HomeModel.NEWFINANCELISTBean) item).getPRODUCTS_STATUS().toString().equals("未开始")){
                //即将发布
//                ljzr.setVisibility(View.GONE);
                rl_daojishi.setVisibility(View.VISIBLE);
                String financestartdate = ((HomeModel.NEWFINANCELISTBean) item).getFINANCE_START_DATE();
                String systemdate = ((HomeModel.NEWFINANCELISTBean) item).getSYSTEM_DATE();
                setTimerView(systemdate, financestartdate);
            }else if (((HomeModel.NEWFINANCELISTBean) item).getPRODUCTS_STATUS().toString().equals("立即投资")){
                //投资中
                button.setText("立即投资");
                button.setBackgroundResource(R.drawable.textview_cricle_btn);
            }else if (((HomeModel.NEWFINANCELISTBean) item).getPRODUCTS_STATUS().toString().equals("还款中")){
                //还款中
                button.setText("收益中");
                button.setBackgroundResource(R.drawable.textview_circle_btn_gray);
            }else if (((HomeModel.NEWFINANCELISTBean) item).getPRODUCTS_STATUS().toString().equals("还款完了")){
                //已完成
                button.setText("已还清");
                button.setBackgroundResource(R.drawable.textview_circle_btn_gray);
            }else if (((HomeModel.NEWFINANCELISTBean) item).getPRODUCTS_STATUS().toString().equals("满标待审")){
//                ljzr.setText("满标待审");
//                ljzr.setBackgroundResource(R.drawable.textview_circle_btn_gray);
                button.setText("已满标");
                button.setBackgroundResource(R.drawable.textview_circle_btn_gray);
            }
            if (((HomeModel.NEWFINANCELISTBean) item).getTENDER_AWARD_FLG().equals("0")){
                yqnhbfb.setVisibility(View.GONE);
                tzjl.setVisibility(View.GONE);
            }else{
                if (((HomeModel.NEWFINANCELISTBean) item).getTENDER_AWARD_SCALE().toString().equals("0.00%")){
                    yqnhbfb.setVisibility(View.GONE);
                    tzjl.setVisibility(View.GONE);
                }else{
                    yqnhbfb.setVisibility(View.VISIBLE);
                    tzjl.setVisibility(View.VISIBLE);
                    yqnhbfb.setText("+"+((HomeModel.NEWFINANCELISTBean) item).getTENDER_AWARD_SCALE().toString());
                }

            }
        }
    }

    /**
     * 设置进度显示在对应的位置
     */

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
            w =windowManager.getDefaultDisplay().getWidth()-(tW+20);
        }
        else{
            w =windowManager.getDefaultDisplay().getWidth()-tW;
        }

        //计算偏移量
        if (w * pro / 100 + tW * 0.3 > w) {
            params.leftMargin = (int) (w - tW * 1.1);
        } else if (w * pro / 100 < tW * 0.7) {
//            if (pro==0) {
                params.leftMargin = 0;
//            } else {
//                params.leftMargin = (int) (( tW * 0.7)-(w * pro / 100) );
//                //                params.leftMargin =pro ;
//            }
        } else {
            params.leftMargin = (int) (w * pro / 100 - tW * 0.7);
        }
        progesssValue.setLayoutParams(params);
    }

//    public void setPos(){
//        //获得屏幕宽度
//        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
//        DisplayMetrics dm = new DisplayMetrics();// 创建了一张白纸
//        windowManager.getDefaultDisplay().getMetrics(dm);// 给白纸设置宽高
//        //        w =windowManager.getDefaultDisplay().getWidth();
//        //获得矩形指示器（TextView）的宽度
//        int ws = View.MeasureSpec.makeMeasureSpec(0,
//                View.MeasureSpec.UNSPECIFIED);
//        int h = View.MeasureSpec.makeMeasureSpec(0,
//                View.MeasureSpec.UNSPECIFIED);
//        progesssValue.measure(ws, h);
//        int tW = progesssValue.getMeasuredWidth();
//        //当百分比大于93时需要进行偏移量的调整，否则%号会被挤压到第二行
//        if( progress.getProgress()==100){
//            w =windowManager.getDefaultDisplay().getWidth()-(tW-15);
//        }
//        else if(progress.getProgress()>=93){
//            w =windowManager.getDefaultDisplay().getWidth()-tW;
//        }
//        else{
//            w =windowManager.getDefaultDisplay().getWidth();
//        }
//        //获得指示器的父布局
//        ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) progesssValue.getLayoutParams();
//        int pro = progress.getProgress();
//        //计算偏移量
//        if (w * pro / 100 + tW * 0.3 > w) {
//            params.leftMargin = (int) (w - tW * 1.1);
//        } else if (w * pro / 100 < tW * 0.7) {
//            //            params.leftMargin = 0;
//            if (pro==0) {
//                params.leftMargin = 0;
//            } else {
//                params.leftMargin = (int) (( tW * 0.7)-(w * pro / 100) );
//                //                params.leftMargin =pro ;
//            }
//        }
//
//        else {
//            params.leftMargin = (int) (w * pro / 100 - tW * 0.7);
//        }
//        progesssValue.setLayoutParams(params);
//    }



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
