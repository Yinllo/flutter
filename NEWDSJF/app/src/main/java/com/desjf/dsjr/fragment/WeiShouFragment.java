package com.desjf.dsjr.fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.desjf.dsjr.R;
import com.desjf.dsjr.activity.AccountCalenderActivity;
import com.desjf.dsjr.adapter.CalenderWeiShouAdapter;
import com.desjf.dsjr.base.BaseFragment;
import com.desjf.dsjr.model.CalendarMonDetailsModel;
import com.desjf.dsjr.widget.ToWeishouListner;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 * //未收
 */
public class WeiShouFragment extends BaseFragment {
    @BindView(R.id.rv_weishou)
    RecyclerView rvWeishou;
    private Context context = getActivity();
    private CalenderWeiShouAdapter calenderWeiShouAdapter;
    private List<CalendarMonDetailsModel> calendarMonDetailsModels;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_wei_shou, container, false);
        ButterKnife.bind(this, view);

        initData();
        return view;
    }


    private void initData() {

    }

}
