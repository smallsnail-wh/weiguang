package com.wh.weiguang.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.wh.weiguang.model.me.AdvertisementModel;
import com.wh.weiguang.model.me.MyCollectionEntity;

@Mapper
public interface MyCollectionDao {

	public List<AdvertisementModel> getCollectionByUserid(@Param("userid") int userid);

	public void insertCollection(MyCollectionEntity myCollectionEntity);

	public void deleteCollection(MyCollectionEntity myCollectionEntity);

}
