package com.wh.weiguang.controller.sys;

import java.io.IOException;
import java.util.List;

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

	// 下面为修改的========================================================================

	@GetMapping("/user/{loginName}")
	public UserEntity userGet(@PathVariable String loginName) {
		UserEntity userEntity = userService.getUserEntityByLoginName(loginName);
		log.debug("The method is ending");
		return userEntity;
	}

	/**
	 * 获取user表数据
	 * 
	 * @param loginName
	 * @param pageSize
	 * @param page
	 * @return
	 */
	@GetMapping("/users")
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
	@PostMapping("/users/user")
	public UserEntity insertUser(@RequestBody UserEntity userEntity) {
		userService.insertUser(userEntity);
		log.debug("The method is ending");
		return userEntity;
	}

	/**
	 * 删除用户信息
	 * 
	 * @param groupId
	 * @return
	 */
	@DeleteMapping("/users")
	public List<String> deleteUsers(@RequestBody List<String> groupId) {
		userService.deleteUsers(groupId);
		return groupId;
	}
}
