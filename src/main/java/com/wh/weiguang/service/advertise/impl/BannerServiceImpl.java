package com.wh.weiguang.service.advertise.impl;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.wh.weiguang.dao.BannerDao;
import com.wh.weiguang.model.advertise.BannerEntity;
import com.wh.weiguang.properties.MyProperties;
import com.wh.weiguang.service.advertise.BannerService;
import com.wh.weiguang.util.DateUtil;
import com.wh.weiguang.util.ImageUtil;

@Service
public class BannerServiceImpl implements BannerService {

	@Autowired
	private BannerDao bannerDao;
	
	@Autowired
	private MyProperties myProperties;

	@Override
	public String savePicture(MultipartFile picture) {

		String path = "/banner/" + DateUtil.currentTimes();

		String pictureUrl = null;
		try {
			if (picture != null) {
				String fileName = ImageUtil.saveImg(picture, myProperties.getPathsProperties().getImage() + path);
				pictureUrl = myProperties.getPathsProperties().getDomainName() + path + "/" + fileName;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return pictureUrl;
	}

	@Override
	public List<BannerEntity> getAllBanners() {
		return bannerDao.getAllBanners();
	}

	@Override
	public void insertBanner(BannerEntity bannerEntity) {
		bannerEntity.setTime(DateUtil.currentTimestamp());
		bannerDao.insertBanner(bannerEntity);
	}

	@Override
	public void bannerUpdate(BannerEntity bannerEntity) {
		if(bannerEntity.getId() == null) {
			return;
		}
		bannerEntity.setTime(DateUtil.currentTimestamp());
		bannerDao.bannerUpdate(bannerEntity);
	}

	@Override
	public void deleteBanners(List<String> groupId) {
		bannerDao.deleteBanners(groupId);
	}

}
