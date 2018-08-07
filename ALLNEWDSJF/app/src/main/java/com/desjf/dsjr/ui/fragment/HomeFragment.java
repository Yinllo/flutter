package com.desjf.dsjr.ui.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.desjf.dsjr.R;
import com.desjf.dsjr.adapter.AdAdapter;
import com.desjf.dsjr.base.BaseFragment;
import com.desjf.dsjr.ui.activity.InvestmentReturnActivity;
import com.desjf.dsjr.ui.activity.NoticeMessageActivity;
import com.desjf.dsjr.ui.activity.PrizeActivity;
import com.desjf.dsjr.utils.ToastUtils;
import com.jude.rollviewpager.RollPagerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @Author YinL
 * @Date 2018/1/15 0015
 * @Describe 首页Fragment
 */

public class HomeFragment extends BaseFragment {

    @BindView(R.id.home_tab_group)
    RadioGroup homeTabGroup;
    @BindView(R.id.btn_news)
    RadioButton news;//平台新闻
    @BindView(R.id.btn_profession)
    RadioButton profession;//行业速递
    @BindView(R.id.home_viewpager)  //新闻中心图片切换
    ViewPager homeViewpager;
    @BindView(R.id.tv_news_title)
    TextView newsTitle;//标题
    @BindView(R.id.tv_page_number)
    TextView pagerNumber;//页数

    protected Activity mActivity;

    private String[] imageUrls;
    // 在values文件假下创建了pager_image_ids.xml文件，并定义了3张轮播图对应的id，用于点击事件
    private int[] imgae_ids = new int[]{R.id.pager_image1,R.id.pager_image2,R.id.pager_image3};
    private ArrayList<ImageView> news_img_List;//图片集合
    private ArrayList<String> titleList;//标题集合

    private ArrayList<View> pageview;//图片集合
    @BindView(R.id.rpv_ad)
    RollPagerView rpvAd;//广告轮播控件
    //广告轮播View —— 适配器和图片集合
    private AdAdapter adapter;
    private List<String> ad_img_list = new ArrayList<>();



    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    protected void init(Bundle savedInstanceState) {

        initAd();
        initViewPager();

    }

    @OnClick({R.id.notice_more,R.id.ll_prize,R.id.ll_investment,R.id.ll_invite})
    public void OnClick(View view){
        switch (view.getId()) {
            case R.id.notice_more:
                Intent toNotice=new Intent(mActivity, NoticeMessageActivity.class);
                startActivity(toNotice);
                break;
            case R.id.ll_prize:
                //红包加息
                Intent toPrize=new Intent(mActivity, PrizeActivity.class);
                startActivity(toPrize);
                break;
            case R.id.ll_investment:
                //投资管理
                Intent toInvestmentRerutn=new Intent(mActivity, InvestmentReturnActivity.class);
                startActivity(toInvestmentRerutn);
                break;
            case R.id.ll_invite:
                //邀请
                break;

        }
    }

    //初始化广告轮播图      final HomeModel homeModel
    private void initAd() {
        //广告条轮播
        ad_img_list.clear();
            ad_img_list.add("https://www.dsp2p.com/upload/images/8993045097a542feb9212745fb411e89/792dd190ed545a84e80ed3d102eadd74.jpg");
            ad_img_list.add("https://www.dsp2p.com/upload/images/8993045097a542feb9212745fb411e89/792dd190ed545a84e80ed3d102eadd74.jpg");
            rpvAd.setPlayDelay(4000);
            adapter = new AdAdapter(rpvAd, ad_img_list, getActivity());
            rpvAd.setAdapter(adapter);

            rpvAd.setOnItemClickListener(new com.jude.rollviewpager.OnItemClickListener() {
                @Override
                public void onItemClick(int position) {
//                    Intent intent = new Intent(getActivity(), WebViewActivity.class);
//                    intent.putExtra("web", 4);
//                    intent.putExtra("4", homeModel.getADVERTISING_LIST().get(position).getLINK_URL());
//                    startActivity(intent);
                }
            });
        }


     //初始化 平台新闻/行业速递 模块
    private void initViewPager(){
        //设置图片
        imageUrls=new String[3];
        imageUrls[0]="https://www.dsp2p.com/upload/images/8993045097a542feb9212745fb411e89/792dd190ed545a84e80ed3d102eadd74.jpg";
        imageUrls[1]="http://pic30.nipic.com/20130626/8174275_085522448172_2.jpg";
        imageUrls[2]="http://pic18.nipic.com/20111215/577405_080531548148_2.jpg";

        news_img_List=new ArrayList<>();
        titleList=new ArrayList<>();

        //设置标题
        titleList.add("111111111111111");
        titleList.add("22222222222222222");
        titleList.add("333333333333333");
        newsTitle.setText(titleList.get(0));
        initViewPagerData();

        //默认选择为平台新闻
        homeTabGroup.check(R.id.btn_news);
        homeTabGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i) {
                    case R.id.btn_news:
                        //点击时改变颜色
                        news.setTextColor(getResources().getColor(R.color.font));
                        profession.setTextColor(getResources().getColor(R.color.font_gray));
                        //清空之前的数据，防止重复
                        imageUrls=null;
                        imageUrls=new String[3];
                        news_img_List.clear();
                        //加载图片url
                        imageUrls[0]="https://www.dsp2p.com/upload/images/8993045097a542feb9212745fb411e89/792dd190ed545a84e80ed3d102eadd74.jpg";
                        imageUrls[1]="http://pic30.nipic.com/20130626/8174275_085522448172_2.jpg";
                        imageUrls[2]="http://pic18.nipic.com/20111215/577405_080531548148_2.jpg";
                        titleList.add("111111111111111");
                        titleList.add("22222222222222222");
                        titleList.add("333333333333333");
                        //加载适配器
                        initViewPagerData();
                        break;
                    case R.id.btn_profession:
                        news.setTextColor(getResources().getColor(R.color.font_gray));
                        profession.setTextColor(getResources().getColor(R.color.font));
                        imageUrls=null;
                        imageUrls=new String[3];
                        news_img_List.clear();
                        imageUrls[0]="http://pic30.nipic.com/20130626/8174275_085522448172_2.jpg";
                        imageUrls[2]="http://pic.58pic.com/58pic/12/64/27/55U58PICrdX.jpg";
                        imageUrls[1]="https://www.dsp2p.com/upload/images/8993045097a542feb9212745fb411e89/792dd190ed545a84e80ed3d102eadd74.jpg";
                        titleList.add("4444444444444444444");
                        titleList.add("5555555555555555");
                        titleList.add("666666666666666666");
                        initViewPagerData();
                        break;
                }
            }
        });
    }
    //初始化图片加载
    private void initViewPagerData(){
        pagerNumber.setText("1/3");
        for(int j = 0; j < 3; j++){
            ImageView imageView = new ImageView(getActivity());
            imageView.setId((imgae_ids[j]));
            imageView.setOnClickListener(new pagerImageOnClick());//设置图片点击事件
            //得到可用的图片
            Glide.with(getActivity()).load(imageUrls[j]).asBitmap().into(imageView);
            news_img_List.add(imageView);
        }
        //数据适配器
        PagerAdapter mPagerAdapter = new PagerAdapter(){
            @Override
            //获取当前窗体界面数
            public int getCount() {
                // TODO Auto-generated method stub
                return news_img_List.size();
            }

            @Override
            //断是否由对象生成界面
            public boolean isViewFromObject(View arg0, Object arg1) {
                // TODO Auto-generated method stub
                return arg0==arg1;
            }
            //是从ViewGroup中移出当前View
            public void destroyItem(View arg0, int arg1, Object arg2) {
                ((ViewPager) arg0).removeView(news_img_List.get(arg1));
            }

            //返回一个对象，这个对象表明了PagerAdapter适配器选择哪个对象放在当前的ViewPager中
            public Object instantiateItem(View arg0, int arg1){
                ((ViewPager)arg0).addView(news_img_List.get(arg1));
                return news_img_List.get(arg1);
            }
        };
        //绑定适配器
        homeViewpager.setAdapter(mPagerAdapter);
        homeViewpager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                newsTitle.setText(titleList.get(position));
                pagerNumber.setText(position+1+"/3");
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        homeViewpager.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtils.showShortTost(getActivity(),"dddd");
            }
        });
    }
    //图片点击事件
    private class pagerImageOnClick implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.pager_image1:
                    Toast.makeText(mActivity, "图片1被点击", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.pager_image2:
                    Toast.makeText(mActivity, "图片2被点击", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.pager_image3:
                    Toast.makeText(mActivity, "图片3被点击", Toast.LENGTH_SHORT).show();
                    break;

            }
        }
    }
    @Override
    public void onResume() {
        super.onResume();
        if (rpvAd.isPlaying()) {
            rpvAd.resume();
        }
    }

    /**
     * 防止getActivity()空指针情况
     * @param context
     */
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
        }
    }
}



