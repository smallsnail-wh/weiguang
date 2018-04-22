package com.wh.weiguang.service.me.impl;

import java.util.List;

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
	public List<MyAdvertisementEntity> getMyAdvertisementEntity(int userid) {
		return advertisementDao.getMyAdvertisementEntity(userid);
	}

}
