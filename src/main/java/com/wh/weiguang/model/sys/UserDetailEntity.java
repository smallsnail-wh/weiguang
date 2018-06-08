package com.wh.weiguang.model.sys;

import io.swagger.annotations.ApiModelProperty;

public class UserDetailEntity {

	private Integer id;
	
	private Integer userid;
	
	@ApiModelProperty("等级")
	private Integer level;
	
	@ApiModelProperty("今日观看次数")
	private Integer todayVtimes;
	
	@ApiModelProperty("额外观看次数")
	private Integer extraVtimes;
	
	@ApiModelProperty("邀请人数")
	private Integer invitationNumber;
	
	@ApiModelProperty("0:普通用户，1：全职销售员，2：发布用户，3：系统管理员")
	private Integer customerType;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getUserid() {
		return userid;
	}

	public void setUserid(Integer userid) {
		this.userid = userid;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public Integer getTodayVtimes() {
		return todayVtimes;
	}

	public void setTodayVtimes(Integer todayVtimes) {
		this.todayVtimes = todayVtimes;
	}

	public Integer getExtraVtimes() {
		return extraVtimes;
	}

	public void setExtraVtimes(Integer extraVtimes) {
		this.extraVtimes = extraVtimes;
	}

	public Integer getInvitationNumber() {
		return invitationNumber;
	}

	public void setInvitationNumber(Integer invitationNumber) {
		this.invitationNumber = invitationNumber;
	}

	public Integer getCustomerType() {
		return customerType;
	}

	public void setCustomerType(Integer customerType) {
		this.customerType = customerType;
	}
	
}
