package com.wh.weiguang.controller.me;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wh.weiguang.service.me.TransferService;

@RestController
public class TransferController {

	@Autowired
	private TransferService transferService;
	
	@PostMapping("/transfer/appil")
	public String applyTiXian() {
		
		
		
		return "SUCCESS";
	}
}
