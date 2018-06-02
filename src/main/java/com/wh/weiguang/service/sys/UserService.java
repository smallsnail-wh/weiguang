package com.wh.weiguang.service.sys;

import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import com.wh.weiguang.model.sys.UserDetailModel;
import com.wh.weiguang.model.sys.UserEntity;
import com.wh.weiguang.model.sys.UserInfoModel;

public interface UserService {
	
	/**
	 * 用户绑定手机号
	 * @param mobile
	 */
	public void bindPhone(String mobile);

	/**
	 * 用户绑定微信
	 * @param code
	 */
	public void bindWeixin(String code);
	
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
	public List<UserInfoModel> usersList(String loginName, int pageSize, int start);

	/**
	 * 获取user列表的总量
	 * @param loginName
	 * @param pageSize
	 * @param page
	 * @return
	 */
	public Integer usersSize(String loginName, int pageSize, int start);
	
	public List<UserEntity> adminusersList(String loginName, int pageSize, int start);

	public Integer adminusersSize(String loginName, int pageSize, int start);

	/**
	 * 新建用户信息
	 * @param userMap
	 */
	public void insertUser(Map<String, String> userMap);

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
	
	public void deleteAdminusers(List<String> groupId);

	public Integer getCount1();

	public Integer getCount2(String time);

	public Integer getCount3(String time);

	public Integer getCount4(String time);

	public Integer getCount5(String time);

	public Integer getCount6();

	public Integer getCount7(String time);

	public Integer getCount8(String time);

	public Integer getCount9();

	public Integer getCount10(String time);

	public Integer getCount11(String time);

	public UserDetailModel getDetailView(String loginName, String mobile);

	public List<UserInfoModel> usersTatiList(int type, int pageSize, int start, String time);

	public Integer usersTatiSize(int type, String time);

	public List<UserInfoModel> publishersList(int type, int pageSize, int start, String time);

	public Integer publishersSize(int type, String time);

	public List<UserInfoModel> ordinaryUsersList(int type, int pageSize, int start, String time);

	public Integer ordinaryUsersSize(int type, String time);

	public List<UserInfoModel> salesmenList(int type, int pageSize, int start, String time);

	public Integer salesmenSize(int type, String time);

}
