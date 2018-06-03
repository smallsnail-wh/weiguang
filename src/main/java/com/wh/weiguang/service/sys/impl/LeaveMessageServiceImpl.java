package com.wh.weiguang.service.sys.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wh.weiguang.dao.LeaveMessageDao;
import com.wh.weiguang.model.sys.LeaveMessageEntity;
import com.wh.weiguang.service.sys.LeaveMessageService;
import com.wh.weiguang.util.DateUtil;

@Service
public class LeaveMessageServiceImpl implements LeaveMessageService {

	@Autowired
	private LeaveMessageDao leaveMessageDao;

	@Override
	public void insertEntity(LeaveMessageEntity leaveMessageEntity) {
		leaveMessageEntity.setSendTime(DateUtil.currentTimestamp());
		leaveMessageDao.insertEntity(leaveMessageEntity);
	}

	@Override
	public List<LeaveMessageEntity> entityList(int pageSize, int start) {
		return leaveMessageDao.entityList(pageSize,start);
	}

	@Override
	public Integer entitySize() {
		return leaveMessageDao.entitySize();
	}


}
