package com.desjf.dsjr.ui.fragment.FundDetails;

import android.os.Bundle;

import com.desjf.dsjr.R;
import com.desjf.dsjr.base.BaseFragment;

/**
 * @Author YinL
 * @Date 2018/1/23 0015
 * @Describe 资金流水中的   全部
 */
public class AllFragment extends BaseFragment {


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_all;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
          getData();
    }

    public void getData() {

       /* IdeaApi.getApiService(IdeaApiService.class)
                .getMezi()
                .compose(this.<BasicResponseBean<List<MeiZi>>>bindToLifecycle())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DefaultObserver<BasicResponseBean<List<MeiZi>>>(getActivity()) {
                    @Override
                    public void onSuccess(BasicResponseBean<List<MeiZi>> response) {
                        List<MeiZi> results = response.getResults();
                        showToast("请求成功，妹子个数为"+results.size());
                    }
                });*/
    }

}