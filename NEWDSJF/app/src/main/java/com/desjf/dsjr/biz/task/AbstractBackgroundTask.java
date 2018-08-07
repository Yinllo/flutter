package com.desjf.dsjr.biz.task;

public abstract class AbstractBackgroundTask<Params, Result> {
	private boolean cancelled = false;

	protected Params[] params;

	protected Result result;

	abstract public void execute(Params... params);

	abstract protected Result doInBackground(Params... params);

	protected abstract void postExecute(Result result);

	protected void onPreExecute(){}
	
	protected void onPostExecute(Result result) {
	}

	public void cancel() {
		cancelled = true;
	}

	public boolean isCancelled() {
		return cancelled;
	}
}
