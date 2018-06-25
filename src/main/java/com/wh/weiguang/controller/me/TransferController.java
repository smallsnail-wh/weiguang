package com.wh.weiguang.controller.me;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wh.weiguang.exception.TransferException;
import com.wh.weiguang.model.sys.PageResult;
import com.wh.weiguang.service.me.TransferService;
import com.wh.weiguang.util.SecurityAuthenUtil;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
public class TransferController {

	@Autowired
	private TransferService transferService;
	
	@ApiOperation("提现")
	@PostMapping("/transfer/appil")
	public String applyTiXian(@ApiParam("提现金额")@RequestParam("amount")Double amount) {
		if(amount == null) {
			throw new TransferException("提现金额必须大于1元", "501");
		}else if(amount < 1) {
			throw new TransferException("提现金额必须大于1元", "501");
		}
		transferService.applyTiXian(SecurityAuthenUtil.getId(),amount);
		
		return "SUCCESS";
	}
	
	@GetMapping("admin/transfer/list")
	public PageResult transferList( int pageSize, int page){
		PageResult pageResult = new PageResult();
		pageResult.setData(transferService.transferList( pageSize, page * pageSize));
		pageResult.setTotalCount(transferService.transferSize());
		return pageResult;
	}
}
