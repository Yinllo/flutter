package com.desjf.dsjr.net;

import android.annotation.SuppressLint;
import android.os.Handler;
import android.os.Message;

/**
 * @Author YinL
 * @Date 2018/1/16 0016
 * @Describe 简单的异步任务类：用于app更新
 */


@SuppressLint("HandlerLeak")
public abstract class EasyLocalTask<Params, Result> extends EasyTask<Params, Result> {

    private Handler handler;

    @Override
    public void execute(Params... params) {
        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                onPostExecute(result);
            }
        };
        super.execute(params);
    }


    @Override
    protected void postExecute(Result result) {
        this.result = result;
        cancel();
        handler.sendMessage(Message.obtain());
    }
}

