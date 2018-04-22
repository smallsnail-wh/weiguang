package com.wh.weiguang.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.wh.weiguang.model.me.AdvertisementModel;
import com.wh.weiguang.model.me.MyAdvertisementEntity;

@Mapper
public interface AdvertisementDao {

	public List<MyAdvertisementEntity> getMyAdvertisementEntity(@Param("userid") int userid);
	
	public List<AdvertisementModel> getAdvModelByid(@Param("id") int id);

}
