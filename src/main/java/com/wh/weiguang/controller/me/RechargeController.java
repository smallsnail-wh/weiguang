package com.wh.weiguang.controller.me;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wh.weiguang.model.me.RechargeRecordEntity;
import com.wh.weiguang.service.me.RechargService;
import com.wh.weiguang.util.SecurityAuthenUtil;

@RestController
@RequestMapping("/recharge")
public class RechargeController {

	@Autowired
	private RechargService rechargService;
	
	@PostMapping("/zhifubao")
	public RechargeRecordEntity zhifubaoRecharge(@RequestBody RechargeRecordEntity rechargeRecordEntity) {
		rechargeRecordEntity.setUserid(SecurityAuthenUtil.getId());
		rechargService.zhifubaoRecharge(rechargeRecordEntity);
		return rechargeRecordEntity;
	}
	
	@PostMapping("/weixin")
	public RechargeRecordEntity weixinRecharge(@RequestBody RechargeRecordEntity rechargeRecordEntity) {
		rechargeRecordEntity.setUserid(SecurityAuthenUtil.getId());
		rechargService.weixinRecharge(rechargeRecordEntity);
		return rechargeRecordEntity;
	}

}
