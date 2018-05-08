package com.wh.weiguang.service.sys;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.wh.weiguang.model.sys.UserDetailModel;
import com.wh.weiguang.model.sys.UserEntity;
import com.wh.weiguang.model.sys.UserInfoModel;

public interface UserService {
	
	/**
	 * 邀请注册成功操作
	 * @param inviteCode
	 * @param invitedid
	 */
	public void inviteSuccess(String inviteCode, int invitedid);
	
	public void insert(UserEntity userEntity);

	public void del(UserEntity userEntity);
	
	public UserInfoModel getUserInfoById(int id);
	
	public int getUserExtraVtimes(int userid);
	
	public UserDetailModel getDetailView(int id);
	
	/**
	 * 消费
	 * @param id
	 * @param money
	 * @param describe
	 * @return
	 */
	public boolean consume(int id ,double money,String describe);
	
	/**
	 * 加钱
	 * @param id
	 * @param money
	 * @param describe
	 * @return
	 */
	public boolean addMoney(int id ,double money,String describe);
	
	/**
	 * 更新用户信息
	 * @param headimg
	 * @param name
	 * @return
	 */
	public UserEntity updateUser( MultipartFile headimg, String name);

	/**
	 * 通过登录名得到用户信息
	 * @param loginName
	 * @return
	 */
	public UserEntity getUserEntityByLoginName(String loginName);

	/**
	 * 获取user列表
	 * @param loginName
	 * @param pageSize
	 * @param page
	 * @return
	 */
	public List<UserEntity> usersList(String loginName, int pageSize, int start);

	/**
	 * 获取user列表的总量
	 * @param loginName
	 * @param pageSize
	 * @param page
	 * @return
	 */
	public Integer usersSize(String loginName, int pageSize, int start);

	/**
	 * 新建用户信息
	 * @param userEntity
	 */
	public void insertUser(UserEntity userEntity);

	/**
	 * 更新用户信息
	 * @param userEntity
	 */
	public void updateUser(UserEntity userEntity);

	/**
	 * 删除用户信息
	 * @param groupId
	 */
	public void deleteUsers(List<String> groupId);
	
}
