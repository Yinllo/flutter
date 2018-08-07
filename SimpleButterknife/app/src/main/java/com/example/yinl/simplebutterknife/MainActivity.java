package com.example.yinl.simplebutterknife;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.yinl.simplebutterknife.Annotation.AnnotationActivity;
import com.example.yinl.simplebutterknife.butterknife.ButterknifeActivity;
import com.example.yinl.simplebutterknife.reflection.ReflectionActivity;

public class MainActivity extends AppCompatActivity {

    Button button1,button2,button3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button1=this.findViewById(R.id.button1);
        button2=this.findViewById(R.id.button2);
        button3=this.findViewById(R.id.button3);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //反射
                Intent i=new Intent(MainActivity.this,ReflectionActivity.class);
                startActivity(i);
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //注解
                Intent i=new Intent(MainActivity.this,AnnotationActivity.class);
                startActivity(i);
            }
        });
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //butterknife
                Intent i=new Intent(MainActivity.this,ButterknifeActivity.class);
                startActivity(i);
            }
        });

    }
}
