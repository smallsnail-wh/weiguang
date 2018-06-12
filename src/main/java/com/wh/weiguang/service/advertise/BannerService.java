package com.wh.weiguang.service.advertise;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.wh.weiguang.model.advertise.BannerEntity;

public interface BannerService {

	public String savePicture(MultipartFile picture);

	public List<BannerEntity> getAllBanners();

	public void insertBanner(BannerEntity bannerEntity);

	public void bannerUpdate(BannerEntity bannerEntity);

	public void deleteBanners(List<String> groupId);
	
	
}
