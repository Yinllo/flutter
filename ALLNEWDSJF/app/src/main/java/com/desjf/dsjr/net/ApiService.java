package com.desjf.dsjr.net;

/**
 * Created by YinL on 2018/1/13 0013.
 */

import com.desjf.dsjr.bean.LoginBean;
import com.desjf.dsjr.bean.SubjectBean;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * @author YinL
 * @Describe  网络请求接口
 */
public interface ApiService {
    //登录
    @FormUrlEncoded
    @POST("dsjf/user/login/appLogin")
    Observable<LoginBean> login(@FieldMap Map<String,Object> map);

    //登录验证码获取
    @FormUrlEncoded
    @POST("dsjf/user/login/sendVerifyCode")
    Observable<String> getloginCode(@Field("mobile")String mobile);

    //注册  传多个参数用Object  或者对应的实体类
    @FormUrlEncoded
    @POST("dsjf/user/regist/regist")
    Observable<Object> regist(@FieldMap Map<String,Object> map);

    //注册验证码获取   只传一个参数可以用String
    @FormUrlEncoded
    @POST("dsjf/user/regist/sendVerifyCode")
    Observable<String> getregCode(@Field("mobile")String mobile);

    //标的列表获取
    @FormUrlEncoded
    @POST("dsjf/finance/listFinance")
    Observable<SubjectBean> listFinance(@FieldMap Map<String,Object> map);


}
