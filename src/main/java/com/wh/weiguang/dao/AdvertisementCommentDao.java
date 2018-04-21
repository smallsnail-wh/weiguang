package com.wh.weiguang.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface AdvertisementCommentDao {

	public Integer getCommentAmountByDevid(@Param("devid") int devid);

}
