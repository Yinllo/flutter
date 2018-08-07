package com.desjf.dsjr.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.desjf.dsjr.R;
import com.desjf.dsjr.base.BaseActivity;
import com.desjf.dsjr.base.BaseConfig;
import com.desjf.dsjr.base.BaseFragment;
import com.desjf.dsjr.ui.fragment.CodeLoginFragment;
import com.desjf.dsjr.ui.fragment.PwdLoginFragment;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author YinL
 * @Describe
 */

public class LoginActivity extends BaseActivity {

   @BindView(R.id.radioGroup)
    RadioGroup radioGroup;
    @BindView(R.id.rb_pwd_login)
    RadioButton pwdLogin;
    @BindView(R.id.iv_one)
    ImageView ivPwd;
    @BindView(R.id.view_pwd)
    View viewPwd;
    @BindView(R.id.rb_code_login)
    RadioButton codeLogin;
    @BindView(R.id.iv_two)
    ImageView ivCode;
    @BindView(R.id.view_code)
    View viewCode;

    ArrayList<BaseFragment> list=null;
    // 定义FragmentManager对象管理器
    android.support.v4.app.FragmentManager fragmentManager;
    PwdLoginFragment pwdLoginFragment;
    CodeLoginFragment codeLoginFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        initData();

    }

    //初始化三个Fragment  懒加载方式
    private void setFragmentSelect(int index){
        FragmentTransaction fragmentTransaction= fragmentManager.beginTransaction();
        //首先将所有的Fragment隐藏
        hideFragments(fragmentTransaction);
        switch(index){
            case 0:
                if(null==pwdLoginFragment){
                    pwdLoginFragment=new PwdLoginFragment();
                    fragmentTransaction.add(R.id.login_FrameLayout,pwdLoginFragment);
                }else{
                    fragmentTransaction.show(pwdLoginFragment);
                }
                break;
            case 1:
                if(null==codeLoginFragment){
                    codeLoginFragment=new CodeLoginFragment();
                    fragmentTransaction.add(R.id.login_FrameLayout,codeLoginFragment);
                }else{
                    fragmentTransaction.show(codeLoginFragment);
                }
                break;
        }
        //提交
        fragmentTransaction.commitAllowingStateLoss();
    }

    //将已经实例化的对象隐藏
    private void hideFragments(FragmentTransaction fragmentTransaction){
        if(pwdLoginFragment!=null){
            fragmentTransaction.hide(pwdLoginFragment);
        }
        if(codeLoginFragment!=null){
            fragmentTransaction.hide(codeLoginFragment);
        }
    }

    private void initData(){
        //设置默认选中项
        radioGroup.check(R.id.rb_pwd_login);
        //实例化
        fragmentManager=getSupportFragmentManager();
        setFragmentSelect(0);

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                 switch(checkedId){
                     case R.id.rb_pwd_login:
                         setFragmentSelect(0);
                         //改变下划线和指示器显示
                         ivCode.setImageResource(R.mipmap.white_triangleindicator);
                         viewCode.setBackgroundColor(getResources().getColor(R.color.view_bg));

                         ivPwd.setImageResource(R.mipmap.triangleindicator);
                         viewPwd.setBackgroundColor(getResources().getColor(R.color.number));

                         pwdLogin.setTextColor(getResources().getColor(R.color.login_text));
                         codeLogin.setTextColor(getResources().getColor(R.color.nfont));

                     break;
                     case R.id.rb_code_login:
                         setFragmentSelect(1);
                         //改变下划线和指示器显示
                         ivPwd.setImageResource(R.mipmap.white_triangleindicator);
                         viewPwd.setBackgroundColor(getResources().getColor(R.color.view_bg));

                         ivCode.setImageResource(R.mipmap.triangleindicator);
                         viewCode.setBackgroundColor(getResources().getColor(R.color.number));

                         codeLogin.setTextColor(getResources().getColor(R.color.login_text));
                         pwdLogin.setTextColor(getResources().getColor(R.color.nfont));

                         break;
                 }
            }
        });

    }

    @OnClick({R.id.rv_title})
    public void onClick(View view){
         switch(view.getId()){
             case R.id.rv_title:
                 //返回首页
                 Intent toHome=new Intent(this,MainActivity.class);
                 BaseConfig.WHERE=2;
                 startActivity(toHome);
                 break;
         }
    }

}
