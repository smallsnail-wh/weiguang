package com.wh.weiguang.model.me;

public class MyAdvertisementEntity extends AdvertisementEntity {
	/**
	 * 红包剩余量
	 */
	private Integer surplus;
	/**
	 * 评论量
	 */
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
