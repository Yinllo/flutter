package com.desjf.dsjr.fragment.bank;

import android.content.ClipboardManager;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.desjf.dsjr.R;
import com.desjf.dsjr.base.BaseFragment;
import com.desjf.dsjr.utils.ToastUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @Author YinL
 * @Date 2018/4/26
 * @Describe 大额充值Fragment
 */

public class BigRechargeFragment extends BaseFragment {
    @BindView(R.id.tv_one_word)
    TextView textView;
    @BindView(R.id.tv1)
    TextView tvBuzhou;


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_big_recharge, container, false);
        ButterKnife.bind(this, view);

        initView();

        return view;
    }

    private void initView(){
        //初始化文字颜色
        String  word="1、使用您在存管账户已绑定的银行卡进行转账充值，转至您在渤海银行的存管帐户，一般两小时内可到账，支持网银、手机银行、柜台转账；（不支持ATM，支付宝，微信）";
        SpannableString spannableString = new SpannableString(word);
        ForegroundColorSpan colorSpan = new ForegroundColorSpan(Color.parseColor("#ff6200"));
//        spannableString.setSpan(new TypefaceSpan("DINBEK"), 0, 2, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        spannableString.setSpan(colorSpan, 63, spannableString.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        textView.setText(spannableString);

    }

    @OnClick({R.id.tv_copy})
    public void OnClick(View view){
           switch(view.getId()){
               case R.id.tv_copy:
                   //自动复制
                   ClipboardManager copy = (ClipboardManager) getActivity()
                           .getSystemService(Context.CLIPBOARD_SERVICE);
                   copy.setText("99038000017444340");
                   ToastUtils.showShortTost(getActivity(),"复制成功");
                   break;
           }

    }


    @Override
    public void onDetach() {
        super.onDetach();

    }


}
