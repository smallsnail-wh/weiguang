package com.wh.weiguang.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.wh.weiguang.model.me.TransferRecordEntity;
import com.wh.weiguang.model.me.TransferRecordModel;

@Mapper
public interface TransferDao {

	public Double getCount1();

	public Double getCount2(@Param("timeStart") String monthFirstday, @Param("timeEnd") String monthLastday);

	public Double getCount3(@Param("timeStart") String daystart, @Param("timeEnd") String dayend);

	public void insertTransferRecord(TransferRecordEntity transferRecordEntity);

	public List<TransferRecordModel> transferList(@Param("pageSize") int pageSize, @Param("start") int start);

	public Integer transferSize();

	public TransferRecordEntity getTransferRecordById(@Param("pageSize") String id);

}
