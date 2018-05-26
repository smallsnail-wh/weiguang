package com.wh.weiguang.service.me;

import java.util.List;

import com.wh.weiguang.model.me.UserLevelEntity;

public interface UserLevelService {

	public List<UserLevelEntity> userLevelAll();

	public List<UserLevelEntity> levelList(int pageSize, int start);

	public Integer levelSize(int pageSize, int start);

	public void insertLevel(UserLevelEntity levelEntity);

	public void updateLevel(UserLevelEntity levelEntity);

	public void deleteLevel(List<String> groupId);
	
	
}
