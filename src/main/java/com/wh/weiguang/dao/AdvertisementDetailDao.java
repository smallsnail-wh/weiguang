package com.wh.weiguang.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.wh.weiguang.model.advertise.AdvertisementDetailEntity;

@Mapper
public interface AdvertisementDetailDao {

	AdvertisementDetailEntity getDetailByAdvid(@Param("advid") int advid);

	void reduceSurplus(@Param("id")Integer id);

	void updateAfterGetRP(AdvertisementDetailEntity advertisementDetailEntity);

}
