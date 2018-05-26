package com.wh.weiguang.service.me.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wh.weiguang.dao.UserLevelDao;
import com.wh.weiguang.model.me.UserLevelEntity;
import com.wh.weiguang.service.me.UserLevelService;

@Service
public class UserLevelServiceImpl implements UserLevelService {

	@Autowired
	private UserLevelDao userLevelDao;
	
	@Override
	public List<UserLevelEntity> userLevelAll() {
		return userLevelDao.userLevelAll();
	}

	@Override
	public List<UserLevelEntity> levelList(int pageSize, int start) {
		// TODO Auto-generated method stub
		return userLevelDao.levelList(pageSize, start);
	}

	@Override
	public Integer levelSize(int pageSize, int start) {
		// TODO Auto-generated method stub
		return userLevelDao.levelSize(pageSize, start);
	}

	@Override
	public void insertLevel(UserLevelEntity levelEntity) {
		userLevelDao.insertLevel(levelEntity);
		
	}

	@Override
	public void updateLevel(UserLevelEntity levelEntity) {
		// TODO Auto-generated method stub
		userLevelDao.updateLevel(levelEntity);
	}

	@Override
	public void deleteLevel(List<String> groupId) {
		// TODO Auto-generated method stub
		userLevelDao.deleteLevel(groupId);
	}

}
