package com.wh.weiguang.service.advertise.impl;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.wh.weiguang.dao.AdvertisementDao;
import com.wh.weiguang.model.me.MyAdvertisementEntity;
import com.wh.weiguang.properties.MyProperties;
import com.wh.weiguang.service.advertise.AdvertisementService;
import com.wh.weiguang.util.DateUtil;
import com.wh.weiguang.util.ImageUtil;

@Service
public class AdvertisementServiceImpl implements AdvertisementService {

	@Autowired
	private AdvertisementDao advertisementDao;
	
	@Autowired
	private MyProperties myProperties;
	
	@Override
	public List<MyAdvertisementEntity> getMyAdvertisementEntity(int userid) {
		return advertisementDao.getMyAdvertisementEntity(userid);
	}

	@Override
	public String savePicture(MultipartFile picture) {
		
		String path = "/adv/"+DateUtil.currentTimes();
		
		String pictureUrl = null;
		try {
			if(picture != null) {
				String fileName = ImageUtil.saveImg(picture, myProperties.getPathsProperties().getImage()+path);
				pictureUrl = myProperties.getPathsProperties().getDomainName()+path+"/"+fileName;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return pictureUrl;
	}

}
