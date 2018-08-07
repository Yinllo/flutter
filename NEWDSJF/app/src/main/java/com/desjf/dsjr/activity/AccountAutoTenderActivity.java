package com.desjf.dsjr.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.desjf.dsjr.R;
import com.desjf.dsjr.base.BaseActivity;
import com.desjf.dsjr.base.BaseApplication;
import com.desjf.dsjr.biz.AutoTenderBiz;
import com.desjf.dsjr.biz.exception.BizFailure;
import com.desjf.dsjr.biz.exception.ZYException;
import com.desjf.dsjr.biz.task.BizDataAsyncTask;
import com.desjf.dsjr.model.AccountModel;
import com.desjf.dsjr.model.AutoBorrowModel;
import com.desjf.dsjr.utils.ToastUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AccountAutoTenderActivity extends BaseActivity {

    @BindView(R.id.tv_add_auto_back)
    ImageView tvAddAutoBack;
    @BindView(R.id.iv_auto_add_open)
    ImageView ivAutoAddOpen;
    @BindView(R.id.tv_add_auto_total)
    TextView tvAddAutoTotal;
    @BindView(R.id.et_auto_borrow)
    EditText etAutoBorrow;
    @BindView(R.id.tv_add_auto_all)
    TextView tvAddAutoAll;
    @BindView(R.id.sp_low_time)
    Spinner spLowTime;
    @BindView(R.id.sp_hight_time)
    Spinner spHightTime;
    @BindView(R.id.iv_add_tender_all)
    ImageView ivAddTenderAll;
    @BindView(R.id.iv_add_tender_jiangli)
    ImageView ivAddTenderJiangli;
    @BindView(R.id.iv_mbfsqx)
    ImageView ivMbfsqx;
    @BindView(R.id.iv_jtjqx)
    ImageView ivJtjqx;
    @BindView(R.id.sp_low_lilv)
    Spinner spLowLilv;
    @BindView(R.id.sp_hight_lilv)
    Spinner spHightLilv;
    @BindView(R.id.tv_add_auto_tijiao)
    TextView tvAddAutoTijiao;
    @BindView(R.id.iv_auto_tender_info)
    ImageView ivAutoTenderInfo;
    private String[] str_time = {"1个月", "2个月", "3个月", "4个月", "5个月", "6个月", "7个月", "8个月"
            , "9个月", "10个月", "11个月", "12个月", "13个月", "14个月", "15个月", "16个月", "17个月", "18个月", "19个月", "20个月",
            "21个月", "22个月", "23个月", "24个月"};
    private String[] str_lilv = {"1%", "2%", "3%", "4%", "5%", "6%", "7%", "8%", "9%", "10%", "11%", "12%",
            "13%", "14%", "15%", "16%", "17%", "18%", "19%", "20%", "21%", "22%", "23%", "24%"};
    private String[] str_time_daoxu = {"24个月", "23个月", "22个月", "21个月", "20个月", "19个月", "18个月", "17个月"
            , "16个月", "15个月", "14个月", "13个月", "12个月", "11个月", "10个月", "9个月", "8个月", "7个月", "6个月", "5个月",
            "4个月", "3个月", "2个月", "1个月"};
    private String[] str_lilv_daoxu = {"24%", "23%", "22%", "21%", "20%", "19%", "18%", "17%", "16%", "15%", "14%", "13%",
            "12%", "11%", "10%", "9%", "8%", "7%", "6%", "5%", "4%", "3%", "2%", "1%"};
    private ArrayAdapter<String> adapter_time, adapter_lilv, adapter_time_daoxu, adapter_lilv_daoxu;
    private Context context = this;
    private boolean isOpen = true;
    private boolean isManbiao = true;
    private boolean isJitou = true;
    private String lowTime, hightTime, lowQX, hightQx;
    private String isJiangli = "0";
    private String mbfs = "1";
    private String jtjqx = "";
    private String sfkq = "0";
    private AccountModel accountModel;
    private String str;
    private AutoBorrowModel.AUTOSETLISTBean xiugaoBean;
    private int flg=-1;
    private String seq="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_auto_tender);
        ButterKnife.bind(this);
        BaseApplication baseApplication = (BaseApplication) getApplication();
        accountModel = baseApplication.getAccountModel();
        Intent intent = getIntent();
        flg = intent.getIntExtra("flg", -1);
        if (flg==0){
            //添加进入
            str = intent.getStringExtra("rate");
            seq="";
        }else if (flg==1){
            //修改进入
            str = intent.getStringExtra("rate");
            xiugaoBean = (AutoBorrowModel.AUTOSETLISTBean) intent.getSerializableExtra("xiugai");
            //seq
            seq = xiugaoBean.getSEQ();
            //原金额
            etAutoBorrow.setText(xiugaoBean.getLOANAMOUNT().toString());
            //原开启
            if (xiugaoBean.getUSINGFLG().equals("1")){
                //开启
                sfkq = "1";
                ivAutoAddOpen.setImageResource(R.mipmap.checked);
                isOpen=false;
            }else{
                //关闭
                sfkq = "0";
                ivAutoAddOpen.setImageResource(R.mipmap.unchecked);
                isOpen=true;
            }
            //原即投即起息起息方式
            if (xiugaoBean.getCHKBORROWWANDER().equals("2")){
                //选中
                jtjqx = "2";
                ivJtjqx.setImageResource(R.mipmap.checked);
                isJitou = false;
            }else{
                //未选中
                jtjqx = "";
                ivJtjqx.setImageResource(R.mipmap.unchecked);
                isJitou = true;
            }
            //原满标复审起息方式
            if (xiugaoBean.getCHKBORROWMORTGAGE().equals("1")){
                //选中
                mbfs = "1";
                ivMbfsqx.setImageResource(R.mipmap.checked);
                isManbiao = false;
            }else{
                //未选中
                mbfs = "";
                ivMbfsqx.setImageResource(R.mipmap.unchecked);
                isManbiao = true;
            }
            //是否奖励
            if (xiugaoBean.getAWARDFLG().equals("1")){
                //奖励
                ivAddTenderAll.setImageResource(R.mipmap.no_point_round);
                ivAddTenderJiangli.setImageResource(R.mipmap.point_round);
                isJiangli = "1";
            }else{
                //全部
                ivAddTenderAll.setImageResource(R.mipmap.point_round);
                ivAddTenderJiangli.setImageResource(R.mipmap.no_point_round);
                isJiangli = "0";
            }
        }

        initData();
    }

    private void initData() {
        adapter_time_daoxu = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, str_time_daoxu);
        adapter_time_daoxu.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spHightTime.setAdapter(adapter_time_daoxu);
        adapter_time_daoxu.notifyDataSetChanged();

        adapter_lilv = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, str_lilv);
        adapter_lilv.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spLowLilv.setAdapter(adapter_lilv);
        adapter_lilv.notifyDataSetChanged();

        adapter_time = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, str_time);
        adapter_time.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spLowTime.setAdapter(adapter_time);
        adapter_time.notifyDataSetChanged();

        adapter_lilv_daoxu = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, str_lilv_daoxu);
        adapter_lilv_daoxu.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spHightLilv.setAdapter(adapter_lilv_daoxu);
        adapter_lilv_daoxu.notifyDataSetChanged();

        spLowTime.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                lowTime = String.valueOf(i + 1);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        spHightTime.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                hightTime = String.valueOf(24 - i);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        spLowLilv.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                lowQX = String.valueOf(i + 1);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        spHightLilv.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                hightQx = String.valueOf(24 - i);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        tvAddAutoTotal.setText(accountModel.getBALANCE());

    }

    @OnClick({R.id.tv_add_auto_back, R.id.iv_auto_add_open, R.id.tv_add_auto_all, R.id.iv_add_tender_all, R.id.iv_add_tender_jiangli, R.id.iv_mbfsqx, R.id.iv_jtjqx, R.id.tv_add_auto_tijiao,R.id.iv_auto_tender_info})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_add_auto_back:
                //返回
                finish();
                break;
            case R.id.iv_auto_add_open:
                //是否开启
                if (isOpen) {
                    sfkq = "1";
                    ivAutoAddOpen.setImageResource(R.mipmap.checked);
                    isOpen = !isOpen;
                } else {
                    sfkq = "0";
                    ivAutoAddOpen.setImageResource(R.mipmap.unchecked);
                    isOpen = !isOpen;
                }
                break;
            case R.id.tv_add_auto_all:
                //余额全投
                etAutoBorrow.setText(tvAddAutoTotal.getText());
                break;
            case R.id.iv_add_tender_all:
                ivAddTenderAll.setImageResource(R.mipmap.point_round);
                ivAddTenderJiangli.setImageResource(R.mipmap.no_point_round);
                isJiangli = "0";
                break;
            case R.id.iv_add_tender_jiangli:
                ivAddTenderAll.setImageResource(R.mipmap.no_point_round);
                ivAddTenderJiangli.setImageResource(R.mipmap.point_round);
                isJiangli = "1";
                break;
            case R.id.iv_mbfsqx:
                if (isManbiao) {
                    mbfs = "";
                    ivMbfsqx.setImageResource(R.mipmap.unchecked);
                    isManbiao = !isManbiao;
                } else {
                    mbfs = "1";
                    ivMbfsqx.setImageResource(R.mipmap.checked);
                    isManbiao = !isManbiao;
                }
                break;
            case R.id.iv_jtjqx:
                if (isJitou) {
                    jtjqx = "2";
                    ivJtjqx.setImageResource(R.mipmap.checked);
                    isJitou = !isJitou;
                } else {
                    jtjqx = "";
                    ivJtjqx.setImageResource(R.mipmap.unchecked);
                    isJitou = !isJitou;
                }
                break;
            case R.id.tv_add_auto_tijiao:
                //添加一标
                iniAdd();
                break;
            case R.id.iv_auto_tender_info:
                //投标规则
                Intent intent = new Intent(AccountAutoTenderActivity.this,AutoTenderInfoActivity.class);
                intent.putExtra("rate",str);
                startActivity(intent);
                break;
        }
    }

    private void iniAdd() {
        showLoadingDialog();
        BizDataAsyncTask<String> addTender = new BizDataAsyncTask<String>() {
            @Override
            protected String doExecute() throws ZYException, BizFailure {
                return AutoTenderBiz.addAutoBorrow(seq, etAutoBorrow.getText().toString(), "", lowTime, hightTime, isJiangli, lowQX, hightQx,
                        mbfs, jtjqx, sfkq);
            }

            @Override
            protected void onExecuteSucceeded(String s) {
                hideLoadingDialog();
                if (flg==0){
                    ToastUtils.showTost(context, "开启成功");
                    finish();
                }else{
                    ToastUtils.showTost(context, "修改成功");
                    finish();
                }

            }

            @Override
            protected void OnExecuteFailed(String error) {
                hideLoadingDialog();
                ToastUtils.showTost(context, error);
            }
        };
        addTender.execute();
    }
}
