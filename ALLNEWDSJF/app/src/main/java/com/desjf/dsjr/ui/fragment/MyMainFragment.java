package com.desjf.dsjr.ui.fragment;

import android.os.Bundle;

import com.desjf.dsjr.R;
import com.desjf.dsjr.base.BaseConfig;
import com.desjf.dsjr.base.BaseFragment;

/**
 * @Author YinL
 * @Date 2018/3/25 0021
 * @Describe  个人中心 主Fragment 承载老账户Fragment和新账户Fragment
 */
public class MyMainFragment extends BaseFragment {

    private FragmentController fragmentController;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_my_main;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
             init();
    }


    private void init(){

        //初始化控制器
        fragmentController = FragmentController.getInstance(this, R.id.id_fragment);
        fragmentController.showFragment(1);
        initWodeMessage();


    }

    //获取我的数据
    private void initWodeMessage() {

    }


    @Override
    public void onResume() {
        super.onResume();

        if(BaseConfig.TO_MY==1){
            fragmentController.showFragment(0);

            BaseConfig.TO_MY=0;
        }
        else if(BaseConfig.TO_MY==2){
            fragmentController.showFragment(1);

            BaseConfig.TO_MY=0;
        }
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (hidden) {
            return;
        } else {
            if(BaseConfig.TO_MY==2){
                fragmentController.showFragment(1);
                BaseConfig.TO_MY=0;
            }
        }
    }

}