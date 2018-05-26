package com.wh.weiguang.dao;

import org.apache.ibatis.annotations.Mapper;

import com.wh.weiguang.model.sys.SecondRedPacketEntity;

@Mapper
public interface SecondRedPacketDao {

	public SecondRedPacketEntity getSecondRedPacket();

	public void entityUpdate(SecondRedPacketEntity secondRedPacketEntity);
	
}
