package com.wh.weiguang.dao;

import org.apache.ibatis.annotations.Mapper;

import com.wh.weiguang.model.me.RechargeRecordEntity;

@Mapper
public interface RechargeDao {

	public void insert(RechargeRecordEntity rechargeRecordEntity);


}
