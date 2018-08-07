package com.desjf.dsjr.biz.task;

import android.content.Intent;
import android.os.AsyncTask;
import android.widget.Toast;

import com.desjf.dsjr.R;
import com.desjf.dsjr.base.BaseApplication;
import com.desjf.dsjr.biz.exception.BizFailure;
import com.desjf.dsjr.biz.exception.NetWorkNotAvailableException;
import com.desjf.dsjr.biz.exception.OperationTimeOutException;
import com.desjf.dsjr.biz.exception.ZYException;
import com.desjf.dsjr.utils.PreferenceCache;
import com.desjf.dsjr.widget.WaitingView;


/**
 * 业务数据访问处理线程(隐式的任务)
 *
 * @param <Result>
 * @author lilingxu
 */
public abstract class BizDataAsyncTask<Result> extends
        AsyncTask<Void, Void, Boolean> {

    private Result mResult;

    private ZYException mException;

    private BizFailure mFailure;

    private WaitingView mWaitingView;


    public  BizDataAsyncTask() {
        super();
    }

    public BizDataAsyncTask(WaitingView waitingView) {
        super();
        mWaitingView = waitingView;
    }

    public void setWaitingView(WaitingView waitingView) {
        this.mWaitingView = waitingView;
    }

    @Override
    protected final Boolean doInBackground(Void... params) {
        try {
            mResult = doExecute();
            return true;
        } catch (ZYException e) {
            mException = e;
            return false;
        } catch (BizFailure f) {
            mFailure = f;
            return false;
        }
    }

    @Override
    protected void onCancelled() {
        super.onCancelled();
        if (mWaitingView != null) {
            mWaitingView.hide();
        }
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        if (mWaitingView != null) {
            mWaitingView.show();
        }
    }

    @Override
    protected void onPostExecute(Boolean result) {
        super.onPostExecute(result);
        if (mWaitingView != null) {
            mWaitingView.hide();
        }
        if (isCancelled()) {
            return;
        }
        if (result) {
            onExecuteSucceeded(mResult);
        } else {
            if (mException instanceof NetWorkNotAvailableException) {
                onNetworkNotAvailable();
            } else if (mException instanceof OperationTimeOutException) {
                onOperationTimeout();
            } else if (mException != null) {
                onExecuteException(mException);
            }

            if (mFailure != null) {
                onExecuteFailure(mFailure);
            }
        }
    }

    protected abstract Result doExecute() throws ZYException, BizFailure;

    protected abstract void onExecuteSucceeded(Result result);

    protected abstract void OnExecuteFailed(String error);

    protected void onNetworkNotAvailable() {
        Toast.makeText(BaseApplication.getAppContext(), R.string.network_not_available,
                Toast.LENGTH_SHORT).show();
        //OnExecuteFailed("无法连接到网络，请检查网络配置");
        OnExecuteFailed("");
    }

    protected void onExecuteException(ZYException exception) {
        Toast.makeText(BaseApplication.getAppContext(), R.string.opreation_failure,
                Toast.LENGTH_SHORT).show();
        //OnExecuteFailed("服务器访问异常，请稍后重试");
        OnExecuteFailed("");
    }

    protected void onExecuteFailure(BizFailure failure) {
        if (mFailure.getMessage().contains("用户名密码过期")) {// token认证失败
//            Toast.makeText(CheLiWangApp.getAppContext(), R.string.auth_expire,
//                    Toast.LENGTH_LONG).show();
            PreferenceCache.putToken("");
            PreferenceCache.putGestureFlag(false);
            Intent i = new Intent();
            i.setAction("dsjr.action.token.expire");
            BaseApplication.getAppContext().sendBroadcast(i);
//            BaseApplication.getAppContext().startActivity(new Intent().setClass(BaseApplication.getAppContext(), LoginActivity.class));
            //CheLiWangApp.getAppContext().startActivity(new Intent().setClass(CheLiWangApp.getAppContext(), LoginActivity.class));

        } /*else{
            Toast.makeText(CsyhApp.getAppContext(), failure.getMessage(),
					Toast.LENGTH_LONG).show();
		}*/
        OnExecuteFailed(mFailure.getMessage());
    }

    protected void onOperationTimeout() {
        Toast.makeText(BaseApplication.getAppContext(), R.string.msg_opreation_timeout,
                Toast.LENGTH_SHORT).show();
        //OnExecuteFailed("与服务器连接超时，请稍后重试");
        OnExecuteFailed("");
    }
}
