package com.wh.weiguang.service.me.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wh.weiguang.dao.RechargeDao;
import com.wh.weiguang.model.me.RechargeRecordEntity;
import com.wh.weiguang.service.me.RechargService;
import com.wh.weiguang.util.DateUtil;

@Service
public class RechargServiceImpl implements RechargService {

	@Autowired
	private RechargeDao rechargeDao;

	@Override
	@Transactional
	public void zhifubaoRecharge(RechargeRecordEntity rechargeRecordEntity) {
		rechargeRecordEntity.setForm(0);
		rechargeRecordEntity.setTime(DateUtil.currentTimestamp());
	}

	@Override
	@Transactional
	public void weixinRecharge(RechargeRecordEntity rechargeRecordEntity) {
		rechargeRecordEntity.setForm(1);
		rechargeRecordEntity.setTime(DateUtil.currentTimestamp());
	}
	
}
