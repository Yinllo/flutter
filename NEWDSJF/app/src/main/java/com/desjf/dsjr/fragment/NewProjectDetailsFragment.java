package com.desjf.dsjr.fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.desjf.dsjr.R;
import com.desjf.dsjr.activity.BankWebViewActivity;
import com.desjf.dsjr.activity.LoginActivity;
import com.desjf.dsjr.activity.NewInvestmentDetailsActivity;
import com.desjf.dsjr.activity.ProjectDetailsActivity;
import com.desjf.dsjr.base.BaseFragment;
import com.desjf.dsjr.bean.NewProductBean;
import com.desjf.dsjr.bean.NewRegBean;
import com.desjf.dsjr.biz.retrofit.BankHttpUtil;
import com.desjf.dsjr.biz.retrofit.CallUtils;
import com.desjf.dsjr.biz.retrofit.InitHttpUtil;
import com.desjf.dsjr.model.ProjectInfoModel;
import com.desjf.dsjr.utils.PreferenceCache;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author YinL on 2018/2/26.
 *  新的项目详情
 */
public class NewProjectDetailsFragment extends BaseFragment {

    private View rootView;
    NewProductBean newProductBean;
    private String b_id="";//项目标的 id

    //企业信息
    @BindView(R.id.company_info)
    TextView companyInfo;
    //借款企业
    @BindView(R.id.company_name)
    TextView company;
    @BindView(R.id.company_name_data)
    TextView companyName;
    //营业执照
    @BindView(R.id.licence)
    TextView licence;
    @BindView(R.id.licence_data)
    TextView licenceData;
    //所属行业
    @BindView(R.id.industry_data)
    TextView industryData;
    //借款人类型
//    @BindView(R.id.borrower_data)
//    TextView borrowerData;
    //法定代表人
    @BindView(R.id.representative)
    LinearLayout representative;
    @BindView(R.id.representative_data)
    TextView representativeData;
    //收入及负债情况
    @BindView(R.id.income_and_liabilities_data)
    TextView income_and_liabilities_Data;

    //借贷余额
    @BindView(R.id.money_data)
    TextView moneyData;
    //借款用途
    @BindView(R.id.use_data)
    TextView useData;
    //还款来源
    @BindView(R.id.repayment_source_data)
    TextView sourceData;
    //担保措施
    @BindView(R.id.security_measures_data)
    TextView measuresData;
    //借款描述
    @BindView(R.id.loan_description_data)
    TextView descriptionData;

    //逾期及信用情况
    @BindView(R.id.credit_situation_data)
    TextView situationData;

    @BindView(R.id.tv_details_login)
    TextView tvDetailsLogin;
    private ProjectInfoModel projectInfoModel;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        if (null == rootView) {
            rootView = inflater.inflate(R.layout.fragment_new_project_details, null);
            ButterKnife.bind(this, rootView);

            initRequest();
            initData();

        }
        ViewGroup parent = (ViewGroup) rootView.getParent();
        if (parent != null) {
            parent.removeView(rootView);
        }
        return rootView;
    }



    private void initRequest() {
        //初始化
        CallUtils.call(getActivity(), InitHttpUtil.getHttpRequestService().getObjectInfo(b_id), new CallUtils.MyCallListener() {
            @Override
            public void onRespnseSuccess(String jsonString) {

                newProductBean= JSON.parseObject(jsonString,NewProductBean.class);
                ininData();

            }
        });
    }

    private void ininData(){

        //判断当前是企业还是个人  根据类型设置不同的文字描述   0代表个人   1代表企业
        if (newProductBean.getResult().getUserType().equals("0")) {
          companyInfo.setText("个人信息");
          company.setText("借款人:");
          licence.setText("身份证号:");
          representative.setVisibility(View.GONE);//隐藏法定代表人
        }

        //借款企业或个人
        companyName.setText(newProductBean.getResult().getBORROWING_VENTURE());
        //所属行业
        industryData.setText(newProductBean.getResult().getINDUSTRY_INVOLVED());
        //借款人类型
//        borrowerData.setText("");
        //证件号码
        licenceData.setText(newProductBean.getResult().getBUSINESS_LICENSE_NUMBER());
        //法定代表人
        representativeData.setText(newProductBean.getResult().getLEGAL_REPRESENTATIVE());
        //收入及负债情况
        if (("").equals(newProductBean.getResult().getINCOME_AND_LIABLITIES())) {
            income_and_liabilities_Data.setText("无");
        } else {
            income_and_liabilities_Data.setText(newProductBean.getResult().getINCOME_AND_LIABLITIES());
        }

        //借贷余额
        moneyData.setText(newProductBean.getResult().getCREDIT_BALANCE());
        //借款用途
        useData.setText(newProductBean.getResult().getBORRPWING_PURPOSE());
        //还款来源
        sourceData.setText(newProductBean.getResult().getREPAYING_SOURCE());
        //担保措施
        measuresData.setText(newProductBean.getResult().getSECURITY_MEASURE());
        //借款描述
        descriptionData.setText(newProductBean.getResult().getBORROWING_DESCRIBE());
        //信用情况
        if (("").equals(newProductBean.getResult().getOVERDUE_CREDIT_REPORT())) {
            situationData.setText("没有逾期情况");
        }else{
            situationData.setText(newProductBean.getResult().getOVERDUE_CREDIT_REPORT());
        }

    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        b_id = ((ProjectDetailsActivity) activity).getId();
        projectInfoModel = ((ProjectDetailsActivity) activity).getProjectInfoModel();
    }

    private void initData() {

        if (PreferenceCache.getToken().isEmpty()) {
            tvDetailsLogin.setText("立即登录");
            tvDetailsLogin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (PreferenceCache.getToken().isEmpty()) {
                        Intent i = new Intent(getActivity(), LoginActivity.class);
                        startActivity(i);
                    } else {
//                            if (projectInfoModel.getID_CARD_VERIFY_FLG().equals("0")) {
//                                 goBank();
//                            } else {
                                Intent i = new Intent(getActivity(), NewInvestmentDetailsActivity.class);
                                i.putExtra("Info", projectInfoModel);
                                i.putExtra("ID", b_id);
                                startActivity(i);

//                        }
                    }
                }
            });
        } else {
            if (projectInfoModel.getPRODUCTS_STATUS().equals("立即投资")) {
                tvDetailsLogin.setText("立即投资");
                tvDetailsLogin.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (PreferenceCache.getToken().isEmpty()) {
                            Intent i = new Intent(getActivity(), LoginActivity.class);
                            startActivity(i);
                        } else {
//                                if (projectInfoModel.getID_CARD_VERIFY_FLG().equals("0")) {
//                                    goBank();
//                                } else {
                                    Intent i = new Intent(getActivity(), NewInvestmentDetailsActivity.class);
                                    i.putExtra("Info", projectInfoModel);
                                    i.putExtra("ID", b_id);
                                    startActivity(i);

//                            }
                        }
                    }
                });
            } else if (projectInfoModel.getPRODUCTS_STATUS().equals("满标待审")) {
                tvDetailsLogin.setText("已满标");
                tvDetailsLogin.setClickable(false);
                tvDetailsLogin.setBackgroundColor(getResources().getColor(R.color.navy_gray));
            } else if (projectInfoModel.getPRODUCTS_STATUS().equals("已完成")) {
                tvDetailsLogin.setText("已还清");
                tvDetailsLogin.setClickable(false);
                tvDetailsLogin.setBackgroundColor(getResources().getColor(R.color.navy_gray));
            } else if (projectInfoModel.getPRODUCTS_STATUS().equals("还款中")) {
                tvDetailsLogin.setText("收益中");
                tvDetailsLogin.setClickable(false);
                tvDetailsLogin.setBackgroundColor(getResources().getColor(R.color.navy_gray));
            }else if (projectInfoModel.getPRODUCTS_STATUS().equals("未开始")) {
                tvDetailsLogin.setText("即将发布");
                tvDetailsLogin.setClickable(false);
            }

        }


    }

    private void goBank(){
        //前往开通存管
        CallUtils.call(getActivity(), BankHttpUtil.getHttpRequestService().bankReg(projectInfoModel.getMOBILE()), new CallUtils.MyCallListener() {
            @Override
            public void onRespnseSuccess(String jsonString) {
                NewRegBean newRegBean= JSON.parseObject(jsonString,NewRegBean.class);
                Intent i=new Intent(getActivity(),BankWebViewActivity.class);
                i.putExtra("type",1);
                i.putExtra("newRegBean",newRegBean);
                startActivity(i);

            }
        });
    }

}