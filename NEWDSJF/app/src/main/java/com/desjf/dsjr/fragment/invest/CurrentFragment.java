package com.desjf.dsjr.fragment.invest;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.desjf.dsjr.R;
import com.desjf.dsjr.activity.CurrentDetailsActivity;
import com.desjf.dsjr.adapter.CurrentAdapter;
import com.desjf.dsjr.base.BaseFragment;
import com.desjf.dsjr.bean.ShouyeListBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class CurrentFragment extends BaseFragment {
    private CurrentAdapter currentAdapter;
    @BindView(R.id.rv_current)
    RecyclerView rvCurrent;
    private List<ShouyeListBean> slb_list=new ArrayList<>();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_current, container, false);
        ButterKnife.bind(this, view);
        initData();
        return view;
    }

    private void initData() {
        slb_list.clear();
        for (int i=0;i<3;i++){
            ShouyeListBean  slb = new ShouyeListBean();
            slb.setName("单身狗哈市"+i+"");
            slb_list.add(slb);
        }
        currentAdapter = new CurrentAdapter(slb_list,getActivity());
        rvCurrent.setAdapter(currentAdapter);
        rvCurrent.setLayoutManager(new StaggeredGridLayoutManager(1,StaggeredGridLayoutManager.VERTICAL));
        currentAdapter.notifyDataSetChanged();
        rvCurrent.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent = new Intent(getActivity(), CurrentDetailsActivity.class);
                intent.putExtra("key",String.valueOf(position));
                startActivity(intent);
            }
        });
    }

}
