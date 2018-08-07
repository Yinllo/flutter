package com.desjf.dsjr.net;

/**
 * @Author YinL
 * @Date 2018/1/16 0016
 * @Describe  自定义的异步操作类
 * 1>主线程调用AsynTask子类实例的execute()方法后，首先会调用onPreExecute()方法。onPreExecute()在主线程中运行，可以用来写一些开始提示代码。
 * 2>之后启动新线程，调用doInBackground()方法，进行异步数据处理。
 * 3>处理完毕之后异步线程结束，在主线程中调用onPostExecute()方法。onPostExecute()可以进行一些结束提示处理。
 *  补充：在doInBackground()方法异步处理的时候，如果希望通知主线程一些数据（如：处理进度）。这时，可以调用publishProgress()方法。这时，
 *  主线程会调用AsynTask子类的onProgressUpdate()方法进行处理。
 */

public abstract class AbstractBackgroundTask<Params, Result> {
    private boolean cancelled = false;

    protected Params[] params;

    protected Result result;

    abstract public void execute(Params... params);

    //启动新线程进行异步数据处理
    abstract protected Result doInBackground(Params... params);

    //处理doInBackground返回的Result
    protected abstract void postExecute(Result result);

    protected void onPreExecute(){}

    protected void onPostExecute(Result result) {
    }

    //取消、清除
    public void cancel() {
        cancelled = true;
    }

    public boolean isCancelled() {
        return cancelled;
    }
}
