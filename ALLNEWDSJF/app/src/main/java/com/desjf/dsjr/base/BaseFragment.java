package com.desjf.dsjr.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.desjf.dsjr.utils.ToastUtils;
import com.desjf.dsjr.widget.CustomClipLoading;
import com.trello.rxlifecycle2.components.support.RxFragment;

import butterknife.ButterKnife;

/**
 * Created by YinL on 2018/1/13 0013.
 */

public abstract class BaseFragment extends RxFragment {

    public View rootView;
    public LayoutInflater inflater;

    private CustomClipLoading dialog;// 加载Loading
    private BaseActivity activity;

    @Nullable
    @Override
    public final View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        this.inflater = inflater;

        if(rootView != null){
            ViewGroup parent = (ViewGroup) rootView.getParent();
            if (parent != null) {
                parent.removeView(rootView);
            }
        } else{
            rootView = inflater.inflate(this.getLayoutId(), container, false);
            ButterKnife.bind(this,rootView);
            init(savedInstanceState);
        }
        return rootView;
    }

    protected abstract int getLayoutId();

    protected abstract void init(Bundle savedInstanceState);

    protected void showToast(String msg) {
        ToastUtils.show(msg);
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
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
