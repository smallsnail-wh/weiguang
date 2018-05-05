package com.wh.weiguang.model.me;

import io.swagger.annotations.ApiModelProperty;

public class MyAttentionModel {

	@ApiModelProperty("关注人id")
	private Integer userid;
	
	@ApiModelProperty("关注人的用户名")
	private String name;
	
	@ApiModelProperty("关注人的头像")
	private String headimgurl;

	public Integer getUserid() {
		return userid;
	}

	public void setUserid(Integer userid) {
		this.userid = userid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getHeadimgurl() {
		return headimgurl;
	}

	public void setHeadimgurl(String headimgurl) {
		this.headimgurl = headimgurl;
	}
	
}
