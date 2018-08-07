package com.desjf.dsjr.net.common;


import android.widget.Toast;

import com.desjf.dsjr.R;
import com.desjf.dsjr.net.exception.ServerNoDataResponseException;
import com.desjf.dsjr.net.exception.ServerResponseException;
import com.desjf.dsjr.utils.LogUtils;
import com.desjf.dsjr.utils.ToastUtils;
import com.google.gson.JsonParseException;

import org.json.JSONException;

import java.io.InterruptedIOException;
import java.net.ConnectException;
import java.net.UnknownHostException;
import java.text.ParseException;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import retrofit2.HttpException;

/**
 * @Author YinL
 * @Date 2018/5/21 0021
 * @Describe   用来处理服务器响应的基类：解析异常类型并进行处理
 */

public abstract class BasicObserver<T> implements Observer<T> {

    //(订阅)、事件  相当于OnClickListener
    @Override
    public void onSubscribe(Disposable d) {

    }

    //普通事件 onNext() （相当于 onClick() / onEvent()）
    @Override
    public void onNext(T response) {
        onSuccess(response);
        onFinish();
    }

    //事件队列完结  没有onNext触发时执行
    @Override
    public void onComplete() {

    }

    //事件队列异常
    @Override
    public void onError(Throwable e) {
        //根据错误类型进行相应的错误处理
        LogUtils.e("Retrofit",e.getMessage());
        //http错误即网络出错
        if(e instanceof HttpException){
          onException(BAD_NETWORK);
        }
        //连接错误
        else if(e instanceof ConnectException ||e instanceof UnknownHostException){
          onException(CONNECT_ERROR);
        }
        //连接超时
        else if(e instanceof InterruptedIOException){
          onException(CONNECT_TIMEOUT);
        }
        //解析错误
        else if(e instanceof ParseException||e instanceof JSONException||e instanceof JsonParseException){
          onException(PARSE_ERROR);
        }
        //服务器异常
        else if(e instanceof ServerResponseException){
           onFail(e.getMessage());
        }
        //服务器无数据返回异常
        else if(e instanceof ServerNoDataResponseException){
            onSuccess(null);
        }
        //未知错误
        else{
            onException(UNKNOWN_ERROR);
        }
        onFinish();
    }

    public void onException(int reason){
        switch (reason){
            case 1:
                ToastUtils.show(R.string.connect_error, Toast.LENGTH_SHORT);
                break;
            case 2:
                ToastUtils.show(R.string.connect_timeout, Toast.LENGTH_SHORT);
                break;
            case 3:
                ToastUtils.show(R.string.bad_network, Toast.LENGTH_SHORT);
                break;
            case 4:
                ToastUtils.show(R.string.parse_error, Toast.LENGTH_SHORT);
                break;
            case 5:
            default:
                ToastUtils.show(R.string.unknown_error, Toast.LENGTH_SHORT);
                break;
        }
    }
    /**
     * 定义错误类型 :请求网络失败的原因
     */
    //连接错误
    private static final int CONNECT_ERROR=1;
    //连接超时
    private static final int CONNECT_TIMEOUT=2;
    //网络错误
    private static final int BAD_NETWORK=3;
    //解析数据失败
    private static final int PARSE_ERROR=4;
    //未知错误
    private static final int UNKNOWN_ERROR=5;

    /**
     * 请求成功
     * @param response 服务器返回的数据
     */
    abstract public void onSuccess(T response);

    /**
     * 服务器返回数据，但响应码不为200
     *
     */
    /**
     * 服务器返回数据，但响应码不为1000
     */
    public void onFail(String message) {
        ToastUtils.show(message);
    }

    public void onFinish(){}

}
