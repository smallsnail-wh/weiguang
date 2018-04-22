package com.wh.weiguang.controller.me;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.wh.weiguang.model.me.AdvertisementModel;
import com.wh.weiguang.model.me.MyCollectionEntity;
import com.wh.weiguang.service.me.MyCollectionService;
import com.wh.weiguang.util.SecurityAuthenUtil;

@RestController
public class MyCollectionController {

	@Autowired
	private MyCollectionService myCollectionService;
	
	@GetMapping("/user/collections")
	public List<AdvertisementModel> collectionGet() {
		return myCollectionService.getCollectionByUserid(SecurityAuthenUtil.getId());
	}
	
	@PostMapping("/user/collections")
	public MyCollectionEntity collectionPost(@RequestBody MyCollectionEntity myCollectionEntity) {
		myCollectionEntity.setUserid(SecurityAuthenUtil.getId());
		myCollectionService.insertCollection(myCollectionEntity);
		return myCollectionEntity;
	}
	
	@DeleteMapping("/user/collections")
	public MyCollectionEntity collectionDelete(@RequestBody MyCollectionEntity myCollectionEntity) {
		myCollectionEntity.setUserid(SecurityAuthenUtil.getId());
		myCollectionService.deleteCollection(myCollectionEntity);
		return myCollectionEntity;
	}
	
}
