package com.wh.weiguang.controller.advertise;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.wh.weiguang.model.me.MyAdvertisementEntity;
import com.wh.weiguang.service.advertise.AdvertisementService;
import com.wh.weiguang.util.DateUtil;
import com.wh.weiguang.util.SecurityAuthenUtil;

@RestController
@RequestMapping("/adv")
public class AdvertisementController {

	@Autowired
	private AdvertisementService advertisementService;
	
	@GetMapping("/myadv")
	public List<MyAdvertisementEntity> getMyAdvertisementEntity() {
		return advertisementService.getMyAdvertisementEntity(SecurityAuthenUtil.getId());
	}
	
	@PostMapping("/upload/picture")
	public Map<String, String>  uploadPicture(@RequestParam("picture") MultipartFile picture) {
		
		Map<String,String> resultMap = new HashMap<String,String>();
		resultMap.put("time", DateUtil.currentTimestamp());
		
		String pictureUrl = advertisementService.savePicture(picture);
		resultMap.put("url", pictureUrl);
		return resultMap;
	}
}
