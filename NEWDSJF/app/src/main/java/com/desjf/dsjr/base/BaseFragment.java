package com.desjf.dsjr.base;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.Fragment;

import com.desjf.dsjr.widget.CustomClipLoading;

/**
 * Created by Administrator on 2017/7/17 0017.
 */

public class BaseFragment extends Fragment {
    private CustomClipLoading dialog;// 加载
    private BaseActivity activity;
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        activity  = (BaseActivity) context;

    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        System.gc();
    }
    protected void showLoadingDialog() {
        if (dialog == null) {
            dialog = new CustomClipLoading(getActivity());
        }
        if (!getActivity().isFinishing()) {
            dialog.show();
        }
    }

    /**
     * 隐藏进度条
     */
    protected void hideLoadingDialog() {
        if (dialog != null) {
            dialog.dismiss();
            dialog = null;
        }
    }
}
