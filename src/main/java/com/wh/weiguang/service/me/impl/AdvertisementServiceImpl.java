package com.wh.weiguang.service.me.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wh.weiguang.dao.AdvertisementDao;
import com.wh.weiguang.model.me.MyAdvertisementEntity;
import com.wh.weiguang.service.me.AdvertisementService;

@Service
public class AdvertisementServiceImpl implements AdvertisementService {

	@Autowired
	private AdvertisementDao advertisementDao;
	
	@Override
	public MyAdvertisementEntity getMyAdvertisementEntity(int id) {
		return advertisementDao.getMyAdvertisementEntity(id);
	}

}
