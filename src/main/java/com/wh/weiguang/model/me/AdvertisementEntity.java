package com.wh.weiguang.model.me;

import io.swagger.annotations.ApiModelProperty;

public class AdvertisementEntity {
	
	@ApiModelProperty(value="广告的id")
	private Integer id;
	
	@ApiModelProperty(value="归属人")
	private Integer userid;
	
	@ApiModelProperty(value="标题")
	private String title;
	
	@ApiModelProperty(value="图片的ulr")
	private String imageurl;
	
	@ApiModelProperty(value="发布地区")
	private String area;
	
	@ApiModelProperty(value="发布时间")
	private String time;
	
	@ApiModelProperty(value="点击量")
	private Integer clickRate;
	
	@ApiModelProperty(value="发布地区范围（0：全国，1：城市，2：范围，3：3位，4：4位，5：5位）")
	private Integer scope;
	
	@ApiModelProperty(value="经度")
	private Double lon;
	
	@ApiModelProperty(value="纬度")
	private Double lat;
	
	@ApiModelProperty(value="geohash编码")
	private String geohash;

	@ApiModelProperty(value="下架标志位（0:上架，1下架）")
	private Integer del;
	
	@ApiModelProperty(value="置顶（0:不置顶，1:置顶）")
	private Integer top;
	
	@ApiModelProperty(value="范围距离（单位km）")
	private Integer distance;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getImageurl() {
		return imageurl;
	}

	public void setImageurl(String imageurl) {
		this.imageurl = imageurl;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public Integer getClickRate() {
		return clickRate;
	}

	public void setClickRate(Integer clickRate) {
		this.clickRate = clickRate;
	}

	public Integer getUserid() {
		return userid;
	}

	public void setUserid(Integer userid) {
		this.userid = userid;
	}

	public Integer getScope() {
		return scope;
	}

	public void setScope(Integer scope) {
		this.scope = scope;
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

	public String getGeohash() {
		return geohash;
	}

	public void setGeohash(String geohash) {
		this.geohash = geohash;
	}

	public Integer getDel() {
		return del;
	}

	public void setDel(Integer del) {
		this.del = del;
	}

	public Integer getTop() {
		return top;
	}

	public void setTop(Integer top) {
		this.top = top;
	}

	public Integer getDistance() {
		return distance;
	}

	public void setDistance(Integer distance) {
		this.distance = distance;
	}
	
}
