package com.wh.weiguang.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.wh.weiguang.model.sys.WeixinUserInfo;

@Mapper
public interface WeixinUserDao {

	public void delete(@Param("openid") String openid);
	
	public WeixinUserInfo getInfoByOpenid(@Param("openid") String openid);
	
	public void insertInfo(@Param("weixinUserInfo") WeixinUserInfo weixinUserInfo);

	public void updateUserid(@Param("openid") String openid,@Param("userid") int userid);

	public void deleteByUserid(@Param("groupId") List<String> groupId);

}
