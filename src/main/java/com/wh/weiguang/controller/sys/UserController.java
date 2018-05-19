package com.wh.weiguang.controller.sys;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
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

	/**
	 * 新建用户信息
	 * 
	 * @param userEntity
	 * @return
	 */
	@PostMapping("/admin/users/user")
	public Map<String,String> insertUser(@RequestBody Map<String,String> userMap) {
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
	
	/**
	 * 用户总量
	 * @return
	 */
	@GetMapping("/users/count1")
	public Integer getCount1() {
		
		return userService.getCount1();
	}
	
	/**
	 * 月新增
	 * @return
	 */
	@GetMapping("/users/count2")
	public Integer getCount2(@RequestParam("time") String time) {
		
		return userService.getCount2(time);
	}
	
	/**
	 * 日新增
	 * @return
	 */
	@GetMapping("/users/count3")
	public Integer getCount3(@RequestParam("time") String time) {
		
		return userService.getCount3(time);
	}
	
	/**
	 * 月活跃率
	 * @return
	 */
	@GetMapping("/users/count4")
	public Integer getCount4(@RequestParam("time") String time) {
		
		return userService.getCount4(time);
	}
	
	/**
	 * 日活跃率
	 * @return
	 */
	@GetMapping("/users/count5")
	public Integer getCount5(@RequestParam("time") String time) {
		
		return userService.getCount5(time);
	}
}
