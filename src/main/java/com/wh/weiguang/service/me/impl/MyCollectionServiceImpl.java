package com.wh.weiguang.service.me.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wh.weiguang.dao.MyCollectionDao;
import com.wh.weiguang.model.PageEntity;
import com.wh.weiguang.model.me.AdvertisementModel;
import com.wh.weiguang.model.me.MyCollectionEntity;
import com.wh.weiguang.service.me.MyCollectionService;
import com.wh.weiguang.util.DateUtil;

@Service
public class MyCollectionServiceImpl implements MyCollectionService {

	@Autowired
	private MyCollectionDao myCollectionDao;
	
	@Override
	public List<AdvertisementModel> getCollectionByUserid(int userid, PageEntity pageEntity) {
		return myCollectionDao.getCollectionByUserid(userid,pageEntity);
	}

	@Override
	public void insertCollection(MyCollectionEntity myCollectionEntity) {
		myCollectionEntity.setTime(DateUtil.currentTimestamp());
		myCollectionDao.insertCollection(myCollectionEntity);
	}

	@Override
	public void deleteCollection(MyCollectionEntity myCollectionEntity) {
		myCollectionDao.deleteCollection(myCollectionEntity);		
	}

}
