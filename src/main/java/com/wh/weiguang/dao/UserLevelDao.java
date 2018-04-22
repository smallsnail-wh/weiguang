package com.wh.weiguang.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.wh.weiguang.model.me.UserLevelEntity;

@Mapper
public interface UserLevelDao {

	public List<UserLevelEntity> userLevelAll();


}
