package com.wh.weiguang.dao;

import org.apache.ibatis.annotations.Mapper;

import com.wh.weiguang.model.sys.RechargeActivityEntity;

@Mapper
public interface RechargeActivityDao {

	public RechargeActivityEntity getActivity();
	
}
