package com.wh.weiguang.controller.me;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.wh.weiguang.model.me.UserLevelEntity;
import com.wh.weiguang.model.sys.PageResult;
import com.wh.weiguang.service.me.UserLevelService;

import io.swagger.annotations.ApiOperation;

@RestController
public class UserLevelController {

	@Autowired
	private UserLevelService userLevelService;
	
	@ApiOperation("用户等级规则查询")
	@GetMapping("/level/all")
	public List<UserLevelEntity> userLevelAll(){
		return userLevelService.userLevelAll();
	}
	
	@GetMapping("/admin/leves")
	public PageResult levesList(int pageSize, int page) {
		PageResult pageResult = new PageResult();
		pageResult.setData(userLevelService.levelList(pageSize, page * pageSize));
		pageResult.setTotalCount(userLevelService.levelSize(pageSize, page * pageSize));
		return pageResult;
	}
	
	@PostMapping("/admin/leves/level")
	public UserLevelEntity insertLeve(@RequestBody UserLevelEntity levelEntity) {
		userLevelService.insertLevel(levelEntity);
		return levelEntity;
	}
	
	@PutMapping("/admin/leves/level")
	public UserLevelEntity updateLeve(@RequestBody UserLevelEntity levelEntity) {
		userLevelService.updateLevel(levelEntity);
		return levelEntity;
	}
	
	@DeleteMapping("/admin/leves")
	public List<String> deleteLeves(@RequestBody List<String> groupId) {
		userLevelService.deleteLevel(groupId);
		return groupId;
	}
	
}
