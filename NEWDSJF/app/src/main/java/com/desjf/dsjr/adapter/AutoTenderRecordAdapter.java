package com.desjf.dsjr.adapter;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.desjf.dsjr.R;
import com.desjf.dsjr.biz.AutoTenderBiz;
import com.desjf.dsjr.biz.exception.BizFailure;
import com.desjf.dsjr.biz.exception.ZYException;
import com.desjf.dsjr.biz.task.BizDataAsyncTask;
import com.desjf.dsjr.model.AutoBorrowModel;
import com.desjf.dsjr.utils.ToastUtils;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/7/21 0021.
 */

public  class AutoTenderRecordAdapter extends BaseQuickAdapter<AutoBorrowModel.AUTOSETLISTBean,BaseViewHolder> {
    private Context context;
    private String userFlg;
    private String flg;
    private   List<AutoBorrowModel.AUTOSETLISTBean> list =new ArrayList<>();
    public AutoTenderRecordAdapter( @Nullable List<AutoBorrowModel.AUTOSETLISTBean> list,Context context) {
        super(R.layout.item_auto_tender_record, list);
        this.context = context;
    }
    public  List<AutoBorrowModel.AUTOSETLISTBean> getList() {
        return list;
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
                        return AutoTenderBiz.AutoBorrowSwitch(item.getSEQ(),userFlg);
                    }

                    @Override
                    protected void onExecuteSucceeded(String s) {
                        if (userFlg.equals("0")){
                            iv_kg.setImageResource(R.mipmap.handle_close);
                            userFlg="1";
                        }else{
                            iv_kg.setImageResource(R.mipmap.handle_open);
                            userFlg="0";
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
                tv_delete.setVisibility(View.VISIBLE);
                tv_change.setVisibility(View.VISIBLE);

            } else {
                tv_delete.setVisibility(View.GONE);
                tv_change.setVisibility(View.GONE);
            }
        } else {
              tv_change.setVisibility(View.GONE);
               tv_delete.setVisibility(View.GONE);
        }
        tv_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }


        });
    }




}
