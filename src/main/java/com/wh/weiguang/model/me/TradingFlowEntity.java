package com.wh.weiguang.model.me;

import io.swagger.annotations.ApiModelProperty;

public class TradingFlowEntity {

	@ApiModelProperty("流水id")
	private Integer id;
	
	@ApiModelProperty("用户id")
	private Integer userid;
	
	@ApiModelProperty("金额")
	private Double amount;
	
	@ApiModelProperty("收支标志（0：收款，1：支付）")
	private Integer sign;
	
	@ApiModelProperty("交易时间")
	private String time;
	
	@ApiModelProperty("交易描述")
	private String describe;

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

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public Integer getSign() {
		return sign;
	}

	public void setSign(Integer sign) {
		this.sign = sign;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getDescribe() {
		return describe;
	}

	public void setDescribe(String describe) {
		this.describe = describe;
	}
	
}
