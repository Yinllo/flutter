package com.desjf.dsjr.net.interceptor;

import android.annotation.SuppressLint;

import com.desjf.dsjr.utils.LogUtils;

import java.io.IOException;

import okhttp3.FormBody;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * @Author YinL
 * @Date 2018/5/22 0022
 * @Describe  http请求拦截器：获取请求信息和返回的json数据信息
 */

public class HttpLoggingInterceptor implements Interceptor{

    @SuppressLint("DefaultLocale")
    @Override
    public Response intercept(Chain chain) throws IOException {
        //chain中包含request和response信息
        Request request=chain.request();
        //获取请求发起时间
        long startTime=System.nanoTime();
//        LogUtils.e(String.format("请求URL------%s on %s%n请求头------%s",
//                request.url(), chain.connection(), request.headers()));
        //获得response对象
        Response response=chain.proceed(request);
        //收到响应的时间
        long  responseTime= System.nanoTime();

        //输出请求信息
        String method=request.method();
        if("POST".equals(method)){
            StringBuilder sb = new StringBuilder();
            if (request.body() instanceof FormBody) {
                FormBody body = (FormBody) request.body();
                for (int i = 0; i < body.size(); i++) {
                    sb.append(body.encodedName(i) + "=" + body.encodedValue(i) + ",");
                }
                sb.delete(sb.length() - 1, sb.length());
                LogUtils.e(String.format("请求URL------%s on %s %n请求参数------%s %n请求头------%s",
                        request.url(), chain.connection(),sb.toString(),request.headers()));
//                LogUtils.e("| RequestParams:{"+sb.toString()+"}");
            }
        }

        /**
         * 这里不能直接使用response.body().string()的方式输出日志
         * 因为response.body().string()之后，response中的流会被关闭，程序会报错，我们需要创建出一
         * 个新的response给应用层处理
         */
//        String content=response.body().string();
        ResponseBody responseBody = response.peekBody(1024 * 1024);

        LogUtils.e(String.format("响应URL-------: %s %n响应数据------%s 请求用时--------%.1fms%n%s",
                response.request().url(),
                responseBody.string(),
                (responseTime - startTime) / 1e6d,
                response.headers()));
        return response;

    }
}
