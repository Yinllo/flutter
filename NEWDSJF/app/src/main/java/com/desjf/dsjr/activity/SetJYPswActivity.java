package com.desjf.dsjr.activity;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.desjf.dsjr.R;
import com.desjf.dsjr.base.BaseActivity;
import com.desjf.dsjr.biz.TransactionPasswordBiz;
import com.desjf.dsjr.biz.exception.BizFailure;
import com.desjf.dsjr.biz.exception.ZYException;
import com.desjf.dsjr.biz.task.BizDataAsyncTask;
import com.desjf.dsjr.util.MyDialogUtil;
import com.desjf.dsjr.utils.ToastUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SetJYPswActivity extends BaseActivity {
    @BindView(R.id.et_set_jy_psw)
    EditText etSetJyPsw;
//    @BindView(R.id.et_new_jy_psw)
//    EditText etNewJyPsw;
//    @BindView(R.id.tv_forget_py_psw)
//    TextView tvForgetPyPsw;
    private Context context = this;
    @BindView(R.id.iv_set_jypsw_back)
    ImageView ivResetJypswBack;
    @BindView(R.id.iv_set_jy_psw)
    ImageView ivSetJyPsw;
//    @BindView(R.id.iv_new_jy_psw)
//    ImageView ivNewJyPsw;
    @BindView(R.id.tv_set_psw_jy)
    TextView tvSetPswJy;
    private boolean isPsw = false;
    private boolean isReSet = true;
    private String pwd;
    private boolean isok;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_jypsw);
        ButterKnife.bind(this);

    }

    @OnClick({R.id.iv_set_jypsw_back, R.id.iv_set_jy_psw, R.id.tv_set_psw_jy})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_set_jypsw_back:
                Intent i=getIntent();
                if(i!=null){
                    Intent intent_setJyPsw = new Intent(this, NewInvestmentDetailsActivity.class);
                    intent_setJyPsw.putExtra("ID",i.getStringExtra("ID"));
                    intent_setJyPsw.putExtra("Info", i.getSerializableExtra("Info"));
                    startActivity(intent_setJyPsw);
                }
                finish();
                break;
            case R.id.iv_set_jy_psw:
                //隐藏原密码
                if (isPsw) {
                    etSetJyPsw.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    ivSetJyPsw.setImageResource(R.mipmap.see_psw);
                    isPsw = !isPsw;
                } else {
                    etSetJyPsw.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    ivSetJyPsw.setImageResource(R.mipmap.unsea_psw);
                    isPsw = !isPsw;
                }
                break;
            case R.id.tv_set_psw_jy:
                initSetJy();
                break;
        }
    }

    private void initSetJy() {
        if (etSetJyPsw.getText().toString().isEmpty()) {
            MyDialogUtil.showSimpleDialog(SetJYPswActivity.this,"密码不能为空","知道了");
            return;
        }if(checkPwd()){
            MyDialogUtil.showSimpleDialog(context,"为保证您的财产安全，请勿使用单一或者长度过短的密码!","知道了");
            return;
        }
        showLoadingDialog();
        BizDataAsyncTask<String> setPayPsw = new BizDataAsyncTask<String>() {
            @Override
            protected String doExecute() throws ZYException, BizFailure {
                return TransactionPasswordBiz.SettingPayPassword(etSetJyPsw.getText().toString());
            }

            @Override
            protected void onExecuteSucceeded(String s) {
                hideLoadingDialog();
                showSimpleDialog(context,"恭喜您成功设置交易密码！","知道了");

            }

            @Override
            protected void OnExecuteFailed(String error) {
                hideLoadingDialog();
                ToastUtils.showTost(context, error.toString());
            }
        };
        setPayPsw.execute();
    }

    public void showSimpleDialog(Context context , String message,String ok) {
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
                Intent i=new Intent(SetJYPswActivity.this,MainActivity.class);
                i.putExtra("ID",getIntent().getStringExtra("ID"));
                i.putExtra("Info", getIntent().getSerializableExtra("Info"));
                startActivity(i);
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

    //判断输入的密码是否为纯数字或者纯字母
    private boolean checkPwd(){
        pwd=etSetJyPsw.getText().toString();
        String nreg="^\\d+$";//数字
        boolean n=pwd.matches(nreg);

        String reg="^[a-zA-Z]+$";//字母
        boolean s=pwd.matches(reg);
        boolean length=pwd.length()<8;
        boolean lengths=pwd.length()>20;
        if(n||s||length||lengths){
            isok=true;
        }else {
            isok=false;
        }
        return isok;
    }


}
