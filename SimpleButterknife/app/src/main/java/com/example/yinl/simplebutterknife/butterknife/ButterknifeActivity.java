package com.example.yinl.simplebutterknife.butterknife;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.example.yinl.simplebutterknife.R;

public class ButterknifeActivity extends AppCompatActivity {


    //使用自定义注解
    @BindView(R.id.tv1)
    TextView textView1;
    @BindView(R.id.tv2)
    TextView textView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_butterknife);

        Butterknife.bind(this);

        Toast.makeText(this,textView1.getText().toString(),Toast.LENGTH_SHORT).show();

    }

    /**
     * 自定义 点击事件注解
     */

    @MyClick(R.id.button)
    public void onClick(){
        Log.e("aaa","onClick");
        Toast.makeText(ButterknifeActivity.this,"点击了按钮",Toast.LENGTH_SHORT).show();
    }

}
