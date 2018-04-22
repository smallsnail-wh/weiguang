package com.wh.weiguang.model.sys;

import java.util.List;

import com.wh.weiguang.model.me.AdvertisementModel;

/**
 * 用户详情功能view
 * @author wanghuan
 *
 */
public class UserDetailModel {

	private Integer id;
	
	private String userName;
	
	private String headimgurl;
	
	private String level;
	
	private List<AdvertisementModel> advertisementModels;

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

	public List<AdvertisementModel> getAdvertisementModels() {
		return advertisementModels;
	}

	public void setAdvertisementModels(List<AdvertisementModel> advertisementModels) {
		this.advertisementModels = advertisementModels;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
}
