package com.desjf.dsjr.net;

/**
 * @Author YinL
 * @Date 2018/1/16 0016
 * @Describe
 *  execute（）向doInBackground（）传递。
 *　doInBackground()的返回值会传递给onPostExecute()。
 *　publishProgress()向progressUpdate（）传递。
 */

public abstract class EasyTask<Params, Result> extends AbstractBackgroundTask<Params, Result> implements Runnable {

    private long executeTime = -1;
    private long waitTime = -1;
    private Object lockObj = new Object();
    private boolean isFirst = true;

    public EasyTask() {

    }

    public void execute(Params... params) {
        this.params = params;
        onPreExecute();
        new Thread(this).start();
    }

    public void wakeUp() {
        synchronized (lockObj) {
            waitTime = System.currentTimeMillis();
            lockObj.notify();
        }
    }

    @Override
    public void run() {
        doBeforeBackground();
        try {
            while (!isCancelled()) {
                if(!isFirst){
                    waitForWakingUp();
                }
                isFirst = false;
                if (isCancelled()) {
                    break;
                }
                executeTime = System.currentTimeMillis();
                postExecute(doInBackground(params));
            }
        } finally {
            doAfterBackground();
        }
    }

    protected void waitForWakingUp() {
        synchronized (lockObj) {
            if (waitTime <= executeTime) {
                try {
                    lockObj.wait();
                } catch (InterruptedException e) {
                    // ignore
                }
            }
        }
    }

    protected boolean isExecuting() {
        return waitTime > executeTime;
    }

    protected void doBeforeBackground() {
    }

    protected void doAfterBackground() {
    }

    @Override
    protected void postExecute(Result result) {
        this.result = result;
        onPostExecute(result);
    }

    @Override
    public void cancel() {
        super.cancel();
        wakeUp();
    }
}

