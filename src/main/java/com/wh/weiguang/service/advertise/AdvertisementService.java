package com.wh.weiguang.service.advertise;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.wh.weiguang.model.me.MyAdvertisementEntity;

public interface AdvertisementService {
	
	public List<MyAdvertisementEntity> getMyAdvertisementEntity(int userid);

	public String savePicture(MultipartFile picture);
	
}
