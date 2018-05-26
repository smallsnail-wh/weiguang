package com.wh.weiguang.controller.sys;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.wh.weiguang.model.sys.SecondRedPacketEntity;
import com.wh.weiguang.service.sys.SecondRedPacketService;

@RestController
public class SecondRedPacketController {

	@Autowired
	private SecondRedPacketService secondRedPacketService;
	
	@GetMapping("/admin/secondredpacket")
	public SecondRedPacketEntity entityGet() {
		return secondRedPacketService.getEntity();
	}

	@PutMapping("/admin/secondredpacket")
	public SecondRedPacketEntity entityUpdate(@RequestBody SecondRedPacketEntity secondRedPacketEntity) {
		secondRedPacketService.entityUpdate(secondRedPacketEntity);
		return secondRedPacketEntity;
	}
	
}
