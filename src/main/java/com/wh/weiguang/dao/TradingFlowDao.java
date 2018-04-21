package com.wh.weiguang.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.wh.weiguang.model.me.TradingFlowEntity;

@Mapper
public interface TradingFlowDao {

	public List<TradingFlowEntity> getListByUserid(@Param("userid") int userid);


}
