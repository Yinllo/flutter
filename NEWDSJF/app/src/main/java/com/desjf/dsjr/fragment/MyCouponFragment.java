package com.desjf.dsjr.fragment;


import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.desjf.dsjr.R;
import com.desjf.dsjr.adapter.CouponAdapter;
import com.desjf.dsjr.base.BaseFragment;
import com.desjf.dsjr.biz.AllCouponBiz;
import com.desjf.dsjr.biz.exception.BizFailure;
import com.desjf.dsjr.biz.exception.ZYException;
import com.desjf.dsjr.biz.task.BizDataAsyncTask;
import com.desjf.dsjr.model.AllInterestModel;
import com.desjf.dsjr.model.SongNameModel;
import com.desjf.dsjr.utils.ToastUtils;
import com.desjf.dsjr.widget.ClickListener;
import com.desjf.dsjr.widget.WrapContentLinearLayoutManager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 * 红包加息券
 */
public class MyCouponFragment extends BaseFragment {
    @BindView(R.id.srl_coupon)
    SwipeRefreshLayout srlCoupon;
    @BindView(R.id.et_coupon_duihuan)
    EditText etCouponDuihuan;
    @BindView(R.id.btn_coupon_duihuan)
    Button btnCouponDuihuan;
    private Context context = getActivity();
    @BindView(R.id.rv_my_coupon)
    RecyclerView rvMyCoupon;
    private CouponAdapter couponAdapter;
    private List<AllInterestModel> allInterest = new ArrayList<>();
    private int i = 0;
    private LinearLayoutManager linearLayoutManager  = new WrapContentLinearLayoutManager(getActivity());
    private int lastVisibleItem;
    private AllInterestModel allInterestModel;
    private ClickListener clicklistener = new ClickListener() {
        @Override
        public void onClick(Object... objects) {
            allInterestModel = (AllInterestModel) objects[0];
            initClic();
        }
    };

    private void initClic() {
        final Dialog dialog = new Dialog(getActivity(), R.style.My_Dialog);
        dialog.setContentView(R.layout.item_dialog_coupon);
        dialog.setCancelable(false);
        dialog.show();
        ImageView iv_finish = (ImageView) dialog.findViewById(R.id.iv_finish);
        final EditText et = (EditText) dialog.findViewById(R.id.et_dailog_coupon);
        TextView tv_queren = (TextView) dialog.findViewById(R.id.tv_dialog_coupon_queren);
        final TextView tv_name = (TextView) dialog.findViewById(R.id.tv_szrxm);
        final TextView tv_phone = (TextView) dialog.findViewById(R.id.tv_szrphone);
        final TextView tv_song = (TextView) dialog.findViewById(R.id.tv_queren);
        iv_finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        tv_queren.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (et.getText().toString().isEmpty()) {
                    ToastUtils.showTost(getActivity(), "请输入受赠人手机号");
                    return;
                }
                BizDataAsyncTask<SongNameModel> getInfo = new BizDataAsyncTask<SongNameModel>() {
                    @Override
                    protected SongNameModel doExecute() throws ZYException, BizFailure {
                        return AllCouponBiz.getMessage(et.getText().toString());
                    }

                    @Override
                    protected void onExecuteSucceeded(SongNameModel songNameModel) {
                        tv_name.setText("受赠人姓名:" + songNameModel.getUSER_NAME());
                        tv_phone.setText("受赠人手机号码:" + songNameModel.getMOBILE());
                        tv_song.setBackgroundResource(R.drawable.textview_cricle_btn);
                        tv_song.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                BizDataAsyncTask<String> getQR = new BizDataAsyncTask<String>() {
                                    @Override
                                    protected String doExecute() throws ZYException, BizFailure {
                                        return AllCouponBiz.getQueren(et.getText().toString(), allInterestModel.getRATECOUPONID());
                                    }

                                    @Override
                                    protected void onExecuteSucceeded(String s) {
                                        dialog.dismiss();
                                        ToastUtils.showTost(getActivity(), "赠送成功");
                                        getInterestRate();
                                    }

                                    @Override
                                    protected void OnExecuteFailed(String error) {
                                        ToastUtils.showTost(getActivity(), error);
                                    }
                                };
                                getQR.execute();
                            }
                        });
                    }

                    @Override
                    protected void OnExecuteFailed(String error) {
                        ToastUtils.showTost(getActivity(), error);
                        tv_song.setBackgroundResource(R.drawable.textview_cricle_gray);
                    }
                };
                getInfo.execute();
            }
        });


    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_my_coupon, container, false);
        ButterKnife.bind(this, view);
        //获取加息劵
        getInterestRate();
        initView();
        initDuihuan();
        return view;
    }

    private void initDuihuan() {
        etCouponDuihuan.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }
            @Override
            public void afterTextChanged(Editable editable) {
                if (!etCouponDuihuan.getText().toString().isEmpty()) {
                    btnCouponDuihuan.setBackgroundResource(R.mipmap.btn_main);
                    btnCouponDuihuan.setClickable(true);
                } else {
                    btnCouponDuihuan.setBackgroundResource(R.mipmap.btn_change);
                    btnCouponDuihuan.setClickable(false);
                }
            }
        });
        btnCouponDuihuan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showLoadingDialog();
                BizDataAsyncTask<String> getDuihuan = new BizDataAsyncTask<String>() {
                    @Override
                    protected String doExecute() throws ZYException, BizFailure {
                        return AllCouponBiz.getDuihuan(etCouponDuihuan.getText().toString());
                    }

                    @Override
                    protected void onExecuteSucceeded(String s) {
                        hideLoadingDialog();
                        ToastUtils.showTost(getActivity(), "兑换成功");

                        etCouponDuihuan.setText("");
                        getInterestRate();
                    }

                    @Override
                    protected void OnExecuteFailed(String error) {
                        hideLoadingDialog();
                        ToastUtils.showTost(getActivity(), error);
                    }
                };
                getDuihuan.execute();
            }
        });
    }

    private void initView() {
        srlCoupon.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                i = 0;
                getInterestRate();
            }
        });
        srlCoupon.setColorSchemeResources(R.color.main);
//        rvMyCoupon.setOnScrollListener(new RecyclerView.OnScrollListener() {
//            @Override
//            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
//                super.onScrollStateChanged(recyclerView, newState);
//                if (newState == RecyclerView.SCROLL_STATE_IDLE
//                        && lastVisibleItem + 1 == couponAdapter.getItemCount() && couponAdapter.getItemCount() >= 20) {
//                    i++;
//                    getInterestRate();
//                }
//            }
//
//            @Override
//            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
//                super.onScrolled(recyclerView, dx, dy);
//                lastVisibleItem = linearLayoutManager.findLastVisibleItemPosition();
//            }
//        });
    }

    private void getInterestRate() {
        showLoadingDialog();
        BizDataAsyncTask<List<AllInterestModel>> getInterest = new BizDataAsyncTask<List<AllInterestModel>>() {
            @Override
            protected List<AllInterestModel> doExecute() throws ZYException, BizFailure {
                return AllCouponBiz.getInterest(String.valueOf(i), "1200");
            }

            @Override
            protected void onExecuteSucceeded(List<AllInterestModel> allInterestModels) {
                hideLoadingDialog();
                srlCoupon.setRefreshing(false);
                allInterest.clear();
                initCoupon(allInterestModels);
            }

            @Override
            protected void OnExecuteFailed(String error) {
                hideLoadingDialog();
                srlCoupon.setRefreshing(false);
            }
        };
        getInterest.execute();

    }

    private void initCoupon(List<AllInterestModel> allInterestModels) {
//        allInterest.clear();

        allInterest.addAll(allInterestModels);
        if (i == 0) {
            couponAdapter = new CouponAdapter(allInterest, getActivity(), clicklistener);
            rvMyCoupon.setLayoutManager(linearLayoutManager);
            rvMyCoupon.setAdapter(couponAdapter);
            couponAdapter.notifyDataSetChanged();
        } else {
            couponAdapter.addData(allInterestModels);
            couponAdapter.notifyDataSetChanged();
        }


    }
}
