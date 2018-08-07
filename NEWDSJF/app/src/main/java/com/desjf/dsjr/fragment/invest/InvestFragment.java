package com.desjf.dsjr.fragment.invest;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.desjf.dsjr.R;
import com.desjf.dsjr.base.BaseFragment;
import com.desjf.dsjr.widget.ChooseListener;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class InvestFragment extends BaseFragment {
    @BindView(R.id.rl_wytz)
    RelativeLayout rlWytz;
    @BindView(R.id.ll_invest_choose)//筛选按钮
    LinearLayout llInvestChoose;
    @BindView(R.id.iv_invest_choose)
    ImageView ivInvestChoose;
    @BindView(R.id.tv_invest_choose)
    TextView tvInvestChoose;
//    @BindView(R.id.sb)
//    View sb;
//    @BindView(R.id.zz)
//    View zz;
    private List<BaseFragment> list_fragment;
    private Context context = getActivity();
//    @BindView(R.id.rg_invest)
//    RadioGroup rgInvest;
    @BindView(R.id.vp_invest)
    ViewPager vpInvest;
    private PopupWindow mPopupWindow;
    private boolean isOpen = true;
    private String tzqx = "";//投资期限
    private String hkfs = "";//还款方式
    private StandardpowderFragment standardpowderFragment;
    private AssignmentFragment assignmentFragment;
    private ChooseListener chooseListener = new ChooseListener() {
        @Override
        public void Tzqx(String s) {

        }

        @Override
        public void Hkfs(String a) {

        }
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_invest, container, false);
        ButterKnife.bind(this, view);
        initData();
        initView();
        return view;
    }

    private void initView() {
        llInvestChoose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isOpen) {
                    showPopup();
                    ivInvestChoose.setVisibility(View.INVISIBLE);
                    tvInvestChoose.setText("取消");
                    isOpen = !isOpen;
                } else {
                    mPopupWindow.dismiss();
                    ivInvestChoose.setVisibility(View.VISIBLE);
                    tvInvestChoose.setText("筛选");
                    isOpen = !isOpen;
                }

//                Intent intent = new Intent(getActivity(), InvestChooseActivity.class);
//                startActivity(intent);
            }
        });
    }

    //筛选框
    private void showPopup() {
        View contentView = LayoutInflater.from(getActivity()).inflate(R.layout.item_popup_window, null);
        mPopupWindow = new PopupWindow(getActivity());
        mPopupWindow.setContentView(contentView);
        mPopupWindow.setBackgroundDrawable(null);
        mPopupWindow.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        mPopupWindow.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        mPopupWindow.showAsDropDown(rlWytz);


        //处理投资期限逻辑
        final TextView under_ss = (TextView) contentView.findViewById(R.id.tv_tzqx_sstyx);
        final TextView tzqx_bx = (TextView) contentView.findViewById(R.id.tv_tzqx_bx);
        final TextView between_ss = (TextView) contentView.findViewById(R.id.tv_tzqx_ssjst);
        final TextView above_js = (TextView) contentView.findViewById(R.id.tv_tzqx_jstys);
        if (tzqx.equals("0")){
            under_ss.setTextColor(getResources().getColor(R.color.white));
            under_ss.setBackgroundResource(R.drawable.text_pop_blue);
            under_ss.setTextColor(getResources().getColor(R.color.white));
            under_ss.setBackgroundResource(R.drawable.text_pop_blue);
            tzqx_bx.setTextColor(getResources().getColor(R.color.popfont));
            tzqx_bx.setBackgroundResource(R.drawable.text_pop_huibai);
            between_ss.setTextColor(getResources().getColor(R.color.popfont));
            between_ss.setBackgroundResource(R.drawable.text_pop_huibai);
            above_js.setTextColor(getResources().getColor(R.color.popfont));
            above_js.setBackgroundResource(R.drawable.text_pop_huibai);
        }else if (tzqx.equals("")){
            tzqx_bx.setTextColor(getResources().getColor(R.color.white));
            tzqx_bx.setBackgroundResource(R.drawable.text_pop_blue);
            under_ss.setTextColor(getResources().getColor(R.color.popfont));
            under_ss.setBackgroundResource(R.drawable.text_pop_huibai);
            between_ss.setTextColor(getResources().getColor(R.color.popfont));
            between_ss.setBackgroundResource(R.drawable.text_pop_huibai);
            above_js.setTextColor(getResources().getColor(R.color.popfont));
            above_js.setBackgroundResource(R.drawable.text_pop_huibai);
        }else if (tzqx.equals("1")){
            between_ss.setTextColor(getResources().getColor(R.color.white));
            between_ss.setBackgroundResource(R.drawable.text_pop_blue);
            tzqx_bx.setTextColor(getResources().getColor(R.color.popfont));
            tzqx_bx.setBackgroundResource(R.drawable.text_pop_huibai);
            under_ss.setTextColor(getResources().getColor(R.color.popfont));
            under_ss.setBackgroundResource(R.drawable.text_pop_huibai);
            above_js.setTextColor(getResources().getColor(R.color.popfont));
            above_js.setBackgroundResource(R.drawable.text_pop_huibai);
        }else if (tzqx.equals("2")){
            above_js.setTextColor(getResources().getColor(R.color.white));
            above_js.setBackgroundResource(R.drawable.text_pop_blue);
            between_ss.setTextColor(getResources().getColor(R.color.popfont));
            between_ss.setBackgroundResource(R.drawable.text_pop_huibai);
            tzqx_bx.setTextColor(getResources().getColor(R.color.popfont));
            tzqx_bx.setBackgroundResource(R.drawable.text_pop_huibai);
            under_ss.setTextColor(getResources().getColor(R.color.popfont));
            under_ss.setBackgroundResource(R.drawable.text_pop_huibai);
        }
        //30填一下
        under_ss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tzqx = "0";
                under_ss.setTextColor(getResources().getColor(R.color.white));
                under_ss.setBackgroundResource(R.drawable.text_pop_blue);
                tzqx_bx.setTextColor(getResources().getColor(R.color.popfont));
                tzqx_bx.setBackgroundResource(R.drawable.text_pop_huibai);
                between_ss.setTextColor(getResources().getColor(R.color.popfont));
                between_ss.setBackgroundResource(R.drawable.text_pop_huibai);
                above_js.setTextColor(getResources().getColor(R.color.popfont));
                above_js.setBackgroundResource(R.drawable.text_pop_huibai);
            }
        });

        tzqx_bx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tzqx = "";
                tzqx_bx.setTextColor(getResources().getColor(R.color.white));
                tzqx_bx.setBackgroundResource(R.drawable.text_pop_blue);
                under_ss.setTextColor(getResources().getColor(R.color.popfont));
                under_ss.setBackgroundResource(R.drawable.text_pop_huibai);
                between_ss.setTextColor(getResources().getColor(R.color.popfont));
                between_ss.setBackgroundResource(R.drawable.text_pop_huibai);
                above_js.setTextColor(getResources().getColor(R.color.popfont));
                above_js.setBackgroundResource(R.drawable.text_pop_huibai);
            }
        });

        between_ss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tzqx = "1";
                between_ss.setTextColor(getResources().getColor(R.color.white));
                between_ss.setBackgroundResource(R.drawable.text_pop_blue);
                tzqx_bx.setTextColor(getResources().getColor(R.color.popfont));
                tzqx_bx.setBackgroundResource(R.drawable.text_pop_huibai);
                under_ss.setTextColor(getResources().getColor(R.color.popfont));
                under_ss.setBackgroundResource(R.drawable.text_pop_huibai);
                above_js.setTextColor(getResources().getColor(R.color.popfont));
                above_js.setBackgroundResource(R.drawable.text_pop_huibai);
            }
        });

        above_js.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tzqx = "2";
                above_js.setTextColor(getResources().getColor(R.color.white));
                above_js.setBackgroundResource(R.drawable.text_pop_blue);
                between_ss.setTextColor(getResources().getColor(R.color.popfont));
                between_ss.setBackgroundResource(R.drawable.text_pop_huibai);
                tzqx_bx.setTextColor(getResources().getColor(R.color.popfont));
                tzqx_bx.setBackgroundResource(R.drawable.text_pop_huibai);
                under_ss.setTextColor(getResources().getColor(R.color.popfont));
                under_ss.setBackgroundResource(R.drawable.text_pop_huibai);
            }
        });

        //处理还款方式逻辑
        final TextView hkfs_bx = (TextView) contentView.findViewById(R.id.tv_hkfs_bx);
        final TextView hkfs_debj = (TextView) contentView.findViewById(R.id.tv_hkfs_debj);
        final TextView hkfs_debx = (TextView) contentView.findViewById(R.id.tv_hkfs_debx);
        final TextView hkfs_dqhbfx = (TextView) contentView.findViewById(R.id.tv_hkfs_dqhbfx);
        final TextView hkfs_dyfx = (TextView) contentView.findViewById(R.id.tv_hkfs_dyfx);
        final TextView hkfs_ajfx = (TextView) contentView.findViewById(R.id.tv_hkfs_ajfx);
        if (hkfs.equals("")){
            hkfs_bx.setTextColor(getResources().getColor(R.color.white));
            hkfs_bx.setBackgroundResource(R.drawable.text_pop_blue);
            hkfs_debj.setTextColor(getResources().getColor(R.color.popfont));
            hkfs_debj.setBackgroundResource(R.drawable.text_pop_huibai);
            hkfs_debx.setTextColor(getResources().getColor(R.color.popfont));
            hkfs_debx.setBackgroundResource(R.drawable.text_pop_huibai);
            hkfs_dqhbfx.setTextColor(getResources().getColor(R.color.popfont));
            hkfs_dqhbfx.setBackgroundResource(R.drawable.text_pop_huibai);
            hkfs_dyfx.setTextColor(getResources().getColor(R.color.popfont));
            hkfs_dyfx.setBackgroundResource(R.drawable.text_pop_huibai);
            hkfs_ajfx.setTextColor(getResources().getColor(R.color.popfont));
            hkfs_ajfx.setBackgroundResource(R.drawable.text_pop_huibai);
        }else if (hkfs.equals("1")){
            hkfs_debj.setTextColor(getResources().getColor(R.color.white));
            hkfs_debj.setBackgroundResource(R.drawable.text_pop_blue);
            hkfs_bx.setTextColor(getResources().getColor(R.color.popfont));
            hkfs_bx.setBackgroundResource(R.drawable.text_pop_huibai);
            hkfs_debx.setTextColor(getResources().getColor(R.color.popfont));
            hkfs_debx.setBackgroundResource(R.drawable.text_pop_huibai);
            hkfs_dqhbfx.setTextColor(getResources().getColor(R.color.popfont));
            hkfs_dqhbfx.setBackgroundResource(R.drawable.text_pop_huibai);
            hkfs_dyfx.setTextColor(getResources().getColor(R.color.popfont));
            hkfs_dyfx.setBackgroundResource(R.drawable.text_pop_huibai);
            hkfs_ajfx.setTextColor(getResources().getColor(R.color.popfont));
            hkfs_ajfx.setBackgroundResource(R.drawable.text_pop_huibai);
        }else if (hkfs.equals("2")){
            hkfs_debx.setTextColor(getResources().getColor(R.color.white));
            hkfs_debx.setBackgroundResource(R.drawable.text_pop_blue);
            hkfs_bx.setTextColor(getResources().getColor(R.color.popfont));
            hkfs_bx.setBackgroundResource(R.drawable.text_pop_huibai);
            hkfs_debj.setTextColor(getResources().getColor(R.color.popfont));
            hkfs_debj.setBackgroundResource(R.drawable.text_pop_huibai);
            hkfs_dqhbfx.setTextColor(getResources().getColor(R.color.popfont));
            hkfs_dqhbfx.setBackgroundResource(R.drawable.text_pop_huibai);
            hkfs_dyfx.setTextColor(getResources().getColor(R.color.popfont));
            hkfs_dyfx.setBackgroundResource(R.drawable.text_pop_huibai);
            hkfs_ajfx.setTextColor(getResources().getColor(R.color.popfont));
            hkfs_ajfx.setBackgroundResource(R.drawable.text_pop_huibai);
        }else if (hkfs.equals("3")){
            hkfs_dqhbfx.setTextColor(getResources().getColor(R.color.white));
            hkfs_dqhbfx.setBackgroundResource(R.drawable.text_pop_blue);
            hkfs_debx.setTextColor(getResources().getColor(R.color.popfont));
            hkfs_debx.setBackgroundResource(R.drawable.text_pop_huibai);
            hkfs_bx.setTextColor(getResources().getColor(R.color.popfont));
            hkfs_bx.setBackgroundResource(R.drawable.text_pop_huibai);
            hkfs_debj.setTextColor(getResources().getColor(R.color.popfont));
            hkfs_debj.setBackgroundResource(R.drawable.text_pop_huibai);
            hkfs_dyfx.setTextColor(getResources().getColor(R.color.popfont));
            hkfs_dyfx.setBackgroundResource(R.drawable.text_pop_huibai);
            hkfs_ajfx.setTextColor(getResources().getColor(R.color.popfont));
            hkfs_ajfx.setBackgroundResource(R.drawable.text_pop_huibai);
        }else if (hkfs.equals("4")){
            hkfs_dyfx.setTextColor(getResources().getColor(R.color.white));
            hkfs_dyfx.setBackgroundResource(R.drawable.text_pop_blue);
            hkfs_dqhbfx.setTextColor(getResources().getColor(R.color.popfont));
            hkfs_dqhbfx.setBackgroundResource(R.drawable.text_pop_huibai);
            hkfs_debx.setTextColor(getResources().getColor(R.color.popfont));
            hkfs_debx.setBackgroundResource(R.drawable.text_pop_huibai);
            hkfs_bx.setTextColor(getResources().getColor(R.color.popfont));
            hkfs_bx.setBackgroundResource(R.drawable.text_pop_huibai);
            hkfs_debj.setTextColor(getResources().getColor(R.color.popfont));
            hkfs_debj.setBackgroundResource(R.drawable.text_pop_huibai);
            hkfs_ajfx.setTextColor(getResources().getColor(R.color.popfont));
            hkfs_ajfx.setBackgroundResource(R.drawable.text_pop_huibai);
        }else if (hkfs.equals("5")){
            hkfs_ajfx.setTextColor(getResources().getColor(R.color.white));
            hkfs_ajfx.setBackgroundResource(R.drawable.text_pop_blue);
            hkfs_dyfx.setTextColor(getResources().getColor(R.color.popfont));
            hkfs_dyfx.setBackgroundResource(R.drawable.text_pop_huibai);
            hkfs_dqhbfx.setTextColor(getResources().getColor(R.color.popfont));
            hkfs_dqhbfx.setBackgroundResource(R.drawable.text_pop_huibai);
            hkfs_debx.setTextColor(getResources().getColor(R.color.popfont));
            hkfs_debx.setBackgroundResource(R.drawable.text_pop_huibai);
            hkfs_bx.setTextColor(getResources().getColor(R.color.popfont));
            hkfs_bx.setBackgroundResource(R.drawable.text_pop_huibai);
            hkfs_debj.setTextColor(getResources().getColor(R.color.popfont));
            hkfs_debj.setBackgroundResource(R.drawable.text_pop_huibai);
        }
        hkfs_bx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hkfs = "";
                hkfs_bx.setTextColor(getResources().getColor(R.color.white));
                hkfs_bx.setBackgroundResource(R.drawable.text_pop_blue);
                hkfs_debj.setTextColor(getResources().getColor(R.color.popfont));
                hkfs_debj.setBackgroundResource(R.drawable.text_pop_huibai);
                hkfs_debx.setTextColor(getResources().getColor(R.color.popfont));
                hkfs_debx.setBackgroundResource(R.drawable.text_pop_huibai);
                hkfs_dqhbfx.setTextColor(getResources().getColor(R.color.popfont));
                hkfs_dqhbfx.setBackgroundResource(R.drawable.text_pop_huibai);
                hkfs_dyfx.setTextColor(getResources().getColor(R.color.popfont));
                hkfs_dyfx.setBackgroundResource(R.drawable.text_pop_huibai);
                hkfs_ajfx.setTextColor(getResources().getColor(R.color.popfont));
                hkfs_ajfx.setBackgroundResource(R.drawable.text_pop_huibai);
            }
        });
        //等额本金
        hkfs_debj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hkfs = "1";
                hkfs_debj.setTextColor(getResources().getColor(R.color.white));
                hkfs_debj.setBackgroundResource(R.drawable.text_pop_blue);
                hkfs_bx.setTextColor(getResources().getColor(R.color.popfont));
                hkfs_bx.setBackgroundResource(R.drawable.text_pop_huibai);
                hkfs_debx.setTextColor(getResources().getColor(R.color.popfont));
                hkfs_debx.setBackgroundResource(R.drawable.text_pop_huibai);
                hkfs_dqhbfx.setTextColor(getResources().getColor(R.color.popfont));
                hkfs_dqhbfx.setBackgroundResource(R.drawable.text_pop_huibai);
                hkfs_dyfx.setTextColor(getResources().getColor(R.color.popfont));
                hkfs_dyfx.setBackgroundResource(R.drawable.text_pop_huibai);
                hkfs_ajfx.setTextColor(getResources().getColor(R.color.popfont));
                hkfs_ajfx.setBackgroundResource(R.drawable.text_pop_huibai);
            }
        });
        //等额本息
        hkfs_debx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hkfs = "2";
                hkfs_debx.setTextColor(getResources().getColor(R.color.white));
                hkfs_debx.setBackgroundResource(R.drawable.text_pop_blue);
                hkfs_bx.setTextColor(getResources().getColor(R.color.popfont));
                hkfs_bx.setBackgroundResource(R.drawable.text_pop_huibai);
                hkfs_debj.setTextColor(getResources().getColor(R.color.popfont));
                hkfs_debj.setBackgroundResource(R.drawable.text_pop_huibai);
                hkfs_dqhbfx.setTextColor(getResources().getColor(R.color.popfont));
                hkfs_dqhbfx.setBackgroundResource(R.drawable.text_pop_huibai);
                hkfs_dyfx.setTextColor(getResources().getColor(R.color.popfont));
                hkfs_dyfx.setBackgroundResource(R.drawable.text_pop_huibai);
                hkfs_ajfx.setTextColor(getResources().getColor(R.color.popfont));
                hkfs_ajfx.setBackgroundResource(R.drawable.text_pop_huibai);
            }
        });
        //到期还本
        hkfs_dqhbfx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hkfs = "3";
                hkfs_dqhbfx.setTextColor(getResources().getColor(R.color.white));
                hkfs_dqhbfx.setBackgroundResource(R.drawable.text_pop_blue);
                hkfs_debx.setTextColor(getResources().getColor(R.color.popfont));
                hkfs_debx.setBackgroundResource(R.drawable.text_pop_huibai);
                hkfs_bx.setTextColor(getResources().getColor(R.color.popfont));
                hkfs_bx.setBackgroundResource(R.drawable.text_pop_huibai);
                hkfs_debj.setTextColor(getResources().getColor(R.color.popfont));
                hkfs_debj.setBackgroundResource(R.drawable.text_pop_huibai);
                hkfs_dyfx.setTextColor(getResources().getColor(R.color.popfont));
                hkfs_dyfx.setBackgroundResource(R.drawable.text_pop_huibai);
                hkfs_ajfx.setTextColor(getResources().getColor(R.color.popfont));
                hkfs_ajfx.setBackgroundResource(R.drawable.text_pop_huibai);
            }
        });
        //到月付息
        hkfs_dyfx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hkfs = "4";
                hkfs_dyfx.setTextColor(getResources().getColor(R.color.white));
                hkfs_dyfx.setBackgroundResource(R.drawable.text_pop_blue);
                hkfs_dqhbfx.setTextColor(getResources().getColor(R.color.popfont));
                hkfs_dqhbfx.setBackgroundResource(R.drawable.text_pop_huibai);
                hkfs_debx.setTextColor(getResources().getColor(R.color.popfont));
                hkfs_debx.setBackgroundResource(R.drawable.text_pop_huibai);
                hkfs_bx.setTextColor(getResources().getColor(R.color.popfont));
                hkfs_bx.setBackgroundResource(R.drawable.text_pop_huibai);
                hkfs_debj.setTextColor(getResources().getColor(R.color.popfont));
                hkfs_debj.setBackgroundResource(R.drawable.text_pop_huibai);
                hkfs_ajfx.setTextColor(getResources().getColor(R.color.popfont));
                hkfs_ajfx.setBackgroundResource(R.drawable.text_pop_huibai);
            }
        });
        //按季付息
        hkfs_ajfx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hkfs = "5";
                hkfs_ajfx.setTextColor(getResources().getColor(R.color.white));
                hkfs_ajfx.setBackgroundResource(R.drawable.text_pop_blue);
                hkfs_dyfx.setTextColor(getResources().getColor(R.color.popfont));
                hkfs_dyfx.setBackgroundResource(R.drawable.text_pop_huibai);
                hkfs_dqhbfx.setTextColor(getResources().getColor(R.color.popfont));
                hkfs_dqhbfx.setBackgroundResource(R.drawable.text_pop_huibai);
                hkfs_debx.setTextColor(getResources().getColor(R.color.popfont));
                hkfs_debx.setBackgroundResource(R.drawable.text_pop_huibai);
                hkfs_bx.setTextColor(getResources().getColor(R.color.popfont));
                hkfs_bx.setBackgroundResource(R.drawable.text_pop_huibai);
                hkfs_debj.setTextColor(getResources().getColor(R.color.popfont));
                hkfs_debj.setBackgroundResource(R.drawable.text_pop_huibai);

            }
        });
        final TextView hfmr = (TextView) contentView.findViewById(R.id.tv_hfmrpx);
        final TextView qr = (TextView) contentView.findViewById(R.id.tv_queren);
        //恢复默认排序
        hfmr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tzqx = "";
                hkfs = "";
                tzqx_bx.setTextColor(getResources().getColor(R.color.white));
                tzqx_bx.setBackgroundResource(R.drawable.text_pop_blue);
                under_ss.setTextColor(getResources().getColor(R.color.popfont));
                under_ss.setBackgroundResource(R.drawable.text_pop_huibai);
                between_ss.setTextColor(getResources().getColor(R.color.popfont));
                between_ss.setBackgroundResource(R.drawable.text_pop_huibai);
                above_js.setTextColor(getResources().getColor(R.color.popfont));
                above_js.setBackgroundResource(R.drawable.text_pop_huibai);
                hkfs_bx.setTextColor(getResources().getColor(R.color.white));
                hkfs_bx.setBackgroundResource(R.drawable.text_pop_blue);
                hkfs_debj.setTextColor(getResources().getColor(R.color.popfont));
                hkfs_debj.setBackgroundResource(R.drawable.text_pop_huibai);
                hkfs_debx.setTextColor(getResources().getColor(R.color.popfont));
                hkfs_debx.setBackgroundResource(R.drawable.text_pop_huibai);
                hkfs_dqhbfx.setTextColor(getResources().getColor(R.color.popfont));
                hkfs_dqhbfx.setBackgroundResource(R.drawable.text_pop_huibai);
                hkfs_dyfx.setTextColor(getResources().getColor(R.color.popfont));
                hkfs_dyfx.setBackgroundResource(R.drawable.text_pop_huibai);
                hkfs_ajfx.setTextColor(getResources().getColor(R.color.popfont));
                hkfs_ajfx.setBackgroundResource(R.drawable.text_pop_huibai);

            }
        });
        //确认
        qr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                standardpowderFragment.setTzqx(tzqx);
                standardpowderFragment.setHkfs(hkfs);
                standardpowderFragment.getList().clear();
                standardpowderFragment.getSanB();
//                standardpowderFragment.getStandarAdapter().notifyDataSetChanged();
                ivInvestChoose.setVisibility(View.VISIBLE);
                tvInvestChoose.setText("筛选");
                isOpen = !isOpen;
                mPopupWindow.dismiss();
            }
        });


    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    private void initData() {
        //债转Fragment
//        assignmentFragment = new AssignmentFragment();
        //散标Fragment
        standardpowderFragment = new StandardpowderFragment();
        //ViewPager设置
        vpInvest.setAdapter(new FragmentPagerAdapter(getChildFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                Fragment fragment = null;
                switch (position) {
                    case 0:
                        fragment = standardpowderFragment;
//                        sb.setBackgroundColor(getResources().getColor(R.color.nmainOrign));
//                        zz.setBackgroundColor(getResources().getColor(R.color.huibai));
                        break;
//                    case 1:
//                        fragment = assignmentFragment;
//                        zz.setBackgroundColor(getResources().getColor(R.color.nmainOrign));
//                        sb.setBackgroundColor(getResources().getColor(R.color.huibai));
//                        break;
                }
                return fragment;
            }

            @Override
            public int getCount() {
                return 1;
            }
        });


    }
    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (hidden){
            return;
        }else{

        }
    }

}
