package com.desjf.dsjr.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.desjf.dsjr.R;
import com.desjf.dsjr.adapter.NoticeRecyclerAdapter;
import com.desjf.dsjr.base.BaseFragment;
import com.desjf.dsjr.bean.NoticeBean;
import com.desjf.dsjr.widget.WrapContentLinearLayoutManager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * @Author YinL
 * @Date 2018/4/24 0015
 * @Describe  公告Fragment
 */

public class NoticeFragment extends BaseFragment {

    @BindView(R.id.notice_recycler)
    RecyclerView noticeRecyclerView;

    private List<NoticeBean> dataBeanList;
    private NoticeBean dataBean1,dataBean;
    private NoticeRecyclerAdapter mAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_notice;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        initData();
    }

    /**
     * 模拟数据
     */
    private void initData(){
        dataBeanList = new ArrayList<>();

            dataBean = new NoticeBean();
            dataBean.setID("1");
            dataBean.setType(0);
            dataBean.setRead(false);
            dataBean.setParentTitleTxt("2018年4月29日--5月1日共三天");
            dataBean.setParentDataTxt("2018-4-29 16:12:00");
            dataBean.setChildTitleTxt("尊敬的德晟金服会员：");
            dataBean.setChildContentTxt("放假时间为2018年4月29日--5月1日共三天，5月2日正常上班；");
            dataBean.setChildAuthorTxt("德晟金服运营团队");
            dataBean.setChildDataTxt("2018-4-32");
            dataBean.setChildBean(dataBean);
            dataBeanList.add(dataBean);

            dataBean1 = new NoticeBean();
            dataBean1.setID("2");
            dataBean1.setType(0);
            dataBean1.setRead(true);
            dataBean1.setParentTitleTxt("节假日期间");
            dataBean1.setParentDataTxt("2018-4-29 16:12:00");
            dataBean1.setChildTitleTxt("尊敬的德晟金服会员：");
            dataBean1.setChildContentTxt("节假日期间投资人可以正常充值投资；节假日期间正常回款，提现将于5月2日统一处理；");
            dataBean1.setChildAuthorTxt("德晟金服运营团队");
            dataBean1.setChildDataTxt("2018-4-32");
            dataBean1.setChildBean(dataBean1);
            dataBeanList.add(dataBean1);

        setData(dataBeanList);
    }

    private void setData(List<NoticeBean> list){
        LinearLayoutManager linearLayoutManager=new WrapContentLinearLayoutManager(getActivity());
        noticeRecyclerView.setLayoutManager(linearLayoutManager);
        mAdapter = new NoticeRecyclerAdapter(getActivity(),list);
        noticeRecyclerView.setAdapter(mAdapter);
        //滚动监听
        mAdapter.setOnScrollListener(new NoticeRecyclerAdapter.OnScrollListener(){
            @Override
            public void scrollTo(int pos) {
                noticeRecyclerView.scrollToPosition(pos);
            }
        });
    }







    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

}
