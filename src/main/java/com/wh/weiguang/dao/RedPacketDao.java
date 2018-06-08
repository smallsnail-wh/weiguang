package com.wh.weiguang.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.wh.weiguang.model.money.ReceiveModel;
import com.wh.weiguang.model.money.RedPacketDetailEntity;
import com.wh.weiguang.model.money.RedPacketDetailModel;

@Mapper
public interface RedPacketDao {

	public Integer getCount(@Param("advid") int advid, @Param("userid") int userid);

	void insert(RedPacketDetailEntity redPacketDetailEntity);

	public List<RedPacketDetailModel> getDetailByAdvid(@Param("advid") int advid);

	void secondInsert(RedPacketDetailEntity redPacketDetailEntity);
	
	public Double getCount1();

	public Double getCount2(@Param("timeStart") String monthFirstday, @Param("timeEnd") String monthLastday);

	public Double getCount3(@Param("timeStart") String daystart, @Param("timeEnd") String dayend);

	public List<ReceiveModel> getReceive1(@Param("pageSize") int pageSize, @Param("start") int start);

	public Integer getReceiveSize1();

	public List<ReceiveModel> getReceive2(@Param("pageSize") int pageSize, @Param("start") int start,
			@Param("timeStart") String timeStart, @Param("timeEnd") String timeEnd);

	public Integer getReceiveSize2(@Param("timeStart") String timeStart, @Param("timeEnd") String timeEnd);

	public List<ReceiveModel> getReceive3(@Param("pageSize") int pageSize, @Param("start") int start,
			@Param("timeStart") String timeStart, @Param("timeEnd") String timeEnd);

	public Integer getReceiveSize3(@Param("timeStart") String timeStart, @Param("timeEnd") String timeEnd);

}
