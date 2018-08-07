package com.example.yinl.mvvmdemo.view;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

import com.example.yinl.mvvmdemo.R;
import com.example.yinl.mvvmdemo.databinding.ActivityMainBinding;
import com.example.yinl.mvvmdemo.viewMedel.MainViewModel;

/**
  * @author yinl
  * @creat  time 2018/8/2 14:36
  * @explain dataBinding + mvvm
  *
 **/
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityMainBinding binding= DataBindingUtil.setContentView(this,R.layout.activity_main);

        MainViewModel mainViewModel=new MainViewModel(this);

        binding.setMainViewModel(mainViewModel);

    }
}
