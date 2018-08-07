package com.desjf.dsjr.fragment;


import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.desjf.dsjr.R;
import com.desjf.dsjr.activity.AccountMyNoticeActivity;
import com.desjf.dsjr.activity.InformationDisclosureActivity;
import com.desjf.dsjr.activity.InviteFriendsWebViewActivity;
import com.desjf.dsjr.activity.LoginActivity;
import com.desjf.dsjr.activity.StandarDetailsActivity;
import com.desjf.dsjr.activity.WebViewActivity;
import com.desjf.dsjr.adapter.AdAdapter;
import com.desjf.dsjr.adapter.ShouyeAdapter;
import com.desjf.dsjr.base.BaseFragment;
import com.desjf.dsjr.biz.HomeBiz;
import com.desjf.dsjr.biz.NoticeBiz;
import com.desjf.dsjr.biz.exception.BizFailure;
import com.desjf.dsjr.biz.exception.ZYException;
import com.desjf.dsjr.biz.retrofit.CallUtils;
import com.desjf.dsjr.biz.retrofit.InitHttpUtil;
import com.desjf.dsjr.biz.task.BizDataAsyncTask;
import com.desjf.dsjr.model.AllMoneyModel;
import com.desjf.dsjr.model.HomeModel;
import com.desjf.dsjr.model.NoticeModel;
import com.desjf.dsjr.utils.PreferenceCache;
import com.desjf.dsjr.utils.PreferencesUtil;
import com.desjf.dsjr.utils.ToastUtils;
import com.jude.rollviewpager.RollPagerView;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class ShouyeFragment extends BaseFragment {

    @BindView(R.id.rpv_ad)
    RollPagerView rpvAd;
    @BindView(R.id.stl_shouye)
    SwipeRefreshLayout stlShouye;
    private ShouyeAdapter shouyeAdapter;
    @BindView(R.id.ll_new_point)
    LinearLayout llNewPoint;
    @BindView(R.id.ll_information_disclosure)
    LinearLayout informationDisclosure;//信息披露
    @BindView(R.id.ll_operation_data)
    LinearLayout operationData;//运营数据
    @BindView(R.id.ll_invate_for_gift)
    LinearLayout llInvateForGift;
    @BindView(R.id.rv_shouye)
    RecyclerView rvShouye;
    //公告标题设置
    @BindView(R.id.auto_roll)
    TextView verticalScrollTV;
    //公告时间设置
    @BindView(R.id.date)
    TextView date;
    private int number = 0;
    private boolean isRunning = true;
    private List<NoticeModel> notice = null;

    private static final int msgKey1 = 1;
    TextView tv_time;//运营时间
    TextView allMoney;//累计本金
    TextView allInterest;//赚取利息
    long days;//计算出来的运营的天数
    int years;//计算出来的运营年数
    String startTime;//开始运营时间

    //广告轮播View
    private AdAdapter adapter;
    private List<String> str_list = new ArrayList<>();
    //rv数据
    private List<Object> total_list;
    private HomeModel homeModels;
    private List<Object> list_bean = new ArrayList<>();
    private List<HomeModel.SCATTEREDLISTBean> sca_bean = new ArrayList<>();
    private List<HomeModel.NEWFINANCELISTBean> news_bean = new ArrayList<>();
    private String news_point;
    private String about;
    private BroadcastReceiver receiver;//通  知刷新广播

    String str;//公告标题
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_shouye, container, false);
        ButterKnife.bind(this, view);
        receiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {

                onReceiveBroadcast(intent);
            }
        };


        IntentFilter filter = new IntentFilter();
        filter.addAction("shuaxin"); //token过期
        getActivity().registerReceiver(receiver, filter);
        //获取首页数据

        //公告轮播初始化
        initNotice();
        initShouyeData();
        initData();

        return view;
    }

    private void onReceiveBroadcast(Intent intent) {
        String action = intent.getAction();
        if (action.equals("shuaxin")) {
            initShouyeData();
        }
    }

    private void initData() {
        stlShouye.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                stlShouye.setProgressViewOffset(false, 0, 100);
                initShouyeData();
                initNotice();
            }
        });
        stlShouye.setColorSchemeResources(R.color.main);
        rvShouye.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent = new Intent(getActivity(), StandarDetailsActivity.class);
                if (position == 0) {
                    intent.putExtra("key", news_bean.get(position).getOID_PLATFORM_PRODUCTS_ID());
                    intent.putExtra("tender", news_bean.get(position).getTENDER_AWARD_SCALE());
                    intent.putExtra("status", news_bean.get(position).getPRODUCTS_STATUS());
                } else {
                    intent.putExtra("key", sca_bean.get(position - 1).getOID_PLATFORM_PRODUCTS_ID());
                    intent.putExtra("tender", sca_bean.get(position - 1).getTENDER_AWARD_SCALE());
                    intent.putExtra("status", sca_bean.get(position - 1).getPRODUCTS_STATUS());
                }

                startActivity(intent);
            }
        });

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }


    private void initShouyeData() {
        showLoadingDialog();
        @SuppressLint("StaticFieldLeak") BizDataAsyncTask<HomeModel> getHomeModel = new BizDataAsyncTask<HomeModel>() {
            @Override
            protected HomeModel doExecute() throws ZYException, BizFailure {
                return HomeBiz.getCaptchaImage();
            }

            @Override
            protected void onExecuteSucceeded(HomeModel homeModel) {
                hideLoadingDialog();

                homeModels = homeModel;
                stlShouye.setRefreshing(false);
                //处理广告条
                initAd(homeModel);
                //处理数据
                initRv(homeModel);

            }

            @Override
            protected void OnExecuteFailed(String error) {
                hideLoadingDialog();
            }
        };
        getHomeModel.execute();
    }

    private void initRv(HomeModel homeModel) {
        list_bean.clear();
        news_bean.clear();
        sca_bean.clear();
        for (int i = 0; i < homeModel.getNEW_FINANCE_LIST().size(); i++) {
            //list_bean.add(homeModel.getNEW_FINANCE_LIST().get(i));
            list_bean.add(homeModel.getNEW_FINANCE_LIST().get(i));
            news_bean.add(homeModel.getNEW_FINANCE_LIST().get(i));
        }
        for (int i = 0; i < homeModel.getSCATTERED_LIST().size(); i++) {
            list_bean.add(homeModel.getSCATTERED_LIST().get(i));
            sca_bean.add(homeModel.getSCATTERED_LIST().get(i));
        }

        //获取recylerView的数据
        shouyeAdapter = new ShouyeAdapter(list_bean, getActivity());
        addFooterView();
        rvShouye.setAdapter(shouyeAdapter);
        rvShouye.setLayoutManager(new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL));
        shouyeAdapter.notifyDataSetChanged();
        news_point = homeModel.getNOVICE_GUIDE_URL();
        about = homeModel.getABOUT_US_URL();
        PreferencesUtil.writeString(getActivity(), "about", about);

    }

    /**
     * 给首页添加尾部布局  并在此方法中实现功能
     */
    private void addFooterView() {
        View footerView = getActivity().getLayoutInflater().inflate(R.layout.shouye_footer_view, null);
        footerView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        shouyeAdapter.addFooterView(footerView);
        //设置当前运营时间
        tv_time=footerView.findViewById(R.id.tv_year);
        allMoney=footerView.findViewById(R.id.all_money);
        allInterest=footerView.findViewById(R.id.all_interest);
        footerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        //开始运营时间实时显示
        new TimeThread().start();
        /**
         * 用新接口获取 累计获取的交易本金和累计赚取的利息
         * @param
         */
        //累计交易本金
        CallUtils.call(getActivity(), InitHttpUtil.getHttpRequestService().getAllMoney(), new CallUtils.MyCallListener() {
            @Override
            public void onRespnseSuccess(String jsonString) {
                AllMoneyModel allMoneyModel= JSON.parseObject(jsonString,AllMoneyModel.class);
                allMoney.setText(allMoneyModel.getResult());
            }
        });
        //累计赚取利息
        CallUtils.call(getActivity(), InitHttpUtil.getHttpRequestService().getAllInterest(), new CallUtils.MyCallListener() {
            @Override
            public void onRespnseSuccess(String jsonString) {
                AllMoneyModel allMoneyModel= JSON.parseObject(jsonString,AllMoneyModel.class);
                allInterest.setText(allMoneyModel.getResult());
            }
        });

    }

    private void initAd(final HomeModel homeModel) {
        //广告条轮播
        str_list.clear();
        if (homeModel.getADVERTISING_LIST().size() != 0) {
            for (int i = 0; i < homeModel.getADVERTISING_LIST().size(); i++) {
                str_list.add(homeModel.getADVERTISING_LIST().get(i).getIMG_PATH());
            }
            rpvAd.setPlayDelay(4000);
            adapter = new AdAdapter(rpvAd, str_list, getActivity());
            rpvAd.setAdapter(adapter);

            rpvAd.setOnItemClickListener(new com.jude.rollviewpager.OnItemClickListener() {
                @Override
                public void onItemClick(int position) {
                    Intent intent = new Intent(getActivity(), WebViewActivity.class);
                    intent.putExtra("web", 4);
                    intent.putExtra("4", homeModel.getADVERTISING_LIST().get(position).getLINK_URL());
                    startActivity(intent);
                }
            });
        }
    }

    //获取首页公告内容
    private void initNotice() {
//        showLoadingDialog();
        @SuppressLint("StaticFieldLeak") BizDataAsyncTask<List<NoticeModel>> getNotice = new BizDataAsyncTask<List<NoticeModel>>() {
            @Override
            protected List<NoticeModel> doExecute() throws ZYException, BizFailure {
                return NoticeBiz.getNoticeInfo("", "0", "20");
            }

            @Override
            protected void onExecuteSucceeded(List<NoticeModel> noticeModels) {
//                hideLoadingDialog();
                iniNoticetData(noticeModels);
            }

            @Override
            protected void OnExecuteFailed(String error) {
                hideLoadingDialog();
                ToastUtils.showTost(getActivity(), error.toString());
            }
        };
        getNotice.execute();
    }


    private void iniNoticetData(final List<NoticeModel> noticeModels) {
        notice = new ArrayList<NoticeModel>();
        for (int i = 0; i < 1; i++) {
            notice.add(noticeModels.get(i));
        }
        //将文字显示为  标题+时间
        if (notice.get(0).getTITLE().length() < 14) {
            str = notice.get(0).getTITLE();
        }
        else {
            str = notice.get(0).getTITLE().substring(0, 14) + "...";
        }
        verticalScrollTV.setText(str);
        date.setText(notice.get(0).getPUBLISH_DATE());
        verticalScrollTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), WebViewActivity.class);
                /**
                 * 当前公告访问路径
                 */
                intent.putExtra("1", "http://www.dsp2p.com/iloanWebService/content.html?newsId=" + noticeModels.get(number % notice.size()).getID());
                intent.putExtra("web", 1);
                startActivity(intent);
            }
        });
    }


    @OnClick({R.id.ll_new_point,R.id.ll_invate_for_gift,  R.id.notice_more,R.id.ll_information_disclosure,R.id.ll_operation_data})
    public void onClick(View view) {
        switch (view.getId()) {
            //新手指引
            case R.id.ll_new_point:
                Intent news = new Intent(getActivity(), WebViewActivity.class);
                news.putExtra("2", news_point);
                news.putExtra("web", 2);
                startActivity(news);
                break;
            case R.id.ll_invate_for_gift:
                //邀请好友
                if (PreferenceCache.getToken().equals("")) {
                    Intent inten = new Intent(getActivity(), LoginActivity.class);
                    startActivity(inten);
                } else {
                    Intent intent = new Intent(getActivity(), InviteFriendsWebViewActivity.class);
                    startActivity(intent);
                }
                break;
            case R.id.notice_more:
                //更多公告
                Intent noticeintent = new Intent(getActivity(), AccountMyNoticeActivity.class);
                startActivity(noticeintent);
                break;
            case R.id.ll_information_disclosure:
                //信息披露
                Intent information = new Intent(getActivity(), InformationDisclosureActivity.class);
                startActivity(information);
                break;
            case R.id.ll_operation_data:
                //运营数据
                Intent operation = new Intent(getActivity(), WebViewActivity.class);
                operation.putExtra("web", 26);
                startActivity(operation);
                break;
        }

    }

    /**
     * 运营时间设置操作
     * @param
     */
    public class TimeThread extends Thread {
        @Override
        public void run () {
            do {
                try {
                    Thread.sleep(1000);
                    Message msg = new Message();
                    msg.what = msgKey1;
                    mHandler.sendMessage(msg);
                }
                catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } while(true);
        }
    }

    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage (Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case msgKey1:
                    tv_time.setText(getTime());
                    break;
                default:
                    break;
            }
        }
    };
    //获得当前时分秒
    public String getTime(){

        //字符串拼接时间
        StringBuffer allTime=new StringBuffer();
        final Calendar c = Calendar.getInstance();
        c.setTimeZone(TimeZone.getTimeZone("GMT+8:00"));
        int mYear = c.get(Calendar.YEAR);//年
        int mMonth=c.get(Calendar.MONTH)+1;//月（月是从0开始算的）
        int mDay=c.get(Calendar.DAY_OF_MONTH);//日
        String mHour = String.valueOf(c.get(Calendar.HOUR_OF_DAY));//时
        String mMinute = String.valueOf(c.get(Calendar.MINUTE));//分
        String mSecond = String.valueOf(c.get(Calendar.SECOND));//秒
        /**
         *  根据当前时间和开始运营时间对比计算年数  (因为每年的天数不同，如果每年按365天算，366天的年数就会让天数多出一天
         *  直接使用开始和结束时间的时间戳来计算会产生天数上的误差)
         *
         *  因此算法应该为判断当前时间的月日是否超过了开始时间
         *  超过了则年份为当前年份减去开始年份，天数则用当前时间的时间戳减去当前年份的9月24日0点的时间戳来得到
         *  没超过则年份为当前年份减去开始年份再减去1，天数则用当前时间的时间戳减去当前年的上一年的9月24日0点的时间戳来得到
         *
         *  先判断当前月日是否大于等于9月24日  （因为从2015-9-24开始算开始时间）
         *  如果当前时间 大于等于9.24 则计算天数的时间戳从当前年的9.24日开始
         *
         */
        if(mMonth>=9&&mDay>=24){
            years=mYear-2015;
            startTime=mYear+"年9月24日00时00分00秒";
        }else{
            years=mYear-2015-1;
            startTime=mYear-1+"年9月24日00时00分00秒";
        }
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date1 = formatter.format(currentTime);
        // 获取服务器返回的时间戳 转换成"yyyy-MM-dd HH:mm:ss"
        int time=Integer.parseInt(getTime(startTime));
        String date2 = formatData("yyyy-MM-dd HH:mm:ss", time);//2017-9-24 00:00:00

        // 计算的时间差
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");


        try {
            Date d1 = df.parse(date1);
            Date d2 = df.parse(date2);
            long diff = d1.getTime() - d2.getTime();// 这样得到的差值是微秒级别
            days = diff / (1000 * 60 * 60 * 24);

        } catch (Exception e) {
        }

        allTime.append(years+"年"+"" + days + "天"+mHour+"时"+mMinute+"分"+mSecond+"秒");
        return allTime+"";
    }
    //将时间戳转换成时间
    public static String formatData(String dataFormat, long timeStamp) {
        if (timeStamp == 0) {
            return "";
        }
        timeStamp = timeStamp * 1000;
        String result = "";
        SimpleDateFormat format = new SimpleDateFormat(dataFormat);
        result = format.format(new Date(timeStamp));
        return result;
    }

    //将字符串转为时间戳
    public String getTime(String user_time) {
        String re_time = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日HH时mm分ss秒");
        Date d;
        try {
            d = sdf.parse(user_time);
            long l = d.getTime();
            String str = String.valueOf(l);
            re_time = str.substring(0, 10);
        }catch (ParseException e) {
            // TODO Auto-generated catch block e.printStackTrace();
        }
        return re_time;
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (hidden) {
            //Fragment隐藏时
            return;
        } else {
            //Fragment显示时
            initShouyeData();
        }
    }

        @Override
        public void onResume() {
            super.onResume();
            if (rpvAd.isPlaying()) {
                rpvAd.resume();
            }
        }


        @Override
        public void onStop() {
            super.onStop();
           //取消发送消息
            mHandler.removeCallbacksAndMessages(null);
        }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        isRunning = false;
        mHandler.removeMessages(1);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mHandler.removeMessages(1);
        getActivity().unregisterReceiver(receiver);
    }
}

