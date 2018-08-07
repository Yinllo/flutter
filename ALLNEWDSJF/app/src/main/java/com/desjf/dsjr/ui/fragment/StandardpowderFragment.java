package com.desjf.dsjr.ui.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.desjf.dsjr.R;
import com.desjf.dsjr.adapter.LendAdapter;
import com.desjf.dsjr.adapter.SelectPopupAdapter;
import com.desjf.dsjr.base.BaseFragment;
import com.desjf.dsjr.bean.SubjectBean;
import com.desjf.dsjr.net.RxRetrofitManager;
import com.desjf.dsjr.net.common.BasicObserver;
import com.desjf.dsjr.ui.activity.InvestmentDetailsActivity;
import com.desjf.dsjr.utils.PreferenceCache;

import com.desjf.dsjr.widget.WrapContentLinearLayoutManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * @Author YinL
 * @Date 2018/1/19 0019
 * @Describe  散标Fragment
 */

public class StandardpowderFragment extends BaseFragment {

    @BindView(R.id.srl_invest)
    SwipeRefreshLayout refreshLayout;
    @BindView(R.id.rv_standerpower)
    RecyclerView rvStanderpower;
    @BindView(R.id.top_btn)
    Button toTop;//回到顶部按钮

    @BindView(R.id.lend_rate)
    TextView tvRate;//年化收益率
    @BindView(R.id.lend_time)
    TextView tvTime;//投资期限
    @BindView(R.id.lend_way)
    TextView tvWay;//还款方式

    private Activity mActivity;
    private int tag=-1;//年化收益率切换标志位

    //下拉框中的LiseView:投资期限、还款方式
    private ListView timeList;
    private ListView wayList;
    //下拉popup窗口
    private PopupWindow timePopup;
    private PopupWindow wayPopup;
    //数据源
    private List<String> timeData;
    private List<String> wayData;
    //适配器(两个通用)
    private SelectPopupAdapter selectPopupAdapter;
    private View notDataView;

    private Handler handler = new Handler(Looper.getMainLooper());
    private SubjectBean subjectBean;
    private List<SubjectBean.ListBean> subjectList = new ArrayList<>();//所有标的集合
    private LendAdapter lendAdapter;//适配器
    private LinearLayoutManager layout = new WrapContentLinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
    private int pagerNumber=1; //当前页数
    private int pagerSize=10;  //每页条数

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_standardpowder;
    }

    @Override
    protected void init(Bundle savedInstanceState) {

        //初始化下拉框选择位置
        PreferenceCache.putPfPositionOne("");
        PreferenceCache.putPfPositionTwo("");

        //初始化相关数据
        initData();
        initView();


    }

    private void initData() {
        pagerNumber=1;
        if(lendAdapter!=null){
            lendAdapter.setEnableLoadMore(false);//这里的作用是防止下拉刷新的时候还可以上拉加载
        }
        //获取标的列表数据
        final Map<String,Object> map=new HashMap<>();
        map.put("pageNumber",pagerNumber);
        map.put("pageSize",pagerSize);
        RxRetrofitManager.getHttpRequestService()
                .listFinance(map)
                .subscribeOn(Schedulers.io())
                .compose(this.<SubjectBean>bindToLifecycle())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BasicObserver<SubjectBean>() {
                    @Override
                    public void onSuccess(SubjectBean response) {

                        subjectBean=response;
//                        subjectList=response.getList();
//                        initAdapter(response.getList());
                        Log.e("aaa","---===---"+response.getList().size());
                        if(lendAdapter!=null){
                            lendAdapter.setEnableLoadMore(true);//这里的作用是防止下拉刷新的时候还可以上拉加载
                        }
                        setData(true, response.getList());
                    }

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
//                        initAdapter(null);
                        if(lendAdapter!=null){
                            lendAdapter.setEnableLoadMore(true);//这里的作用是防止下拉刷新的时候还可以上拉加载
                        }

                    }
                });

    }

    private void initAdapter(List<SubjectBean.ListBean> list){
        lendAdapter = new LendAdapter(list, mActivity);
        lendAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
                         @Override
                         public void onLoadMoreRequested() {
                             rvStanderpower.postDelayed(new Runnable() {
                                 @Override
                                 public void run() {
                                           loadMore();
                                 }

                             }, 500);
                         }
                     },rvStanderpower);
        //如果没有数据则显示 无数据
//        if(list.size()==0) {
//            rvStanderpower.setHasFixedSize(true);
//            //初始化无数据布局
//            notDataView = getActivity().getLayoutInflater().inflate(R.layout.empty, (ViewGroup) rvStanderpower.getParent(), false);
//            //将无数据布局设置到适配器中
//            lendAdapter.setEmptyView(notDataView);
//        }
        rvStanderpower.setAdapter(lendAdapter);
    }

    private void initView(){
        lendAdapter = new LendAdapter(null, mActivity);
        lendAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
//                rvStanderpower.postDelayed(new Runnable() {
//                    @Override
//                    public void run() {
                        loadMore();
//                    }
//
//                }, 500);
            }
        },rvStanderpower);
        //如果没有数据则显示 无数据
//        if(list.size()==0) {
//            rvStanderpower.setHasFixedSize(true);
//            //初始化无数据布局
//            notDataView = getActivity().getLayoutInflater().inflate(R.layout.empty, (ViewGroup) rvStanderpower.getParent(), false);
//            //将无数据布局设置到适配器中
//            lendAdapter.setEmptyView(notDataView);
//        }
        rvStanderpower.setLayoutManager(layout);
        rvStanderpower.setAdapter(lendAdapter);

        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refresh();
            }
        });
        rvStanderpower.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent = new Intent(getActivity(), InvestmentDetailsActivity.class);
                startActivity(intent);
            }
        });
    }

    private void refresh() {
        pagerNumber = 1;
        if(lendAdapter!=null){
            lendAdapter.setEnableLoadMore(false);//这里的作用是防止下拉刷新的时候还可以上拉加载
        }
        //获取标的列表数据
        final Map<String,Object> map=new HashMap<>();
        map.put("pageNumber",pagerNumber);
        map.put("pageSize",pagerSize);
        RxRetrofitManager.getHttpRequestService()
                .listFinance(map)
                .subscribeOn(Schedulers.io())
                .compose(this.<SubjectBean>bindToLifecycle())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BasicObserver<SubjectBean>() {
                    @Override
                    public void onSuccess(SubjectBean response) {
                        if(lendAdapter!=null){
                            lendAdapter.setEnableLoadMore(false);//这里的作用是防止下拉刷新的时候还可以上拉加载
                        }
                        subjectBean=response;
                        subjectList=response.getList();
                        setData(true, subjectList);
                    }

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                        if(lendAdapter!=null){
                            lendAdapter.setEnableLoadMore(false);//这里的作用是防止下拉刷新的时候还可以上拉加载
                        }
                    }
                });
    }

    private void loadMore() {
        //获取标的列表数据
        pagerNumber++;
        final Map<String,Object> map=new HashMap<>();
        map.put("pageNumber",pagerNumber);
        map.put("pageSize",pagerSize);
        RxRetrofitManager.getHttpRequestService()
                .listFinance(map)
                .subscribeOn(Schedulers.io())
                .compose(this.<SubjectBean>bindToLifecycle())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BasicObserver<SubjectBean>() {
                    @Override
                    public void onSuccess(SubjectBean response) {
                        subjectBean=response;
//                        subjectList=response.getList();
                        setData(false, response.getList());
                    }

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                        lendAdapter.loadMoreFail();
//                        Toast.makeText(mActivity, "cuowu", Toast.LENGTH_LONG).show();
                    }
                });
    }

    private void setData(boolean isRefresh, List<SubjectBean.ListBean> data) {
//        pagerNumber++;
        final int size = data == null ? 0 : data.size();
        if (isRefresh) {
            lendAdapter.setNewData(data);
//            lendAdapter.getData().clear();
//            lendAdapter.getData().addAll(data);
        } else {
            if (size > 0) {
//                lendAdapter.addData(data);
                lendAdapter.getData().addAll(data);
                lendAdapter.notifyDataSetChanged();
            }else{
                //如果没有数据则显示 无数据
                    rvStanderpower.setHasFixedSize(true);
                    //初始化无数据布局
                    notDataView = getActivity().getLayoutInflater().inflate(R.layout.empty, (ViewGroup) rvStanderpower.getParent(), false);
                    //将无数据布局设置到适配器中
                    lendAdapter.setEmptyView(notDataView);

            }
        }
        if (size < pagerSize) {
            //第一页如果不够一页就不显示没有更多数据布局
            lendAdapter.loadMoreEnd(isRefresh);
//            Toast.makeText(mActivity, "no more data", Toast.LENGTH_SHORT).show();
        } else {
            lendAdapter.loadMoreComplete();
        }
    }


    @OnClick({R.id.lend_time,R.id.lend_way,R.id.lend_rate})
    public void OnClick(View view){
        switch (view.getId()){
            case R.id.lend_time:
                // 点击控件后显示popup窗口
                initOneSelectPopup();
                // 使用isShowing()检查popup窗口是否在显示状态
                if (timePopup != null && !timePopup.isShowing()) {
                    timePopup.showAsDropDown(tvTime, 0, 1);
                }
                break;
            case R.id.lend_way:
                initTwoSelectPopup();
                // 使用isShowing()检查popup窗口是否在显示状态
                if (wayPopup != null && !wayPopup.isShowing()) {
                    wayPopup.showAsDropDown(tvWay, 0, 1);
                }
                break;
            case R.id.lend_rate:

                break;
        }

    }

    /**
     * 初始化投资期限的popup窗口
     */
    private void initOneSelectPopup() {
        timeList = new ListView(getActivity());
        timeList.setDivider(new ColorDrawable(0xffd6d6d6));
        timeList.setDividerHeight(1);
        TimeData();
        // 设置适配器
        selectPopupAdapter=new SelectPopupAdapter(timeData,getActivity(),1);
        timeList.setAdapter(selectPopupAdapter);

        //判断当前是否有已经选中的按钮，有则将该按钮设置为选中状态
        if(!("").equals(PreferenceCache.getPfPositionOne())){
            selectPopupAdapter.setFlg("1");
            int butPosition=Integer.parseInt(PreferenceCache.getPfPositionOne());
//            i=butPosition;
            selectPopupAdapter.setI(butPosition);
        }
        selectPopupAdapter.notifyDataSetChanged();
        // 设置RecyclerView点击事件监听
        timeList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //记录位置
                PreferenceCache.putPfPositionOne(position+"");
                // 在这里获取item数据
                String value = timeData.get(position);
                // 把选择的数据展示对应的TextView上,并设置颜色
                if(value.equals("全部期限")){
                    tvTime.setText("投资期限");
                    tvTime.setTextColor(getActivity().getResources().getColor(R.color.nfont));
                }else{
                    tvTime.setText(value);
                    tvTime.setTextColor(getActivity().getResources().getColor(R.color.number));
                }
                // 选择完后关闭popup窗口
                timePopup.dismiss();
            }
        });
        //设置全屏显示
        timePopup = new PopupWindow(timeList, tvTime.getWidth(), ActionBar.LayoutParams.WRAP_CONTENT, true);
        // 取得popup窗口的背景图片
        Drawable drawable = ContextCompat.getDrawable(getActivity(), R.drawable.popup_bg_corner);
        timePopup.setBackgroundDrawable(drawable);
        //popupWindow弹出后屏幕半透明
        BackgroudAlpha((float)0.5);
        timePopup.setFocusable(true);
        timePopup.setOutsideTouchable(true);
        timePopup.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                // 关闭popup窗口
                timePopup.dismiss();
                //恢复窗口透明度
                BackgroudAlpha((float)1);
            }
        });
    }

    /**
     * 初始化还款方式的popup窗口
     */
    private void initTwoSelectPopup() {
        wayList = new ListView(getActivity());
        WayData();
        // 设置适配器
        selectPopupAdapter=new SelectPopupAdapter(wayData,getActivity(),2);
        wayList.setAdapter(selectPopupAdapter);

        //判断当前是否有已经选中的按钮，有则将该按钮设置为选中状态
        if(!("").equals(PreferenceCache.getPfPositionTwo())){
            selectPopupAdapter.setFlg("1");
            int butPosition=Integer.parseInt(PreferenceCache.getPfPositionTwo());
            //            i=butPosition;
            selectPopupAdapter.setI(butPosition);
        }
        selectPopupAdapter.notifyDataSetChanged();
        // 设置RecyclerView点击事件监听
        wayList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //记录位置
                PreferenceCache.putPfPositionTwo(position+"");
                // 在这里获取item数据
                String value = wayData.get(position);
                // 把选择的数据展示对应的TextView上,并设置颜色
                if(value.equals("全部方式")){
                    tvWay.setText("还款方式");
                    tvWay.setTextColor(getActivity().getResources().getColor(R.color.nfont));
                }else{
                    tvWay.setText(value);
                    tvWay.setTextColor(getActivity().getResources().getColor(R.color.number));
                }
                // 选择完后关闭popup窗口
                wayPopup.dismiss();
            }
        });
        //设置全屏显示
        wayPopup = new PopupWindow(wayList, tvWay.getWidth(), ActionBar.LayoutParams.WRAP_CONTENT, true);
        // 取得popup窗口的背景图片
        Drawable drawable = ContextCompat.getDrawable(getActivity(), R.drawable.popup_bg_corner);
        wayPopup.setBackgroundDrawable(drawable);
        //popupWindow弹出后屏幕半透明
        BackgroudAlpha((float)0.5);
        wayPopup.setFocusable(true);
        wayPopup.setOutsideTouchable(true);
        wayPopup.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                // 关闭popup窗口
                wayPopup.dismiss();
                //恢复窗口透明度
                BackgroudAlpha((float)1);
            }
        });
    }

    //设置屏幕背景透明度
    private void BackgroudAlpha(float alpha) {
        // TODO Auto-generated method stub
        WindowManager.LayoutParams l = getActivity().getWindow().getAttributes();
        l.alpha = alpha;
        getActivity().getWindow().setAttributes(l);
    }

    /**
     * 初始化 投资期限下拉选项数据
     */
    private void TimeData() {
        timeData = new ArrayList<>();
        timeData.add("全部期限");
        timeData.add("期限<1个月");
        timeData.add("期限>=1个月");
        timeData.add("期限>=3个月");
        timeData.add("期限>=6个月");
        timeData.add("期限>=12个月");
    }
    /**
     * 初始化 还款方式下拉选项数据
     */
    private void WayData() {
        wayData = new ArrayList<>();
        wayData.add("全部方式");
        wayData.add("每月还息是");
        wayData.add("等额本息");
        wayData.add("等额本金");
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.mActivity = (Activity)context;
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if(hidden){
           return;
        }else{
            //重新加载数据
           initData();
        }
    }
}
