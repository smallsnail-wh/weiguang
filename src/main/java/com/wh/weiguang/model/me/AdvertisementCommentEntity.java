package com.wh.weiguang.model.me;

import io.swagger.annotations.ApiModelProperty;

public class AdvertisementCommentEntity {

	@ApiModelProperty(value = "无需给值")
	private Integer id;

	@ApiModelProperty(value = "广告id")
	private Integer advid;

	@ApiModelProperty(value = "无需给值")
	private Integer userid;
	
	@ApiModelProperty(value = "如果是回复评论，必填回复评论id")
	private Integer parentid;

	@ApiModelProperty(value = "评论或回复的内容")
	private String comments;

	@ApiModelProperty(value = "用户所在地区")
	private String area;

	@ApiModelProperty(value = "无需给值")
	private Integer pop;

	@ApiModelProperty(value = "无需给值")
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

	public Integer getUserid() {
		return userid;
	}

	public void setUserid(Integer userid) {
		this.userid = userid;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public Integer getPop() {
		return pop;
	}

	public void setPop(Integer pop) {
		this.pop = pop;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public Integer getParentid() {
		return parentid;
	}

	public void setParentid(Integer parentid) {
		this.parentid = parentid;
	}

}
