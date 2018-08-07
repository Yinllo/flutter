package com.desjf.dsjr.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;

import com.desjf.dsjr.R;
import com.desjf.dsjr.base.BaseFragment;
import com.desjf.dsjr.fragment.IndividualCenter.MyMainFragment;
import com.desjf.dsjr.fragment.IndividualCenter.WodeFragment;
import com.desjf.dsjr.fragment.ShouyeFragment;
import com.desjf.dsjr.fragment.invest.InvestFragment;
import com.desjf.dsjr.utils.PreferenceCache;

import java.util.List;

public class SplashActivity extends Activity {
    //休眠时间
    public static final int SLEEP_TIME=2000;
    private long strat_time;

    private List<BaseFragment> list_fragment;
    private ShouyeFragment shoyeFragment;
    private InvestFragment investfragment;//投资  带有头部导航栏
    private WodeFragment wodeFragment;
    private MyMainFragment myMainFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        initData();
    }


    private void initData() {
        strat_time = System.currentTimeMillis();
        new ADTask().execute();
    }

    class ADTask extends AsyncTask<Void,Void,Void> {

        @Override
        protected Void doInBackground(Void... voids) {
            long endtime=System.currentTimeMillis();
            long runtime=endtime-strat_time;
            if(runtime<SLEEP_TIME){
                try {
                    Thread.sleep(SLEEP_TIME-runtime);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            if (PreferenceCache.getGestureFlag()==true) {
                Intent intent = new Intent(SplashActivity.this, GestureTestActivity.class);
                intent.putExtra("gestureFlg", 3);
                startActivity(intent);
                finish();
            } else {
                Intent i=new Intent(SplashActivity.this, MainActivity.class);
                startActivity(i);

                finish();
            }

        }

    }
}
