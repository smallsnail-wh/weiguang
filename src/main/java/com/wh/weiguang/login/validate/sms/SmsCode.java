package com.wh.weiguang.login.validate.sms;

public class SmsCode {
	
	private String code;
	
	private int expireTime;
	
	/*private LocalDateTime expireTime;*/

	public SmsCode(String code,int expireIn) {
		this.code = code;
		this.expireTime = expireIn;
	}
	
	/*public SmsCode(String code,int expireIn) {
		this.code = code;
		this.expireTime = LocalDateTime.now().plusSeconds(expireIn);
	}
	
	public SmsCode(String code,LocalDateTime expireTime) {
		this.code = code;
		this.expireTime = expireTime;
	}*/
	
	/*public boolean isExpried() {
		return LocalDateTime.now().isAfter(expireTime);
	}*/

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public int getExpireTime() {
		return expireTime;
	}

	public void setExpireTime(int expireTime) {
		this.expireTime = expireTime;
	}

}
