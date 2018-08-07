package com.desjf.dsjr.activity.bankActivity;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.desjf.dsjr.R;
import com.desjf.dsjr.activity.BankWebViewActivity;
import com.desjf.dsjr.activity.ChangePhoneNumberActivity;
import com.desjf.dsjr.activity.RiskEvaluationActivity;
import com.desjf.dsjr.base.BaseActivity;
import com.desjf.dsjr.base.BaseApplication;
import com.desjf.dsjr.bean.Identity;
import com.desjf.dsjr.bean.NewRegBean;
import com.desjf.dsjr.biz.retrofit.BankHttpUtil;
import com.desjf.dsjr.biz.retrofit.BankHttpUtils;
import com.desjf.dsjr.biz.retrofit.CallUtils;
import com.desjf.dsjr.biz.retrofit.InitHttpUtil;
import com.desjf.dsjr.model.AccountModel;
import com.desjf.dsjr.model.GradeModel;
import com.desjf.dsjr.model.bankModel.BankUserInfModel;
import com.desjf.dsjr.util.MyDialogUtil;
import com.desjf.dsjr.utils.PreferenceCache;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class BankAccountMessageActivity extends BaseActivity {
    @BindView(R.id.iv_back_account_message)
    ImageView ivBackAccountMessage;
    @BindView(R.id.tv_project_title)
    TextView tvProjectTitle;

    @BindView(R.id.tv_account_phone)
    TextView tvAccountPhone;
    @BindView(R.id.ll_account_phonenum)
    LinearLayout llAccountPhonenum;

    @BindView(R.id.iv_account_bank_more)
    ImageView ivAccountBankMore;
    @BindView(R.id.tv_account_bank_more)
    TextView tvAccountBankMore;
    @BindView(R.id.ll_account_addbanks)
    LinearLayout llAccountAddbanks;
    @BindView(R.id.ll_account_marks)
    LinearLayout llAccountMarks;

    @BindView(R.id.tv_test_type) //测评类型
    TextView test;
    @BindView(R.id.tv_test_more)//前往测评
    TextView testMore;

    @BindView(R.id.tv_agreement_more)//确定授权
    TextView agreementMore;
    @BindView(R.id.iv_agreement_more)
    ImageView imageView;

    private AccountModel accountModel;
    private Context context = this;
    public static BankAccountMessageActivity mInstace = null;

    String msg="";
    private boolean type=false;
    Identity identity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bank_account_message);
        BaseApplication baseApplication = (BaseApplication) getApplication();
        accountModel = baseApplication.getAccountModel();
        mInstace = this;
        ButterKnife.bind(this);

//        ifOpenBank();
        initData();
        ifTest();
        ifEmpower();

    }

    private void initData() {
        if (accountModel != null) {
//            if (accountModel.getBANKFLG().equals("0")) {
//                //银行账号未绑定
//                tvAccountBankMore.setText("添加银行卡");
//            } else {
//                //银行账号已绑定
//                tvAccountBankMore.setText(accountModel.getBANKCARDNOCONCEAL());
//            }
            tvAccountPhone.setText(accountModel.getPHONENUMBERCONCEAL());
        }

        ivBackAccountMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        //如果是老用户且未开通存管则进入普通账户页面
        CallUtils.call(BankAccountMessageActivity.this, BankHttpUtil.getHttpRequestService().getIdentity(accountModel.getPHONENUMBER()), new CallUtils.MyCallListener() {
            @Override
            public void onRespnseSuccess(String jsonString) {
                identity= JSON.parseObject(jsonString,Identity.class);
                /**
                 * 判断当前是新用户还是老用户，据此来判断是否显示新、旧账户切换按钮
                 * 0001 请登录   0002 老用户未实  0004  新用户未实名  0005  新用户已实名
                 0003  老用户已实名
                 */
                if (identity.getMessageType().equals("0002")||identity.getMessageType().equals("0004")) {
                    type=false;
                } else {
                    type=true;
                }
            }
        });
    }

    //先判断是否开通了存管
    public  void ifOpenBank(){
        CallUtils.call(BankAccountMessageActivity.this, BankHttpUtils.getHttpRequestService().queryUserInf(PreferenceCache.getToken()), new CallUtils.MyCallListener() {
            @Override
            public void onRespnseSuccess(String jsonString) {
                BankUserInfModel bankUserInfModel=JSON.parseObject(jsonString,BankUserInfModel.class);
                if (bankUserInfModel.getUsermessage()!=null) {
                    //                    tvWodePhonenum.setText(bankUserInfModel.getUsermessage().get(0).getMbl_no());
                    //如果是老用户且未开通存管则进入普通账户页面
                    CallUtils.call(BankAccountMessageActivity.this, BankHttpUtil.getHttpRequestService().getIdentity( bankUserInfModel.getUsermessage().get(0).getMbl_no()), new CallUtils.MyCallListener() {
                        @Override
                        public void onRespnseSuccess(String jsonString) {
                            identity= JSON.parseObject(jsonString,Identity.class);
                            /**
                             * 判断当前是新用户还是老用户，据此来判断是否显示新、旧账户切换按钮
                             * 0001 请登录   0002 老用户未实  0004  新用户未实名  0005  新用户已实名
                             0003  老用户已实名
                             */
                            if (identity.getMessageType().equals("0002")||identity.getMessageType().equals("0004")) {
                                type=false;
                            } else {
                                type=true;
                            }
                        }
                    });
                }
                //如果是新用户但是未实名就不会查询到用户个人信息
                else{
                    //如果是新用户 则隐藏
                    type=false;
                }
            }
        });
    }

    @OnClick({R.id.ll_account_addbanks, R.id.ll_account_marks,R.id.ll_test,R.id.ll_agreement
    ,R.id.ll_account_phonenum,R.id.ll_account_pwd,R.id.ll_account_pwd_find})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_account_addbanks:
                //修改银行卡
                if (type) {
                    CallUtils.call(BankAccountMessageActivity.this, BankHttpUtil.getHttpRequestService().changeBank(accountModel.getPHONENUMBER()), new CallUtils.MyCallListener() {
                        @Override
                        public void onRespnseSuccess(String jsonString) {
                            NewRegBean newRegBean= JSON.parseObject(jsonString,NewRegBean.class);

                            Intent i=new Intent(BankAccountMessageActivity.this,BankWebViewActivity.class);
                            i.putExtra("newRegBean",newRegBean);
                            i.putExtra("type",4);
                            startActivity(i);
                        }
                    });
                } else {
                    MyDialogUtil.showSimpleDialog(BankAccountMessageActivity.this,"请您先开通存管","知道了");
                }

                //                if (accountModel != null) {
//                    if (accountModel.getIDCARDVERIFYFLG().toString().equals("0") || accountModel.getBANKFLG().toString().equals("0")) {
//                        Intent intent = new Intent(BankAccountMessageActivity.this, AccountRealNameActivity.class);
//                        intent.putExtra("RealName", 1);
//                        intent.putExtra("UserName", accountModel.getUSERNAME());
//                        intent.putExtra("UserID", accountModel.getIDCARD());
//                        startActivity(intent);
//                        return;
//                    } else {
////                        showToast("银行卡已绑定");
//
//                        Intent intent = new Intent(this, BankModifyActiviy.class);
//                        intent.putExtra("RealName", 1);
//                        intent.putExtra("UserName", accountModel.getUSERNAME());
//                        intent.putExtra("UserID", accountModel.getIDCARD());
//                        startActivity(intent);
//                        finish();
//                    }
//                }


                break;
            case R.id.ll_account_phonenum:
                //修改手机号

                if (type) {
                    Intent change=new Intent(this,ChangePhoneNumberActivity.class);
                    startActivity(change);
                }else {
                    MyDialogUtil.showSimpleDialog(BankAccountMessageActivity.this,"请您先开通存管","知道了");
                }


                break;
            case R.id.ll_account_pwd:
                //修改密码
                if (type) {
                    CallUtils.call(BankAccountMessageActivity.this, BankHttpUtil.getHttpRequestService().changePwd(accountModel.getPHONENUMBER()), new CallUtils.MyCallListener() {
                        @Override
                        public void onRespnseSuccess(String jsonString) {
                            NewRegBean newRegBean= JSON.parseObject(jsonString,NewRegBean.class);

                            Intent i=new Intent(BankAccountMessageActivity.this,BankWebViewActivity.class);
                            i.putExtra("newRegBean",newRegBean);
                            i.putExtra("type",5);
                            startActivity(i);
                        }
                    });
                }else {
                    MyDialogUtil.showSimpleDialog(BankAccountMessageActivity.this,"请您先开通存管","知道了");
                }

                break;
            case R.id.ll_account_pwd_find:
                //找回密码
                if (type) {
                    CallUtils.call(BankAccountMessageActivity.this, BankHttpUtil.getHttpRequestService().changePwd(accountModel.getPHONENUMBER()), new CallUtils.MyCallListener() {
                        @Override
                        public void onRespnseSuccess(String jsonString) {
                            NewRegBean newRegBean= JSON.parseObject(jsonString,NewRegBean.class);

                            Intent i=new Intent(BankAccountMessageActivity.this,BankWebViewActivity.class);
                            i.putExtra("newRegBean",newRegBean);
                            i.putExtra("type",7);
                            startActivity(i);
                        }
                    });
                }else {
                    MyDialogUtil.showSimpleDialog(BankAccountMessageActivity.this,"请您先开通存管          ","知道了");
                }

                break;

//            case R.id.ll_account_marks:
//                //我的积分
//                if (accountModel != null) {
//                    Intent intent_one = new Intent(BankAccountMessageActivity.this, AccountMyMarksActivity.class);
//                    startActivity(intent_one);
//                }
//                break;
//            case R.id.ll_account_realname:
//                //实名认证
//                if (accountModel != null) {
//                    if (accountModel.getIDCARDVERIFYFLG().toString().equals("0")) {
//                        Intent intent_two = new Intent(BankAccountMessageActivity.this, AccountRealNameActivity.class);
//                        intent_two.putExtra("RealName", 3);
//                        startActivity(intent_two);
//                    } else {
//                        showToast("您已实名认证");
//                        return;
//                    }
//                }
//
//                break;

            case R.id.ll_test:
                //风险测评
                Intent i=new Intent(this,RiskEvaluationActivity.class);
                startActivity(i);
                finish();
                break;

            case R.id.ll_agreement:
                //合同授权
                if(agreementMore.getText().equals("确定授权")){
                    showDialog("授权第三方 法大大 保管您的投资合同");
                }
                //取消授权
                else if(agreementMore.getText().equals("取消授权")){
                    ShowCancelEmpower("是否确定取消授权？");
                }

                break;

        }

    }

    /**
     * 查看当前用户是否测评，如果已经测评  类型是什么
     */
    private void  ifTest(){

        CallUtils.call(BankAccountMessageActivity.this, InitHttpUtil.getHttpRequestService().ifTest(accountModel.getPHONENUMBER()), new CallUtils.MyCallListener() {
            @Override
            public void onRespnseSuccess(String jsonString) {
                GradeModel gradeModel= JSON.parseObject(jsonString,GradeModel.class);
                /**
                 *  Message : 0 代表保守型   1 代表稳健型  2代表积极型
                 */
              if(gradeModel!=null){
                  if(gradeModel.getMessage().equals("0")){
                      test.setText("保守型(限投50万)");
                  }else if(gradeModel.getMessage().equals("1")){
                      test.setText("稳健型(限投100万)");
                  }else if(gradeModel.getMessage().equals("2")){
                      test.setText("积极型(限投1000万)");
                  }
                  testMore.setText("重新测评");
              }
            }
        });

    }
    /**
     * 查看当前用户是否已经授权
     */
    private void  ifEmpower(){

        CallUtils.call(BankAccountMessageActivity.this, InitHttpUtil.getHttpRequestService().ifEmpower(accountModel.getPHONENUMBER()), new CallUtils.MyCallListener() {
            @Override
            public void onRespnseSuccess(String jsonString) {
                GradeModel gradeModel= JSON.parseObject(jsonString,GradeModel.class);
                /**
                 *  Message : 0 与""代表没有授权   1 代表授权了
                 */
                if(gradeModel.getMessage().equals("0")){
                    agreementMore.setText("确定授权");
                }else if(gradeModel.getMessage().equals("")){;
                    agreementMore.setText("确定授权");
                }else if(gradeModel.getMessage().equals("1")){
                    agreementMore.setText("取消授权");
                }

            }
        });

    }
    /**
     *  授权 法大大合同
     */
    private void  Empower(){
        CallUtils.call(BankAccountMessageActivity.this, InitHttpUtil.getHttpRequestService().Empower(accountModel.getPHONENUMBER()),new CallUtils.MyCallListener() {
            @Override
            public void onRespnseSuccess(String jsonString) {
                GradeModel gradeModel= JSON.parseObject(jsonString,GradeModel.class);
                if(gradeModel.isStatus()==true){
                    MyDialogUtil.showSimpleDialog(BankAccountMessageActivity.this, "恭喜您授权成功", "知道了");
                    agreementMore.setText("取消授权");
                }else{
                    MyDialogUtil.showSimpleDialog(BankAccountMessageActivity.this, "授权失败，请您稍后重试", "知道了");
                }

            }
        });

    }

    /**
     *  取消授权 法大大合同
     */
    private void  CancelEmpower(){

        CallUtils.call(BankAccountMessageActivity.this, InitHttpUtil.getHttpRequestService().CancelEmpower(accountModel.getPHONENUMBER()), new CallUtils.MyCallListener() {
            @Override
            public void onRespnseSuccess(String jsonString) {
                GradeModel gradeModel= JSON.parseObject(jsonString,GradeModel.class);
                if(gradeModel.isStatus()==true){
                    MyDialogUtil.showSimpleDialog(BankAccountMessageActivity.this, "取消授权成功", "知道了");
                    agreementMore.setText("确定授权");
                }else{
                    MyDialogUtil.showSimpleDialog(BankAccountMessageActivity.this, "取消失败，请您稍后重试", "知道了");
                }
            }
        });

    }

       //确定授权  弹框
    private void showDialog(String msg) {
        final Dialog dialog = new Dialog(this, R.style.My_Dialog);
        dialog.setContentView(R.layout.dialog_auth_name);
        dialog.setCancelable(false);
        TextView tv_msg = (TextView) dialog.findViewById(R.id.tv_msg);
        tv_msg.setText(msg);
        TextView tv_ok = (TextView) dialog.findViewById(R.id.tv_ok);
        tv_ok.setText("确定授权");
        dialog.findViewById(R.id.tv_cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        dialog.findViewById(R.id.tv_ok).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //调用授权方法
                Empower();
                dialog.dismiss();
            }
        });
        dialog.show();
        WindowManager m = (WindowManager) this.getSystemService(Context.WINDOW_SERVICE);
        Display d = m.getDefaultDisplay();  //为获取屏幕宽、高
        android.view.WindowManager.LayoutParams p = dialog.getWindow().getAttributes();  //获取对话框当前的参数值
        p.height = (int) (d.getHeight() * 0.3);   //高度设置为屏幕的0.3
        p.width = (int) (d.getWidth() * 0.8);    //宽度设置为屏幕的0.5
        dialog.setCanceledOnTouchOutside(false);// 设置点击屏幕Dialog不消失
        dialog.getWindow().setAttributes(p);     //设置生效

    }

      //取消授权 弹框
    private void ShowCancelEmpower(String msg) {
        // 如果最优标的存在则获得当前标的 Modle   否则提示当前红包无满足使用条件的标的
        //        getSbInfo();
        final Dialog dialog = new Dialog(this, R.style.My_Dialog);
        dialog.setContentView(R.layout.dialog_auth_name);
        dialog.setCancelable(false);
        TextView tv_msg = (TextView) dialog.findViewById(R.id.tv_msg);
        tv_msg.setText(msg);
        TextView tv_ok = (TextView) dialog.findViewById(R.id.tv_ok);
        tv_ok.setText("取消授权");
        dialog.findViewById(R.id.tv_cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        dialog.findViewById(R.id.tv_ok).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //调用 取消授权方法
                CancelEmpower();
                dialog.dismiss();
            }
        });
        dialog.show();
        WindowManager m = (WindowManager) this.getSystemService(Context.WINDOW_SERVICE);
        Display d = m.getDefaultDisplay();  //为获取屏幕宽、高
        android.view.WindowManager.LayoutParams p = dialog.getWindow().getAttributes();  //获取对话框当前的参数值
        p.height = (int) (d.getHeight() * 0.3);   //高度设置为屏幕的0.3
        p.width = (int) (d.getWidth() * 0.8);    //宽度设置为屏幕的0.5
        dialog.setCanceledOnTouchOutside(false);// 设置点击屏幕Dialog不消失
        dialog.getWindow().setAttributes(p);     //设置生效

    }
}
