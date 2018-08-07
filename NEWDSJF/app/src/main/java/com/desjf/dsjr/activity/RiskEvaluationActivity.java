package com.desjf.dsjr.activity;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.desjf.dsjr.R;
import com.desjf.dsjr.activity.bankActivity.BankAccountMessageActivity;
import com.desjf.dsjr.adapter.RiskEvaluationAdapter;
import com.desjf.dsjr.base.BaseActivity;
import com.desjf.dsjr.base.BaseApplication;
import com.desjf.dsjr.biz.retrofit.CallUtils;
import com.desjf.dsjr.biz.retrofit.InitHttpUtil;
import com.desjf.dsjr.model.AccountModel;
import com.desjf.dsjr.model.GradeModel;
import com.desjf.dsjr.model.RiskEvaluationModel;
import com.desjf.dsjr.util.MyDialogUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author YinL
 * @Describe 风险测评
 * @Date 2018-2-6
 */

public class RiskEvaluationActivity extends BaseActivity {

    @BindView(R.id.rv_risk)
    RecyclerView recyclerView;

    //定义分数计数器变量
    private int grade=0;
    //总分
    private int total;
    //各题分数计算
    private int one=-1;private int two=-1;private int three=-1;private int four=-1;private int five=-1;
    private int six=-1;private int seven=-1;private int eight=-1;private int nine=-1;private int ten=-1;


    String msg="";

    List<RiskEvaluationModel> list=null;
    RiskEvaluationAdapter riskEvaluationAdapter;
    private LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);

    private AccountModel accountModels;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_risk_evaluation);
        ButterKnife.bind(this);

        //获取用户信息 —— 抓取当前用户手机号
        BaseApplication baseApplication = (BaseApplication) getApplication();
        accountModels= baseApplication.getAccountModel();

        initAdapter();

    }

    public void initAdapter(){
        riskEvaluationAdapter=new RiskEvaluationAdapter(initData(),this);
        addHeaderView();
        addFooterView();
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(riskEvaluationAdapter);
//        riskEvaluationAdapter.notifyDataSetChanged();

        riskEvaluationAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                RiskEvaluationModel riskEvaluationModel= (RiskEvaluationModel) adapter.getItem(position);
                switch(view.getId()){
                    case R.id.radioButton1:
                        riskEvaluationModel.setOne(true);
                        riskEvaluationModel.setTwo(false);
                        riskEvaluationModel.setThree(false);
                        riskEvaluationModel.setFour(false);
                        //根据当前项 来判断选项对应的分值
                        if(position==0){
                         //3
                            one=3;
                        }else if(position==1){
                         //1
                            two=1;
                        }
                        else if(position==2){
                        //1
                            three=1;
                        }else if(position==3){
                         //0
                            four=0;
                        }else if(position==4){
                        //1
                            five=1;
                        }else if(position==5){
                        //5
                            six=5;
                        }else if(position==6){
                        //5
                            seven=5;
                        }else if(position==7){
                        //1
                            eight=1;
                        }else if(position==8){
                        //1
                            nine=1;
                        }
                        riskEvaluationAdapter.notifyDataSetChanged();
                        break;
                    case R.id.radioButton2:
                        riskEvaluationModel.setTwo(true);
                        riskEvaluationModel.setOne(false);
                        riskEvaluationModel.setThree(false);
                        riskEvaluationModel.setFour(false);
                        riskEvaluationAdapter.notifyDataSetChanged();
                        if(position==0){
                         //5
                            one=5;
                        }else if(position==1){
                         //2
                            two=2;
                        }
                        else if(position==2){
                        //2
                            three=2;
                        }else if(position==3){
                        //2
                            four=2;
                        }else if(position==4){
                        //2
                            five=2;
                        }else if(position==5){
                        //3
                            six=3;
                        }else if(position==6){
                         //3
                            seven=3;
                        }else if(position==7){
                        //3
                            eight=3;
                        }else if(position==8){
                        //2
                            nine=2;
                        }
                        break;
                    case R.id.radioButton3:
                        riskEvaluationModel.setThree(true);
                        riskEvaluationModel.setOne(false);
                        riskEvaluationModel.setTwo(false);
                        riskEvaluationModel.setFour(false);
                        riskEvaluationAdapter.notifyDataSetChanged();
                        if(position==0){
                        //4
                            one=4;
                        }else if(position==1){
                        //3
                            two=3;
                        }
                        else if(position==2){
                        //3
                            three=3;
                        }else if(position==3){
                        //3
                            four=3;
                        }else if(position==4){
                         //4
                            five=4;
                        }else if(position==5){
                        //2
                            six=2;
                        }else if(position==6){
                        //2
                            seven=2;
                        }else if(position==7){
                        //4
                            eight=4;
                        }else if(position==8){
                        //4
                            nine=4;
                        }
                        break;
                    case R.id.radioButton4:
                        riskEvaluationModel.setFour(true);
                        riskEvaluationModel.setOne(false);
                        riskEvaluationModel.setTwo(false);
                        riskEvaluationModel.setThree(false);
                        riskEvaluationAdapter.notifyDataSetChanged();
                        if(position==0){
                        //1
                            one=1;
                        }else if(position==1){
                        //5
                            two=5;
                        }
                        else if(position==2){
                        //5
                            three=5;
                        }else if(position==3){
                        //5
                            four=5;
                        }else if(position==4){
                        //5
                            five=5;
                        }else if(position==5){
                         //1
                            six=1;
                        }else if(position==6){
                        //1
                            seven=1;
                        }else if(position==7){
                         //5
                            eight=5;
                        }else if(position==8){
                        //5
                            nine=5;
                        }
                        break;

                }

            }
        });

    }

    public List<RiskEvaluationModel> initData(){
        list=new ArrayList<>();
        RiskEvaluationModel re1=new RiskEvaluationModel();
        re1.setTitle("1、您目前的年龄？");
        re1.setOptionOne("A.小于25岁");
        re1.setOptionTwo("B.25-40岁之间");
        re1.setOptionThree("C.41-60岁之间");
        re1.setOptionFour("D.大于60岁");
        re1.setOne(false);
        re1.setTwo(false);
        re1.setThree(false);
        re1.setFour(false);
        list.add(re1);

        RiskEvaluationModel re2=new RiskEvaluationModel();
        re2.setTitle("2、您当前的资产主要构成情况？");
        re2.setOptionOne("A.只有银行存款（活期或者定期）");
        re2.setOptionTwo("B.主要是银行存款，还有少量国债和基金、蓝筹股");
        re2.setOptionThree("C.既有股票、基金、股票、理财产品，也有银行存款，前后两者各半");
        re2.setOptionFour("D.以期货等金融衍生产品和股票投资为主");
        re2.setOne(false);
        re2.setTwo(false);
        re2.setThree(false);
        re2.setFour(false);
        list.add(re2);

        RiskEvaluationModel re3=new RiskEvaluationModel();
        re3.setTitle("3、您当前用家庭资产多少比例进行投资？");
        re3.setOptionOne("A.10%之内");
        re3.setOptionTwo("B.20%以内");
        re3.setOptionThree("C.50%以内");
        re3.setOptionFour("D.超过50%");
        re3.setOne(false);
        re3.setTwo(false);
        re3.setThree(false);
        re3.setFour(false);
        list.add(re3);

        RiskEvaluationModel re4=new RiskEvaluationModel();
        re4.setTitle("4、如果有个为期1 年投资项目可参与，投资成功的概率是50%，一旦成功可获得4 倍收益，一旦失败则本钱全部损失，对这样的投资您愿意投入的资金量是多少？");
        re4.setOptionOne("A.一分也不投入");
        re4.setOptionTwo("B.1个月的收入");
        re4.setOptionThree("C.3个月的收入");
        re4.setOptionFour("D.6个月或超过6个月的收入");
        re4.setOne(false);
        re4.setTwo(false);
        re4.setThree(false);
        re4.setFour(false);
        list.add(re4);

        RiskEvaluationModel re5=new RiskEvaluationModel();
        re5.setTitle("5、往往高收益的基金或投资产品伴随着高风险，您准备承担多大的风险？");
        re5.setOptionOne("A.不愿意让我的钱有任何损失");
        re5.setOptionTwo("B.因为基金有潜在的增值功能，我愿意承担一定的风险");
        re5.setOptionThree("C.很愿意通过长期投资使资产增值保值，也愿意在此过程中承担风险");
        re5.setOptionFour("D.我愿意投资最具增长潜力的产品，也愿意为了更高的收益承受大幅度的风险变动");
        re5.setOne(false);
        re5.setTwo(false);
        re5.setThree(false);
        re5.setFour(false);
        list.add(re5);

        RiskEvaluationModel re6=new RiskEvaluationModel();
        re6.setTitle("6、请问您投资价格波动性的产品有多少年的经验？（具有价格波动的产品包括股票、基金、外汇、期权期货等金融产品）");
        re6.setOptionOne("A.7-10 年");
        re6.setOptionTwo("B.4-7 年");
        re6.setOptionThree("C.1-3 年");
        re6.setOptionFour("D.从来没有或刚刚开始");
        re6.setOne(false);
        re6.setTwo(false);
        re6.setThree(false);
        re6.setFour(false);
        list.add(re6);

        RiskEvaluationModel re7=new RiskEvaluationModel();
        re7.setTitle("7、通常情况下您一笔投资预备持有的期限是多久？");
        re7.setOptionOne("A.长期——超过7 年");
        re7.setOptionTwo("B.中期——4 年到7 年");
        re7.setOptionThree("C.中短期——1 年到3 年");
        re7.setOptionFour("D.短期——1 年内");
        re7.setOne(false);
        re7.setTwo(false);
        re7.setThree(false);
        re7.setFour(false);
        list.add(re7);

        RiskEvaluationModel re8=new RiskEvaluationModel();
        re8.setTitle("8、一年中您能最能接受的资产波动范围是？");
        re8.setOptionOne("A.最多盈利10%，最大亏损5%");
        re8.setOptionTwo("B.最多盈利20%，最大亏损15%");
        re8.setOptionThree("C.最多盈利40%，最大亏损30%");
        re8.setOptionFour("D.最多盈利60%，最大亏损50%");
        re8.setOne(false);
        re8.setTwo(false);
        re8.setThree(false);
        re8.setFour(false);
        list.add(re8);

        RiskEvaluationModel re9=new RiskEvaluationModel();
        re9.setTitle("9、如果您刚刚投资就遇到市场调整，您能承受的损失大概是多少？");
        re9.setOptionOne("A.5%以内");
        re9.setOptionTwo("B.6-20%");
        re9.setOptionThree("C.21-40%");
        re9.setOptionFour("D.超过40%");
        re9.setOne(false);
        re9.setTwo(false);
        re9.setThree(false);
        re9.setFour(false);
        list.add(re9);

        return list;
    }

    //添加头部  风险测评说明
    private void addHeaderView(){
        View headerView = RiskEvaluationActivity.this.getLayoutInflater().inflate(R.layout.risk_evaluation_header_view, null);
        headerView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        riskEvaluationAdapter.addHeaderView(headerView);
    }

    //添加尾部 有五个选项的题目
    private void addFooterView() {
        View footerView = RiskEvaluationActivity.this.getLayoutInflater().inflate(R.layout.risk_evaluation_footer_view, null);
        footerView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        riskEvaluationAdapter.addFooterView(footerView);
        RadioButton radioButton1=footerView.findViewById(R.id.radioButton1);
        RadioButton radioButton2=footerView.findViewById(R.id.radioButton2);
        RadioButton radioButton3=footerView.findViewById(R.id.radioButton3);
        RadioButton radioButton4=footerView.findViewById(R.id.radioButton4);
        RadioButton radioButton5=footerView.findViewById(R.id.radioButton5);
        Button commit=footerView.findViewById(R.id.tijiao);
        radioButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ten=1;
            }
        });
        radioButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ten=2;
            }
        });
        radioButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ten=3;
            }
        });radioButton4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ten=4;

            }
        });radioButton5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ten=5;

            }
        });
        commit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                total=one+two+three+four+five+six+seven+eight+nine+ten;
                //当所有选项都被选择时才能提交
                if (one!=-1&&two!=-1&&three!=-1&&four!=-1&&five!=-1&&six!=-1&&seven!=-1&&eight!=-1&&nine!=-1&&ten!=-1) {
                    setGrade(total);
                } else {
                    MyDialogUtil.showSimpleDialog(RiskEvaluationActivity.this, "请您确定是否完整答题", "知道了");
                }
            }
        });

    }

    @OnClick({R.id.iv_back})
    public void OnClick(View view){
        switch(view.getId()){
            case R.id.iv_back:
                Intent i=new Intent(RiskEvaluationActivity.this,BankAccountMessageActivity.class);
                startActivity(i);
                finish();
                break;
        }
    }

    private void  setGrade(final int grade){

        CallUtils.call(RiskEvaluationActivity.this, InitHttpUtil.getHttpRequestService().setGrade(grade, accountModels.getPHONENUMBER()), new CallUtils.MyCallListener() {
            @Override
            public void onRespnseSuccess(String jsonString) {
                GradeModel gradeModel= JSON.parseObject(jsonString,GradeModel.class);
                /**
                 *  Message : 0 代表保守型   1 代表稳健型  2代表积极型
                 */
                if(gradeModel.getMessage().equals("0")){
                    msg="测评完成，您是保守型投资人(投资限额50万)";
                }else if(gradeModel.getMessage().equals("1")){
                    msg="测评完成，您是稳健型投资人(投资限额100万)";
                }else if(gradeModel.getMessage().equals("2")){
                    msg="测评完成，您是积极型投资人(投资限额1000万)";
                }else{
                    msg="提交失败，请您稍后重试";
                }
                showSimpleDialog(RiskEvaluationActivity.this, msg, "知道了");
            }

        });

    }

    public  void showSimpleDialog(Context context , String message,String ok) {
        View view = LayoutInflater.from(context).inflate(R.layout.mydialog, null);
        TextView confirm; //确定按钮
        final TextView content; //内容
        confirm = (TextView) view.findViewById(R.id.tv_dialog_ok);
        content = (TextView) view.findViewById(R.id.tv_dialog_message);confirm.setText(ok);
        content.setText(message);
        final Dialog dialog = new Dialog(context,R.style.My_Dialog);
        dialog.setContentView(view);
        dialog.setCancelable(false);
        //        dialog.getWindow().setBackgroundDrawableResource(R.color.bg_white);

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(RiskEvaluationActivity.this,BankAccountMessageActivity.class);
                startActivity(i);
                finish();
                dialog.dismiss();
            }
        });
        dialog.show();
        DisplayMetrics dm = context.getResources().getDisplayMetrics();
        int displayWidth = dm.widthPixels;
        int displayHeight = dm.heightPixels;
        android.view.WindowManager.LayoutParams p = dialog.getWindow().getAttributes(); //获取对话框当前的参数值
        p.width = (int) (displayWidth * 0.8); //宽度设置为屏幕的0.5
        p.height = (int) (displayHeight * 0.25); //宽度设置为屏幕的0.25
        dialog.setCanceledOnTouchOutside(false);// 设置点击屏幕Dialog不消失
        dialog.getWindow().setAttributes(p);  //设置生效

    }

}
