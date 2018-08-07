package com.desjf.dsjr.biz.retrofit;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by YinL on 2018/1/31 0028.
 * 网络请求接口类      因为这是新接口没有使用WebService  所以使用新的网络请求框架
 */

public interface HttpRequestService {

     //获取累计交易本金
     @POST("IloanFront/information/BorrowimgAmount")
     Call<String> getAllMoney();

     //获取赚取利息
     @POST("IloanFront/information/AccruedInterest")
     Call<String> getAllInterest();

     //风险测评分数提交
     @FormUrlEncoded
     @POST("finance/topicSubmit")
     Call<String> setGrade(@Field("totalScoreStr") int grade,@Field("mobile") String phoneNum);

     /**
      * 后台返回的json中 均为  result、message、status  所以可以公用一个Module
      * @return
      */
     //是否进行了风险测评
     @FormUrlEncoded
     @POST("finance/riskLevel")
     Call<String> ifTest(@Field("mobile") String phoneNum);

     //进行 法大大授权
     @FormUrlEncoded
     @POST("finance/fddLicense")
     Call<String> Empower(@Field("mobile") String phoneNum);

     //取消 法大大授权
     @FormUrlEncoded
     @POST("finance/fddCancelLicense")
     Call<String> CancelEmpower(@Field("mobile") String phoneNum);

     //是否进行了授权
     @FormUrlEncoded
     @POST("finance/getFddLicense")
     Call<String> ifEmpower(@Field("mobile") String phoneNum);

     //项目详情 数据接口      Productid 代表标的id
     @FormUrlEncoded
     @POST("IloanFront/webservice/getEnterpriseInf")
     Call<String> getObjectInfo(@Field("Productid") String id);

     //新 老用户判定
     @FormUrlEncoded
     @POST("oldOrNewUserApp")
     Call<String> getIdentity(@Field("MobileNo")String MobileNo);

     //存管账户注册
     @FormUrlEncoded
     @POST("AppOpenAccount")
     Call<String> bankReg(@Field("MobileNo")String MobileNo);

     //充值
     @FormUrlEncoded
     @POST("rechargeApp")
     Call<String> charge(@Field("MobileNo")String MobileNo,@Field("TransAmt") String TransAmt);

     //提现
     @FormUrlEncoded
     @POST("withdrawDepositApp")
     Call<String> tixian(@Field("MobileNo")String MobileNo,@Field("TransAmt") String TransAmt,@Field("FastFlag") String FastFlag);

     //修改手机号
     @FormUrlEncoded
     @POST("changeMobileAPP")
     Call<String> changePhone(@Field("MobileNo")String MobileNo ,@Field("oldMobileNo")String oldMobileNo,@Field("UsrName") String UsrName,
                              @Field("IdentType") String IdentType,@Field("IdentNo")String IdentNo);

     //修改银行卡
     @FormUrlEncoded
     @POST("changeAppBank")
     Call<String> changeBank(@Field("MobileNo")String MobileNo );

     //修改与找回存管密码
     @FormUrlEncoded
     @POST("changePayPswApp")
     Call<String> changePwd(@Field("MobileNo")String Mobile);

     //用户充值记录
     @FormUrlEncoded
     @POST("page/UserRecord")
     Call<String> getRecord(@Field("token") String token,@Field("firstIdx")String  firstIdx,@Field("maxCount")String  maxCount);

     //用户提现记录
     @FormUrlEncoded
     @POST("page/UserRecordWD")
     Call<String> getWithdraw(@Field("token") String token,@Field("firstIdx")String  firstIdx,@Field("maxCount")String  maxCount);

     //用户投资记录
     @FormUrlEncoded
     @POST("page/getInvestM")
     Call<String> getInvestM(@Field("token") String token,@Field("firstIdx")String  firstIdx,@Field("maxCount")String  maxCount);

     //用户回款记录
     @FormUrlEncoded
     @POST("page/getUserReturnM")
     Call<String> getUserReturnM(@Field("token") String token,@Field("firstIdx")String  firstIdx,@Field("maxCount")String  maxCount);

     //用户资金流水（资金明细中的全部）
     @FormUrlEncoded
     @POST("page/getUserAllMessage")
     Call<String> getUserAllMessage(@Field("token") String token,@Field("firstIdx")String  firstIdx,@Field("maxCount")String  maxCount,@Field("type")String type);

     //用户个人信息查询
     @FormUrlEncoded
     @POST("bg/queryUserInf")
     Call<String> queryUserInf(@Field("token") String token);

     //用户余额查询
     @FormUrlEncoded
     @POST("bg/QueryBalance")
     Call<String> QueryBalance(@Field("token") String token);

     //累计收益
     @FormUrlEncoded
     @POST("bhuser/selectAccount")
     Call<String> selectAccount(@Field("MobileNo") String MobileNo);

     //用户暂未回款记录
     @FormUrlEncoded
     @POST("bg/queryUserRecover")
     Call<String> queryUserRecover(@Field("token") String token,@Field("pageNumber") String pageNumber,@Field("pageSize") String pageSize);

     //用户已回款详细记录
     @FormUrlEncoded
     @POST("bg/queryUserRecoverYes")
     Call<String> queryUserRecoverYes(@Field("token") String token,@Field("pageSize") String pageSize,@Field("pageNumber") String pageNumber);

     //用户已投资信息
     @FormUrlEncoded
     @POST("bg/queryUserTenderYes")
     Call<String> queryUserTenderYes(@Field("token") String token,@Field("pageNumber") String pageNumber,@Field("pageSize") String pageSize);

     //用户未满标信息
     @FormUrlEncoded
     @POST("bg/queryUserTender")
     Call<String> queryUserTender(@Field("token") String token,@Field("pageNumber") String pageNumber,@Field("pageSize") String pageSize);

     //投资
     @FormUrlEncoded
     @POST("finance/tender")
     Call<String> investment(@Field("token") String token,@Field("productId") String productId,@Field("smsCode") String smsCode
             ,@Field("tenderAmountStr") String tenderAmountStr,@Field("ticketType") String ticketType,@Field("ticketId") String ticketId
             ,@Field("payPassword") String payPassword,@Field("directionalPwd") String directionalPwd,@Field("agent") String agent);

     //查询授权记录
     @FormUrlEncoded
     @POST("bg/QueryAuthInf")
     Call<String> QueryAuthInf(@Field("MobileNo")String Mobile);

     //获取动态口令
     @POST("bg/dynamicpassword")
     @FormUrlEncoded
     Call<String> dynamicpassword(@Field("token") String token);

     //检查手机号码
     @POST("regist/checkMobile")
     @FormUrlEncoded
     Call<String> checkMobile(@Field("mobile") String mobile);
     //发送验证码
     @POST("regist/sendVerifyCode")
     @FormUrlEncoded
     Call<String> sendVerifyCode(@Field("mobile") String mobile);
     //注册
     @POST("regist/regist")
     @FormUrlEncoded
     Call<String> regist(@Field("mobile") String mobile,@Field("password") String password,@Field("verifyCode") String verifyCode
     ,@Field("introducer") String introducer
     ,@Field("placeid") String placeid,@Field("tagEndMode") String tagEndMode);

      //预计收益
     @FormUrlEncoded
     @POST("finance/getExpectedInvest")
     Call<String> getExpectedInvest(@Field("productId") String productId
             ,@Field("tenderAmountStr") String tenderAmountStr
             ,@Field("rateStr") String rateStr);

     //全额投资
     @FormUrlEncoded
     @POST("finance/allTenderNew")
     Call<String> allTenderNew(@Field("token") String token,@Field("productId") String productId);

     //投资记录
     @FormUrlEncoded
     @POST("finance/listInvestor")
     Call<String>listInvestor(@Field("productId") String productId,@Field("pageIndex") String pageIndex,@Field("pageSize") String pageSize);

     //修改手机号码
     @FormUrlEncoded
     @POST("page/getPersonalData")
     Call<String> getPersonalData(@Field("token") String token);

     //获取邀请相关信息  邀请红包  邀请返现
     @FormUrlEncoded
     @POST("invite/award")
     Call<String> getAwarding(@Field("token") String token,@Field("queryType") String queryType,@Field("pageNumber") String pageNumber,@Field("pageSize") String pageSize);

}
