package com.wh.weiguang.model.me;

import io.swagger.annotations.ApiModelProperty;

public class MyAdvertisementEntity extends AdvertisementEntity {
	/**
	 * 红包剩余量
	 */
	@ApiModelProperty("红包剩余量")
	private Integer surplus;
	/**
	 * 评论量
	 */
	@ApiModelProperty("评论量")
	private Integer commentAmount;

	public Integer getSurplus() {
		return surplus;
	}

	public void setSurplus(Integer surplus) {
		this.surplus = surplus;
	}

	public Integer getCommentAmount() {
		return commentAmount;
	}

	public void setCommentAmount(Integer commentAmount) {
		this.commentAmount = commentAmount;
	}

}
