package com.wh.weiguang.service.sys.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wh.weiguang.dao.SecondRedPacketDao;
import com.wh.weiguang.model.sys.SecondRedPacketEntity;
import com.wh.weiguang.service.sys.SecondRedPacketService;

@Service
public class SecondRedPacketServiceImpl implements SecondRedPacketService {

	@Autowired
	private SecondRedPacketDao secondRedPacketDao;

	@Override
	public SecondRedPacketEntity getEntity() {
		// TODO Auto-generated method stub
		return secondRedPacketDao.getSecondRedPacket();
	}

	@Override
	public void entityUpdate(SecondRedPacketEntity secondRedPacketEntity) {
		// TODO Auto-generated method stub
		secondRedPacketDao.entityUpdate(secondRedPacketEntity);
	}

}
