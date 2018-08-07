package com.desjf.dsjr.net.interceptor;

import android.os.Build;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * @Author YinL
 * @Date 2018/5/28 0028
 * @Describe
 */

public class HttpHeaderInterceptor implements Interceptor{
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request().newBuilder()
                .addHeader("source-terminal", "Android")   //操作系统名称（注：ios、android）
                .addHeader("device-model", Build.MODEL)         //设备型号
                .addHeader("os-version", Build.VERSION.RELEASE)//操作系统版本号
//                .header("Authorization", tokenType + " " + accessToken)
//                .header("Content-Type", "application/json")
//                .addHeader("Connection", "close")
//                .addHeader("Accept-Encoding", "identity")
                .build();
        return chain.proceed(request);
    }
}
