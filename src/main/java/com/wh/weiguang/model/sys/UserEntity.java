package com.wh.weiguang.model.sys;

import io.swagger.annotations.ApiModelProperty;

/**
 * @author wanghuan
 *
 */
public class UserEntity {
	/**
	 * id
	 */
	@ApiModelProperty("id")
	private int id;

	/**
	 * 昵称
	 */
	@ApiModelProperty("昵称")
	private String name;
	/**
	 * 密码
	 */
	@ApiModelProperty("密码")
	private String password;
	/**
	 * 邮箱
	 */
	@ApiModelProperty("邮箱")
	private String email;
	/**
	 * 头像
	 */
	@ApiModelProperty("头像")
	private String headimgurl;
	/**
	 * 微信的openid
	 */
	@ApiModelProperty("微信的openid")
	private String weixinId;
	/**
	 * 手机号
	 */
	@ApiModelProperty("手机号")
	private String mobile;
	/**
	 * 账号余额
	 */
	@ApiModelProperty("账号余额")
	private Double money;
	/**
	 * 邀请码
	 */
	@ApiModelProperty("邀请码")
	private String inviteCode;
	/**
	 * 用户注册时间
	 */
	@ApiModelProperty("用户注册时间")
	private String createTime;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getHeadimgurl() {
		return headimgurl;
	}

	public void setHeadimgurl(String headimgurl) {
		this.headimgurl = headimgurl;
	}

	public String getWeixinId() {
		return weixinId;
	}

	public void setWeixinId(String weixinId) {
		this.weixinId = weixinId;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public Double getMoney() {
		return money;
	}

	public void setMoney(Double money) {
		this.money = money;
	}

	public String getInviteCode() {
		return inviteCode;
	}

	public void setInviteCode(String inviteCode) {
		this.inviteCode = inviteCode;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

}
