package com.wh.weiguang.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.wh.weiguang.model.money.RedPacketDetailEntity;
import com.wh.weiguang.model.money.RedPacketDetailModel;

@Mapper
public interface RedPacketDao {

	Integer getCount(@Param("advid") int advid, @Param("userid") int userid);

	void insert(RedPacketDetailEntity redPacketDetailEntity);

	List<RedPacketDetailModel> getDetailByAdvid(@Param("advid") int advid);

	void secondInsert(RedPacketDetailEntity redPacketDetailEntity);
	
	public Double getCount1();

	public Double getCount2(@Param("timeStart") String monthFirstday, @Param("timeEnd") String monthLastday);

	public Double getCount3(@Param("timeStart") String daystart, @Param("timeEnd") String dayend);

}
