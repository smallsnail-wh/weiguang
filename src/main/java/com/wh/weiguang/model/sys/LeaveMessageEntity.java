package com.wh.weiguang.model.sys;

import io.swagger.annotations.ApiModelProperty;

public class LeaveMessageEntity {
	
	private Integer id;
	
	@ApiModelProperty("姓名")
	private String name;
	
	@ApiModelProperty("邮箱")
	private String email;
	
	@ApiModelProperty("联系方式")
	private String phone;
	
	@ApiModelProperty("留言")
	private String content;
	
	@ApiModelProperty("发送时间")
	private String sendTime;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getSendTime() {
		return sendTime;
	}

	public void setSendTime(String sendTime) {
		this.sendTime = sendTime;
	}
	
}
