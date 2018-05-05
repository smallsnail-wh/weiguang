package com.wh.weiguang.controller.me;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wh.weiguang.model.PageEntity;
import com.wh.weiguang.model.me.AdvertisementModel;
import com.wh.weiguang.model.me.MyCollectionEntity;
import com.wh.weiguang.service.me.MyCollectionService;
import com.wh.weiguang.util.SecurityAuthenUtil;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
public class MyCollectionController {

	@Autowired
	private MyCollectionService myCollectionService;

	@ApiOperation("我的收藏")
	@GetMapping("/user/collections")
	public List<AdvertisementModel> collectionGet(
			@ApiParam(value = "分页的参数，每页多少数据") @RequestParam("pageSize") int pageSize,
			@ApiParam(value = "分页的参数，第几页,从0开始") @RequestParam("page") int page) {
		PageEntity pageEntity = new PageEntity(pageSize, page);
		return myCollectionService.getCollectionByUserid(SecurityAuthenUtil.getId(), pageEntity);
	}

	@ApiOperation("收藏")
	@PostMapping("/user/collections")
	public MyCollectionEntity collectionPost(@ApiParam(value = "收藏广告id") @RequestParam("advid") int advid) {
		MyCollectionEntity myCollectionEntity = new MyCollectionEntity();
		myCollectionEntity.setAdvid(advid);
		myCollectionEntity.setUserid(SecurityAuthenUtil.getId());
		myCollectionService.insertCollection(myCollectionEntity);
		return myCollectionEntity;
	}

	@ApiOperation("删除收藏")
	@DeleteMapping("/user/collections")
	public MyCollectionEntity collectionDelete(@ApiParam(value = "收藏广告id") @RequestParam("advid") int advid) {
		MyCollectionEntity myCollectionEntity = new MyCollectionEntity();
		myCollectionEntity.setAdvid(advid);
		myCollectionEntity.setUserid(SecurityAuthenUtil.getId());
		myCollectionService.deleteCollection(myCollectionEntity);
		return myCollectionEntity;
	}

}
