package com.wh.weiguang.login.validate.sms;

public class SmsCodeException extends Exception {

	private static final long serialVersionUID = -3332058572242461106L;

	private String code;
	
	public SmsCodeException(String msg) {
		super(msg);
	}
	
	public SmsCodeException(String message,String code) {
		super(message);
		this.code = code;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

}
