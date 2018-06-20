package com.wh.weiguang.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface TransferDao {

	public Double getCount1();

	public Double getCount2(@Param("timeStart") String monthFirstday, @Param("timeEnd") String monthLastday);

	public Double getCount3(@Param("timeStart") String daystart, @Param("timeEnd") String dayend);
	

}
