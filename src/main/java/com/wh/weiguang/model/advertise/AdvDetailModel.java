package com.wh.weiguang.model.advertise;

import io.swagger.annotations.ApiModelProperty;

public class AdvDetailModel extends AdvertisementDetailEntity{
	
	@ApiModelProperty(value = "点击量")
	private Integer clickRate;
	
	@ApiModelProperty(value = "评论量")
	private Integer commentAmount;
	
	@ApiModelProperty(value = "发布地区范围（0：全国，1：城市，2：1公里内，3：5公里内）")
	private Integer scope;
	
	@ApiModelProperty(value = "发布地区")
	private String area;
	
	@ApiModelProperty(value = "经度")
	private Double lon;
	
	@ApiModelProperty(value = "纬度")
	private Double lat;

	public Integer getClickRate() {
		return clickRate;
	}

	public void setClickRate(Integer clickRate) {
		this.clickRate = clickRate;
	}

	public Integer getCommentAmount() {
		return commentAmount;
	}

	public void setCommentAmount(Integer commentAmount) {
		this.commentAmount = commentAmount;
	}

	public Integer getScope() {
		return scope;
	}

	public void setScope(Integer scope) {
		this.scope = scope;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public Double getLon() {
		return lon;
	}

	public void setLon(Double lon) {
		this.lon = lon;
	}

	public Double getLat() {
		return lat;
	}

	public void setLat(Double lat) {
		this.lat = lat;
	}
	
	
}
