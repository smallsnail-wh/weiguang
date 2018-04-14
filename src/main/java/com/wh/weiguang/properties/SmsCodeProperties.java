package com.wh.weiguang.properties;

public class SmsCodeProperties {

	private int lendth = 6;

	private int expireIn = 60;

	private String url;

	public int getLendth() {
		return lendth;
	}

	public void setLendth(int lendth) {
		this.lendth = lendth;
	}

	public int getExpireIn() {
		return expireIn;
	}

	public void setExpireIn(int expireIn) {
		this.expireIn = expireIn;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

}
