package com.desjf.dsjr.activity;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.desjf.dsjr.R;
import com.desjf.dsjr.adapter.WithDrawRecordAdapter;
import com.desjf.dsjr.base.BaseActivity;
import com.desjf.dsjr.base.BaseApplication;
import com.desjf.dsjr.biz.WithDrawBiz;
import com.desjf.dsjr.biz.exception.BizFailure;
import com.desjf.dsjr.biz.exception.ZYException;
import com.desjf.dsjr.biz.task.BizDataAsyncTask;
import com.desjf.dsjr.biz.task.SoapProcessor;
import com.desjf.dsjr.cyview.base.BaseDialog;
import com.desjf.dsjr.model.AccountModel;
import com.desjf.dsjr.model.WithDrawModel;
import com.desjf.dsjr.model.WithDrawStageModel;
import com.desjf.dsjr.util.MyDialogUtil;
import com.desjf.dsjr.utils.ToastUtils;
import com.desjf.dsjr.widget.WithDrawListener;
import com.google.gson.JsonElement;
import com.umeng.analytics.MobclickAgent;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AccountWithDrawActivity extends BaseActivity {

    private Context context = this;
    @BindView(R.id.iv_withdraw_back)
    ImageView ivWithdrawBack;
    @BindView(R.id.tv_with_draw_shuom)
    TextView tvWithDrawShuom;
    @BindView(R.id.tv_with_draw_money)
    TextView tvWithDrawMoney;
    @BindView(R.id.et_with_draw_less)
    EditText etWithDrawLess;
    @BindView(R.id.tv_with_draw_yeqt)
    TextView tvWithDrawYeqt;
    @BindView(R.id.et_with_draw_psw)
    EditText etWithDrawPsw;
    @BindView(R.id.tv_with_draw_forgetpsw)
    TextView tvWithDrawForgetpsw;
    @BindView(R.id.btn_withdraw_tixian)
    Button btnWithdrawTixian;
    private AccountModel accountModel;
    private WithDrawRecordAdapter withDrawAdapter;
    private int i = 0;
    private List<WithDrawModel> withDrawList = new ArrayList<>();
    private int lastVisibleItem;
    private LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
    private WithDrawListener withDraw = new WithDrawListener() {
        @Override
        public void Chexiao(String s) {
            initCx(s);
        }
    };
    private String with_less, all_money;


    private BaseDialog dialog_tixian;
    private TextView tv_poundage, tv_sum;


    private Handler handler;

    private void initCx(final String s) {
        showLoadingDialog();
        BizDataAsyncTask<String> getCx = new BizDataAsyncTask<String>() {
            @Override
            protected String doExecute() throws ZYException, BizFailure {
                return WithDrawBiz.getWithDrawCx(s);
            }

            @Override
            protected void onExecuteSucceeded(String s) {
                hideLoadingDialog();
                initData();
                i = 0;
                withDrawList.clear();
                if (String.valueOf(s).equals("1")) {
                    showToast("提现撤销成功");
                } else {
                    showToast("提现撤销失败");
                }
            }

            @Override
            protected void OnExecuteFailed(String error) {
                hideLoadingDialog();
                showToast(error + "");
            }
        };
        getCx.execute();
    }

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


        setContentView(R.layout.activity_account_with_draw);

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
                showLoadingDialog();
                @SuppressLint("StaticFieldLeak") BizDataAsyncTask<String> getTixina = new BizDataAsyncTask<String>() {
                    @Override
                    protected String doExecute() throws ZYException, BizFailure {
                        return WithDrawBiz.getWithDraw(etWithDrawPsw.getText().toString(), etWithDrawLess.getText().toString());

                    }

                    @Override
                    protected void onExecuteSucceeded(String s) {
                        //友盟统计 埋点
                        MobclickAgent.onEvent(AccountWithDrawActivity.this,"提现");
                        hideLoadingDialog();
                        etWithDrawLess.setText("");
                        etWithDrawPsw.setText("");
                        showSkipDialog(context,"恭喜您，提现申请成功！","知道了");
                    }

                    @Override
                    protected void OnExecuteFailed(String error) {
                        hideLoadingDialog();
                        etWithDrawPsw.setText("");
                        MyDialogUtil.showSimpleDialog(context,error,"知道了");
                    }
                };
                getTixina.execute();
            }
        });

        ButterKnife.bind(this);
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
        showLoadingDialog();
        BizDataAsyncTask<WithDrawStageModel> getWithDSm = new BizDataAsyncTask<WithDrawStageModel>() {
            @Override
            protected WithDrawStageModel doExecute() throws ZYException, BizFailure {
                return WithDrawBiz.getWithDrawStage();
            }

            @Override
            protected void onExecuteSucceeded(WithDrawStageModel withDrawStageModel) {
                hideLoadingDialog();
                if (withDrawStageModel != null) {
                    all_money = withDrawStageModel.getBALANCE();
                    tvWithDrawMoney.setText(withDrawStageModel.getBALANCE() + "元");
                    with_less = withDrawStageModel.getMIN_WITHDRAW();
                    etWithDrawLess.setHint("单笔提现最低" + withDrawStageModel.getMIN_WITHDRAW() + "元");
                }
            }

            @Override
            protected void OnExecuteFailed(String error) {
                hideLoadingDialog();
                Toast.makeText(context,error,Toast.LENGTH_LONG).show();
//                showToast(error);
            }
        };
        getWithDSm.execute();
//        BaseApplication baseApplication = (BaseApplication) getApplication();
//        accountModel = baseApplication.getAccountModel();
//        if (accountModel != null) {
//            tvWithDrawMoney.setText(accountModel.getBALANCE() + "元");
//            etWithDrawLess.setHint("单笔提现最低"+accountModel.getMINWITHDRAW()+"元");
//        }

    }

    @OnClick({R.id.iv_withdraw_back, R.id.tv_with_draw_shuom, R.id.tv_with_draw_yeqt, R.id.tv_with_draw_forgetpsw, R.id.btn_withdraw_tixian})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_withdraw_back:
                finish();
                break;
            case R.id.tv_with_draw_shuom:
                Intent intent = new Intent(AccountWithDrawActivity.this, PresentRecordActivity.class);
//                intent.putExtra("web", 6);
                startActivity(intent);
                break;
            case R.id.tv_with_draw_yeqt:
//                if (accountModel != null) {
//                    String balance = accountModel.getBALANCE();
////                    balance = balance.substring(0, balance.length() - 3);
//                    balance=balance.replace(",","");
//                    etWithDrawLess.setText(balance);
//                }
                String balance = all_money;
//                    balance = balance.substring(0, balance.length() - 3);
                balance = balance.replace(",", "");
                etWithDrawLess.setText(balance);
                break;
            case R.id.tv_with_draw_forgetpsw:
                Intent intent_jy = new Intent(AccountWithDrawActivity.this, ForgetJYPswStepOneActivity.class);
                startActivity(intent_jy);
                break;
            case R.id.btn_withdraw_tixian:
                initTixian();
                break;
        }
    }

    private void initTixian() {
        if (etWithDrawLess.getText().toString().isEmpty()) {
            ToastUtils.showTost(context, "请输入提现金额");
            return;
        }
        if (etWithDrawLess.getText().toString().endsWith(".")) {
            ToastUtils.showTost(context, "请输入正确的提现金额");
            return;
        }
        double a = Double.parseDouble(etWithDrawLess.getText().toString());
        double b = Double.parseDouble(with_less);
        if (a < b) {
//            ToastUtils.showTost(context, "提现金额不少于" + with_less + "元");
            MyDialogUtil.showSimpleDialog(context,"提现金额不少于" + with_less + "元","知道了");
            return;
        }
        //如果提现金额大于20万
        if (a >200000) {
//            ToastUtils.showTost(context, "提现金额不少于" + with_less + "元");
            MyDialogUtil.showSimpleDialog(context,"单笔提现金额不能大于20万","知道了");
            return;
        }
        if (etWithDrawPsw.getText().toString().isEmpty()) {
            ToastUtils.showTost(context, "请输入交易密码");
            return;
        }


        tv_sum.setText(etWithDrawLess.getText().toString().trim());

        new Thread(new Runnable() {
            public void run() {
                SoapProcessor ksoap2 = new SoapProcessor("Service", "getWithdrawFee", true);
                ksoap2.setProperty("withdrawAmount", etWithDrawLess.getText().toString().trim(), SoapProcessor.PropertyType.TYPE_STRING);

                JsonElement element = null;
                try {
                    element = ksoap2.request();
                } catch (ZYException e) {
                    e.printStackTrace();
                }

                if(element!=null){

//                Gson gson = new GsonBuilder().create();
                    Log.e("fanhui",element.toString());
//                WithdrawPoundageBean withdrawPoundageBean = gson.fromJson(element, WithdrawPoundageBean.class);
                    Message message = Message.obtain();
                    message.what = 0;
                    message.obj = element;
                    handler.sendMessage(message);
                }else {
                    Message message = Message.obtain();
                    message.what = 0;
                    message.obj = 0;
                    handler.sendMessage(message);
                }
            }
        }).start();


    }
}
