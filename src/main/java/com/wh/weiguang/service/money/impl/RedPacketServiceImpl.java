package com.wh.weiguang.service.money.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wh.weiguang.dao.AdvertisementDetailDao;
import com.wh.weiguang.dao.RedPacketDao;
import com.wh.weiguang.dao.SecondRedPacketDao;
import com.wh.weiguang.dao.UserDao;
import com.wh.weiguang.dao.UserDetailDao;
import com.wh.weiguang.exception.OverVtimesException;
import com.wh.weiguang.exception.RedPacketException;
import com.wh.weiguang.model.advertise.AdvDetailModel;
import com.wh.weiguang.model.advertise.AdvertisementDetailEntity;
import com.wh.weiguang.model.money.RedPacketDetailEntity;
import com.wh.weiguang.model.money.RedPacketDetailModel;
import com.wh.weiguang.model.sys.SecondRedPacketEntity;
import com.wh.weiguang.model.sys.UserDetailEntity;
import com.wh.weiguang.model.sys.UserEntity;
import com.wh.weiguang.service.advertise.AdvertisementService;
import com.wh.weiguang.service.money.RedPacketService;
import com.wh.weiguang.service.sys.UserService;
import com.wh.weiguang.util.DateUtil;
import com.wh.weiguang.util.RedPacketUtil;
import com.wh.weiguang.util.SecurityAuthenUtil;

@Service
public class RedPacketServiceImpl implements RedPacketService {

	@Autowired
	private UserDao userDao;
	
	@Autowired
	private UserDetailDao userDetailDao;

	@Autowired
	private RedPacketDao redPacketDao;

	@Autowired
	private AdvertisementDetailDao advertisementDetailDao;
	
	@Autowired
	private SecondRedPacketDao secondRedPacketDao;

	/*@Autowired
	private UserDao userDao;*/
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private AdvertisementService advertisementService;

	@Override
	@Transactional
	public List<AdvDetailModel> readyGrabRedPacket(int advid) {
		
		int userid = SecurityAuthenUtil.getId();
		UserEntity userEntity = userDao.getUserEntityById(userid);
		if(userEntity.getMobile() == null || "".equals(userEntity.getMobile())) {
			new RedPacketException("前红包必须先绑定手机号", "1004");
		}
		
		return advertisementService.getAdvDetail(advid);
	}
	
	@Override
	@Transactional
	public RedPacketDetailEntity grabRedPacket(int advid) {

		int userid = SecurityAuthenUtil.getId();

		UserDetailEntity userDetailEntity = userDetailDao.getUserDetailByUserid(userid);
		if (userDetailEntity.getTodayVtimes() != 0) {
			userDetailDao.reduceTodayVtimes(userid);
		} else if (userDetailEntity.getExtraVtimes() != 0) {
			userDetailDao.reduceExtraVtimes(userid);
		} else {
			throw new OverVtimesException("缺少观看次数");
		}

		int redPacketRecord = redPacketDao.getCount(advid, userid);
		if (redPacketRecord != 0) {
			throw new RedPacketException("重复领取","1001");
		}

		AdvertisementDetailEntity advertisementDetailEntity = advertisementDetailDao.getDetailByAdvid(advid);
		if (advertisementDetailEntity.getSurplus() <= 0) {
			throw new RedPacketException("红包以领取完","1002");
		}
		
		double money = getMoney(advertisementDetailEntity);
		
		advertisementDetailEntity.setMoney(advertisementDetailEntity.getMoney()-money);
		advertisementDetailEntity.setSurplus(advertisementDetailEntity.getSurplus()-1);
		
		advertisementDetailDao.updateAfterGetRP(advertisementDetailEntity);
		
		/*advertisementDetailDao.reduceSurplus(advertisementDetailEntity.getId());*/
		/*double money = getMoney(advertisementDetailEntity);*/
		
		userService.addMoney(userid, money, "抢红包");
		//userDao.recharge(userid, money);

		RedPacketDetailEntity redPacketDetailEntity = new RedPacketDetailEntity();
		redPacketDetailEntity.setAdvid(advid);
		redPacketDetailEntity.setUserid(userid);
		redPacketDetailEntity.setMoney(money);
		redPacketDetailEntity.setTime(DateUtil.currentTimestamp());
		redPacketDao.insert(redPacketDetailEntity);

		return redPacketDetailEntity;
	}

	public double getMoney(AdvertisementDetailEntity advertisementDetailEntity) {

		return RedPacketUtil.getRandomMoney(advertisementDetailEntity.getSurplus(), advertisementDetailEntity.getMoney());
		
	}

	@Override
	public List<RedPacketDetailModel> getDetailByAdvid(int advid) {
		return redPacketDao.getDetailByAdvid(advid);
	}

	@Override
	public RedPacketDetailEntity grabSecondRedPacket(int advid) {

		int userid = SecurityAuthenUtil.getId();

		/*UserDetailEntity userDetailEntity = userDetailDao.getUserDetailByUserid(userid);
		if (userDetailEntity.getTodayVtimes() != 0) {
			userDetailDao.reduceTodayVtimes(userid);
		} else if (userDetailEntity.getExtraVtimes() != 0) {
			userDetailDao.reduceExtraVtimes(userid);
		} else {
			throw new OverVtimesException("缺少观看次数");
		}*/

		int redPacketRecord = redPacketDao.getCount(advid, userid);
		if (redPacketRecord == 0) {
			throw new RedPacketException("二次领取错误领取","1003");
		}

		AdvertisementDetailEntity advertisementDetailEntity = advertisementDetailDao.getDetailByAdvid(advid);
		if (advertisementDetailEntity.getSurplus() <= 0) {
			throw new RedPacketException("红包以领取完","1002");
		}
		
		double money = getMoney(advertisementDetailEntity);
		
		advertisementDetailEntity.setMoney(advertisementDetailEntity.getMoney()-money);
		advertisementDetailEntity.setSurplus(advertisementDetailEntity.getSurplus()-1);
		
		advertisementDetailDao.updateAfterGetRP(advertisementDetailEntity);
		
		/*advertisementDetailDao.reduceSurplus(advertisementDetailEntity.getId());*/
		/*double money = getMoney(advertisementDetailEntity);*/
		
		userService.addMoney(userid, money, "第二红包红包");
		//userDao.recharge(userid, money);

		RedPacketDetailEntity redPacketDetailEntity = new RedPacketDetailEntity();
		redPacketDetailEntity.setAdvid(advid);
		redPacketDetailEntity.setUserid(userid);
		redPacketDetailEntity.setMoney(money);
		redPacketDetailEntity.setTime(DateUtil.currentTimestamp());
		redPacketDao.secondInsert(redPacketDetailEntity);

		return redPacketDetailEntity;
	}

	@Override
	public int haveSecondRedPacket(int advid) {
		
		AdvertisementDetailEntity advertisementDetailEntity = advertisementDetailDao.getDetailByAdvid(advid);
		if (advertisementDetailEntity.getSurplus() <= 0) {
			return 1;
		}
		
		SecondRedPacketEntity secondRedPacketEntity = secondRedPacketDao.getSecondRedPacket();
		if(secondRedPacketEntity == null || secondRedPacketEntity.getForm() == 1) {
			return 1;
		}
		
		return RedPacketUtil.getProbability(secondRedPacketEntity.getProportion());
	}

}
