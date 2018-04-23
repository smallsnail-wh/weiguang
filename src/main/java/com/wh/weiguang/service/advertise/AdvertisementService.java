package com.wh.weiguang.service.advertise;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.wh.weiguang.model.advertise.AdvertisementReceiveModel;
import com.wh.weiguang.model.me.MyAdvertisementEntity;

public interface AdvertisementService {
	
	public List<MyAdvertisementEntity> getMyAdvertisementEntity(int userid);

	public String savePicture(MultipartFile picture);

	/**
	 * 发布广告
	 * @param advertisementReceiveModel
	 */
	public void advertising(AdvertisementReceiveModel advertisementReceiveModel);
	
}
