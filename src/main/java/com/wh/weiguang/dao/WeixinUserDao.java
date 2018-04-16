package com.wh.weiguang.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.wh.weiguang.model.sys.WeixinUserInfo;

@Mapper
public interface WeixinUserDao {

	public WeixinUserInfo getInfoByOpenid(@Param("openid") String openid);
	
	public void insertInfo(@Param("weixinUserInfo") WeixinUserInfo weixinUserInfo);

}
