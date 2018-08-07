package com.desjf.dsjr.biz.exception;

public class BizFailure extends RuntimeException {

	private static final long serialVersionUID = -5995790406608055706L;

	public BizFailure(){
		super();
	}
	
	public BizFailure(String status){
		super(status);
	}
}
