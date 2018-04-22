package com.wh.weiguang.model.sys;

public class UserInfoModel extends UserEntity {

	private UserDetailEntity userDetailEntity;

	public UserDetailEntity getUserDetailEntity() {
		return userDetailEntity;
	}

	public void setUserDetailEntity(UserDetailEntity userDetailEntity) {
		this.userDetailEntity = userDetailEntity;
	}
	
}
