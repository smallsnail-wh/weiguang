package com.wh.weiguang.service.me.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wh.weiguang.dao.MyAttentionDao;
import com.wh.weiguang.model.me.MyAttentionEntity;
import com.wh.weiguang.model.me.MyAttentionModel;
import com.wh.weiguang.service.me.MyAttentionService;
import com.wh.weiguang.util.DateUtil;

@Service
public class MyAttentionServiceImpl implements MyAttentionService {

	@Autowired
	private MyAttentionDao myAttentionDao;
	
	/*@Override
	public List<AdvertisementModel> getCollectionByUserid(int userid) {
		return myCollectionDao.getCollectionByUserid(userid);
	}

	@Override
	public void insertCollection(MyCollectionEntity myCollectionEntity) {
		myCollectionEntity.setTime(DateUtil.currentTimestamp());
		myCollectionDao.insertCollection(myCollectionEntity);
	}

	@Override
	public void deleteCollection(MyCollectionEntity myCollectionEntity) {
		myCollectionDao.deleteCollection(myCollectionEntity);		
	}*/

	@Override
	public List<MyAttentionModel> getAttentionsByUserid(int userid) {
		return myAttentionDao.getAttentionsByUserid(userid);
	}

	@Override
	public void insertAttention(MyAttentionEntity myAttentionEntity) {
		myAttentionEntity.setTime(DateUtil.currentTimestamp());
		myAttentionDao.insertAttention(myAttentionEntity);
	}

	@Override
	public void deleteAttention(MyAttentionEntity myAttentionEntity) {
		myAttentionDao.deleteAttention(myAttentionEntity);	
	}

}
