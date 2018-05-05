package com.wh.weiguang.service.me;

import java.util.List;

import com.wh.weiguang.model.PageEntity;
import com.wh.weiguang.model.me.AdvertisementModel;
import com.wh.weiguang.model.me.MyCollectionEntity;

public interface MyCollectionService {

	public List<AdvertisementModel> getCollectionByUserid(int userid, PageEntity pageEntity);

	public void insertCollection(MyCollectionEntity myCollectionEntity);

	public void deleteCollection(MyCollectionEntity myCollectionEntity);
	
}
