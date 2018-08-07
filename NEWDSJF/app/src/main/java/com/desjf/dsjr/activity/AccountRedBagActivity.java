package com.desjf.dsjr.activity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.desjf.dsjr.R;
import com.desjf.dsjr.base.BaseActivity;
import com.desjf.dsjr.base.BaseFragment;
import com.desjf.dsjr.fragment.MyRedBagFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AccountRedBagActivity extends BaseActivity {
    @BindView(R.id.iv_my_redbag_back)
    ImageView ivMyRedbagBack;
    private List<BaseFragment> list_fragment;
//    @BindView(R.id.rg_redBag)
//    RadioGroup rgRedBag;
    @BindView(R.id.fl_redBag)
    FrameLayout flRedBag;
    private Context context = this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_red_bag);
        ButterKnife.bind(this);
        initData();
//        initView();
    }

//    private void initView() {
//        rgRedBag.check(R.id.btn_my_redBag);//默认选择项
//        rgRedBag.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(RadioGroup radioGroup, int i) {
//                switch (i) {
//                    case R.id.btn_my_redBag:
//                        getSupportFragmentManager().beginTransaction()
//                                .show(list_fragment.get(0))
//                                .hide(list_fragment.get(1))
//                                .commit();
//                        break;
//                    case R.id.btn_my_couPon:
//                        getSupportFragmentManager().beginTransaction()
//                                .show(list_fragment.get(1))
//                                .hide(list_fragment.get(0))
//                                .commit();
//                        break;
//                }
//            }
//        });
//    }

    private void initData() {
        list_fragment = new ArrayList<>();
        list_fragment.add(new MyRedBagFragment());
//        list_fragment.add(new MyCouponFragment());
        for (int i = 0; i < list_fragment.size(); i++) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fl_redBag, list_fragment.get(i), "BaseFragment" + i)
                    .show(list_fragment.get(0))
//                    .hide(list_fragment.get(i))
                    .commit();
        }
        ivMyRedbagBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
