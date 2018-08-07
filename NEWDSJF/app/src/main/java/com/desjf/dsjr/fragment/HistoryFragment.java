package com.desjf.dsjr.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.desjf.dsjr.R;
import com.desjf.dsjr.base.BaseFragment;

import butterknife.ButterKnife;

/**
 * @author YinL
 * @Describe 历史数据
 */
public class HistoryFragment extends BaseFragment {

    private View rootView;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        if (null == rootView) {
            rootView = inflater.inflate(R.layout.fragment_history, null);
            ButterKnife.bind(this, rootView);

        }

        ViewGroup parent = (ViewGroup)rootView.getParent();
        if (parent != null) {
            parent.removeView(rootView);
        }


        return rootView;
    }



}
