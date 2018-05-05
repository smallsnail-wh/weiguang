package com.wh.weiguang.model.money;

import io.swagger.annotations.ApiModelProperty;

public class RedPacketDetailModel extends RedPacketDetailEntity {
	
	@ApiModelProperty(value = "用户名")
	private String userName;

	@ApiModelProperty(value = "用户头像")
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
