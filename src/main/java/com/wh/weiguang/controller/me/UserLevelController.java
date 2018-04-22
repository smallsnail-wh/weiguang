package com.wh.weiguang.controller.me;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wh.weiguang.model.me.UserLevelEntity;
import com.wh.weiguang.service.me.UserLevelService;

@RestController
@RequestMapping("/level")
public class UserLevelController {

	@Autowired
	private UserLevelService userLevelService;
	
	@GetMapping("all")
	public List<UserLevelEntity> userLevelAll(){
		return userLevelService.userLevelAll();
	}
	
}
