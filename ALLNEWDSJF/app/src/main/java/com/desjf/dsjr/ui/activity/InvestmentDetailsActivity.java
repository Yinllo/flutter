package com.desjf.dsjr.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.desjf.dsjr.R;
import com.desjf.dsjr.utils.DialogUtil;
import com.desjf.dsjr.utils.PreferenceCache;
import com.desjf.dsjr.utils.ToastUtils;
import com.desjf.dsjr.widget.TimerView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author YinL
 * @Describe  投资详情Activity
 */
public class InvestmentDetailsActivity extends AppCompatActivity {

    @BindView(R.id.tv_title)
    TextView tvTitle;//当前页面的标题
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_rate)
    TextView tvRate;//标的利率
    @BindView(R.id.tv_limit_time)
    TextView LimitTime;//标的期限
    @BindView(R.id.tv_limit_time_unit)
    TextView LimitTimeUnit;//标的期限单位
    @BindView(R.id.tv_residue)
    TextView residue;//剩余可投金额
    @BindView(R.id.tv_way)
    TextView way;//满标方式
    @BindView(R.id.tv_repayment)
    TextView repayment;//还款方式
    @BindView(R.id.timerView)
    TimerView timerView;//倒计时时间
    @BindView(R.id.tv_account_residue_money)
    TextView accountResidueMoney;//账户剩余金额
    @BindView(R.id.tv_charge_btn)
    TextView chargeBtn;//充值按钮
    @BindView(R.id.tv_red_money)
    TextView redMoney;//可用红包数/已选择的红包使用条件
    @BindView(R.id.tv_choose)
    TextView choose;//请选择
    @BindView(R.id.tv_red_value)
    TextView value;//已选择的奖励值
    @BindView(R.id.tv_invest_money)
    TextView investMoney;//投资金额
    @BindView(R.id.tv_invest_all)
    TextView investAll;//全额投资
    @BindView(R.id.tv_anticipated_income)
    TextView anticipatedIncome;//预计收益
    @BindView(R.id.tv_real_income)
    TextView realIncome;//实际收益
    @BindView(R.id.et_code)
    EditText code;//验证码输入框
    @BindView(R.id.tv_code_btn)
    TextView codeBtn;//获取验证码按钮
    @BindView(R.id.cb_choose)
    CheckBox checkBox;//协议
    @BindView(R.id.tv_risk_book)
    TextView riskBook;//风险提示书
    @BindView(R.id.tv_electronic_signature)
    TextView electronicSignature;//电子签章授权
    @BindView(R.id.tv_invest_btn)
    TextView investBtn;//确认投资按钮
    @BindView(R.id.tv_see_info_btn)
    TextView seeInfoBtn;//查看借款详情

    //是否同意投资协议
    private boolean agreement=false;


    //记录当前的滑动事件：手指按下的点为(x1, y1)手指离开屏幕的点为(x2, y2)
    private float x1 = 0;
    private float x2 = 0;
    private float y1 = 0;
    private float y2 = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_investment_details);
        ButterKnife.bind(this);

        initView();

    }

    private void initView(){
      //初始化标题信息
        tvTitle.setText("某公司借款");
        //初始化投资 协议是否同意
        checkBox.setChecked(PreferenceCache.isAutoInvestment());

    }

    @OnClick({R.id.tv_see_info_btn,R.id.iv_back,R.id.tv_invest_all,R.id.tv_pwd_btn,R.id.tv_invest_btn,R.id.tv_way,R.id.tv_repayment
              ,R.id.iv_hint,R.id.tv_risk_book,R.id.tv_electronic_signature,R.id.ll_choose_prize})
    public void OnClick(View view){
        switch(view.getId()){
            case R.id.tv_invest_btn:
                //确认投资
                initInvestment();
                break;
            case R.id.tv_see_info_btn:
                //查看借款详情
                Intent projectDetails=new Intent(InvestmentDetailsActivity.this,ProjectDetailsActivity.class);
                startActivity(projectDetails);
                break;
            case R.id.ll_choose_prize:
                //选择红包
                if ("".equals("0")) {
                    ToastUtils.showShortTost(this, "您暂无可用红包");
                } else {
                    //获得可用红包并初始化弹框
                    getAllBag();
                }
                break;
            case R.id.tv_risk_book:
                //风险提示书
                Intent toWeb=new Intent(this,WebViewActivity.class);
                toWeb.putExtra("webUrl","https://www.dsp2p.com/m/share_risk.html");
                toWeb.putExtra("title","风险提示书");
                startActivity(toWeb);
                break;
            case R.id.tv_electronic_signature:
                //电子签章及授权
                Intent web=new Intent(this,WebViewActivity.class);
                web.putExtra("webUrl","https://www.dsp2p.com/m/share_contract.html");
                web.putExtra("title","电子签章及授权");
                startActivity(web);
                break;
            case R.id.tv_charge_btn:
                //充值
                Intent recharge = new Intent(this, RechargeActivity.class);
                startActivity(recharge);
                break;
            case R.id.tv_invest_all:
                //全额投资
                break;
            case R.id.tv_pwd_btn:
                //忘记密码
                break;
            case R.id.tv_way:
                //计息方式提示消息
                DialogUtil.getSimpleDialogInstance(this,"计息方式提示消息","知道了");
                break;
            case R.id.tv_repayment:
                //还款方式提示消息
                DialogUtil.getSimpleDialogInstance(this,"还款方式提示消息","知道了");
                break;
            case R.id.iv_hint:
                //倒计时提示消息
                DialogUtil.getSimpleDialogInstance(this,"倒计时提示消息","知道了");
                break;
            case R.id.iv_back:
                finish();
                break;
        }
    }

    private void getAllBag(){
      //获得所有可用化红包

        initChoose();
    }
    //初始化弹框
    private void initChoose(){

    }

    /**
     * 投资方法
     */
    private void initInvestment(){
        //保存协议选择状态
        PreferenceCache.putAutoInvestment(checkBox.isChecked());
        if(checkBox.isChecked()==false){
            //判断协议是否选中:未选中则提示用户
            ToastUtils.showShortTost(this,"请您确认协议并勾选");
         return;
        }
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //继承了Activity的onTouchEvent方法，直接监听点击事件
        if(event.getAction() == MotionEvent.ACTION_DOWN) {
            //当手指按下的时候
            x1 = event.getX();
            y1 = event.getY();
        }
        if(event.getAction() == MotionEvent.ACTION_UP) {
            //当手指离开的时候
            x2 = event.getX();
            y2 = event.getY();
            if(y1 - y2 > 50) {
                //向上滑    查看借款详情
                Intent projectDetails=new Intent(InvestmentDetailsActivity.this,ProjectDetailsActivity.class);
                startActivity(projectDetails);
            } else if(y2 - y1 > 50) {
                //向下滑
            } else if(x1 - x2 > 50) {
                //向左滑
            } else if(x2 - x1 > 50) {
                //向右滑
            }
        }
        return super.onTouchEvent(event);
    }

}
