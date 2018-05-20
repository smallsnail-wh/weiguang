package com.wh.weiguang.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.wh.weiguang.model.sys.RechargeActivityEntity;

@Mapper
public interface RechargeActivityDao {

	public RechargeActivityEntity getActivity();

	public void changeForm(@Param("form") int form);

	public void activityUpdate(RechargeActivityEntity rechargeActivityEntity);

}
