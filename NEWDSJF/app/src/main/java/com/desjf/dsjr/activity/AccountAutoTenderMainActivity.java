package com.desjf.dsjr.activity;

import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.listener.OnItemLongClickListener;
import com.desjf.dsjr.R;
import com.desjf.dsjr.base.BaseActivity;
import com.desjf.dsjr.biz.AutoTenderBiz;
import com.desjf.dsjr.biz.exception.BizFailure;
import com.desjf.dsjr.biz.exception.ZYException;
import com.desjf.dsjr.biz.task.BizDataAsyncTask;
import com.desjf.dsjr.model.AutoBorrowModel;
import com.desjf.dsjr.utils.BarUtils;
import com.desjf.dsjr.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AccountAutoTenderMainActivity extends BaseActivity {
    @BindView(R.id.iv_auto_tender_add_back)
    ImageView ivAutoTenderAddBack;
    @BindView(R.id.tv_auto_tender_caozuo)
    TextView tvAutoTenderCaozuo;
    @BindView(R.id.tv_auto_tender_total)
    TextView tvAutoTenderTotal;
    @BindView(R.id.tv_auto_tender_touzi)
    TextView tvAutoTenderTouzi;
    @BindView(R.id.tv_auto_tender_count)
    TextView tvAutoTenderCount;
    @BindView(R.id.ll_auto_tender_record)
    LinearLayout llAutoTenderRecord;
    @BindView(R.id.tv_auto_leijizhuanqu)
    TextView tvAutoLeijizhuanqu;
    @BindView(R.id.tv_auto_total_money)
    TextView tvAutoTotalMoney;
    private   AutoTenderRecordAdapter1 autoTenderRecordAdapter;
    private List<String> str_list = new ArrayList<>();
    @BindView(R.id.iv_auto_tender_add)
    ImageView ivAutoTenderAdd;
    @BindView(R.id.rv_auto_tender_main)
    RecyclerView rvAutoTenderMain;
    private Context context = this;
    private AutoBorrowModel autoBorrowModels;
    private String str = "";
    private AutoBorrowModel autoModels_list = new AutoBorrowModel();
//    private List<AutoBorrowModel> autoModels_list = new ArrayList<>();
    private int changePosition;
    private BroadcastReceiver receiver;//通知通知修改数据
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_auto_tender_main);
        ButterKnife.bind(this);
        BarUtils.setColor(this, getResources().getColor(R.color.endmian), 0);

        //获取投标信息
        getAutoBorrow();
        initData();
    }

    private void getAutoBorrow() {

        showLoadingDialog();
        BizDataAsyncTask<AutoBorrowModel> getAuto = new BizDataAsyncTask<AutoBorrowModel>() {
            @Override
            protected AutoBorrowModel doExecute() throws ZYException, BizFailure {

                return AutoTenderBiz.getAutoBorrow();
            }

            @Override
            protected void onExecuteSucceeded(AutoBorrowModel autoBorrowModel) {

                tvAutoTenderCaozuo.setText("操作");

                hideLoadingDialog();

                autoBorrowModels = autoBorrowModel;

                initAuto(autoBorrowModel);
            }

            @Override
            protected void OnExecuteFailed(String error) {
                hideLoadingDialog();
                ToastUtils.showTost(context, error);
            }
        };
        getAuto.execute();
    }

    private void initAuto(AutoBorrowModel autoBorrowModel) {

        autoModels_list=autoBorrowModel;
        tvAutoTenderTouzi.setText(autoBorrowModel.getCAPITAL());
        tvAutoTenderTotal.setText(autoBorrowModel.getINTEREST());
        tvAutoTenderCount.setText(autoBorrowModel.getINTTOTALCNT());
        tvAutoLeijizhuanqu.setText("为您累计赚取("+autoBorrowModel.getINTERESTUNIT()+")");
        tvAutoTotalMoney.setText("投资总金额("+autoBorrowModel.getCAPITALUNIT()+")");
        autoTenderRecordAdapter = new AutoTenderRecordAdapter1(autoModels_list.getAUTOSETLIST(), AccountAutoTenderMainActivity.this);
        rvAutoTenderMain.setAdapter(autoTenderRecordAdapter);
        rvAutoTenderMain.setLayoutManager(new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL));
        autoTenderRecordAdapter.notifyDataSetChanged();
        str = autoBorrowModel.getAUTOTENDERMAXRATE();
    }

    @Override
    protected void onResume() {
        super.onResume();
        getAutoBorrow();
    }

    private void initData() {

        //长按删除
        rvAutoTenderMain.addOnItemTouchListener(new OnItemLongClickListener() {
            @Override
            public void onSimpleItemLongClick(BaseQuickAdapter adapter, View view, final int position) {
                new AlertDialog.Builder(AccountAutoTenderMainActivity.this)
                        .setItems(R.array.auto_bidding_operation,
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        switch (which) {
                                            case 0:
                                                //删除item
                                              //  deleteItem(position);
                                                break;
                                            case 1:
                                                break;
                                            default:
                                                break;
                                        }
                                    }
                                }).create().show();
            }
        });
    }
    private void setFlag(String flg, String str) {
        autoTenderRecordAdapter.setFlg("1");
        autoTenderRecordAdapter.notifyDataSetChanged();
        //tvAutoTenderCaozuo.setText("完成");
        tvAutoTenderCaozuo.setText(str);
    }
    //长按删除item
    private void deleteItem(final AutoBorrowModel.AUTOSETLISTBean item) {
        showLoadingDialog();
        BizDataAsyncTask<String> delItem = new BizDataAsyncTask<String>() {
            @Override
            protected String doExecute() throws ZYException, BizFailure {
                return AutoTenderBiz.delAutoBorrow( item.getSEQ());
            }

            @Override
            protected void onExecuteSucceeded(String s) {
                hideLoadingDialog();
                getAutoBorrow();
            }

            @Override
            protected void OnExecuteFailed(String error) {
                hideLoadingDialog();
            }
        };
        delItem.execute();
    }

    @OnClick({R.id.iv_auto_tender_add, R.id.iv_auto_tender_add_back, R.id.ll_auto_tender_record,R.id.tv_auto_tender_caozuo})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_auto_tender_add_back:
                //返回
                finish();
                break;
            case R.id.iv_auto_tender_add:
                //添加自动投标页面
                Intent intent = new Intent(AccountAutoTenderMainActivity.this, AccountAutoTenderActivity.class);
                intent.putExtra("flg",0);
                intent.putExtra("rate",str);
                startActivity(intent);
                break;
            case R.id.ll_auto_tender_record:
                //投标记录详情
                Intent gotoList = new Intent(AccountAutoTenderMainActivity.this, AccountAutoTenderListActivity.class);
                startActivity(gotoList);
                break;
            case R.id.tv_auto_tender_caozuo:

//
                if (tvAutoTenderCaozuo.getText().equals("操作")) {
                    Log.e("CAOZUO11","111");
                    autoTenderRecordAdapter.setFlg("1");
                    autoTenderRecordAdapter.notifyDataSetChanged();
                    tvAutoTenderCaozuo.setText("完成");
                } else {
                    autoTenderRecordAdapter.setFlg("0");
                    autoTenderRecordAdapter.notifyDataSetChanged();
                    tvAutoTenderCaozuo.setText("操作");
                }
                break;
        }

    }

    public  class AutoTenderRecordAdapter1 extends BaseQuickAdapter<AutoBorrowModel.AUTOSETLISTBean,BaseViewHolder> {

        private Context context;
        private String userFlg;
        private String flg;
        private List<AutoBorrowModel.AUTOSETLISTBean> list =new ArrayList<>();
        private int id;
        public AutoTenderRecordAdapter1(@Nullable List<AutoBorrowModel.AUTOSETLISTBean> list, Context context) {
            super(R.layout.item_auto_tender_record, list);
            this.context = context;
            //11.10新增
//             this.list=list;
        }
        public  List<AutoBorrowModel.AUTOSETLISTBean> getList() {
            Log.e("AutoBorrowModel器=======",list.size()+"");
            return list;
//            Log.e("AutoBorrowModel==========",list.size());
        }

        public String getFlg() {
            return flg;
        }

        public void setFlg(String flg) {
            this.flg = flg;
        }

        @Override
        protected void convert(BaseViewHolder helper, final AutoBorrowModel.AUTOSETLISTBean item) {
            final ImageView iv_kg = helper.getView(R.id.iv_tender_isopen);
            TextView tv_change=helper.getView(R.id.iv_change);
            TextView tv_delete=helper.getView(R.id.iv_delete);
            ImageView iv_change=helper.getView(R.id.iv_edit);
            ImageView iv_delete=helper.getView(R.id.iv_del);
            id = helper.getPosition();
            LinearLayout ll_edit = helper.getView(R.id.ll_edit);
            if (item.getUSINGFLG().equals("0")){
                //关闭

                iv_kg.setImageResource(R.mipmap.handle_close);
                userFlg="0";

            }else{
                //开启

                iv_kg.setImageResource(R.mipmap.handle_open);
                userFlg="1";

            }
            //点击开关
            iv_kg.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    BizDataAsyncTask<String> getSwitch = new BizDataAsyncTask<String>() {
                        @Override
                        protected String doExecute() throws ZYException, BizFailure {
                            Log.e("======点击按钮之前",userFlg);
                            Log.e("==============自动设置",AutoTenderBiz.AutoBorrowSwitch(item.getSEQ(),userFlg));
                            return AutoTenderBiz.AutoBorrowSwitch(item.getSEQ(),userFlg);

                        }

                        @Override
                        protected void onExecuteSucceeded(String s) {
                            Log.e("======自动投标关闭",userFlg);
                            if (userFlg.equals("0")){
                                iv_kg.setImageResource(R.mipmap.handle_open);
                                //
                                userFlg="1";
                                Log.e("zidong投标关闭",userFlg);
                            }else{
                                iv_kg.setImageResource(R.mipmap.handle_close);

                                userFlg="0";
                                Log.e("zidong投标kaiqi",userFlg);
                            }
                        }

                        @Override
                        protected void OnExecuteFailed(String error) {
                            ToastUtils.showTost(context,error);

                        }
                    };
                    getSwitch.execute();
                }
            });


            //借款开始期限
            String periodbegin = item.getPERIODBEGIN();
            //借款结束期限
            String periodend = item.getPERIODEND();
            helper.setText(R.id.tv_tender_jkqx,periodbegin+"~"+periodend+"个月");

            //年化最低利率
            String ratebegin = item.getRATEBEGIN();
            //年化最高利率
            String rateend = item.getRATEEND();
            helper.setText(R.id.tv_tender_nhsy,ratebegin+"%~"+rateend+"%");
            //投资金额
            String loanamount = item.getLOANAMOUNT();
            String loanamountunit = item.getLOANAMOUNTUNIT();
            helper.setText(R.id.tv_tender_jkje,loanamount+loanamountunit);
            //ToastUtils.showTost(context,flg);
            if (getFlg() != null) {
                if (getFlg().equals("1")) {
                    ll_edit.setVisibility(View.VISIBLE);
                } else {
                    ll_edit.setVisibility(View.INVISIBLE);
                }
            } else {
                ll_edit.setVisibility(View.INVISIBLE);
            }
//            tv_delete.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    deleteItem(item);
//                }
//
//
//            });
//            tv_change.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    change(item);
//                }
//            });
            iv_change.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    change(item,id);
                }
            });
            iv_delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    deleteItem(item);
                }
            });
        }




    }
   //自动投标修改
    private void change(final AutoBorrowModel.AUTOSETLISTBean  item,int position) {
        changePosition=position;
        Intent intent = new Intent(this, AccountAutoTenderActivity.class);
        intent.putExtra("flg",1);
        intent.putExtra("rate",str);
        intent.putExtra("xiugai",item);
        startActivity(intent);
    }

}
