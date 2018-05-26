package com.wh.weiguang.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.wh.weiguang.model.me.UserLevelEntity;

@Mapper
public interface UserLevelDao {

	public List<UserLevelEntity> userLevelAll();

	public List<UserLevelEntity> levelList(@Param("pageSize")int pageSize,@Param("start") int start);

	public Integer levelSize(@Param("pageSize")int pageSize,@Param("start") int start);

	public void insertLevel(UserLevelEntity levelEntity);

	public void updateLevel(UserLevelEntity levelEntity);

	public void deleteLevel(@Param("groupId") List<String> groupId);


}
