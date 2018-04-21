package com.wh.weiguang.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.wh.weiguang.model.me.MyAdvertisementEntity;

@Mapper
public interface AdvertisementDao {

	public MyAdvertisementEntity getMyAdvertisementEntity(@Param("id") int id);

}
