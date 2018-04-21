package com.wh.weiguang.controller.me;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wh.weiguang.model.me.MyAdvertisementEntity;
import com.wh.weiguang.service.me.AdvertisementService;
import com.wh.weiguang.util.SecurityAuthenUtil;

@RestController
@RequestMapping("/adv")
public class AdvertisementController {

	@Autowired
	private AdvertisementService advertisementService;
	
	@GetMapping("/myadv")
	public MyAdvertisementEntity getMyAdvertisementEntity() {
		return advertisementService.getMyAdvertisementEntity(SecurityAuthenUtil.getId());
	}
}
