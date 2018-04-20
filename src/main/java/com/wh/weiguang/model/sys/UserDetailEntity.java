package com.wh.weiguang.model.sys;

public class UserDetailEntity {

	private Integer id;
	
	private Integer userid;
	
	private Integer level;
	
	private Integer todayVtimes;
	
	private Integer extraVtimes;
	
	private Integer invitationNumber;

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
	
}
