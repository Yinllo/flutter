package com.desjf.dsjr.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.desjf.dsjr.R;
import com.desjf.dsjr.adapter.CalenderWeiShouAdapter;
import com.desjf.dsjr.base.BaseActivity;
import com.desjf.dsjr.biz.CalendarBiz;
import com.desjf.dsjr.biz.exception.BizFailure;
import com.desjf.dsjr.biz.exception.ZYException;
import com.desjf.dsjr.biz.task.BizDataAsyncTask;
import com.desjf.dsjr.model.CalendarMonDetailsModel;
import com.desjf.dsjr.model.CalendarMonHeadModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CalenderAllActivity extends BaseActivity {

    @BindView(R.id.iv_calendar_all_back)
    ImageView ivCalendarAllBack;
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
//    @BindView(R.id.btn_weishou)
//    RadioButton btnWeishou;
//    @BindView(R.id.btn_yishou)
//    RadioButton btnYishou;
//    @BindView(R.id.rg_shoukuan)
//    RadioGroup rgShoukuan;
    @BindView(R.id.rv_huikuan_all)
    RecyclerView rvHuikuanAll;
    @BindView(R.id.srl_calender_all)
    SwipeRefreshLayout srlCalenderAll;
    private String shou = "1";
    private List<CalendarMonDetailsModel> calendarMonDetails = new ArrayList<>();
    private CalenderWeiShouAdapter calenderWeiShouAdapter;
    private Context context = this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calender_all);
        ButterKnife.bind(this);
        initData();
        getPayment();
        getPaymentDetails();
        initView();

    }

    private void initView() {

    }

    private void getPaymentDetails() {
        showLoadingDialog();
        BizDataAsyncTask<List<CalendarMonDetailsModel>> getDetails = new BizDataAsyncTask<List<CalendarMonDetailsModel>>() {
            @Override
            protected List<CalendarMonDetailsModel> doExecute() throws ZYException, BizFailure {

                return CalendarBiz.getDetails("1", "0", "300", "", shou);
            }

            @Override
            protected void onExecuteSucceeded(List<CalendarMonDetailsModel> calendarMonDetailsModels) {
                hideLoadingDialog();
                srlCalenderAll.setRefreshing(false);
                //处理回款详情
                initDetails(calendarMonDetailsModels);

            }

            @Override
            protected void OnExecuteFailed(String error) {
                hideLoadingDialog();
//                srlCalender.setRefreshing(false);
                srlCalenderAll.setRefreshing(false);
            }
        };
        getDetails.execute();
    }

    private void initDetails(List<CalendarMonDetailsModel> calendarMonDetailsModels) {
        calendarMonDetails = calendarMonDetailsModels;
        calenderWeiShouAdapter = new CalenderWeiShouAdapter(calendarMonDetailsModels, context, shou);
        rvHuikuanAll.setAdapter(calenderWeiShouAdapter);
        rvHuikuanAll.setLayoutManager(new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL));
        calenderWeiShouAdapter.notifyDataSetChanged();

        rvHuikuanAll.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent = new Intent(CalenderAllActivity.this, WodeInvestDetailsActivity.class);
                intent.putExtra("kind",4);
                intent.putExtra("kind_id",calendarMonDetails.get(position).getOID_TENDER_ID());
                intent.putExtra("kind_status",calendarMonDetails.get(position).getTENDER_FROM());
                intent.putExtra("kind_zhaiquan",calendarMonDetails.get(position).getTRANSFER_CONTRACT_ID());
                startActivity(intent);
            }
        });
    }

    private void initData() {
//        btnWeishou.setChecked(true);
//        btnYishou.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                shou = "0";
//                getPaymentDetails();
//            }
//        });
//        btnWeishou.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                shou = "1";
//                getPaymentDetails();
//            }
//        });
        srlCalenderAll.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getPaymentDetails();
            }
        });
        srlCalenderAll.setColorSchemeResources(R.color.main);
    }

    private void getPayment() {
        showLoadingDialog();
        BizDataAsyncTask<CalendarMonHeadModel> getMonHead = new BizDataAsyncTask<CalendarMonHeadModel>() {
            @Override
            protected CalendarMonHeadModel doExecute() throws ZYException, BizFailure {
                return CalendarBiz.getPayment("1", "");
            }

            @Override
            protected void onExecuteSucceeded(CalendarMonHeadModel calendarMonHeadModel) {
                hideLoadingDialog();
                initAllCalender(calendarMonHeadModel);
            }

            @Override
            protected void OnExecuteFailed(String error) {
                hideLoadingDialog();
            }
        };
        getMonHead.execute();
    }

    private void initAllCalender(CalendarMonHeadModel calendarMonHeadModel) {
        tvCalendaerYsze.setText(calendarMonHeadModel.getRI_ZONGE_TOTAL());
        tvCalendaerBj.setText(calendarMonHeadModel.getRI_BENJIN_TOTAL());
        tvCalendaerSy.setText(calendarMonHeadModel.getRI_SHOUYI_TOTAL());
        tvCalendaerBs.setText(calendarMonHeadModel.getRI_BISHU_TOTAL());
//        btnWeishou.setText("未收(元)" + calendarMonHeadModel.getRI_WEI_SHOU());
//        btnYishou.setText("已收(元)" + calendarMonHeadModel.getRI_YI_SHOU());
    }

    @OnClick(R.id.iv_calendar_all_back)
    public void onClick() {
        finish();
    }
}
