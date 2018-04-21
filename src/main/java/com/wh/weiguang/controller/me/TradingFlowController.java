package com.wh.weiguang.controller.me;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wh.weiguang.model.me.TradingFlowEntity;
import com.wh.weiguang.service.me.TradingFlowService;
import com.wh.weiguang.util.SecurityAuthenUtil;

@RestController
public class TradingFlowController {

	@Autowired
	private TradingFlowService tradingFlowService; 
	
	@GetMapping("/tradingflow")
	public List<TradingFlowEntity> getTradingFlow() {
		return tradingFlowService.getListByUserid(SecurityAuthenUtil.getId());
	}
	
}
