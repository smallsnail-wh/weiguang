package com.wh.weiguang.model.advertise;

import java.util.List;

import com.wh.weiguang.model.me.AdvertisementCommentEntity;

import io.swagger.annotations.ApiModelProperty;

public class AdvCommentModel extends AdvertisementCommentEntity {
	
	@ApiModelProperty(value = "评论者用户名")
	private String userName;

	@ApiModelProperty(value = "评论者用户头像")
	private String headimgurl;
	
	@ApiModelProperty(value = "评论的回复")
	private List<AdvCommentModel> childComments;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getHeadimgurl() {
		return headimgurl;
	}

	public void setHeadimgurl(String headimgurl) {
		this.headimgurl = headimgurl;
	}

	public List<AdvCommentModel> getChildComments() {
		return childComments;
	}

	public void setChildComments(List<AdvCommentModel> childComments) {
		this.childComments = childComments;
	}
	
}
