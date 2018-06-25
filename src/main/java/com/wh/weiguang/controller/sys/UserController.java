package com.wh.weiguang.controller.sys;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.wh.weiguang.model.sys.PageResult;
import com.wh.weiguang.model.sys.UserDetailModel;
import com.wh.weiguang.model.sys.UserEntity;
import com.wh.weiguang.model.sys.UserInfoModel;
import com.wh.weiguang.service.sys.UserService;
import com.wh.weiguang.util.SecurityAuthenUtil;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
/* @PreAuthorize("hasRole('ADMI')") */
public class UserController {

	private Logger log = LoggerFactory.getLogger(UserController.class);

	@Resource(name = "userServiceImpl")
	private UserService userService;

	@ApiOperation("用户绑定手机号")
	@PutMapping("/users/user/bind/phone")
	public String bindPhone(@ApiParam(value = "手机号") @RequestParam("mobile") String mobile,
			@ApiParam(value = "验证码") @RequestParam("code") String code) {
		userService.bindPhone(mobile);
		return mobile;
	}

	@ApiOperation("用户绑定微信")
	@PutMapping("/users/user/bind/weixin")
	public String bindWeixin(@ApiParam(value = "授权码") @RequestParam("code") String code) {
		userService.bindWeixin(code);
		return "SUCCESS";
	}

	@ApiOperation("用户详情")
	@GetMapping("/users/user/view")
	public UserDetailModel detailViewGet() {
		return userService.getDetailView(SecurityAuthenUtil.getId());
	}

	@GetMapping("/admin/users/query")
	public UserDetailModel detailViewGet(@RequestParam(value = "loginName", required = false) String loginName,
			@RequestParam(value = "mobile", required = false) String mobile) {
		return userService.getDetailView(loginName, mobile);
	}

	/**
	 * 更新用户信息
	 * 
	 * @param userEntity
	 * @param id
	 * @return
	 * @throws IOException
	 */
	@ApiOperation("更新用户信息")
	@PutMapping("/users/user")
	public UserEntity updateUser(
			@ApiParam(value = "头像图片") @RequestParam(value = "headimg", required = false) MultipartFile headimg,
			@ApiParam(value = "用户名称") @RequestParam(value = "name", required = false) String name) throws IOException {

		if (headimg == null && name == null) {
			return new UserEntity();
		}

		UserEntity userEntity = userService.updateUser(headimg, name);
		;

		log.debug("The method is ending");
		return userEntity;
	}

	/**
	 * 得到当前用户完整信息
	 * 
	 * @return
	 */
	@ApiOperation("得到当前用户完整信息")
	@GetMapping("/users/user")
	public UserInfoModel userInfoGet() {
		UserInfoModel userInfoModel = userService.getUserInfoById(SecurityAuthenUtil.getId());
		log.debug("The method is ending");
		return userInfoModel;
	}
	
	@ApiOperation("得到当前用户完整信息")
	@GetMapping("/admin/users/user/{id}")
	public UserInfoModel userInfoGedByID(@PathVariable("id") int id) {
		UserInfoModel userInfoModel = userService.getUserInfoById(id);
		log.debug("The method is ending");
		return userInfoModel;
	}

	@ApiOperation("得到当前用户额外观看次数")
	@GetMapping("/users/user/extravtimes")
	public int userExtraVtimesGet() {
		int times = userService.getUserExtraVtimes(SecurityAuthenUtil.getId());
		log.debug("The method is ending");
		return times;
	}

	@GetMapping("/manage/user")
	public UserEntity userGet() {
		String loginName = SecurityAuthenUtil.getLoginName();
		UserEntity userEntity = userService.getUserEntityByLoginName(loginName);
		log.debug("The method is ending");
		return userEntity;
	}

	// 下面为修改的========================================================================

	/**
	 * 获取user表数据
	 * 
	 * @param loginName
	 * @param pageSize
	 * @param page
	 * @return
	 */
	@GetMapping("/admin/users")
	public PageResult usersList(String loginName, int pageSize, int page) {
		PageResult pageResult = new PageResult();
		pageResult.setData(userService.usersList(loginName, pageSize, page * pageSize));
		pageResult.setTotalCount(userService.usersSize(loginName, pageSize, page * pageSize));
		log.debug("The method is ending");
		return pageResult;
	}
	
	@GetMapping("/admin/adminusers")
	public PageResult adminusersList(String loginName, int pageSize, int page) {
		PageResult pageResult = new PageResult();
		pageResult.setData(userService.adminusersList(loginName, pageSize, page * pageSize));
		pageResult.setTotalCount(userService.adminusersSize(loginName, pageSize, page * pageSize));
		log.debug("The method is ending");
		return pageResult;
	}

	/**
	 * 新建用户信息
	 * 
	 * @param userEntity
	 * @return
	 */
	@PostMapping("/admin/users/user")
	public Map<String, String> insertUser(@RequestBody Map<String, String> userMap) {
		userService.insertUser(userMap);
		log.debug("The method is ending");
		return userMap;
	}

	/**
	 * 删除用户信息
	 * 
	 * @param groupId
	 * @return
	 */
	@DeleteMapping("/admin/users")
	public List<String> deleteUsers(@RequestBody List<String> groupId) {
		userService.deleteUsers(groupId);
		return groupId;
	}
	
	@DeleteMapping("/admin/adminusers")
	public List<String> deleteAdminusers(@RequestBody List<String> groupId) {
		userService.deleteAdminusers(groupId);
		return groupId;
	}

	@GetMapping("/admin/userstatistics")
	public PageResult usersTatisticsList(int type, int pageSize, int page,String time) {
		PageResult pageResult = new PageResult();
		pageResult.setData(userService.usersTatiList(type, pageSize, page * pageSize,time));
		pageResult.setTotalCount(userService.usersTatiSize(type,time));
		log.debug("The method is ending");
		return pageResult;
	}
	
	/**
	 * 用户总量
	 * 
	 * @return
	 */
	@GetMapping("/users/count1")
	public Integer getCount1() {

		return userService.getCount1();
	}

	/**
	 * 月新增
	 * 
	 * @return
	 */
	@GetMapping("/users/count2")
	public Integer getCount2(@RequestParam("time") String time) {

		return userService.getCount2(time);
	}

	/**
	 * 日新增
	 * 
	 * @return
	 */
	@GetMapping("/users/count3")
	public Integer getCount3(@RequestParam("time") String time) {

		return userService.getCount3(time);
	}

	/**
	 * 月活跃率
	 * 
	 * @return
	 */
	@GetMapping("/users/count4")
	public Integer getCount4(@RequestParam("time") String time) {

		return userService.getCount4(time);
	}

	/**
	 * 日活跃率
	 * 
	 * @return
	 */
	@GetMapping("/users/count5")
	public Integer getCount5(@RequestParam("time") String time) {

		return userService.getCount5(time);
	}

	@GetMapping("/admin/publishers")
	public PageResult publishersList(int type, int pageSize, int page,String time) {
		PageResult pageResult = new PageResult();
		pageResult.setData(userService.publishersList(type, pageSize, page * pageSize,time));
		pageResult.setTotalCount(userService.publishersSize(type,time));
		log.debug("The method is ending");
		return pageResult;
	}
	
	@GetMapping("/admin/ordinaryusers")
	public PageResult ordinaryUsersList(int type, int pageSize, int page,String time) {
		PageResult pageResult = new PageResult();
		pageResult.setData(userService.ordinaryUsersList(type, pageSize, page * pageSize,time));
		pageResult.setTotalCount(userService.ordinaryUsersSize(type,time));
		log.debug("The method is ending");
		return pageResult;
	}
	
	@GetMapping("/admin/salesmen")
	public PageResult salesmenList(int type, int pageSize, int page,String time) {
		PageResult pageResult = new PageResult();
		pageResult.setData(userService.salesmenList(type, pageSize, page * pageSize,time));
		pageResult.setTotalCount(userService.salesmenSize(type,time));
		log.debug("The method is ending");
		return pageResult;
	}
	
	/**
	 * 发布用户总量
	 * 
	 * @return
	 */
	@GetMapping("/users/publishers/count1")
	public Integer getCount6() {

		return userService.getCount6();
	}

	/**
	 * 发布月新增
	 * 
	 * @return
	 */
	@GetMapping("/users/publishers/count2")
	public Integer getCount7(@RequestParam("time") String time) {

		return userService.getCount7(time);
	}

	/**
	 * 发布日新增
	 * 
	 * @return
	 */
	@GetMapping("/users/publishers/count3")
	public Integer getCount8(@RequestParam("time") String time) {

		return userService.getCount8(time);
	}

	/**
	 * 普通用户总量
	 * 
	 * @return
	 */
	@GetMapping("/users/ordinaryusers/count1")
	public Integer getCount9() {

		return userService.getCount9();
	}

	/**
	 * 普通月新增
	 * 
	 * @return
	 */
	@GetMapping("/users/ordinaryusers/count2")
	public Integer getCount10(@RequestParam("time") String time) {

		return userService.getCount10(time);
	}

	/**
	 * 普通日新增
	 * 
	 * @return
	 */
	@GetMapping("/users/ordinaryusers/count3")
	public Integer getCount11(@RequestParam("time") String time) {

		return userService.getCount11(time);
	}
}
