package com.desjf.dsjr.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.desjf.dsjr.R;
import com.desjf.dsjr.base.BaseActivity;
import com.desjf.dsjr.base.BaseFragment;
import com.desjf.dsjr.config.DsjrConfig;
import com.desjf.dsjr.fragment.FkclFragment;
import com.desjf.dsjr.fragment.InvestRecordFragment;
import com.desjf.dsjr.fragment.NewBrrowDataFragment;
import com.desjf.dsjr.fragment.NewProjectDetailsFragment;
import com.desjf.dsjr.model.InvestmentInfoModel;
import com.desjf.dsjr.model.ProjectInfoModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ProjectDetailsActivity extends BaseActivity {
    @BindView(R.id.fl_project_details)
    FrameLayout flProjectDetails;
    @BindView(R.id.iv_details_back)
    ImageView ivDetailsBack;
    @BindView(R.id.btn_project_details)
    RadioButton btnProjectDetails;
    @BindView(R.id.btn_borrow)
    RadioButton btnBorrow;
    @BindView(R.id.btn_fkcl)
    RadioButton btnFkcl;
    @BindView(R.id.btn_record)
    RadioButton btnRecord;
    private List<BaseFragment> list_fragment;
    private Context context = this;
    @BindView(R.id.rg_project_details)
    RadioGroup rgProjectDetails;
    private String id;
    private int showId=-1;//显示哪个fragment
    private InvestmentInfoModel investmentInfoModels;
    private ProjectInfoModel projectInfoModel;
    public InvestmentInfoModel getInvestmentInfoModels() {
        return investmentInfoModels;
    }

    public void setInvestmentInfoModels(InvestmentInfoModel investmentInfoModels) {
        this.investmentInfoModels = investmentInfoModels;
    }

    public ProjectInfoModel getProjectInfoModel() {
        return projectInfoModel;
    }

    public void setProjectInfoModel(ProjectInfoModel projectInfoModel) {
        this.projectInfoModel = projectInfoModel;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project_details);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        id = intent.getStringExtra("id");
        projectInfoModel = (ProjectInfoModel) intent.getSerializableExtra("Info");
        getDetails();

        initData();
        initView();
    }

    private void getDetails() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    private void initView() {
        //默认选择项
        if(DsjrConfig.FROM_PD==1){
            rgProjectDetails.check(R.id.btn_project_details);
            getSupportFragmentManager().beginTransaction()
                    .show(list_fragment.get(0))
                    .commit();
        }else if(DsjrConfig.FROM_PD==2){
            rgProjectDetails.check(R.id.btn_borrow);
            getSupportFragmentManager().beginTransaction()
                    .show(list_fragment.get(1))
                    .commit();
        }else if(DsjrConfig.FROM_PD==3){
            rgProjectDetails.check(R.id.btn_fkcl);
            getSupportFragmentManager().beginTransaction()
                    .show(list_fragment.get(2))
                    .commit();
        }else if(DsjrConfig.FROM_PD==4){
            rgProjectDetails.check(R.id.btn_record);
            getSupportFragmentManager().beginTransaction()
                    .show(list_fragment.get(3))
                    .commit();
        }

        rgProjectDetails.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                for (int j = 0; j < list_fragment.size(); j++) {
                    getSupportFragmentManager().beginTransaction()
                            .hide(list_fragment.get(j))
                            .commitAllowingStateLoss();
                }
                switch (i) {
                    case R.id.btn_project_details:
                        getSupportFragmentManager().beginTransaction()
                                .show(list_fragment.get(0))
                                .commitAllowingStateLoss();
                        break;
                    case R.id.btn_borrow:
                        getSupportFragmentManager().beginTransaction()
                                .show(list_fragment.get(1))
                                .commitAllowingStateLoss();
                        break;
                    case R.id.btn_fkcl:
                        getSupportFragmentManager().beginTransaction()
                                .show(list_fragment.get(2))
                                .commitAllowingStateLoss();
                        break;
                    case R.id.btn_record:
                        getSupportFragmentManager().beginTransaction()
                                .show(list_fragment.get(3))
                                .commitAllowingStateLoss();
                        break;
                }
            }
        });
    }

    private void initData() {
        list_fragment = new ArrayList<>();
        list_fragment.add(new NewProjectDetailsFragment());
        list_fragment.add(new NewBrrowDataFragment());
        list_fragment.add(new FkclFragment());
        list_fragment.add(new InvestRecordFragment());

        //根据跳转来源  判断需要显示的fragment
        if(DsjrConfig.FROM_PD==1){
            showId=0;
        }else if(DsjrConfig.FROM_PD==2){
            showId=1;
        }else if(DsjrConfig.FROM_PD==3){
            showId=2;
        }else if(DsjrConfig.FROM_PD==4){
            showId=3;
        }

        for (int i = 0; i < list_fragment.size(); i++) {
                getSupportFragmentManager().beginTransaction()
                        .add(R.id.fl_project_details, list_fragment.get(i), "BaseFragment" + i)
                        .show(list_fragment.get(showId))
                        .hide(list_fragment.get(i))
                        .commitAllowingStateLoss();
        }
        ivDetailsBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
