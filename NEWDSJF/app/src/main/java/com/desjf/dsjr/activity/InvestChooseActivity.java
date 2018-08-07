package com.desjf.dsjr.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.desjf.dsjr.R;
import com.desjf.dsjr.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class InvestChooseActivity extends BaseActivity {

    @BindView(R.id.tv_invest_choose_cancel)
    TextView tvInvestChooseCancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invest_choose);
        ButterKnife.bind(this);
        tvInvestChooseCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
