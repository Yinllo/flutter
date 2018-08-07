package com.desjf.dsjr.ui.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.desjf.dsjr.R;
import com.desjf.dsjr.adapter.TabFragmentAdapter;
import com.desjf.dsjr.base.BaseActivity;
import com.desjf.dsjr.ui.fragment.ProjectDetails.CertificateContractFragment;
import com.desjf.dsjr.ui.fragment.ProjectDetails.InvestmentRecordFragment;
import com.desjf.dsjr.ui.fragment.ProjectDetails.LoanDetailsFragment;
import com.desjf.dsjr.ui.fragment.ProjectDetails.PostLoanManagementFragment;
import com.desjf.dsjr.ui.fragment.ProjectDetails.RiskControlFragment;
import com.desjf.dsjr.ui.fragment.ProjectDetails.RiskEvaluationFragment;
import com.desjf.dsjr.utils.MiscUtil;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author YinL
 * @Describe  项目详情Activity（包括借款详情、风控流程、证件合同、投资记录）
 */
public class ProjectDetailsActivity extends BaseActivity {

    @BindView(R.id.tv_title)
    TextView tvTitle;//当前页面的标题
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private List<String> strings = new ArrayList<String>();
    private List<Fragment> fragments = new ArrayList<Fragment>();

    LoanDetailsFragment loanDetailsFragment;//借款详情
    CertificateContractFragment certificateContractFragment;//证件合同
    RiskControlFragment riskControlFragment;//风控流程
    InvestmentRecordFragment investmentRecordFragment;//投资记录
    RiskEvaluationFragment riskEvaluationFragment;//风险评估及提示
    PostLoanManagementFragment postLoanManagementFragment;//贷后管理

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project_details);
        ButterKnife.bind(this);
        initData();
        initView();
    }
    private void initView() {
        tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        viewPager.setAdapter(new TabFragmentAdapter(fragments, strings,
                getSupportFragmentManager(), this));

        //与ViewPager关联
        tabLayout.setupWithViewPager(viewPager);
        //设置指示器的长度
        setIndicatorWidth(tabLayout);

        //设置未选中和选中时的颜色
//        tabLayout.setTabTextColors(getResources().getColor(R.color.font_gray)
//                , getResources().getColor(R.color.nmainOrign));



        //设置根据来源设置默认选择项
//        if(BaseConfig.FROM_PO==1){
//            tabLayout.getTabAt(0).select();
//            viewPager.setCurrentItem(0);
//        }else if(BaseConfig.FROM_PO==2){
//            tabLayout.getTabAt(1).select();
//            viewPager.setCurrentItem(1);
//        }else if(BaseConfig.FROM_PO==3){
//            tabLayout.getTabAt(2).select();
//            viewPager.setCurrentItem(2);
//        }else{
//            tabLayout.getTabAt(3).select();
//            viewPager.setCurrentItem(3);
//        }

    }

    private void initData() {

        tvTitle.setText("某公司借款");

        loanDetailsFragment = new LoanDetailsFragment();
        fragments.add(loanDetailsFragment);
        strings.add("借款详情");

        certificateContractFragment = new CertificateContractFragment();
        fragments.add(certificateContractFragment);
        strings.add("证件合同");

        riskControlFragment = new RiskControlFragment();
        fragments.add(riskControlFragment);
        strings.add("风控流程");

        investmentRecordFragment = new InvestmentRecordFragment();
        fragments.add(investmentRecordFragment);
        strings.add("投资记录");

        riskEvaluationFragment=new RiskEvaluationFragment();
        fragments.add(riskEvaluationFragment);
        strings.add("风险评估及提示");

        //如果当前标的的状态为  收益中   则显示贷后管理
        if(1==1){
        postLoanManagementFragment=new PostLoanManagementFragment();
        fragments.add(postLoanManagementFragment);
        strings.add("贷后管理");
        }

    }

    public void setIndicatorWidth(final TabLayout tabLayout){
        //从源码得知 线的宽度是根据 tabView的宽度来设置的
        tabLayout.post(new Runnable() {
            @Override
            public void run() {
                try {
                    //拿到tabLayout的mTabStrip属性
                    LinearLayout mTabStrip = (LinearLayout) tabLayout.getChildAt(0);
                    //将dp转换成px
                    int dp10 = MiscUtil.dipToPx(tabLayout.getContext(), 10);
                    for (int i = 0; i < mTabStrip.getChildCount(); i++) {
                        View tabView = mTabStrip.getChildAt(i);
                        //拿到tabView的mTextView属性  tab的字数不固定一定用反射取mTextView
                        Field mTextViewField = tabView.getClass().getDeclaredField("mTextView");
                        mTextViewField.setAccessible(true);
                        TextView mTextView = (TextView) mTextViewField.get(tabView);
                        tabView.setPadding(0, 0, 0, 0);
                        //想要的效果是   字多宽线就多宽，所以测量mTextView的宽度
                        int width = 0;
                        width = mTextView.getWidth();
                        if (width == 0) {
                            mTextView.measure(0, 0);
                            width = mTextView.getMeasuredWidth();
                        }
                        //设置tab左右间距 ，因为源码中线的宽度是根据tabView的宽度来设置的，所以得注意这里不能使用Padding
                        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) tabView.getLayoutParams();
                        //指示器宽度值设置
                        params.width = width ;
                        //设置一下tabview的margin，不设置会连在一起
                        params.leftMargin = dp10;
                        params.rightMargin = dp10;
                        tabView.setLayoutParams(params);
                        tabView.invalidate();
                    }

                } catch (NoSuchFieldException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        });

    }

    @OnClick(R.id.iv_back)
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
        }
    }
}
