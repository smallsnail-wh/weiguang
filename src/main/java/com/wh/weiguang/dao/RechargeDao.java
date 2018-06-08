package com.wh.weiguang.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.wh.weiguang.model.me.RechargeRecordEntity;
import com.wh.weiguang.model.me.RechargeRecordModel;

@Mapper
public interface RechargeDao {

	public void insert(RechargeRecordEntity rechargeRecordEntity);

	public Double getCount1();

	public Double getCount2(@Param("timeStart") String monthFirstday, @Param("timeEnd") String monthLastday);

	public Double getCount3(@Param("timeStart") String daystart, @Param("timeEnd") String dayend);

	public List<RechargeRecordModel> getRecharges1(@Param("pageSize") int pageSize, @Param("start") int start);

	public Integer getRechargesSize1();

	public List<RechargeRecordModel> getRecharges2(@Param("pageSize") int pageSize, @Param("start") int start,
			@Param("timeStart") String timeStart, @Param("timeEnd") String timeEnd);

	public Integer getRechargesSize2(@Param("timeStart") String timeStart, @Param("timeEnd") String timeEnd);

	public List<RechargeRecordModel> getRecharges3(@Param("pageSize") int pageSize, @Param("start") int start,
			@Param("timeStart") String timeStart, @Param("timeEnd") String timeEnd);

	public Integer getRechargesSize3(@Param("timeStart") String timeStart, @Param("timeEnd") String timeEnd);


}
