package com.desjf.dsjr.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.desjf.dsjr.R;
import com.desjf.dsjr.base.BaseFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class YiShouFragment extends BaseFragment {


    public YiShouFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_yi_shou, container, false);
    }

}
