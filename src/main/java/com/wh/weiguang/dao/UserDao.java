package com.wh.weiguang.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.wh.weiguang.model.sys.UserDetailModel;
import com.wh.weiguang.model.sys.UserEntity;
import com.wh.weiguang.model.sys.UserInfoModel;

@Mapper
public interface UserDao {
	
	/**
	 * 用户彻底解绑（手机和微信）
	 * @param id
	 */
	public void untie(@Param("id") int id);
	
	public void updateUserWeixin(@Param("openid")String openid,@Param("id") int id);

	public void updateUserMobile(@Param("mobile") String mobile,@Param("id") int id);

	public Integer getIdByInvitecode(@Param("inviteCode") String inviteCode);

	public void recharge(@Param("id") int id, @Param("money") double money);

	public void consume(@Param("id") int id, @Param("money") double money);

	public Double getCurrentMoney(@Param("id") int id);

	public UserEntity getUserEntityByMobile(@Param("mobile") String mobile);
	
	public UserEntity getUserByWeixinId(@Param("openid") String openid);

	public void insert(UserEntity userEntity);

	public void insertByWeixin(UserEntity userEntity);

	public void update(UserEntity userEntity);

	public UserInfoModel getUserInfoById(@Param("id") int id);

	public Integer getUserExtraVtimes(@Param("userid") int userid);

	public UserDetailModel getDetailView(@Param("id") int id);

	/**
	 * 通过id拿到用户信息
	 * 
	 * @param loginName
	 * @return
	 */
	public UserEntity getUserEntityById(@Param("id") int id);

	/* 下面的都是未修改的方法 */
	public ArrayList<UserEntity> select(@Param("userEntity") UserEntity userEntity);

	public void del(@Param("userEntity") UserEntity userEntity);

	/**
	 * 通过登录名拿到用户信息
	 * 
	 * @return
	 */
	public UserEntity getUserEntityByLoginName(@Param("loginName") String loginName);

	/**
	 * 获取user列表
	 * 
	 * @param loginName
	 * @param pageSize
	 * @param page
	 * @return
	 */
	public ArrayList<UserEntity> usersList(@Param("loginName") String loginName, @Param("pageSize") int pageSize,
			@Param("start") int start);

	/**
	 * 获取user列表的总量
	 * 
	 * @param loginName
	 * @param pageSize
	 * @param page
	 * @return
	 */
	public Integer usersSize(@Param("loginName") String loginName, @Param("pageSize") int pageSize,
			@Param("start") int start);

	/**
	 * 新建用户信息
	 * 
	 * @param userEntity
	 */
	public void insertUser(@Param("userEntity") UserEntity userEntity);

	/**
	 * 更新用户信息
	 * 
	 * @param userEntity
	 */
	public void updateUser(@Param("userEntity") UserEntity userEntity);

	/**
	 * 删除用户信息
	 * 
	 * @param groupId
	 */
	public void deleteUsers(@Param("groupId") List<String> groupId);

}
