package com.desjf.dsjr.fragment;


import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.desjf.dsjr.R;
import com.desjf.dsjr.activity.MainActivity;
import com.desjf.dsjr.adapter.NewMyRedBagAdapter;
import com.desjf.dsjr.base.BaseFragment;
import com.desjf.dsjr.biz.AllCouponBiz;
import com.desjf.dsjr.biz.exception.BizFailure;
import com.desjf.dsjr.biz.exception.ZYException;
import com.desjf.dsjr.biz.task.BizDataAsyncTask;
import com.desjf.dsjr.config.DsjrConfig;
import com.desjf.dsjr.model.AllCouponAndInterestModle;
import com.desjf.dsjr.model.AllCouponModel;
import com.desjf.dsjr.model.BestProductModel;
import com.desjf.dsjr.model.ProjectInfoModel;
import com.desjf.dsjr.utils.ToastUtils;
import com.desjf.dsjr.widget.ClickListener;
import com.desjf.dsjr.widget.ClickMore;
import com.desjf.dsjr.widget.WrapContentLinearLayoutManager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class MyRedBagFragment extends BaseFragment {
    @BindView(R.id.rv_my_redbag)
    RecyclerView rvMyRedbag;
    //    @BindView(R.id.et_my_red_bag)
    //    EditText etMyRedBag;
    //    @BindView(R.id.btn_my_redbag_duihuan)
    //    Button btnMyRedbagDuihuan;
    @BindView(R.id.srl_redBag)
    SwipeRefreshLayout srlRedBag;
    private NewMyRedBagAdapter myRedBagAdapter;
    private List<AllCouponAndInterestModle> allCouponModel = new ArrayList<>();
    private AllCouponModel allCoupon;
    private AllCouponModel all;

    private String msg;//弹框显示的消息
    private String type = "";//类型
    private String id = "";//当前红包或者加息券的id
    private String bid = "";//当前 标的 的id
    private String number = "";//当前红包或者加息券的金额
    private String min = "";//当前红包或者加息券最小投资金额
    private String miaoshu = "";
    private String redLogId = "";
    private String flag="-1";
    private boolean best = false;

    //    private String flag;//是否成功查询到ProjectInfoModel

    private ProjectInfoModel projectInfoModels = null;
    private BestProductModel bestProductModels = null;


    private ClickListener clickListener = new ClickListener() {
        @Override
        public void onClick(Object... objects) {
            //兑换
            allCoupon = (AllCouponModel) objects[0];
            initDuihuan(allCoupon);
        }
    };
    private ClickMore clickMore = new ClickMore() {
        @Override
        public void onClick(Object... objects) {
            all = (AllCouponModel) objects[0];
            ToastUtils.showTost(getActivity(), all.getRULE());
        }
    };
    private int i = 0;
    private LinearLayoutManager linearLayoutManager = new WrapContentLinearLayoutManager(getActivity());

    private void initDuihuan(final AllCouponModel allCoupon) {
        showLoadingDialog();
        BizDataAsyncTask<String> getDuixian = new BizDataAsyncTask<String>() {
            @Override
            protected String doExecute() throws ZYException, BizFailure {
                return AllCouponBiz.getCouponToCash(allCoupon.getRED_PACKET_TEMPLET_ID(), allCoupon.getRED_PACKET_LOG_ID());
            }

            @Override
            protected void onExecuteSucceeded(String s) {
                hideLoadingDialog();
                ToastUtils.showTost(getActivity(), "兑换成功");
                allCouponModel.clear();
                getRedBag(i);
            }

            @Override
            protected void OnExecuteFailed(String error) {
                hideLoadingDialog();
                ToastUtils.showTost(getActivity(), "兑换失败");
            }
        };
        getDuixian.execute();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_my_red_bag, container, false);
        ButterKnife.bind(this, view);
        //获取红包数据
        getRedBag(i);
        initData();
        return view;
    }

    private void getRedBag(final int i) {
        showLoadingDialog();
        BizDataAsyncTask<List<AllCouponAndInterestModle>> getCoupon = new BizDataAsyncTask<List<AllCouponAndInterestModle>>() {
            @Override
            protected List<AllCouponAndInterestModle> doExecute() throws ZYException, BizFailure {
                return AllCouponBiz.getCouponAndInterest("", String.valueOf(i), "200","");
            }

            @Override
            protected void onExecuteSucceeded(List<AllCouponAndInterestModle> allCouponModels) {
                hideLoadingDialog();
                allCouponModel.clear();
                srlRedBag.setRefreshing(false);
                initRedBag(allCouponModels);
            }

            @Override
            protected void OnExecuteFailed(String error) {
                hideLoadingDialog();
                srlRedBag.setRefreshing(false);
            }
        };
        getCoupon.execute();
    }

    private void initRedBag(List<AllCouponAndInterestModle> allCouponModels) {
        //        allCouponModel.clear();
        allCouponModel.addAll(allCouponModels);
        if (i == 0) {
            myRedBagAdapter = new NewMyRedBagAdapter(allCouponModel, getActivity(), clickListener, clickMore);
            //        rvMyRedbag.setLayoutManager(new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL));
            rvMyRedbag.setLayoutManager(linearLayoutManager);
            rvMyRedbag.setAdapter(myRedBagAdapter);
            myRedBagAdapter.notifyDataSetChanged();
        } else {
            myRedBagAdapter.addData(allCouponModels);
            myRedBagAdapter.notifyDataSetChanged();
        }

    }

    private void initData() {

        //红包卡券点击事件
        rvMyRedbag.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {


                msg = "即将跳转 投资项目列表 页面";
                showDialog(msg);

            }
        });


        srlRedBag.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                i = 0;
                getRedBag(i);
            }
        });
        srlRedBag.setColorSchemeResources(R.color.main);

    }

    private void showDialog(String msg) {
        // 如果最优标的存在则获得当前标的 Modle   否则提示当前红包无满足使用条件的标的
        //        getSbInfo();
        final Dialog dialog = new Dialog(getActivity(), R.style.My_Dialog);
        dialog.setContentView(R.layout.dialog_auth_name);
        dialog.setCancelable(false);
        TextView tv_msg = (TextView) dialog.findViewById(R.id.tv_msg);
        tv_msg.setText(msg);
        TextView tv_ok = (TextView) dialog.findViewById(R.id.tv_ok);
        tv_ok.setText("前往投资");
        dialog.findViewById(R.id.tv_cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        dialog.findViewById(R.id.tv_ok).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //直接跳转到我要投资列表（2017.2.1日修改  之前为下方逻辑）
                DsjrConfig.WHERE=3;
                Intent intent=new Intent(getActivity(), MainActivity.class);
                startActivity(intent);

                dialog.dismiss();
            }
        });
        dialog.show();
        WindowManager m = (WindowManager) getActivity().getSystemService(Context.WINDOW_SERVICE);
        Display d = m.getDefaultDisplay();  //为获取屏幕宽、高
        android.view.WindowManager.LayoutParams p = dialog.getWindow().getAttributes();  //获取对话框当前的参数值
        p.height = (int) (d.getHeight() * 0.3);   //高度设置为屏幕的0.3
        p.width = (int) (d.getWidth() * 0.8);    //宽度设置为屏幕的0.5
        dialog.setCanceledOnTouchOutside(false);// 设置点击屏幕Dialog不消失
        dialog.getWindow().setAttributes(p);     //设置生效

    }



}
