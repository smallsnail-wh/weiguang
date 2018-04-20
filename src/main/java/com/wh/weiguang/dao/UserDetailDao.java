package com.wh.weiguang.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.wh.weiguang.model.sys.UserDetailEntity;

@Mapper
public interface UserDetailDao {

	public void insert(@Param("userDetailEntity") UserDetailEntity userDetailEntity);
	
}
