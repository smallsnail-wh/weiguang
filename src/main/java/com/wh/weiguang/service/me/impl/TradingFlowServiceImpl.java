package com.wh.weiguang.service.me.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wh.weiguang.dao.TradingFlowDao;
import com.wh.weiguang.model.PageEntity;
import com.wh.weiguang.model.me.TradingFlowEntity;
import com.wh.weiguang.service.me.TradingFlowService;

@Service
public class TradingFlowServiceImpl implements TradingFlowService {

	@Autowired
	private TradingFlowDao tradingFlowDao;
	
	@Override
	public List<TradingFlowEntity> getListByUserid(int id, PageEntity pageEntity) {
		return tradingFlowDao.getListByUserid(id,pageEntity);
	}

}
