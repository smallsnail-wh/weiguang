package com.wh.weiguang.controller.me;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wh.weiguang.model.me.RechargeRecordEntity;
import com.wh.weiguang.model.sys.PageResult;
import com.wh.weiguang.service.me.RechargService;
import com.wh.weiguang.util.SecurityAuthenUtil;

import io.swagger.annotations.ApiOperation;

@RestController
public class RechargeController {

	@Autowired
	private RechargService rechargService;
	
	@ApiOperation("支付宝充值")
	@PostMapping("/recharge/zhifubao")
	public RechargeRecordEntity zhifubaoRecharge(@RequestBody RechargeRecordEntity rechargeRecordEntity) {
		rechargeRecordEntity.setUserid(SecurityAuthenUtil.getId());
		rechargService.zhifubaoRecharge(rechargeRecordEntity);
		return rechargeRecordEntity;
	}
	
	@ApiOperation("微信充值")
	@PostMapping("/recharge/weixin")
	public RechargeRecordEntity weixinRecharge(@RequestBody RechargeRecordEntity rechargeRecordEntity) {
		rechargeRecordEntity.setUserid(SecurityAuthenUtil.getId());
		rechargService.weixinRecharge(rechargeRecordEntity);
		return rechargeRecordEntity;
	}
	
	@GetMapping("/admin/recharges")
	public PageResult rechargesList(int type, int pageSize, int page,String time) {
		PageResult pageResult = new PageResult();
		pageResult.setData(rechargService.rechargesList(type, pageSize, page * pageSize,time));
		pageResult.setTotalCount(rechargService.rechargesSize(type,time));
		return pageResult;
	}
	
	/**
	 * 总充值金额
	 * 
	 * @return
	 */
	@GetMapping("/recharge/count1")
	public Double getCount1() {

		return rechargService.getCount1();
	}

	/**
	 * 月充值金额
	 * 
	 * @return
	 */
	@GetMapping("/recharge/count2")
	public Double getCount2(@RequestParam("time") String time) {

		return rechargService.getCount2(time);
	}

	/**
	 * 日充值金额
	 * 
	 * @return
	 */
	@GetMapping("/recharge/count3")
	public Double getCount3(@RequestParam("time") String time) {

		return rechargService.getCount3(time);
	}

}
