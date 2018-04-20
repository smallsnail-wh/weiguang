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
import com.wh.weiguang.model.sys.UserEntity;
import com.wh.weiguang.service.sys.UserService;

@RestController
/*@PreAuthorize("hasRole('ADMI')")*/
public class UserController {

	private Logger log = LoggerFactory.getLogger(UserController.class);

	@Resource(name = "userServiceImpl")
	private UserService userService;

	/**
	 * 更新用户信息
	 * 
	 * @param userEntity
	 * @param id
	 * @return
	 * @throws IOException 
	 */
	@PutMapping("/users/user")
	public UserEntity updateUser(@RequestParam(value = "headimg",required = false) MultipartFile headimg,@RequestParam(value="name",required = false) String name) throws IOException {
		
		if(headimg == null && name==null) {
			return new UserEntity();
		}
		
		UserEntity userEntity = userService.updateUser(headimg,name);;
		
		log.debug("The method is ending");
		return userEntity;
	}
	
	
	//下面为修改的========================================================================
	
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
