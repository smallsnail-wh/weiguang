package com.wh.weiguang.model.me;

import io.swagger.annotations.ApiModelProperty;

public class InviteRelationEntity {

	@ApiModelProperty("邀请人id")
	private Integer inviteid;
	
	@ApiModelProperty("被邀请人id")
	private Integer invitedid;
	
	@ApiModelProperty("邀请时间")
	private String time;

	@ApiModelProperty("0:未完成首冲，1：完成首冲")
	private Integer sign;
	
	public Integer getInviteid() {
		return inviteid;
	}

	public void setInviteid(Integer inviteid) {
		this.inviteid = inviteid;
	}

	public Integer getInvitedid() {
		return invitedid;
	}

	public void setInvitedid(Integer invitedid) {
		this.invitedid = invitedid;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public Integer getSign() {
		return sign;
	}

	public void setSign(Integer sign) {
		this.sign = sign;
	}

}
