package com.desjf.dsjr.net;

import com.desjf.dsjr.net.converter.GsonConverterFactory;
import com.desjf.dsjr.net.interceptor.HttpHeaderInterceptor;
import com.desjf.dsjr.net.interceptor.LoggingInterceptor;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * @author YinL
 * @Describe  网络请求初始化
 */
public class RxRetrofitManager {

    /**
     * 1、 设置超时时长
     *   retrofit底层用的okHttp,所以设置超时还需要okHttp
     *  然后设置5秒超时
     *  TimeUnit为java.util.concurrent包下的时间单位
     *  TimeUnit.SECONDS这里为秒的单位
     * 2、添加拦截器
     */

   private static final OkHttpClient client = new OkHttpClient.Builder()
            .connectTimeout(20, TimeUnit.SECONDS)
            .writeTimeout(20, TimeUnit.SECONDS)
            .readTimeout(20, TimeUnit.SECONDS)
            .addInterceptor(new HttpHeaderInterceptor())
            .addInterceptor(new LoggingInterceptor())
            .sslSocketFactory(SSLSocketClient.getSSLSocketFactory())
            .hostnameVerifier(SSLSocketClient.getHostnameVerifier())
            .build();

    private static ApiService httpRequestService;
    static {
        Retrofit retrofit = new Retrofit.Builder()
                //http://192.168.2.146:3000/
                .baseUrl("http://t.dsp2p.com/")
                //增加返回值为String的支持
                .addConverterFactory(ScalarsConverterFactory.create())
                //增加返回值为Gson的支持(以实体类返回)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(client)
                .build();
        httpRequestService = retrofit.create(ApiService.class);
    }

    //单例
    public static ApiService getHttpRequestService() {
        if (httpRequestService == null) {
            synchronized (RxRetrofitManager.class) {
                if (httpRequestService == null) {
                    new RxRetrofitManager();
                }
            }
        }
        return httpRequestService;
    }

}
