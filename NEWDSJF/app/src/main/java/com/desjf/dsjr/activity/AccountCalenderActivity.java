package com.desjf.dsjr.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.GestureDetector;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.desjf.dsjr.R;
import com.desjf.dsjr.adapter.CalenderWeiShouAdapter;
import com.desjf.dsjr.adapter.calendar.CalendarAdapter;
import com.desjf.dsjr.base.BaseActivity;
import com.desjf.dsjr.biz.CalendarBiz;
import com.desjf.dsjr.biz.exception.BizFailure;
import com.desjf.dsjr.biz.exception.ZYException;
import com.desjf.dsjr.biz.task.BizDataAsyncTask;
import com.desjf.dsjr.fragment.WeiShouFragment;
import com.desjf.dsjr.fragment.YiShouFragment;
import com.desjf.dsjr.model.CalendarMonDetailsModel;
import com.desjf.dsjr.model.CalendarMonHeadModel;
import com.desjf.dsjr.model.RecoverCalendarModel;
import com.desjf.dsjr.widget.NoScrollGridView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AccountCalenderActivity extends BaseActivity {
    //每次滑动，增加或者减去一个月，默认为0
    private  int jumpMonth = 0;
    //每次跨越一年，增加或者减去一年，默认为0
    private  int jumpYear = 0;
    @BindView(R.id.btn_weishou)
    RadioButton btnWeishou;
    @BindView(R.id.btn_yishou)
    RadioButton btnYishou;
    @BindView(R.id.rg_shoukuan)
    RadioGroup rgShoukuan;
    @BindView(R.id.tv_project_title)
    TextView tvProjectTitle;
    @BindView(R.id.tv_calendaer_ysze)
    TextView tvCalendaerYsze;
    @BindView(R.id.tv_calendaer_bj)
    TextView tvCalendaerBj;
    @BindView(R.id.tv_calendaer_sy)
    TextView tvCalendaerSy;
    @BindView(R.id.tv_calendaer_bs)
    TextView tvCalendaerBs;
    @BindView(R.id.rv_huikuan)
    RecyclerView rvHuikuan;
    @BindView(R.id.iv_liri_back)
    ImageView ivLiriBack;
    @BindView(R.id.srl_calender)
    SwipeRefreshLayout srlCalender;
    @BindView(R.id.tv_calendaer_all)
    TextView tvCalendaerAll;
    private int year_c = 0;
    private int month_c = 0;
    private int day_c = 0;
    private String currentData = "";
    //每次添加gridview到viewflipper中给的标记
    private int gvFlag = 0;
    //添加手势监听
    private GestureDetector gestureDetector;
    //日历布局
    private NoScrollGridView gridView = null;
    //日历适配器
    private CalendarAdapter calv = null;
    private Context context = this;
    private List<RecoverCalendarModel> recoverCalendarModels = new ArrayList<>();
    @BindView(R.id.prevMonth)
    ImageView prevMonth;
    @BindView(R.id.currentMonth)
    TextView currentMonth;
    @BindView(R.id.nextMonth)
    ImageView nextMonth;
    @BindView(R.id.flipper)
    ViewFlipper flipper;
    private String shou = "1";
    private String year = "";
    private String month = "";
    private String month_details = "";
    private WeiShouFragment weishouFragment;
    private YiShouFragment yishouFragment;
    private List<CalendarMonDetailsModel> calendarMonDetails = new ArrayList<>();
    private CalenderWeiShouAdapter calenderWeiShouAdapter;
    public AccountCalenderActivity() {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-M-d");
        currentData = sdf.format(date);
        year_c = Integer.parseInt(currentData.split("-")[0]);
        month_c = Integer.parseInt(currentData.split("-")[1]);
        day_c = Integer.parseInt(currentData.split("-")[2]);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_calender);
        ButterKnife.bind(this);
        initCalendarView();
        initData();
        //回款日历--单月、全部
        getPayment();
        //回款日历明细
        getPaymentDetails();
        initCheck();
        initView();
    }

    private void initView() {
        srlCalender.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getPaymentDetails();
            }
        });
        srlCalender.setColorSchemeResources(R.color.main);

    }

    //判断谁点击
    private void initCheck() {
    }
    

    private void getPaymentDetails() {
        showLoadingDialog();
        BizDataAsyncTask<List<CalendarMonDetailsModel>> getDetails = new BizDataAsyncTask<List<CalendarMonDetailsModel>>() {
            @Override
            protected List<CalendarMonDetailsModel> doExecute() throws ZYException, BizFailure {
                month_details = getMonth();
                if (month_details.length() == 1) {
                    month_details = "0" + getMonth();
                } else {
                    month_details = getMonth();
                }
                return CalendarBiz.getDetails("0", "0", "30", getYear() + "-" + month_details, shou);
            }

            @Override
            protected void onExecuteSucceeded(List<CalendarMonDetailsModel> calendarMonDetailsModels) {
                hideLoadingDialog();
                //处理回款详情
                initDetails(calendarMonDetailsModels);
                srlCalender.setRefreshing(false);
            }

            @Override
            protected void OnExecuteFailed(String error) {
                hideLoadingDialog();
                srlCalender.setRefreshing(false);
            }
        };
        getDetails.execute();
    }

    private void initDetails(final List<CalendarMonDetailsModel> calendarMonDetailsModels) {

        if (shou.equals("1")){
            //weishou
            calendarMonDetails = calendarMonDetailsModels;
            calv.setRecoverCalendarModels(calendarMonDetails);
            calenderWeiShouAdapter = new CalenderWeiShouAdapter(calendarMonDetails, context, shou);
            rvHuikuan.setAdapter(calenderWeiShouAdapter);
            rvHuikuan.setLayoutManager(new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL));
            calv.notifyDataSetChanged();
            calenderWeiShouAdapter.notifyDataSetChanged();
        }else if (shou.equals("0")){
            calendarMonDetails = calendarMonDetailsModels;
            calv.setRecoverCalendarModels(calendarMonDetails);
            calenderWeiShouAdapter = new CalenderWeiShouAdapter(calendarMonDetails, context, shou);
            rvHuikuan.setAdapter(calenderWeiShouAdapter);
            rvHuikuan.setLayoutManager(new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL));
            calenderWeiShouAdapter.notifyDataSetChanged();
            calv.notifyDataSetChanged();
        }
        rvHuikuan.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {
                if (shou.equals("1")){
                    //未收
                    Intent intent = new Intent(AccountCalenderActivity.this, WodeInvestDetailsActivity.class);
                    intent.putExtra("kind",3);
                    intent.putExtra("kind_shou",0);
                    intent.putExtra("kind_id",calendarMonDetails.get(position).getOID_TENDER_ID());
                    intent.putExtra("kind_status",calendarMonDetails.get(position).getTENDER_FROM());
                    intent.putExtra("kind_zhaiquan",calendarMonDetails.get(position).getTRANSFER_CONTRACT_ID());
                    startActivity(intent);
                }else if (shou.equals("0")){
                    //已收
                    Intent intent = new Intent(AccountCalenderActivity.this, WodeInvestDetailsActivity.class);
                    intent.putExtra("kind",3);
                    intent.putExtra("kind_shou",1);
                    intent.putExtra("kind_id",calendarMonDetails.get(position).getOID_TENDER_ID());
                    intent.putExtra("kind_status",calendarMonDetails.get(position).getTENDER_FROM());
                    intent.putExtra("kind_zhaiquan",calendarMonDetails.get(position).getTRANSFER_CONTRACT_ID());
                    startActivity(intent);
                }

            }
        });




    }

    private void getPayment() {
        showLoadingDialog();
        BizDataAsyncTask<CalendarMonHeadModel> getMonHead = new BizDataAsyncTask<CalendarMonHeadModel>() {
            @Override
            protected CalendarMonHeadModel doExecute() throws ZYException, BizFailure {
                month = getMonth();
                if (month.length() == 1) {
                    month = "0" + getMonth();
                } else {
                    month = getMonth();
                }
                return CalendarBiz.getPayment("0", getYear() + "-" + month);
            }

            @Override
            protected void onExecuteSucceeded(CalendarMonHeadModel calendarMonHeadModel) {
                hideLoadingDialog();
                initCalendarMonHead(calendarMonHeadModel);
            }

            @Override
            protected void OnExecuteFailed(String error) {
                hideLoadingDialog();
            }
        };
        getMonHead.execute();
    }

    //处理单月、全部数据
    private void initCalendarMonHead(CalendarMonHeadModel calendarMonHeadModel) {
        tvCalendaerYsze.setText(calendarMonHeadModel.getRI_ZONGE_TOTAL());
        tvCalendaerBj.setText(calendarMonHeadModel.getRI_BENJIN_TOTAL());
        tvCalendaerSy.setText(calendarMonHeadModel.getRI_SHOUYI_TOTAL());
        tvCalendaerBs.setText(calendarMonHeadModel.getRI_BISHU_TOTAL());
        btnWeishou.setText("未收(元)" + calendarMonHeadModel.getRI_WEI_SHOU());
        btnYishou.setText("已收(元)" + calendarMonHeadModel.getRI_YI_SHOU());
    }


    private void initData() {
        btnWeishou.setChecked(true);
    }

    private void initCalendarView() {
        //实例化手势监听
        gestureDetector = new GestureDetector(this, new MyGestureListener());
        flipper.removeAllViews();
        calv = new CalendarAdapter(this, getResources(), jumpMonth, jumpYear, year_c, month_c, day_c);
        addGridView();
        gridView.setAdapter(calv);
        flipper.addView(gridView, 0);
        addTextToTopTextView(currentMonth);


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        System.gc();

    }

    @OnClick({R.id.prevMonth, R.id.nextMonth, R.id.btn_weishou, R.id.btn_yishou, R.id.iv_liri_back,R.id.tv_calendaer_all})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.prevMonth:
                enterPrevMonth(gvFlag);
                break;
            case R.id.nextMonth:
                enterNextMonth(gvFlag);
                break;
            case R.id.btn_weishou:
                shou = "1";
                calendarMonDetails.clear();
                calv.setRecoverCalendarModels(calendarMonDetails);
                calv.notifyDataSetChanged();
                getPaymentDetails();
//                calenderWeiShouAdapter.notifyDataSetChanged();
                break;
            case R.id.btn_yishou:
                shou = "0";
                calendarMonDetails.clear();
                calv.setRecoverCalendarModels(calendarMonDetails);
                calv.notifyDataSetChanged();
                getPaymentDetails();
//                calenderWeiShouAdapter.notifyDataSetChanged();
                break;
            case R.id.iv_liri_back:
                finish();
                break;
            case R.id.tv_calendaer_all:
                Intent intent = new Intent(AccountCalenderActivity.this,CalenderAllActivity.class);
                startActivity(intent);
                break;
        }
    }


    //日历滑动的手势监听
    private class MyGestureListener extends GestureDetector.SimpleOnGestureListener {
        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            //每次添加gridview到viewflipper中时 给的标记
            int gvFlag = 0;
            if (e1.getX() - e2.getX() > 120) {
                //向左滑动 上一个月
                enterNextMonth(gvFlag);
                return true;
            } else if (e1.getX() - e2.getX() < -120) {
                //向右滑动 下一个月
                enterPrevMonth(gvFlag);
                return true;
            }
            return false;
        }
    }

    //移动到上一个月
    private void enterPrevMonth(int gvFlag) {
        addGridView();
        jumpMonth--;
        calv = new CalendarAdapter(this, getResources(), jumpMonth, jumpYear, year_c, month_c, day_c);
        gridView.setAdapter(calv);
        gvFlag++;
        addTextToTopTextView(currentMonth);
        flipper.addView(gridView, gvFlag);
        flipper.setInAnimation(AnimationUtils.loadAnimation(this, R.anim.push_left_in));
        flipper.setOutAnimation(AnimationUtils.loadAnimation(this, R.anim.push_left_out));
        flipper.showPrevious();
        flipper.removeViewAt(0);
        //请求上个月数据
        getPayment();
        getPaymentDetails();
    }

    //移动到下一个月
    private void enterNextMonth(int gvFlag) {
        addGridView();
        jumpMonth++;
        calv = new CalendarAdapter(this, getResources(), jumpMonth, jumpYear, year_c, month_c, day_c);
        gridView.setAdapter(calv);
        addTextToTopTextView(currentMonth);
        gvFlag++;
        flipper.addView(gridView, gvFlag);
        flipper.setInAnimation(AnimationUtils.loadAnimation(this, R.anim.push_left_in));
        flipper.setOutAnimation(AnimationUtils.loadAnimation(this, R.anim.push_left_out));
        flipper.showNext();
        flipper.removeViewAt(0);
        //请求下个月数据
        getPayment();
        getPaymentDetails();

    }

    //添加一个月的图
    private void addGridView() {
        final LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(AbsListView.LayoutParams.MATCH_PARENT, AbsListView.LayoutParams.WRAP_CONTENT);
        gridView = new NoScrollGridView(this);
        gridView.setNumColumns(7);
        gridView.setGravity(Gravity.CENTER_VERTICAL);
        gridView.setSelector(new ColorDrawable(Color.TRANSPARENT));
        gridView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                return AccountCalenderActivity.this.gestureDetector.onTouchEvent(motionEvent);
            }
        });
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //天
                String schedulaDay = calv.getDateByClickItem(i).split("\\.")[0];
                //年
                String schedulaYear = calv.getShowYear();
                //月
                String schedulaMonth = calv.getShowMonth();

                for (int j = 0; j < calendarMonDetails.size(); j++) {
                    if (calendarMonDetails.get(j).getRECOVER_YEAR().equals(String.format("%02d", Integer.parseInt(schedulaYear))) &&
                            calendarMonDetails.get(j).getRECOVER_MONTH().equals(String.format("%02d", Integer.parseInt(schedulaMonth))) &&
                            calendarMonDetails.get(j).getRECOVER_DAY().equals(String.format("%02d", Integer.parseInt(schedulaDay)))) {
                        Intent intent = new Intent(AccountCalenderActivity.this, CalenderDetailsActivity.class);
                        intent.putExtra("date", schedulaYear + "-" + String.format("%02d", Integer.parseInt(schedulaMonth)) + "-" + String.format("%02d", Integer.parseInt(schedulaDay)));
                        intent.putExtra("shou",shou);
                        startActivity(intent);
                    }
                }
            }
        });
        gridView.setLayoutParams(params);
    }


    public void addTextToTopTextView(TextView view) {
        StringBuffer textDate = new StringBuffer();
        textDate.append(calv.getShowYear()).append("年").append(calv.getShowMonth()).append("月").append("\t");
        view.setText(textDate);
    }

    public String getYear() {
        return calv.getShowYear();
    }

    public String getMonth() {
        return calv.getShowMonth();
    }
}
