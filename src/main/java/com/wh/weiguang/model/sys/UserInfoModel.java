package com.wh.weiguang.model.sys;

public class UserInfoModel extends UserEntity {

	private String level;
	
	private UserDetailEntity userDetailEntity;

	public UserDetailEntity getUserDetailEntity() {
		return userDetailEntity;
	}

	public void setUserDetailEntity(UserDetailEntity userDetailEntity) {
		this.userDetailEntity = userDetailEntity;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}
	
}
