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

}
