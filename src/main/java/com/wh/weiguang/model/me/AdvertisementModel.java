package com.wh.weiguang.model.me;

import io.swagger.annotations.ApiModelProperty;

public class AdvertisementModel extends AdvertisementEntity{

	@ApiModelProperty(value="广告发布者用户名")
	private String userName;
	
	@ApiModelProperty(value="广告发布者用户头像")
	private String headimgurl;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getHeadimgurl() {
		return headimgurl;
	}

	public void setHeadimgurl(String headimgurl) {
		this.headimgurl = headimgurl;
	}
	
}
