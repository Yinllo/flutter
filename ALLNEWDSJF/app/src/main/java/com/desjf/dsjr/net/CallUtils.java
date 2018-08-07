package com.desjf.dsjr.net;

import android.content.Context;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.desjf.dsjr.utils.JsonUtils;
import com.desjf.dsjr.utils.ToastUtils;
import com.desjf.dsjr.widget.BaseDialog;
import com.facebook.shimmer.ShimmerFrameLayout;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * @author YinL
 * @Describe 网络请求处理类:根据返回的数据进行转换
 */

public class CallUtils {
    public static void call(final Context context, Call<String> call, final MyCallListener myCallListener) {
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                //判断返回的额Json数据是否完整
                if (!JsonUtils.isGoodJson(response.body())) {
                    ToastUtils.showToast(context, "请检查网络或者重试");
                    return;
                }
                //将返回的数据解析成Json格式
                com.alibaba.fastjson.JSONObject jsonObject = JSON.parseObject(response.body());
                //根据返回码状态进行判断
//                if (jsonObject.getInteger("code") == 0) {
                if (0 == 0) {
                    myCallListener.onRespnseSuccess(response.body());
                } else {
                    if (jsonObject.getString("result") != null) {
                        ToastUtils.showToast(context, jsonObject.getString("result"));
                    } else {
                        ToastUtils.showToast(context, jsonObject.getString("message"));
                    }
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                ToastUtils.showToast(context, "请检查网络");

            }
        });
    }

    public static void call(final Context context, Call<String> call, final ShimmerFrameLayout shimmerFrameLayout,
                            final View layout_content, final CallAgainListener callAgainListener) {
        shimmerFrameLayout.setVisibility(View.VISIBLE);
        layout_content.setVisibility(View.INVISIBLE);

        shimmerFrameLayout.setDuration(1500);

        shimmerFrameLayout.startShimmerAnimation();
        final TextView tv_load = (TextView) shimmerFrameLayout.getChildAt(0);
        tv_load.setText("德晟金服");
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(final Call<String> call, Response<String> response) {
                shimmerFrameLayout.stopShimmerAnimation();

                if (!JsonUtils.isGoodJson(response.body())) {
                    ToastUtils.showToast(context, "请检查网络或者重试");
                    tv_load.setText("点击重试");
                    tv_load.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            callAgainListener.callAgain();

                        }
                    });
                    return;
                }
                com.alibaba.fastjson.JSONObject jsonObject = JSON.parseObject(response.body());
                if (jsonObject.getInteger("code") == 0) {
                    shimmerFrameLayout.setVisibility(View.GONE);
                    layout_content.setVisibility(View.VISIBLE);

                    callAgainListener.onRespnseSuccess(response.body());
                } else {
                    if (jsonObject.getString("result") != null) {

                        ToastUtils.showToast(context, jsonObject.getString("result"));
                    } else {
                        ToastUtils.showToast(context, jsonObject.getString("message"));

                    }

                    tv_load.setText("点击重试");
                    tv_load.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            callAgainListener.callAgain();
                        }
                    });
                }

            }

            @Override
            public void onFailure(final Call<String> call, Throwable t) {

                shimmerFrameLayout.stopShimmerAnimation();

                ToastUtils.showToast(context, "请检查网络");
                tv_load.setText("点击重试");
                tv_load.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        callAgainListener.callAgain();

                    }
                });
            }
        });
    }
//    public static void callRefresh(final Context context, Call<String> call, final ShimmerFrameLayout shimmerFrameLayout,
//                                   final View layout_content, final TwinklingRefreshLayout twinklingRefreshLayout, final CallAgainListener callAgainListener) {
//        shimmerFrameLayout.setVisibility(View.VISIBLE);
//        layout_content.setVisibility(View.VISIBLE);
//
//        shimmerFrameLayout.setDuration(1500);
//
//        shimmerFrameLayout.startShimmerAnimation();
//        final TextView tv_load = (TextView) shimmerFrameLayout.getChildAt(0);
//        tv_load.setText("德晟金服");
//        call.enqueue(new Callback<String>() {
//            @Override
//            public void onResponse(final Call<String> call, Response<String> response) {
//                twinklingRefreshLayout.finishRefreshing();
//                shimmerFrameLayout.stopShimmerAnimation();
//
//                if (!JsonUtils.isGoodJson(response.body())) {
//                    ToastUtils.showToast(context, "请检查网络或者重试");
//
//
//                    tv_load.setText("点击重试");
//                    tv_load.setOnClickListener(new View.OnClickListener() {
//                        @Override
//                        public void onClick(View v) {
//                            callAgainListener.callAgain();
//
//                        }
//                    });
//                    return;
//                }
//
//
//                com.alibaba.fastjson.JSONObject jsonObject = JSON.parseObject(response.body());
//                if (jsonObject.getInteger("code") == 0) {
//                    shimmerFrameLayout.setVisibility(View.GONE);
//                    layout_content.setVisibility(View.VISIBLE);
//
//                    callAgainListener.onRespnseSuccess(response.body());
//                } else {
//                    if (jsonObject.getString("result") != null) {
//
//                        ToastUtils.showToast(context, jsonObject.getString("result"));
//                    } else {
//                        ToastUtils.showToast(context, jsonObject.getString("message"));
//
//                    }
//
//                    tv_load.setText("点击重试");
//                    tv_load.setOnClickListener(new View.OnClickListener() {
//                        @Override
//                        public void onClick(View v) {
//                            callAgainListener.callAgain();
//                        }
//                    });
//                }
//
//            }
//
//            @Override
//            public void onFailure(final Call<String> call, Throwable t) {
//                twinklingRefreshLayout.finishRefreshing();
//                shimmerFrameLayout.stopShimmerAnimation();
//
//                ToastUtils.showToast(context, "请检查网络");
//                tv_load.setText("点击重试");
//                tv_load.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        callAgainListener.callAgain();
//
//                    }
//                });
//
//            }
//        });
//    }

    public static void callHandleNoData(final Context context, Call<String> call, final ShimmerFrameLayout shimmerFrameLayout,
                                        final View layout_content,
                                        final View view_empty, final CallHandleNoDataListener callHandleNoDataListener) {
        shimmerFrameLayout.setVisibility(View.VISIBLE);
        view_empty.setVisibility(View.GONE);
        layout_content.setVisibility(View.INVISIBLE);

        shimmerFrameLayout.setDuration(1500);

        shimmerFrameLayout.startShimmerAnimation();
        final TextView tv_load = (TextView) shimmerFrameLayout.getChildAt(0);
        tv_load.setText("德晟金服");
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(final Call<String> call, Response<String> response) {
                shimmerFrameLayout.stopShimmerAnimation();

                if (!JsonUtils.isGoodJson(response.body())) {
                    ToastUtils.showToast(context, "请检查网络或者重试");


                    tv_load.setText("点击重试");
                    tv_load.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            callHandleNoDataListener.callAgain();

                        }
                    });
                    return;
                }


                com.alibaba.fastjson.JSONObject jsonObject = JSON.parseObject(response.body());
                if (jsonObject.getInteger("code") == 0) {
                    shimmerFrameLayout.setVisibility(View.GONE);
                    layout_content.setVisibility(View.VISIBLE);

                    if (jsonObject.get("list").toString().equals("[]")) {
                        view_empty.setVisibility(View.VISIBLE);

                        callHandleNoDataListener.onRespnseNoDataSuccess(response.body());
                        return;
                    }

                    callHandleNoDataListener.onRespnseSuccess(response.body());
                } else {
                    if (jsonObject.getString("result") != null) {

                        ToastUtils.showToast(context, jsonObject.getString("result"));
                    } else {
                        ToastUtils.showToast(context, jsonObject.getString("message"));

                    }
                    tv_load.setText("点击重试");
                    tv_load.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            callHandleNoDataListener.callAgain();
                        }
                    });
                }

            }

            @Override
            public void onFailure(final Call<String> call, Throwable t) {

                shimmerFrameLayout.stopShimmerAnimation();

                ToastUtils.showToast(context, "请检查网络");
                tv_load.setText("点击重试");
                tv_load.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        callHandleNoDataListener.callAgain();

                    }
                });

            }
        });
    }

    public static void callHandleNoData(final Context context, Call<String> call, final ShimmerFrameLayout shimmerFrameLayout,
                                        final View layout_content,
                                        final CallHandleNoDataListener callHandleNoDataListener) {
        shimmerFrameLayout.setVisibility(View.VISIBLE);
        layout_content.setVisibility(View.INVISIBLE);

        shimmerFrameLayout.setDuration(1500);

        shimmerFrameLayout.startShimmerAnimation();
        final TextView tv_load = (TextView) shimmerFrameLayout.getChildAt(0);
        tv_load.setText("德晟金服");
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(final Call<String> call, Response<String> response) {
                shimmerFrameLayout.stopShimmerAnimation();

                if (!JsonUtils.isGoodJson(response.body())) {
                    ToastUtils.showToast(context, "请检查网络或者重试");


                    tv_load.setText("点击重试");
                    tv_load.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            callHandleNoDataListener.callAgain();

                        }
                    });
                    return;
                }


                com.alibaba.fastjson.JSONObject jsonObject = JSON.parseObject(response.body());
                if (jsonObject.getInteger("code") == 0) {
                    shimmerFrameLayout.setVisibility(View.GONE);
                    layout_content.setVisibility(View.VISIBLE);

                    if (jsonObject.get("list").toString().equals("[]")) {

                        callHandleNoDataListener.onRespnseNoDataSuccess(response.body());
                        return;
                    }

                    callHandleNoDataListener.onRespnseSuccess(response.body());
                } else {
                    if (jsonObject.getString("result") != null) {

                        ToastUtils.showToast(context, jsonObject.getString("result"));
                    } else {
                        ToastUtils.showToast(context, jsonObject.getString("message"));

                    }
                    tv_load.setText("点击重试");
                    tv_load.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            callHandleNoDataListener.callAgain();
                        }
                    });
                }

            }

            @Override
            public void onFailure(final Call<String> call, Throwable t) {

                shimmerFrameLayout.stopShimmerAnimation();

                ToastUtils.showToast(context, "请检查网络");
                tv_load.setText("点击重试");
                tv_load.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        callHandleNoDataListener.callAgain();

                    }
                });

            }
        });
    }

    public static void callHandleNoData(final Context context, Call<String> call,
                                        final CallHandleNoDataListener callHandleNoDataListener) {


        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(final Call<String> call, Response<String> response) {

                if (!JsonUtils.isGoodJson(response.body())) {
                    ToastUtils.showToast(context, "请检查网络或者重试");

                    return;
                }


                com.alibaba.fastjson.JSONObject jsonObject = JSON.parseObject(response.body());
                if (jsonObject.getInteger("code") == 0) {

                    if (jsonObject.get("list").toString().equals("[]")) {

                        callHandleNoDataListener.onRespnseNoDataSuccess(response.body());
                        return;
                    }

                    callHandleNoDataListener.onRespnseSuccess(response.body());
                } else {
                    if (jsonObject.getString("result") != null) {

                        ToastUtils.showToast(context, jsonObject.getString("result"));
                    } else {
                        ToastUtils.showToast(context, jsonObject.getString("message"));

                    }
                }

            }

            @Override
            public void onFailure(final Call<String> call, Throwable t) {

                ToastUtils.showToast(context, "请检查网络");

            }
        });
    }

    public static void callHandleNoData(final Context context, final BaseDialog baseDialog, Call<String> call,
                                        final CallHandleNoDataListener callHandleNoDataListener) {

        baseDialog.show();

        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(final Call<String> call, Response<String> response) {
                baseDialog.dismiss();

                if (!JsonUtils.isGoodJson(response.body())) {
                    ToastUtils.showToast(context, "请检查网络或者重试");

                    return;
                }


                com.alibaba.fastjson.JSONObject jsonObject = JSON.parseObject(response.body());
                if (jsonObject.getInteger("code") == 0) {

                    if (jsonObject.get("list").toString().equals("[]")) {

                        callHandleNoDataListener.onRespnseNoDataSuccess(response.body());
                        return;
                    }

                    callHandleNoDataListener.onRespnseSuccess(response.body());
                } else {
                    if (jsonObject.getString("result") != null) {

                        ToastUtils.showToast(context, jsonObject.getString("result"));
                    } else {
                        ToastUtils.showToast(context, jsonObject.getString("message"));

                    }
                }

            }

            @Override
            public void onFailure(final Call<String> call, Throwable t) {
                baseDialog.dismiss();

                ToastUtils.showToast(context, "请检查网络");

            }
        });
    }

    public static void call(final Context context, Call<String> call, final ShimmerFrameLayout shimmerFrameLayout,
                            final CallAgainListener callAgainListener) {
        shimmerFrameLayout.setVisibility(View.VISIBLE);

        shimmerFrameLayout.setDuration(1500);

        shimmerFrameLayout.startShimmerAnimation();
        final TextView tv_load = (TextView) shimmerFrameLayout.getChildAt(0);
        tv_load.setText("德晟金服");
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(final Call<String> call, Response<String> response) {

                shimmerFrameLayout.stopShimmerAnimation();

                if (!JsonUtils.isGoodJson(response.body())) {
                    ToastUtils.showToast(context, "请检查网络或者重试");


                    tv_load.setText("点击重试");
                    tv_load.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            callAgainListener.callAgain();

                        }
                    });
                    return;
                }


                com.alibaba.fastjson.JSONObject jsonObject = JSON.parseObject(response.body());
                if (jsonObject.getInteger("code") == 0) {
                    shimmerFrameLayout.setVisibility(View.GONE);

                    callAgainListener.onRespnseSuccess(response.body());
                } else {
                    if (jsonObject.getString("result") != null) {

                        ToastUtils.showToast(context, jsonObject.getString("result"));
                    } else {
                        ToastUtils.showToast(context, jsonObject.getString("message"));

                    }
                    tv_load.setText("点击重试");
                    tv_load.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            callAgainListener.callAgain();
                        }
                    });
                }

            }

            @Override
            public void onFailure(final Call<String> call, Throwable t) {

                shimmerFrameLayout.stopShimmerAnimation();

                ToastUtils.showToast(context, "请检查网络");
                tv_load.setText("点击重试");
                tv_load.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        callAgainListener.callAgain();

                    }
                });

            }
        });
    }

    public static void call(final Context context, final BaseDialog baseDialog, Call<String> call,
                            final MyCallListener myCallListener) {
        baseDialog.show();
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                baseDialog.dismiss();
                if (!JsonUtils.isGoodJson(response.body())) {
                    ToastUtils.showToast(context, "请检查网络或者重试");

                    return;
                }

                com.alibaba.fastjson.JSONObject jsonObject = JSON.parseObject(response.body());
                if (jsonObject.getInteger("code") == 0) {
                    myCallListener.onRespnseSuccess(response.body());
                } else {
                    if (jsonObject.getString("result") != null) {

                        ToastUtils.showToast(context, jsonObject.getString("result"));
                    } else {
                        ToastUtils.showToast(context, jsonObject.getString("message"));

                    }
                }

            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                baseDialog.dismiss();

                ToastUtils.showToast(context, "请检查网络");


            }
        });
    }

    public static void callOrigin(final Context context, final BaseDialog baseDialog, Call<String> call,
                                  final CallOriginListener callOriginListener) {
        baseDialog.show();
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                baseDialog.dismiss();
                if (!JsonUtils.isGoodJson(response.body())) {
                    ToastUtils.showToast(context, "请检查网络或者重试");

                    return;
                }

                callOriginListener.onRespnse(response.body());


            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                baseDialog.dismiss();

                ToastUtils.showToast(context, "请检查网络或者重试");


            }
        });
    }

//    public static void call(final Context context, Call<String> call, final TwinklingRefreshLayout twinklingRefreshLayout,
//                            final Handler handler, final TextView tv_result_head, final TextView tv_result_foot,
//                            final CallRefreshListener callRefreshListener) {
//
//        call.enqueue(new Callback<String>() {
//            @Override
//            public void onResponse(Call<String> call, Response<String> response) {
//                twinklingRefreshLayout.finishRefreshing();
//                twinklingRefreshLayout.finishLoadmore();
//
//                if (!JsonUtils.isGoodJson(response.body())) {
//                    ToastUtils.showToast(context, "请检查网络或者重试");
//
//                    return;
//                }
//
//                com.alibaba.fastjson.JSONObject jsonObject = JSON.parseObject(response.body());
//                if (jsonObject.getInteger("code") == 0) {
//                    callRefreshListener.onRespnseSuccess(response.body());
//                } else {
//                    if (tv_result_head != null) {
//                        callRefreshListener.onNoRefresh();
//                    }
//                    if (tv_result_foot != null) {
//                        loadMoreNoHandle(handler, tv_result_foot);
//
//                    }
//
//                }
//            }
//
//            @Override
//            public void onFailure(Call<String> call, Throwable t) {
//
//
//                twinklingRefreshLayout.finishRefreshing();
//                twinklingRefreshLayout.finishLoadmore();
//
//                ToastUtils.showToast(context, "请检查网络");
//
//
//            }
//        });
//    }

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
