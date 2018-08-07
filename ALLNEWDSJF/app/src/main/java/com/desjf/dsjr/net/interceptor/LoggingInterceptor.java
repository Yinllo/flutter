package com.desjf.dsjr.net.interceptor;

import android.util.Log;

import java.io.IOException;

import okhttp3.FormBody;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * @Author YinL
 * @Date 2018/5/24 0024
 * @Describe
 */

public class LoggingInterceptor implements Interceptor {
    public static String TAG = "LoggingInterceptor";
    @Override
    public Response intercept(Interceptor.Chain chain) throws IOException {
        Request request = chain.request();
        long startTime = System.currentTimeMillis();
        Response response = chain.proceed(chain.request());
        long endTime = System.currentTimeMillis();
        long duration=endTime-startTime;
        MediaType mediaType = response.body().contentType();
        String content = response.body().string();
        Log.e(TAG,"\n");
        Log.e(TAG,"----------Start---------------------------");
        Log.e(TAG, "| ==--"+request.toString());
        Log.e(TAG, "| ==--RequestHeaders:--==--"+request.headers());
        String method=request.method();
        if("POST".equals(method)){
            StringBuilder sb = new StringBuilder();
            if (request.body() instanceof FormBody) {
                FormBody body = (FormBody) request.body();
                for (int i = 0; i < body.size(); i++) {
                    sb.append(body.encodedName(i) + "=" + body.encodedValue(i) + ",");
                }
                sb.delete(sb.length() - 1, sb.length());
                Log.e(TAG, "| ==--RequestParams：{"+sb.toString()+"}");
            }
        }
        Log.e(TAG, "| ==--Response：" + content);
        Log.e(TAG,"----------End:==--用时："+duration+"毫秒------------");
        return response.newBuilder()
                .body(ResponseBody.create(mediaType, content))
                .build();
    }

}