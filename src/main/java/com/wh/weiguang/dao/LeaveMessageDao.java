package com.wh.weiguang.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.wh.weiguang.model.sys.LeaveMessageEntity;

@Mapper
public interface LeaveMessageDao {

	public void insertEntity(LeaveMessageEntity leaveMessageEntity);

	public List<LeaveMessageEntity> entityList(@Param("pageSize")int pageSize,@Param("start") int start);

	public Integer entitySize();
	
}
