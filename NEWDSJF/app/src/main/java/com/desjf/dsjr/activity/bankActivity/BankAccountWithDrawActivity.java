package com.desjf.dsjr.activity.bankActivity;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.desjf.dsjr.R;
import com.desjf.dsjr.activity.BankWebViewActivity;
import com.desjf.dsjr.adapter.WithDrawRecordAdapter;
import com.desjf.dsjr.base.BaseActivity;
import com.desjf.dsjr.base.BaseApplication;
import com.desjf.dsjr.bean.NewRegBean;
import com.desjf.dsjr.biz.retrofit.BankHttpUtil;
import com.desjf.dsjr.biz.retrofit.BankHttpUtils;
import com.desjf.dsjr.biz.retrofit.CallUtils;
import com.desjf.dsjr.cyview.base.BaseDialog;
import com.desjf.dsjr.model.AccountModel;
import com.desjf.dsjr.model.WithDrawModel;
import com.desjf.dsjr.model.bankModel.BankUserBalanceModel;
import com.desjf.dsjr.util.MyDialogUtil;
import com.desjf.dsjr.util.NumberUtil;
import com.desjf.dsjr.utils.ButtonUtils;
import com.desjf.dsjr.utils.DataUtil;
import com.desjf.dsjr.utils.PreferenceCache;
import com.desjf.dsjr.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class BankAccountWithDrawActivity extends BaseActivity {

    private Context context = this;
    @BindView(R.id.iv_withdraw_back)
    ImageView ivWithdrawBack;
    @BindView(R.id.tv_with_draw_shuom)
    TextView tvWithDrawShuom;
    @BindView(R.id.tv_with_draw_money)
    TextView tvWithDrawMoney;
    @BindView(R.id.et_with_draw_less)
    EditText etWithDrawLess;


//    @BindView(R.id.tv_with_draw_yeqt)
//    TextView tvWithDrawYeqt;
//    @BindView(R.id.et_with_draw_psw)
//    EditText etWithDrawPsw;
//    @BindView(R.id.tv_with_draw_forgetpsw)
//    TextView tvWithDrawForgetpsw;
    @BindView(R.id.btn_withdraw_tixian)
    Button btnWithdrawTixian;

//    @BindView(R.id.radioButton1)
//    RadioButton radioButton1;
//    @BindView(R.id.radioButton2)
//    RadioButton radioButton2;
    private int flag=0;//到账方式记录值 1代表T+0  0代表T+1

    private AccountModel accountModel;
    private WithDrawRecordAdapter withDrawAdapter;
    private int i = 0;
    private List<WithDrawModel> withDrawList = new ArrayList<>();
    private int lastVisibleItem;
    private LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);

    private double all_money;


    private BaseDialog dialog_tixian;
    private TextView tv_poundage, tv_sum;
    List<BankUserBalanceModel.UsermessageBean> list=null;
    private String sys;
    private String phone;
    private Handler handler;

    @SuppressLint("HandlerLeak")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                tv_poundage.setText(String.valueOf(msg.obj));
                dialog_tixian.show();
            }
        };


        //输入框只能输入数字和小数点
//        etWithDrawLess.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
        setContentView(R.layout.activity_bank_account_with_draw);

        dialog_tixian = new BaseDialog(this);

        dialog_tixian.config(R.layout.dialog_tixian, Gravity.CENTER, -1, true);
        tv_poundage = dialog_tixian.findViewById(R.id.tv_poundage);
        tv_sum = dialog_tixian.findViewById(R.id.tv_sum);
        dialog_tixian.findViewById(R.id.tv_cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog_tixian.dismiss();
            }
        });
        dialog_tixian.findViewById(R.id.tv_confirm).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog_tixian.dismiss();

            }
        });

        ButterKnife.bind(this);

        BaseApplication baseApplication = (BaseApplication) getApplication();
        accountModel = baseApplication.getAccountModel();
        if (accountModel!=null){
            phone = accountModel.getPHONENUMBER().toString();
            sys = accountModel.getOPENFORE();
        }
        initData();

    }

    public void showSkipDialog(Context context , String message,String ok) {
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
                Intent intent = new Intent();
                intent.setAction("withdraw");
                BaseApplication.getAppContext().sendBroadcast(intent);
                dialog.dismiss();
                finish();
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



    private void initData() {

        //设置默认选中项
//        radioButton1.setChecked(true);
//        flag=1;

        CallUtils.call(BankAccountWithDrawActivity.this, BankHttpUtils.getHttpRequestService().QueryBalance(PreferenceCache.getToken()), new CallUtils.MyCallListener() {
            @Override
            public void onRespnseSuccess(String jsonString) {

                BankUserBalanceModel bankUserBalanceModel=JSON.parseObject(jsonString,BankUserBalanceModel.class);
                if(bankUserBalanceModel!=null){
                    list=bankUserBalanceModel.getUsermessage();
                    for(int i=0;i<list.size();i++){
                        if(list.get(i).getCap_typ().equals("1")){
                            //可用余额
                            if(list.get(i).getAvlBal().equals("0")){
                                tvWithDrawMoney.setText("0.00元");
                                all_money=Double.parseDouble(list.get(i).getAvlBal());
                            }else {
                                tvWithDrawMoney.setText(DataUtil.toMoney(Float.parseFloat(list.get(i).getAvlBal())) + "元");
                                all_money = Double.parseDouble(list.get(i).getAvlBal());

                            }
                        }
                    }
                }
            }
        });


    }

    @OnClick({R.id.iv_withdraw_back, R.id.tv_with_draw_shuom, R.id.btn_withdraw_tixian})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_withdraw_back:
                finish();
                break;
            case R.id.tv_with_draw_shuom:
                //提现记录
                Intent intent = new Intent(BankAccountWithDrawActivity.this, BankPresentRecordActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_withdraw_tixian:
                //提现
                if(ButtonUtils.isFastClick()) {
                    initTixian();
                }
                break;
//            case R.id.radioButton1:  //提现方式
//                flag=1;
//                break;
//            case R.id.radioButton2:  //提现方式
//                flag=0;
//                break;
        }
    }

    private void initTixian() {
        if (etWithDrawLess.getText().toString().isEmpty()) {
            ToastUtils.showTost(context, "请输入提现金额");
            return;
        }
        if (etWithDrawLess.getText().toString().endsWith(".")||etWithDrawLess.getText().toString().startsWith(".")) {
            ToastUtils.showTost(context, "请输入正确的提现金额");
            return;
        }
        if (NumberUtil.getNumber(etWithDrawLess.getText().toString())>2) {
            ToastUtils.showTost(context, "您的输入有误，最多小数点后两位数字");
            return;
        }
        double a = Double.parseDouble(etWithDrawLess.getText().toString());
        if (a <100) {
            MyDialogUtil.showSimpleDialog(context,"提现金额不少于" + 100 + "元","知道了");
            return;
        }
//        //如果提现金额大于可用余额
        if (a >all_money) {
            MyDialogUtil.showSimpleDialog(context,"余额不足","知道了");
            return;
        }
        tv_sum.setText(etWithDrawLess.getText().toString().trim());
        CallUtils.call(BankAccountWithDrawActivity.this, BankHttpUtil.getHttpRequestService().tixian(phone,etWithDrawLess.getText().toString(),flag+""), new CallUtils.MyCallListener() {
            @Override
            public void onRespnseSuccess(String jsonString) {
                NewRegBean newRegBean= JSON.parseObject(jsonString,NewRegBean.class);
                Intent i=new Intent(BankAccountWithDrawActivity.this,BankWebViewActivity.class);
                i.putExtra("newRegBean",newRegBean);
                i.putExtra("type",3);
                startActivity(i);
            }
        });
    }

}
