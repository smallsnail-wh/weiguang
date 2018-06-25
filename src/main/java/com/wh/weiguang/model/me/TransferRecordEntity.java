package com.wh.weiguang.model.me;

import io.swagger.annotations.ApiModelProperty;

public class TransferRecordEntity {

	@ApiModelProperty("商户订单号")
	private String id;

	@ApiModelProperty("交易订单号（支付宝转账单据号）")
	private String orderNumber;

	@ApiModelProperty("用户id")
	private Integer userid;

	@ApiModelProperty("金额")
	private Double amount;
	
	@ApiModelProperty("提现的手机号")
	private String mobile;

	@ApiModelProperty("是否支付成功（0：未成功，1:成功）")
	private Integer succ;

	@ApiModelProperty("充值时间")
	private String time;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}

	public Integer getUserid() {
		return userid;
	}

	public void setUserid(Integer userid) {
		this.userid = userid;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public Integer getSucc() {
		return succ;
	}

	public void setSucc(Integer succ) {
		this.succ = succ;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

}
