package com.desjf.dsjr.activity;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

import com.desjf.dsjr.R;
import com.desjf.dsjr.adapter.HqbRecordsAdapter;
import com.desjf.dsjr.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AccountTransactionRecordsActivity extends BaseActivity {
    private List<String> str_list =new ArrayList<>();
    private HqbRecordsAdapter hqbRecordsAdapter;
    @BindView(R.id.rv_hqb_records)
    RecyclerView rvHqbRecords;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_transaction_records);
        ButterKnife.bind(this);
        initData();
    }

    private void initData() {
        str_list.clear();
        for (int i=0;i<10;i++){
            str_list.add("-50"+"i"+"元"+"");
        }
        hqbRecordsAdapter = new HqbRecordsAdapter(str_list,this);
        rvHqbRecords.setAdapter(hqbRecordsAdapter);
        rvHqbRecords.setLayoutManager(new StaggeredGridLayoutManager(1,StaggeredGridLayoutManager.VERTICAL));
        hqbRecordsAdapter.notifyDataSetChanged();

    }
}
