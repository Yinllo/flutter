package com.desjf.dsjr.biz.retrofit;

import android.content.Context;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.desjf.dsjr.utils.JsonUtils;
import com.desjf.dsjr.utils.ToastUtils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * @author YinL
 * @Describe 网络请求处理类（调用时只带成功的回调方法，没有带失败的回调方法）:根据返回的数据进行转换
 */

public class CallUtils {
    public static void call(final Context context, Call<String> call, final MyCallListener myCallListener) {
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
//                Log.e("tyl","--======---"+"====="+ response.body());
                //判断返回的额Json数据是否完整
                if (!JsonUtils.isGoodJson(response.body())) {
                    ToastUtils.showShortTost(context, "请检查网络或者重试");
                    return;
                }
                //将返回的数据解析成Json格式
                com.alibaba.fastjson.JSONObject jsonObject = JSON.parseObject(response.body());
                //根据返回码状态进行判断    if (jsonObject.getInteger("code") == 0) {
                if (0==0) {//没有返回码
                    myCallListener.onRespnseSuccess(response.body());
                }
                else {
                    if (jsonObject.getString("result") != null) {
                        ToastUtils.showShortTost(context, jsonObject.getString("result"));
                    } else {
                        ToastUtils.showShortTost(context, jsonObject.getString("message"));
                    }
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

                ToastUtils.showShortTost(context, "请检查网络");

            }
        });
    }
    public static void Refreshcall(final Context context, Call<String> call, final MyCallListener myCallListener, final SwipeRefreshLayout swipeRefreshLayout) {
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
//                Log.e("tyl","--======---"+ response.body());
                if(response.body()==null){
                    swipeRefreshLayout.setRefreshing(false);
                }
                //判断返回的额Json数据是否完整
                if (!JsonUtils.isGoodJson(response.body())) {
                    swipeRefreshLayout.setRefreshing(false);
                    ToastUtils.showShortTost(context, "请检查网络或者重试");
                    return;
                }

                //将返回的数据解析成Json格式
                com.alibaba.fastjson.JSONObject jsonObject = JSON.parseObject(response.body());
                //根据返回码状态进行判断    if (jsonObject.getInteger("code") == 0) {
                if (0==0) {//没有返回码
                    myCallListener.onRespnseSuccess(response.body());
                }
                else {
                    if (jsonObject.getString("result") != null) {
                        ToastUtils.showShortTost(context, jsonObject.getString("result"));
                    } else {
                        ToastUtils.showShortTost(context, jsonObject.getString("message"));
                    }
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                ToastUtils.showShortTost(context, "请检查网络");
                swipeRefreshLayout.setRefreshing(false);

            }
        });
    }
    public static void loadMoreNoHandle(Handler handler, TextView tv_result_footer) {
        tv_result_footer.setVisibility(View.VISIBLE);
        tv_result_footer.setText("没有更多");
//        handler.sendEmptyMessageDelayed(Constant.WHAT_FOOT_GONE, 2000);
    }

    public abstract static class MyCallListener {
        public abstract void onRespnseSuccess(String jsonString);

        public void onRespnseNoDataSuccess(String jsonString) {
        }

        public void onRespnse(String jsonString) {
        }

        public void callAgain() {
        }

        public void onNoRefresh() {
        }

    }

    public abstract static class CallAgainListener {
        public abstract void onRespnseSuccess(String jsonString);

        public void onRespnseNoDataSuccess(String jsonString) {
        }

        public void onRespnse(String jsonString) {
        }

        public abstract void callAgain();

        public void onNoRefresh() {
        }

    }
    public abstract static class CallHandleNoDataListener {
        public abstract void onRespnseSuccess(String jsonString);

        public abstract void onRespnseNoDataSuccess(String jsonString) ;

        public void onRespnse(String jsonString) {
        }

        public abstract void callAgain();

        public void onNoRefresh() {
        }

    }
    /*
    刷新，加载更多
     */
    public abstract static class CallRefreshListener {
        public abstract void onRespnseSuccess(String jsonString);

        public  void onRespnseNoDataSuccess(String jsonString){}

        public void onRespnse(String jsonString) {
        }

        public  void callAgain(){}

        public abstract void onNoRefresh() ;

    }
    /*
    原始的callback
     */
    public abstract static class CallOriginListener {

        public abstract  void onRespnse(String jsonString);

    }
}
