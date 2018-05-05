package com.wh.weiguang.model.me;

import io.swagger.annotations.ApiModelProperty;

public class MyAttentionEntity {

	@ApiModelProperty("用户id")
	private Integer userid;
	
	@ApiModelProperty("关注人id")
	private Integer attentionid;
	
	@ApiModelProperty("关注的时间")
	private String time;

	public Integer getUserid() {
		return userid;
	}

	public void setUserid(Integer userid) {
		this.userid = userid;
	}

	public Integer getAttentionid() {
		return attentionid;
	}

	public void setAttentionid(Integer attentionid) {
		this.attentionid = attentionid;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}
	
}
