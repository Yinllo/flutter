package com.desjf.dsjr.activity;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.desjf.dsjr.R;
import com.desjf.dsjr.adapter.MyMarksAdapter;
import com.desjf.dsjr.base.BaseActivity;
import com.desjf.dsjr.base.BaseApplication;
import com.desjf.dsjr.model.AccountModel;
import com.desjf.dsjr.utils.BarUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AccountMyMarksActivity extends BaseActivity {
    @BindView(R.id.tv_my_marks_back)
    ImageView tvMyMarksBack;
    @BindView(R.id.tv_my_marks_total)
    TextView tvMyMarksTotal;
    private List<String> str_list = new ArrayList<>();
    private MyMarksAdapter myMarksAdapter;
    @BindView(R.id.rv_my_marks)
    RecyclerView rvMyMarks;
    private AccountModel accountModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_my_marks);
        ButterKnife.bind(this);
        BarUtils.setColor(this, getResources().getColor(R.color.endmian), 0);
        BaseApplication baseApplication = (BaseApplication) getApplication();
        accountModel = baseApplication.getAccountModel();
        initData();
    }

    private void initData() {
        tvMyMarksTotal.setText(accountModel.getUSERINTEGRAL());
        str_list.clear();
        myMarksAdapter = new MyMarksAdapter(accountModel.getPOINTINFOLIST(), this);
        rvMyMarks.setAdapter(myMarksAdapter);
        rvMyMarks.setLayoutManager(new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL));
        myMarksAdapter.notifyDataSetChanged();
        tvMyMarksBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
