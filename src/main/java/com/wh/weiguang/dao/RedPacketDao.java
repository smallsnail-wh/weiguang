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

}
