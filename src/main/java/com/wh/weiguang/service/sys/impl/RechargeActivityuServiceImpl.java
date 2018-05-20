package com.wh.weiguang.service.sys.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wh.weiguang.dao.RechargeActivityDao;
import com.wh.weiguang.model.sys.RechargeActivityEntity;
import com.wh.weiguang.service.sys.RechargeActivityuService;

@Service
public class RechargeActivityuServiceImpl implements RechargeActivityuService {

	@Autowired
	private RechargeActivityDao rechargeActivityDao;

	@Override
	public RechargeActivityEntity getActivity() {
		// TODO Auto-generated method stub
		return rechargeActivityDao.getActivity();
	}

	@Override
	public void changeForm(int form) {
		// TODO Auto-generated method stub
		rechargeActivityDao.changeForm(form);
	}

	@Override
	public void activityUpdate(RechargeActivityEntity rechargeActivityEntity) {
		// TODO Auto-generated method stub
		rechargeActivityDao.activityUpdate(rechargeActivityEntity);
	}

}
