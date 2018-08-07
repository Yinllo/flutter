package com.desjf.dsjr.fragment.IndividualCenter;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alibaba.fastjson.JSON;
import com.desjf.dsjr.R;
import com.desjf.dsjr.base.BaseApplication;
import com.desjf.dsjr.base.BaseFragment;
import com.desjf.dsjr.bean.Identity;
import com.desjf.dsjr.biz.AccountBiz;
import com.desjf.dsjr.biz.exception.BizFailure;
import com.desjf.dsjr.biz.exception.ZYException;
import com.desjf.dsjr.biz.retrofit.BankHttpUtil;
import com.desjf.dsjr.biz.retrofit.CallUtil;
import com.desjf.dsjr.biz.task.BizDataAsyncTask;
import com.desjf.dsjr.config.DsjrConfig;
import com.desjf.dsjr.model.AccountModel;

import butterknife.ButterKnife;
import retrofit2.Call;

/**
 * @Author YinL
 * @Date 2018/3/25 0021
 * @Describe  个人中心 主Fragment 承载老账户Fragment和新账户Fragment
 */
public class MyMainFragment extends BaseFragment {

    private View rootView;
    private FragmentController fragmentController;
    private Identity identity;
    private AccountModel accountModels;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        if (null == rootView) {
            rootView = inflater.inflate(R.layout.fragment_my_main, null);
            ButterKnife.bind(this, rootView);

         init();

        }

        ViewGroup parent = (ViewGroup)rootView.getParent();
        if (parent != null) {
            parent.removeView(rootView);
        }
        return rootView;
    }

    private void init(){

        //初始化控制器
        fragmentController = FragmentController.getInstance(this, R.id.id_fragment);
        fragmentController.showFragment(1);
        initWodeMessage();

    }

    //获取我的数据
    private void initWodeMessage() {
//        showLoadingDialog();
        @SuppressLint("StaticFieldLeak")
        BizDataAsyncTask<AccountModel> shouyeMessage = new BizDataAsyncTask<AccountModel>() {
            @Override
            protected AccountModel doExecute() throws ZYException, BizFailure {
                return AccountBiz.getWodeMessage();
            }

            @Override
            protected void onExecuteSucceeded(AccountModel accountModel) {
//                hideLoadingDialog();

                //将AccountModel存到BaseApplication
                BaseApplication baseApplication = (BaseApplication)getActivity().getApplication();
                baseApplication.setAccountModel(accountModel);
                //赋值
                accountModels=accountModel;
                //如果是老用户且未开通存管则进入普通账户页面
                CallUtil.call(getActivity(), BankHttpUtil.getHttpRequestService().getIdentity(accountModel.getPHONENUMBER()), new CallUtil.MyCallListener() {
                    @Override
                    public void onRespnseSuccess(String jsonString) {
                        identity= JSON.parseObject(jsonString,Identity.class);

                        //默认显示项
                        /**
                         * 判断当前是新用户还是老用户，据此来判断是否显示新、旧账户切换按钮
                         * 0001 请登录   0002 老用户未实  0004  新用户未实名  0005  新用户已实名
                         0003  老用户已实名
                         */
                        if(identity.getMessageType().equals("0002")){
                            fragmentController.showFragment(0);
                        }else {
                            fragmentController.showFragment(1);
                        }
                    }

                    @Override
                    public void onRespnseFailure(Call<String> call, Throwable t) {
                        fragmentController.showFragment(1);
                    }
                });
            }

            @Override
            protected void OnExecuteFailed(String error) {
                hideLoadingDialog();
                fragmentController.showFragment(1);
            }
        };
        shouyeMessage.execute();
    }

    @Override
    public void onResume() {
        super.onResume();
        if(DsjrConfig.TO_MY==1){
            fragmentController.showFragment(0);
            DsjrConfig.TO_MY=0;
        }
        else if(DsjrConfig.TO_MY==2){
            fragmentController.showFragment(1);
            DsjrConfig.TO_MY=0;
        }
    }


//    @Override
//    public void onHiddenChanged(boolean hidden) {
//        super.onHiddenChanged(hidden);
//        if (hidden) {
//            return;
//        } else {
//                fragmentController.showFragment(0);
//        }
//    }

}