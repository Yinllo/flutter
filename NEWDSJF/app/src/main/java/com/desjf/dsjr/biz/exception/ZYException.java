package com.desjf.dsjr.biz.exception;

import android.util.Log;



public class ZYException extends Exception {

	private static final long serialVersionUID = 153504232797165313L;

	public ZYException(){
		super();
	}
	
	public ZYException(Throwable e){
		super(e);
		Log.e("TAG", "ZYException: "+e.toString() );
	}
	
	public ZYException(String message){
		super(message);
	}
	
	public ZYException(String message, Throwable e){
		super(message, e);
		Log.e("TAG", "ZYException: "+e.toString() );
	}
}
