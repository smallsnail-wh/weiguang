package com.wh.weiguang.controller.advertise;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.wh.weiguang.model.advertise.BannerEntity;
import com.wh.weiguang.service.advertise.BannerService;
import com.wh.weiguang.util.DateUtil;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
public class BannerController {
	
	@Autowired
	private BannerService bannerService;
	
	@ApiOperation("获取轮播图")
	@GetMapping("/public/banners")
	public List<BannerEntity> bannersAll(){
		return bannerService.getAllBanners();
	}
	
	@PostMapping("/admin/banners/banner")
	public BannerEntity bannerInsert(@RequestBody BannerEntity bannerEntity) {
		bannerService.insertBanner(bannerEntity);
		return bannerEntity;
	}
	
	@PutMapping("/admin/banners/banner")
	public BannerEntity bannerUpdate(@RequestBody BannerEntity bannerEntity) {
		bannerService.bannerUpdate(bannerEntity);
		return bannerEntity;
	} 
	
	@DeleteMapping("/admin/banners")
	public List<String> deleteBanners(@RequestBody List<String> groupId) {
		bannerService.deleteBanners(groupId);
		return groupId;
	}
	
	@ApiOperation("上传图片，返回图片地址")
	@PostMapping("/banner/upload/picture")
	public Map<String, String> uploadPicture(@ApiParam("图片") @RequestParam("picture") MultipartFile picture) {

		Map<String, String> resultMap = new HashMap<String, String>();
		resultMap.put("time", DateUtil.currentTimestamp());

		String pictureUrl = bannerService.savePicture(picture);
		resultMap.put("url", pictureUrl);
		return resultMap;
	}

}
