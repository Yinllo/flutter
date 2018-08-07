package com.desjf.dsjr.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.desjf.dsjr.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author YinL
 * @Describe 信息披露 主页面  包含所有信息披露项的点击按钮
 */
public class InformationDisclosureActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information_disclosure);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.iv_back,R.id.introduction,R.id.information,R.id.background,R.id.team,R.id.structure,R.id.honor
    ,R.id.investment,R.id.partner,R.id.contact,R.id.manager,R.id.control,R.id.data,
    R.id.need_know,R.id.agreement,R.id.examination_report,R.id.audit_report,R.id.compliance,R.id.Record_Registration
    ,R.id.certificate,R.id.affirm})
    public void OnClick(View view){
         switch(view.getId()){
             //退出
             case R.id.iv_back:
                 finish();
                 break;
             //公司简介
             case R.id.introduction:
                 Intent intent1 = new Intent(InformationDisclosureActivity.this, WebViewActivity.class);
                 intent1.putExtra("web", 10);
                 startActivity(intent1);
                 break;
             //平台信息
             case R.id.information:
                 Intent news = new Intent(InformationDisclosureActivity.this, WebViewActivity.class);
                 news.putExtra("web", 11);
                 startActivity(news);
                 break;
             //股东背景
             case R.id.background:
                 Intent intent2 = new Intent(InformationDisclosureActivity.this, WebViewActivity.class);
                 intent2.putExtra("web", 12);
                 startActivity(intent2);
                 break;
             //管理团队
             case R.id.team:
                 Intent intent3 = new Intent(InformationDisclosureActivity.this, WebViewActivity.class);
                 intent3.putExtra("web", 13);
                 startActivity(intent3);
                 break;
             //组织架构
             case R.id.structure:
                 Intent intent4 = new Intent(InformationDisclosureActivity.this, WebViewActivity.class);
                 intent4.putExtra("web", 14);
                 startActivity(intent4);
                 break;
             //荣誉资质
             case R.id.honor:
                 Intent intent5 = new Intent(InformationDisclosureActivity.this, WebViewActivity.class);
                 intent5.putExtra("web", 15);
                 startActivity(intent5);
                 break;
             //投资产品
             case R.id.investment:
                 Intent intent6 = new Intent(InformationDisclosureActivity.this, WebViewActivity.class);
                 intent6.putExtra("web", 16);
                 startActivity(intent6);
                 break;
             //合作伙伴
             case R.id.partner:
                 Intent intent7 = new Intent(InformationDisclosureActivity.this, WebViewActivity.class);
                 intent7.putExtra("web", 17);
                 startActivity(intent7);
                 break;
             //联系我们
             case R.id.contact:
                 Intent intent8 = new Intent(InformationDisclosureActivity.this, WebViewActivity.class);
                 intent8.putExtra("web", 18);
                 startActivity(intent8);
                 break;
             //银行存管
             case R.id.manager:
                 Intent intent9 = new Intent(InformationDisclosureActivity.this, WebViewActivity.class);
                 intent9.putExtra("web", 19);
                 startActivity(intent9);
                 break;
             //风控流程
             case R.id.control:
                 Intent intent10 = new Intent(InformationDisclosureActivity.this, WebViewActivity.class);
                 intent10.putExtra("web", 20);
                 startActivity(intent10);
                 break;
             //数据安全
             case R.id.data:
                 Intent intent11 = new Intent(InformationDisclosureActivity.this, WebViewActivity.class);
                 intent11.putExtra("web", 21);
                 startActivity(intent11);
                 break;
             //投资人须知
             case R.id.need_know:
                 Intent intent12 = new Intent(InformationDisclosureActivity.this, WebViewActivity.class);
                 intent12.putExtra("web", 22);
                 startActivity(intent12);
                 break;
             //合同存证
             case R.id.agreement:
                 Intent intent13 = new Intent(InformationDisclosureActivity.this, WebViewActivity.class);
                 intent13.putExtra("web", 23);
                 startActivity(intent13);
                 break;
             //审查报告
             case R.id.examination_report:
                 Intent intent14 = new Intent(InformationDisclosureActivity.this, WebViewActivity.class);
                 intent14.putExtra("web", 24);
                 startActivity(intent14);
                 break;
             //审计报告
             case R.id.audit_report:
                 Intent intent15 = new Intent(InformationDisclosureActivity.this, WebViewActivity.class);
                 intent15.putExtra("web", 25);
                 startActivity(intent15);
                 break;
             //合规进程
             case R.id.compliance:
                 Intent intent16 = new Intent(InformationDisclosureActivity.this, WebViewActivity.class);
                 intent16.putExtra("web", 31);
                 startActivity(intent16);
                 break;
             //备案登记
             case R.id.Record_Registration:
                 Intent intent17 = new Intent(InformationDisclosureActivity.this, WebViewActivity.class);
                 intent17.putExtra("web", 32);
                 startActivity(intent17);
                 break;
             //粤ICP证
             case R.id.certificate:
                 Intent intent18 = new Intent(InformationDisclosureActivity.this, WebViewActivity.class);
                 intent18.putExtra("web", 33);
                 startActivity(intent18);
                 break;
             //信披确认书
             case R.id.affirm:
                 Intent intent19 = new Intent(InformationDisclosureActivity.this, WebViewActivity.class);
                 intent19.putExtra("web", 34);
                 startActivity(intent19);
                 break;
         }
    }

}
