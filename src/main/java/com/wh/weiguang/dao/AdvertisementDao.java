package com.wh.weiguang.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.wh.weiguang.model.advertise.AdvertisementContentEntity;
import com.wh.weiguang.model.advertise.AdvertisementDetailEntity;
import com.wh.weiguang.model.me.AdvertisementEntity;
import com.wh.weiguang.model.me.AdvertisementModel;
import com.wh.weiguang.model.me.MyAdvertisementEntity;

@Mapper
public interface AdvertisementDao {

	public List<MyAdvertisementEntity> getMyAdvertisementEntity(@Param("userid") int userid);
	
	public List<AdvertisementModel> getAdvModelByid(@Param("id") int id);

	public void insertAdv(AdvertisementEntity advertisementEntity);

	public void insertAdvContent(AdvertisementContentEntity advertisementContentEntity);

	public void insertAdvDetail(AdvertisementDetailEntity advertisementDetailEntity);

}
