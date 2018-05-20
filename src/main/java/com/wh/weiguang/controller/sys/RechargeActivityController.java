package com.wh.weiguang.controller.sys;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wh.weiguang.model.sys.RechargeActivityEntity;
import com.wh.weiguang.service.sys.RechargeActivityuService;

@RestController
public class RechargeActivityController {

	@Autowired
	private RechargeActivityuService rechargeActivityuService;
	
	@GetMapping("/admin/activity")
	public RechargeActivityEntity activityGet() {
		return rechargeActivityuService.getActivity();
	}

	@PutMapping("/admin/activity/form")
	public String changeForm(@RequestParam("form") int form) {
		rechargeActivityuService.changeForm(form);
		return "SUCCESS";
	}
	
	@PutMapping("/admin/activity")
	public RechargeActivityEntity activityUpdate(@RequestBody RechargeActivityEntity rechargeActivityEntity) {
		rechargeActivityuService.activityUpdate(rechargeActivityEntity);
		return rechargeActivityEntity;
	}
	
}
