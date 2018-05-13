package com.wh.weiguang.exception;

public class RedPacketException extends RuntimeException {

	private static final long serialVersionUID = -2226382746049743L;

	private String code;

	public RedPacketException(String message,String code) {
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
