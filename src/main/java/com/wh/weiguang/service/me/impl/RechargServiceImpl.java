package com.wh.weiguang.service.me.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wh.weiguang.dao.InviteRelationDao;
import com.wh.weiguang.dao.RechargeActivityDao;
import com.wh.weiguang.dao.RechargeDao;
import com.wh.weiguang.dao.UserDetailDao;
import com.wh.weiguang.model.me.InviteRelationEntity;
import com.wh.weiguang.model.me.RechargeRecordEntity;
import com.wh.weiguang.model.me.RechargeRecordModel;
import com.wh.weiguang.model.sys.RechargeActivityEntity;
import com.wh.weiguang.model.sys.UserDetailEntity;
import com.wh.weiguang.service.me.RechargService;
import com.wh.weiguang.service.sys.TichengService;
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
	
	@Autowired
	private TichengService tichengService;
	
	@Autowired
	private UserDetailDao userDetailDao;
	
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
			userService.addMoney(inviteRelationEntity.getInviteid(), money*10/100, "提成");
			inviteRelationEntity.setSign(1);
			inviteRelationDao.setSign(inviteRelationEntity);
		}else if(inviteRelationEntity.getSign() == 1) {
			
			UserDetailEntity userDetailEntity = userDetailDao.getUserDetailByUserid(inviteRelationEntity.getInviteid());
			if(userDetailEntity.getCustomerType() == 1) {
				int proportion = tichengService.getTicheng(1).getProportion();
				userService.addMoney(inviteRelationEntity.getInviteid(), money*proportion/100, "提成");
			}else {
				int proportion = tichengService.getTicheng(0).getProportion();
				userService.addMoney(inviteRelationEntity.getInviteid(), money*proportion/100, "提成");
			}
			
		}
		
	}

	@Override
	public Double getCount1() {
		return rechargeDao.getCount1();
	}

	@Override
	public Double getCount2(String time) {
		return rechargeDao.getCount2(DateUtil.monthFirstday(time),DateUtil.monthLastday(time));
	}

	@Override
	public Double getCount3(String time) {
		return rechargeDao.getCount3(DateUtil.daystart(time),DateUtil.dayend(time));
	}

	@Override
	public List<RechargeRecordModel> rechargesList(int type, int pageSize, int start, String time) {
		if(type == 1) {
			return rechargeDao.getRecharges1(pageSize,start);
		}else if(type == 2) {
			return rechargeDao.getRecharges2(pageSize,start,DateUtil.monthFirstday(time),DateUtil.monthLastday(time));
		}else if(type == 3) {
			return rechargeDao.getRecharges3(pageSize,start,DateUtil.daystart(time),DateUtil.dayend(time));
		}
		return null;
	}

	@Override
	public Integer rechargesSize(int type, String time) {
		if(type == 1) {
			return rechargeDao.getRechargesSize1();
		}else if(type == 2) {
			return rechargeDao.getRechargesSize2(DateUtil.monthFirstday(time),DateUtil.monthLastday(time));
		}else if(type == 3) {
			return rechargeDao.getRechargesSize3(DateUtil.daystart(time),DateUtil.dayend(time));
		}
		return null;
	}

	@Override
	public void orderCreate(RechargeRecordEntity rechargeRecordEntity) {
		rechargeDao.insertOrder(rechargeRecordEntity);
	}

	@Override
	public void orderPaySucc(String id, String orderNumber, String amount) {
		//更改交易订单号和支付状态
		rechargeDao.updateOreder(id,orderNumber);
		
		RechargeRecordEntity rechargeRecordEntity = rechargeDao.getRechargeRecord(id);
		
		takePercentage(rechargeRecordEntity.getUserid(),rechargeRecordEntity.getAmount());

		double money = rechargeRecordEntity.getAmount()+rechargeActivity(rechargeRecordEntity.getAmount());

		if(rechargeRecordEntity.getForm() == 0) {
			userService.addMoney(rechargeRecordEntity.getUserid(), money, "支付宝充值");
		}else if(rechargeRecordEntity.getForm() == 1) {
			userService.addMoney(rechargeRecordEntity.getUserid(), money, "微信充值");
		}
		
		
	}

}
