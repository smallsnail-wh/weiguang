package com.wh.weiguang.model.me;

import io.swagger.annotations.ApiModelProperty;

public class TransferRecordModel extends TransferRecordEntity{

	@ApiModelProperty("用户名")
	private String username;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

}
