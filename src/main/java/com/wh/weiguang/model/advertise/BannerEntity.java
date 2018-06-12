package com.wh.weiguang.model.advertise;

import io.swagger.annotations.ApiModelProperty;

public class BannerEntity {
	
	@ApiModelProperty(value="id")
	private Integer id;
	
	@ApiModelProperty(value="广告id")
	private Integer advid;
	
	@ApiModelProperty(value="图片url")
	private String imageurl;
	
	@ApiModelProperty(value="发布时间")
	private String time;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getAdvid() {
		return advid;
	}

	public void setAdvid(Integer advid) {
		this.advid = advid;
	}

	public String getImageurl() {
		return imageurl;
	}

	public void setImageurl(String imageurl) {
		this.imageurl = imageurl;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}
	
}
