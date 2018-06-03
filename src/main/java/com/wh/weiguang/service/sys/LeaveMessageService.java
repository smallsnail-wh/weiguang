package com.wh.weiguang.service.sys;

import java.util.List;

import com.wh.weiguang.model.sys.LeaveMessageEntity;

public interface LeaveMessageService {

	public void insertEntity(LeaveMessageEntity leaveMessageEntity);

	public List<LeaveMessageEntity> entityList(int pageSize, int start);

	public Integer entitySize();

}
