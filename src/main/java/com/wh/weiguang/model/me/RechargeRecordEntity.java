package com.wh.weiguang.model.me;

import io.swagger.annotations.ApiModelProperty;

public class RechargeRecordEntity {

	@ApiModelProperty("流水id")
	private Integer id;
	
	@ApiModelProperty("交易订单号")
	private String orderNumber;
	
	@ApiModelProperty("用户id")
	private Integer userid;
	
	@ApiModelProperty("金额")
	private Double amount;
	
	@ApiModelProperty("充值方式（0：支付宝，1：微信）")
	private Integer form;
	
	@ApiModelProperty("充值时间")
	private String time;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
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

	public Integer getForm() {
		return form;
	}

	public void setForm(Integer form) {
		this.form = form;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

}
