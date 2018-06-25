package com.wh.weiguang.service.me.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wh.weiguang.alipay.comfig.AlipayUtil;
import com.wh.weiguang.dao.TransferDao;
import com.wh.weiguang.dao.UserDao;
import com.wh.weiguang.exception.TransferException;
import com.wh.weiguang.model.me.TransferRecordEntity;
import com.wh.weiguang.model.me.TransferRecordModel;
import com.wh.weiguang.model.sys.UserEntity;
import com.wh.weiguang.service.me.TransferService;
import com.wh.weiguang.service.sys.UserService;
import com.wh.weiguang.util.DateUtil;

@Service
public class TransferServiceImpl implements TransferService {

	@Autowired
	private TransferDao transferDao;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private UserDao userDao;

	@Override
	@Transactional
	public void applyTiXian(int userid, Double amount) {
		
		UserEntity userEntity = userDao.getUserEntityById(userid);
		if(userEntity.getMobile() == null || "".equals(userEntity.getMobile())) {
			throw new TransferException("必须绑定手机号", "503");
		}
		
		TransferRecordEntity transferRecordEntity = new TransferRecordEntity();
		if(!userService.consume(userid, amount, "提现申请")) {
			throw new TransferException("账号余额不足", "502");
		}
		
		transferRecordEntity.setMobile(userEntity.getMobile());
		transferRecordEntity.setAmount(amount);
		transferRecordEntity.setId(AlipayUtil.getOutTradeNo());
		transferRecordEntity.setSucc(0);
		transferRecordEntity.setUserid(userid);
		transferRecordEntity.setTime(DateUtil.currentTimestamp());
		
		transferDao.insertTransferRecord(transferRecordEntity);
	}

	@Override
	public List<TransferRecordModel> transferList(int pageSize, int start) {
		return transferDao.transferList(pageSize,start);
	}

	@Override
	public Integer transferSize() {
		return transferDao.transferSize();
	}

	@Override
	public TransferRecordEntity getTransferRecordById(String id) {
		return transferDao.getTransferRecordById(id);
	}

}
