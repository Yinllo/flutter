package com.desjf.dsjr.ui.fragment.ProjectDetails;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.desjf.dsjr.R;
import com.desjf.dsjr.base.BaseFragment;
import com.desjf.dsjr.bean.NewProductBean;

import butterknife.BindView;


/**
 * @Author YinL
 * @Date 2018/1/24 0015
 * @Describe  项目详情中的   借款详情
 */
public class LoanDetailsFragment extends BaseFragment {

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
    @BindView(R.id.borrower_data)
    TextView borrowerData;
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

    @BindView(R.id.tv_details_btn)
    TextView tvDetailsBtn;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_loan_details;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        //初始化
//        initRequest();
    }

    public void initRequest(){



    }

    public void initData(){

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
        borrowerData.setText("");
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


    //获得当前标的的  id值
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }
}