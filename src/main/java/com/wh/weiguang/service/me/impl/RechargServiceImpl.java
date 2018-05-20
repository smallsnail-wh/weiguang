package com.wh.weiguang.service.me.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wh.weiguang.dao.InviteRelationDao;
import com.wh.weiguang.dao.RechargeActivityDao;
import com.wh.weiguang.dao.RechargeDao;
import com.wh.weiguang.model.me.InviteRelationEntity;
import com.wh.weiguang.model.me.RechargeRecordEntity;
import com.wh.weiguang.model.sys.RechargeActivityEntity;
import com.wh.weiguang.service.me.RechargService;
import com.wh.weiguang.service.sys.UserService;
import com.wh.weiguang.util.DateUtil;

@Service
public class RechargServiceImpl implements RechargService {

	@Autowired
	private RechargeDao rechargeDao;

	@Autowired
	private UserService userService;
	
	@Autowired
	private RechargeActivityDao rechargeActivityDao;
	
	@Autowired
	private InviteRelationDao inviteRelationDao;
	
	@Override
	@Transactional
	public void zhifubaoRecharge(RechargeRecordEntity rechargeRecordEntity) {
		rechargeRecordEntity.setForm(0);
		rechargeRecordEntity.setTime(DateUtil.currentTimestamp());

		rechargeDao.insert(rechargeRecordEntity);
		
		takePercentage(rechargeRecordEntity.getUserid(),rechargeRecordEntity.getAmount());

		double money = rechargeRecordEntity.getAmount()+rechargeActivity(rechargeRecordEntity.getAmount());

		userService.addMoney(rechargeRecordEntity.getUserid(), money, "支付宝充值");
		// userDao.recharge(rechargeRecordEntity.getUserid(), money);

	}

	@Override
	@Transactional
	public void weixinRecharge(RechargeRecordEntity rechargeRecordEntity) {
		rechargeRecordEntity.setForm(1);
		rechargeRecordEntity.setTime(DateUtil.currentTimestamp());

		rechargeDao.insert(rechargeRecordEntity);

		takePercentage(rechargeRecordEntity.getUserid(),rechargeRecordEntity.getAmount());
		
		double money = rechargeRecordEntity.getAmount()+rechargeActivity(rechargeRecordEntity.getAmount());
		
		userService.addMoney(rechargeRecordEntity.getUserid(), money, "微信充值");
		// userDao.recharge(rechargeRecordEntity.getUserid(), money);
	}

	/**
	 * 充值活动额外赠送值
	 * @param money
	 * @return
	 */
	public double rechargeActivity(double money) {
		
		RechargeActivityEntity rechargeActivityEntity = rechargeActivityDao.getActivity();
		if(rechargeActivityEntity == null || rechargeActivityEntity.getForm() == 1 ) {
			return 0;
		}
		
		if(money < rechargeActivityEntity.getReachMoney()) {
			return 0;
		}
		
		return money*rechargeActivityEntity.getProportion()/100;
	}
	
	/**
	 * 推广广告主拿提成
	 * @param id
	 * @param money
	 */
	public void takePercentage(int id ,double money) {
		
		InviteRelationEntity inviteRelationEntity =inviteRelationDao.getInviteidByInvitedid(id);
		
		if(inviteRelationEntity == null) {
			return;
		}
		
		if(inviteRelationEntity.getSign() == 0) {
			userService.addMoney(inviteRelationEntity.getInviteid(), money*10/100, "推广广告主拿的提成");
			inviteRelationEntity.setSign(1);
			inviteRelationDao.setSign(inviteRelationEntity);
		}else if(inviteRelationEntity.getSign() == 1) {
			userService.addMoney(inviteRelationEntity.getInviteid(), money*5/100, "推广广告主拿的提成");
		}
		
	}

	@Override
	public Double getCount1() {
		// TODO Auto-generated method stub
		return rechargeDao.getCount1();
	}

	@Override
	public Double getCount2(String time) {
		// TODO Auto-generated method stub
		return rechargeDao.getCount2(DateUtil.monthFirstday(time),DateUtil.monthLastday(time));
	}

	@Override
	public Double getCount3(String time) {
		// TODO Auto-generated method stub
		return rechargeDao.getCount3(DateUtil.daystart(time),DateUtil.dayend(time));
	}
}
