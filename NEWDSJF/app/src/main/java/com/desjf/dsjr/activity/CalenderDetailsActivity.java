package com.desjf.dsjr.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.desjf.dsjr.R;
import com.desjf.dsjr.adapter.CalendarDetailsAdapter;
import com.desjf.dsjr.base.BaseActivity;
import com.desjf.dsjr.biz.CalendarBiz;
import com.desjf.dsjr.biz.exception.BizFailure;
import com.desjf.dsjr.biz.exception.ZYException;
import com.desjf.dsjr.biz.task.BizDataAsyncTask;
import com.desjf.dsjr.model.CalendarDayDetailsModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CalenderDetailsActivity extends BaseActivity {
    @BindView(R.id.iv_calender_details_back)
    ImageView ivCalenderDetailsBack;
    @BindView(R.id.rv_calender_details)
    RecyclerView rvCalenderDetails;
    @BindView(R.id.srl_calender_details)
    SwipeRefreshLayout srlCalenderDetails;
    private Context context = this;
    private String date;
    private String shou;
    private CalendarDetailsAdapter calendarDetailsAdapter;
    private List<CalendarDayDetailsModel> calendarDay = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calender_details);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        date = intent.getStringExtra("date");
        shou = intent.getStringExtra("shou");
        //获取单日明细
        getDayDetails();
        initData();
    }

    private void initData() {
        srlCalenderDetails.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getDayDetails();
            }
        });

        rvCalenderDetails.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent = new Intent(CalenderDetailsActivity.this, WodeInvestDetailsActivity.class);
                intent.putExtra("kind",5);
                intent.putExtra("kind_id",calendarDay.get(position).getOID_TENDER_ID());
                intent.putExtra("kind_status",calendarDay.get(position).getTENDER_FROM());
                intent.putExtra("kind_zhaiquan",calendarDay.get(position).getTRANSFER_CONTRACT_ID());
                startActivity(intent);
            }
        });
    }

    private void getDayDetails() {
        showLoadingDialog();
        BizDataAsyncTask<List<CalendarDayDetailsModel>> getMx = new BizDataAsyncTask<List<CalendarDayDetailsModel>>() {
            @Override
            protected List<CalendarDayDetailsModel> doExecute() throws ZYException, BizFailure {
                return CalendarBiz.getDayDetails("0", "30", date);
            }

            @Override
            protected void onExecuteSucceeded(List<CalendarDayDetailsModel> calendarDayDetailsModels) {
                hideLoadingDialog();
                initDetails(calendarDayDetailsModels);
                srlCalenderDetails.setRefreshing(false);
            }

            @Override
            protected void OnExecuteFailed(String error) {
                hideLoadingDialog();
                srlCalenderDetails.setRefreshing(false);
            }
        };
        getMx.execute();
    }

    private void initDetails(List<CalendarDayDetailsModel> calendarDayDetailsModels) {
        calendarDay.clear();
        calendarDay.addAll(calendarDayDetailsModels);
        calendarDetailsAdapter = new CalendarDetailsAdapter(calendarDay, this,shou);
        rvCalenderDetails.setAdapter(calendarDetailsAdapter);
        rvCalenderDetails.setLayoutManager(new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL));
        calendarDetailsAdapter.notifyDataSetChanged();
    }

    @OnClick(R.id.iv_calender_details_back)
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_calender_details_back:
                CalenderDetailsActivity.this.finish();
                break;
        }
    }
}
