package com.example.yinl.simplebutterknife.Annotation;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.yinl.simplebutterknife.R;

public class AnnotationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_annotation);

        Test t=new Test();

        try {
            Butterknife.bind(t);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        Toast.makeText(this,t.toString(),Toast.LENGTH_SHORT).show();

    }
}
