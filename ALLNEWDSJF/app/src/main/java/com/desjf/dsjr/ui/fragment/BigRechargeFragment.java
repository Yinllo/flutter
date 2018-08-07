package com.desjf.dsjr.ui.fragment;

import android.content.ClipboardManager;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.TextView;

import com.desjf.dsjr.R;
import com.desjf.dsjr.base.BaseFragment;
import com.desjf.dsjr.utils.ToastUtils;

import butterknife.BindView;
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
    protected int getLayoutId() {
        return R.layout.fragment_big_recharge;
    }

    @Override
    protected void init(Bundle savedInstanceState) {

    }


    private void initView(){
        //初始化文字颜色
        String  word="1、使用您已绑定存管账户的银行卡给德晟金服渤海银行账号进行转账充值，一般2小时内即可到账；支持网银转账、手机银行转账、柜台转账；（不支持ATM、支付宝、微信转账）";
        SpannableString spannableString = new SpannableString(word);
        ForegroundColorSpan colorSpan = new ForegroundColorSpan(Color.parseColor("#ff6200"));
//        spannableString.setSpan(new TypefaceSpan("DINBEK"), 0, 2, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        spannableString.setSpan(colorSpan, 64, spannableString.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
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
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onDetach() {
        super.onDetach();

    }
}
