package com.desjf.dsjr.activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.desjf.dsjr.R;
import com.desjf.dsjr.base.BaseActivity;
import com.desjf.dsjr.base.BaseApplication;
import com.desjf.dsjr.biz.ProjectInfoBiz;
import com.desjf.dsjr.biz.exception.BizFailure;
import com.desjf.dsjr.biz.exception.ZYException;
import com.desjf.dsjr.biz.task.BizDataAsyncTask;
import com.desjf.dsjr.config.DsjrConfig;
import com.desjf.dsjr.model.AccountModel;
import com.desjf.dsjr.model.ProjectInfoModel;
import com.desjf.dsjr.util.MyDialogUtil;
import com.desjf.dsjr.utils.BarUtils;
import com.desjf.dsjr.utils.PreferenceCache;
import com.desjf.dsjr.utils.ToastUtils;
import com.desjf.dsjr.widget.TimerView;

import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class StandarDetailsActivity extends BaseActivity {
    @BindView(R.id.iv_project_back)
    ImageView ivProjectBack;
    @BindView(R.id.tv_project_nhll)
    TextView tvProjectNhll;
    @BindView(R.id.tv_project_syqx)
    TextView tvProjectSyqx;//标的期限
    @BindView(R.id.tv_project_jkje)
    TextView tvProjectJkje;//标的总金额
    @BindView(R.id.title)
    TextView tvProjectTitle;//标的名称
    @BindView(R.id.pb_project)
    ProgressBar pbProject;
    @BindView(R.id.tv_project_sykt)
    TextView tvProjectSykt;

    @BindView(R.id.see)
    TextView see;

    @BindView(R.id.tv_shouye_bfb)
    TextView tvMbiao;
    @BindView(R.id.tv_project_hkfs)
    TextView tvProjectHkfs;
    @BindView(R.id.tv_project_qxsj)
    TextView tvProjectQxsj;
    @BindView(R.id.tv_project_svsj)
    TimerView tvProjectSvsj;
    @BindView(R.id.btn_project_qxdl)
    Button btnProjectQxdl;
    @BindView(R.id.timerView_tag)
    TextView timerViewTag;
    @BindView(R.id.tv_fbsj)
    TextView tvFbsj;
    @BindView(R.id.tv_project_qtje) //起投金额
    TextView initMoney;
    @BindView(R.id.ll_progress)
    LinearLayout ll_progress;
    private Context context = this;
    @BindView(R.id.ll_standar_details)
    LinearLayout llStandarDetails;

    @BindView(R.id.ll_paper)
    LinearLayout paper;
    @BindView(R.id.ll_control)
    LinearLayout control;
    @BindView(R.id.ll_record)
    LinearLayout record;
    @BindView(R.id.ll_manager)
    LinearLayout manager;

    @BindView(R.id.progesss_value1)
    TextView progressValue;
    @BindView(R.id.type)
    TextView type;
    @BindView(R.id.tv_jiaxi)
    TextView jiaxi;

    private ProjectInfoModel projectInfoModels;
    private String id = "";
    private String status = "";
    private String tender = "";
    public static StandarDetailsActivity mInstace = null;
    private AccountModel accountModel;
    private String name,idCard;
    private int w;//屏幕宽度

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_standar_details);
        ButterKnife.bind(this);
        //获取散标信息，项目详情
        BarUtils.setColor(this, getResources().getColor(R.color.endmian), 0);
        mInstace = this;
        BaseApplication baseApplication = (BaseApplication) getApplication();
        accountModel = baseApplication.getAccountModel();

        if (accountModel!=null){
            name = accountModel.getUSERNAME();
            idCard = accountModel.getIDCARD();
        }
        Intent intent = getIntent();
        id = intent.getStringExtra("key");

        status = intent.getStringExtra("status");
        tender = intent.getStringExtra("tender");

        initData();
        getSbInfo();

    }

    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {
            setPos();
        }
    }

    private void initData() {
        llStandarDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //跳转到项目详情界面 的项目详情
                if (PreferenceCache.getToken().isEmpty()) { //没登录进入 未登录状态的项目详情
                    Intent i = new Intent(StandarDetailsActivity.this, LoginActivity.class);
                    startActivity(i);
                }else{
                    DsjrConfig.FROM_PD=1;
                    Intent intent = new Intent(StandarDetailsActivity.this, ProjectDetailsActivity.class);
                    intent.putExtra("Info", projectInfoModels);
                    intent.putExtra("id", id);
                    startActivity(intent);
                }
            }
        });
        paper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //跳转到项目详情界面  的证件合同
                if (PreferenceCache.getToken().isEmpty()) { //没登录进入 未登录状态的项目详情
                    Intent i = new Intent(StandarDetailsActivity.this, LoginActivity.class);
                    startActivity(i);
                } else {
                    DsjrConfig.FROM_PD = 2;
                    Intent intent = new Intent(StandarDetailsActivity.this, ProjectDetailsActivity.class);
                    intent.putExtra("Info", projectInfoModels);
                    intent.putExtra("id", id);
                    startActivity(intent);
                }
            }
        });
        control.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //跳转到项目详情界面  的风控流程
                if (PreferenceCache.getToken().isEmpty()) { //没登录进入 未登录状态的项目详情
                    Intent i = new Intent(StandarDetailsActivity.this, LoginActivity.class);
                    startActivity(i);
                } else {
                    DsjrConfig.FROM_PD = 3;
                    Intent intent = new Intent(StandarDetailsActivity.this, ProjectDetailsActivity.class);
                    intent.putExtra("Info", projectInfoModels);
                    intent.putExtra("id", id);
                    startActivity(intent);
                }
            }
        });
        record.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //跳转到项目详情界面   投资记录
                if (PreferenceCache.getToken().isEmpty()) { //没登录进入 未登录状态的项目详情
                    Intent i = new Intent(StandarDetailsActivity.this, LoginActivity.class);
                    startActivity(i);
                } else {
                    DsjrConfig.FROM_PD = 4;
                    Intent intent = new Intent(StandarDetailsActivity.this, ProjectDetailsActivity.class);
                    intent.putExtra("Info", projectInfoModels);
                    intent.putExtra("id", id);
                    startActivity(intent);
                }
            }
        });
    }

    private void getSbInfo() {
        showLoadingDialog();
        @SuppressLint("StaticFieldLeak") BizDataAsyncTask<ProjectInfoModel> getProjectInfo = new BizDataAsyncTask<ProjectInfoModel>() {
            @Override
            protected ProjectInfoModel doExecute() throws ZYException, BizFailure {
                return ProjectInfoBiz.getProjectInfo(id);
            }

            @Override
            protected void onExecuteSucceeded(ProjectInfoModel projectInfoModel) {
                hideLoadingDialog();
                projectInfoModels = projectInfoModel;
                initInfo(projectInfoModel);
            }

            @Override
            protected void OnExecuteFailed(String error) {
                hideLoadingDialog();
                ToastUtils.showTost(context, error.toString());
            }
        };
        getProjectInfo.execute();
    }

    private void initInfo(ProjectInfoModel projectInfoModel) {
        tvProjectNhll.setText(projectInfoModel.getFINANCE_INTEREST_RATE());//年化率
        //加息
        if(projectInfoModel.getAddRate().equals("0")){
            jiaxi.setVisibility(View.INVISIBLE);
        }else{
            jiaxi.setVisibility(View.VISIBLE);
            jiaxi.setText("+"+projectInfoModel.getAddRate()+"%");
        }
        tvProjectTitle.setText(projectInfoModel.getPRODUCTS_TITLE());//标的标题
        tvProjectSyqx.setText(projectInfoModel.getFINANCE_PERIOD() + projectInfoModel.getINTEREST_RATE_TYPE());//期限
        initMoney.setText(projectInfoModel.getTENDER_MIN_CAPTION());//起投金额
        tvProjectJkje.setText(projectInfoModel.getFINANCE_AMOUNT() + projectInfoModel.getFINANCE_AMOUNT_UNIT());//总金额
        //判断是否为流标
        if (projectInfoModel.getWANDER_FLG().equals("0")) {
//            非流标
            tvProjectSykt.setText(projectInfoModel.getSURPLUS_AMOUNT());
        } else {
            //流标
            tvProjectSykt.setText(projectInfoModel.getSURPLUS_FEN());
        }

        //设置进度条值
        String tenderawardscale = projectInfoModel.getFINANCE_AMOUNT_SCALE();
        if (tenderawardscale.equals("0%")) {
            pbProject.setProgress(0);
            progressValue.setText("0%");
            setPos();
        } else {
            String str = tenderawardscale.substring(0, tenderawardscale.indexOf("."));
            int i = Integer.parseInt(str);
            pbProject.setProgress(i);
            //根据当前进度值来设置显示进度的背景图片
            if(i<=3){
                pbProject.setProgress(i);
                progressValue.setBackgroundResource(R.drawable.p_left);
                progressValue.setText(i+"%");
            }else if (3<i&&i<93){
                pbProject.setProgress(i);
                progressValue.setBackgroundResource(R.drawable.p_middle);
                progressValue.setText(i+"%");
            }else{
                pbProject.setProgress(i);
                progressValue.setBackgroundResource(R.drawable.p_right);
                progressValue.setText(i+"%");
            }
            setPos();
        }

            tvMbiao.setText(projectInfoModel.getFULL_TIP());

        if (projectInfoModel.getFINANCE_REPAY_TYPE().toString().equals("1")) {
            tvProjectHkfs.setText("等额本金");
        } else if (projectInfoModel.getFINANCE_REPAY_TYPE().toString().equals("2")) {
            tvProjectHkfs.setText("等额本息");
        } else if (projectInfoModel.getFINANCE_REPAY_TYPE().toString().equals("3")) {
            tvProjectHkfs.setText("到期还本付息");
        } else if (projectInfoModel.getFINANCE_REPAY_TYPE().toString().equals("4")) {
            tvProjectHkfs.setText("按月付息，到期还本");
        } else if (projectInfoModel.getFINANCE_REPAY_TYPE().toString().equals("5")) {
            tvProjectHkfs.setText("按季付息，到期还本");
        }
        if (projectInfoModel.getSTART_INTEREST_TYPE().toString().equals("1")) {
            tvProjectQxsj.setText("17:00前满标当日计息,否则次日计息");
        } else {
            tvProjectQxsj.setText("即投即起息");
        }
//        tvProjectBzfs.setText(projectInfoModel.getGUARANTEE_TYPE());
//        tvProjectSvsj.setText(projectInfoModel.getFINANCE_START_DATE());
        String finance_amount_scale = projectInfoModel.getFINANCE_AMOUNT_SCALE();
        String str = finance_amount_scale.substring(0, finance_amount_scale.length() - 4);
        int i = Integer.parseInt(str);
        pbProject.setProgress(i);
        String finance_end_date = projectInfoModel.getFINANCE_END_DATE();
        String system_date_now = projectInfoModel.getSYSTEM_DATE_NOW();
        String finance_start_date = projectInfoModel.getFINANCE_START_DATE();
        //如果当前没有登录
        if (PreferenceCache.getToken().isEmpty()) {
            btnProjectQxdl.setText("立即投资");

            //TODO 未登录状态下加上时间
            if (projectInfoModel.getPRODUCTS_STATUS().toString().equals("未开始")) {
                setTimerView(system_date_now, finance_start_date);
            } else if (projectInfoModel.getPRODUCTS_STATUS().toString().equals("立即投资")) {
                setTimerView(system_date_now, finance_end_date);
            } else if (projectInfoModel.getPRODUCTS_STATUS().toString().equals("满标待审")) {
                timerViewTag.setText("发布时间");
                tvProjectSvsj.setVisibility(View.GONE);
                tvFbsj.setVisibility(View.VISIBLE);
                tvFbsj.setText(projectInfoModel.getFINANCE_START_DATE());
                btnProjectQxdl.setText("已满标");
                btnProjectQxdl.setClickable(false);
                btnProjectQxdl.setBackgroundColor(getResources().getColor(R.color.graybutton));
                setTimerView(system_date_now, finance_end_date);
            } else if (projectInfoModel.getPRODUCTS_STATUS().toString().equals("已完成")) {
                timerViewTag.setText("发布时间");
                tvProjectSvsj.setVisibility(View.GONE);
                tvFbsj.setVisibility(View.VISIBLE);
                tvFbsj.setText(projectInfoModel.getFINANCE_START_DATE());
                btnProjectQxdl.setText("已完成");
                btnProjectQxdl.setClickable(false);
                btnProjectQxdl.setBackgroundColor(getResources().getColor(R.color.graybutton));
                setTimerView(system_date_now, finance_end_date);
            } else if (projectInfoModel.getPRODUCTS_STATUS().toString().equals("还款中")) {
                timerViewTag.setText("发布时间");
                tvFbsj.setVisibility(View.VISIBLE);
                tvFbsj.setText(projectInfoModel.getFINANCE_START_DATE());
                tvProjectSvsj.setVisibility(View.GONE);
                manager.setVisibility(View.VISIBLE);//显示贷后管理
                tvFbsj.setVisibility(View.VISIBLE);
                btnProjectQxdl.setText("收益中");
                btnProjectQxdl.setClickable(false);
                btnProjectQxdl.setBackgroundColor(getResources().getColor(R.color.graybutton));
                setTimerView(system_date_now, finance_end_date);
            }
        } else {
            if (projectInfoModel.getPRODUCTS_STATUS().toString().equals("未开始")) {
                btnProjectQxdl.setText("即将发布");
                btnProjectQxdl.setClickable(false);
                setTimerView(system_date_now, finance_start_date);
            } else if (projectInfoModel.getPRODUCTS_STATUS().toString().equals("立即投资")) {
                btnProjectQxdl.setText("立即投资");
                setTimerView(system_date_now, finance_end_date);
            } else if (projectInfoModel.getPRODUCTS_STATUS().toString().equals("满标待审")) {
                timerViewTag.setText("发布时间");
                tvProjectSvsj.setVisibility(View.GONE);
                tvFbsj.setVisibility(View.VISIBLE);
                tvFbsj.setText(projectInfoModel.getFINANCE_START_DATE());
                btnProjectQxdl.setText("已满标");
                btnProjectQxdl.setClickable(false);
                btnProjectQxdl.setBackgroundColor(getResources().getColor(R.color.graybutton));
                setTimerView(system_date_now, finance_end_date);
            } else if (projectInfoModel.getPRODUCTS_STATUS().toString().equals("已完成")) {
                timerViewTag.setText("发布时间");
                tvProjectSvsj.setVisibility(View.GONE);
                tvFbsj.setVisibility(View.VISIBLE);
                tvFbsj.setText(projectInfoModel.getFINANCE_START_DATE());
                btnProjectQxdl.setText("已还清");
                btnProjectQxdl.setClickable(false);
                btnProjectQxdl.setBackgroundColor(getResources().getColor(R.color.graybutton));
                setTimerView(system_date_now, finance_end_date);
            } else if (projectInfoModel.getPRODUCTS_STATUS().toString().equals("还款中")) {
                timerViewTag.setText("发布时间");
                tvFbsj.setVisibility(View.VISIBLE);
                tvFbsj.setText(projectInfoModel.getFINANCE_START_DATE());
                tvProjectSvsj.setVisibility(View.GONE);
                manager.setVisibility(View.VISIBLE);//显示贷后管理
                tvFbsj.setVisibility(View.VISIBLE);
                btnProjectQxdl.setText("收益中");
                btnProjectQxdl.setClickable(false);
                btnProjectQxdl.setBackgroundColor(getResources().getColor(R.color.graybutton));
                setTimerView(system_date_now, finance_end_date);
            }

        }
    //设置当前标的 的名称
        /**
         * 1：新手标
         * 3:满标待审  已满标带计息
         4:立即投资  可投资
         5:还款中  已满标已计息
         6:已完成  已还本付息
         */
        if (projectInfoModel.getNOVICE_FLG().toString().equals("0")) {
            //非新手标  是否有广告显示
            if(("").equals(projectInfoModel.getADVERTISE_WORD())){
                //推荐表
                type.setText("");
                type.setTextColor(context.getResources().getColor(R.color.white));
                type.setBackgroundDrawable(context.getResources().getDrawable(R.drawable.text_view_type_border_white));
            }else{
                type.setText(projectInfoModel.getADVERTISE_WORD());
                type.setTextColor(context.getResources().getColor(R.color.skyblue));
                type.setBackgroundResource(R.drawable.text_view_type_border);
                type.setPadding(5,0,5,0);
            }
        }else{
            //新手标
//            type.setText("新手标，每人限投两万");
            type.setText(projectInfoModel.getADVERTISE_WORD());
            type.setTextColor(context.getResources().getColor(R.color.red));
            type.setBackgroundResource(R.drawable.text_view_type_border_red);
            type.setPadding(5,0,5,0);
        }
    }

    /**
     * 设置进度显示在对应的位置
     */
    public void setPos(){
        //获得屏幕宽度
        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics dm = new DisplayMetrics();// 创建了一张白纸
        windowManager.getDefaultDisplay().getMetrics(dm);// 给白纸设置宽高

        //获得指示器的父布局
        ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) progressValue.getLayoutParams();
        int pro = pbProject.getProgress();
        //获得矩形指示器（TextView）的宽度
        int ws = View.MeasureSpec.makeMeasureSpec(0,
                View.MeasureSpec.UNSPECIFIED);
        int h = View.MeasureSpec.makeMeasureSpec(0,
                View.MeasureSpec.UNSPECIFIED);
        progressValue.measure(ws, h);
        int tW = progressValue.getMeasuredWidth();
        if( pbProject.getProgress()==100){
            w =windowManager.getDefaultDisplay().getWidth()-(tW-5);
        }else if(pbProject.getProgress()<=7){
            w =windowManager.getDefaultDisplay().getWidth()-tW;
        }else if(pbProject.getProgress()>=93){
            w =windowManager.getDefaultDisplay().getWidth()-(tW+20);
        }
        else{
            w =windowManager.getDefaultDisplay().getWidth()-tW;
        }

        //计算偏移量
        if (w * pro / 100 + tW * 0.3 > w) {
            params.leftMargin = (int) (w - tW * 1.1);
        } else if (w * pro / 100 < tW * 0.7) {
                params.leftMargin = 0;
        } else {
            params.leftMargin = (int) (w * pro / 100 - tW * 0.7);
        }
        progressValue.setLayoutParams(params);
    }



    private void setTimerView(String system_date_now, String finance_start_date) {
        if (!TextUtils.isEmpty(system_date_now) && !TextUtils.isEmpty(finance_start_date)) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date curDate = null;//当前时间
            Date endDate = null;//结束时间
            Long compTime = 0L;//差值时间
            try {
                curDate = sdf.parse(system_date_now);
                endDate = sdf.parse(finance_start_date);
                compTime = endDate.getTime() - curDate.getTime();//获取时间差
                if (compTime > 0) {
                    tvProjectSvsj.start(compTime);
                } else {
                    tvProjectSvsj.updateShow(0);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (tvProjectSvsj != null)
            tvProjectSvsj.stop();
    }

    @OnClick({R.id.iv_project_back, R.id.btn_project_qxdl,R.id.see,R.id.ll_risk,R.id.ll_manager})
    public void onClick(View view) {
        switch (view.getId()) {
            //查看还款方式的计算公式
            case R.id.see:
                MyDialogUtil.showSimpleDialog(context,
                        "每月还利息，最后一期还所有本金和最后一期利息。借款金额为X，年利率为Y，借款期限为Z月，" +
                                "则每月应还利息计算公式为：X×Y/12," +
                                "应还总利息计算公式为：X×Y/12×Z。" +
                                "应还本金为X。","知道了");
                break;
            case R.id.iv_project_back:
                if (tvProjectSvsj != null) {
                    tvProjectSvsj.stop();
                }
                finish();
                break;
            //风险评估及提示
            case R.id.ll_risk:
                if (PreferenceCache.getToken().isEmpty()) { //没登录进入 未登录状态的项目详情
                    Intent i = new Intent(StandarDetailsActivity.this, LoginActivity.class);
                    startActivity(i);
                } else {
                    Intent news = new Intent(context, WebViewActivity.class);
                    news.putExtra("web", 29);
                    startActivity(news);
                }
                break;
                //贷后管理
            case R.id.ll_manager:
                if (PreferenceCache.getToken().isEmpty()) { //没登录进入 未登录状态的项目详情
                    Intent i = new Intent(StandarDetailsActivity.this, LoginActivity.class);
                    startActivity(i);
                } else {
                    Intent news = new Intent(context, WebViewActivity.class);
                    news.putExtra("web", 30);
                    startActivity(news);
                }
                break;
                //立即投资
            case R.id.btn_project_qxdl:
                if (PreferenceCache.getToken().isEmpty()) { //没登录进入 未登录状态的项目详情
                    Intent i = new Intent(StandarDetailsActivity.this, InvestmentDetailsActivity.class);
                    i.putExtra("Info", projectInfoModels);
                    i.putExtra("ID", id);
                    startActivity(i);
                } else {
                    Intent i = new Intent(StandarDetailsActivity.this, NewInvestmentDetailsActivity.class);
                    i.putExtra("Info", projectInfoModels);
                    i.putExtra("ID", id);
                    startActivity(i);
                }
                break;
        }
    }
}
