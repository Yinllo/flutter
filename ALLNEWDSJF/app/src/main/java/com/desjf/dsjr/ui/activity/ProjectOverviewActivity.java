package com.desjf.dsjr.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.desjf.dsjr.R;
import com.desjf.dsjr.base.BaseActivity;
import com.desjf.dsjr.base.BaseConfig;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author YinL
 * @Describe 项目概况
 */
public class ProjectOverviewActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project_overview);
        ButterKnife.bind(this);
    }


    @OnClick({R.id.ll_LoanParticulars,R.id.ll_CertificateContract,R.id.ll_RiskControlFlow,R.id.ll_InvestmentMemo})
    public void onClick(View view){
          switch (view.getId()) {
              //借款详情
              case R.id.ll_LoanParticulars:
                  BaseConfig.FROM_PO=1;
                  Intent intent1=new Intent(this,ProjectDetailsActivity.class);
                  startActivity(intent1);
                  break;
              //证件合同
              case R.id.ll_CertificateContract:
                  BaseConfig.FROM_PO=2;
                  Intent intent2=new Intent(this,ProjectDetailsActivity.class);
                  startActivity(intent2);
                  break;
              //风控流程
              case R.id.ll_RiskControlFlow:
                  BaseConfig.FROM_PO=3;
                  Intent intent3=new Intent(this,ProjectDetailsActivity.class);
                  startActivity(intent3);
                  break;
              //投资记录
              case R.id.ll_InvestmentMemo:
                  BaseConfig.FROM_PO=4;
                  Intent intent4=new Intent(this,ProjectDetailsActivity.class);
                  startActivity(intent4);
                  break;

          }
    }


}
