package com.wh.weiguang.model.me;

public class RechargeRecordModel extends RechargeRecordEntity{

	private String username ;
	
	private String mobile;
	
	private String weixinid;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getWeixinid() {
		return weixinid;
	}

	public void setWeixinid(String weixinid) {
		this.weixinid = weixinid;
	}

}
