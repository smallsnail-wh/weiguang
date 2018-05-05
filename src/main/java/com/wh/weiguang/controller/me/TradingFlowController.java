package com.wh.weiguang.controller.me;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wh.weiguang.model.PageEntity;
import com.wh.weiguang.model.me.TradingFlowEntity;
import com.wh.weiguang.service.me.TradingFlowService;
import com.wh.weiguang.util.SecurityAuthenUtil;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
public class TradingFlowController {

	@Autowired
	private TradingFlowService tradingFlowService;

	@ApiOperation("站内交易流水查询")
	@GetMapping("/tradingflow")
	public List<TradingFlowEntity> getTradingFlow(
			@ApiParam(value = "分页的参数，每页多少数据") @RequestParam("pageSize") int pageSize,
			@ApiParam(value = "分页的参数，第几页,从0开始") @RequestParam("page") int page) {
		PageEntity pageEntity = new PageEntity(pageSize, page);
		return tradingFlowService.getListByUserid(SecurityAuthenUtil.getId(),pageEntity);
	}

}
