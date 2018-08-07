package com.desjf.dsjr.fragment.IndividualCenter;

import android.support.v4.app.Fragment;

import com.desjf.dsjr.base.BaseFragment;

import java.util.ArrayList;

/**
 * @Author YinL
 * @Date 2018/3/25 0025
 * @Describe 个人中心 Fragment嵌套控制器
 */

public class FragmentController {

    private int containerId;
    private android.support.v4.app.FragmentManager fm;
    private ArrayList<BaseFragment> fragments;
    OldWodeFragment oldWodeFragment;
    WodeFragment wodeFragment;

    private static FragmentController controller;

    public static FragmentController getInstance(Fragment parentFragment, int containerId) {
        if (controller == null) {
            controller = new FragmentController(parentFragment, containerId);
        }
        return controller;
    }

    private FragmentController(Fragment fragment, int containerId) {
        this.containerId = containerId;
        //fragment嵌套fragment，调用getChildFragmentManager
        fm = fragment.getChildFragmentManager();

        initFragment();
    }

    private void initFragment() {
        fragments = new ArrayList<BaseFragment>();
        if(oldWodeFragment==null){
            oldWodeFragment=new OldWodeFragment();
        }
        if(wodeFragment==null){
            wodeFragment=new WodeFragment();
        }
        fragments.add(oldWodeFragment);//老账户界面
        fragments.add(wodeFragment);//新账户


        android.support.v4.app.FragmentTransaction ft = fm.beginTransaction();
        for(Fragment fragment : fragments) {
            ft.add(containerId, fragment);
        }
        ft.commitAllowingStateLoss();
    }

    public void showFragment(int position) {
        hideFragments();
        Fragment fragment = fragments.get(position);
        android.support.v4.app.FragmentTransaction ft = fm.beginTransaction();
        ft.show(fragment);
        ft.commitAllowingStateLoss();
    }

    public void hideFragments() {
        android.support.v4.app.FragmentTransaction ft = fm.beginTransaction();
        for(Fragment fragment : fragments) {
            if(fragment != null) {
                ft.hide(fragment);
            }
        }
//        ft.commit();
        ft.commitAllowingStateLoss();
    }

    public Fragment getFragment(int position) {
        return fragments.get(position);
    }
}


