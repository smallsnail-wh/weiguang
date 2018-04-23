package com.wh.weiguang.model.advertise;

public class AdvertisementReceiveModel {

	/**
	 * 标题
	 */
	private String title;

	/**
	 * 显示图片
	 */
	private String imageurl;

	/**
	 * 广告形式（0：url，1：内容）
	 */
	private Integer form;
	/**
	 * 正文
	 */
	private String content;
	/**
	 * 正确关键字
	 */
	private String correctKeywords;
	/**
	 * 错误关键字
	 */
	private String error_keywords;
	/**
	 * 发布地区
	 */
	private String area;
	/**
	 * 发布地区范围（0：全国，1：城市，2：1公里内，3：5公里内）
	 */
	private Integer scope;
	/**
	 * 经度
	 */
	private Double lon;
	/**
	 * 纬度
	 */
	private Double lat;
	/**
	 * 红包总数量
	 */
	private Integer total;
	/**
	 * 金额
	 */
	private Double money;

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

	public Integer getForm() {
		return form;
	}

	public void setForm(Integer form) {
		this.form = form;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getCorrectKeywords() {
		return correctKeywords;
	}

	public void setCorrectKeywords(String correctKeywords) {
		this.correctKeywords = correctKeywords;
	}

	public String getError_keywords() {
		return error_keywords;
	}

	public void setError_keywords(String error_keywords) {
		this.error_keywords = error_keywords;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
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

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

	public Double getMoney() {
		return money;
	}

	public void setMoney(Double money) {
		this.money = money;
	}

}
