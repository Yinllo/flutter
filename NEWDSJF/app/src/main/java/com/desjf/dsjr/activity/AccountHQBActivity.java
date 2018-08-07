package com.desjf.dsjr.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.desjf.dsjr.R;
import com.desjf.dsjr.adapter.HqbAdapter;
import com.desjf.dsjr.base.BaseActivity;
import com.desjf.dsjr.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AccountHQBActivity extends BaseActivity {
    private HqbAdapter hqbAdapter;
    private List<String> str;
    private Context context = this;
    @BindView(R.id.rv_hqb)
    RecyclerView rvHqb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_hqb);
        ButterKnife.bind(this);
        initData();
    }

    private void initData() {
        str = new ArrayList<>();
        str.clear();
        for (int i =0;i<5;i++){
            str.add("活期专区"+"i"+"");
        }
        hqbAdapter = new HqbAdapter(str,this);
        rvHqb.setAdapter(hqbAdapter);
        rvHqb.setLayoutManager(new StaggeredGridLayoutManager(1,StaggeredGridLayoutManager.VERTICAL));
        hqbAdapter.notifyDataSetChanged();
        rvHqb.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent = new Intent(AccountHQBActivity.this,AccountMyHqbActivity.class);
                startActivity(intent);
                ToastUtils.showTost(context,position+"");
            }
        });

    }
}
