package com.wh.weiguang.service.me;

import java.util.List;

import com.wh.weiguang.model.me.MyAdvertisementEntity;

public interface AdvertisementService {
	
	public List<MyAdvertisementEntity> getMyAdvertisementEntity(int userid);
	
}
