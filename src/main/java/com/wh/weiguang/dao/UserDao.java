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
	 * 
	 * @param id
	 */
	public void untie(@Param("id") int id);

	public void updateUserWeixin(@Param("openid") String openid, @Param("id") int id);

	public void updateUserMobile(@Param("mobile") String mobile, @Param("id") int id);

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

	/**
	 * 通过登录名拿到用户信息
	 * 
	 * @return
	 */
	public UserEntity getUserEntityByLoginName(@Param("loginName") String loginName);

	/* 下面的都是未修改的方法 */
	public ArrayList<UserEntity> select(@Param("userEntity") UserEntity userEntity);

	public void del(@Param("userEntity") UserEntity userEntity);

	/**
	 * 获取user列表
	 * 
	 * @param loginName
	 * @param pageSize
	 * @param page
	 * @return
	 */
	public List<UserInfoModel> usersList(@Param("loginName") String loginName, @Param("pageSize") int pageSize,
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

	public ArrayList<UserEntity> adminusersList(@Param("loginName") String loginName, @Param("pageSize") int pageSize,
			@Param("start") int start);

	public Integer adminusersSize(@Param("loginName") String loginName, @Param("pageSize") int pageSize,
			@Param("start") int start);

	/**
	 * 新建用户信息
	 * 
	 * @param userEntity
	 */
	public void insertUser(UserEntity userEntity);

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

	public Integer getCount1();

	public Integer getCount2(@Param("timeStart") String monthFirstday, @Param("timeEnd") String monthLastday);

	public Integer getCount3(@Param("timeStart") String daystart, @Param("timeEnd") String dayend);

	public Integer getCount4(@Param("timeStart") String monthFirstday, @Param("timeEnd") String monthLastday);

	public Integer getCount5(@Param("timeStart") String daystart, @Param("timeEnd") String dayend);

	public void changeCreateTime(@Param("id") int userid, @Param("createTime") String createTime);

	public Integer getCount6();

	public Integer getCount7(@Param("timeStart") String monthFirstday, @Param("timeEnd") String monthLastday);

	public Integer getCount8(@Param("timeStart") String daystart, @Param("timeEnd") String dayend);

	public Integer getCount9();

	public Integer getCount10(@Param("timeStart") String monthFirstday, @Param("timeEnd") String monthLastday);

	public Integer getCount11(@Param("timeStart") String daystart, @Param("timeEnd") String dayend);

	public UserDetailModel getDetByNameOrMobile(@Param("name") String loginName, @Param("mobile") String mobile);

	public List<UserInfoModel> getUserInfo1(@Param("pageSize") int pageSize, @Param("start") int start);

	public Integer getInfoSize1();

	public List<UserInfoModel> getUserInfo2(@Param("pageSize") int pageSize, @Param("start") int start,
			@Param("timeStart") String timeStart, @Param("timeEnd") String timeEnd);

	public Integer getInfoSize2(@Param("timeStart") String timeStart, @Param("timeEnd") String timeEnd);

	public List<UserInfoModel> getUserInfo3(@Param("pageSize") int pageSize, @Param("start") int start,
			@Param("timeStart") String timeStart, @Param("timeEnd") String timeEnd);

	public Integer getInfoSize3(@Param("timeStart") String timeStart, @Param("timeEnd") String timeEnd);

	public List<UserInfoModel> getUserInfo4(@Param("pageSize") int pageSize, @Param("start") int start,
			@Param("timeStart") String timeStart, @Param("timeEnd") String timeEnd);

	public Integer getInfoSize4(@Param("timeStart") String timeStart, @Param("timeEnd") String timeEnd);

	public List<UserInfoModel> getUserInfo5(@Param("pageSize") int pageSize, @Param("start") int start,
			@Param("timeStart") String timeStart, @Param("timeEnd") String timeEnd);

	public Integer getInfoSize5(@Param("timeStart") String timeStart, @Param("timeEnd") String timeEnd);

	public List<UserInfoModel> getPublishers1(@Param("pageSize") int pageSize, @Param("start") int start);

	public Integer getPublishersSize1();

	public List<UserInfoModel> getPublishers2(@Param("pageSize") int pageSize, @Param("start") int start,
			@Param("timeStart") String timeStart, @Param("timeEnd") String timeEnd);

	public Integer getPublishersSize2(@Param("timeStart") String timeStart, @Param("timeEnd") String timeEnd);

	public List<UserInfoModel> getPublishers3(@Param("pageSize") int pageSize, @Param("start") int start,
			@Param("timeStart") String timeStart, @Param("timeEnd") String timeEnd);

	public Integer getPublishersSize3(@Param("timeStart") String timeStart, @Param("timeEnd") String timeEnd);

	public List<UserInfoModel> getOrdiUsers1(@Param("pageSize") int pageSize, @Param("start") int start);

	public Integer getOrdiUsersSize1();

	public List<UserInfoModel> getOrdiUsers2(@Param("pageSize") int pageSize, @Param("start") int start,
			@Param("timeStart") String timeStart, @Param("timeEnd") String timeEnd);

	public Integer getOrdiUsersSize2(@Param("timeStart") String timeStart, @Param("timeEnd") String timeEnd);

	public List<UserInfoModel> getOrdiUsers3(@Param("pageSize") int pageSize, @Param("start") int start,
			@Param("timeStart") String timeStart, @Param("timeEnd") String timeEnd);

	public Integer getOrdiUsersSize3(@Param("timeStart") String timeStart, @Param("timeEnd") String timeEnd);

	public List<UserInfoModel> getSalesmen1(@Param("pageSize") int pageSize, @Param("start") int start);

	public Integer getSalesmenSize1();

	public List<UserInfoModel> getSalesmen2(@Param("pageSize") int pageSize, @Param("start") int start,
			@Param("timeStart") String timeStart, @Param("timeEnd") String timeEnd);

	public Integer getSalesmenSize2(@Param("timeStart") String timeStart, @Param("timeEnd") String timeEnd);

	public List<UserInfoModel> getSalesmen3(@Param("pageSize") int pageSize, @Param("start") int start,
			@Param("timeStart") String timeStart, @Param("timeEnd") String timeEnd);

	public Integer getSalesmenSize3(@Param("timeStart") String timeStart, @Param("timeEnd") String timeEnd);

}
